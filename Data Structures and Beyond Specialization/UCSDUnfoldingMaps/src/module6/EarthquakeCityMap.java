package module6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.GeoJSONReader;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.AbstractShapeMarker;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.MultiMarker;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.providers.MBTilesMapProvider;
import de.fhpotsdam.unfolding.providers.Microsoft;
import de.fhpotsdam.unfolding.utils.MapUtils;
import parsing.ParseFeed;
import processing.core.PApplet;

/** EarthquakeCityMap
 * An application with an interactive map displaying earthquake data.
 * Author: UC San Diego Intermediate Software Development MOOC team
 * @author Your name here
 * Date: July 17, 2015
 * */
public class EarthquakeCityMap extends PApplet {
	
	// We will use member variables, instead of local variables, to store the data
	// that the setUp and draw methods will need to access (as well as other methods)
	// You will use many of these variables, but the only one you should need to add
	// code to modify is countryQuakes, where you will store the number of earthquakes
	// per country.
	
	// You can ignore this.  It's to get rid of eclipse warnings
	private static final long serialVersionUID = 1L;

	// IF YOU ARE WORKING OFFILINE, change the value of this variable to true
	private static final boolean offline = false;
	
	/** This is where to find the local tiles, for working without an Internet connection */
	public static String mbTilesString = "blankLight-1-3.mbtiles";
	
	

	//feed with magnitude 2.5+ Earthquakes
	private String earthquakesURL = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/2.5_week.atom";
	
	// The files containing city names and info and country names and info
	private String cityFile = "city-data.json";
	private String countryFile = "countries.geo.json";
	
	// The map
	private UnfoldingMap map;
	
	// Markers for each city
	private List<Marker> cityMarkers;
	// Markers for each earthquake
	private List<Marker> quakeMarkers;

	// A List of country markers
	private List<Marker> countryMarkers;
	
	// NEW IN MODULE 5
	private CommonMarker lastSelected;
	private CommonMarker lastClicked;
	
	//for additional code I added
	private boolean isCity = false;
	private int countNearby = 0;
	private int averageMag = 0;
	private EarthquakeMarker recentEarthQuake;
	
	//for key pressed
	private boolean cDown = false;
	
	public void setup() {		
		// (1) Initializing canvas and map tiles
		size(900, 700, OPENGL);
		if (offline) {
		    map = new UnfoldingMap(this, 200, 50, 650, 600, new MBTilesMapProvider(mbTilesString));
		    earthquakesURL = "2.5_week.atom";  // The same feed, but saved August 7, 2015
		}
		else {
			map = new UnfoldingMap(this, 200, 50, 650, 600, new Microsoft.HybridProvider());
			// IF YOU WANT TO TEST WITH A LOCAL FILE, uncomment the next line
		    //earthquakesURL = "2.5_week.atom";
		}
		MapUtils.createDefaultEventDispatcher(this, map);
		
		// FOR TESTING: Set earthquakesURL to be one of the testing files by uncommenting
		// one of the lines below.  This will work whether you are online or offline
		//earthquakesURL = "test1.atom";
		//earthquakesURL = "test2.atom";
		
		// Uncomment this line to take the quiz
		earthquakesURL = "quiz2.atom";
		
		
		// (2) Reading in earthquake data and geometric properties
	    //     STEP 1: load country features and markers
		List<Feature> countries = GeoJSONReader.loadData(this, countryFile);
		countryMarkers = MapUtils.createSimpleMarkers(countries);
		
		//     STEP 2: read in city data
		List<Feature> cities = GeoJSONReader.loadData(this, cityFile);
		cityMarkers = new ArrayList<Marker>();
		for(Feature city : cities) {
		  cityMarkers.add(new CityMarker(city));
		}
	    
		//     STEP 3: read in earthquake RSS feed
	    List<PointFeature> earthquakes = ParseFeed.parseEarthquake(this, earthquakesURL);
	    quakeMarkers = new ArrayList<Marker>();
	    
	    for(PointFeature feature : earthquakes) {
		  //check if LandQuake
		  if(isLand(feature)) {
		    quakeMarkers.add(new LandQuakeMarker(feature));
		  }
		  // OceanQuakes
		  else {
		    quakeMarkers.add(new OceanQuakeMarker(feature));
		  }
	    }

	    // could be used for debugging
	    //printQuakes();
	 		
	    // (3) Add markers to map
	    //     NOTE: Country markers are not added to the map.  They are used
	    //           for their geometric properties
	    map.addMarkers(quakeMarkers);
	    map.addMarkers(cityMarkers);
	    
	    sortAndPrint(10);
	    
	}  // End setup
	
	
	public void draw() {
		background(0);
		map.draw();
		addKey();
		drawPopup();
		
	}
	
	private void drawPopup() {
		if(lastClicked != null && isCity == true) {
			fill(255, 250, 240);
			rect(0, 340, 200, 120);
			
			fill(150, 30, 30);
			text("City Info", 70, 350);
			
			fill(0);
			textSize(12);
			text("Nearby Quakes: " + countNearby, 10,370);
			if(countNearby == 0) {
				text("Average Magnitude: 0", 10,390);
			}else {
				text("Average Magnitude: " + averageMag/countNearby, 10,390);
			}
			
			if(recentEarthQuake != null) {
				textSize(12);
				String title = recentEarthQuake.getTitle();
				String firstPart = title.substring(0, title.indexOf("km")+2);
				String secondPart = title.substring(title.indexOf("km")+2);
				text("Recent Quake: " + firstPart, 10,410);
				text(secondPart, 5, 430);
			}else {
				text("Most Recent Quake: None", 10,410);
			}
		}
	}
	
	
	private void sortAndPrint(int num) {
		//make an array of earthQuakeMarkers from the quakeMarkers list
		EarthquakeMarker[] marker = new EarthquakeMarker[quakeMarkers.size()];
		int index = 0;
		for(Marker m : quakeMarkers) {
			marker[index] = (EarthquakeMarker)m;
			index++;
		}
		
		//bubbleSort(marker);
		//insertionSort(marker);
		//mergeSort(marker,0,marker.length-1);     //highPointers are always at the last index aka the length minus 1.
		quickSort(marker,0,marker.length-1);
		//selectionSort(marker);
		//Collections.sort(marker);
		
		if(num > marker.length) {
			num = marker.length;
		}
		
		for(int i  = 0; i < num; i++) {
			System.out.println(marker[i]);

		}
		
		int count = 0;
		float compare = marker[0].getMagnitude();
		for(EarthquakeMarker mark : marker) {
			float Mag = mark.getMagnitude();
			if(Mag == compare) {
				count++;
				if(count == 3) {
					break;
				}
			}else {
				compare = Mag;
				count = 0;
			}
		}
		
		System.out.println("Repeated 3 times largest " + compare);
		
	}
	
	
	
	private void bubbleSort(EarthquakeMarker[] m) {
		
		for(int i = 0; i < m.length - 1; i++) {
			for(int j = 0; j < m.length-1-i; j++) {
				if(m[j].compareTo(m[j+1]) > 0){
					EarthquakeMarker temp = m[j];
					m[j] = m[j+1];
					m[j+1] = temp;
				}
			}
		}
		
	}
	
	private void insertionSort(EarthquakeMarker[] m) {
		for(int i = 1; i < m.length-1; i++) {
			
			int checkPos = i;
			EarthquakeMarker temp = m[i];
			while(checkPos > 0 && temp.compareTo(m[checkPos-1]) < 0 ) {
				m[checkPos] = m[checkPos-1];
				checkPos--;
			}
			m[checkPos] = temp;
		}
	}
	
	private void selectionSort(EarthquakeMarker[] m) {
		
		for(int i = 0; i < m.length-1; i++) {
			float maxMag = m[i].getMagnitude();
			int index = i;
			for(int j = i + 1; j < m.length; j++) {
				if(m[j].getMagnitude() > maxMag) {
					maxMag = m[j].getMagnitude();
					index = j;
					
				}
			}
			
			EarthquakeMarker temp = m[i];
			m[i] = m[index];
			m[index] = temp;
			
		}
		
	}
	
	//sort largest to smallest
	//greater the magnitude, the more it comes before
	private void mergeSort(EarthquakeMarker[] m, int low, int high) {
		if(low < high) {
			int middle = (low+high)/2;
			mergeSort(m,low,middle);
			mergeSort(m,middle+1,high);
			merge(m,low,middle,high);
		}
	}
	
	private void merge(EarthquakeMarker[] m, int low, int mid, int high) {
		
		int lowPointer = low;
		int highPointer = mid+1;
		int correctLoc = low;
		
		EarthquakeMarker[] temp = new EarthquakeMarker[m.length];
		int index = 0;
		for(EarthquakeMarker marker : m) {
			temp[index] = marker;
			index++;
		}
		
		while(lowPointer <= mid && highPointer <= high) {
			if(temp[lowPointer].compareTo(temp[highPointer]) <= 0) {
				m[correctLoc] = temp[lowPointer];
				correctLoc++;
				lowPointer++;
			}else {
				m[correctLoc] = temp[highPointer];
				highPointer++;
				correctLoc++;
			}
		}
		
		while(lowPointer <= mid) {
			m[correctLoc] = temp[lowPointer];
			lowPointer++;
			correctLoc++;
		}
		
		
	}
	
	private void quickSort(EarthquakeMarker[] m, int left, int right) {
		
		if(left >= right) {
			return;
		}
		
		EarthquakeMarker pivotValue = m[(left+right)/2];
		int partitionIndex = partition(m,left,right,pivotValue);
		quickSort(m,left,partitionIndex-1);
		quickSort(m,partitionIndex,right);
		
	}
	
	private int partition(EarthquakeMarker[] m, int left, int right, EarthquakeMarker pivotValue) {
		
		while(left <= right) {
			
			while(m[left].compareTo(pivotValue) < 0) {
				left++;
			}
			
			while(m[right].compareTo(pivotValue) > 0) {
				right--;
			}
			
			if(left <= right) {
				EarthquakeMarker temp = m[left];
				m[left] = m[right];
				m[right] = temp;
				right--;
				left++;
			}
			
		}
		return left;
	}
	
	/** Event handler that gets called automatically when the 
	 * mouse moves.
	 */
	@Override
	public void mouseMoved()
	{
		// clear the last selection
		if (lastSelected != null) {
			lastSelected.setSelected(false);
			lastSelected = null;
		
		}
		selectMarkerIfHover(quakeMarkers);
		selectMarkerIfHover(cityMarkers);
		//loop();
	}
	
	// If there is a marker selected 
	private void selectMarkerIfHover(List<Marker> markers)
	{
		// Abort if there's already a marker selected
		if (lastSelected != null) {
			return;
		}
		
		for (Marker m : markers) 
		{
			CommonMarker marker = (CommonMarker)m;
			if (marker.isInside(map,  mouseX, mouseY)) {
				lastSelected = marker;
				marker.setSelected(true);
				return;
			}
		}
	}
	
	/** The event handler for mouse clicks
	 * It will display an earthquake and its threat circle of cities
	 * Or if a city is clicked, it will display all the earthquakes 
	 * where the city is in the threat circle
	 */
	@Override
	public void mouseClicked()
	{
		if (lastClicked != null) {
			unhideMarkers();
			lastClicked = null;
		}
		else if (lastClicked == null) 
		{
			checkEarthquakesForClick();
			if (lastClicked == null) {
				checkCitiesForClick();
			}
		}
	}
	
	//only allow c key to work and hide those city markers if lastClicked is
	//already null and you are not currently clicked on an earthquake marker
	//or city marker that is hiding some markers already
	public void keyPressed() {
		if(key == 'c' && cDown ==false && lastClicked == null) {
			cDown = true;
			for(Marker m : cityMarkers) {
				if(m.getLocation().getLat() < 50) {
					m.setHidden(true);
				}
			}
		}else if(key == 'c' && cDown == true & lastClicked == null) {
			cDown = false;
			for(Marker m : cityMarkers) {
				m.setHidden(false);
			}
		}
	}
	
	// Helper method that will check if a city marker was clicked on
	// and respond appropriately
	private void checkCitiesForClick()
	{
		if (lastClicked != null) return;
		// Loop over the earthquake markers to see if one of them is selected
		for (Marker marker : cityMarkers) {
			if (!marker.isHidden() && marker.isInside(map, mouseX, mouseY)) {
				lastClicked = (CityMarker)marker;
				isCity = true;
				countNearby = 0;
				recentEarthQuake = null;
				// Hide all the other earthquakes and hide
				for (Marker mhide : cityMarkers) {
					if (mhide != lastClicked) {
						mhide.setHidden(true);
					}
				}
				for (Marker mhide : quakeMarkers) {
					EarthquakeMarker quakeMarker = (EarthquakeMarker)mhide;
					if (quakeMarker.getDistanceTo(marker.getLocation()) 
							> quakeMarker.threatCircle()) {
						quakeMarker.setHidden(true);
					}else {
						countNearby++;
						averageMag += (int)(quakeMarker.getMagnitude());
						if(recentEarthQuake == null) {
							recentEarthQuake = quakeMarker;
						}else {
							if(quakeMarker.getStringProperty("age").equals("Past Hour")) {
								recentEarthQuake = quakeMarker;
							}else if(quakeMarker.getStringProperty("age").equals("Past Day")) {
								if(!recentEarthQuake.getStringProperty("age").equals("Past Day") && !recentEarthQuake.getStringProperty("age").equals("Past Hour")) {
									recentEarthQuake = quakeMarker;
								}
							}
						}
					}
				}
				return;
			}
		}		
	}
	
	// Helper method that will check if an earthquake marker was clicked on
	// and respond appropriately
	private void checkEarthquakesForClick()
	{
		if (lastClicked != null) return;
		// Loop over the earthquake markers to see if one of them is selected
		for (Marker m : quakeMarkers) {
			EarthquakeMarker marker = (EarthquakeMarker)m;
			if (!marker.isHidden() && marker.isInside(map, mouseX, mouseY)) {
				lastClicked = marker;
				isCity = false;
				// Hide all the other earthquakes and hide
				for (Marker mhide : quakeMarkers) {
					if (mhide != lastClicked) {
						mhide.setHidden(true);
					}
				}
				for (Marker mhide : cityMarkers) {
					if (mhide.getDistanceTo(marker.getLocation()) 
							> marker.threatCircle()) {
						mhide.setHidden(true);
					}
				}
				return;
			}
		}
	}
	
	// loop over and unhide all markers
	private void unhideMarkers() {
		for(Marker marker : quakeMarkers) {
			marker.setHidden(false);
		}
			
		for(Marker marker : cityMarkers) {
			marker.setHidden(false);
		}
	}
	
	// helper method to draw key in GUI
	private void addKey() {	
		// Remember you can use Processing's graphics methods here
		fill(255, 250, 240);
		
		int xbase = 25;
		int ybase = 50;
		
		rect(xbase, ybase, 150, 250);
		
		fill(0);
		textAlign(LEFT, CENTER);
		textSize(12);
		text("Earthquake Key", xbase+25, ybase+25);
		
		fill(150, 30, 30);
		int tri_xbase = xbase + 35;
		int tri_ybase = ybase + 50;
		triangle(tri_xbase, tri_ybase-CityMarker.TRI_SIZE, tri_xbase-CityMarker.TRI_SIZE, 
				tri_ybase+CityMarker.TRI_SIZE, tri_xbase+CityMarker.TRI_SIZE, 
				tri_ybase+CityMarker.TRI_SIZE);

		fill(0, 0, 0);
		textAlign(LEFT, CENTER);
		text("City Marker", tri_xbase + 15, tri_ybase);
		
		text("Land Quake", xbase+50, ybase+70);
		text("Ocean Quake", xbase+50, ybase+90);
		text("Size ~ Magnitude", xbase+25, ybase+110);
		
		fill(255, 255, 255);
		ellipse(xbase+35, 
				ybase+70, 
				10, 
				10);
		rect(xbase+35-5, ybase+90-5, 10, 10);
		
		fill(color(255, 255, 0));
		ellipse(xbase+35, ybase+140, 12, 12);
		fill(color(0, 0, 255));
		ellipse(xbase+35, ybase+160, 12, 12);
		fill(color(255, 0, 0));
		ellipse(xbase+35, ybase+180, 12, 12);
		
		textAlign(LEFT, CENTER);
		fill(0, 0, 0);
		text("Shallow", xbase+50, ybase+140);
		text("Intermediate", xbase+50, ybase+160);
		text("Deep", xbase+50, ybase+180);

		text("Past hour", xbase+50, ybase+200);
		
		fill(255, 255, 255);
		int centerx = xbase+35;
		int centery = ybase+200;
		ellipse(centerx, centery, 12, 12);

		strokeWeight(2);
		line(centerx-8, centery-8, centerx+8, centery+8);
		line(centerx-8, centery+8, centerx+8, centery-8);
		
		
	}

	
	
	// Checks whether this quake occurred on land.  If it did, it sets the 
	// "country" property of its PointFeature to the country where it occurred
	// and returns true.  Notice that the helper method isInCountry will
	// set this "country" property already.  Otherwise it returns false.
	private boolean isLand(PointFeature earthquake) {
		
		// IMPLEMENT THIS: loop over all countries to check if location is in any of them
		// If it is, add 1 to the entry in countryQuakes corresponding to this country.
		for (Marker country : countryMarkers) {
			if (isInCountry(earthquake, country)) {
				return true;
			}
		}
		
		// not inside any country
		return false;
	}
	
	// prints countries with number of earthquakes
	// You will want to loop through the country markers or country features
	// (either will work) and then for each country, loop through
	// the quakes to count how many occurred in that country.
	// Recall that the country markers have a "name" property, 
	// And LandQuakeMarkers have a "country" property set.
	private void printQuakes() {
		int totalWaterQuakes = quakeMarkers.size();
		for (Marker country : countryMarkers) {
			String countryName = country.getStringProperty("name");
			int numQuakes = 0;
			for (Marker marker : quakeMarkers)
			{
				EarthquakeMarker eqMarker = (EarthquakeMarker)marker;
				if (eqMarker.isOnLand()) {
					if (countryName.equals(eqMarker.getStringProperty("country"))) {
						numQuakes++;
					}
				}
			}
			if (numQuakes > 0) {
				totalWaterQuakes -= numQuakes;
				System.out.println(countryName + ": " + numQuakes);
			}
		}
		System.out.println("OCEAN QUAKES: " + totalWaterQuakes);
	}
	
	
	
	// helper method to test whether a given earthquake is in a given country
	// This will also add the country property to the properties of the earthquake feature if 
	// it's in one of the countries.
	// You should not have to modify this code
	private boolean isInCountry(PointFeature earthquake, Marker country) {
		// getting location of feature
		Location checkLoc = earthquake.getLocation();

		// some countries represented it as MultiMarker
		// looping over SimplePolygonMarkers which make them up to use isInsideByLoc
		if(country.getClass() == MultiMarker.class) {
				
			// looping over markers making up MultiMarker
			for(Marker marker : ((MultiMarker)country).getMarkers()) {
					
				// checking if inside
				if(((AbstractShapeMarker)marker).isInsideByLocation(checkLoc)) {
					earthquake.addProperty("country", country.getProperty("name"));
						
					// return if is inside one
					return true;
				}
			}
		}
			
		// check if inside country represented by SimplePolygonMarker
		else if(((AbstractShapeMarker)country).isInsideByLocation(checkLoc)) {
			earthquake.addProperty("country", country.getProperty("name"));
			
			return true;
		}
		return false;
	}

}

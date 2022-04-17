//public methods and variables can be accessed in any other class within entire program
//private methods and variables can only be accessed within same class
//protected methods and variables can be accessed in same class, sub classes, or within same package
//package (default) methods and variables can ONLY be accessed from classes within same package. Initialized without keyword.

package module3;

//Java utilities libraries
import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Comparator;
import java.util.List;

//Processing library
import processing.core.PApplet;

//Unfolding libraries
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.providers.MBTilesMapProvider;
import de.fhpotsdam.unfolding.providers.Microsoft;
import de.fhpotsdam.unfolding.utils.MapUtils;

//Parsing library
import parsing.ParseFeed;

/** EarthquakeCityMap
 * An application with an interactive map displaying earthquake data.
 * Author: UC San Diego Intermediate Software Development MOOC team
 * @author Your name here
 * Date: July 17, 2015
 * */
public class EarthquakeCityMap extends PApplet {
	
	//colors to use throughout program
	final int yellow = color(255, 255, 0);
    final int blue = color(0,0,255);
    final int red = color(255,0,0);
    final int green = color(0,255,0);

	// You can ignore this.  It's to keep eclipse from generating a warning.
	private static final long serialVersionUID = 1L;

	// IF YOU ARE WORKING OFFLINE, change the value of this variable to true
	private static final boolean offline = false;
	
	// Less than this threshold is a light earthquake
	public static final float THRESHOLD_MODERATE = 5;
	// Less than this threshold is a minor earthquake
	public static final float THRESHOLD_LIGHT = 4;

	/** This is where to find the local tiles, for working without an Internet connection */
	public static String mbTilesString = "blankLight-1-3.mbtiles";
	
	// The map
	private UnfoldingMap map;
	
	//feed with magnitude 2.5+ Earthquakes
	private String earthquakesURL = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/2.5_week.atom";

	
	public void setup() {
		size(950, 600, OPENGL);

		if (offline) {
		    map = new UnfoldingMap(this, 200, 50, 700, 500, new MBTilesMapProvider(mbTilesString));
		    earthquakesURL = "2.5_week.atom"; 	// Same feed, saved Aug 7, 2015, for working offline
		}
		else {
			map = new UnfoldingMap(this, 200, 50, 700, 500, new Microsoft.HybridProvider());
			// IF YOU WANT TO TEST WITH A LOCAL FILE, uncomment the next line
			//earthquakesURL = "2.5_week.atom";
		}
		
	    map.zoomToLevel(1);
	    MapUtils.createDefaultEventDispatcher(this, map);	
			
	    // The List you will populate with new SimplePointMarkers
	    List<Marker> markers = new ArrayList<Marker>();

	    //Use provided parser to collect properties for each earthquake
	    //PointFeatures have a getLocation method
	    List<PointFeature> earthquakes = ParseFeed.parseEarthquake(this, earthquakesURL);
	    for(PointFeature x: earthquakes) {
	    	SimplePointMarker simple = createMarker(x);
	    	markers.add(simple);
	    	
	    }
	    
	    //TODO (Step 3): Add a loop here that calls createMarker (see below) 
	    // to create a new SimplePointMarker for each PointFeature in 
	    // earthquakes.  Then add each new SimplePointMarker to the 
	    // List markers (so that it will be added to the map in the line below)
	    
	    
	    // Add the markers to the map so that they are displayed
	    map.addMarkers(markers);
	}
		
	/* createMarker: A suggested helper method that takes in an earthquake 
	 * feature and returns a SimplePointMarker for that earthquake
	 * 
	 * In step 3 You can use this method as-is.  Call it from a loop in the 
	 * setp method.  
	 * 
	 * TODO (Step 4): Add code to this method so that it adds the proper 
	 * styling to each marker based on the magnitude of the earthquake.  
	*/
	private SimplePointMarker createMarker(PointFeature feature)
	{  
		// Create a new SimplePointMarker at the location given by the PointFeature
		SimplePointMarker marker = new SimplePointMarker(feature.getLocation());
		
		Object magObj = feature.getProperty("magnitude");
		float mag = Float.parseFloat(magObj.toString());
		
		// Here is an example of how to use Processing's color method to generate 
	    // an int that represents the color yellow.  
		
		// TODO (Step 4): Add code below to style the marker's size and color 
	    // according to the magnitude of the earthquake.  
	    // Don't forget about the constants THRESHOLD_MODERATE and 
	    // THRESHOLD_LIGHT, which are declared above.
	    // Rather than comparing the magnitude to a number directly, compare 
	    // the magnitude to these variables (and change their value in the code 
	    // above if you want to change what you mean by "moderate" and "light")

	    
		//simple point marker inherits from marker interface, so we can use both simple point marker methods, and the methods from the marker interface that the simple point marker must use and override (remember like a contract).
	    //marker is an interface, SimplePointMarker is class that implements interface marker
		if(mag < THRESHOLD_LIGHT) {
	    	marker.setColor(blue);
	    	marker.setRadius(5);
	    	
	    }else if(mag > THRESHOLD_LIGHT && mag < THRESHOLD_MODERATE) {
	    	marker.setColor(yellow);
	    	marker.setRadius(8);
	    }else {
	    	marker.setColor(red);
	    	marker.setRadius(12);
	    }
	    
	    return marker;
	}
	
	public void draw() {
	    background(10);
	    map.draw();
	    addKey();
	}


	// helper method to draw key in GUI
	// TODO: Implement this method to draw the key
	private void addKey(){	
		// Remember you can use Processing's graphics methods here
		
		//rectangular legend
		fill(255, 250, 227);
		rect(25,50,150,150,0);
		
		fill(0,0,0);
		textSize(10);
		text("EarthQuake Key", 55, 70,1);
		
		//red earthquake marker
		fill(red);
		ellipse(50,107,12,12);
		fill(0,0,0);
		textSize(8);
		text("5.0 + Magnitude",75,110,1);
		
		//yellow earthquake marker
		fill(yellow);
		ellipse(50,135,10,10);
		fill(0,0,0);
		textSize(8);
		text("Between 4 and 5",75,140,1);
		
		//blue earthquake marker
		fill(blue);
		ellipse(50,165,8,8);
		fill(0,0,0);
		textSize(8);
		text("Below 4.0 Magnitude",75,170,1);
		
		
	
	}
}

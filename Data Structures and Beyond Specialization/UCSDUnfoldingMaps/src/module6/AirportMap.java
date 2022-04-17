package module6;

import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.data.ShapeFeature;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.SimpleLinesMarker;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import de.fhpotsdam.unfolding.utils.MapUtils;
import de.fhpotsdam.unfolding.geo.Location;
import parsing.ParseFeed;
import processing.core.PApplet;

/** An applet that shows airports (and routes)
 * on a world map.  
 * @author Adam Setters and the UC San Diego Intermediate Software Development
 * MOOC team
 *
 */
public class AirportMap extends PApplet {
	
	UnfoldingMap map;
	private List<Marker> airportList;
	List<Marker> routeList;
	
	private CommonMarker lastSelected;
	private CommonMarker lastClicked;
	
	
	public void setup() {
		// setting up PAppler
		size(800,600, OPENGL);
		
		// setting up map and default events
		map = new UnfoldingMap(this, 50, 50, 750, 550);
		MapUtils.createDefaultEventDispatcher(this, map);
		
		// get features from airport data
		List<PointFeature> features = ParseFeed.parseAirports(this, "airports.dat");
		
		// list for markers, hashmap for quicker access when matching with routes
		airportList = new ArrayList<Marker>();
		HashMap<Integer, Location> airports = new HashMap<Integer, Location>();
		
		// create markers from features
		for(PointFeature feature : features) {
			AirportMarker m = new AirportMarker(feature);
	
			m.setRadius(5);
			int alt = Integer.parseInt((String)m.getProperty("altitude"));
			if(alt > 0 && alt < 30) {
				airportList.add(m);
				airports.put(Integer.parseInt(feature.getId()), feature.getLocation());
				//put airports id matched with its location into the map
			}
			
			
			// put airport in hashmap with OpenFlights unique id for key
		
		}
		
		
		// parse route data
		List<ShapeFeature> routes = ParseFeed.parseRoutes(this, "routes.dat");
		routeList = new ArrayList<Marker>();
		for(ShapeFeature route : routes) {
			
			// get source and destination airportIds
			int source = Integer.parseInt((String)route.getProperty("source"));
			int dest = Integer.parseInt((String)route.getProperty("destination"));
			
			// get locations for airports on route
			if(airports.containsKey(source) && airports.containsKey(dest)) {
				route.addLocation(airports.get(source));
				route.addLocation(airports.get(dest));
				SimpleLinesMarker sl = new SimpleLinesMarker(route.getLocations(), route.getProperties());
				AirportMarker.routes.add(sl);
				routeList.add(sl);
			}
			
		
		//	System.out.println(sl.getProperties());
			
			//UNCOMMENT IF YOU WANT TO SEE ALL ROUTE
		}
		
		System.out.println(AirportMarker.routes.size());
		map.addMarkers(airportList);
		
		//UNCOMMENT IF YOU WANT TO SEE ALL ROUTES
		map.addMarkers(routeList);
		
	}
	
	public void draw() {
		background(0);
		map.draw();
		
	}
	
	public void mouseMoved() {
		if(lastSelected != null) {
			lastSelected.setSelected(false);
			lastSelected = null;
		}
		
		selectForHovers(airportList);
		
	}
	
	public void mouseClicked() {
		if(lastClicked != null) {
			unhideMarkers();
			lastClicked = null;
		}else if(lastClicked == null) {
			checkAirportsForClick();
		}
	}
	
	private void unhideMarkers() {
		for(Marker m : routeList) {
			m.setHidden(false);
		}
	}
	
	private void checkAirportsForClick() {
		if(lastClicked != null) {
			return;
		}
		
		for(Marker m : airportList) {
			CommonMarker marker = (CommonMarker)m;
			if(!marker.isHidden() && marker.isInside(map, mouseX, mouseY)) {
				lastClicked = marker;
				for(Marker mark : routeList) {
					SimpleLinesMarker sl = (SimpleLinesMarker)mark;
					String id = (String)lastClicked.getProperty("id");
					if(id.equals(sl.getStringProperty("source")) || id.equals(sl.getStringProperty("destination"))) {
						sl.setHidden(false);
					}else {
						sl.setHidden(true);
					}
				}
				return;
			}
		}
		
		
	}
	
	private void selectForHovers(List<Marker> marker) {
		if(lastSelected != null) {
			return;
		}
		
		for(Marker m : marker) {
			CommonMarker mark = (CommonMarker)m;
			if(mark.isInside(map, mouseX, mouseY)) {
				lastSelected = mark;
				mark.setSelected(true);
				return;
			}
		}
	}
	
	
	

}

package module6;

import java.util.*;

import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.marker.SimpleLinesMarker;
import processing.core.PGraphics;

/** 
 * A class to represent AirportMarkers on a world map.
 *   
 * @author Adam Setters and the UC San Diego Intermediate Software Development
 * MOOC team
 *
 */
public class AirportMarker extends CommonMarker {
	public static List<SimpleLinesMarker> routes = new ArrayList<SimpleLinesMarker>();
	
	public AirportMarker(Feature city) {
		super(((PointFeature)city).getLocation(), city.getProperties());
	
	}
	
	@Override
	public void drawMarker(PGraphics pg, float x, float y) {
		pg.fill(11);
		pg.ellipse(x, y, 5, 5);
		
		
	}

	@Override
	public void showTitle(PGraphics pg, float x, float y) {
		 // show rectangle with title
		String title = (String)getProperty("name");
		pg.pushStyle();
		pg.fill(255);
		pg.rect(x,y+5,pg.textWidth(title) + 6,30);
		pg.fill(0);
		pg.textSize(12);
		pg.text(title, x+2,y+18);
		
		int count = 0;
		String id = (String)getProperty("id");
		for(SimpleLinesMarker m : routes) {
			if(m.getStringProperty("source").equals(id) || m.getStringProperty("destination").equals(id)){
				count++;
			}	
		}
		
		//should really say involved in ___ routes, but ___ routes works as well
		pg.text(count + " routes",x+2, y+30);
		

		
		
		
	}
	
}

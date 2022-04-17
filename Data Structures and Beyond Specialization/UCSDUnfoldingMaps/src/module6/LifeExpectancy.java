package module6;

import processing.core.PApplet;
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.utils.MapUtils;
import parsing.ParseFeed;
import de.fhpotsdam.unfolding.providers.*;
import de.fhpotsdam.unfolding.providers.Google.*;

import java.util.List;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.GeoJSONReader;

import java.util.HashMap;


import de.fhpotsdam.unfolding.marker.Marker;

/**
 * Visualizes life expectancy in different countries. 
 * 
 * It loads the country shapes from a GeoJSON file via a data reader, and loads the population density values from
 * another CSV file (provided by the World Bank). The data value is encoded to transparency via a simplistic linear
 * mapping.
 */
public class LifeExpectancy extends PApplet {

	UnfoldingMap map;
	HashMap<String, Float> lifeExpMap;
	List<Feature> countries;
	List<Marker> countryMarkers;
	Marker lastSelected;

	public void setup() {
		size(800, 600, OPENGL);
		map = new UnfoldingMap(this, 50, 50, 700, 500, new Google.GoogleMapProvider());
		MapUtils.createDefaultEventDispatcher(this, map);

		// Load lifeExpectancy data
		lifeExpMap = ParseFeed.loadLifeExpectancyFromCSV(this,"LifeExpectancyWorldBank.csv");
		

		// Load country polygons and adds them as markers
		countries = GeoJSONReader.loadData(this, "countries.geo.json");
		countryMarkers = MapUtils.createSimpleMarkers(countries);
		map.addMarkers(countryMarkers);
		//System.out.println(countryMarkers.get(0).getId());
		
		// Country markers are shaded according to life expectancy (only once)
		shadeCountries();
		
		for(String name : lifeExpMap.keySet()) {
			System.out.println(name + " " + lifeExpMap.get(name));
		}
	}

	public void draw() {
		// Draw map tiles and country markers
		map.draw();
		
		
		for(Marker m : countryMarkers) {
			if(m.isSelected() == true) {
				String name = m.getStringProperty("name");
				String id = m.getId();
				if(lifeExpMap.containsKey(id)) {
					textSize(12);
					fill(0);
					rect(0,50,145,50);
					int lifeExp = (int)(lifeExpMap.get(id).floatValue());
					if(textWidth(name) > 145) {	
						String name1 = name.substring(0,name.indexOf(" ", 10));
						String name2 = name.substring(name.indexOf(" ", 10));
						fill(255);
						text(name1, 5, 65);
						text(name2, 5, 75);
						text("Life expectancy is " + lifeExp, 5, 95);
					}else {
						fill(255);
						text(name, 5,70);
						text("Life expectancy is " + lifeExp, 5, 90);
					}
				}else {
					fill(0);
					textSize(12);
					rect(0,50,145,50);
					if(textWidth(name) > 145) {
						String name1 = name.substring(0,name.indexOf(" ", 15));
						String name2 = name.substring(name.indexOf(" ", 15));
						fill(255);
						text(name1, 5, 65);
						text(name2, 5, 80);
						text("No data available", 5, 95);
					}else {
						fill(255);
						text(name, 5,70);
						text("No data available", 5, 90);
					}
				}
			}
		}
		
		
	}

	//Helper method to color each country based on life expectancy
	//Red-orange indicates low (near 40)
	//Blue indicates high (near 100)
	private void shadeCountries() {
		for (Marker marker : countryMarkers) {
			// Find data for country of the current marker
			String countryId = marker.getId();
			System.out.println(lifeExpMap.containsKey(countryId));
			if (lifeExpMap.containsKey(countryId)) {
				float lifeExp = lifeExpMap.get(countryId);
				// Encode value as brightness (values range: 40-90)
				int colorLevel = (int) map(lifeExp, 40, 90, 10, 255);
				marker.setColor(color(255-colorLevel, 100, colorLevel));
			}
			else {
				marker.setColor(color(150,150,150));
			}
		}
	}
	
	public void mouseMoved() {
		if(lastSelected != null) {
			lastSelected.setSelected(false);
			lastSelected = null;
		}
		
		selectIfHover(countryMarkers);
		
	}
	
	private void selectIfHover(List<Marker> marker) {
		for(Marker m : marker) {
			if(m.isInside(map, mouseX, mouseY)) {
				lastSelected = m;
				m.setSelected(true);
				return;
			}
		}
	}
	
	


}

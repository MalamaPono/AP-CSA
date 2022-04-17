package TravelingSalesPerson;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class MapTraverser {
	
	private ArrayList<String> visitedLocations;
	
	public MapTraverser() {
		visitedLocations = new ArrayList<String>();
	}
	
	public void addVisitedLocation(String location) {
		visitedLocations.add(location);
	}
	
	public String recentLocation() {
		return visitedLocations.get(visitedLocations.size()-1);
	}
	
	public List<String> getVisitedLcoations(){
		return visitedLocations;
	}

	public void setVisitedLocations(ArrayList<String> reset) {
		visitedLocations = reset;
	}
}

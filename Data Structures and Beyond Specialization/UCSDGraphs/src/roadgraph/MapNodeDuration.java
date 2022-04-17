package roadgraph;

import java.util.HashMap;
import geography.GeographicPoint;

public class MapNodeDuration implements Comparable {
	
	private GeographicPoint location;
	HashMap<MapNodeDuration, EdgeInfoDuration> neighbors;
	double durationElapsed;
	double durationFromGoal;
	
	//we could either keep the information of the distances of each node from the start node as its own HashMap data structure in the
	//MapGraph class which maps each node with its distance from the start node, or alternatively, we could just keep the info of how far
	//each node is from the start node within the object of the node itself, so whenever you have access to a particular node, it has 
	//a variable which holds the distance from the start node. This variable will be changed and edited so you can either input getters and
	//setters to the variable, or simply leave it as the package or public level so other files like the MapGraph class can access and modify the variable.
	
	
	public MapNodeDuration(GeographicPoint point) {
		this.location = point;
		neighbors = new HashMap<MapNodeDuration,EdgeInfoDuration>();
		durationElapsed = Double.MAX_VALUE;
		durationFromGoal = Double.MAX_VALUE;
	}
	
	//we use these getters here becuase we only want outside users to be able to see the neighbors and location of a MapNode, but not actually access the actual data and change it.
	public GeographicPoint getLocation() {
		return location;
	}
	
	public HashMap<MapNodeDuration,EdgeInfoDuration> getNeighbors(){
		return neighbors;
	}
	
	public int compareTo(Object o) {
		MapNodeDuration other = (MapNodeDuration)o;
		
		double combinedDistance = this.durationElapsed + this.durationFromGoal;
		double otherCombinedDistance = other.durationElapsed+ other.durationFromGoal;
		
		if(combinedDistance < otherCombinedDistance) {
			return -1;
		}else if (otherCombinedDistance < combinedDistance) {
			return 1;
		}else {
			return 0;
		}
		
	}

}

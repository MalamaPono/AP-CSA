/**
 * @author UCSD MOOC development team and YOU
 * 
 * A class which reprsents a graph of geographic locations
 * Nodes in the graph are intersections between 
 *
 */
package roadgraph;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.function.Consumer;

import geography.GeographicPoint;
import util.GraphLoader;

/**
 * @author UCSD MOOC development team and YOU
 * 
 * A class which represents a graph of geographic locations
 * Nodes in the graph are intersections between 
 *
 */
public class MapGraphDuration {
	
	int numVertices;
	int numEdges;
	HashMap<GeographicPoint, MapNodeDuration> nodeLocations;
	
	/** 
	 * Create a new empty MapGraph 
	 */
	public MapGraphDuration()
	{
		numVertices = 0;
		numEdges = 0;
		nodeLocations = new HashMap<GeographicPoint, MapNodeDuration>();
		
	}
	
	/**
	 * Get the number of vertices (road intersections) in the graph
	 * @return The number of vertices in the graph.
	 */
	public int getNumVertices()
	{
		return numVertices;
	}
	
	/**
	 * Return the intersections, which are the vertices in this graph.
	 * @return The vertices in this graph as GeographicPoints
	 */
	public Set<GeographicPoint> getVertices()
	{
		HashSet<GeographicPoint> vertices = (HashSet<GeographicPoint>) nodeLocations.keySet();
		return vertices;
		
	}
	
	/**
	 * Get the number of road segments in the graph
	 * @return The number of edges in the graph.
	 */
	public int getNumEdges()
	{
		return numEdges;
	}

	
	
	/** Add a node corresponding to an intersection at a Geographic Point
	 * If the location is already in the graph or null, this method does 
	 * not change the graph.
	 * @param location  The location of the intersection
	 * @return true if a node was added, false if it was not (the node
	 * was already in the graph, or the parameter is null).
	 */
	public boolean addVertex(GeographicPoint location)
	{
		if(nodeLocations.containsKey(location) || location == null) {
			return false;
		}
		
		MapNodeDuration node = new MapNodeDuration(location);
		nodeLocations.put(location,node);
		numVertices++;
		return true;
		
	}
	
	/**
	 * Adds a directed edge to the graph from pt1 to pt2.  
	 * Precondition: Both GeographicPoints have already been added to the graph
	 * @param from The starting point of the edge
	 * @param to The ending point of the edge
	 * @param roadName The name of the road
	 * @param roadType The type of the road
	 * @param length The length of the road, in km
	 * @throws IllegalArgumentException If the points have not already been
	 *   added as nodes to the graph, if any of the arguments is null,
	 *   or if the length is less than 0.
	 */
	public void addEdge(GeographicPoint from, GeographicPoint to, String roadName,
			String roadType, double length) throws IllegalArgumentException {
		
		
		if(nodeLocations.containsKey(from) == 
				false || nodeLocations.containsKey(to) == false || roadName == null
				|| roadType == null || length < 0) {
			throw new IllegalArgumentException();
		}
		
		MapNodeDuration start = nodeLocations.get(from);
		MapNodeDuration destination = nodeLocations.get(to);

		EdgeInfoDuration info = new EdgeInfoDuration(roadName,roadType,length);
		start.neighbors.put(destination, info);
		numEdges++;
		
	}
	
	private List<GeographicPoint> reconfigurePath(MapNodeDuration start, MapNodeDuration goal, HashMap<MapNodeDuration,MapNodeDuration> parents){
		
		LinkedList<GeographicPoint> path = new LinkedList<GeographicPoint>();
		
		MapNodeDuration current = goal;
		while(current != start) {
			path.addFirst(current.getLocation());
			current = parents.get(current);
		}
		
		path.addFirst(start.getLocation());
		return path;
		
	}
	
	/** Find the path from start to goal using Dijkstra's algorithm
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> dijkstra(GeographicPoint start, GeographicPoint goal) {
		// Dummy variable for calling the search algorithms
		// You do not need to change this method.
        Consumer<GeographicPoint> temp = (x) -> {};
        return dijkstra(start, goal, temp);
	}
	
	/** Find the path from start to goal using Dijkstra's algorithm
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @param nodeSearched A hook for visualization.  See assignment instructions for how to use it.
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	
	private void dijkstraResetDurations() {
		for(GeographicPoint key : nodeLocations.keySet()) {
			MapNodeDuration value = nodeLocations.get(key);
			value.durationElapsed = Double.MAX_VALUE;
			value.durationFromGoal = 0;
		}
	}
	
	public List<GeographicPoint> dijkstra(GeographicPoint start, 
										  GeographicPoint goal, Consumer<GeographicPoint> nodeSearched)
	{
		
		dijkstraResetDurations();
		
		if(start == null || goal == null) {
			System.out.println("no path exists");
			return new LinkedList<GeographicPoint>();
		}
		
		MapNodeDuration begin = nodeLocations.get(start);
		MapNodeDuration end = nodeLocations.get(goal);
		HashMap<MapNodeDuration,MapNodeDuration> parents = new HashMap<MapNodeDuration,MapNodeDuration>();
		
		boolean found = dijkstraSearch(begin,end,nodeSearched,parents);
		
		if(found == false) {
			System.out.println("no path exists");
			return new LinkedList<GeographicPoint>();
		}
		
		return reconfigurePath(begin,end,parents);
		
	}
	
	private boolean dijkstraSearch(MapNodeDuration start, MapNodeDuration goal, Consumer<GeographicPoint> nodeSearched, HashMap<MapNodeDuration,MapNodeDuration> parents) {
		
		HashSet<MapNodeDuration> visited = new HashSet<MapNodeDuration>();
		PriorityQueue<MapNodeDuration> queue = new PriorityQueue<MapNodeDuration>();
		start.durationElapsed = 0;
		queue.add(start);
		int nodesVisited = 0;
		
		boolean found = false;
		
		while(queue.isEmpty() == false) {
			MapNodeDuration current = queue.poll();
			nodesVisited++;
			if(current == goal) {
				found = true;
				break;
			}
			
			if(visited.contains(current) == false) {
				visited.add(current);
				nodeSearched.accept(current.getLocation());
				for(MapNodeDuration neighbor : current.neighbors.keySet()) {
					double edgeWeight = current.neighbors.get(neighbor).getDuration();
					if(current.durationElapsed + edgeWeight < neighbor.durationElapsed) {
						neighbor.durationElapsed = current.durationElapsed + edgeWeight;
						parents.put(neighbor, current);
						queue.add(neighbor);
					}
				}
			}
			
		}
		System.out.println(nodesVisited);
		return found;
	}

	/** Find the path from start to goal using A-Star search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> aStar(GeographicPoint start, GeographicPoint goal) {
		// Dummy variable for calling the search algorithms
        Consumer<GeographicPoint> temp = (x) -> {};
        return aStar(start, goal, temp);
	}
	
	/** Find the path from start to goal using A-Star search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @param nodeSearched A hook for visualization.  See assignment instructions for how to use it.
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	
	public void aStarResetDurations(MapNodeDuration goal) {
		for(GeographicPoint key : nodeLocations.keySet()) {
			MapNodeDuration value = nodeLocations.get(key);
			value.durationElapsed = Double.MAX_VALUE;
			double distanceFromGoal = key.distance(goal.getLocation());
			value.durationFromGoal = distanceFromGoal/50;
				//we are using 50 mph as the speed limit from a node to the goal, so we can underestimate the duration expected to go from the node to the goal. This is a good heuristic estimated cost to ensure we
				//never overestimate, but also to ensure we don't include nodes that are way far off from the shortest path, and only search ones that are reasonable and worthwhile to search and explore.
		}
	}
	
	public List<GeographicPoint> aStar(GeographicPoint start, 
											 GeographicPoint goal, Consumer<GeographicPoint> nodeSearched)
	{
		
		if(start == null || goal == null) {
			System.out.println("no path exists");
			return new LinkedList<GeographicPoint>();
		}
		
		MapNodeDuration begin = nodeLocations.get(start);
		MapNodeDuration end = nodeLocations.get(goal);
		HashMap<MapNodeDuration,MapNodeDuration> parents = new HashMap<MapNodeDuration,MapNodeDuration>();
		aStarResetDurations(end);
		
		boolean found = aStarSearch(begin,end,nodeSearched,parents);
		
		if(found == false) {
			System.out.println("no path exists");
			return new LinkedList<GeographicPoint>();
		}
		
		return reconfigurePath(begin,end,parents);
		
	}

	private boolean aStarSearch(MapNodeDuration start, MapNodeDuration goal, Consumer<GeographicPoint> nodeSearched,HashMap<MapNodeDuration,MapNodeDuration> parents) {
		
		HashSet<MapNodeDuration> visited = new HashSet<MapNodeDuration>();
		PriorityQueue<MapNodeDuration> queue = new PriorityQueue<MapNodeDuration>();
		
		start.durationElapsed = 0;
		start.durationFromGoal = 0;
		queue.add(start);
		boolean found = false;
		int nodesVisited = 0;
		
		while(queue.isEmpty() == false) {
			MapNodeDuration current = queue.poll();
			nodesVisited++;
			if(current == goal) {
				found = true;
				break;
			}
			
			if(visited.contains(current) == false) {
				visited.add(current);
				nodeSearched.accept(current.getLocation());
				for(MapNodeDuration neighbor : current.neighbors.keySet()) {
					double edgeWeight = current.neighbors.get(neighbor).getDuration();
					if(current.durationElapsed + edgeWeight < neighbor.durationElapsed) {
						neighbor.durationElapsed = current.durationElapsed + edgeWeight;
						parents.put(neighbor, current);
						queue.add(neighbor);
					}
				}
			}
			
		}
		System.out.println(nodesVisited);
		return found;
	}
	
	public static void main(String[] args)
	{
//		//testing bfs
//		System.out.print("Making a new map...");
//		MapGraph firstMap = new MapGraph();
//		System.out.print("DONE. \nLoading the map...");
//		GraphLoader.loadRoadMap("data/testdata/simpletest.map", firstMap);
//		System.out.println("DONE.");
//		
//		GeographicPoint start = new GeographicPoint(1,1);
//		GeographicPoint goal = new GeographicPoint(8,-1);
//		
//		List<GeographicPoint> path = firstMap.bfs(start,goal);
//		System.out.println(path);
		
		// You can use this method for testing.  
		
		/* Here are some test cases you should try before you attempt 
		 * the Week 3 End of Week Quiz, EVEN IF you score 100% on the 
		 * programming assignment.
		 */
		
		
		MapGraphDuration simpleTestMap = new MapGraphDuration();
		GraphLoader.loadRoadMap("data/testdata/simpletest.map", simpleTestMap);
		
		GeographicPoint testStart = new GeographicPoint(1.0, 1.0);
		GeographicPoint testEnd = new GeographicPoint(8.0, -1.0);
		
		System.out.println("Test 1 using simpletest: Dijkstra should be 9 and AStar should be 5");
		List<GeographicPoint> testroute = simpleTestMap.dijkstra(testStart,testEnd);
		System.out.println(testroute);
		List<GeographicPoint> testroute2 = simpleTestMap.aStar(testStart,testEnd);
		System.out.println(testroute2);
		
		MapGraphDuration testMap = new MapGraphDuration();
		GraphLoader.loadRoadMap("data/maps/utc.map", testMap);
		
		// A very simple test using real data
		testStart = new GeographicPoint(32.869423, -117.220917);
		testEnd = new GeographicPoint(32.869255, -117.216927);
		System.out.println("Test 2 using utc: Dijkstra should be 13 and AStar should be 5");
		testroute = testMap.dijkstra(testStart,testEnd);
		System.out.println(testroute);
		testroute2 = testMap.aStar(testStart,testEnd);
		System.out.println(testroute2);
		
		// A slightly more complex test using real data
		testStart = new GeographicPoint(32.8674388, -117.2190213);
		testEnd = new GeographicPoint(32.8697828, -117.2244506);
		System.out.println("Test 3 using utc: Dijkstra should be 37 and AStar should be 10");
		testroute = testMap.dijkstra(testStart,testEnd);
		System.out.println(testroute);
		testroute2 = testMap.aStar(testStart,testEnd);
		System.out.println(testroute2);
		
		
		/* Use this code in Week 3 End of Week Quiz */
		
		
		MapGraphDuration theMap = new MapGraphDuration();
		System.out.print("DONE. \nLoading the map...");
		GraphLoader.loadRoadMap("data/maps/utc.map", theMap);
		System.out.println("DONE.");

		GeographicPoint start = new GeographicPoint(32.8648772, -117.2254046);
		GeographicPoint end = new GeographicPoint(32.8660691, -117.217393);
		
		
		List<GeographicPoint> route = theMap.dijkstra(start,end);
		List<GeographicPoint> route2 = theMap.aStar(start,end);

	}
	
}

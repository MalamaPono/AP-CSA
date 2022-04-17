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
public class MapGraph {
	
	int numVertices;
	int numEdges;
	HashMap<GeographicPoint, MapNode> nodeLocations;
	
	/** 
	 * Create a new empty MapGraph 
	 */
	public MapGraph()
	{
		numVertices = 0;
		numEdges = 0;
		nodeLocations = new HashMap<GeographicPoint, MapNode>();
		
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
		
		MapNode node = new MapNode(location);
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
		
		MapNode start = nodeLocations.get(from);
		MapNode destination = nodeLocations.get(to);

		EdgeInfo info = new EdgeInfo(roadName,roadType,length);
		start.neighbors.put(destination, info);
		numEdges++;
		
	}
	

	/** Find the path from start to goal using breadth first search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @return The list of intersections that form the shortest (unweighted)
	 *   path from start to goal (including both start and goal).
	 */
	public List<GeographicPoint> bfs(GeographicPoint start, GeographicPoint goal) {
		// Dummy variable for calling the search algorithms
        Consumer<GeographicPoint> temp = (x) -> {};
        return bfs(start, goal, temp);
	}
	
	/** Find the path from start to goal using breadth first search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @param nodeSearched A hook for visualization.  See assignment instructions for how to use it.
	 * @return The list of intersections that form the shortest (unweighted)
	 *   path from start to goal (including both start and goal).
	 */
	
	
	public List<GeographicPoint> bfs(GeographicPoint start, 
			 					     GeographicPoint goal, Consumer<GeographicPoint> nodeSearched)
	{
		HashMap<MapNode, MapNode> parents = new HashMap<MapNode,MapNode>(); //maps a particular node to its parent. 
																		//So if you call the get method on a certain key(node), this map will return the parent of the node.
		MapNode begin = nodeLocations.get(start);
		MapNode end = nodeLocations.get(goal);
		
		if(begin == null || end == null) {
			System.out.println("No path exists");
			return new LinkedList<GeographicPoint>();
		}
		
		boolean found = bfsSearch(begin, end, nodeSearched, parents);
		
		if(found == false) {
			System.out.println("No path exists");
			return new LinkedList<GeographicPoint>();
		}
		
		return reconfigurePath(begin,end,parents);
		
		// Hook for visualization.  See writeup.
		//nodeSearched.accept(next.getLocation());
	}
	
	private boolean bfsSearch(MapNode start, MapNode goal, Consumer<GeographicPoint> nodeSearched, HashMap<MapNode,MapNode> parents) {
		
		//using a linked list to resemble our queue.
		Queue<MapNode> toExplore = new LinkedList<MapNode>();
		HashSet<MapNode> visited = new HashSet<MapNode>();
		toExplore.add(start);
		
		boolean found = false;
		
		while(toExplore.isEmpty() == false) {
			MapNode current = toExplore.poll();
			nodeSearched.accept(current.getLocation());
			if(current == goal) {
				found = true;
				break;
			}
			
			for(MapNode neighbor : current.neighbors.keySet()) {
				if(visited.contains(neighbor) == false) {
					parents.put(neighbor,current);
					toExplore.add(neighbor);
					visited.add(neighbor);
				}
				
				
			}
			
		}
		
		return found;
		
	}
	
	private List<GeographicPoint> reconfigurePath(MapNode start, MapNode goal, HashMap<MapNode,MapNode> parents){
		
		LinkedList<GeographicPoint> path = new LinkedList<GeographicPoint>();
		
		MapNode current = goal;
		while(current != start) {
			path.addFirst(current.getLocation());
			current = parents.get(current);
		}
		
		path.addFirst(start.getLocation());
		return path;
		
	}
	
//	//keep in mind that this dfs search will find a path between intersections, but will return the first one found which is not neccasarily 
	//the shortest path of nodes from start to goal like in bfs. Thats why we won't even use this dfs search in this implementation
	//and particular project becuase we are only looking for shortest paths in this project of finding routes between places on the earth. 
	
//	*refactored
//	public List<GeographicPoint> dfs(GeographicPoint start, 
//		     GeographicPoint goal, Consumer<GeographicPoint> nodeSearched)
//	{
//		HashMap<MapNode, MapNode> parents = new HashMap<MapNode,MapNode>(); //maps a particular node to its parent. 
//														//So if you call the get method on a certain key(node), this map will return the parent of the node.
//		MapNode begin = nodeLocations.get(start);
//		MapNode end = nodeLocations.get(goal);
//		
//		if(begin == null || end == null) {
//			System.out.println("No path exists");
//			return new LinkedList<GeographicPoint>();
//		}
//		
//		boolean found = dfsSearch(begin, end, nodeSearched, parents);
//		
//		if(found == false) {
//			System.out.println("No path exists");
//			return new LinkedList<GeographicPoint>();
//		}
//		
//		return reconfigurePath(begin,end,parents);
//		
//		// Hook for visualization.  See writeup.
//		//nodeSearched.accept(next.getLocation());
//	}
//	
//	private boolean dfsSearch(MapNode start, MapNode goal, Consumer<GeographicPoint> nodeSearched, HashMap<MapNode,MapNode> parents) {
//		
//		Stack<MapNode> stack = new Stack<MapNode>();
//		HashSet<MapNode> visited = new HashSet<MapNode>();
//		
//		boolean found = false;
//		stack.add(start);
//		
//		while(stack.isEmpty() == false) {
//			MapNode current = stack.pop();
//			nodeSearched.accept(current.location);
//			if(current == goal) {
//				found = true;
//				break;
//			}
//			
//			for(MapNode neighbor : current.neighbors.keySet()) {
//				if(visited.contains(neighbor) == false) {
//					visited.add(neighbor);
//					stack.push(neighbor);
//					parents.put(neighbor,current);
//				}
//			}
//		}
//		
//		return found;
//		
//	}

	
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
	
	private void dijkstraResetDistances() {
		
		for(GeographicPoint key : nodeLocations.keySet()) {
			
			MapNode value = nodeLocations.get(key);
			value.distanceFromGoal = 0;
			value.distanceTraveled = Double.MAX_VALUE;
		}
		
	}
	
	public List<GeographicPoint> dijkstra(GeographicPoint start, 
										  GeographicPoint goal, Consumer<GeographicPoint> nodeSearched)
	{
		dijkstraResetDistances();
		
		if(start == null || goal == null) {
			System.out.println("no path exists");
			return new LinkedList<GeographicPoint>();
		}
		
		MapNode begin = nodeLocations.get(start);
		MapNode end = nodeLocations.get(goal);
		HashMap<MapNode,MapNode> parents = new HashMap<MapNode,MapNode>();
		
		boolean found = dijkstraSearch(begin,end,nodeSearched,parents);
		
		if(found == false) {
			System.out.println("no path exists");
			return new LinkedList<GeographicPoint>();
		}
		
		return reconfigurePath(begin,end,parents);
		
	}
	
	private boolean dijkstraSearch(MapNode start, MapNode goal, Consumer<GeographicPoint> nodeSearched, HashMap<MapNode,MapNode> parents) {
		
		HashSet<MapNode> visited = new HashSet<MapNode>();
		PriorityQueue<MapNode> queue = new PriorityQueue<MapNode>();
		start.distanceTraveled = 0;
		queue.add(start);
		boolean found = false;
		
		int nodesVisited = 0;
		
		while(queue.isEmpty() == false) {
			MapNode current = queue.poll();
			nodesVisited++;
			
			if(current == goal) {
				found = true;
				break;
			}
			
			if(visited.contains(current) == false) {
				visited.add(current);
				nodeSearched.accept(current.getLocation());
				
				for(MapNode neighbor : current.neighbors.keySet()) {
					double edgeWeight = current.neighbors.get(neighbor).getLength();
					if(edgeWeight + current.distanceTraveled < neighbor.distanceTraveled) { //if the distance from start through current through this edge is less than the distance that neighbor already has from the start
						neighbor.distanceTraveled = edgeWeight + current.distanceTraveled; //(this distance may be through a different path, or just infinity to begin with), then change it and add it to queue. And change current to be the parent of this node.
						parents.put(neighbor,current);
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
	
	private void aStarResetDistances(MapNode goal) {
		
		for(GeographicPoint key : nodeLocations.keySet()) {
			
			MapNode value = nodeLocations.get(key);
			value.distanceTraveled = Double.MAX_VALUE;
			value.distanceFromGoal = value.getLocation().distance(goal.getLocation());
		}
		
	}
	
	public List<GeographicPoint> aStar(GeographicPoint start, 
											 GeographicPoint goal, Consumer<GeographicPoint> nodeSearched)
	{
		if(start == null || goal == null) {
			System.out.println("no path exists");
			return new LinkedList<GeographicPoint>();
		}
		
		MapNode begin = nodeLocations.get(start);
		MapNode end = nodeLocations.get(goal);
		HashMap<MapNode,MapNode> parents = new HashMap<MapNode,MapNode>();
		aStarResetDistances(end);
		
		boolean found = aStarSearch(begin,end,nodeSearched,parents);
		
		if(found == false) {
			System.out.println("no path exists");
			return new LinkedList<GeographicPoint>();
		}
		
		return reconfigurePath(begin,end,parents);
		//nodeSearched.accept(next.getLocation());
	}

	private boolean aStarSearch(MapNode start, MapNode goal, Consumer<GeographicPoint> nodeSearched,HashMap<MapNode,MapNode> parents) {
		
		HashSet<MapNode> visited = new HashSet<MapNode>();
		PriorityQueue<MapNode> queue = new PriorityQueue<MapNode>();
		
		start.distanceTraveled = 0;
		start.distanceFromGoal = 0;
		queue.add(start);
		int nodesVisited = 0;
		
		boolean found = false;
		
		while(queue.isEmpty() == false) {
			MapNode current = queue.poll();
			nodesVisited++;
			if(current == goal) {
				found = true;
				break;
			}
			
			if(visited.contains(current) == false) {
				visited.add(current);
				for(MapNode neighbor : current.neighbors.keySet()) {
					double edgeWeight = current.neighbors.get(neighbor).getLength();
					if(current.distanceTraveled + edgeWeight < neighbor.distanceTraveled) {
						neighbor.distanceTraveled = current.distanceTraveled + edgeWeight;
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
		
		/*
		MapGraph simpleTestMap = new MapGraph();
		GraphLoader.loadRoadMap("data/testdata/simpletest.map", simpleTestMap);
		
		GeographicPoint testStart = new GeographicPoint(1.0, 1.0);
		GeographicPoint testEnd = new GeographicPoint(8.0, -1.0);
		
		System.out.println("Test 1 using simpletest: Dijkstra should be 9 and AStar should be 5");
		List<GeographicPoint> testroute = simpleTestMap.dijkstra(testStart,testEnd);
		System.out.println(testroute);
		List<GeographicPoint> testroute2 = simpleTestMap.aStarSearch(testStart,testEnd);
		System.out.println(testroute2);
		
		MapGraph testMap = new MapGraph();
		GraphLoader.loadRoadMap("data/maps/utc.map", testMap);
		
		// A very simple test using real data
		testStart = new GeographicPoint(32.869423, -117.220917);
		testEnd = new GeographicPoint(32.869255, -117.216927);
		System.out.println("Test 2 using utc: Dijkstra should be 13 and AStar should be 5");
		testroute = testMap.dijkstra(testStart,testEnd);
		System.out.println(testroute);
		testroute2 = testMap.aStarSearch(testStart,testEnd);
		System.out.println(testroute2);
		
		// A slightly more complex test using real data
		testStart = new GeographicPoint(32.8674388, -117.2190213);
		testEnd = new GeographicPoint(32.8697828, -117.2244506);
		System.out.println("Test 3 using utc: Dijkstra should be 37 and AStar should be 10");
		testroute = testMap.dijkstra(testStart,testEnd);
		System.out.println(testroute);
		testroute2 = testMap.aStarSearch(testStart,testEnd);
		System.out.println(testroute2);
		*/
		
		/* Use this code in Week 3 End of Week Quiz */
		
		
		MapGraph theMap = new MapGraph();
		System.out.print("DONE. \nLoading the map...");
		GraphLoader.loadRoadMap("data/maps/utc.map", theMap);
		System.out.println("DONE.");

		GeographicPoint start = new GeographicPoint(32.8648772, -117.2254046);
		GeographicPoint end = new GeographicPoint(32.8660691, -117.217393);

		List<GeographicPoint> route = theMap.dijkstra(start,end);
		List<GeographicPoint> route2 = theMap.aStar(start,end);

	}
	
}

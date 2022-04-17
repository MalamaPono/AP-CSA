package TravelingSalesPerson;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MapGraph {
	
	//since the TSP problem utilizes the fact that the graph is fully connected, this means that the amount of edges is n(n-1) <----- if there are no self loops. Would be n^2 if each vertex also had a self loop to itself.
	//so it is really close to n^2 which is the size of an adjacency matrix representation with n vertices. Thus since we are
	//not wasting much space by using an adjacency matrix in a fully connected graph, I will use an adjacency matrix to 
	//model this problem to switch it up a bit from using an adjacency list which I have gotten very accustomed to 
	//and actually extremely good at. 
	
	private static int DEFAULT_SIZE = 5;
	
	private double[][] map;
	private HashMap<String,Integer> locations;
	private int numEdges;
	private int numVertices;
	
	public MapGraph() {
		
		map = new double[DEFAULT_SIZE][DEFAULT_SIZE];
		locations = new HashMap<String,Integer>();
		numEdges = 0;
		numVertices = 0;
		
	}
	
	private double[][] resizeArray() {
		double[][] temp = new double[numVertices*2][numVertices*2];
		for(int row = 0; row < map.length; row++) {
			for(int col = 0; col < map[0].length; col++) {
				temp[row][col] = map[row][col];
			}
		}
		return temp;
	}
	
	public void addVertex(String name) {
		if(map.length == numVertices) {
			map = resizeArray();
		}
		
		locations.put(name, numVertices);
		numVertices++;
	}
	
	public void addEdge(String start,String destination, double distance) {
		if(locations.keySet().contains(start) == false || locations.keySet().contains(destination) == false) {
			throw new IllegalArgumentException();
		}
		
		map[locations.get(start)][locations.get(destination)] = distance;
		numEdges++;
		
	}
	
	//uses breadth first search algorithm 
	public ArrayList<String> TSPBruteForce(String startLocation){
		
		Queue<MapTraverser> queue =  new LinkedList<MapTraverser>();
		
		MapTraverser start = new MapTraverser();
		start.addVisitedLocation(startLocation);
		queue.add(start);
		
		ArrayList<String> saved = new ArrayList<String>();
		double min = Double.MAX_VALUE;
		
		
		while(queue.isEmpty() == false) {
			MapTraverser current = queue.poll();
			
			if(current.getVisitedLcoations().size() == numVertices) {
				if(getDistanceTraveled(current.getVisitedLcoations()) < min) {
					min = getDistanceTraveled(current.getVisitedLcoations());
					saved = copyPath(current.getVisitedLcoations()); 
				}
			}else {
				
				for(String location : locations.keySet()) {
					if(current.getVisitedLcoations().contains(location) == false) {
						MapTraverser path = new MapTraverser();
						ArrayList<String> newPath = copyPath(current.getVisitedLcoations());
						newPath.add(location);
						path.setVisitedLocations(newPath);
						queue.add(path);
					}
				}
				
			}
			
		}
		saved.add(startLocation);
		System.out.println(getDistanceTraveled(saved));
		return saved;
		
	}

	private ArrayList<String> copyPath(List<String> path){
		ArrayList<String> ret = new ArrayList<String>();
		for(String x : path) {
			ret.add(x);
		}
		return ret;
	}
	
	//distance is in miles
	private double getDistanceTraveled(List<String> path) {
		double sum = 0;
		for(int i = 0; i < path.size(); i++) {
			
			String first; 
			String second;
			
			if(i == path.size() - 1) {
				first = path.get(i);
				second = path.get(0);
			}else {
				first = path.get(i);
				second = path.get(i+1);
			}
			
			sum += getEdgeDistance(first,second);
		}
		return sum;
	}
	
	private double getEdgeDistance(String start,String destination) { //helper method to get the distance between 2 locations on graph. This distance is the length of the edge that connects them. 
		if(locations.keySet().contains(start) == false || locations.keySet().contains(destination) == false) {
			throw new IllegalArgumentException();
		}
		
		return map[locations.get(start)][locations.get(destination)];
	}
	
	
	public ArrayList<String> greedyAlgorithm(String startLocation){
	
		
		ArrayList<String> path = new ArrayList<String>();
		HashSet<String> visitedLocations = new HashSet<String>();
		
		String currentLocation = startLocation;
		
		path.add(startLocation);
		visitedLocations.add(currentLocation);
		
		while(true) {
			double min = Double.MAX_VALUE;
			String closestLocation = "";
			for(String location: locations.keySet()) {
				
				if(visitedLocations.contains(location) == false) {
					if(map[locations.get(currentLocation)][locations.get(location)] < min) {
						min = map[locations.get(currentLocation)][locations.get(location)];
						closestLocation = location;
					}
				}
				
			}
			currentLocation = closestLocation;
			path.add(currentLocation);
			visitedLocations.add(currentLocation);
			
			if(visitedLocations.size() == numVertices) {
				break;
			}
		}
		path.add(startLocation);
		System.out.println(getDistanceTraveled(path));
		return path;
		
	}
	
	//heuristic means you don't quite know at all how well this optimization will perform. As in you don't know how good it can improve the solution
	//in the best case or on the average case in this case this twoOptHeuristic is guarenteed to not make the path worse, it will either keep it the same
	//or improve it, but we don't know how good this improvement is at all. 
	
	//aproximations on the other hand have a good sense of how it will optimize the solution, by doing at least this good, or at worst this bad
	//and on the average case this good. That type of thing.
	
	
	//Thinks like this 2-Opt Heuristic algorithm which uses the 2-change method are reusable algorithms of code and can be reused to solve other NP-Hard
	//problems or even regular problems just by using the same 2Opt algorithm concept and code with maybe a few tweeks and changes to exactly fit the problem at hand.
	public ArrayList<String> twoOptHeuristic(ArrayList<String> improveMe){
		
		//this ensures that whatever original path the user enters, will not be changed and they will still have acesss to the 
		//exact same path we are only modifying copies of their path and never changing anything original. 
		
		ArrayList<String> path = copyPath(improveMe);
		
		ArrayList<String> twoChange = findImprovingTwoChange(path);
		while(twoChange.isEmpty() == false){
	
			path = improvePath(path,twoChange);
			twoChange = findImprovingTwoChange(path);

		}
		System.out.println(getDistanceTraveled(path));
		return path;
	}
	
	private ArrayList<String> findImprovingTwoChange(ArrayList<String> path) {
		
		ArrayList<String> edgesToImprove = new ArrayList<String>();
		
		A:for(int i = 0; i < path.size()-3; i++) {
			for(int j = i+2; j < path.size()-1; j++) {
				double oldEdge1 = map[locations.get(path.get(i))][locations.get(path.get(i+1))];
				double oldEdge2 = map[locations.get(path.get(j))][locations.get(path.get(j+1))];
				
				double newEdge1 = map[locations.get(path.get(i))][locations.get(path.get(j))];
				double newEdge2 = map[locations.get(path.get(i+1))][locations.get(path.get(j+1))];
				
				if(newEdge1 + newEdge2 < oldEdge1 + oldEdge2) {
					
					edgesToImprove.add(path.get(i));
					edgesToImprove.add(path.get(i+1));
					edgesToImprove.add(path.get(j));
					edgesToImprove.add(path.get(j+1));
					break A;
					//return the 2 edges that can be improved. Then our other algortihms will actually go about finding the improved edges and changing the path accordingly
				}
				
			}
		}
		
		return edgesToImprove;
		
	}
																	//two change list is the 2 edges that must be fixed and imroved
	public ArrayList<String> improvePath(ArrayList<String> path, ArrayList<String> twoChange){
		ArrayList<String> fixed = new ArrayList<String>();
		fixed.add(twoChange.get(0));
		fixed.add(twoChange.get(2));
		fixed.add(twoChange.get(1));
		fixed.add(twoChange.get(3));
		
		int boundry1 = path.indexOf(fixed.get(2));
		int boundry2 = path.indexOf(fixed.get(1));
		
		ArrayList<String> firstPart = new ArrayList<String>();
		for(int i = 0; i < boundry1; i++) {
			firstPart.add(path.get(i));
		}
		
		ArrayList<String> secondPart = flipPath(boundry1,boundry2,path);
		
		ArrayList<String> thirdPart = new ArrayList<String>();
		for(int j = boundry2+1; j < path.size(); j++) {
			thirdPart.add(path.get(j));
		}
		
		return reconfigurePath(firstPart,secondPart,thirdPart);
		
	}
	
	private ArrayList<String> flipPath(int boundry1, int boundry2, ArrayList<String> path){
		ArrayList<String> ret = new ArrayList<String>();
		
		for(int i = boundry2; i >= boundry1; i--) {
			ret.add(path.get(i));
		}
		
		return ret;
	}
	
	private ArrayList<String> reconfigurePath(ArrayList<String> first, ArrayList<String> second, ArrayList<String> third){
		ArrayList<String> ret = new ArrayList<String>();
		
		for(String x : first) {
			ret.add(x);
		}
		
		for(String y : second) {
			ret.add(y);
		}
		
		for(String z : third) {
			ret.add(z);
		}
		
		return ret;
	}
	
	public static void main(String[] args) {
		
		MapGraph map = new MapGraph();
		map.addVertex("SD");
		map.addVertex("Lima");
		map.addVertex("Paris");
		map.addVertex("Chen");
		map.addVertex("Cairo");
		map.addVertex("Perth");
		map.addVertex("Beij");
		map.addVertex("Joha");
		
		map.addEdge("SD","Lima",6091);
		map.addEdge("SD","Paris",9144);
		map.addEdge("SD","Chen",14587);
		map.addEdge("SD","Cairo",12276);
		map.addEdge("SD","Perth",15078);
		map.addEdge("SD","Beij",10234);
		map.addEdge("SD","Joha",16575);
		
		map.addEdge("Lima","SD",6091);
		map.addEdge("Lima","Paris",10248);
		map.addEdge("Lima","Chen",17540);
		map.addEdge("Lima","Cairo",12414);
		map.addEdge("Lima","Perth",14924);
		map.addEdge("Lima","Beij",16637);
	 	map.addEdge("Lima","Joha",10872);
		
		map.addEdge("Paris","SD",9144);
		map.addEdge("Paris","Lima",10248);
		map.addEdge("Paris","Chen",8031);
		map.addEdge("Paris","Cairo",3210);
		map.addEdge("Paris","Perth",14269);
		map.addEdge("Paris","Beij",8212);
		map.addEdge("Paris","Joha",8225);
		
		map.addEdge("Chen","SD",14587);
		map.addEdge("Chen","Lima",17540);
		map.addEdge("Chen","Paris",8031);
		map.addEdge("Chen","Cairo",5360);
		map.addEdge("Chen","Perth",6276);
		map.addEdge("Chen","Beij",4615);
		map.addEdge("Chen","Joha",7133);
		
		map.addEdge("Cairo","SD",12276);
		map.addEdge("Cairo","Lima",12414);
		map.addEdge("Cairo","Paris",3210);
		map.addEdge("Cairo","Chen",5360);
		map.addEdge("Cairo","Perth",11258);
		map.addEdge("Cairo","Beij",7540);
		map.addEdge("Cairo","Joha",6260);
		
		map.addEdge("Perth","SD",15078);
		map.addEdge("Perth","Lima",14924);
		map.addEdge("Perth","Paris",14269);
		map.addEdge("Perth","Chen",6726);
		map.addEdge("Perth","Cairo",11258);
		map.addEdge("Perth","Beij",7985);
		map.addEdge("Perth","Joha",8308);
		
		map.addEdge("Beij","SD",10234);
		map.addEdge("Beij","Lima",16637);
		map.addEdge("Beij","Paris",8212);
		map.addEdge("Beij","Chen",4615);
		map.addEdge("Beij","Cairo",7540);
		map.addEdge("Beij","Perth",7985);
		map.addEdge("Beij","Joha",11699);
		
		map.addEdge("Joha","SD",16575);
		map.addEdge("Joha","Lima",10872);
		map.addEdge("Joha","Paris",8295);
		map.addEdge("Joha","Chen",7133);
		map.addEdge("Joha","Cairo",6260);
		map.addEdge("Joha","Perth",8308);
		map.addEdge("Joha","Beij",11699);
		
		
		ArrayList<String> shortestPath = map.TSPBruteForce("SD");
		System.out.println(shortestPath);
		
		ArrayList<String> greedyAlgorithm = map.greedyAlgorithm("SD");
		System.out.println(greedyAlgorithm);
		
		ArrayList<String> twoOptHeuristic = map.twoOptHeuristic(greedyAlgorithm);
		System.out.println(twoOptHeuristic);
		
//		ArrayList<String> twoChange = new ArrayList<String>();
//		twoChange.add("Paris");
//		twoChange.add("Cairo");
//		twoChange.add("Chen");
//		twoChange.add("Beij");
//		
//		ArrayList<String> improved = map.improvePath(greedyAlgorithm,twoChange);
//		System.out.println(improved);
		
		
//		ArrayList<String> test = new ArrayList<String>();
//		test.add("SD");
//		test.add("Beij");
//		test.add("Chen");
//		test.add("Perth");
//		test.add("Joha");
//		test.add("Cairo");
//		test.add("Paris");
//		test.add("Lima");
//		System.out.println(map.getDistanceTraveled(test));
		
		
	}
	

}

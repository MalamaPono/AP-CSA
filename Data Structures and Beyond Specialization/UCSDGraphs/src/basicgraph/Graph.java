
package basicgraph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import util.GraphLoader;

/** An abstract class that implements a directed graph. 
 * The graph may have self-loops, parallel edges. 
 * Vertices are labeled by integers 0 .. n-1
 * and may also have String labels.
 * The edges of the graph are not labeled.
 * Representation of edges is left abstract.
 * 
 * @author UCSD MOOC development team and YOU
 * 
 */

public abstract class Graph {

	private int numVertices;
	private int numEdges;
	//optional association of String labels to vertices 
	private Map<Integer,String> vertexLabels;
	
	/**
	 * Create a new empty Graph
	 */
	public Graph() {
		numVertices = 0;
		numEdges = 0;
		vertexLabels = null;
	}

	
	/**
	 * Report size of vertex set
	 * @return The number of vertices in the graph.
	 */
	public int getNumVertices() {
		return numVertices;
	}
	
	
	/**
	 * Report size of edge set
	 * @return The number of edges in the graph.
	 */	
	public int getNumEdges() {
		return numEdges;
	}
	
	/**
	 * Add new vertex to the graph.  This vertex will
	 * have as its index the next available integer.
	 * Precondition: contiguous integers are used to 
	 * index vertices.
	 * @return index of newly added vertex
	 */
	public int addVertex() {
		implementAddVertex();
		numVertices ++;
		return (numVertices-1);
	}
	
	/**
	 * Abstract method implementing adding a new
	 * vertex to the representation of the graph.
	 */
	public abstract void implementAddVertex();
	
	/**
	 * Add new edge to the graph between given vertices,
	 * @param v Index of the start point of the edge to be added. 
	 * @param w Index of the end point of the edge to be added. 
	 */
	public void addEdge(int v , int w) {
		if (v < numVertices && w < numVertices) {
			numEdges ++;
			implementAddEdge(v , w);			
		}
		else {
			throw new IndexOutOfBoundsException();
		}
	}
	
	/**
	 * Abstract method implementing adding a new
	 * edge to the representation of the graph.
	 */
	public abstract void implementAddEdge(int v, int w);
	
	/**
	 * Get all (out-)neighbors of a given vertex.
	 * @param v Index of vertex in question.
	 * @return List of indices of all vertices that are adjacent to v
	 * 	via outgoing edges from v. 
	 */
	public abstract List<Integer> getNeighbors(int v); 
	
	/**
	 * Get all in-neighbors of a given vertex.
	 * @param v Index of vertex in question.
	 * @return List of indices of all vertices that are adjacent to v
	 * 	via incoming edges to v. 
	 */
	public abstract List<Integer> getInNeighbors(int v);
	
	

	/** 
	 * The degree sequence of a graph is a sorted (organized in numerical order 
	 * from largest to smallest, possibly with repetitions) list of the degrees 
	 * of the vertices in the graph.
	 * 
	 * @return The degree sequence of this graph.
	 */
	public List<Integer> degreeSequence() {
		
		List<Integer> degrees = new ArrayList<Integer>();
		for(int i = 0; i < numVertices; i++) {
			int inDegree = getInNeighbors(i).size();
			int outDegree = getNeighbors(i).size();
			int degree = inDegree + outDegree;
			degrees.add(degree);
			
		}
		
		mergeSort(degrees,0,degrees.size()-1);
		return degrees;
		
	}
	
	private void mergeSort(List<Integer> num, int low, int high){
		
		if(low < high) {
			int mid = (low+high)/2;
			mergeSort(num,low,mid);
			mergeSort(num,mid+1,high);
			merge(num,low,mid,high);
		}
		
	}
	
	private void merge(List<Integer> num, int low, int middle, int high) {
		
		int lowPointer = low;
		int highPointer = middle+1;
		int correctLoc = low;
		
		ArrayList<Integer> temp = new ArrayList<Integer>();
		for(int x : num) {
			temp.add(x);
		}
		
		while(lowPointer <= middle && highPointer <= high) {
			
			if(temp.get(lowPointer) >= temp.get(highPointer)) {
				num.set(correctLoc, temp.get(lowPointer));
				lowPointer++;
				correctLoc++;
				
			}else {
				num.set(correctLoc, temp.get(highPointer));
				highPointer++;
				correctLoc++;
			}
			
		}
		
		//copy remaining elements from first half if any
		while(lowPointer <= middle) {
			num.set(correctLoc, temp.get(lowPointer));
			correctLoc++;
			lowPointer++;
		}
		
		//copy remaining elements from second half if any
		while(highPointer <= high) {
			num.set(correctLoc, temp.get(highPointer));
			correctLoc++;
			highPointer++;
		}
		
	}
	
	
	/**
	 * Get all the vertices that are 2 away from the vertex in question.
	 * @param v The starting vertex
	 * @return A list of the vertices that can be reached in exactly two hops (by 
	 * following two edges) from vertex v.
	 * XXX: Implement in part 2 of week 2 for each subclass of Graph
	 */
	public abstract List<Integer> getDistance2(int v); 

	/** Return a String representation of the graph
	 * @return A string representation of the graph
	 */
	public String toString() {
		String s = "\nGraph with " + numVertices + " vertices and " + numEdges + " edges.\n";
		s += "Degree sequence: " + degreeSequence() + ".\n";
		if (numVertices <= 20) s += adjacencyString();
		return s;
	}

	/**
	 * Generate string representation of adjacency list
	 * @return the String
	 */
	public abstract String adjacencyString();

	
	// The next methods implement labeled vertices.
	// Basic graphs may or may not have labeled vertices.
	
	/**
	 * Create a new map of vertex indices to string labels
	 * (Optional: only if using labeled vertices.)
	 */
	public void initializeLabels() {
		vertexLabels = new HashMap<Integer,String>();
	}	
	/**
	 * Test whether some vertex in the graph is labeled 
	 * with a given index.
	 * @param The index being checked
	 * @return True if there's a vertex in the graph with this index; false otherwise.
	 */
	public boolean hasVertex(int v)
	{
		return v < getNumVertices();
	}
	
	/**
	 * Test whether some vertex in the graph is labeled 
	 * with a given String label
	 * @param The String label being checked
	 * @return True if there's a vertex in the graph with this label; false otherwise.
	 */
	public boolean hasVertex(String s)
	{
		return vertexLabels.containsValue(s);
	}
	
	/**
	 * Add label to an unlabeled vertex in the graph.
	 * @param The index of the vertex to be labeled.
	 * @param The label to be assigned to this vertex.
	 */
	public void addLabel(int v, String s) {
		if (v < getNumVertices() && !vertexLabels.containsKey(v)) 
		{
			vertexLabels.put(v, s);
		}
		else {
			System.out.println("ERROR: tried to label a vertex that is out of range or already labeled");
		}
	}
	
	/**
	 * Report label of vertex with given index
	 * @param The integer index of the vertex
	 * @return The String label of this vertex 
	 */
	public String getLabel(int v) {
		if (vertexLabels.containsKey(v)) {
			return vertexLabels.get(v);
		}
		else return null;
	}

	/**
	 * Report index of vertex with given label.
	 * (Assume distinct labels for vertices.)
	 * @param The String label of the vertex
	 * @return The integer index of this vertex 
	 */
	public int getIndex(String s) {
		for (Map.Entry<Integer,String> entry : vertexLabels.entrySet()) {
			if (entry.getValue().equals(s))
				return entry.getKey();
		}
		System.out.println("ERROR: No vertex with this label");
		return -1;
	}
	

	
	/** Main method provided with some basic tests.  */
	public static void main (String[] args) {
		
//		GraphLoader.createIntersectionsFile("data/testdata/simpletest.map","data/intersections/simpletest.intersections");
//		
//		// For testing of Part 1 functionality
//		// Add your tests here to make sure your degreeSequence method is returning
//		// the correct list, after examining the graphs.
//		System.out.println("Loading graphs based on real data...");
//		System.out.println("Goal: use degree sequence to analyse graphs.");
//		
//		System.out.println("****");
//		System.out.println("Roads / intersections:");
//		GraphAdjList graphFromFile = new GraphAdjList();
//		GraphLoader.loadRoadMap("data/testdata/simpletest.map", graphFromFile);
//		System.out.println(graphFromFile);
//		
////		Flights Data 
//		
//		System.out.println("Flight data:");
//		GraphAdjList airportGraph = new GraphAdjList();
//		GraphLoader.loadRoutes("data/airports/routesUA.dat", airportGraph);
//		System.out.println(airportGraph);
//		System.out.println("Observe most degrees are small (1-30), eight are over 100.");
//		System.out.println("****");
//		
////		Road Intersections Data of roads near UCSD
//		
//		System.out.println("Road intersections data:");
//		GraphAdjList ucsdGraph = new GraphAdjList();
//		GraphLoader.loadRoadMap("data/maps/ucsd.map", ucsdGraph);
//		System.out.println(ucsdGraph);
//		
//		GraphAdjList list = new GraphAdjList();
//		list.addVertex();
//		list.addVertex();
//		list.addVertex();
//		list.addVertex();
//		list.addEdge(0, 1);
//		list.addEdge(0, 2);
//		list.addEdge(2, 1);
//		list.addEdge(2, 3);
//		list.addEdge(1, 3);
//		System.out.println(list.getDistance2(0));
//		System.out.println(list.getDistance2Easy(0));
//		
//		GraphAdjMatrix matrix = new GraphAdjMatrix();
//		matrix.addVertex();
//		matrix.addVertex();
//		matrix.addVertex();
//		matrix.addVertex();
//		matrix.addEdge(0, 1);
//		matrix.addEdge(0, 2);
//		matrix.addEdge(2, 1);
//		matrix.addEdge(2, 3);
//		matrix.addEdge(1, 3);
//		System.out.println(matrix.getDistance2(0));
//		System.out.println(matrix.getDistance2Easy(0));
//		
//		System.out.println(matrix.getDistance2Multiplication(0));
		
		GraphAdjList list = new GraphAdjList();
		list.addVertex();
		list.addVertex();
		list.addVertex();
		list.addVertex();
		list.addVertex();
		list.addEdge(1, 2);
		list.addEdge(1, 3);
		list.addEdge(2, 1);
		list.addEdge(2, 3);
		list.addEdge(3, 0);
		list.addEdge(3, 2);
		list.addEdge(3, 3);
		list.addEdge(4, 0);
		list.addEdge(4, 1);
		list.addEdge(4, 3);
		list.addEdge(4, 4);
		System.out.println(list.degreeSequence());

	}
}

/*
1. What will be the maximum sum of degrees of the road intersections graph?
Since it has 17 vertices we must find the maximum edges depending on if it is a undirected or directed graph and than multiply the number of
edges by 2 to get the maximum sum of all degrees. To simply find the highest degree of a vertex in the graph, we must be able to 
visuallize the graph with all its vertices and edges, and find the vertex with the highest degree and calculate that degree.


2. What will be the maximum degree of the nonstop flights graph?
Since it has 432 vertices we must find the maximum edges depending on if it is a undirected or directed graph and than multiply the number of
edges by 2 to get the maximum sum of all degrees. To simply find the highest degree of a vertex in the graph, we must be able to 
visuallize the graph with all its vertices and edges, and find the vertex with the highest degree and calculate that degree.

3. Which of these graphs will be more regular?
The road intersections graph will be more regular because most intersections are connected to the same roads as it is only a tight small
area near the UCSD campus. Whereas the flights data is full of flights connecting all over the place and in random orders
with different locations (vertices) all over the globe. 
 */

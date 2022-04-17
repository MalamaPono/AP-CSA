package basicgraph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/** A class that implements a directed graph. 
 * The graph may have self-loops, parallel edges. 
 * Vertices are labeled by integers 0 .. n-1
 * and may also have String labels.
 * The edges of the graph are not labeled.
 * Representation of edges via an adjacency matrix.
 * 
 * @author UCSD MOOC development team and YOU
 *
 */
public class GraphAdjMatrix extends Graph {

	private final int defaultNumVertices = 5;
	private int[][] adjMatrix;
	
	/** Create a new empty Graph */
	public GraphAdjMatrix () {
		adjMatrix = new int[defaultNumVertices][defaultNumVertices];
	}
	
	/** 
	 * Implement the abstract method for adding a vertex.
	 * If need to increase dimensions of matrix, double them
	 * to amortize cost. 
	 */
	public void implementAddVertex() {
		int v = getNumVertices();
		if (v >= adjMatrix.length) {
			int[][] newAdjMatrix = new int[v*2][v*2];
			for (int i = 0; i < adjMatrix.length; i ++) {
				for (int j = 0; j < adjMatrix.length; j ++) {
					newAdjMatrix[i][j] = adjMatrix[i][j];
				}
			}
			adjMatrix = newAdjMatrix;
		}
	}
	
	/** 
	 * Implement the abstract method for adding an edge.
	 * Allows for multiple edges between two points:
	 * the entry at row v, column w stores the number of such edges.
	 * @param v the index of the start point for the edge.
	 * @param w the index of the end point for the edge.  
	 */	
	public void implementAddEdge(int v, int w) {
		adjMatrix[v][w] += 1;
	}
	
	/** 
	 * Implement the abstract method for finding all 
	 * out-neighbors of a vertex.
	 * If there are multiple edges between the vertex
	 * and one of its out-neighbors, this neighbor
	 * appears once in the list for each of these edges.
	 * 
	 * @param v the index of vertex.
	 * @return List<Integer> a list of indices of vertices.  
	 */	
	public List<Integer> getNeighbors(int v) {
		List<Integer> neighbors = new ArrayList<Integer>();
		for (int i = 0; i < getNumVertices(); i ++) {
			for (int j=0; j< adjMatrix[v][i]; j ++) {
				neighbors.add(i);
			}
		}
		return neighbors;
	}
	
	/** 
	 * Implement the abstract method for finding all 
	 * in-neighbors of a vertex.
	 * If there are multiple edges from another vertex
	 * to this one, the neighbor
	 * appears once in the list for each of these edges.
	 * 
	 * @param v the index of vertex.
	 * @return List<Integer> a list of indices of vertices.  
	 */
	public List<Integer> getInNeighbors(int v) {
		List<Integer> inNeighbors = new ArrayList<Integer>();
		for (int i = 0; i < getNumVertices(); i ++) {
			for (int j=0; j< adjMatrix[i][v]; j++) {
				inNeighbors.add(i);
			}
		}
		return inNeighbors;
	}
	
	/** 
	 * Implement the abstract method for finding all 
	 * vertices reachable by two hops from v.
	 * Use matrix multiplication to record length 2 paths.
	 * 
	 * @param v the index of vertex.
	 * @return List<Integer> a list of indices of vertices.  
	 */	
	
	//Matrix Multiplication strategy from UCSD teachers. We are only working with square matrices when we are talking
	//about adjacency matrices so matrix multiplication will always be defined.
	public List<Integer> getDistance2Multiplication(int v){
		
		//matrix multiplication strategy actually results in a new matrix which is just the square of an original matrix
		//and this new matrix represents all the two hop paths for every vertex to another vertex and it will be more than 1 if there
		//are multiple 2 hop paths from one vertex to another.
		
		int[][] squared = squareArray(adjMatrix);
		List<Integer> distanceTwo = new ArrayList<Integer>();
		for(int i = 0; i < getNumVertices(); i++) {
			for(int j = 0; j < squared[v][i]; j++) {
				distanceTwo.add(i);
			}
		}
		
		return distanceTwo;
		
	}
	
	/*
	 KEEP IN MIND THAT THE ADJ MATRIX KEEPS EXTRA STORAGE SO REALLY YOU SHOULD NOT BE LOOPING OVER THE SIZE OF THE ADJ MATRIX, BUT
	 STRICTLY ONLY THE NUMBER OF VERTICES IN THE GRAPH BECUASE THE ACTUAL PART OF THE ADJ MATRIX YOU ONLY CARE ABOUT IS THE PART
	 THAT REPRESENTS THE GRAPH, THE REST IS JUST EXTRA SPACE IN THE CASE YOU WANT TO EXPAND THE GRAPH LATER AND IS OPTIMIZED
	 SO THAT YOU DON'T NEED TO KEEP RESIZING THE ADJ MATRIX. 
	 */
	
	private int[][] squareArray(int[][] arr){
		int[][] squared = new int[getNumVertices()][getNumVertices()];
		
		for(int row = 0; row < getNumVertices(); row++) {
			for(int col = 0; col < getNumVertices(); col++) {
				int[] one = getRow(arr,row);
				int[] two = getCol(arr,col);
				int dotProduct = dotProduct(one,two);
				squared[row][col] = dotProduct;
			}
		}
				
		return squared;
	}
	
	private int[] getRow(int[][] arr, int rowNum) {
		int[] row = new int[getNumVertices()];
		for(int i = 0; i < getNumVertices(); i++) {
			row[i] = arr[rowNum][i];
		}
		return row;
	}
	
	private int[] getCol(int[][] arr, int col) {
		int[] column = new int[getNumVertices()];
		for(int i = 0; i < getNumVertices(); i++) {
			column[i] = arr[i][col];
		}
		return column;
	}
	
	private int dotProduct(int[] one, int[] two) {
		int sum = 0;
		for(int i = 0; i < one.length; i++) {
			sum += one[i] * two[i];
		}
		return sum;
	}
	
	//My Own implementation without Matrix Multiplication, but hard coding using the adjacency matrix.
	public List<Integer> getDistance2(int v) {
		
		List<Integer> distanceOne = new ArrayList<Integer>();
		for(int i = 0; i < getNumVertices(); i++) {
			for(int j = 0; j < adjMatrix[v][i]; j++) {
				distanceOne.add(i);
			}
		}
		
		List<Integer> distanceTwo = new ArrayList<Integer>();
		for(int vertex : distanceOne) {
			for(int i = 0; i < getNumVertices(); i++) {
				for(int j = 0; j < adjMatrix[vertex][i]; j++) {
					distanceTwo.add(i);
				}
			}
		}
		
		return distanceTwo;
	}
	
	//My Own implementation without Matrix Multiplication. Easy Verison using past already made methods of getting Neighbors from a certain node.
	public List<Integer> getDistance2Easy(int v) {
		
		List<Integer> distanceOne = getNeighbors(v);
		List<Integer> distanceTwo = new ArrayList<Integer>();
		
		for(int vertex : distanceOne) {
			for(int secondVertex : getNeighbors(vertex)) {
				distanceTwo.add(secondVertex);
			}
		}
		
		return distanceTwo;
	}
	
	/**
	 * Generate string representation of adjacency matrix
	 * @return the String
	 */
	public String adjacencyString() {
		int dim = getNumVertices();
		String s = "Adjacency matrix";
		s += " (size " + dim + "x" + dim + " = " + dim* dim + " integers):";
		for (int i = 0; i < dim; i ++) {
			s += "\n\t"+i+": ";
			for (int j = 0; j < dim; j++) {
			s += adjMatrix[i][j] + ", ";
			}
		}
		return s;
	}

}

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.print.attribute.standard.PrinterLocation;

import java.util.Iterator;


/**
 * Filename:   GraphImpl.java
 * Project:    p4
 * Course:     cs400 
 * Authors:    Professors, Trevor Dinsmoor
 * Due Date:   11/17/2018
 * 
 * T is the label of a vertex, and List<T> is a list of
 * adjacent vertices for that vertex.
 *
 * Additional credits: 
 *
 * Bugs or other notes: 
 *
 * @param <T> type of a vertex
 */
public class GraphImpl<T> implements GraphADT<T> {

    // YOU MAY ADD ADDITIONAL private members // TODO - remove
    // YOU MAY NOT ADD ADDITIONAL public members
	
	/*
	 * inner node class that represents a vertex/node
	 * within the graph
	 */
	class GraphNode<T> { 
        private T data; // information about the node
        private boolean visited; // when traversing, whether or not this
        // node has been "visited"
        
        /*
         * constructor to create a node within the graph
         */
        private GraphNode (T value) {
        	data = value;
        	visited = false;
        } // constructor
        
        /*
         * @param newData the data to be set
         * sets the data in the node
         */
        private void setNodeData(T newData) {
        	this.data = newData;
        } // setNodedata()
        
        /*
         * @return the data in the node
         */
        private T getNodeData() {
        	return data;
        } // getNodeData()
        
        /*
         * sets the node to have been visited
         */
        private void setVisited() {
        	visited = true;
        } //setVisited()
        
    } // inner GraphNode class


    /**
     * Store the vertices and the vertice's adjacent vertices
     */
    private Map<T, List<T>> verticesMap; 
    
    
    /**
     * Construct and initialize and empty Graph
     */ 
    public GraphImpl() {
        verticesMap = new HashMap<T, List<T>>();
        // you may initialize additional data members here // TODO - do I need to?
    } // constructor

    /**
     * Add new vertex to the graph.
     *
     * If vertex is null or already exists,
     * method ends without adding a vertex or 
     * throwing an exception.
     * 
     * Valid argument conditions:
     * 1. vertex is non-null
     * 2. vertex is not already in the graph 
     * 
     * @param vertex the vertex to be added
     */
    public void addVertex(T vertex) {
    	if(vertex == null) {
    		System.out.println("Vertex was null. Not adding. ");
    		return;
    	}
    	
    	// add vertex and its prereqs to the hashmap
    	// check whether or not vertex already exists in map
    	if(verticesMap.containsKey(vertex)) {
    		System.out.println("Duplicate vertex");
    		return;
    	}
    	
    	// convert an array of prereqs to a list to be added/put
    	ArrayList<T> prereqList = new ArrayList<T>();
    	GraphNode<T> newNode = new GraphNode<T>(vertex);
    	verticesMap.put(newNode.getNodeData(), prereqList);
    	
    } // addVertex()
    
    /**
    * Remove a vertex and all associated 
    * edges from the graph.
    * 
    * If vertex is null or does not exist,
    * method ends without removing a vertex, edges, 
    * or throwing an exception.
    * 
    * Valid argument conditions:
    * 1. vertex is non-null
    * 2. vertex is not already in the graph 
    *  
    * @param vertex the vertex to be removed
    */
    public void removeVertex(T vertex) {
    	// if vertex argument is null
    	if(vertex == null) {
    		System.out.println("Vertex was null. Not adding. ");
    		return;
    	}
    	// if graph doesn't contain the vertex
    	else if(!verticesMap.containsKey(vertex)) {
    		System.out.println("Vertex is not in the graph so it cannot be removed. ");
    		return;
    	}
    	// Valid vertex argument
    	else {
    		verticesMap.remove(vertex);
    	} // else vertex is in graph

    } // removeVertex()
   
    /**
     * Add the edge from vertex1 to vertex2
     * to this graph.  (edge is directed and unweighted)
     * If either vertex does not exist,
     * no edge is added and no exception is thrown.
     * If the edge exists in the graph,
     * no edge is added and no exception is thrown.
     * 
     * Valid argument conditions:
     * 1. neither vertex is null
     * 2. both vertices are in the graph 
     * 3. the edge is not in the graph
     *  
     * @param vertex1 the first vertex (src)
     * @param vertex2 the second vertex (dst)
     */
    public void addEdge(T vertex1, T vertex2) {
    	// exit if either vertex is null
        if(vertex1 == null || vertex2 == null) {
        	return;
        }
    	
        // get the key-value pair for vertex
        List<T> prereqList = verticesMap.remove(vertex1);
        
        // if the edge already exists
        if(prereqList.contains(vertex2)) {
        	verticesMap.put(vertex1, prereqList);
        	return;
        }
        else {
            // it's a new edge to add
            prereqList.add(vertex2);
        	verticesMap.put(vertex1, prereqList);
        }
    	
    } // addEdge()
    
    /**
     * Remove the edge from vertex1 to vertex2
     * from this graph.  (edge is directed and unweighted)
     * If either vertex does not exist,
     * or if an edge from vertex1 to vertex2 does not exist,
     * no edge is removed and no exception is thrown.
     * 
     * Valid argument conditions:
     * 1. neither vertex is null
     * 2. both vertices are in the graph 
     * 3. the edge from vertex1 to vertex2 is in the graph
     *  
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     */
    public void removeEdge(T vertex1, T vertex2) {
    	// exit if either vertex is null
        if(vertex1 == null || vertex2 == null) {
        	return;
        }
    	// if either vertex doesn't exist in the graph
        if(!verticesMap.containsKey(vertex1) || !verticesMap.containsKey(vertex2)) {
        	return;
        }
        
        // otherwise vertices are valid
        // search for edge
        // get the key-value pair for vertex
        List<T> prereqList = verticesMap.remove(vertex1);
        // if the edge doesn't exist, quit
        if(!prereqList.contains(vertex2)) {
        	verticesMap.put(vertex1, prereqList);
        	return;
        }
        // otherwise the edge exists. It's time to remove it
        else {
        	prereqList.remove(vertex2);
        	verticesMap.put(vertex1, prereqList);
        }
	
    } // removeEdge()
    
    /**
     * Returns a Set that contains all the vertices
     * 
     * @return a java.util.Set<T> where T represents the vertex type
     */
    public Set<T> getAllVertices() {
        return verticesMap.keySet();
    } // getAllVertices()

    /**
     * Get all the neighbor (adjacent) vertices of a vertex
     * 
     * @param vertex the specified vertex
     * @return an List<T> of all the adjacent vertices for specified vertex
     */
    @SuppressWarnings("unchecked")
	public List<T> getAdjacentVerticesOf(T vertex) {
    	List<T> value = verticesMap.get(vertex);
    	return value;
    } // getAdjacentVerticesOf()
    
    /*
     * checks whether the vertex exists in the graph
     * 
     * @param vertex the specified value to search for
     * @return true if vertex is in the graph. false otherwise
     */
    public boolean hasVertex(T vertex) {
    	return verticesMap.containsKey(vertex);
    } // hasVertex()

    /**
     * Returns the number of vertices in this graph.
     * @return number of vertices in graph.
     */
    public int order() {
        return verticesMap.size();
    } // order()

    /**
     * Returns the number of edges in this graph.
     * @return number of edges in the graph.
     */
    public int size() {
    	int count = 0;
    	Collection<List<T>> list = verticesMap.values();
    	Object[] array = list.toArray();
    	for(int i = 0; i < array.length; i++) {
    		List<T> prereqs = (List<T>) array[i];
    		count = count + prereqs.size();
//    		System.out.println("Prereqs are " + prereqs);
//    		System.out.println("Count is " + count);
    	}
    	return count;
    } // size()
    
    /**
     * Prints the graph for the reference
     * DO NOT EDIT THIS FUNCTION
     * DO ENSURE THAT YOUR verticesMap is being used 
     * to represent the vertices and edges of this graph.
     */
    public void printGraph() {

        for ( T vertex : verticesMap.keySet() ) {
            if ( verticesMap.get(vertex).size() != 0) {
                for (T edges : verticesMap.get(vertex)) {
                    System.out.println(vertex + " -> " + edges + " ");
                }
            } else {
                System.out.println(vertex + " -> " + " " );
            }
        }
    } // printGraph()
    
    public static void main(String[] args) throws FileNotFoundException {


    	
    	
    	
	} // Main()
    
    
} // class GraphImpl 

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


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

    // YOU MAY ADD ADDITIONAL private members
    // YOU MAY NOT ADD ADDITIONAL public members

    /**
     * Store the vertices and the vertice's adjacent vertices
     */
    private Map<T, List<T>> verticesMap; 
    
    
    /**
     * Construct and initialize and empty Graph
     */ 
    public GraphImpl() {
        verticesMap = new HashMap<T, List<T>>();
        // you may initialize additional data members here
    } // constructor

    public void addVertex(T vertex) {
    	if(vertex == null) {
    		System.out.println("Vertex was null. Not adding. ");
    		return;
    	}
    	
    	
    	// add vertex and its prereqs to the hashmap
    	// verticesMap.put(vertex, value) // TODO - add prereqs too
    	// check whether or not vertex already exists in map
    	
    	
    	
    	
    } // addVertex()

    public void removeVertex(T vertex) {
        //TODO: implement this method
    } // removeVertex()

    public void addEdge(T vertex1, T vertex2) {
        //TODO: implement this method
    } // addEdge()
    
    public void removeEdge(T vertex1, T vertex2) {
        //TODO: implement this method
    } // removeEdge()
    
    public Set<T> getAllVertices() {
        //TODO: implement this method
        return null;
    } // getAllVertices()

    public List<T> getAdjacentVerticesOf(T vertex) {
        //TODO: implement this method
        return null;
    } // getAdjacentVerticesOf()
    
    public boolean hasVertex(T vertex) {
        //TODO: implement this method
        return false;
    } // hasVertex()

    public int order() {
        //TODO: implement this method
        return 0;
    } // order()

    public int size() {
        //TODO: implement this method
        return 0;
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

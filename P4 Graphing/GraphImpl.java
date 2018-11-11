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
    	// Entity current = new Entity();
    	// current.getPrerequisites();
    	ArrayList<T> preList = new ArrayList<T>();
		T [] entityArray = (T []) ((Entity) vertex).getPrerequisites();
		if(entityArray != null) {
			for (int i = 0; i < entityArray.length; i++) {
	    		preList.add(entityArray[i]);
	    	} // for
		}
    	
    	verticesMap.put(vertex, preList);
    	
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
    	
        // get array of prereqs from this entity
        Entity course1 = (Entity) vertex1;
        Entity course2 = (Entity) vertex2;
        String [] prereqs = (String[]) course1.getPrerequisites();
        String name = (String) course2.getName();
        
        // search for this edge
        // if it exists, leave method
    	for(int i = 0; i < prereqs.length; i++) {
    		if(prereqs[i] == name) {
    			return;
    		}
    	}
    	// otherwise it's a new, legitimate edge to add
    	int newLength = prereqs.length + 1;
    	String [] newPrereqs = new String [newLength];
    	// place values of old array into the new array
    	for(int i = 0; i < prereqs.length; i++) {
    		newPrereqs[i] = prereqs [i];
    	}
    	// add new edge
    	newPrereqs[newLength - 1] = name;
    	course1.setPrerequisites(newPrereqs);
    	
    	((Entity) vertex1).setPrerequisites(newPrereqs);
    	verticesMap.remove(vertex1);
    	ArrayList<T> preList = new ArrayList<T>();
		T [] entityArray = (T []) course1.getPrerequisites();
		if(entityArray != null) {
			for (int i = 0; i < entityArray.length; i++) {
	    		preList.add(entityArray[i]);
	    	} // for
		}
    	verticesMap.put((T) course1, preList);
    	
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
    	
        // get array of prereqs from this entity
        Entity course1 = (Entity) vertex1;
        Entity course2 = (Entity) vertex2;
        String [] prereqs = (String[]) course1.getPrerequisites();
        String name = (String) course2.getName();

        // search for this edge
        // if it exists, remove it
        // otherwise, return
    	for(int i = 0; i < prereqs.length; i++) {
    		if(prereqs[i] == name) {
    			prereqs[i] = null;
    			
    			// fix array
    			int newLength = prereqs.length - 1;
    	    	String [] newPrereqs = new String [newLength];
    			int k = 0;
    			for(int j = 0; j < prereqs.length; j++) {
    				if(prereqs[j] != null) {
    					newPrereqs[k] = prereqs[j];
    					k++;
    				}
    			}   			
    			course1.setPrerequisites(newPrereqs);
    			((Entity) vertex1).setPrerequisites(newPrereqs);   			
    			verticesMap.remove(vertex1);
    	    	ArrayList<T> preList = new ArrayList<T>();
    			T [] entityArray = (T []) course1.getPrerequisites();
    			if(entityArray != null) {
    				for (int a = 0; a < entityArray.length; a++) {
    		    		preList.add(entityArray[a]);
    		    	} // for
    			}
    	    	verticesMap.put((T) course1, preList);
    			
    			return;
    		}
    	} // for looking for course name
    	
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
		GraphImpl<Entity> testGraph = new GraphImpl<Entity>();
    	Entity testCourse = new Entity();
    	testCourse.setName("TJD 300");
    	String [] prereqs = new String [] {"TJD 100", "TJD 107", "TJD 202"};
    	testCourse.setPrerequisites(prereqs);
    	testGraph.addVertex(testCourse);
    	
    	Entity testCourse2 = new Entity();
    	testCourse2.setName("TJD 450");
    	String [] prereqs2 = new String [] {"TJD 233"};
    	testCourse2.setPrerequisites(prereqs2);
    	testGraph.addVertex(testCourse2);
    	
    	Entity testCourse3 = new Entity();
    	testCourse3.setName("TJD 107");
    	String [] prereqs3 = null;
    	testCourse3.setPrerequisites(prereqs3);
    	testGraph.addVertex(testCourse3);

    	
    	
    	
	} // Main()
    
    
} // class GraphImpl 

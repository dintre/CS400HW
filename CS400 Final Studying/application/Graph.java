package application;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Graph<T> {

	// graph class variables
	/**
    * Store the vertices and the vertice's adjacent vertices
    */
    private Map<T, List<T>> verticesMap; 
	
	// constructs a graph instance
    public Graph(){
    	verticesMap = new HashMap<T, List<T>>();
    } // constructor for a graph

    // adds a vertex
    public void addVertex(T value) {
    	if(value == null) {
    		return;
    	}
    	if(verticesMap.containsKey(value)) {
    		return;
    	}
    	// otherwise add it.
    	ArrayList<T> prereqs = new ArrayList<T>();
    	verticesMap.put(value, prereqs);  	
    } // addVertex()
    
    // removes a vertex
    public void removeVertex(T value) {
    	if(value == null) {
    		return;
    	}
    	if(!verticesMap.containsKey(value)) {
    		return;
    	}
    	// otherwise remove it
    	verticesMap.remove(value);
    } // removeVertex()
    
    // gets all of the graph's vertices
    public Set<T> getAllVertices() {
    	return verticesMap.keySet();
    } // getAllVertices()
    
    // gets all predecessors of a vertex
    public List<T> getPrereqsOf(T vertex) {
    	if(vertex == null) {
    		return null;
    	}
    	if(!verticesMap.containsKey(vertex)) {
    		return null;
    	}
    	// otherwise find the predecessors
    	return verticesMap.get(vertex);
    } // getPrereqsOf()
    
    // gets all of the predessors in the graph
    public Collection<List<T>> getAllPredecessors(){
    	return verticesMap.values();
    } // getAllPredecessors()

    // adds a predecessor
    public void addPrereq(T vertex, T prereq) {
    	if(!verticesMap.containsKey(vertex)) {
    		return;
    	}
    	// otherwise add prereq
    	
    	// get whatever prereqs are already there
    	ArrayList<T> prereqList = (ArrayList<T>) verticesMap.get(vertex);
    	// check if this one is already there
    	if(prereqList.contains(prereq)) {
    		return; // no need to add
    	}
    	else {
    		prereqList.add(prereq);
    	}
    	// replace the key - value pair
    	verticesMap.put(vertex, prereqList);
    } // addPrereqs()
    
    // removes a predecessor
	public void removePrereq(T vertex, T prereq) {
    	if(!verticesMap.containsKey(vertex)) {
    		return;
    	}
    	// get whatever prereqs are already there
    	ArrayList<T> prereqList = (ArrayList<T>) verticesMap.get(vertex);
    	// check if this one is already there
    	if(!prereqList.contains(prereq)) {
    		return; // no need to add
    	}
    	else {
    		prereqList.remove(prereq);
    	}
    	// replace the key - value pair
    	verticesMap.put(vertex, prereqList);	
	} // removePrereq()
	
	public boolean graphContains(T vertex) {
		if(verticesMap.containsKey(vertex)) {
			return true;
		}
		return false;
	} // graphContains()
	
	// main method for testing
	public static void main(String[] args) {
		Graph<String> courseGraph = new Graph<String>();
		courseGraph.addVertex("CS400");
		courseGraph.addVertex("CS300");
		courseGraph.addVertex("CS540");
		System.out.println("Graph after adding 3 vertices, no prereqs: ");

		courseGraph.addPrereq("CS300", "CS200");
		courseGraph.addPrereq("CS400", "CS300");
		courseGraph.addPrereq("CS540", "CS400");
		courseGraph.addPrereq("CS540", "CS369");
		System.out.println("Graph after adding the prereqs: ");
		courseGraph.printGraph();


		
		
		
	} // Main()
	
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
    
} // class Graph

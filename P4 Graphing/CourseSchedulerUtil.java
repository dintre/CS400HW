
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Spliterator;
import java.util.Stack;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


/**
 * Filename:   CourseSchedulerUtil.java
 * Project:    p4
 * Authors:    Debra Deppeler, Trevor Dinsmoor
 * 
 * Use this class for implementing Course Planner
 * @param <T> represents type
 */

public class CourseSchedulerUtil<T> {
    
    // can add private but not public members
	/*
	 * inner stack class
	 */
	private class CourseStack<T> {
		// fields
		private int size;
		private Entity[] position;
		
		/*
		 * constructor
		 */
		private CourseStack(int n) {
			position = new Entity[n];
		} // constructor
		
		private boolean isEmpty() {
			if(size == 0) {
				return true;
			}
			else {
				return false;
			}
		} // isEmpty()
		
		private void push(Entity newNode) {
			if(isEmpty()) {
				position[0] = newNode;
			}
			else {
				// place item on top of stack (index 0)
				// shift other positions on the stack
				for (int index = size; index > 0; index--){
					// index starts at the first open position on the stack
					// shift the bottom item 1 further
				position[index] = position[index - 1];
				} // for
				// done making room for the top item on the stack
				// place item on the top of the stack
				position[0] = newNode;
				// increment size
				size++;	
			}
		} // push()
		
		private Entity pop() {
			Entity node = position[0];
			// shift rest of stack into array index at 0
			// through whatever index is necessary
			for(int i = 0; i < size; i++) {
				// set position at index to position at index + 1
				position[i] = position[i + 1];
			} // for
			// done shifting
			// clear the former lowest position on the stack
			position[size - 1] = null;	
			// decrement size
			size--;
			return node;
		} // pop()
		
	}// inner CourseStack class
	
    
    /**
     * Graph object
     */
    private GraphImpl<T> graphImpl;
    
    
    /**
     * constructor to initialize a graph object
     */
    public CourseSchedulerUtil() {
        this.graphImpl = new GraphImpl<T>();
    } // constructor
    
    /**
    * createEntity method is for parsing the input json file 
    * @return array of Entity object which stores information 
    * about a single course including its name and its prerequisites
    * @throws Exception like FileNotFound, JsonParseException
    */
    @SuppressWarnings("rawtypes")
    public Entity[] createEntity(String fileName) throws Exception {
    	
		try {
	        // parsing fileName
	        Object obj = new JSONParser().parse(new FileReader(fileName)); 
			// typecasting obj to JSONObject - courses object
			JSONObject jo = (JSONObject) obj; 
	        // creating array - list of courses
	        JSONArray courses = (JSONArray) jo.get("courses");	
	        
	        // iterates through array within a course
	        Iterator itr = courses.iterator();
	        
	        Entity [] entities = new Entity[courses.size()]; // array to be returned
	        
			int courseIndex = 0;
	        // iterates through courses array
	        // each time it's a different course
			while(itr.hasNext()) {
				// setting up for defining and parsing prerequisites
				JSONObject courseObj = (JSONObject) itr.next();
				JSONArray set = (JSONArray) courseObj.get("prerequisites");
				Iterator prereqIterator = set.iterator();
				String [] prerequisites = new String [set.size()]; // array to store prereqs
				
				int prereqIndex = 0; // index for setting prereq array values
				
				while(prereqIterator.hasNext()) {
					String prereqValue = (String) prereqIterator.next();
					prerequisites[prereqIndex] = prereqValue;
					prereqIndex++; // increment index
				} // inner while

				String nameValue = (String) courseObj.get("name");
				Entity newCourse = new Entity();
				newCourse.setName(nameValue);
				newCourse.setPrerequisites(prerequisites);
		        entities[courseIndex] = newCourse;
		        courseIndex++; // increment index since done parsing this course
			} // while
			
			return entities; // array of new entities (courses) being returned
	
		} // try
		
		catch(Exception e) {
			System.out.println("Caught exception while parsing JSON file. ");
			System.out.println(e);
			e.printStackTrace();
			return null; // no array to return if there is an exception
		} // catch		
	
	} // createEntity()
    
    /**
     * Construct a directed graph from the created entity object 
     * @param entities which has information about a single course 
     * including its name and its prerequisites
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void constructGraph(Entity[] entities) {
    	graphImpl = new GraphImpl();
    	for(int i = 0; i < entities.length; i++) {
        	graphImpl.addVertex((T) entities[i].getName());
        	Object[] array = entities[i].getPrerequisites();
        	for(int j = 0; j < array.length; j++) {
        		graphImpl.addEdge((T) entities[i].getName(), (T) array[j]);
        	}
    	}	
    	
    } // constructGraph()
    
    
    /**
     * Returns all the unique available courses, including non-JSON object ones
     * @return the set of all available courses
     */
    public Set<T> getAllCourses() {
    	Set<T> courseSet = graphImpl.getAllVertices();
    	Iterator itr = courseSet.iterator();
    	ArrayList<T> array = new ArrayList<T>();

    	while(itr.hasNext()) {
    		T course = (T) itr.next();
    		array.add(course);
    		List<T> prereqList = graphImpl.getAdjacentVerticesOf(course);
    		for(int i = 0; i < prereqList.size(); i++) {
    			T prereq = prereqList.get(i);

    			if(!array.contains(prereq)) {
        			array.add(prereq);
    			}
    		}	
    	} // while
		
    	HashSet<T> returnSet = new HashSet<T>();
    	for(int i = 0; i < array.size(); i++) {
    		T value = array.get(i);
    		returnSet.add(value);	
    	}
    	
        return returnSet;
    } // getAllCourses()
    
    
    /**
     * To check whether all given courses can be completed or not
     * @return boolean true if all given courses can be completed, otherwise false
     * @throws Exception
     */
    public boolean canCoursesBeCompleted() throws Exception {
        //TODO: implement this method
        return false;

    }
    
    
    /**
     * The order of courses in which the courses has to be taken
     * @return the list of courses in the order it has to be taken
     * @throws Exception when courses can't be completed in any order
     */
    public List<T> getSubjectOrder() throws Exception {
//    	Let N be the number of vertices.  Mark all vertices as unvisited. 
//    	Make a stack.   Make an array of length N.  
//
//    	for each vertex V with no predecessors // alphanumeric order on quizzes
//    		mark V as visited
//    	push V onto the stack
//
//    	decrement N so that N is now the index of the last element of the array.
//    	while Stack is not empty
//    		current = stack.peek();
//    		if all successors of current are visited
//    			pop current off the stack…..store it in the array at index N
//    			decrement N
//    		else
//    			select an unvisited successor U of current  // alphanumeric order on quizzes
//    			mark U as visited
//    			push U onto the stack
    	
    	// TODO - implement one of the graphing methods - topological ordering
    	// TODO - mark all nodes as unvisited
    	
    	int n = getAllCourses().size(); // get all courses
    	Set<T> courses = getAllCourses();
    	CourseStack stack = new CourseStack(n); // create a stack    
    	ArrayList<T> array = new ArrayList<T>(); // arraylist to store order
    	
    	Iterator courseListItr = courses.iterator();
    	graphImpl.getAdjacentVerticesOf(vertex)
    	
    	
    	
    	
    	
        //TODO: implement this method
        return null;

    } // getSubjectOrder()

        
    /**
     * The minimum course required to be taken for a given course
     * @param courseName 
     * @return the number of minimum courses needed for a given course
     */
    public int getMinimalCourseCompletion(T courseName) throws Exception {    	
    	int count = 0;
        count = helpGetMinimum(count, courseName); // call helper method
        return count;
    } // getMinimalCourseCompletion()
    
    
    private int helpGetMinimum(int count, T courseName) {
    	// for each vertex with no predecessors
    	ArrayList<T> prereqs = (ArrayList<T>) graphImpl.getAdjacentVerticesOf(courseName);
    	if(prereqs == null) {
    		return 0;
    	}
    	Iterator itr = prereqs.iterator();
    	
    	for(int i = 0; i < prereqs.size(); i++) {
    		try {
    			T next = (T) prereqs.get(i);
				count = count + helpGetMinimum(count, next); // recursive call
			} catch (Exception e) {
				e.printStackTrace();
			}
    		
    	}
    	count = count + prereqs.size();
    	return count;
    } // helpGetMinimum()
    
    
    public static void main(String[] args) throws FileNotFoundException {
    	CourseSchedulerUtil<String> util = new CourseSchedulerUtil<String>();


    	try {
			Entity [] entities = util.createEntity("Valid.json");
			util.constructGraph(entities);
	    	Set<String> courseList = util.getAllCourses();
			System.out.println(courseList);
			
			System.out.println("Testing minimum number of courses to complete CS300.");
			System.out.println(util.getMinimalCourseCompletion("CS300"));
			System.out.println("Testing minimum number of courses to complete CS400.");
			System.out.println(util.getMinimalCourseCompletion("CS400"));
			System.out.println("Testing minimum number of courses to complete CS540.");
			System.out.println(util.getMinimalCourseCompletion("CS540"));
			System.out.println("Testing minimum number of courses to complete CS760.");
			System.out.println(util.getMinimalCourseCompletion("CS760"));
			System.out.println("Testing minimum number of courses to complete CS790.");
			System.out.println(util.getMinimalCourseCompletion("CS790"));
	    	
	    	System.out.println();
	    	System.out.println("Getting subject order ");
			util.getSubjectOrder();
			
			
			
			
			
	    	System.out.println();
	    	System.out.println();
	    	System.out.println("Graph directly called.");
			// calling code directly so I can print it
	    	GraphImpl graph = new GraphImpl();
	    	for(int i = 0; i < entities.length; i++) {
	        	graph.addVertex(entities[i].getName());
	        	Object[] array = entities[i].getPrerequisites();
	        	for(int j = 0; j < array.length; j++) {
	        		graph.addEdge(entities[i].getName(), array[j]);
	        	}
	        	
	    	}
	   	    	
	    	
	    	graph.printGraph();
			

			
			
		} 
    	catch (Exception e) {
			e.printStackTrace();
		}
    	
    	
	} // Main()
    
    
} // Class CourseSchedulerUtil

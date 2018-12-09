
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
 * Creates a utility that can be used to test an implementation of a graph
 * 
 * 
 * Credits:
 *      https://www.geeksforgeeks.org/java-program-for-topological-sorting/
 *      https://www.geeksforgeeks.org/detect-cycle-in-a-graph/
 */
public class CourseSchedulerUtil<T> {
    
    // can add private but not public members
    private CourseStack stack; // stack to keep track of traversals

	/*
	 * inner stack class
	 * a basic array stack used to track traversals of vertices
	 */
	private class CourseStack<T> {
		// fields
		private int size; // current size of the stack
		private Entity[] position; // storage of the stack
		
		/*
		 * constructor
		 * Sets size of the array to the user input
		 */
		private CourseStack(int n) {
			position = new Entity[n];
		} // constructor
		
		/*
		 * @return true if stack is empty
		 */
		private boolean isEmpty() {
			if(size == 0) {
				return true;
			}
			else {
				return false;
			}
		} // isEmpty()
		
		/*
		 * @param newNode - the node to add to the stack
		 * Adds a node to the top of the stack
		 */
		private void push(Entity newNode) {
			if(isEmpty()) {
				position[0] = newNode;
				size++;
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
		
		/*
		 * @return node - the node that was on the top of the stack
		 * Removes the top node of the stack and returns it
		 */
		private Entity pop() {
			Entity node = position[0];
			// shift rest of stack into array index at 0
			// through whatever index is necessary
			for(int i = 0; i <= size; i++) {
				// set position at index to position at index + 1
				position[i] = position[i + 1];
			} // for
			// done shifting
			// clear the former lowest position on the stack
			position[size] = null;	
			// decrement size
			size--;
			return node;
		} // pop()
		
		/*
		 * @return topEntity - the top node of the stack
		 * Returns the top node without removing it
		 */
		private Entity peek() {
			Entity topEntity = position[0];
			return topEntity;
		} // peek()
		
	}// inner CourseStack class
	
    
    /**
     * Graph variables
     */
    private GraphImpl<T> graphImpl; // the graph itself
    private ArrayList<T> visited; // arrayList to track what's been visited while traversing
    
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
    	} // outer for	
    	
    } // constructGraph()
    
    
    /**
     * Returns all the unique available courses, including non-JSON object ones
     * @return the set of all available courses
     */
    public Set<T> getAllCourses() {
    	Set<T> courseSet = graphImpl.getAllVertices();
    	Iterator itr = courseSet.iterator();
    	ArrayList<T> array = new ArrayList<T>();
    	// iterate through the set of courses and check their prerequisites
    	while(itr.hasNext()) {
    		T course = (T) itr.next();
    		array.add(course);
    		List<T> prereqList = graphImpl.getAdjacentVerticesOf(course);
    		for(int i = 0; i < prereqList.size(); i++) {
    			T prereq = prereqList.get(i);
    			 // add this prereq to the array if it isn't already in it
    			if(!array.contains(prereq)) {
        			array.add(prereq);
    			}
    		}	
    	} // while
		
    	// populate the HashSet to be returned
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
     */
    public boolean canCoursesBeCompleted() {
       	int n = graphImpl.getAllVertices().size(); // get all courses
    	System.out.println(n);
    	visited = new ArrayList<T>();
    	ArrayList<T> track = new ArrayList<T>();
    	
    	Set<T> courses = graphImpl.getAllVertices();
    	Iterator<T> courseListItr = courses.iterator();
    	// go through vertices
    	for(int i = 0; i < n; i++) {
    		T current = courseListItr.next();
    		// call recursive helper method
    		if(helperBeCompleted(current, visited, track) == true) {
    			track.remove(current);
    			continue; // keep going if helper keeps returning true
    		}
    		else {
    			return false;
    		}
    	}
    	return true;
    } // canCoursesBeCompleted()
    
    /*
     * @param current - current course being evaluated
     * @param visited - the arraylist containing courses that have been "visited"
     * @param track - arraylist containing the actual courses we need to track
     */
    private boolean helperBeCompleted(T current, ArrayList<T> visited, ArrayList<T> track) {
    	if(track.contains(current)) {
    		return false; // this means that we automatically fail because this course has been tracked
    	}
    	
    	if(visited.contains(current)) {
    		return true;
    	}
    	
    	// mark current as visited
    	visited.add(current);
    	// add current to the track record
    	track.add(current);
    	
    	// work on prereqs
    	List<T> adjacentList = graphImpl.getAdjacentVerticesOf(current);
    	if(adjacentList == null) {
    		return true;
    	}
		if(adjacentList != null) { // check so we don't get an exception
			Iterator prereqs = adjacentList.iterator();
			while(prereqs.hasNext()) {
				current = (T) prereqs.next();
				// make the recursive call again
				if(helperBeCompleted(current, visited, track)) {
					track.remove(current);
					continue;
				}

				else {
					return false;
				}
			} // while
		} // if check
		
		track.remove(current);
		return true;
		//return false;
    } // helperBeCompleted()
    
    /**
     * The order of courses in which the courses has to be taken
     * @return the list of courses in the order it has to be taken
     */
    public List<T> getSubjectOrder() throws Exception {  
       	int n = getAllCourses().size(); // get all courses
    	stack = new CourseStack(n); // create a stack
    	ArrayList<T> orderedArray = new ArrayList<T>();
    	ArrayList<T> sorted = new ArrayList<T>(7);
    	Set<T> courses = getAllCourses();
    	Iterator courseListItr = courses.iterator();
    	visited = new ArrayList<T>();
    	// move through the courses
    	for(int j = 0; j < courses.size(); j++) {
        	T current = (T) courseListItr.next();
        	Entity cur = new Entity();
        	cur.setName(current);
        	if(!visited.contains(current)) {
        		// make a recursive call
        		helperSubjectOrder(sorted, stack, current);
        	}
    	} // for
    	return sorted;
    } // testSubjectOrder()
    
    /*
     * recursive method to help move through courses while getting their proper order
     * @param sorted - the sorted arraylist
     * @param stack - the stack to keep track of where the method is at
     * @param current - the current node being evaluated
     */
    private void helperSubjectOrder(ArrayList<T> sorted, CourseStack stack, T current) {
    	Entity newcur = new Entity();
		newcur.setName(current);
    	stack.push(newcur);
		visited.add(current);
		// keep progressing while the stack has entries on it
    	while(!stack.isEmpty()) {
    		List<T> adjacentList = graphImpl.getAdjacentVerticesOf(current);
    		if(adjacentList != null) {
    			Iterator prereqs = adjacentList.iterator();
    			while(prereqs.hasNext()) {
    				Entity cur = new Entity();
    				T check = (T) prereqs.next();
    				cur.setName(check);
    				if(!visited.contains(check)) {
    					// call recursive method again
        				helperSubjectOrder(sorted, stack, check);
    				}
    			}
    		} // if check
    		if(stack.isEmpty()) {
    			break;
    		}
    		// add top of the stack to the sorted order
    		sorted.add((T) stack.pop().getName()); 
    	} // while stack isn't empty
 	
    } // helperSubjectOrder()
        
    /**
     * The minimum course required to be taken for a given course
     * @param courseName 
     * @return the number of minimum courses needed for a given course
     * @throws exception when input is invalid
     */
    public int getMinimalCourseCompletion(T courseName) throws IllegalArgumentException {
    	try {
    		Set<T> checkList = getAllCourses();
    		if(!checkList.contains(courseName)) {
    			throw new IllegalArgumentException();
    		}
        	int count = 0;
            count = helpGetMinimum(count, courseName); // call helper method
            return count;
    	} // try
        
        catch (IllegalArgumentException e) {
        	System.out.println("Course name is not one of the available courses.");
			e.printStackTrace();
			return -1;
		}
        catch (Exception e) {
        	e.printStackTrace();
        	return -1;
        }
    } // getMinimalCourseCompletion()
    
    /*
     * @param count - the current count so far
     * @param courseName - the course being evaluated now
     */
    private int helpGetMinimum(int count, T courseName) {
    	// for each vertex with no predecessors
    	ArrayList<T> prereqs = (ArrayList<T>) graphImpl.getAdjacentVerticesOf(courseName);
    	if(prereqs == null) {
    		return 0;
    	}
    	Iterator itr = prereqs.iterator();
    	
    	for(int i = 0; i < prereqs.size(); i++) {
    		T next = (T) prereqs.get(i);
    		// recursive call
			count = count + helpGetMinimum(count, next);
    	} // for
    	count = count + prereqs.size();
    	return count;
    } // helpGetMinimum()
    
    /*
     * Main method for testing
     */
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
			System.out.println(util.getSubjectOrder());			
			
	    	System.out.println();
	    	System.out.println("Checking whether or not you can complete courses ");
			System.out.println(util.canCoursesBeCompleted());	
			
			// ------------- invalid JSON
			
			System.out.println();
			Entity [] entitiestwo = util.createEntity("invalid.json");
			util.constructGraph(entitiestwo);
			System.out.println("Can invalid be completed?");
			System.out.println(util.canCoursesBeCompleted());
			
			
			
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

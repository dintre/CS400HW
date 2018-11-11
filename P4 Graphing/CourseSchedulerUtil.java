
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
					// Entity newCourse = new Entity(); // create the entity object
					// newCourse.setName(prereqValue); // TODO - do I need this object?
					prerequisites[prereqIndex] = prereqValue;
					prereqIndex++; // increment index
				} // inner while

				String nameValue = (String) courseObj.get("name");
				Entity newerCourse = new Entity();
				newerCourse.setName(nameValue);
				newerCourse.setPrerequisites(prerequisites);
		        entities[courseIndex] = newerCourse;
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
    	GraphImpl graph = new GraphImpl();
    	for(int i = 0; i < entities.length; i++) {
        	graph.addVertex(entities[i]);
    	}    	
    	
    } // constructGraph()
    
    
    /**
     * Returns all the unique available courses
     * @return the set of all available courses
     */
    public Set<T> getAllCourses() {
    	Set<T> courseSet = graphImpl.getAllVertices();
    	Spliterator splt = courseSet.spliterator();
    	splt.forEachRemaining((n) -> System.out.println(n));
    	
        //TODO: implement this method
        return null;
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
        //TODO: implement this method
        return null;

    } // getSubjectOrder()

        
    /**
     * The minimum course required to be taken for a given course
     * @param courseName 
     * @return the number of minimum courses needed for a given course
     */
    public int getMinimalCourseCompletion(T courseName) throws Exception {
        //TODO: implement this method
        return -1;
        
    } // getMinimalCourseCompletion()
    
    
    public static void main(String[] args) throws FileNotFoundException {
    	CourseSchedulerUtil util = new CourseSchedulerUtil();


    	try {
			Entity [] entities = util.createEntity("valid.JSON");
			util.constructGraph(entities);
			
			
			// calling code directly so I can print it
	    	GraphImpl graph = new GraphImpl();
	    	for(int i = 0; i < entities.length; i++) {
	        	graph.addVertex(entities[i]);
	    	}
	    	graph.printGraph();
			
			
			
		} 
    	catch (Exception e) {
			e.printStackTrace();
		}
    	
    	
	} // Main()
    
    
} // Class CourseSchedulerUtil

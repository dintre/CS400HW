
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
import java.util.Stack;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


/**
 * Filename:   CourseSchedulerUtil.java
 * Project:    p4
 * Authors:    Debra Deppeler
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
    		// typecasting obj to JSONObject 
    		JSONObject jo = (JSONObject) obj; 
            // creating array 
            JSONArray courses = (JSONArray) jo.get("courses");
            
            // for prereqs

            
            // iterates through array within a course
            Iterator itr = courses.iterator();
            // iterates through each field within a course (i.e. name, prereqs)
            Iterator<Map.Entry> innerItr;

            // iterates through courses array
            // each time it's a different course
    		while(itr.hasNext()) {
    			innerItr = ((Map) itr.next()).entrySet().iterator();
    			System.out.println();
    			
    			// iterates through map pairs in a course
    			// first it's the prereqs
    			// second it's the course name
    			while(innerItr.hasNext()) {
    				// pairs are: name + <courseName>
    				//            prerequisites + <array of prereqs>
    				Map.Entry pair = innerItr.next();
    				
    				// handle prereqs array
    	            JSONObject pjo = (JSONObject) obj;
    	            JSONArray prereqs = (JSONArray) pjo.get("prerequisites");
    	            Iterator preItr = prereqs.iterator();
        			preItr = ((Map) innerItr.next()).entrySet().iterator();
    	            while(preItr.hasNext()) {
        				System.out.println(pair.getKey() + " : " + pair.getValue());

    	            }
    				
    				System.out.println(pair.getKey() + " : " + pair.getValue());
    				
    			} // while

    		} // while
    		
    	} // try
    	
    	catch(Exception e) {
    		System.out.println("Caught exception while parsing JSON file. ");
    		System.out.println(e);
    		e.printStackTrace();
    	} // catch
        return null;
        
    } // createEntity()
    
    
    /**
     * Construct a directed graph from the created entity object 
     * @param entities which has information about a single course 
     * including its name and its prerequisites
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void constructGraph(Entity[] entities) {
        //TODO: implement this method

    } // constructGraph()
    
    
    /**
     * Returns all the unique available courses
     * @return the sorted list of all available courses
     */
    public Set<T> getAllCourses() {
        //TODO: implement this method
        return null;
    } // getAllCourses()
    
    
    /**
     * To check whether all given courses can be completed or not
     * @return boolean true if all given courses can be completed,
     * otherwise false
     * @throws Exception
     */
    public boolean canCoursesBeCompleted() throws Exception {
        //TODO: implement this method
        return false;

    } // canCoursesBeCompleted()
    
    
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
			util.createEntity("valid.JSON");
			
			
		} 
    	catch (Exception e) {
			e.printStackTrace();
		}
    	
    	
	} // Main()
    
    
} // Class CourseSchedulerUtil

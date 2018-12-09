package application;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class GraphUtility<T> {
	// variables for the utility
	private Graph<T> graph;
	
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
		        //Entity [] entities = new Entity[8]; // array to be returned
				int courseIndex = 0;
		        // iterates through courses array
		        // each time it's a different course
				ArrayList<Entity> actualArray = new ArrayList<Entity>();

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
						// trying to create prereqs as entities too ----------------------------------------------------------------------------
						Entity preEntity = new Entity();
						preEntity.setName(prereqValue);
					//	entities[courseIndex] = preEntity;
						actualArray.add(preEntity);
						courseIndex++; 
						// trying to create prereqs as entities too ----------------------------------------------------------------------------
					} // inner while
					String nameValue = (String) courseObj.get("name");
					Entity newCourse = new Entity();
					newCourse.setName(nameValue);
					newCourse.setPrerequisites(prerequisites);
			        //entities[courseIndex] = newCourse;
			        actualArray.add(newCourse);
			        courseIndex++; // increment index since done parsing this course
				} // while
				Entity [] entities = new Entity[actualArray.size()];
				for(int h = 0; h < actualArray.size(); h++) {
					entities[h] = actualArray.get(h);	
				}
				
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
	    	graph = new Graph(); // instantiate the graph 	
	    	for(int i = 0; i < entities.length; i++) {
	    		// add the course
	    		Entity currentEntity = entities[i];
	    		graph.addVertex((T) currentEntity.getName());
	    		// add its prerequisites
	    		T[] prerequisites = (T[]) currentEntity.getPrerequisites();
	    		if(prerequisites != null) {
	    			for(int j = 0; j < prerequisites.length; j++) {
	    				graph.addPrereq((T) currentEntity.getName(), prerequisites[j]);
	    			}
	    		}
	    	} // for
	    	System.out.println("Built a graph. ");
	    } // constructGraph()
	       
	    /**
	     * Returns all the unique available courses
	     * @return the sorted list of all available courses
	     */
	    public Set<T> getAllCourses() {
	    	Set<T>unsortedSet = graph.getAllVertices();	    		
	    	return unsortedSet;
	    } // getAllCourses()
	    
	    
	    /**
	     * To check whether all given courses can be completed or not
	     * @return boolean true if all given courses can be completed,
	     * otherwise false
	     * @throws Exception
	     */
	    public boolean canCoursesBeCompleted() throws Exception {
	    try {
	    	ArrayList<T> visited = new ArrayList<T>();
	    	ArrayList<T> tracking = new ArrayList<T>();
	    	Set<T> vertices = graph.getAllVertices();
	    	Iterator<T> itr = vertices.iterator();
	    	while(itr.hasNext()) {
	    		T current = itr.next();
	    		if(helperCanComplete(visited, tracking, current)) {
	    			tracking.remove(current);
	    			continue;
	    		}
	    		else {
	    			throw new Exception();
	    			
	    		}
	    	} // while
	    	return true;
	    }
	    	catch(Exception e)  {
	    		return false;
	    	}
	    } // canCoursesBeCompleted()
	    
	    //helper check for cycles
	    private boolean helperCanComplete(ArrayList<T> visited, ArrayList<T> tracking, T current) {
	    	if(tracking.contains(current)) {
	    		return false;
	    	}
	    	if(visited.contains(current)) {
	    		return true;
	    	}
	    	
	    	tracking.add(current);
	    	visited.add(current);

	    	List<T> prereqs = graph.getPrereqsOf(current);
	    	if(prereqs == null) {
	    		return true; // if no prereqs
	    	}
	    	
	    	// look through prereqs
	    	Iterator itr = prereqs.iterator();
	    	while(itr.hasNext()) {
	    		T cur = (T) itr.next();
	    		if(helperCanComplete(visited, tracking, cur)) {
	    			tracking.remove(cur);
	    			continue;
	    		}
	    		else {
	    			return false;
	    		}
	    	} // while
	    	return true;
	    } // helperCanComplete()
	    
	    /**
	     * The order of courses in which the courses has to be taken
	     * @return the list of courses in the order it has to be taken
	     * @throws Exception when courses can't be completed in any order
	     */  
	    private ArrayList<T> visited;
	    
	    public List<T> getSubjectOrder() throws Exception {
	    	// "mark" all vertices as unvisited
	    	Set<T> vertices = graph.getAllVertices();	    	
	    	visited = new ArrayList<T>();
	    	ArrayList<T> returnList = new ArrayList<T>();
	    	Stack<T> stack = new Stack<T>();
	    	Iterator itr = vertices.iterator();
	    	while(itr.hasNext()) {
	    		T current = (T) itr.next();
	    		if(!visited.contains(current)) {
		    		helpGetSubjectOrder( returnList, current);
	    		} // if not visited yet
	    	} // while
	    	return returnList;
	    } // getSubjectOrder()

	    // helps with getting the order of courses
	    private void helpGetSubjectOrder(ArrayList<T> returnList, T current){
	    	Stack<T> stack = new Stack<T>();
	    	visited.add(current); // add current to visited
	    	stack.push(current); // add current to the stack
    		ArrayList<T> prereqs = (ArrayList<T>) graph.getPrereqsOf(current);
	    	while(!stack.isEmpty()) {
	    		if(prereqs.size() > 0) {
	    			Iterator<T> itr = prereqs.iterator();
	    			while(itr.hasNext()) {
	    				T cur = itr.next();
	    				if(!visited.contains(cur)) {
	    					helpGetSubjectOrder(returnList, cur);
	    				}
	    				continue;
	    			} // check all prereqs of current
	    		} // if prereqs are not null
	    		if(stack.isEmpty()) {
	    			System.out.println(returnList);
	    			break;
	    		}
	    		returnList.add(stack.pop());
	    	} // while the stack is not empty
	    } // helpGetSubjectOrder()
	        
	    /**
	     * The minimum course required to be taken for a given course
	     * @param courseName 
	     * @return the number of minimum courses needed for a given course
	     */
	    public int getMinimalCourseCompletion(T courseName) throws Exception {
	    	try {
	    		if(!graph.graphContains(courseName)) {
	    			throw new NoSuchElementException();
	    		}
	    		ArrayList<T> prereqs = (ArrayList<T>) graph.getPrereqsOf(courseName);
	    		if(prereqs.size() == 0) {
	    			return 0;
	    		}
	    		else {
	    			return helperCourseCount(courseName, prereqs);

	    		} // else
	    	} // try
	    	catch(Exception NoSuchElementException) {
	    		System.out.println("That course does not exist. ");
	    		return -1;
	    	}
	    	
	    } // getMinimalCourseCompletion()
	    
	    private int helperCourseCount(T course, ArrayList<T> prereqs) throws Exception {
	    	try {
	    		int count = 0;
				for(int i = 0; i < prereqs.size(); i++) {
					count++;
					ArrayList<T> morePreReqs = (ArrayList<T>) graph.getPrereqsOf(prereqs.get(i));
					if(morePreReqs.contains(course)) {
						throw new Exception();
					}
					count = count + helperCourseCount(prereqs.get(i), morePreReqs);
				} // looping through prereq array
		    	return count;
	    	}
	    	
	    	catch(Exception e) {
	    		System.out.println("Can't be completed. No minimal value.");
	    		return -1;
	    	}
	    } // helperCourseCount()
	    
	    // prints the graph
	    public void printGraph() {
	    	graph.printGraph();
	    } // printGraph
	
	// main method for testing ----------------------------------------------------------------------------------------
	public static void main(String[] args) {
		GraphUtility<String> courseUtility = new GraphUtility<String>();
		try {
			/*
			 // on valid.JSON
			@SuppressWarnings("unchecked")
			Entity<String> [] courses = courseUtility.createEntity("valid.JSON");
			courseUtility.constructGraph(courses);
			
			courseUtility.printGraph();
			
			System.out.println("List of all courses: ");
			System.out.println(courseUtility.getAllCourses());
			
			System.out.println("Testing minimal course completion number: ");
			System.out.println("CS790: " + courseUtility.getMinimalCourseCompletion("CS790"));
			System.out.println("CS9000: " + courseUtility.getMinimalCourseCompletion("CS9000"));

			System.out.println("Testing get minimal course completion... ");
			System.out.println("CS300: " + courseUtility.getMinimalCourseCompletion("CS300"));
			System.out.println("CS200: " + courseUtility.getMinimalCourseCompletion("CS200"));
			System.out.println("CS400: " + courseUtility.getMinimalCourseCompletion("CS400"));
			System.out.println("CS540: " + courseUtility.getMinimalCourseCompletion("CS540"));
			System.out.println("CS760: " + courseUtility.getMinimalCourseCompletion("CS760"));
			System.out.println("CS790: " + courseUtility.getMinimalCourseCompletion("CS790"));

			System.out.println("Testing get subject order... ");
			System.out.println(courseUtility.getSubjectOrder());
			
			System.out.println("Testing can courses be completed... ");
			System.out.println("Valid file should be possible: " + courseUtility.canCoursesBeCompleted());
			*/
			
			// Test1Valid.JSON
			Entity<String> [] courses = courseUtility.createEntity("Test1Valid.JSON");
			courseUtility.constructGraph(courses);
			System.out.println();
			System.out.println();

			courseUtility.printGraph();
			
			System.out.println("List of all courses: ");
			System.out.println(courseUtility.getAllCourses());
			
			System.out.println("Testing minimal course completion number: ");
			System.out.println("CS790: " + courseUtility.getMinimalCourseCompletion("CS790"));
			System.out.println("CS9000: " + courseUtility.getMinimalCourseCompletion("CS9000"));

			System.out.println("Testing get minimal course completion... ");
			System.out.println("CS300: " + courseUtility.getMinimalCourseCompletion("CS300"));
			System.out.println("CS200: " + courseUtility.getMinimalCourseCompletion("CS200"));
			System.out.println("CS400: " + courseUtility.getMinimalCourseCompletion("CS400"));
			System.out.println("CS540: " + courseUtility.getMinimalCourseCompletion("CS540"));
			System.out.println("CS766: " + courseUtility.getMinimalCourseCompletion("CS766"));
			System.out.println("CS757: " + courseUtility.getMinimalCourseCompletion("CS757"));
			
			System.out.println("Testing get subject order... ");
			System.out.println(courseUtility.getSubjectOrder());
			/*
			System.out.println("Testing can courses be completed... ");
			System.out.println("Valid file should be possible: " + courseUtility.canCoursesBeCompleted());
			*/
			
			// Test2Cyclic.JSON
			GraphUtility<String> course2Utility = new GraphUtility<String>();

			Entity<String> [] courses2 = course2Utility.createEntity("Test2Cyclic.JSON");
			course2Utility.constructGraph(courses2);
			System.out.println();
			System.out.println();

			course2Utility.printGraph();
			
			System.out.println("List of all courses: ");
			System.out.println(course2Utility.getAllCourses());
			
			System.out.println("Testing minimal course completion number: ");
			System.out.println("CS760: " + course2Utility.getMinimalCourseCompletion("CS760"));

			System.out.println("Testing get minimal course completion... ");
			System.out.println("CS300: " + course2Utility.getMinimalCourseCompletion("CS300"));
			
			System.out.println("Testing get subject order... ");
			System.out.println(course2Utility.getSubjectOrder());
			
			System.out.println("Testing can courses be completed... ");
			System.out.println("Valid file should be possible: " + course2Utility.canCoursesBeCompleted());
			
			
			
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	} // main()

} // class GraphUtility

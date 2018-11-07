import java.io.FileReader;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ParsingTest {
	
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
	
	        // iterates through courses array
	        // each time it's a different course
			while(itr.hasNext()) {
				//System.out.println("outer while loop ------------ " + itr.next());
				
				//Object innerObj = new Object();
				JSONObject courseObj = (JSONObject) itr.next();
				//System.out.println(courseObj);
				JSONArray set = (JSONArray) courseObj.get("prerequisites");
				Iterator prereqIterator = set.iterator();
				while(prereqIterator.hasNext()) {
					String prereqValue = (String) prereqIterator.next();
					System.out.println("prereq array --------------- " + prereqValue);
				}

				
				String values = (String) courseObj.get("name");
				System.out.println("name values  --------------- " + values);
				
				//courseObj.get("prerequisites");
				//System.out.println(courseObj);

				
				
		        System.out.println();
			} // while
			
		} // try
		
		catch(Exception e) {
			System.out.println("Caught exception while parsing JSON file. ");
			System.out.println(e);
			e.printStackTrace();
		} // catch
		return null;
	
	} // entity create method()
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
		ParsingTest util = new ParsingTest();
    	try {
			util.createEntity("valid.JSON");
			
			
		} 
    	catch (Exception e) {
			e.printStackTrace();
		}
	}

} // class parsing test

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
	        
	        //
	        
	        JSONObject innerJo = new JSONObject();
	
	        // iterates through courses array
	        // each time it's a different course
			while(itr.hasNext()) {
				System.out.println(itr.next());
				
				//Object innerObj = new Object();
				JSONObject courseObj = new JSONObject();
				courseObj = (JSONObject) itr.next();
				
				courseObj.get("prerequisites");
				System.out.println(courseObj);
				
				courseObj.get("name");
		        System.out.println(courseObj);
				
				
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

import java.io.FileReader;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ParseJSON {

	// parses a JSON file
	public static void parseFile(String fileName) {
		try {
			// 1. overall object
			Object overallObject = new JSONParser().parse(new FileReader(fileName));
			
			// 2. Cast to JSON Object
			JSONObject JObject = (JSONObject) overallObject;
			
			// 3. Array of courses
			JSONArray courseArray = (JSONArray) JObject.get("courses");
			
			// 4. Iterate through courses
			Iterator courseIterator = courseArray.iterator();
			while(courseIterator.hasNext()) {
				
				// course object
				JSONObject courseObject = (JSONObject) courseIterator.next();
				//System.out.println(courseObject);
				
				// prereq array
				JSONArray prereqsArray = (JSONArray) courseObject.get("prerequisites");
				
				// course name
				String courseName = (String) courseObject.get("name");
				System.out.println("Course Name: " + courseName);
				
				// iterate through pre requisisites
				Iterator preIterator = prereqsArray.iterator();
				System.out.print("Prerequisites: ");
				while(preIterator.hasNext()) {
					String prereqName = (String) preIterator.next();
					System.out.print(prereqName + "  ");
				}
				
				System.out.println();
				
				
			} // while iterating through courses
			
			
			
			
		} // try
		
		
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
	} // parseFile()
	
	
	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
		parseFile("valid.JSON");
		
	} // Main()

	
} // class ParseJSON

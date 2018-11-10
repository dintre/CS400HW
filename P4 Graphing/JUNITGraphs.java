import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class JUNITGraphs<T> {

	@Test
	public void test_addOneEntity() {
		String testName = "addOneEntity";
		try {
			GraphImpl<Entity> testGraph = new GraphImpl<Entity>();
	    	Entity testCourse = new Entity();
	    	testCourse.setName("TJD 300");
	    	String [] prereqs = new String [] {"TJD 100", "TJD 202"};
	    	testCourse.setPrerequisites(prereqs);
	    	testGraph.addVertex(testCourse);
			assertEquals(testCourse.getName(), "TJD 300");
			String [] testprereqs = (String[]) testCourse.getPrerequisites();
			assertEquals("TJD 202",testprereqs[1]);
			assertTrue(testGraph.hasVertex(testCourse));
		}
		catch (Exception e){
			System.out.println("Caught Exception in " + testName);
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void test_addMultipleEntities() {
		String testName = "addMultipleEntities";
		try {
			GraphImpl<Entity> testGraph = new GraphImpl<Entity>();
	    	Entity testCourse = new Entity();
	    	testCourse.setName("TJD 300");
	    	String [] prereqs = new String [] {"TJD 100", "TJD 202"};
	    	testCourse.setPrerequisites(prereqs);
	    	testGraph.addVertex(testCourse);
	    	
	    	Entity testCourse2 = new Entity();
	    	testCourse2.setName("TJD 450");
	    	String [] prereqs2 = new String [] {"TJD 233"};
	    	testCourse2.setPrerequisites(prereqs2);
	    	testGraph.addVertex(testCourse2);
	    	
	    	Entity testCourse3 = new Entity();
	    	testCourse3.setName("TJD 107");
	    	String [] prereqs3 = new String [] {};
	    	testCourse3.setPrerequisites(prereqs3);
	    	testGraph.addVertex(testCourse3);
	    	
			assertEquals("TJD 450", testCourse2.getName());
			String [] testprereqs = (String[]) testCourse2.getPrerequisites();
			assertEquals(testprereqs[0], "TJD 233");
			assertTrue(testGraph.hasVertex(testCourse2));
		}
		catch (Exception e){
			System.out.println("Caught Exception in " + testName);
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void test_addMultipleRemoveOneEntity() {
		String testName = "addMultipleRemoveOneEntity";
		try {
			GraphImpl<Entity> testGraph = new GraphImpl<Entity>();
	    	Entity testCourse = new Entity();
	    	testCourse.setName("TJD 300");
	    	String [] prereqs = new String [] {"TJD 100", "TJD 202"};
	    	testCourse.setPrerequisites(prereqs);
	    	testGraph.addVertex(testCourse);
	    	
	    	Entity testCourse2 = new Entity();
	    	testCourse2.setName("TJD 450");
	    	String [] prereqs2 = new String [] {"TJD 233"};
	    	testCourse2.setPrerequisites(prereqs2);
	    	testGraph.addVertex(testCourse2);
	    	
	    	Entity testCourse3 = new Entity();
	    	testCourse3.setName("TJD 107");
	    	String [] prereqs3 = new String [] {};
	    	testCourse3.setPrerequisites(prereqs3);
	    	testGraph.addVertex(testCourse3);
	    	
	    	assertEquals(3, testGraph.order());
	    	testGraph.removeVertex(testCourse2);
	    	assertFalse(testGraph.hasVertex(testCourse2));
	    	assertEquals(2, testGraph.order());

		}
		catch (Exception e){
			System.out.println("Caught Exception in " + testName);
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void test_addAndGetVertices() {
		String testName = "addAndGetVertices";
		try {
			GraphImpl<Entity> testGraph = new GraphImpl<Entity>();
	    	Entity testCourse = new Entity();
	    	testCourse.setName("TJD 300");
	    	String [] prereqs = new String [] {"TJD 100", "TJD 202"};
	    	testCourse.setPrerequisites(prereqs);
	    	testGraph.addVertex(testCourse);
	    	
	    	Entity testCourse2 = new Entity();
	    	testCourse2.setName("TJD 450");
	    	String [] prereqs2 = new String [] {"TJD 233"};
	    	testCourse2.setPrerequisites(prereqs2);
	    	testGraph.addVertex(testCourse2);
	    	
	    	Entity testCourse3 = new Entity();
	    	testCourse3.setName("TJD 107");
	    	String [] prereqs3 = new String [] {};
	    	testCourse3.setPrerequisites(prereqs3);
	    	testGraph.addVertex(testCourse3);
	    	
			assertNotSame(null, testGraph.getAllVertices());
		}
		catch (Exception e){
			System.out.println("Caught Exception in " + testName);
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void test_addAndCheckNumberOfEdges() {
		String testName = "addAndCheckNumberOfEdges";
		try {
			GraphImpl<Entity> testGraph = new GraphImpl<Entity>();
	    	Entity testCourse = new Entity();
	    	testCourse.setName("TJD 300");
	    	String [] prereqs = new String [] {"TJD 100", "TJD 202"};
	    	testCourse.setPrerequisites(prereqs);
	    	testGraph.addVertex(testCourse);
	    	
	    	Entity testCourse2 = new Entity();
	    	testCourse2.setName("TJD 450");
	    	String [] prereqs2 = new String [] {"TJD 233"};
	    	testCourse2.setPrerequisites(prereqs2);
	    	testGraph.addVertex(testCourse2);
	    	
	    	Entity testCourse3 = new Entity();
	    	testCourse3.setName("TJD 107");
	    	String [] prereqs3 = new String [] {};
	    	testCourse3.setPrerequisites(prereqs3);
	    	testGraph.addVertex(testCourse3);
	    	
			assertEquals(3, testGraph.size());
		}
		catch (Exception e){
			System.out.println("Caught Exception in " + testName);
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void test_addNewEdge() {
		String testName = "addNewEdge";
		try {
			GraphImpl<Entity> testGraph = new GraphImpl<Entity>();
	    	Entity testCourse = new Entity();
	    	testCourse.setName("TJD 300");
	    	String [] prereqs = new String [] {"TJD 100", "TJD 202"};
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
	    	
			assertEquals(3, testGraph.size());
			testGraph.addEdge(testCourse,testCourse3);
			assertEquals(4, testGraph.size());
			
		}
		catch (Exception e){
			System.out.println("Caught Exception in " + testName);
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void test_removeEdge() {
		String testName = "removeEdge";
		try {
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
	    	
			testGraph.removeEdge(testCourse, testCourse3);
			
			
			//System.out.println("Adjacent courses " + testGraph.getAdjacentVerticesOf(testCourse));
			List<Entity> list = testGraph.getAdjacentVerticesOf(testCourse);
			//System.out.println("Size is " + testGraph.size());
			
			
			assertFalse(list.contains("TJD 107"));
			assertEquals(3, testGraph.size());
			
		}
		catch (Exception e){
			System.out.println("Caught Exception in " + testName);
			e.printStackTrace();
			fail();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
} // class

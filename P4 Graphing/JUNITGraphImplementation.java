import static org.junit.Assert.*;

import org.junit.Test;

public class JUNITGraphImplementation {

	@Test
	public void test_addNode() {
		String testName = "addNode";
		try {
			GraphImpl<String> testGraph = new GraphImpl<String>();
			testGraph.addVertex("Latin 401");
			assertEquals(1, testGraph.order());
			assertEquals(0, testGraph.size());
		}
		catch (Exception e){
			System.out.println("Caught Exception in " + testName);
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void test_addNodeAndEdge() {
		String testName = "addNodeAndEdge";
		try {
			GraphImpl<String> testGraph = new GraphImpl<String>();
			testGraph.addVertex("Latin 401");
			testGraph.addVertex("Latin 302");
			testGraph.addEdge("Latin 401", "Latin 302");
			assertEquals(2, testGraph.order());
			assertEquals(1, testGraph.size());
		}
		catch (Exception e){
			System.out.println("Caught Exception in " + testName);
			e.printStackTrace();
			fail();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
} // JUNIT class

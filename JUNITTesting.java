import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class JUNITTesting {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	
	// --------------------- BST Tests -----------------------------------//
	@Test
	public void test01_insertOne() {
		String testName = "test01_insertOne";
		BinarySearchTree<String> BST = new BinarySearchTree();
		
		try {
			BST.insert(20, "Value of 20");
		} // try
		
		catch (Exception e) {
			System.out.println("Caught an exception in " + testName);
		} // catch
		assertEquals("Size should be 1",1,BST.checkSize());
	}

	@Test
	public void test02_insertMultiple() {
		String testName = "test02_insertMultiple";
		BinarySearchTree<String> BST = new BinarySearchTree();
		
		try {
			BST.insert(30, "Value of 30");
			BST.insert(20, "Value of 20");
			BST.insert(22, "Value of 22");
			BST.insert(55, "Value of 55");
			assertEquals("Size should be 4",4,BST.checkSize());
			
		} // try
		
		catch (Exception e) {
			System.out.println("Caught an exception in " + testName);
		} // catch	
	}
	
	@Test
	public void test03_getExistingValue() {
		String testName = "test02_insertMultiple";
		BinarySearchTree<String> BST = new BinarySearchTree();
		
		try {
			BST.insert(30, "Value of 30");
			BST.insert(20, "Value of 20");
			BST.insert(22, "Value of 22");
			BST.insert(55, "Value of 55");
			assertEquals("Value should be 'Value of 55' ","Value of 55",BST.get(55).getValue());
			
		} // try
		
		catch (Exception e) {
			System.out.println("Caught an exception in " + testName);
		} // catch	
		
	}
	
	@Test
	public void test08_BSTRemove() {
		String testName = "test08_BSTRemove";
		BinarySearchTree<String> BST = new BinarySearchTree();
		
		//try {
			BST.insert(30, "Value of 30");
			BST.insert(20, "Value of 20");
			BST.insert(22, "Value of 22");
			BST.insert(55, "Value of 55");
			BST.remove(22);
			assertEquals("Size should be 3",3,BST.checkSize());
			assertEquals("Once removed...",null,BST.get(22));
		//} // try
		/*
		catch (Exception e) {
			System.out.println("Caught an exception in " + testName);
		} // catch	
		*/
	}
	
	// ------------------------ AVL Tree Tests ---------------------------------
	
	@Test
	public void test04_AVLInsertOne() {
		String testName = "test04_AVLInsertOne";
		AVLTree<String> AVL = new AVLTree<String>();
		
		try {
			AVL.insert(40, "Value for key 40");
			assertEquals("Size should be 1",1,AVL.checkSize());
		} // try
		
		catch (Exception e) {
			System.out.println("Caught an exception in " + testName);
		} // catch	
	} 
	
	@Test
	public void test05_AVLInsertMultiple() {
		String testName = "test05_AVLInsertMultiple";
		AVLTree<String> AVL = new AVLTree<String>();
		
		try {
			AVL.insert(40, "Value for key 40");
			AVL.insert(50, "Value for key 50");
			AVL.insert(30, "Value for key 30");
			AVL.insert(25, "Value for key 25");
			AVL.insert(32, "Value for key 32");
			AVL.insert(12, "Value for key 12");
			assertEquals("Size should be 6",6,AVL.checkSize());
			assertEquals("Root should be 30",30,AVL.getRoot().getKey());
		} // try
		
		catch (Exception e) {
			System.out.println("Caught an exception in " + testName);
		} // catch	
		
	}
	
	@Test
	public void test06_AVLInsertAndDelete() {
		String testName = "test06_AVLInsertAndDelete";
		AVLTree<String> AVL = new AVLTree<String>();
		
		//try {
			AVL.insert(40, "Value for key 40");
			AVL.insert(50, "Value for key 50");
			AVL.insert(30, "Value for key 30");
			AVL.insert(25, "Value for key 25");
			AVL.insert(32, "Value for key 32");
			AVL.insert(12, "Value for key 12");
			assertEquals("Size should be 6",6,AVL.checkSize());

			AVL.remove(25);
			
			assertEquals("Size should be 5",5,AVL.checkSize());
			assertEquals("Root should be 30",30,AVL.getRoot().getKey());
		//} // try
		/*
		catch (Exception e) {
			System.out.println("Caught an exception in " + testName);
		} // catch	
		*/
	}
	
	@Test
	public void test07_AVLInsertAndGet() {
		String testName = "test07_AVLInsertAndGet";
		AVLTree<String> AVL = new AVLTree<String>();
		
		try {
			AVL.insert(40, "Value for key 40");
			AVL.insert(50, "Value for key 50");
			AVL.insert(30, "Value for key 30");
			AVL.insert(25, "Value for key 25");
			AVL.insert(32, "Value for key 32");
			AVL.insert(12, "Value for key 12");
			
			AVL.get(32);
			
			assertEquals("Size should be 6",6,AVL.checkSize());
			assertEquals("Get's value should be -value of 32-","Value for key 32",AVL.get(32).getValue());
		} // try
		
		catch (Exception e) {
			System.out.println("Caught an exception in " + testName);
		} // catch	
		
	}
	
	// --------------- HashTable Tests ----------------------
	
	@Test
	public void test09_HTInsert() {
		String testName = "test09_HTInsert";
		HashTable<String> ht = new HashTable<String>();
		
		try {
			ht.insert(40, "Value for key 40");
			
			assertEquals("Size should be 1",1,ht.getSize());
			assertEquals("Get's value should be -Value for key 40-","Value for key 40",ht.get(40));
		} // try
		
		catch (Exception e) {
			System.out.println("Caught an exception in " + testName);
		} // catch	
		
		
	}
	
	
	@Test
	public void test10_HTDelete() {
		String testName = "test10_HTDelete";
		HashTable<String> ht = new HashTable<String>();
		
		try {
			ht.insert(40, "Value for key 40");
			ht.insert(38, "Value for key 38");
			ht.insert(50, "Value for key 50");
			
			assertEquals("Size should be 3",3,ht.getSize());
			ht.remove(38);
			assertEquals("Size should be 2",2,ht.getSize());

			assertEquals("Get's value should be -null-",null,ht.get(38));
		} // try
		
		catch (Exception e) {
			System.out.println("Caught an exception in " + testName);
		} // catch	
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
		String testName = "test02_insertMultiple";
		BinarySearchTree<String> BST = new BinarySearchTree();
		
		try {

		} // try
		
		catch (Exception e) {
			System.out.println("Caught an exception in " + testName);
		} // catch	
	*/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
} // BSTTest class


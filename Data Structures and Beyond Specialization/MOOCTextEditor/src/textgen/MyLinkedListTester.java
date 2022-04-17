/**
 * 
 */
package textgen;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

/**
 * @author UC San Diego MOOC team
 *
 */


/*
 * One important thing to keep in mind is that UNIT Tests don't save across different 
 * tester methods. For example if you added or removed to an ArrayList in one unit tester method, 
 * those things you did will not save in a different tester methods. 
 * UNIT TESTS WORK BY CALLING A SETUP METHOD WHICH WILL INTITIALIZE EVERYTHING NEEDED
 * THEN IT WILL GO THROUGH THE TESTER METHODS ONE BY ONE, CALLING THE SETEUP METHOD
 * AGAIN AFTER EACH METHOD IS FINISHED IN ORDER TO RESET THE INITIALIZATIONS SO THAT
 * ALL INITIALIZED VARIABLES OR LISTS IN THE SETUP METHOD STAY THE SAME  AT THE START FOR EVERY
 * SINGLE TESTER METHOD THAT YOU TEST. 
 */


public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH =10; 

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Feel free to use these lists, or add your own
	    shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.add(i);
		}
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);
		
	}

	
	/** Test if the get method is working correctly.
	 */
	/*You should not need to add much to this method.
	 * We provide it as an example of a thorough test. */
	@Test
	public void testGet()
	{
		//test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));
		
		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			shortList.get(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}
		
		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
	}
	
	
	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
	@Test
	public void testRemove()
	{
		int a = list1.remove(0);
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());
		
		try {
			a = list1.remove(-1);
			fail("not a valid index ");
		}
		catch(IndexOutOfBoundsException e) {
			
		}
		
		try {
			a = list1.remove(100);
			fail("not a valid index ");
		}
		catch(IndexOutOfBoundsException e) {
			
		}
		
		a = list1.remove(list1.size()-1);
		assertEquals("Remove: check a is correct ", 42, a);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 1, list1.size());
		
		a = longerList.remove(5);
		assertEquals("Remove: check a is correct ", 5, a);
		assertEquals("Remove: check element 0 is correct ", (Integer)0, longerList.get(0));
		assertEquals("Remove: check size is correct ", 9, longerList.size());
		
		
		// TODO: Add more tests here
	}
	
	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd()
	{
		
		shortList.add("3");
		assertEquals("Checking adding to an index ", "3", shortList.get(2));
		
		list1.add(40);
		assertEquals("Checking adding to an index ", (Integer)40, list1.get(3));
		
		longerList.add(10);
		assertEquals("Checking adding to an index ", (Integer)10, longerList.get(10));
		
		try {
			shortList.add(null);
			fail("make sure you can't add null element to list");
		}
		catch(NullPointerException e){
			
		}
		
        // TODO: implement this test
		
	}

	
	/** Test the size of the list */
	@Test
	public void testSize()
	{
		
		int size1 = list1.size();
		list1.add(5);
		int size2 = list1.size();
		assertEquals("checking add method ", size1+1,size2);
		
		
		size1 = list1.size();
		list1.add(1,4);
		size2 = list1.size();
		assertEquals("checking add at index method ", size1 + 1, size2);
		
		size1 = list1.size();
		list1.remove(0);
		size2 = list1.size();	
		assertEquals("checking the remove method ", size1-1, size2);
	}
	
	
	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex()
	{
		
		//checking with index over what has already been added to list
		try {
			shortList.add(30,"hello");
			fail("not a valid index to add to");
		}
		catch(IndexOutOfBoundsException e){
			
		}
		
		longerList.add(10,10);
		assertEquals("checking if adding to index at end ", (Integer)10,longerList.get(10));
		
		list1.add(2,5);
		assertEquals("checking if add to any index already within array ", (Integer)5,list1.get(2));
		
		list1.add(0,70);
		assertEquals("checking if add to first index ", (Integer)70,list1.get(0));
		
		try {
			list1.add(-1,15);
			fail("checking if adding to a negative index");
		}
		catch(IndexOutOfBoundsException e) {
			
		}
		
		// TODO: implement this test
		
	}
	
	/** Test setting an element in the list */
	@Test
	public void testSet()
	{
		
		try {
			shortList.set(30,"hello");
			fail("not a valid index to add to");
		}
		catch(IndexOutOfBoundsException e){
			
		}
		
		try {
			shortList.set(-5,"hello");
			fail("not a valid index to add to");
		}
		catch(IndexOutOfBoundsException e){
			
		}

		try {
			shortList.set(0,null);
			fail("cannot enter a null value");
		}
		catch(NullPointerException e){
			
		}
		
		shortList.set(1, "hello");
		assertEquals("setting index in the middle ", "hello", shortList.get(1));
		
		list1.set(0, 1000);
		assertEquals("setting first index ", (Integer)1000, list1.get(0));
		
		longerList.set(9, 467);
		assertEquals("setting the last index ", (Integer)467, longerList.get(9));
	    // TODO: implement this test
	    
	}
	
	
	// TODO: Optionally add more test methods.
	
}

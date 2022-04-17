/**
 * 
 */
package spelling;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

/**
 * @author UC San Diego MOOC team
 *
 */
public class NearbyWordsTester {


	NearbyWords dict;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception 
	{
		
		Dictionary d = new DictionaryHashSet();
	    DictionaryLoader.loadDictionary(d, "data/words.small.txt");
	    dict = new NearbyWords(d);
		
	}

	@Test
	public void testSubstitution()
	{
		String alphabet = "abcdefghijklmnopqrstuvwxyz";
		String test = "h";
		ArrayList<String> substitutes = new ArrayList<String>();
		
		for(int i = 0; i < alphabet.length(); i++) {
			String letter = alphabet.substring(i,i+1);
			if(!letter.equals(test)) {
				substitutes.add(letter);
			}
		}
		
		ArrayList<String> fromMethod = new ArrayList<String>();
		dict.substitution(test, fromMethod, false);
		
		assertEquals("substitutes ", substitutes, fromMethod);
		
	}
	
	@Test
	public void testInsertion()
	{
		String test = "h";
		ArrayList<String> fromMethod = new ArrayList<String>();
		dict.insertions(test, fromMethod, false);
		System.out.println(fromMethod);
		
	}	
	
	@Test
	public void testDeletion()
	{
		String test = "hello";
		ArrayList<String> deletions = new ArrayList<String>();
		deletions.add("ello");
		deletions.add("hllo");
		deletions.add("helo");
		deletions.add("hell");
		
		ArrayList<String> fromMethod = new ArrayList<String>();
		dict.deletions(test, fromMethod, false);
		
		assertEquals("test deletion ",deletions, fromMethod);
	}	
	
	
}

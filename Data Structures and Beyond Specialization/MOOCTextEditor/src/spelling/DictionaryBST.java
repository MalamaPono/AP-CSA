package spelling;

import java.util.TreeSet;

/**
 * @author UC San Diego Intermediate MOOC team
 *
 */


//running time O(log(n)) in worst case and average case to find a specific element in a balanced BST
//in a regular BST that is not balanced, the average case is O(log(n)) and worst case is O(n) which
//the worst case actually happens fairly often as a result of a stringy and long tree in a certain direction.

public class DictionaryBST implements Dictionary 
{
   private TreeSet<String> dict; //a tree set is an already made balanced binary search tree data structure in java.
	
   
   public DictionaryBST() {
	   dict = new TreeSet<String>();
   }
    // TODO: Implement the dictionary interface using a TreeSet.  
 	// You'll need a constructor here
	
    
    /** Add this word to the dictionary.  Convert it to lowercase first
     * for the assignment requirements.
     * @param word The word to add
     * @return true if the word was added to the dictionary 
     * (it wasn't already there). */
    public boolean addWord(String word) {
    	
    	word = word.toLowerCase();
    	if(dict.contains(word) == false) {
    		dict.add(word);
    		return true;
    	}else {
    		return false;
    	}
    	
    	// TODO: Implement this method
    }


    /** Return the number of words in the dictionary */
    public int size()
    {
        return dict.size();
    }

    /** Is this a word according to this dictionary? */
    public boolean isWord(String s) {
    	//TODO: Implement this method
    	
    	s = s.toLowerCase();
    	if(dict.contains(s)) {
    		return true;
    	}else {
    	    return false;
    	}
    }

}

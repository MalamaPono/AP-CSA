package spelling;

import java.util.LinkedList;


//running time O(n) in worst case and average case to find a specific element in the Linked List


/**
 * A class that implements the Dictionary interface using a LinkedList
 *
 */
public class DictionaryLL implements Dictionary 
{

	private LinkedList<String> dict;
	
    public DictionaryLL() {
    	dict = new LinkedList<String>();
    }


    /** Add this word to the dictionary.  Convert it to lowercase first
     * for the assignment requirements.
     * @param word The word to add
     * @return true if the word was added to the dictionary 
     * (it wasn't already there). */
    public boolean addWord(String word) {
    	
    	word = word.toLowerCase();
    	if(dict.indexOf(word) == -1) {
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
        s = s.toLowerCase();
        if(dict.indexOf(s) != -1) {
        	return true;
        }else {
        	return false;
        }
    }

    
}

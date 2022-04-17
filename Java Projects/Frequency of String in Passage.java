import java.util.*;

/*

Finding the frequency of a certain word in a passage.

*/

public class MyClass {
    public static void main(String args[]) {
        
        String word = "cat";
        String passage = "my cat went to the cat is not the cat although us tha ebs jb sbjhs cc cat cbabdjh adk mat cat dbjs djdlat cat";
        System.out.println(frequencyOfWord(word,passage));
        
    }
    
    public static int frequencyOfWord(String word, String passage){
        
        int index = passage.indexOf(word);
        int count = 0;
        while(index != -1){
            count++;
            index = passage.indexOf(word,index+word.length());
        }
        
        return count;
    }
    
}

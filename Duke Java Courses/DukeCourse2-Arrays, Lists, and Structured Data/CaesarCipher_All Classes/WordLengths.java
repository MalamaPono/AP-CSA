
/**
 * Write a description of WordLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;


public class WordLengths {

    
    public void countWordLengths(FileResource fr, int[] count){
        for(String word : fr.words()){
            int wordLength = 0;
            for(int i = 0; i < word.length(); i++){
                if(Character.isLetter(word.charAt(i))){
                    wordLength++;
                }
            }
            count[wordLength] += 1;
        }
    }
    
    public void testCountWordLengths(){
        FileResource fr = new FileResource();
        int[] count = new int[31];
        countWordLengths(fr, count);
        
        for(int i = 0; i < count.length; i++){
            System.out.println("There are " + count[i] + " words of word length " + i);
        }
        System.out.println(indexOfMax(count) + " is the most common word length");
        
    }
    
    public int indexOfMax(int[] values){
        int max = -1;
        int loc = -1;
        for(int i = 0 ; i < values.length; i++){
            if(values[i] > max){
                max = values[i];
                loc = i;
            }
        }
        return loc;
    }
}

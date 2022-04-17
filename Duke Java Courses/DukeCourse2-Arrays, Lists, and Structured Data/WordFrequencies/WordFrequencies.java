
/**
 * Find out how many times each word occurs, and
 * in particular the most frequently occurring word.
 * 
 * @author Duke Software Team
 * @version 1.0
 */
import edu.duke.*;
import java.util.ArrayList;
import java.util.HashMap;

public class WordFrequencies
{
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    
    public WordFrequencies() {
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    
    public void findUnique(){
        FileResource resource = new FileResource();
        
        //parallel ArrayList implementation
        for(String s : resource.words()){
            s = s.toLowerCase();
            int index = myWords.indexOf(s);
            if (index == -1){
                myWords.add(s);
                myFreqs.add(1);
            }
            else {
                int freq = myFreqs.get(index);
                myFreqs.set(index,freq+1);
            }
        }
        //hashmap implementation
        /*
        HashMap<String,Integer> map = new HashMap<String,Integer>();
        FileResource fr = new FileResource();
        for(String w : fr.words()){
            w = w.toLowerCase();
            if(!map.containsKey(w)){
                map.put(w,1);
            }else{
                map.put(w, map.get(w)+1)
            }
        }
        */
    }
    
    public void tester(){
        findUnique();
        System.out.println("# unique words: "+myWords.size());
        int index = findMax();
        System.out.println("max word/freq: "+myWords.get(index)+" "+myFreqs.get(index));
    }
    public int findMax(){
        int max = myFreqs.get(0);
        int maxIndex = 0;
        for(int k=0; k < myFreqs.size(); k++){
            if (myFreqs.get(k) > max){
                max = myFreqs.get(k);
                maxIndex = k;
            }
        }
        return maxIndex;
    }
}

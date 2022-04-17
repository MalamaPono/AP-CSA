
/**
 * Write a description of WordFreqs here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;
import java.io.*;
public class WordFreqs {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    
    public WordFreqs(){
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    
    public void findUnique(){
        myWords.clear();
        myFreqs.clear();
        FileResource fr = new FileResource();
        for(String str : fr.words()){
            str = str.toLowerCase();
            int index = myWords.indexOf(str);
            if(index == -1){
                myWords.add(str);
                myFreqs.add(1);
            }else{
                int val = myFreqs.get(index);
                myFreqs.set(index, val+1);
            }
        }
    }
    
    public int findIndexOfMax(){
        int max = -1;
        int loc = -1;
        for(int i = 0 ; i < myFreqs.size(); i++){
            if(myFreqs.get(i) > max){
                max = myFreqs.get(i);
                loc = i;
            }
        }
        return loc;
    }
    
    public void tester(){
        findUnique();
        System.out.println("The amount of unique words are " + myWords.size());
        /*
        System.out.println("Now I will print all the unique words with their respective frequency");
        for(int i = 0; i < myWords.size(); i++){
            System.out.println(myWords.get(i) + " " + myFreqs.get(i));
        }
        */
        int max = findIndexOfMax();
        System.out.println("The most frequent word in this file is: " + "(" + myWords.get(max) + ")" + " and it occurs " + myFreqs.get(max) + " times");
    }

}

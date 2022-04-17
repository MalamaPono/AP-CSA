
/**
 * Write a description of EfficientMarkovWord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;


public class EfficientMarkovWord implements IMarkovModel{
    
    HashMap<WordGram, ArrayList<String>> map;
    private String[] myText;
    private int myOrder;
    private Random myRandom;
    
    public EfficientMarkovWord(int num){
        myRandom = new Random();
        myOrder = num;
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
    }
    
    public String toString(){
        String x = "EfficientMarkovWord of order ";
        String y = Integer.toString(myOrder);
        return x+y;
    }
    
    public void buildMap(){
        map = new HashMap<WordGram, ArrayList<String>>();
        for(int i = 0; i <= myText.length-myOrder; i++){
            WordGram add = new WordGram(myText, i, myOrder);
            ArrayList<String> follows = new ArrayList<String>();
            String next;
            if(i == myText.length-myOrder){
                if(!map.containsKey(add)){
                    map.put(add,follows);
                }
                break;
            }
            
            if(map.containsKey(add)){
                next = myText[i+myOrder];
                follows = map.get(add);
                follows.add(next);
                map.put(add,follows);
            }else{
                next = myText[i+myOrder];
                follows.add(next);
                map.put(add,follows);
            }
        }
    }
    
    public String getRandomText(int numWords){
        buildMap();
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-myOrder);  // random word to start with
        WordGram key = new WordGram(myText, index, myOrder);
        sb.append(key.toString());
        sb.append(" ");
        for(int k=0; k < numWords-myOrder; k++){
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key = key.shiftAdd(next);
        }
        return sb.toString().trim();
    }
    
    private ArrayList<String> getFollows(WordGram kGram) {
        if(map.containsKey(kGram)){
            return map.get(kGram);
        }else{
            return new ArrayList<String>();
        }
    }
    
    private int indexOf(String[] words, WordGram target, int start){
        for(int i = start; i < words.length-target.length(); i++){
            WordGram match = new WordGram(words, i, target.length());
            if(match.equals(target)){
                return i;
            }
        }
        return -1;
    }
    
    public void printHashMapInfo(){
        //System.out.println(map); //prints out all keys and values
        System.out.println("There are " + map.size() + " keys");
        int max = 0;
        WordGram maxGram;
        for(WordGram x : map.keySet()){
            int temp = map.get(x).size();
            if(temp > max){
                max = temp;
                maxGram = x;
            }
        }
        System.out.println("The maximum amount of times a word gram appears is " + max);
        
        System.out.println("Those/the word grams with maximum appearence are: ");
        for(WordGram y : map.keySet()){
            if(map.get(y).size() == max){
                System.out.println(y);
            }
        }
        System.out.println("The " + max + " words that follow each word with max appearences are");
        for(WordGram y : map.keySet()){
            if(map.get(y).size() == max){
                System.out.println("for: " + y);
                for(String a : map.get(y)){
                    System.out.println(a);
                }
            }
        }
    }
}

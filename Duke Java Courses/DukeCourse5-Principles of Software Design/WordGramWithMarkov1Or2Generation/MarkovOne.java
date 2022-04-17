
/**
 * Write a description of class MarkovZero here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import java.util.Random;
import java.util.*;

public class MarkovOne {
    private String myText;
    private Random myRandom;
    
    public MarkovOne() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
        myText = s.trim();
    }
    
    public String toString(){
        return "MarkovOne chars of order 1";
    }
    
    public ArrayList<String> getFollows(String key){
        ArrayList<String> follows = new ArrayList<String>();
        int pos = 0;
        while(pos < myText.length()){
            int index = myText.indexOf(key, pos);
            if(index == -1){
                break;
            }
            if(index + key.length() > myText.length()-1){
                break;
            }
            String next = myText.substring(index+key.length(), index+key.length()+1);
            follows.add(next);
            pos = index+key.length();
        }
        return follows;
    }
    
    public String getRandomText(int numChars){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-1);
        String key = myText.substring(index, index+1);
        sb.append(key);
        for(int i = 0; i < numChars-1; i++){
            ArrayList<String> follows = getFollows(key);
            if(follows.size() == 0){
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = next;
            
        }
        return sb.toString();
    }

}

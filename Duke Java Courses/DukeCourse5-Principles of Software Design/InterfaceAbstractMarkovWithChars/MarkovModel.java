
/**
 * Write a description of class MarkovZero here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import java.util.Random;
import java.util.*;

public class MarkovModel extends AbstractMarkovModel{
    
    private int num;
    
    public MarkovModel(int num) {
        this.num = num;
    }
    
    //if you want to change markov to predict after certain amount of letters change in 3 parts. For example MarkovN
    public String getRandomText(int numChars){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-num); //change num here
        String key = myText.substring(index, index+num); //change num here
        sb.append(key);
        for(int i = 0; i < numChars-num; i++){ //change num here minus from numChars
            ArrayList<String> follows = getFollows(key);
            if(follows.size() == 0){
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = key.substring(1) + next;
        }
        return sb.toString();
    }
    
    public String toString(){
        String x = "Markov of order ";
        String numStr = Integer.toString(num);
        return x+numStr;
    }
    
    public int getOrderNum(){
        return num;
    }
}


/**
 * Write a description of MarkovWordAny here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;


public class MarkovWordAny implements IMarkovModel{
    
    private String[] myText;
    private int myOrder;
    private Random myRandom;
    
    public MarkovWordAny(int num){
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
        String x = "MarkovWord of order ";
        String y = Integer.toString(myOrder);
        return x+y;
    }
    
    public String getRandomText(int numWords){
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
        ArrayList<String> follows = new ArrayList<String>();
        int pos = 0;
        while(pos < myText.length){
            int index = indexOf(myText, kGram, pos);
            if(index == -1){
                break;
            }
            if(index + myOrder > myText.length-1){
                break;
            }
            String next = myText[index+kGram.length()];
            follows.add(next);
            pos = index+1;
        }
        return follows;
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

}

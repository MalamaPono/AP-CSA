
/**
 * Write a description of EfficientMarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;

public class EfficientMarkovModel extends AbstractMarkovModel{
    
    private HashMap<String,ArrayList<String>> map = new HashMap<String,ArrayList<String>>();
    private int num;
    
    public EfficientMarkovModel(int num){
        this.num = num;
    }
    /*
     * this is trying other way and attemping to add keys as we go and find new keys in random method. This is just experimental code for practice and doesnʻt work as the real method underneath.
    public void buildMap(String key){
        ArrayList<String> follows = new ArrayList<String>();
        map.put(key,follows); 
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
            map.put(key,follows);
            pos = index+key.length();
        }
    }
    */
    public void buildMap(){
        for(int i = 0; i <= myText.length()-num; i++){
            ArrayList<String> follows = new ArrayList<String>();
            String check = myText.substring(i, i+num);
            String next;
            if(i == myText.length()-num){
                if(!map.containsKey(check)){
                    map.put(check,follows);
                }
                break;
            }
            if(map.containsKey(check)){
                next = myText.substring(i+num, i+num+1);
                follows = map.get(check);
                follows.add(next);
                map.put(check, follows);
            }else{
                next = myText.substring(i+num, i+num+1);
                follows.add(next);
                map.put(check,follows);
            }
        }
    }
    
    public int getOrderNum(){
        return num;
    }
    
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
        String x = "This this is the EfficientMarkovModel class of order ";
        String y = Integer.toString(getOrderNum());
        return x+y;
    }
    
    public ArrayList<String> getFollows(String key){
        if(map.containsKey(key) == true){
            return map.get(key);
        }else{
            return new ArrayList<String>();
        }
    }
    
    public void printHashMapInfo(){
        System.out.println("******");
        System.out.println("There are " + map.size() + " keys");
        int max = 0;
        String save = "";
        for(String a : map.keySet()){
            int temp = map.get(a).size();
            if(temp > max){
                max = temp;
                save = a;
            }
        }
        //System.out.println("The largest arrayList of characters that follows a certain key (could be key of any length, just one letter, or many letters. The first key it picks is random and picks somewhere in the story to choose a letter of length you 
        //chose than it will statistically create random words after that are most likely the ones that would follow.");
        System.out.println(map.get(save).size());
        //printing Strings with maximum size value
        ArrayList<String> keysWithMax = new ArrayList<String>();
        for(String a : map.keySet()){
            if(map.get(a).size() == max){
                keysWithMax.add(a);
            }
        }
        System.out.println("keys that have maximum value are " + keysWithMax);
    }

}

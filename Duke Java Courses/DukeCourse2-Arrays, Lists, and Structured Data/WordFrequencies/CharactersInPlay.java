
/**
 * Write a description of CharactersInPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
import java.util.*;

public class CharactersInPlay {
    
    private ArrayList<String> characters;
    private ArrayList<Integer> count;
    
    public CharactersInPlay(){
        characters = new ArrayList<String>();
        count = new ArrayList<Integer>();
    }
    
    public void update(String person){
        int index = characters.indexOf(person);
        if(index == -1){
            characters.add(person);
            count.add(1);
        }else{
            int val = count.get(index);
            count.set(index, val+1);
        }
    }
    
    public void findAllCharacters(){
        characters.clear();
        count.clear();
        FileResource fr = new FileResource();
        for(String str : fr.lines()){
            int index = str.indexOf(".");
            if(index != -1){
                String name = str.substring(0, index);
                update(name);
            }
        }
       System.out.println("here");
        
    }
    
    public void tester(){
        findAllCharacters();
        findMax();
        charactersWithNumParts(10, 15);
        
        /*
        for(int i = 0; i < characters.size(); i++){
            if(count.get(i) > 5){
                System.out.println(characters.get(i) + " spoke " + count.get(i) + " times");
            }
        }
        */
    }
    
    public void charactersWithNumParts(int num1, int num2){
        for(int i = 0; i < characters.size(); i++){
            if(count.get(i) >= num1 && count.get(i) <= num2){
                System.out.println(characters.get(i) + " spoke " + count.get(i) + " times");
            }
        }
    }
    
    public void findMax(){
        int max = 0;
        int maxIndex = 0;
        for(int i = 0 ; i < characters.size(); i++){
            if(count.get(i) > max){
                max = count.get(i);
                maxIndex = i;
            }
        }
        System.out.println(characters.get(maxIndex) + " was the highest speaker and spoke " + count.get(maxIndex) + " times");
    }
}

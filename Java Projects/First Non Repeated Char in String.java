
import java.util.*;
/*
1) Find the first non-repeated character in a String.
*/

public class MyClass{
    public static void main(String[] args){
        
        //if an empty character is returned that resembles that there is no non-repeated characters in the String. this means that in the string, 
        //every character present apprears at least 2 times. 
        // the empty character is resembled by '\0'
        
        String check = "swwsii t unmsdnkadnkjsnkads";
        char firstNonRepeatedCharacter = firstNonRepeatedCharacter(check);
        System.out.println(firstNonRepeatedCharacter);
        
    }
    
    
    public static char firstNonRepeatedCharacter(String str){
        if(str.length() == 0){
            return '\0';
        }
        if(str.length() == 1){
            return str.charAt(0);
        }
        
        HashMap<Character,Integer> map = new HashMap<Character,Integer>();
        
        for(char c : str.toCharArray()){
            if(map.containsKey(c) == true){
                map.put(c,map.get(c) + 1);
            }else{
                map.put(c,1);
            }
        }
        
        for(char ch : str.toCharArray()){
            if(map.get(ch) == 1){
                return ch;
            }
        }
        
        return '\0';
        
    }
    
}

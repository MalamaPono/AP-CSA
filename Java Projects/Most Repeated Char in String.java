import java.util.HashMap;
/*
Finding the most repeated character in a list
*/

public class MyClass {
    public static void main(String args[]) {
        
        String text = "aaaaaaa this is the sentance aaaaa";
        System.out.println(mostRepeated(text));
        
    }
    
    public static char mostRepeated(String text){
        HashMap<Character,Integer> map = new HashMap<Character,Integer>();
        
        for(char c : text.toCharArray()){
            if(map.containsKey(c) == false){
                map.put(c,1);
            }else{
                map.put(c,map.get(c)+1);
            }
        }
        
        int max = 0;
        char mostRepeated = '\0';
        for(char ch : map.keySet()){
            if(map.get(ch) > max){
                max = map.get(ch);
                mostRepeated = ch;
            }
        }
        
        return mostRepeated;
        
    }
    
}
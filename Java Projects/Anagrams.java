import java.util.*;
public class MyClass {
    public static void main(String args[]) {
        
        
        String one = "rellH";
        String two = "Hellr";
        
        System.out.println(anagram(one,two));
        
        
    
    }
    
    //if care about casing make sure every letter in String 1 exactly matches every letter in String 2 with casing preserved. For example, Casing would not match casing, but
    //gnisaC would match Casing.
    
    //if don't care about casing and just want to compare two strings to see if they can make the same word since they have the same Characters, change them 
    //both to lower case before comparing them. 
    
    
    
    
    //brute force strategy
    public static boolean anagram(String one, String two){
        if(one.equals(two)){
            return true;
        }
        
        if(one.length() != two.length()){
            return false;
        }
        
        //check to see if all their characters are the same
        while (one.length() > 0){
            String x = one.substring(0,1);
            int index = two.indexOf(x);
            if(index == -1){
                return false;
            }else{
                one = one.substring(1);
                two = two.substring(0,index) + two.substring(index+1);
            }
        }
        return true;
        
    }
    
    //sorting both words strategy with built in java methods
    public static boolean anagram(String one, String two){
        char[] first = one.toCharArray();
        Arrays.sort(first);
        char[] second = two.toCharArray();
        Arrays.sort(second);
        return Arrays.equals(first,second);
    }
    
    
    //sorting strategy with only my methods
    public static boolean anagram(String one, String two){
        
        if(one.equals(two)){
             return true;
         }
        
        if(one.length() != two.length()){
             return false;
        }
        
        
        char[] first = one.toCharArray();
        char[] second = two.toCharArray();
        
        insertionSort(first);
        insertionSort(second);
        
        
        for(int i = 0; i < one.length(); i++){
            if(first[i] != second[i]){
                return false;
            }
        }
        
        return true;
    }
    
    
    //compareTo methods are only for objects. For example of the String class, or of a random class you want to make and sort so you need to define a compareTo method. 
    //compareTo methods are used to sort objects and can only be called on objects. object.compareTo(object other).
    //for primitive types, you can sort them without compareTo methods. Primitive types already have built in ways to find which ones are less than the other.
    //for example integers you can compare by doing (x < y) or doubles you can compare (x < y) or even chars you can compare (x < y). For example in chars, 
    //'a' < 'b' becuase a comes before b alphabetically. Or 'z' > 'c' becuase z comes after c alphabetically. 
    public static void insertionSort(char[] ch){
        
        for(int currentPos = 1; currentPos < ch.length; currentPos++){
            int checkPos = currentPos;
            char temp = ch[checkPos];
            while(checkPos > 0 && temp < ch[checkPos-1]){
                ch[checkPos] = ch[checkPos-1];
                checkPos--;
            }
            ch[checkPos] = temp;
        }
    }
    
    
}


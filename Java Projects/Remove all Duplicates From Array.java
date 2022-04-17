import java.util.*;
/*
1) Remove duplicate elements from java
*/

public class MyClass{
    public static void main(String[] args){
        
        int[] original = {1,2,2,2,2,5,5,6,6,7,8,24,22,15,15,14};
        int[] newArray = removeDuplicates(original);
        System.out.println(Arrays.toString(newArray));
        
        
    }
    
    public static int[] removeDuplicates(int[] num){
    
    HashSet<Integer> numbers = new HashSet<Integer>();
    for(int x : num){
        if(numbers.contains(x) == false){
            numbers.add(x);
        }
    }
    
    int[] newArray = new int[numbers.size()];
    int index = 0;
    for(Integer x : numbers){
        newArray[index] = x;
        index++;
    }
    
    return newArray;
    
}

}

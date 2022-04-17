import java.util.*;

/*

Check if a number is binary or not. Only have 1's or 0's.

*/



public class MyClass{
    
    public static void main(String[] args){
        
        
        System.out.println(isBinary(92738));
        
    }
    
    public static boolean isBinary(int x){
        while(x != 0){
            int checkDigit = x%10;
            if(checkDigit != 0 && checkDigit != 1){
                return false;
            }
            x /= 10;
        }
        return true;
    }
    
}

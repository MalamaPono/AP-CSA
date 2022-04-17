import java.util.*;

/*

Reverse an integer.

*/



public class MyClass{
    
    public static void main(String[] args){
        
        
        System.out.println(reverseInteger(92738));
        
    }
    
    public static int reverseInteger(int x){
        
        ArrayList<Integer> digits = new ArrayList<Integer>();
        while(x!=0){
            int digit = x % 10;
            digits.add(0,digit);
            x/=10;
        }
        
        int reversedNum = 0;
        int exponent = 0;
        for(Integer digit: digits){
            reversedNum += Math.pow(10,exponent) * digit;
            exponent++;
        }
        
        return reversedNum;
    }
    
}

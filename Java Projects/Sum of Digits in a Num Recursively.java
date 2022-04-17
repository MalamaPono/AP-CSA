
import java.util.*;

/*

Sum of the digits in a number using recursion

*/



public class MyClass{
    
    public static void main(String[] args){
        
        
        int num = 567;
        System.out.println(sumDigit(num));
        
        
    }
    
    public static int sumDigit(int x){
        if(x==0){
            return 0;
        }
        
        return x%10 + sumDigit(x/10);
    }
    
}

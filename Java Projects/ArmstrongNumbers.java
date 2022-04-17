import java.util.*;

/*

Identifying if a number is an armstrong number or not. An armstrong number is a 3 digit number whos cube of each digits sum to the number itself. 
For example, 371 because 3^3 + 7^3 + 1^3 = 371.

*/



public class MyClass{
    
    public static void main(String[] args){
        
        int num = 123;
        System.out.println(isArmstrong(num));
        
    }
    
    public static boolean isArmstrong(int num){
        if(numDigits(num) != 3){
            return false;
        }
        
        int temp = num;
        
        int sum = 0;
        for(int i = 0; i < 3; i++){
            int digit = temp % 10;
            sum += Math.pow(digit,3);
            temp /= 10;
        }
        
        if(sum == num){
            return true;
        }else{
            return false;
        }
        
    }
    
    public static int numDigits(int num){
        int digits = 0;
        while(num != 0){
            digits++;
            num /= 10;
        }
        return digits;
    }
    
}

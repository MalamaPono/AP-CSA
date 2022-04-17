
import java.util.*;

/*
1) Converting numeric Strings to integer values without using built in java methods.
*/


public class MyClass{
    public static void main(String[] args){
        
        int value = intValue("-2231242");
        System.out.println(value + 2231242);
    }
    
    //1787
    public static int intValue(String number){
        boolean isNegative = false;
        String[] digits = {"0","1","2","3","4","5","6","7","8","9"};
        
        if(number.substring(0,1).equals("-")){
            isNegative = true;
        }
        
        
        int placeValue = number.length()-1;
        int finalValue = 0;
        
        for(int i = 0; i < number.length(); i++){
            String digit = number.substring(i,i+1);
            int integerValueOfString = 0;
            
            
            for(int j = 0; j < digits.length; j++){
                String x = digits[j];
                if(x.equals(digit)){
                    integerValueOfString = j;
                    break;
                }
            }
            
            finalValue += integerValueOfString * Math.pow(10,placeValue);
            placeValue --;
        }
        
        if(isNegative == true){
            return finalValue * -1;
        }else{
            return finalValue;
        }
    }
    
}

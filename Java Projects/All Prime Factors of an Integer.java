
import java.util.*;

/*

Finding prime factors of an integer

*/



public class MyClass{
    
    public static void main(String[] args){
        
        System.out.println(primeFactors(60));
        
    }
    
    public static ArrayList<Integer> primeFactors(int num){
        
        ArrayList<Integer> primeFactors = new ArrayList<Integer>();
        
        for(int i = 2; i < num/2; i++){
            if(isPrime(i) == true){
                if(num % i == 0){
                    primeFactors.add(i);
                }
            }
        }
        
        return primeFactors;
    }
    
    public static boolean isPrime(int num){
        for(int i = 2; i < num; i++){
            if(num % i == 0){
                return false;
            }
        }
        return true;
    }
    
}

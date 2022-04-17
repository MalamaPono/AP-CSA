import java.util.*;

/*

Write a program to find all the prime numbers up to a specific number, and print out all those prime numbers.

*/



public class MyClass{
    
    public static void main(String[] args){
        
        
        printAllPrimeNumbers(8);
        
    }
    
    
    public static void printAllPrimeNumbers(int threshold){
        for(int i = 2; i <= threshold; i++){
            if(isPrime(i) == true){
                System.out.println(i);
            }
        }
    }
    
    public static boolean isPrime(int check){

        for(int i = 2; i < check; i++){
            if(check % i == 0){
                return false;
            }
        }
        return true;
    }
    
}

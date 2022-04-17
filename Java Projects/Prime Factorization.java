import java.util.*;
/*
Print out the prime factorization of a number. This will include being able to print and save the highest prime factor of a number.
*/

public class MyClass {
    public static void main(String args[]) {
        
        ArrayList<Integer> list = primeFactorization(546);
        System.out.println(list);
        
    }
    
    public static ArrayList<Integer> primeFactorization(int num){
        
        ArrayList<Integer> list = new ArrayList<Integer>();
        
        int squareRoot = (int)(Math.pow(num,0.5));
        for(int i = 2; i < squareRoot; i++){
            if(isPrime(i) == true){
                if(num % i == 0){
                    list.add(i);
                    num = num/i;
                    i--;
                }
            }
        }
        
        if(num != 1){
            list.add(num);
        }
        
        return list;
        
    }
    
    public static boolean isPrime(int x){
        for(int i = 2; i < x/2; i++){
            if(x%i == 0){
                return false;
            }
        }
        return true;
    }
    
}
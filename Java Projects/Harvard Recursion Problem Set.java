public class MyClass{
    public static void main(String[] args){
        
        int nthFibonacciString = 4;
        
        
        // String fiboString = fibonacciStringNaive(nthFibonacciString);
        // System.out.println(fiboString);
        
        // String[] memo = new String[nthFibonacciString+1];
        // for(int i = 0; i < memo.length; i++){
        //     memo[i] = null;
        // }
        // String fiboString = fibonacciStringMemo(nthFibonacciString, memo);
        // System.out.println(fiboString);
        
        String fiboString = fibonacciStringBottomUp(nthFibonacciString);
        System.out.println(fiboString);
        
        
    }




/*
1) The Fibonacci strings are a series of recursively­defined strings. F₀ is the string a, F₁ is
the string bc, and Fₙ₊₂ is the concatenation of Fₙ and Fₙ₊₁. For example, F₂ is abc, F₃ is
bcabc, F₄ is abcbcabc, etc. Given a number n and an index k, return the kth character of
the string Fₙ.
*/


public static char fibonaciChar(int n, int index){
    String fibonaci = fibonacciStringNaive(n);
    // String fibonaci = fibonacciStringMemo(n);
    // String fibonaci = fibonacciStringBottomUp(n);
    return fibonaci.charAt(index);
}


//helper method. fibonacciString non optimized, naive algorithm. 
public static String fibonacciStringNaive(int n){
    String result = "";
    if(n == 0){
        result = "a";
    }else if(n==1){
        result = "bc";
    }else{
        result = fibonacciStringNaive(n-2) + fibonacciStringNaive(n-1);
    }
    
    return result;
}

//helper method. fibbonacciString with memo
public static String fibonacciStringMemo(int n, String[] memo){
    String result = "";
    if(memo[n] != null){
        return memo[n];
    }
    
    if(n == 0){
        result = "a";
    }else if(n==1){
        result = "bc";
    }else{
        result = fibonacciStringMemo(n-2,memo) + fibonacciStringMemo(n-1,memo);
    }
    
    memo[n] = result;
    return result;
    
}

//helper method. fibonacciString with bottomUp
public static String fibonacciStringBottomUp(int n){
    
    if(n == 0){
        return "a";
    }else if(n==1){
        return "bc";
    }
    
    String[] fibonacciStrings = new String[n+1];
    fibonacciStrings[0] = "a";
    fibonacciStrings[1] = "bc";
    for(int i = 2; i <= n; i++){
        fibonacciStrings[i] = fibonacciStrings[i-2] + fibonacciStrings[i-1]; 
    }
    
    return fibonacciStrings[n];
    
}

}

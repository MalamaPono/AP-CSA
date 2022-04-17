public class Recursion {
    public static void main(String args[]) {
        /*
        countUp(1,10);
        System.out.println();
        countUpv2(1,10);
        int num1 = 135;
        int num2 = 210;
        int gcf = GreatestCommonFactor(num1, num2);
        System.out.println(gcf);
        int num = 4;
        int factorial = factorial(num);
        System.out.println(factorial);
        
        System.out.println(checkPalindrome1("stats"));
        System.out.println(checkPalindrome2("stats"));
        */
        System.out.println(palindrone("yesserski"));
        
        
    }
    public static void countUp(int a, int b){ //for loop iteration
        for(int j = a; j <= b; j++){
            System.out.println(j + " ");
        }
    }
    public static void countUpv2(int a, int b){ //recursive iteration 
        if(a<=b){
            System.out.println(a + " ");
            countUpv2(a+1,b);
        }
    }
    public static int GreatestCommonFactor(int a, int b){
        int rem = a%b;
        if(rem == 0){
            return b;
        }else{
            return GreatestCommonFactor(b, rem);
        }
    }
    public static int factorial(int num){
        if(num <=1){
            return 1;
        }else{
            return num * factorial(num - 1);
        }
    }
    
     public static boolean checkPalindrome1(String str) //1 recursive solution
   {
        if(str.length() == 0 || str.length() == 1){
            return true; 
        }
        if(str.charAt(0) == str.charAt(str.length()-1)){
            return checkPalindrome1(str.substring(1, str.length()-1));
        }
        return false;
   }

    public static boolean checkPalindrome2(String str) //2 non-recursive solution
    {
        int len = str.length();
        int i, j;
        j = len - 1;

        for (i = 0; i <= (len - 1)/2; i++)
        {
            if (str.charAt(i) !=  str.charAt(j))
               return false;
            j--;
        }
        return true;
    }
    public static int fibo1(int n){ //iterative find nth fibonacci number method
        int n1 = 1;
        int n2 = 1;
        int n3 = 1;
        
        if(n==1 || n ==2){
            return 1;
        }else{
            for(int j = 3; j <= n; j++){
                n3 = n1+n2;
                n1 = n2;
                n2 = n3;
            }
            }
            return n3;
        }
        
    public static int fibo2(int n){ //recursive find nth fibonacci number method
        if(n==1 || n==2){
            return 1;
        }else{
            return fibo2(n-1) + fibo2(n-2); 
            /*
            this code here works becuase the definition of a fibonacci sequence is that each term is just the sum of the 2 terms before it. 
            Then when it gets to the base case of the 1st or 2nd term, it returns 1, then will work backward from there to solve problem. Will add
            1+1 to make 3rd term, then 1+2 to make 4th term, then 3+2 to make 5th, then 5+3 to make 6th, then so on and so forth to any term
            in the fibonacci sequence that you please.
            */
        }
    }
    
    //iterative way to check for palindrome makes more sense to me
    public static boolean palindrone(String str){
        String temp = "";
        for(int i = str.length(); i > 0; i--){
            temp = temp + str.substring(i-1, i);
        }
        if(temp.equals(str)){
            return true;
        }else{
            return false;
        }
    }
    
}
    

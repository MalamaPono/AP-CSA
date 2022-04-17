public class MyClass {
    public static void main(String args[]) {
        
        
        String perms = "kkkkotonmp";
        double x = findAllPerms(perms);
        System.out.println(x);
        
    }
    
    public static double findAllPerms(String word){
        
        if(word.length() == 1 || word.length()==0){
            return 0;
        }
        
        double num = factorial(word.length());
        
        int count = 0;
        
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        for(char letter: alphabet.toCharArray()){
            count = 0;
            for(char c : word.toCharArray()){
                if(letter == c){
                    count++;
                }
            }
            if(count > 1){
                num = num/factorial(count);
            }
        }
        return num;
    }
    
    public static int factorial(int n){
        if(n == 0 || n == 1){
            return 1;
        }else{
            return n * factorial(n-1);
        }
    }
    
}

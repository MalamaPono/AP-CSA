public class MyClass {
    public static void main(String args[]) {
        
        // largest palindrone that is a product of 2 3 digit numbers
        int max = 0;
        int num1 = 0;
        int num2 = 0;
        for(int i = 100; i < 1000; i++){
            for(int j = 100; j < 1000; j++){
                int product = i*j;
                if (product > max && isPalindrone(product) == true){
                    max = product;
                    num1 = i;
                    num2 = j;
                }
            }
        }
        
        System.out.println("The first number was " + num1 + ". The second number was "+ num2);
        
    }
        
        
        public static boolean isPalindrone(int x){
            String original = Integer.toString(x);
            String compare = "";
            
            for(int i = original.length(); i > 0;i--){
                compare += original.substring(i-1,i);
            }
            
            if(original.equals(compare)){
                return true;
            }else{
                return false;
            }
        }
        
    
}
/*

Converting double String into a double using my own methods, without built in 
Double.parseDouble() method in java.

*/

public class MyClass {
    public static void main(String args[]) {
        
        String decimal = "783.532";
        System.out.println(toDouble(decimal) + 0.468);
        
    }
    
    public static double toDouble(String x){
        int perIndex = x.indexOf(".");
        if(perIndex != -1){
            String nonDecimal = x.substring(0,perIndex);
            String decimal = x.substring(perIndex+1);
            return parseWhole(nonDecimal) + parseDecimal(decimal);
        }else{
            return parseWhole(x);
        }
    }
    
    public static double parseWhole(String x){
        int exponent = x.length()-1;
        
        double ans = 0;
        for(char c : x.toCharArray()){
            int digit = Character.getNumericValue(c);
            ans += Math.pow(10,exponent)*digit;
            exponent--;
        }
        return ans;
    }
    
    public static double parseDecimal(String x){
        
        double ans = 0;
        int exponent = -1;
        for(char c : x.toCharArray()){
            int digit = Character.getNumericValue(c);
            ans += Math.pow(10,exponent)*digit;
            exponent--;
        }
        return ans;
    }
    
    
}
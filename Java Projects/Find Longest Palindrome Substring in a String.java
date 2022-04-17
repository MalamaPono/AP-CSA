/*
4) Write a function to find out longest palindrome in a given string.
*/


public class MyClass{
    public static void main(String[] args){
        String word = "";
        String longestSubstringPalindrone = longestPalindroneSubstring(word);
        System.out.println(longestSubstringPalindrone); //should be abccba
    }


public static String longestPalindroneSubstring(String word){
    
    String check = "";
    int maxSize = 0;
    String finalPalindrone = "";
    for(int i = 0; i < word.length(); i++){
        for(int j = i+1; j <= word.length(); j++){
            
            check = word.substring(i, j);
            if(checkPalindrone(check) == true){
                if(check.length() > maxSize){
                    maxSize = check.length();
                    finalPalindrone = check;
                }
            }
        }
    }
    
    return finalPalindrone;
    
}

//helper method to check if a String is indeed a palindrone
private static boolean checkPalindrone(String word){
    String check = "";
    for(int i = word.length(); i > 0; i--){
        check += word.substring(i-1,i);
    }
    
    if(check.equals(word)){
        return true;
    }else{
        return false;
    }
}

}


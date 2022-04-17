import edu.duke.*;
import java.util.*;

public class CaesarCipher {
    public String encrypt(String input, int key){
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseAlphabet = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder encrypted = new StringBuilder(input);
        String shiftedAlphabet = alphabet.substring(key)+ alphabet.substring(0,key);
        String shiftedLowerCaseAlphabet = lowerCaseAlphabet.substring(key) + lowerCaseAlphabet.substring(0, key);
        
        for(int i = 0; i < encrypted.length(); i++) {
            //Look at the ith character of encrypted (call it currChar)
            char currChar = encrypted.charAt(i);
            int idx;
            if(Character.isLowerCase(currChar) == true){
                idx = lowerCaseAlphabet.indexOf(currChar);
            }else{
                idx = alphabet.indexOf(currChar);
            }
            //If currChar is in the alphabet
            char newChar;
            if(idx != -1){
                if(Character.isLowerCase(currChar) == true){
                newChar = shiftedLowerCaseAlphabet.charAt(idx);
            }else{
                newChar = shiftedAlphabet.charAt(idx);
            }
                encrypted.setCharAt(i, newChar);
            }
            //Otherwise: do nothing
        }
        //Your answer is the String inside of encrypted
        return encrypted.toString();
    }
    public void testCaesar() {
        int key = 15;
       // FileResource fr = new FileResource();
       //String message = fr.asString();
        String message = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
        String encrypted = encrypt(message, key);
        System.out.println(encrypted);
        String decrypted = encrypt(encrypted, 26-key);
        System.out.println(decrypted);
    }
    
    public String encryptTwoKeys(String input, int key1, int key2){
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseAlphabet = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder word = new StringBuilder(input);
        
        String shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        String shiftedAlphabetLowerCase1 = lowerCaseAlphabet.substring(key1) + lowerCaseAlphabet.substring(0, key1);
        String shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);
        String shiftedAlphabetLowerCase2 = lowerCaseAlphabet.substring(key2) + lowerCaseAlphabet.substring(0, key2);
        for(int i = 0; i < word.length(); i+=2){
            char currChar = word.charAt(i);
            int idx;
            if(Character.isLowerCase(currChar) == true){
                idx = lowerCaseAlphabet.indexOf(currChar);
            }else{
                idx = alphabet.indexOf(currChar);
            }
            
            char newChar;
            if(idx != -1){
                if(Character.isLowerCase(currChar) == true){
                newChar = shiftedAlphabetLowerCase1.charAt(idx);
                }else{
                newChar = shiftedAlphabet1.charAt(idx);
                }
                word.setCharAt(i, newChar);
            }
            
        }
        
        for(int i = 1; i < word.length(); i+=2){
            char currChar = word.charAt(i);
            int idx;
            if(Character.isLowerCase(currChar) == true){
                idx = lowerCaseAlphabet.indexOf(currChar);
            }else{
                idx = alphabet.indexOf(currChar);
            }
            
            char newChar;
            if(idx != -1){
                if(Character.isLowerCase(currChar) == true){
                newChar = shiftedAlphabetLowerCase2.charAt(idx);
                }else{
                newChar = shiftedAlphabet2.charAt(idx);
                }
                word.setCharAt(i, newChar);
            }
        }
        return word.toString();
    }
    
    public void testEncryptTwoKeys(){
        int key1 = 21;
        int key2 = 8;
        String phrase = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        String encrypted = encryptTwoKeys(phrase, key1, key2);
        System.out.println(encrypted);
        String decrypt = encryptTwoKeys(encrypted, 26-key1, 26-key2);
        System.out.println(decrypt);
    }
    
    public void bruteForce(){
        String message = "i want to decrypt this code";
        String encrypted = encrypt(message, 15);
        
        for(int key = 0; key < 26; key++){
            String check = encrypt(encrypted, 26-key);
            if(check.equals(message)){
                System.out.println(key);
            }
        }
    }
}


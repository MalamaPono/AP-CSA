
/**
 * Write a description of TwoKeysObjectOriented here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
import java.util.*;

//there are many improvements of code and test cases and bruteforce algorithms in this class, so if I ever want to add improvemnts to the regular Caeser cipher that is not focused on object oriented, or the objected oriented caesar cipher with one key, I definetly can. 
public class TwoKeysObjectOriented{
    private String alphabet;
    private String lowerCaseAlphabet;
    private String alphabetShifted1;
    private String lowerCaseAlphabetShifted1;
    private String alphabetShifted2;
    private String lowerCaseAlphabetShifted2;
    int mainKey1;
    int mainKey2;
    
    
    public TwoKeysObjectOriented(int key1, int key2){
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        lowerCaseAlphabet = "abcdefghijklmnopqrstuvwxyz";
        
        alphabetShifted1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        lowerCaseAlphabetShifted1 = lowerCaseAlphabet.substring(key1) + lowerCaseAlphabet.substring(0, key1);
        alphabetShifted2 = alphabet.substring(key2) + alphabet.substring(0, key2);
        lowerCaseAlphabetShifted2 = lowerCaseAlphabet.substring(key2) + lowerCaseAlphabet.substring(0, key2);
    
        mainKey1 = key1;
        mainKey2 = key2;
    }
    
    public String encrypt(String input){
        TwoKeysObjectOriented cs = new TwoKeysObjectOriented(mainKey1, mainKey2);
        StringBuilder message = new StringBuilder(input);
        
        if(mainKey1 != 0 && mainKey1 !=26){
        for(int i = 0; i < message.length(); i+=2){
            int idx;
            char ch = message.charAt(i);
            
            if(Character.isLowerCase(ch) == true){
                idx = lowerCaseAlphabet.indexOf(ch);
            }else{
                idx = alphabet.indexOf(ch);
            }
            
            if(idx != -1){
                char newChar;
                if(Character.isLowerCase(ch) == true){
                    newChar = lowerCaseAlphabetShifted1.charAt(idx);
                }else{
                    newChar = alphabetShifted1.charAt(idx);
                }
                message.setCharAt(i, newChar);
            }
        }
    }
        if(mainKey2 != 0 && mainKey2 != 26){
        for(int i = 1; i < message.length(); i+=2){
            char ch = message.charAt(i);
            int idx;
            
            if(Character.isLowerCase(ch) == true){
                idx = lowerCaseAlphabet.indexOf(ch);
            }else{
                idx = alphabet.indexOf(ch);
            }
            
            if(idx != -1){
                char newChar;
                if(Character.isLowerCase(ch) == true){
                    newChar = lowerCaseAlphabetShifted2.charAt(idx);
                }else{
                    newChar = alphabetShifted2.charAt(idx);
                }
                message.setCharAt(i, newChar);
            }
        }
    }
        return message.toString();
    }
        
    public String decrypt(String encrypted){
            TwoKeysObjectOriented ns = new TwoKeysObjectOriented(26-mainKey1, 26-mainKey2);
            String decrypted = ns.encrypt(encrypted);
            return decrypted;
    }
        
}
   

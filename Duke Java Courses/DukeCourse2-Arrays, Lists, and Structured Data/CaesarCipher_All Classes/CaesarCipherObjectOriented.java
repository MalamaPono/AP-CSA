
/**
 * Write a description of CaesarCipherObjectOriented here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
import java.util.*;
public class CaesarCipherObjectOriented {
    private String alphabet;
    private String lowerCaseAlphabet;
    private String alphabetShifted;
    private String lowerCaseAlphabetShifted;
    int mainKey;
    
    public CaesarCipherObjectOriented(int key){
        mainKey = key;
        lowerCaseAlphabet = "abcdefghijklmnopqrstuvwxyz";
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        alphabetShifted = alphabet.substring(key) + alphabet.substring(0, key);
        lowerCaseAlphabetShifted = lowerCaseAlphabet.substring(key) + lowerCaseAlphabet.substring(0, key);
    }
    
    public String encrypt(String input){
        CaesarCipherObjectOriented cs = new CaesarCipherObjectOriented(mainKey);
        StringBuilder message = new StringBuilder(input);
        int idx;
        char newChar;
        for(int i = 0; i < message.length(); i++){
            char ch = message.charAt(i);
           
           if(Character.isLowerCase(ch) == true){
                idx = lowerCaseAlphabet.indexOf(ch);
           }else{
                idx = alphabet.indexOf(ch);
           }
            
           if(idx != -1){
                if(Character.isLowerCase(ch) == true){
                   newChar = lowerCaseAlphabetShifted.charAt(idx);
                }else{
                   newChar = alphabetShifted.charAt(idx);
                }
                message.setCharAt(i, newChar);
           }
          
        }
        return message.toString();
    }
    
    
    public String decrypt(String encrypted){
        CaesarCipherObjectOriented cs = new CaesarCipherObjectOriented(26-mainKey);
        String decrypted = cs.encrypt(encrypted);
        return decrypted;
    }
    
}

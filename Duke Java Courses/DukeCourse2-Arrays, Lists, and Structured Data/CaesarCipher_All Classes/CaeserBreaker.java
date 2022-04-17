
/**
 * Write a description of CaeserBreaker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.ArrayList;
import java.io.*;

public class CaeserBreaker {
    
    public int[] countLetters(String message){
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int[] count = new int[26];
        
        for(int i = 0; i < message.length(); i++){
            char ch = message.charAt(i);
            ch = Character.toLowerCase(ch);
            int index = alphabet.indexOf(ch);
            if(index != -1){
                count[index] += 1;
            }
            
        }
        return count;
    }
    
    public int maxIndex(int[] count){
        int max = -1;
        int loc = -1;
        for(int i = 0; i < count.length; i++){
            if(count[i] > max){
                max = count[i];
                loc = i;
            }
        }
        return loc;
    }
    
    public String decrypt1Key(String message){
        CaesarCipher object = new CaesarCipher();
        int[] freqs = countLetters(message);
        int highestLetterIndex = maxIndex(freqs);
        int key;
        if(highestLetterIndex < 4){
            key = 26 - (4-highestLetterIndex);
        }else{
            key = highestLetterIndex-4;
        } 
        String decrypted = object.encrypt(message, 26-key);
        return decrypted;
    }
    
    public void testDecription(){
        CaesarCipher object = new CaesarCipher();
        String message = "Never gonna give you up, never gonna let you down, never gonna run around and desert you.";
        int key = 13;
        String encrypted = object.encrypt(message, key);
        System.out.println(encrypted);
        String decrypted = decrypt1Key(encrypted);
        System.out.println(decrypted);
    }
    
    public String halfOfString(String message, int start){
        String newString = "";
        for(int i = start; i < message.length(); i+=2){
            newString += message.charAt(i);
        }
        return newString;
    }
    
    public int getKey(String message){
        int[] freqs = countLetters(message);
        int highestLetterIndex = maxIndex(freqs);
        int key;
        if(highestLetterIndex < 4){
            key = 26 - (4-highestLetterIndex);
        }else{
            key = highestLetterIndex-4;
        }
        return key;
    }
    
    public String decryptTwoKeys(String encrypted){
        CaesarCipher object = new CaesarCipher();
        String first = halfOfString(encrypted, 0);
        String second = halfOfString(encrypted, 1);
        
        int key1 = getKey(first);
        System.out.println("key 1 is " + key1);
        int key2 = getKey(second);
        System.out.println("key 2 is " + key2);
        
        String decrypted1 = object.encrypt(first, 26 - key1);
        String decrypted2 = object.encrypt(second, 26 - key2);
        ArrayList<Character> combineWord = new ArrayList<Character>();
        int i;
        for(i = 0; i < decrypted2.length(); i++){
            combineWord.add(decrypted1.charAt(i));
            combineWord.add(decrypted2.charAt(i));
        }
        if(decrypted1.length() - decrypted2.length() > 0){
            combineWord.add(decrypted1.charAt(i));
        }
        
        String together = "";
        for(char x : combineWord){
            together += x;
        }
        return together;
    }
    
    public void run(){
        /*
        CaesarCipher object = new CaesarCipher();
        FileResource fr = new FileResource("mysteryTwoKeysPractice.txt");
        String message = fr.asString();
        System.out.println(decryptTwoKeys(message));
        */
        CaesarCipher object = new CaesarCipher();
        String original = "leeeeeet me go AAAAAA to sleep pleaseee";
        String message = object.encryptTwoKeys(original, 13, 10);
        System.out.println(message);
        System.out.println(decryptTwoKeys(message));
        
        //System.out.println(decryptTwoKeys("Akag tjw Xibhr awoa aoee xakex znxag xwko"));
        
    }
    
    public String DecryptGivenStringAndKeys(String message, int key1, int key2){
        CaesarCipher object = new CaesarCipher();
        String first = halfOfString(message, 0);
        String second = halfOfString(message, 1);
        
        String decrypted1 = object.encrypt(first, 26 - key1);
        String decrypted2 = object.encrypt(second, 26 - key2);
        ArrayList<Character> combineWord = new ArrayList<Character>();
        int i;
        for(i = 0; i < decrypted2.length(); i++){
            combineWord.add(decrypted1.charAt(i));
            combineWord.add(decrypted2.charAt(i));
        }
        if(decrypted1.length() - decrypted2.length() > 0){
            combineWord.add(decrypted1.charAt(i));
        }
        
        String together = "";
        for(char x : combineWord){
            together += x;
        }
        return together;
        
    }
    
    public void testDecryptGivenStringAndKeys(){
        System.out.println(DecryptGivenStringAndKeys("Akag tjw Xibhr awoa aoee xakex znxag xwko", 22, 19));
    }
}

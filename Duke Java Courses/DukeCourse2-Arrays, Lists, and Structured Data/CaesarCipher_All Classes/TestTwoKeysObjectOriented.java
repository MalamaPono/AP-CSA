
/**
 * Write a description of TestTwoKeysObjectOriented here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class TestTwoKeysObjectOriented {
    public String halfOfString(String message, int start){
        String newString = "";
        for(int i = start; i < message.length(); i+=2){
            newString += message.charAt(i);
        }
        return newString;
    }
    
    public int[] countLetters(String message){
        int[] count = new int[26];
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        char ch;
        int idx;
        for(int i = 0; i < message.length(); i++){
            ch = Character.toLowerCase(message.charAt(i));
            idx = alphabet.indexOf(ch);
            if(idx != -1){
                count[idx]+=1;
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
    
    public void simpleTests(){
       // FileResource fr = new FileResource();
      //  String message = fr.asString();
      String message = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        TwoKeysObjectOriented cs = new TwoKeysObjectOriented(21, 8);
        String encrypted = cs.encrypt(message);
        System.out.println(encrypted);
        String decrypted = breakCaeserCipher(encrypted);
        System.out.println(decrypted);
    }
    
    public int getKey(String message){
        int[] count = countLetters(message);
        int max = maxIndex(count);
        int key;
        
        if(max < 4){
            key = 26-(4-max);
            
        }
        key = max-4;
        
        return key;
    }
    
    public String breakCaeserCipher(String message){
        String first = halfOfString(message, 0);
        String second = halfOfString(message, 1);
        
        int key1 = getKey(first);
        System.out.println("key1 is " + key1);
        int key2 = getKey(second);
        System.out.println("key2 is " + key2);
        TwoKeysObjectOriented cs = new TwoKeysObjectOriented(key1, key2);
        String decrypted = cs.decrypt(message);
        return decrypted;
    }
    
    public void countE(){
        FileResource fr = new FileResource();
        String message = fr.asString();
        int e = 0;
        int a = 0;
        for(int i = 0; i < message.length(); i++){
            if(message.charAt(i) == 'e'){
                e++;
            }
            if(message.charAt(i) == 'a'){
                a++;
            }
        }
        System.out.println("amount of e's: " + e);
        System.out.println("amount of a's: " + a);
        
    }
    
    public void bruteForceTwoKeys(){ //this is when e frequency isn't highest so need to adapt. Try the e frequency first becuase that is cooler and doesn't need to sift through every key. But this algorithm will work for every single string.
        TwoKeysObjectOriented cs = new TwoKeysObjectOriented(18,25);
        String original = "This algorithm works no matter what";
        String encrypted = cs.encrypt(original);
        System.out.println(encrypted);
        int i = 0;
        int k = 0;
        a:for(i = 0; i <26; i++){
            for(k = 0; k <26; k++){
                TwoKeysObjectOriented x = new TwoKeysObjectOriented(i, k);
                if(x.encrypt(original).equals(encrypted)){
                    System.out.println("first key is " + i);
                    System.out.println("second key is " + k);
                    break a;
                }
            }
        }
        System.out.println("now that I have found the keys let me decrypt the message");
        TwoKeysObjectOriented name = new TwoKeysObjectOriented(i,k);
        String decrypted = name.decrypt(encrypted);
        System.out.println(decrypted);
    }
        
}

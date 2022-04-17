
/**
 * Write a description of TestCaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class TestCaesarCipher {
    
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
    
    public void simpleTest(){
      //FileResource fr = new FileResource();
      //String message = fr.asString();
      String message  = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
      CaesarCipherObjectOriented cs = new CaesarCipherObjectOriented(18);
      String encrypted = cs.encrypt(message);
      System.out.println(encrypted);
        
        String decrypted = breakCaesarCipher(encrypted);
        System.out.println(decrypted);
        
    }
    
    public String breakCaesarCipher(String input){
        int[] count = countLetters(input);
        int max = maxIndex(count);
        
        int key;
        if(max < 4){
            key = 26 - (4-max);
        }else{
            key = 26 - (26-(max-4));
        }
        
        CaesarCipherObjectOriented cs = new CaesarCipherObjectOriented(key);
        String decrypted = cs.decrypt(input);
        return decrypted;
        
    }
    
}

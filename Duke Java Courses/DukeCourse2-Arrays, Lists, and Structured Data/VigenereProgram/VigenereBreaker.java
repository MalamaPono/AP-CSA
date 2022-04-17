import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder words = new StringBuilder(message);
        String str = "";
        for(int i = whichSlice; i < words.length(); i+=totalSlices){
            str += message.charAt(i);
        }
        return str;
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker cs = new CaesarCracker();
        for(int i = 0; i < klength; i++){
            String x = sliceString(encrypted, i, klength);
            int thisKey = cs.getKey(x);
            key[i] = thisKey;
        }
        return key;
    }
    
    public HashSet<String> readDictionary(FileResource fr){
        HashSet<String> words = new HashSet<String>();
        for(String line : fr.lines()){
            line = line.toLowerCase();
            words.add(line);
        }
        return words;
    }
    
    public int countWords(String message,HashSet<String> dictionary){
        int validWords = 0;
        String[] split = message.split("\\W+");
        ArrayList<String> words = new ArrayList<String>();
        for(int i = 0; i < split.length; i++){
            String check = split[i];
            check = check.toLowerCase();
            if(dictionary.contains(check) && words.indexOf(check) ==-1){
                validWords++;
                words.add(check);
            }
        }
        return validWords;
    }
    
    public String breakForLanguage(String encrypted, HashSet<String> dictionary){
        int max = 0;
        String decrypted = "";
        int keyLength = 0;
        for(int i = 57; i < 58; i++){
            int[] key = tryKeyLength(encrypted, i, mostCommonCharIn(dictionary));
            VigenereCipher vs = new VigenereCipher(key);
            String check = vs.decrypt(encrypted);
            int numWords = countWords(check, dictionary);
            if(numWords > max){
                max = numWords;
                decrypted = check;
                keyLength = i;
            }
        }
        System.out.println(max);
        return decrypted;
    }
    
    public void breakForAllLangs(String encrypted, HashMap<String,HashSet<String>> languages){
        int maxWords = 0;
        String returnString = "";
        String language = "";
        for(String a : languages.keySet()){
            String decrypted = breakForLanguage(encrypted, languages.get(a));
            int tempWords = countWords(decrypted, languages.get(a));
            if(tempWords > maxWords){
                maxWords = tempWords;
                returnString = decrypted;
                language = a;
            }
            
        }
        System.out.println("The language is " + language + " and the decrypted message is " + returnString);
    }
    
    public char mostCommonCharIn(HashSet<String> dic){
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        HashMap<Character,Integer> map = new HashMap<Character,Integer>();
        for(String a : dic){
            char[] arr = a.toCharArray();
            for(char c : arr){
                if(!map.containsKey(c)){
                    map.put(c,1);
                }else{
                    map.put(c,map.get(c)+1);
                }
            }
        }
        int max = 0;
        char ch = 'e';
        for(Character c : map.keySet()){
            if(map.get(c) > max){
                max = map.get(c);
                ch = c;
            }
        }
        return ch;
    }

    public void breakVigenere(){
      FileResource fr = new FileResource();
      String message = fr.asString();
      FileResource dic = new FileResource("english3.txt");
      HashSet<String> dictionary = readDictionary(dic);
      String decrypted = breakForLanguage(message, dictionary);
      //System.out.println(decrypted); 
        
      /*
      FileResource fr = new FileResource();
      String message = fr.asString();
      HashMap<String,HashSet<String>> map = new HashMap<String,HashSet<String>>();
      String[] languages = {"Danish", "Dutch", "English", "French", "German", "Italian", "Portuguese", "Spanish"};
      for(String a : languages){
          FileResource dic = new FileResource(a);
          map.put(a,readDictionary(dic));
      }
      breakForAllLangs(message, map);
      
      
       * if you don't know key length
      FileResource fr = new FileResource();
      String message = fr.asString();
      FileResource dic = new FileResource("caesar.txt");
      HashSet<String> dictionary = readDictionary(dic);
      String decrypted = breakForLanguage(message, dictionary);
      System.out.println(decrypted.substring(0,40));
      */
     
      /*
         * if you know key length
        int[] key = tryKeyLength(message, 4, 'e');
        System.out.println(Arrays.toString(key));
        VigenereCipher vs = new VigenereCipher(key);
        String decrypted = vs.decrypt(message);
        System.out.println(decrypted);
      */
    }
    
}

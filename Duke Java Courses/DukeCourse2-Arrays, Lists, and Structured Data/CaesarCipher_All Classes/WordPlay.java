
/**
 * Write a description of WordPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class WordPlay {
    
    public boolean isVowel(char ch){
        String vowels = "aeiou";
        String upperVowels = "AEIOU";
        
        if(vowels.indexOf(ch) != -1 || upperVowels.indexOf(ch) != -1){
            return true;
        }
        return false;
    }
    
    public void testReplaceVowels(){
        String phrase = "my cat ate the dog";
        char ch = '*';
        System.out.println(replaceVowels(phrase, ch));
    }
    
    public String replaceVowels(String phrase, char ch){
        StringBuilder tempPhrase = new StringBuilder(phrase);
        for(int i = 0; i < tempPhrase.length(); i++){
            if(isVowel(tempPhrase.charAt(i)) == true){ //char is a primitive value so we use the comparison ==. Whereas string is its own object and class so we use .equals().
                tempPhrase.setCharAt(i, ch);
            }
        }
        return tempPhrase.toString();
    }
    
    public String emphasize(String phrase, char ch){
        char lowerCase = Character.toLowerCase(ch);
        char upperCase = Character.toUpperCase(ch);
        
        StringBuilder tempPhrase = new StringBuilder(phrase);
        for(int i = 0; i < tempPhrase.length(); i++){
            if((tempPhrase.charAt(i) == lowerCase || tempPhrase.charAt(i) == upperCase) && i % 2 == 0){
                tempPhrase.setCharAt(i, '*');
            }else if((tempPhrase.charAt(i) == lowerCase || tempPhrase.charAt(i) == upperCase) && i % 2 == 1){
                tempPhrase.setCharAt(i, '+');
            }
        }
        return tempPhrase.toString();
    }
    
    public void testEmphasize(){
        System.out.println(emphasize("Mary Bella Abracadabra", 'a'));
    }
}

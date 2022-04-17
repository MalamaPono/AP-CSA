
/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;

public class Tester {
    
    public void testGetFollows(){
        MarkovOne markov = new MarkovOne();
        String s = "this is a test yes this is a test.";
        markov.setTraining(s);
        ArrayList<String> test = markov.getFollows(".");
        for(String a : test){
            System.out.print(a);
        }
        System.out.println(test.size());
    }
    
    public void testGetFollowsWithFile(){
        MarkovOne markov = new MarkovOne();
        FileResource fr = new FileResource();
        String s = fr.asString();
        s = s.replace('\n', ' '); //replacing each new line in file with a space instead. This will make long strings with spaces between what were new lines.
        markov.setTraining(s);
        ArrayList<String> test = markov.getFollows("th");
        for(String a : test){
            System.out.println(a);
        }
        System.out.println(test.size());
    }

}

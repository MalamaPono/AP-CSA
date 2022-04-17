import java.util.*;
import edu.duke.*;

public class PhraseFilter implements Filter{
    
    String where;
    String phrase;
    private String name = "Phrase";
    
    public PhraseFilter(String where, String phrase){
        this.where = where;
        this.phrase = phrase;
    }
    
    public boolean satisfies(QuakeEntry qe){
        String title = qe.getInfo();
        if(where.equals("start")){
            if(title.substring(0, phrase.length()).equals(phrase)){
                return true;
            }
        }
        if(where.equals("end")){
            if(title.substring(title.length() - phrase.length()).equals(phrase)){
                return true;
            }
        }
        if(where.equals("any")){
            if(title.indexOf(phrase) != -1){
                return true;
            }
        }
        return false;
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String x){
        name = x;
    }
    
    
}
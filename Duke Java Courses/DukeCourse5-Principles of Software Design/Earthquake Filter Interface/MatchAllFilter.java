
/**
 * Write a description of MatchAllFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;

public class MatchAllFilter implements Filter{
    
    private ArrayList<Filter> fills;
    
    public MatchAllFilter(){
        fills = new ArrayList<Filter>();
    }
    
    public void addFilter(Filter f){
        fills.add(f);
    }
    
    public boolean satisfies(QuakeEntry qe){
        for(Filter f : fills){
            if(!f.satisfies(qe)){
                return false;
            }
        }
        return true;
    }
    
    public String getName(){
        String name = "";
        for(Filter f : fills){
            name += f.getName() + " ";
            
        }
        return name;
    }
    
}

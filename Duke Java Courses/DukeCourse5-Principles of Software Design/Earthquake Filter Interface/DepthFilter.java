
/**
 * Write a description of DepthFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;

public class DepthFilter implements Filter{
    
    private double minDepth;
    private double maxDepth;
    private String name = "Depth";
    
    public DepthFilter(double min, double max){
        minDepth = min;
        maxDepth = max;
    }
    
    public boolean satisfies(QuakeEntry qe){
        if(qe.getDepth() >= minDepth && qe.getDepth() <= maxDepth){
            return true;
        }else{
            return false;
        }
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String x){
        name = x;
    }
    
}

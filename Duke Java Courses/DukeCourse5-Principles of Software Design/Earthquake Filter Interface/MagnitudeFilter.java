
/**
 * Write a description of MagnitudeFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;

public class MagnitudeFilter implements Filter {
    
    private double minMag;
    private double maxMag;
    private String name = "Magnitude";
    
    public MagnitudeFilter(double min, double max){
        minMag = min;
        maxMag = max;
    }
    
    public boolean satisfies(QuakeEntry qe){
        if(qe.getMagnitude() >= minMag && qe.getMagnitude() <= maxMag){
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

import java.util.*;
import edu.duke.*;

public class DistanceFilter implements Filter{
    
    private Location location;
    private double maxDist;
    private String name = "Distance";
    
    public DistanceFilter(Location location, double max){
        this.location = location;
        maxDist = max;
    }
    
    public boolean satisfies(QuakeEntry qe){
        if(location.distanceTo(qe.getLocation()) < maxDist){
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

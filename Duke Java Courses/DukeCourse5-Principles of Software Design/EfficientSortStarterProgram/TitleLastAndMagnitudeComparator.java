
/**
 * Write a description of TitleLastAndMagnitudeComparator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry>{
    
    public int compare(QuakeEntry first, QuakeEntry second){
        int start1 = first.getInfo().lastIndexOf(" ") + 1;
        int start2 = second.getInfo().lastIndexOf(" ") + 1;
        String lastWord1 = first.getInfo().substring(start1);
        String lastWord2 = second.getInfo().substring(start2);
        
        if(lastWord1.compareTo(lastWord2) < 0){
            return -1;
        }
        if(lastWord1.compareTo(lastWord2) > 0){
            return 1;
        }
        if(lastWord1.compareTo(lastWord2) == 0){
            double mag1 = first.getMagnitude(); 
            double mag2 = second.getMagnitude();
            
            if(mag1 < mag2){
                return -1;
            }
            if(mag1 > mag2){
                return 1;
            }
            return 0;
            /*
            another way to do it. simpler way of using already built in code instead of manually doing it.
            return Double.compare(mag1, mag2);
            */
        }
        return 0; //never reached
    }

}


/**
 * Write a description of TitleAndDepthComparator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;

public class TitleAndDepthComparator implements Comparator<QuakeEntry>{
    
    public int compare(QuakeEntry q1, QuakeEntry q2){
        String q1Title = q1.getInfo();
        String q2Title = q2.getInfo();
        if(q1Title.compareTo(q2Title) < 0){
            return -1;
        }
        if(q1Title.compareTo(q2Title) > 0){
            return 1;
        }
        if(q1Title.compareTo(q2Title) == 0){
            double q1Depth = q1.getDepth();
            double q2Depth = q2.getDepth();
            
            if(q1Depth < q2Depth){
                return -1;
            }
            if(q1Depth > q2Depth){
                return 1;
            }
            return 0;
            /*
            another way to do it 
            return Double.compare(q1Depth, q2Depth);
            */
        }
        return 0; //never reached
    }
    
}

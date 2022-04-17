
/**
 * Write a description of LargestQuakes here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;

public class LargestQuakes {
    
    public void findLargestQuakes(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> original = parser.read(source);
        System.out.println("read data for " + original.size() + " quakes");
        int howMany = 5;
        ArrayList<QuakeEntry> toPrint = getLargests(original, howMany);
        
        if(toPrint == null){
            System.out.println("please return a valid amount of quakes. The amount of quakes you want to find must not exceed the amount of quakes actually in the file");
        }else{
        for(QuakeEntry qe : toPrint){
            System.out.println(qe);
        }
    }
    }
    
    public int getLargestIndex(ArrayList<QuakeEntry> data){
        int maxIndex = 0;
        for(int k = 1; k < data.size(); k++){
            double tempMagnitude = data.get(k).getMagnitude();
            double maxMagnitude = data.get(maxIndex).getMagnitude();
            if(tempMagnitude > maxMagnitude){
                maxMagnitude = tempMagnitude;
                maxIndex = k;
            }
        }
        return maxIndex;
    }
    
    public ArrayList<QuakeEntry> getLargests(ArrayList<QuakeEntry> data, int howMany){
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(data);
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        if(howMany > copy.size()){
            return null;
        }
        
        for(int i = 0; i < howMany; i++){
        int maxIndex = getLargestIndex(copy);
        answer.add(copy.get(maxIndex));
        copy.remove(maxIndex);
    }
    return answer;
    }
}

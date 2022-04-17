
/**
 * Write a description of ClosestQuakes here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;

public class ClosestQuakes {
    public void findClosesQuakes(){ //this method actually returns the number quakes closest to you in order depening on how many you want to find. For example top10 or top 5, excertera. 
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> original = parser.read(source);
        System.out.println("reading data from " + original.size() + " entries");
        
        Location jakarta = new Location(-6.211, 106.845);
        int howMany = 3;
        
        ArrayList<QuakeEntry> closestEntries = getClosest(original, jakarta, howMany);
       
        //printing out quakes with distance from your location and other formats different from original printing or CSV
        for(int k = 0; k < closestEntries.size(); k++){
            QuakeEntry toPrint = closestEntries.get(k);
            double distanceInMeters = jakarta.distanceTo(toPrint.getLocation());
            System.out.printf("%4.2f\t %s\n", distanceInMeters/1000, toPrint);
        }
        System.out.println("number found " + closestEntries.size());
    } 
    
    public ArrayList<QuakeEntry> getClosest(ArrayList<QuakeEntry> original, Location current, int howMany){
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(original);
        ArrayList<QuakeEntry> returnList = new ArrayList<QuakeEntry>();
        int minIndex = 0;
        for(int i = 0; i < howMany; i++){
            for(int k = 1; k < copy.size(); k++){
                QuakeEntry qe = copy.get(k);
                Location tempLoc = qe.getLocation();
                Location minLoc = copy.get(minIndex).getLocation();
                if(current.distanceTo(tempLoc) < current.distanceTo(minLoc)){
                    minIndex = k;
                }
            }
            returnList.add(copy.get(minIndex));
            copy.remove(minIndex);
        }
        return returnList;
    }
}

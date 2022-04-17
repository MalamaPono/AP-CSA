
import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    
    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData, double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        //TODO
        for (QuakeEntry qe : quakeData) {
            if (qe.getMagnitude() > magMin) {
                answer.add(qe);
            }
        }
        return answer;              
    }
    
            
    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                              qe.getLocation().getLatitude(),
                              qe.getLocation().getLongitude(),
                              qe.getMagnitude(),
                              qe.getInfo());
        }
        
    }
    
    public void bigQuakes() {
    EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");
        
        ArrayList<QuakeEntry> listBig = filterByMagnitude(list, 5.0);
        for (QuakeEntry qe : listBig) {
           System.out.println(qe); 
        }
    }
    
    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }
    
    public void closeToMe() { //this methods returns all the quakes within a certain distance from you (which you can pass in that location where it says city)
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("# quakes read: " + list.size());
        
        //Durham, NC
        //Location city = new Location(35.988, -78.907);
        //Bridgeport, CA
        Location city = new Location(38.17, -118.82);
        ArrayList<QuakeEntry> close = filterByDistanceFrom(list, 1000000, city);
        if(close.size() == 0){
            System.out.println("no entries of earthquakes are found closer than the distance you inputed");
        }
        for (int k=0; k< close.size(); k++) {
            QuakeEntry entry = close.get(k);
            double distanceInMeters = city.distanceTo(entry.getLocation());
            System.out.println(distanceInMeters/1000 + " " + entry.getInfo());
        }

    }
    
    //filters all data in ArrayList and adds it to new ArrayList that contains all quakes closer than a certian distance. Can also modify this to get all quakes further than a certain distance.
    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData, double distMax, Location from) {      
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        for (QuakeEntry qe : quakeData) {
            if (qe.getLocation().distanceTo(from) < distMax) {
                answer.add(qe);
            }
        }
        return answer;
    }
    
    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData, double minDepth, double maxDepth){
        ArrayList<QuakeEntry> returnList = new ArrayList<QuakeEntry>();
        for(int i = 0; i < quakeData.size(); i++){
            double depth = quakeData.get(i).getDepth();
            if(depth > minDepth && depth < maxDepth){
                returnList.add(quakeData.get(i));
            }
        }
        return returnList;
    }
    
    public void QuakesOfCertainDepth(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> original = parser.read(source);
        System.out.println("read data for " + original.size() + " quakes");
        double minDepth = -10000.0;
        double maxDepth = -8000.0;
        System.out.println("Find quakes between " + minDepth + " and " + maxDepth);
        ArrayList<QuakeEntry> answer = filterByDepth(original, minDepth, maxDepth);
        for(QuakeEntry qe : answer){
            System.out.println(qe);
        }
        
        System.out.println("Found " + answer.size() + " that meet this criteria");
    }
    
    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData, String where, String phrase){
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData){
            String title = qe.getInfo();
            if(where.equals("any")){
                if(title.indexOf(phrase)!=-1){
                    answer.add(qe);
                }
            }
            if(where.equals("start")){
                if(title.substring(0, phrase.length()).equals(phrase)){
                    answer.add(qe);
                }
            }
            if(where.equals("end")){
                if(title.substring(title.length() - phrase.length()).equals(phrase)){
                    answer.add(qe);
                }
            }
        }
        return answer;
    }
    
    public void quakesByPhrase(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> original = parser.read(source);
        System.out.println("read data for " + original.size() + " quakes");
        String where = "any";
        String phrase = "Creek";
        ArrayList<QuakeEntry> answer = filterByPhrase(original, where, phrase);
        
        for(QuakeEntry qe : answer){
            System.out.println(qe);
        }
        System.out.println("Found a total of " + answer.size() + " quakes that match " + phrase + " at " + where);
    }
}

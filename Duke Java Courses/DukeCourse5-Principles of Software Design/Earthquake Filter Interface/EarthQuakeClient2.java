import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        
        return answer;
    } 

    public void quakesWithFilter() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");

        /*
        Filter f = new MinMagFilter(4.0); 
        ArrayList<QuakeEntry> m7  = filter(list, f); 
        for(QuakeEntry qe: m7) { 
            System.out.println(qe);
        } 
        */
       
       /*
       Filter f = new MagnitudeFilter(4.0,5.0);
       ArrayList<QuakeEntry> mag = filter(list, f);
       Filter p = new DepthFilter(-35000.0,-12000.0);
       ArrayList<QuakeEntry> answer = filter(mag, p);
       */
       
       Filter f = new MagnitudeFilter(4.0, 5.0);
       ArrayList<QuakeEntry> distance = filter(list, f);
       Filter p = new DepthFilter(-35000.0,-12000.0);
       ArrayList<QuakeEntry> answer = filter(distance, p);
      
       for(QuakeEntry qe : answer){
           System.out.println(qe);
       }
      System.out.println(answer.size() + " earthquakes met that criteria");
       
    }
    
    public void testMatchAllFilter(){
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        MatchAllFilter maf = new MatchAllFilter();
        Location tuslaOklahoma = new Location(36.1314, -95.9372);
        Filter mag = new MagnitudeFilter(0.0,3.0);
        Filter dep = new DistanceFilter(tuslaOklahoma, 10000000);
        Filter str = new PhraseFilter("any", "Ca");
        maf.addFilter(mag);
        maf.addFilter(dep);
        maf.addFilter(str);
        
        ArrayList<QuakeEntry> matchAll = filter(list, maf); //this maf can be passed in here becuase it is a MatchAllFilter ArrayList that is a type/class 
        for(QuakeEntry qe : matchAll){
            System.out.println(qe);
        }
        System.out.println(matchAll.size() + " earthquakes met this criteria");
        System.out.println("Filters used are " + maf.getName());
    }
    
    public void tetMatchAllFilter2(){
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        MatchAllFilter maf = new MatchAllFilter();
        Location oklahoma = new Location(36.1314,-95.9372);
        Filter mag = new MagnitudeFilter(0.0,3.0);
        Filter dep = new DistanceFilter(oklahoma, 10000000);
        Filter str = new PhraseFilter("any", "Ca");
        maf.addFilter(mag);
        maf.addFilter(dep);
        maf.addFilter(str);
        
        ArrayList<QuakeEntry> matchAll = filter(list, maf); //this maf can be passed in here becuase it is a MatchAllFilter ArrayList that is a type/class 
        for(QuakeEntry qe : matchAll){
            System.out.println(qe);
        }
        System.out.println(matchAll.size() + " earthquakes met this criteria");
    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
    }

    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }
    }

}

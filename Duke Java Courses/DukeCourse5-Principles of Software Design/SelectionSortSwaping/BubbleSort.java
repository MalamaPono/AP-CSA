import java.util.*;
import edu.duke.*;

public class BubbleSort {
    
    public void onePassBubbleSortMagnitude(ArrayList<QuakeEntry> data, int numSorted){
        for(int i = 0; i < data.size()-1; i++){
            if(data.get(i+1).getMagnitude() < data.get(i).getMagnitude()){
                QuakeEntry first = data.get(i);
                QuakeEntry second = data.get(i+1);
                data.set(i, second);
                data.set(i+1, first);
            }
        }
    }
    
    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> data){
        int reps = data.size()-1;
        for(int i = 0; i < reps; i++){
            onePassBubbleSortMagnitude(data, i);
        }
    }
    
    public void testBubbleSort(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/earthQuakeDataDec6sample1.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("*****");
        sortByMagnitudeWithBubbleSortWithCheck(list);
        for(QuakeEntry qe: list) {
            System.out.println(qe);
        }
    }
    
    public boolean checkInSortedOrder(ArrayList<QuakeEntry> data){
        for(int i = 0; i < data.size()-1; i++){
            if(data.get(i).getMagnitude() > data.get(i+1).getMagnitude()){
                return false;
            }
        }
        return true;
    }
    
    public void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> data){
        int passes = 0; 
        int reps = data.size()-1;
        a:for(int i = 0; i < reps; i++){
            passes++;
            onePassBubbleSortMagnitude(data, i);
            if(checkInSortedOrder(data) == true){
                break a;
            }
        }
        System.out.println("A total of " + passes + " cycles were needed to bubblesort this data");
    }
    
}

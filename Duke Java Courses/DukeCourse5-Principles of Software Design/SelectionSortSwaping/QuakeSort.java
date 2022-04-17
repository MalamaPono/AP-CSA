import java.util.*;

public class QuakeSort {
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i = from +1; i < quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
        //count from 0 to < in.size()
        for(int i = 0; i < in.size(); i++) {
            /* find the index of the smallest quake*/
            int minIdx = getSmallestMagnitude(in, i);
            /* swap the ith quake with the minIdxth quake */
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i, qmin);
            in.set(minIdx, qi);
        }
    }
    
    public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in){
        int passes = 0;
        if(checkInSortedOrder(in) == false){
            for(int i = 0; i < in.size(); i++) {
            passes++;
            /* find the index of the smallest quake*/
            int minIdx = getSmallestMagnitude(in, i);
            /* swap the ith quake with the minIdxth quake */
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i, qmin);
            in.set(minIdx, qi);
            if(checkInSortedOrder(in) == true){
                break;
            }
        }
        }
        System.out.println("A total of " + passes + " cycles were needed to selectionSort this data");
    }
    /* tester method to use in BlueJ */
    public void testSort(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/earthQuakeDataWeekDec6sample2.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("*******");
        sortByMagnitudeWithCheck(list);
        
        /*
        for(QuakeEntry qe : list) {
            System.out.println(qe);
        }
        
        
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        sortByLargestDepth(list);
        System.out.println("*****");
        for(QuakeEntry qe: list) {
            System.out.println(qe);
        }
        */
    }
    
    public int getLargestDepth(ArrayList<QuakeEntry> quakeData, int startPos){
        int maxIndex = startPos;
        for(int i = startPos+1; i < quakeData.size(); i++){
            if(quakeData.get(i).getDepth() > quakeData.get(maxIndex).getDepth()){
                maxIndex = i;
            }
        }
        return maxIndex;
    }
    
    public void sortByLargestDepth(ArrayList<QuakeEntry> quakeData){
        for(int i = 0; i < 70; i++){
            int maxIndex = getLargestDepth(quakeData, i);
            QuakeEntry curDepth = quakeData.get(i);
            QuakeEntry maxDepth = quakeData.get(maxIndex);
            quakeData.set(i, maxDepth);
            quakeData.set(maxIndex, curDepth);
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
    
}

import edu.duke.*;
import java.util.*;

public class WordFrequenciesMap {
    
    public void countWords(String filename){
        FileResource fr = new FileResource(filename);
        ArrayList<String> words = new ArrayList<String>();
        ArrayList<Integer> counters = new ArrayList<Integer>();
        for(String w : fr.words()){
            w = w.toLowerCase();
            int index = words.indexOf(w);
            if (index == -1){
                words.add(w);
                counters.add(1);
            }
            else {
                int value = counters.get(index);
                counters.set(index, value + 1);
            }
        }
        int total = 0;
        for(int k=0; k < words.size(); k++){
            if (counters.get(k) > 500){
                System.out.println(counters.get(k)+"\t"+words.get(k));
            }
            total += counters.get(k);
        }
        System.out.println("total count: "+total+" different = "+words.size());
    }
    
    public String findMax(HashMap<String,Integer> map){
        int max = 0;
        String find = "";
        for(String s : map.keySet()){
            int temp = map.get(s);
            if(temp > max){
                max = temp;
                find = s;
            }
        }
        return find;
    }
    
    public void countWordsMap(String filename){
        FileResource fr = new FileResource(filename);
        HashMap<String,Integer> map = new HashMap<String,Integer>();

        for(String w : fr.words()){
            w = w.toLowerCase();
            if (!map.containsKey(w)){
                map.put(w,1);
            }
            else {
                map.put(w,map.get(w)+1);
            }
        }
        int total = 0;
        for(String w : map.keySet()){
            int occurences = map.get(w);
            System.out.println(occurences+"\t"+w);
            total += occurences;
        }
	System.out.println("total count: "+total+" different = "+map.size());
    }
    public void tester(){
		String filename = "errors.txt";
		double start = System.currentTimeMillis();
		countWords(filename);
		double end = System.currentTimeMillis();
		double time = (end-start)/1000;
		System.out.println("time = "+time);
		start = System.currentTimeMillis();
		countWordsMap(filename);
		end = System.currentTimeMillis();
		time = (end-start)/1000;
		System.out.println("time = "+time);
	}
}

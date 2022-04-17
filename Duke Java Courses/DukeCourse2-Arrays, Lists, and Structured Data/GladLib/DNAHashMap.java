
/**
 * Write a description of DNAHashMap here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;
import java.io.*;

public class DNAHashMap {
    
    private HashMap<String,Integer> map;
    
    public DNAHashMap(){
        map = new HashMap<String,Integer>();
    }
    
    public void buildCodonMap(int start, String dna){
        map.clear();
        for(int i = start; i < dna.length()-2; i+=3){
            String str = dna.substring(i, i+3);
            if(!map.containsKey(str)){
                char ch = str.charAt(2);
                if(Character.isLetter(ch) == true){
                    map.put(str,1);
                }
            }else{
                map.put(str,map.get(str)+1);
            }
        }
    }
    
    public String getMostCommonCodon(){
        int max = 0;
        String codon = "";
        int temp;
        for(String s : map.keySet()){
            temp = map.get(s);
            if(temp > max){
                max = temp;
                codon = s;
            }
        }
        return codon;
    }
    
    public void printCodonCounts(int start, int end){
        for(String s : map.keySet()){
            if(map.get(s) >= start && map.get(s) <= end){
                System.out.println(s + " appears " + map.get(s) + " times");
            }
        }
    }
    
    public void tester(){
        FileResource fr = new FileResource();
        String dna = fr.asString().toUpperCase();
        //dna = dna.trim(); //getting rid of one extra space at end of the file which happens when converting file to String due to convention of textfiles.
        DNAHashMap map1 = new DNAHashMap(); //map1 refers to the object name and .map refers to the actual map created in this object of the class
        map1.buildCodonMap(0, dna);
        DNAHashMap map2 = new DNAHashMap();
        map2.buildCodonMap(1, dna);
        DNAHashMap map3 = new DNAHashMap();
        map3.buildCodonMap(2, dna);
        
        System.out.println("Reading frame starts with 0 results in " + map1.map.size() + " unique codons");
        System.out.println("The most common codon is " + map1.getMostCommonCodon() + " with count " + map1.map.get(map1.getMostCommonCodon()));
        System.out.println("counts of codons between 1 and 5 inclusive are:");
        System.out.println();
        map1.printCodonCounts(1, 5);
        
        System.out.println("Reading frame starts with 1 results in " + map2.map.size() + " unique codons");
        System.out.println("The most common codon is " + map2.getMostCommonCodon() + " with count " + map2.map.get(map2.getMostCommonCodon()));
        System.out.println("counts of codons between 1 and 5 inclusive are:");
        System.out.println();
        map2.printCodonCounts(1, 5);
        
        System.out.println("Reading frame starts with 2 results in " + map3.map.size() + " unique codons");
        System.out.println("The most common codon is " + map3.getMostCommonCodon() + " with count " + map3.map.get(map3.getMostCommonCodon()));
        System.out.println("counts of codons between 1 and 5 inclusive are:");
        System.out.println();
        map3.printCodonCounts(1, 5);
        
    }
}

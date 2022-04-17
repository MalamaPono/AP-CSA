
/**
 * Write a description of GladLibHashMap here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;

public class GladLibHashMap{
    
    HashMap<String,ArrayList<String>> myMap;
    ArrayList<String> usedWords;
    ArrayList<String> categoriesUsed;
    
    private Random myRandom;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "datalong";
    
    public GladLibHashMap(){
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
    }
    
    public GladLibHashMap(String source){
        initializeFromSource(source);
        myRandom = new Random();
    }
    
    private void initializeFromSource(String source) {
        String[] labels = {"country", "noun", "verb", "adjective", "fruit", "timeframe",
            "color", "animal", "name"};
        usedWords = new ArrayList<String>();
        categoriesUsed = new ArrayList<String>();
        myMap = new HashMap<String,ArrayList<String>>();
        
        for(String s : labels){
            ArrayList<String> list = readIt(source+"/"+s+".txt");
            myMap.put(s, list);
        }
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label){
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }else{
            if(categoriesUsed.indexOf(label) == -1){
                categoriesUsed.add(label);
            }
           
        }
        return randomFrom(myMap.get(label));
    }
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));
        while(usedWords.indexOf(sub) != -1){
            sub = getSubstitute(w.substring(first+1,last));
        }
        if(usedWords.indexOf(sub) == -1){
            usedWords.add(sub);
        }
        return prefix+sub+suffix;
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    
    public void makeStory(){
        System.out.println("\n");
        usedWords.clear();
        String story = fromTemplate("datalong/madtemplate3.txt");
        printOut(story, 60);
        System.out.println();
        System.out.println("The amount of words that were replaced were " + usedWords.size());
        System.out.println("The total amount of words in all the categories that may not have been chosen in this template, but may be used for different stories and templates are : " + totalWordsInMap());
        System.out.println("The total amount of words that could have been picked specifically for this story and template are : " + totalWordsConsidered());
    }

    public int totalWordsInMap(){
        int total = 0;
        for(String s : myMap.keySet()){
            total += myMap.get(s).size();
        }
        return total;
    }
    
    public int totalWordsConsidered(){
        int total = 0;
        for(String s : categoriesUsed){
            total += myMap.get(s).size();
        }
        return total;
    }
}


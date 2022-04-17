
/**
 * Write a description of WordsInFile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import java.io.*;

public class WordsInFile {
    private HashMap<String,ArrayList<String>> map;
    private ArrayList<String> fileNames;
    
    public WordsInFile(){
        map = new HashMap<String,ArrayList<String>>();
        fileNames = new ArrayList<String>();
    }
    
    private void addWordsFromFile(File f){
        String name = f.getName();
        FileResource fr = new FileResource(f);
        for(String word : fr.words()){
            word = word.toLowerCase();
            if(map.containsKey(word)){
                if(map.get(word).indexOf(name) == -1){
                    map.get(word).add(name);
                }
            }else{
                ArrayList<String> temp = new ArrayList<String>();
                temp.add(name);
                map.put(word, temp);
            }
        }
    }
    public void buildWordFileMap(){
        map.clear();
        DirectoryResource dr = new DirectoryResource();
        for(File x : dr.selectedFiles()){
            fileNames.add(x.getName());
            addWordsFromFile(x);
        }
    }
    
    public int maxNumber(){
        int max = 0;
        for(String s : map.keySet()){
            int temp = map.get(s).size();
            if(temp > max){
                max = temp;
            }
        }
        return max; 
    }
    
    public ArrayList<String> whichFileDoesWordNotOccurIn(String excluded){
        ArrayList<String> whichFilesDontOccur = new ArrayList<String>();
        for(String s : map.keySet()){
            if(s.equalsIgnoreCase(excluded)){ 
                for(String x : fileNames){
                    if(map.get(excluded).indexOf(x) == -1){
                        whichFilesDontOccur.add(x);
                    }
                }
            }
        }
        return whichFilesDontOccur;
    }
    
    public ArrayList<String> whichFileDoesOccur(String included){ 
        ArrayList<String> whichFiles = new ArrayList<String>();
        a:for(String s : map.keySet()){
            if(s.equalsIgnoreCase(included)){
                for(String x : map.get(s)){
                    whichFiles.add(x);
                }
                break a;
            }
        }
        return whichFiles;
    }
    
    public int occurInCertainNumberOfFiles(){
        int total = 0;
        for(String s : map.keySet()){
            if(map.get(s).size() == 4){
                total++;
            }
        }
        return total;
    }
    
    public ArrayList<String> wordsInNumFiles(int number){
        ArrayList<String> words = new ArrayList<String>();
        for(String s : map.keySet()){
            if(map.get(s).size() == number){
                words.add(s);
            }
        }
        return words;
    }
    
    public void printFilesIn(String word){
        if(map.containsKey(word)){
            for(String s : map.get(word)){
                System.out.println(s + "," + " ");
            }
        }
    }
    
    public void tester(){
        WordsInFile test = new WordsInFile();
        test.buildWordFileMap();
        int max = test.maxNumber();
        ArrayList<String> word = test.wordsInNumFiles(max);
        /*
        ArrayList<String> whichFiles = test.whichFileDoesOccur("red");
        for(String a : whichFiles){
            System.out.println(a);
        }
        System.out.println(test.whichFileDoesWordNotOccurIn("sad"));
        System.out.println(test.occurInCertainNumberOfFiles());
        int max = test.maxNumber();
        System.out.println(max);
        ArrayList<String> allWords = test.wordsInNumFiles(max);
        System.out.println("The greatest number of files a word appears in is " + max + " and there are " + allWords.size() + " such words(s)");
        System.out.println();
        for(String s : allWords){
            System.out.println(s + " appears in files: ");
            test.printFilesIn(s);
        }
        */
    }
}

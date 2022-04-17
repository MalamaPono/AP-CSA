
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class MarkovRunner {
    public void runModel(IMarkovModel markov, String text, int size){ 
        markov.setTraining(text); 
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runModel(IMarkovModel markov, String text, int size, int seed){ 
        markov.setTraining(text); 
        markov.setRandom(seed);
        System.out.println("running with " + markov); 
        for(int k=0; k < 1; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runMarkov() { 
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        MarkovWordAny markovWord = new MarkovWordAny(3);
        int seed = 643;
        int numWords = 100;
        runModel(markovWord, st, numWords, 643);
        //MarkovWordOne markovWord = new MarkovWordOne(); 
        //runModel(markovWord, st, 200); 
    } 

    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println(); 
                psize = 0;
            } 
        } 
        System.out.println("\n----------------------------------");
    } 
    
    public void compareMethods(){ //learn how to use System.nanoTime() to check and compare speed
        FileResource fr = new FileResource();
        String st = fr.asString();
        st.replace('\n',' ');
        EfficientMarkovWord markov1 = new EfficientMarkovWord(5);
        MarkovWordAny markov2 = new MarkovWordAny(5);
        int size = 100;
        int seed = 56; //a set seed makes all outputs the same, where as not defining a certain seed will make it truly random and create different texts.
        runModel(markov1, st, size, seed);
        runModel(markov2, st, size, seed);
    }
    
    public void testHashMap(){
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        //String st = "this is a test yes this is really a test yes a test this is wow";
        EfficientMarkovWord markovWord = new EfficientMarkovWord(2);
        int seed = 65;
        int numWords = 100;
        markovWord.setTraining(st);
        markovWord.setRandom(42);
        markovWord.buildMap();
        markovWord.printHashMapInfo();
    }

}

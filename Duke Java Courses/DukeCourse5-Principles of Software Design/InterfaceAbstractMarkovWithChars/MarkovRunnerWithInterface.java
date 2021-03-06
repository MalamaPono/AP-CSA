
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*; 

public class MarkovRunnerWithInterface {
    public void runModel(IMarkovModel markov, String text, int size, int seed) {
        markov.setTraining(text);
        markov.setRandom(seed);
        System.out.println(markov);
        for(int k=0; k < 3; k++){
            String st= markov.getRandomText(size);
            printOut(st);
        }
    }
    
    public void runMarkov() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 10000;
        int seed = 615;
        
        EfficientMarkovModel m = new EfficientMarkovModel(5);
        runModel(m, st, size, seed);
        /*
        MarkovZero mz = new MarkovZero();
        runModel(mz, st, size, seed);
   
        MarkovOne mOne = new MarkovOne();
        runModel(mOne, st, size, seed);
        
        MarkovModel mThree = new MarkovModel(3);
        runModel(mThree, st, size, seed);
        
        MarkovFour mFour = new MarkovFour();
        runModel(mFour, st, size, seed);
        */

    }
    
    public void testHashMap(){
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        //String st = "yes-this-is-a-thin-pretty-pink-thistle";
        EfficientMarkovModel eff = new EfficientMarkovModel(5);
        eff.setTraining(st);
        eff.setRandom(615);
        eff.buildMap();
        //System.out.println(eff.getRandomText(50)); //if random keys donʻt have a following letter any more this program in specifically built to return whatever random String you have made so far and stop before actually excecuting the intended length string that the user wanted. This only usually happens in an extremely small sample text, but with longer texts with many letter combinations, random letter combinations will not throw it off.
        eff.printHashMapInfo();
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
        MarkovModel two1 = new MarkovModel(2);
        EfficientMarkovModel two2 = new EfficientMarkovModel(2);
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 1000;
        int seed = 42;
        runModel(two1, st, size, seed);
        runModel(two2, st, size, seed);
    }

}

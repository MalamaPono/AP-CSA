
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*;

public class MarkovRunner {
    public void runMarkovZero() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        MarkovZero markov = new MarkovZero();
        System.out.println("******");
        markov.setTraining(st);
        markov.setRandom(88);
        for(int k=0; k < 3; k++){
        String text = markov.getRandomText(500);
        printOut(text);
        }
    }
    
    public void runMarkovOne() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        MarkovOne markov = new MarkovOne();
        markov.setTraining(st);
        System.out.println("******");
        markov.setRandom(273);
        for(int k=0; k < 3; k++){
        String text = markov.getRandomText(500);
        printOut(text);
        }
    }
    
    public void runMarkovTwo() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        MarkovTwo markov = new MarkovTwo();
        markov.setTraining(st);
        markov.setRandom(42);
        for(int k=0; k < 3; k++){
        String text = markov.getRandomText(500);
        printOut(text);
        }
    }
    
    public void runMarkov4(){
        FileResource fr = new FileResource();
        String s = fr.asString();
        s = s.replace('\n', ' ');
        MarkovFour markov = new MarkovFour();
        markov.setRandom(371);
        markov.setTraining(s);
        System.out.println("*********");
        for(int i = 0; i < 3; i++){
            String a = markov.getRandomText(500);
            printOut(a);
        }
    }
    
    public void runMarkovModel() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        MarkovModel markov = new MarkovModel(8);
        markov.setTraining(st);
        markov.setRandom(365);
        System.out.println("*********");
        for(int k=0; k < 3; k++){
        String text = markov.getRandomText(500);
        printOut(text);
        }
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
	
}

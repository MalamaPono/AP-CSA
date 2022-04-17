
public class WofF {
    
    private String phraseCompare;
    private String phrase;
    private String guessedLetter;
    private int WrongCounter;
    
    public WofF(String p){ //constructor
        phraseCompare = p;
        phrase = p;      //giving value to instance variables that were just initialized in the top of class
        guessedLetter = ""; //giving value to instance variables that were just initialized in the top of class
        
        
    }
    
    public void displayPhrase() {
        for(int i = 0; i < phrase.length(); i++){
            String letterToCheck = phrase.substring(i, i+1);
            if(letterToCheck.equals(" ")){
                System.out.print(" ");
            }else if (guessedLetter.indexOf(letterToCheck) == -1){
                System.out.print("*");
            }else {
                System.out.print(letterToCheck);
            }
    }
        System.out.println();
}
    
    public void guessLetter(String l){
        guessedLetter += l;
        displayPhrase();
    }
}

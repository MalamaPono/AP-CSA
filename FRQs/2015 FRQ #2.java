public class HiddenWord {
    
    private String hiddenWord;
}

public HiddenWord(String x){
    hiddenWord = x;
}

public String getHint(String guess){
    
    String output = "";
    for(int i = 0; i < hiddenWord.length(); i++){
        if(hiddenWord.substring(i, i+1).equals(guess.substring(i,i+1))){
            output += hiddenWord.substring(i,i+1);
        }else if(hiddenWord.indexOf(guess.substring(i,i+1)) != -1){
            output += "+";
        }else{
            output += "*";
        }
    }
    return output;
    
}




public class WordGram {
    private String[] myWords;
    private int myHash;

    public WordGram(String[] source, int start, int size) {
        myWords = new String[size];
        System.arraycopy(source, start, myWords, 0, size); //first parameter is source and second paremter is where to start copying from and third is destination
    }

    public String wordAt(int index) {
        if (index < 0 || index >= myWords.length) {
            throw new IndexOutOfBoundsException("bad index in wordAt "+index);
        }
        return myWords[index];
    }

    public int length(){
        return myWords.length;
    }

    public String toString(){
        String ret = "";
        for(int i = 0; i < myWords.length; i++){
            ret += myWords[i] + " ";
        }
        return ret.trim();
    }

    public boolean equals(Object o) {
        WordGram other = (WordGram) o; //casting o to a wordgram
        
        //comparing length of this wordgram object in class to a different wordgram object.
        if(this.length() != other.length()){
            return false;
        }
        for(int i = 0; i < myWords.length; i++){
            if(!myWords[i].equals(other.myWords[i])){
                return false;
            }
        }
        return true;
    }

    public WordGram shiftAdd(String word) { 
        WordGram out = new WordGram(myWords, 0, myWords.length);
        int count = 1;
        for(int i = 0; i < myWords.length-1; i++){
            out.myWords[i] = out.myWords[count];
            count++;
        }
        out.myWords[myWords.length-1] = word;
        return out;
    }
    
    public int hashCode(){
        String temp = "";
        for(int i = 0; i < myWords.length; i++){
            temp += myWords[i];
        }
        int hash = temp.hashCode();
        return hash;
    }

}
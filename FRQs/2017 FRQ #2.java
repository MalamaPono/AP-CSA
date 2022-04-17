public class MultPractice implements StudyPractice{
    
    private final int FIRST_DIGIT;
    private int secondDigit;
    
    public MultPractice(int x, int y){
        FIRST_DIGIT = x;
        secondDigit = y;
    }
    
    public String getProblem(){
        return FIRST_DIGIT + " TIMES " + secondDigit;
    }
    
    public void nextProblem(){
        secondDigit+=1;
    }
}

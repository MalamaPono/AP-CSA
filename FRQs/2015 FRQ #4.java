public interface NumberGroup{
    public boolean contains(int x);
}

public class Range implements NumberGroup{
    
    private int min;
    private int max;
    
    public Range(int x, int y){
        min = x;
        max = y;
    }
    
    //one way to write contains method
    public boolean contains(int x){
        for(int i = min; i <= max; i++){
            if(x == i){
                return true;
            }
            min++;
        }
        return false;
    }
    
    //another way to write contains method
    public boolean contains(int x){
        if(min<=x<=max){
            return true;
        }else{
            return false;
        }
    }
    
}

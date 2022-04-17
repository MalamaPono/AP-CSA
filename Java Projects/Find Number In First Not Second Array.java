import java.util.*;
/*
1) Given two arrays, 1,2,3,4,5 and 2,3,1,0,5 find which number is not present in the second array.
*/

public class MyClass{
    public static void main(String[] args){
        
        
        int[] one = {1,2,3,4,5,7,3,2,6};
        int[] two = {2,3,1,0,5,7,3,4,6};
        System.out.println(findWhichNumber(one,two));
        
    }
    
    
    public static int findWhichNumber(int[] one, int[] two){
        
        boolean thisNum = false;
        
        for(int i = 0; i < one.length; i++){
            for(int j = 0; j < two.length; j++){
                if(two[j] == one[i]){
                    thisNum = true;
                    break;
                }else{
                    thisNum = false;
                }
            }
            if(thisNum == false){
                return one[i];
            }
        }
        
        return -1;
    }
    
}

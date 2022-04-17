/*
THE KEY TO THIS SOLUTION IS THAT IF EQUAL SLICES OF CAKE CANNOT BE CUT WITHOUT LEAVING ANY LEFTOVERS,
THE AMOUNT OF SLICES THAT CAN BE CUT IS SIMPLY 1, WHICH JUST REPRESENTS THE SLICE OF THE WHOLE
CAKE ITSELF. THE CAKE ITSELF IS ONE SLICE WHICH IS THE MAXIMUM AMOUNT OF SLICES THAT CAN BE MADE
IF THERE IS NO OTHER PATTERN FOUND WITHIN MY CODE.
*/
 
public class Solution {
    
    public static int solution(String x) {
        
        for(int i = 1; i <= x.length()/2; i++){
            String checkRepeat = x.substring(0,i);
            if(repeatsThroughout(checkRepeat,x) == true){
                return countRepeat(checkRepeat,x);
            }
        }
        
        return 1;
        
    }
    
    private static boolean repeatsThroughout(String check, String str){
       
       if(str.equals("")){
            return true;
        }
        
        // String checkStr = str.substring(0,check.length());
        int index = str.indexOf(check);
        if(index == 0){
            str = str.substring(check.length());
            return repeatsThroughout(check,str);
        }else{
            return false;
        }
        
        
    }
    
    private static int countRepeat(String check, String str){
        int index = str.indexOf(check);
        int count = 0;
        while(index != -1){
            count++;
            index = str.indexOf(check,index+check.length());
        }
        return count;
    }
    
}
 

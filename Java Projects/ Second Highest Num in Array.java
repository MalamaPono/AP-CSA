/*
1) Find the second highest number in an array
*/

public class MyClass{
    public static void main(String[] args){
        
        int[] nums = {-5,0,4,3};
        System.out.println(secondHighest(nums));
        
    }
    
    public static int secondHighest(int[] num){
        if(num.length == 1){
            return num[0];
        }
        
        int max = num[0];
        for(int i = 0; i < num.length; i++){
            if(num[i] > max){
                max = num[i];
            }
        }
        
      
      for(int j = 0; j < num.length; j++){
          num[j] = num[j] - max;
      }
      
      int secondMax = num[0];
      for(int x : num){
          if(x != 0){
          if(x > secondMax){
              secondMax = x;
          }
          }
      }
        
        return secondMax + max;
        
    }
    
}

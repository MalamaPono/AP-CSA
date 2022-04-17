import java.util.LinkedList;
import java.util.ArrayList;
public class MyClass{
    public static void main(String[] args){
        
        int[] num = {1,3,7};
        System.out.println(subArray(num));
        
    }
    
    
    
    //just for fun
    //max number of subarrays in any given array is equal to its length n inside of this explicit expression below
    /*
    explicit expression ((n(n+1))/2) + 1
    0 > 1
    1 > 2
    2 > 4
    3 > 7
    4 > 11
    5 > 16
    6 > 22
    7 > 29
    8 > 37
    */
    
    public static int subArray(int[] num){
        int sum = 0;
        for(int x : num){
            sum += x;
        }
        
        //you can have this
        int sumSoFar = 0;
        for(int i = 0; i < num.length-1; i++){
            sumSoFar = num[i];
            for(int j = i+1; j < num.length; j++){
                sumSoFar = sumSoFar + num[j];
                sum += sumSoFar;
            }
        }
        
        return sum;
    }
        
        
        //code to incliude the sum of each individual indicie which gets rid of the need of that for each loop in the beginning
        // for(int i = 0; i < num.length; i++){
        //     int sumSoFar = 0;
        //     for(int j = i; j < num.length; j++){
        //         sumSoFar = sumSoFar + num[j];
        //         sum += sumSoFar
        //     }
        // }
    
        // //or you can have this
        // for(int i = 0; i < num.length; i++){
        //     for(int j = 0; j < num.length; j++){
        //         int y = i;
        //         while(y <= j){
        //             sum += num[y];
        //             y++;
        //         }
        //     }
        // }
        //
        
        // the code commented right above this is also the same as this code, but instead of using a while loop, I chose to use a for loop for the third loop.
        // for(int i = 0; i < num.length-1; i++){
        //     for(int j = i+1; j < num.length; j++){
        //             for(int y = i; y <= j; y++){
        //                 sum += num[y];
        //             }
        //         }
        //     }
        
    }

/*

Find the kth smallest integer in an array

*/


import java.util.*;
public class MyClass {
    public static void main(String args[]) {
        
        int[] num = {1,2,2,2,2,2,2};
        System.out.println(kthSmallest(num,3));
        
    }
    
    public static int kthSmallest(int[] num, int k){
        // //if not considering duplicates leave this check up here
        // if(k > num.length || k <= 0){
        //     throw new IllegalArgumentException();
        // }
        
        //first removeDuplicates
        num = removeDuplicates(num);
        
        //then sort array
        bubbleSort(num);
        
        //then find kth smallest int
        
        //  1)
        //If no duplicate entires or duplicate entries are not considered remove the removeDuplicatrs method, and simply just call 
        // int kthSmallest = num[k-1]; after sorting the array.
        
        //  2)
        //use removeDuplicates if there are duplicate entries allowed and each duplicate number would count as the same kth smallest number
        
        
        if(k > num.length || k <= 0){
            throw new IllegalArgumentException();
        }
        //If considering duplicates leave this check down here.
        
        
        int kthSmallest = num[k-1];
        
        return kthSmallest;
        
    }
    
    public static void bubbleSort(int[] num){
        
        for(int i = 0; i < num.length-1; i++){
            for(int j = 0; j < num.length-1-i; j++){
                if(num[j] > num[j+1]){
                    int temp = num[j+1];
                    num[j+1] = num[j];
                    num[j] = temp;
                }
            }
        }
        
        
    }
    
    public static int[] removeDuplicates(int[] num){
        
        HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
        
        for(int x : num){
            if(map.containsKey(x) == false){
                map.put(x,1);
            }else{
                map.put(x,map.get(x)+1);
            }
        }
        
        int size = map.size();
        
        int[] ret = new int[size];
        int index = 0;
        for(Integer y : map.keySet()){
            ret[index] = y;
            index++;
        }
        return ret;
    }
    
}

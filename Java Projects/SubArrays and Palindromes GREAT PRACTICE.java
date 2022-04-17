import java.util.*;

public class MyClass {
    public static void main(String args[]) {
        
        int[] arr = {5,1,-7,10,20,30};
        // System.out.println(largestSubArraySum(arr));
       // System.out.println(subArraySum(arr));
       
       int[] subarray = subArrayToMakeSum(arr,-1);
       System.out.println(Arrays.toString(subarray));
        
        String palindrone = "abcdeffedsnkddbaaaaaaaaaaaa";
        System.out.println(longestPalindrone(palindrone));
        
    }
    
    
    public static String longestPalindrone(String str){
        
        if(str.equals("")){
            return str;
        }
        
        int longest = 0;
        String palin = "";
        for(int i = 0; i < str.length(); i++){
            for(int j = i+1; j <= str.length(); j++){
                
                String check = str.substring(i,j);
                if(isPalindrone(check) == true && check.length() > longest){
                    longest = check.length();
                    palin = check;
                }
                
            }
        }
        
        return palin;
        
    }
    
    public static boolean isPalindrone(String str){
        
        if(str.equals("")){
            return false;
        }
        
        String check = "";
        for(int i = str.length(); i >= 1; i--){
            String temp = str.substring(i-1,i);
            check += temp;
        }
        
        if(str.equals(check)){
            return true;
        }else{
            return false;
        }
        
    }
    


    //solution with 2 arraylists
    public static int largestSubArraySum(int[] arr){
        
        if(arr.length == 0){
            throw new IllegalArgumentException();
        }
        
        ArrayList<Integer> ret = new ArrayList<Integer>();
        int largestSoFar = arr[0];
        for(int i = 0; i < arr.length; i++){
            for(int j = i; j < arr.length; j++){
                int tempSum = 0;
                int s = i;
                ArrayList<Integer> saved = new ArrayList<Integer>();
                while(s <= j){
                    tempSum = tempSum + arr[s];
                    saved.add(arr[s]);
                    if(tempSum > largestSoFar){
                        largestSoFar = tempSum;
                        ret = saved;
                        
                    }
                    s++;
                }
            }
        }
        
        System.out.println(ret);
        return largestSoFar;
        
    }
    
    
    //solution with array
    // public static int largestSubArraySum(int[] arr){
        
    //     if(arr.length == 0){
    //         throw new IllegalArgumentException();
    //     }
        
    //     ArrayList<Integer> ret = new ArrayList<Integer>();
    //     int largestSoFar = arr[0];
    //     for(int i = 0; i < arr.length; i++){
    //         for(int j = i; j < arr.length; j++){
    //             int tempSum = 0;
    //             int s = i;
    //             int[] saved = new int[j-s+1];
    //             int index = 0;
    //             while(s <= j){
    //                 tempSum = tempSum + arr[s];
    //                 saved[index] = arr[s];
    //                 if(tempSum > largestSoFar){
    //                     largestSoFar = tempSum;
    //                     ret = new ArrayList<Integer>();
    //                     for(int x : saved){
    //                         ret.add(x);
    //                     }
                        
    //                 }
    //                 s++;
    //                 index++;
    //             }
    //         }
    //     }
        
    //     System.out.println(ret);
    //     return largestSoFar;
        
    // }
    
    
    /*
    this specific entire subArraySum will define the tempSum variable in a different loop becuase in this case the tempSum doesn't represent the sum of a specific 
    subArray, but rather represents the sum of all subarrays starting from a particular index all the way to end of array.
    
    For example in arr [1,24,3]
    tempSum would represent the sum of subarrays [1], [1,24], and [1,24,3] when starting at index 0.
    
    Whereas in other subArray methods in this jdoodle file, the tempSum variable represents only the sum of one specific subarray as we don't care about the sum of all subarrays in those other methods.
    For example in arr [1,24,3]
    tempSum would represent specifically the sum of the subarray [1,24,3] or specifically the sum of the subarray [24,3] depending on starting index.
    
    */
    public static int subArraySum(int[] arr){
        
        int totalSum = 0;
        for(int i = 0; i < arr.length; i++){
            int tempSum = 0;
            for(int j = 0; j < arr.length; j++){
                int s = i;
                while(s <= j){
                    tempSum = tempSum + arr[s];
                    s++;
                }
            }
            totalSum += tempSum;
        }
        
        return totalSum;
        
    }
    
    public static int[] subArrayToMakeSum(int[] arr, int sum){
        
        for(int i = 0; i < arr.length; i++){
            for(int j = i; j < arr.length; j++){
                int tempSum = 0;
                //loop through the subarray
                int s = i;
                
                int[] saved = new int[j-s+1];
                int index = 0;
                
                while(s <= j){
                    tempSum += arr[s];
                    saved[index] = arr[s];
                    index++;
                    s++;
                }
                
                if(tempSum == sum){
                    return saved;
                }
                
            }
        }
        
        throw new IllegalArgumentException();
        
    }
    
    
    //number of subarrays that make a certain target sum
    public static int numArrays(int[] num, int target){
        int count = 0;
        for(int i = 0; i < num.length; i++){
            for(int j = i; j < num.length; j++){
                int sum = 0;
                int s = i;
                while(s <= j){
                    sum += num[s];
                    s++;
                }
                
                if(sum == target){
                    count++;
                }
            }
        }
        return count;
    }
    
    
}


/*
for(int i = 0; i < arr.length; i++){
            for(int j = i; j < arr.length; j++){
                int tempSum = 0;
                int s = i;
                int index = 0;
                while(s <= j){
                    int[] saved = new int[j-s+1];
                    tempSum = tempSum + arr[s];
                    saved[index] = arr[s];
                    if(tempSum > largestSoFar){
                        largestSoFar = tempSum;
                        ret.clear();
                        for(int x : saved){
                            ret.add(x);
                        }
                    }
                    s++;
                    index++;
                }
                
            }
        }
*/
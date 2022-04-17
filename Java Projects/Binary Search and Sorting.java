import java.util.Arrays;
public class MyClass {
    public static void main(String args[]) {
        
        int[] num = {1,3,2,6,18,9,-5,-4};
        num = sortMe3(num);
        System.out.println(Arrays.toString(num));
        
        int position = BinarySearch(num, -4);
        System.out.println(position);
        
        
        
    }
    
    
    public static int[] sortMe(int[] num){
        
        for(int currentPos = 1; currentPos < num.length; currentPos++){
            
            int checkPos = currentPos;
            int temp = num[checkPos];
            while(checkPos > 0 && temp < num[checkPos-1]){
                num[checkPos] = num[checkPos-1];
                checkPos--;
            }
            num[checkPos] = temp;
        }
        return num;
        
    }
    
    public static int[] sortMe2(int[] num){
        for(int i = 0; i < num.length-1; i++){
            int min = num[i];
            int minLoc = i;
            for(int j = i+1; j < num.length; j++){
                if(num[j] < min){
                    min = num[j];
                    minLoc = j;
                }
            }
            int temp = num[i];
            num[i] = min;
            num[minLoc] = temp;
            
        }
        return num;
    }
    
    public static int[] sortMe3(int[] num){
        for(int i = 0; i < num.length-1; i ++){
            for(int j = 0; j < num.length-1-i; j++){
                if(num[j] > num[j+1]){
                    int temp = num[j];
                    num[j] = num[j+1];
                    num[j+1] = temp;
                }
            }
        }
        return num;
    }
    
    
    //assumes that the array is in order least to greatest
    //binary search works just as linear search and will work for any case with any numbers or negatives.
    public static int BinarySearch(int[] num, int target){
        int start = 0;
        int end = num.length-1;
        int mid;
        while(start<=end){
            mid = (start+end)/2;
            if(target < num[mid]){
                end = mid - 1;
            }else if(target > num[mid]){
                start = mid +1;
            }else{
                return mid;
            }
        }
        return -1; //-1 means the thing we are searchign for is not in array
    }
    
    
    public static int binarySearchRecursive(int[] num, int low, int high, int target){
        int mid = (low + high)/2;
        if(low <= high){
            if(target > num[mid]){
                return binarySearchRecursive(num, mid+1, high, target);
            }else if(target < num[mid]){
                return binarySearchRecursive(num, low, mid-1, target);
            }else{
                return mid;
            }
        }else{
            return -1;
        }
    }
    
    public static int binarySearchIterative(int[] num, int target){
        int low = 0;
        int high = num.length-1;
        while(low <= high){
            int mid = (low+high)/2;
            if(target > num[mid]){
                    low = mid+1;
            }else if(target < num[mid]){
                high = mid -1;
            }else{
                return mid;
            }
        }
        return -1;
    }
    
/*
MAXIMUM TIMES A BINARY SEARCH CAN RUN:
1. Continuously divide the number of elements in the array (array.length) by 2 over and over until you get 1
2. This represents the scenario that you keep cutting the data set in half and it is the worst case scenario that you
donʻt find it until you finnally split that last data set and your number is the only number left in the array. This
shows that it was the maximum number of times possible becuase you couldnʻt find your number until you eliminated all other 
elements in the sorted array and was just left with your number.
3. The best case scenario is when the central point of the start and end is the target you are looking for. In that case it will only run once.
4. Example: What is the maximum number of times the Binary Search Method will Run with an array of 2000 elements.

2000
1000
500
250
125
62
31
15
7
3
1

*11 times is the answer

*/


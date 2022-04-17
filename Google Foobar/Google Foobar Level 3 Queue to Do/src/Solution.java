/*

This solution uses an array to find and hold all of the worker ids, then simply XORs each one of them one by one in order
just like shown in the first couple of test cases. However, this solution doesn't work when the worker ids
become very large like in the millions and the length of the number of worker ids that are scanned gets large as well.
This is because Google wants an efficient solution and will count you as failed for the test cases with large outputs
because your solution takes too long. 

0^1^2^4^5^6

 */

public class Solution {
    public static int solution(int start, int length) {
        //Your code goes here.
        
        int[] ids = getIds(start,length);
        int answer = ids[0];
        
        for(int i = 1; i < ids.length; i++) {
        	answer = answer^ids[i];
        }
        
        return answer;
    }

public static int[] getIds(int start, int length){
    int numWorkers = getWorkers(length);
    int[] ids = new int[numWorkers];
    
    int num = start;
    int index = 0;
    int addition = 0;
    for(int i = length; i > 0; i--){
        for(int j = 0; j < i;j++){
            ids[index] = num;
            index++;
            num++;
        }
        if(i != length){
            addition++;
            num+=addition;
        }
    }
    return ids;
}

public static int getWorkers(int length){
    if(length == 0){
        return 0;
    }
    
    return length + getWorkers(length-1);
}

}
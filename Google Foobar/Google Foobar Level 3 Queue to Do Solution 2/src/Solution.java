import java.util.ArrayList;

public class Solution {
    public static int solution(int start, int length) {
        
    	int id = start;
    	int addition = 0;
    	
    	int answer = 0;
    	
    	for(int i = length; i > 0; i--) {
    		int begin = id;
    		for(int j = 0; j < i; j++) {
    			id++;
    		}
    		int end = id;
    		answer = answer^XOR(begin-1)^XOR(end-1);
    		if(i != length) {
    			addition++;
    			id+=addition;
    		}
    	}

    	return answer;
    }
    
    private static int XOR(int x) {
    	int val = x%4;
    	if(val == 0) {
    		return x;
    	}else if(val == 1) {
    		return 1;
    	}else if(val == 2) {
    		return x+1;
    	}else{
    		return 0;
    	}
    	
    }
}
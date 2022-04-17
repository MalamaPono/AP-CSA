/*
SORT BASED ON ELEVATOR RECORD CLASS HAVING VARIABLES FIRSTNUM, SECONDNUM, AND THIRDNUM. AGAIN USE STRING LENGTH
TO BREAK THE TIEBREAKERS. SHOULD BE EASY FROM HERE JUST MAKE THOSE ADJUSTMENTS. 
*/


public class Solution{
    public static String[] solution(String[] l){
        
        ElevatorRecord[] record = new ElevatorRecord[l.length];
        int index = 0;
        
        /*
        convert all elevator strings to elevator records with a double as the 
        representation of the elevator version, and a string as the same string
        representation of the elevator version.
        */
        for(String x : l){
            int first = getFirst(x);
            int second = getSecond(x);
            int third = getThird(x);
            ElevatorRecord rec = new ElevatorRecord(first,second,third,x);
            record[index] = rec;
            index++;
        }
        
        quickSort(record,0,record.length-1);
        
        String[] ret = new String[l.length];
        
        index = 0;
        for(ElevatorRecord e: record){
            ret[index] = e.version;
            index++;
        }
        
        return ret;
        
    }
    
    public static int getFirst(String x) {
    	int numPeriods = numPeriods(x);
    	if(numPeriods == 0) {
    		return Integer.parseInt(x);
    	}else{
    		String first = x.substring(0,x.indexOf("."));
    		return Integer.parseInt(first);
    	}
    }
    
    public static int getSecond(String x) {
    	int numPeriods = numPeriods(x);
    	if(numPeriods == 0) {
    		return 0;
    	}else if(numPeriods == 1) {
    		String second = x.substring(x.indexOf(".")+1);
    		return Integer.parseInt(second);
    	}else {
    		int firstIndex = x.indexOf(".");
        	int secondIndex = x.indexOf(".",firstIndex+1);
        	String sec = x.substring(firstIndex+1,secondIndex);
        	return Integer.parseInt(sec);
    	}
    	
    	
    }

    public static int getThird(String x) {
    	int numPeriods = numPeriods(x);
    	if(numPeriods == 0 || numPeriods == 1) {
    		return 0;
    	}
    	
    	int firstIndex = x.indexOf(".");
    	int secondIndex = x.indexOf(".",firstIndex+1);
    	String third = x.substring(secondIndex+1);
    	return Integer.parseInt(third);
	
    }
    
    private static void quickSort(ElevatorRecord[] rec, int left, int right){
        
        if(left >= right){
            return;
        }
        
        ElevatorRecord pivotValue = rec[(left+right)/2];
        int partitionIndex = partition(rec,left,right,pivotValue);
        quickSort(rec,left,partitionIndex-1);
        quickSort(rec,partitionIndex,right);
        
    }
    
    private static int partition(ElevatorRecord[] rec, int left, int right, ElevatorRecord pivotValue){
        
        while(left <= right){
            
            while(rec[left].compareTo(pivotValue) < 0){
                left++;
            }
            
            while(rec[right].compareTo(pivotValue) > 0){
                right--;
            }
            
            if(left <= right){
                ElevatorRecord temp = rec[left];
                rec[left] = rec[right];
                rec[right] = temp;
                right--;
                left++;
            }
            
        }
        
        return left;
        
    }
    
    private static int numPeriods(String x) {
    	String[] periods = x.split("\\.");
        int numPeriods = periods.length - 1;
        return numPeriods;
    }
    
    
}

class ElevatorRecord{
    int first;
    int second;
    int third;
    String version;
    
    public ElevatorRecord(int first, int second, int third, String version){
        this.first = first;
        this.second = second;
        this.third = third;
        this.version = version;
    }
    
    
    public int compareTo(Object o){
        ElevatorRecord other = (ElevatorRecord)o;
        
        //check first int
        if(this.first < other.first) {
        	return -1;
        }else if(this.first > other.first) {
        	return 1;
        }
        
        //since firsts are equal, now check second int
        if(this.second < other.second) {
        	return -1;
        }else if(this.second > other.second) {
        	return 1;
        }
        
        //since firsts and seconds are equal, now check third int
        if(this.third < other.third) {
        	return -1;
        }else if(this.third > other.third) {
        	return 1;
        }
        
        //since all numbers are equal, now check the String lengths to determine which object is greater and which is less
        if(this.version.length() > other.version.length()){
            return 1;
        }else if(this.version.length() < other.version.length()){
            return -1;
        }else{
            return 0;
        }
        
        
    }
}

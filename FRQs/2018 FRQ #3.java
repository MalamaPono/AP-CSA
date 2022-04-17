public class CodeWordChecker{
    private int minLen;
    private int maxLen;
    private String isExculded;

public CodeWordChecker(int x, int y, String exclude){
    minLen = x;
    maxLen = y;
    isExcluded = exclude;
}
 
public CodeWordChecker(String exclude){
    minLen = 6;
    maxLen = 20;
    isExcluded = exclude;
}

public boolean isValid(String str){
    if(str.length() < minLen || str.length() > maxLen || str.indexOf(isExclude) != -1){
        return false;
    }else{
        return true;
    }
}
}

//another solution traversing string and finding substring to see if it matches isExcluded

/*
if(str.length < min){
    return false;
}else if(str.length > max){
    return false;
}else if(str.indexOf(isExcluded) != -1){
    return false;
}else{
    for(int i = 0; i < str.length() - isExcluded.length(); i++){
        if(str.substring(i, i + isExcluded.length()).equals(isExcluded)){
            return false;
        }
    }
    return true;
}
*/


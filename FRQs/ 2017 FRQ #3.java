public void replaceNthOccurence(String str, int n, String repl){
    if(findNthOccurence(str, n) != -1){
    int occurence = findNthOccurence(str, n);
    String previous = currentPhrase.substring(0, occurence);
    String trailing = currentPhrase.substring(occurence + str.length());
    currentPhrase = previous + repl + trailing;
    }
}

public int findLastOccurence(String str){
    if(findNthOccurence(str, 1) == -1){
        return -1;
    }
    boolean check = true;
    int i = 1;
    while(check == true)
    if(findNthOccurence(str, i) != -1){
        i++;
    }else{
        check = false;
    }
    return findNthOccurence(str, i-1);
}

//another way
public int findLastOccurence(String str){
    if(findNthOccurence(str, 1) == -1){
        return -1;
    }
    
    int i = 1;
    while(findNthOccurence(str, i) != -1){
        i++;
    }
    return findNthOccurence(str, i-1);
}

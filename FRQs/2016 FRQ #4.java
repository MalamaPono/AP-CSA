public static int totalLetters(List<String> wordList){
    
    for(int i = 0; i < wordList.size(); i++){
        int x += wordList.get(i).length();
    }
    return x;
}

public static int basicGapWidth(List<String> wordList, int formattedLen){
    
    int stringLength = formattedLen - totalLetters(wordList);
    int gaps = wordList.size() - 1;
    
    
    return stringLength/gaps;
    
}

public static String format(List<String> wordList, int formattedLen){
    
    int spacesLeftover = leftoverSpaces(wordList, formattedLen);
    int basicGap = basicGabWidth(wordlist, formattedLen);
    String answer = "";
    
    
    for(int i = 0; i < wordList.size(); i++){
        while(wordList.get(i) != " "){
        for(int x = 1; x < basicGap+1; x++){
        wordlist.add(i + x, " ");
            }
        }
        
    }
    for(int j = 0; j < spacesLeftover; j++){
        while(spacesLeftover != 0)
        if(wordList.get(j) == " " && wordList.get(j+1) != " "){
            wordList.add(j+1, " ");
            spacesLeftover--;
            j++;
            
        }
    }
    
    for(int i = 0; i < wordList.size(); i++){
        answer += wordList.get(i);
    }
    return x;
}

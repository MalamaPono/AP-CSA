public WordPairList(String[] words){
    allPairs = new ArrayList<WordPair>();
    for(int i = 0; i < words.length()-1; i++){
        for(int k = i+1; k < words.length(); k++){
            WordPair pair = new WordPair(words[i], words[j]);
            allPairs.add(pair);
        }
    }
}

//part b
public int numMatches(){
    int count = 0;
    for(int i = 0; i < allPairs.size(); i++){
        WordPair pair = allPairs[i];
        if(pair.getFirst().equals(pair.getSecond())){
            count++;
        }
    }
    return count;
}

//another way to do part b
public int numMatches(){
    int count = 0;
    for(int i = 0; i < allPairs.size(); i++){
        WordPair pair = allPairs[i];
        String first = pair.getFirst();
        String second = pair.getSecond();
        if(first.equals(second)){
            count++;
        }
    }
    return count;
}

//for each way
public int numMatches(){
    int count = 0;
    for(WordPair p : allPais){
        if(p.getFirst().equals(p.getSecond())){
            count++;
        }
    }
    return count;
}

//part a
public int findHorseName(String name){

    for(int i = 0; i < spaces.length; i++){
        if(spaces[i] != null){
        if(spaces[i].getName().equals(name)){
            return i;
        }
    }
 }
    return -1;
}

//part b
public void consolidate(){
    
    for(int currentPos = 0; i < spaces.length; i++){
        
        if(spaces[currentPos] == null]){
        for(int checkPos = currentPos+1; checkPos < spaces.length; checkPos++){
            if(spaces[checkPos] != null){
                spaces[currentPos] = spaces[checkPos];
                spaces[checkPos] == null;
                checkPos = spaces.length;
                
            }
        }
    
    }
}
}

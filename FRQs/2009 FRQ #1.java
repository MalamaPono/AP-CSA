public static int[] getCubeTosses(NumberCube cube, int numTosses){
    int[] tosses = new int[numTosses]
    for(int i = 0; i <= numTosses; i++){
        toses[i] = cube.toss();
    }
    
    return tosses;
}

public static int getLongestRun(int[] values){
    
    int MaxrunLength = 0;
    int length = 0;
    int index = 0;
    for(int i = 0; i < values.length-1; i++){
        if(valus[i] == values[i+1]){
            length++;
        }
        if(values[i] != values[i+1]){
            if(length > MaxrunLength){
                MaxrunLength = length;
                index = values[i];
            }
            length = 0; 
        }

    }
    if(MaxrunLength == 0){
        return -1;
    }else{
        return index;
    }
}

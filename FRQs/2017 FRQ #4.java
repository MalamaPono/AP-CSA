public static Position findPosition(int num, int[][] intArr){
    for(int i = 0; i < intArr.length; i++){
        for(int k = 0; k < intArr[i].length; i++){
            if(intArr[i][k] == num){
                return new Poition(i,k);
            }
        }
    }
    return null;
}

public static Position[][] getSuccessorArray(int[][] intArr){
    Position[][] pos = new Position[intArr.length][intArr[0].length];
    for(int i = 0; i < intArr.length; i++){
        for(int k = 0; i < intArr[i].length; i++){
            int check = intArr[i][k] + 1;
            Position p = findPosition(check, intArr);
            pos[i][k] = p;
        }
    }
    return pos;
}

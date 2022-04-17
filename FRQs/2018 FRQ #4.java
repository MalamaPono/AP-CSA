//this is if you donʻt know the length of each column 
public static int[] getColumn(int[][] arr2D, int c){
    
    ArrayList<Integer> temp = new ArrayList<Integer>();
    for(int row = 0; row < arr2D.length; row++){
        for(int col = 0; col<arr2D[row].length; col++){
            if(col == c){
                temp.add(arr2D[row][col]);
            }
        }
    }
    int[] result = new int[temp.size()];
    for(int i = 0; i < temp.size(); i++){
        result[i] = temp.get(i);
    }
   return result; 
}

//this is easier way thorugh doing it in array. The column length is the same as the number of rows.
public static int[] getColumn(int[][] arr2D, int c){
    int[] result = new int[arr2D.length];
    
    for(int row = 0; row < arr2D.length; row++){
        result[row] = arr2D[row][c]; //at each row there is only one value in the corresponding column we want. This line will add that value in that specific column to our column array.
    }
    return result;
}

public static boolean isLatin(int[][] square){
    
    int[] temp = square[0];
    if(containsDuplicates(temp) == true){
        return false;
    }
    
    //since it is a square only need one for loop as rows and columns are the same. 
    for(int i = 0; i < square.legnth; i++){
        if(hasAllValues(square[0], square[i]) == false){
            return false;
        }
        int[] columns = getColumn(square, i);
        if(hasAllValues(square[0], columns) == false){
            return false;
        }
    }
    return true;
}


//Say it was a rectangle array and columns were differet: The columns will all have same length though and we will get this through calling the length of row 1
//in order to check columns with first row
for(int i = 0; i < square[0].length; i++){ //this checks length of first row as precondition states there is at least one row
    int[] columns = getColumn(square, i);
    if(hasAllValues(square[0], columns) == false){
        return false;
    }
    
}

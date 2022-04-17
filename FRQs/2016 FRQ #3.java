private boolean toBeLabeled(int r, int c, boolean[][] blackSquares){
    
    if(blackSquares[r][c] == true){
        return false;
    }
    
    if(r == 0 || c == 0){
        return true;
    }if(blackSquares[r-1][c] == true || blackSquares[r][c-1] == true){
        return true; 
    }
    
    return false;
}

public Crossword(boolean[][] blackSquares){
    
    puzzle = new Square[blackSquares.length][blackSquares(0).length];
    int x = 1;
    for(int row = 0; i < puzzle.length; i++){
        
        for(int col = 0; i < puzzle[row].length; i++){
            if(balckSquares[row][col] == true){
                puzzle[row][col] = new Square(true, 0);
            }else{
                if(toBeLabled(row,col,blackSquare) == true){
                    puzzle[row][col] = new Square(false, x);
                    x++;
                }else{
                    puzzle[row][col] = new Square(false,0);
                }
            }
        }
    }
    
}

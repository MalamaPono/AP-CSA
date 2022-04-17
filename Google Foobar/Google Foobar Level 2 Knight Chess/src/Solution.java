import java.util.LinkedList;
public class Solution {
        
    public static int solution(int src, int dest) {
    	
    	if(src == dest) {
    		return 0;
    	}
        
    	//setup board
        int[][] board = new int[8][8];
           int num = 0;
           for(int row = 0; row < 8; row++){
               for(int col = 0; col < 8; col++){
                   board[row][col] = num;
                   num++;
               }
           }
       LinkedList<Square> squares = new LinkedList<Square>();
       validMoves(src,0,board,squares);
        
        while(true){
            Square pop = squares.pollFirst();
            if(pop.id == dest){
                return pop.numMoves;
            }else{
                validMoves(pop.id,pop.numMoves,board,squares);
            }
            
        }
        
    }
    
    private static void validMoves(int position,int moves,int[][]board,LinkedList<Square> squares){
        int row = getRow(position,board);
        int col = getCol(position,board);
        checkLeftUp(position,row,col,moves,board,squares);
        checkLeftDown(position,row,col,moves,board,squares);
        checkRightUp(position,row,col,moves,board,squares);
        checkRightDown(position,row,col,moves,board,squares);
        checkUpLeft(position,row,col,moves,board,squares);
        checkUpRight(position,row,col,moves,board,squares);
        checkDownLeft(position,row,col,moves,board,squares);
        checkDownRight(position,row,col,moves,board,squares);
    }    
    private static void checkLeftUp(int num,int row,int col,int moves,int[][]board,LinkedList<Square> squares){
        
        int newCol = col-2;
        int newRow = row-1;
        
        if(isValid(newRow,newCol)){
            int position = board[newRow][newCol];
            int numMoves = moves+1;
            Square addition = new Square(position,numMoves);
            squares.add(addition);
        }
        
    }
    private static void checkLeftDown(int num,int row,int col,int moves,int[][]board,LinkedList<Square> squares){
        int newCol = col-2;
        int newRow = row+1;
        
        if(isValid(newRow,newCol)){
            int position = board[newRow][newCol];
            int numMoves = moves+1;
            Square addition = new Square(position,numMoves);
            squares.add(addition);
        }
    }
    private static void checkRightUp(int num,int row,int col,int moves,int[][]board,LinkedList<Square> squares){
        int newCol = col+2;
        int newRow = row-1;
        
        if(isValid(newRow,newCol)){
            int position = board[newRow][newCol];
            int numMoves = moves+1;
            Square addition = new Square(position,numMoves);
            squares.add(addition);
        }
    }
    private static void checkRightDown(int num,int row,int col,int moves,int[][]board,LinkedList<Square> squares){
        int newCol = col+2;
        int newRow = row+1;
        
        if(isValid(newRow,newCol)){
            int position = board[newRow][newCol];
            int numMoves = moves+1;
            Square addition = new Square(position,numMoves);
            squares.add(addition);
        }
    }
    private static void checkUpLeft(int num,int row,int col,int moves,int[][]board,LinkedList<Square> squares){
        int newCol = col-1;
        int newRow = row-2;
        
        if(isValid(newRow,newCol)){
            int position = board[newRow][newCol];
            int numMoves = moves+1;
            Square addition = new Square(position,numMoves);
            squares.add(addition);
        }
    }
    private static void checkUpRight(int num,int row,int col,int moves,int[][]board,LinkedList<Square> squares){
        int newCol = col+1;
        int newRow = row-2;
        
        if(isValid(newRow,newCol)){
            int position = board[newRow][newCol];
            int numMoves = moves+1;
            Square addition = new Square(position,numMoves);
            squares.add(addition);
        }
    }
    private static void checkDownLeft(int num,int row,int col,int moves,int[][]board,LinkedList<Square> squares){
        int newCol = col-1;
        int newRow = row+2;
        
        if(isValid(newRow,newCol)){
            int position = board[newRow][newCol];
            int numMoves = moves+1;
            Square addition = new Square(position,numMoves);
            squares.add(addition);
        }
    }
    private static void checkDownRight(int num,int row,int col,int moves,int[][]board,LinkedList<Square> squares){
        int newCol = col+1;
        int newRow = row+2;
        
        if(isValid(newRow,newCol)){
            int position = board[newRow][newCol];
            int numMoves = moves+1;
            Square addition = new Square(position,numMoves);
            squares.add(addition);
        }
    }
    
    private static boolean isValid(int row, int col){
        if(row >= 0 && row <= 7 && col >= 0 && col <=7){
            return true;
        }else{
            return false;
        }
    }
    
    private static int getRow(int position,int[][]board){
        
        for(int row = 0; row < 8; row++){
            for(int col = 0; col < 8; col++){
                if(board[row][col] == position){
                    return row;
                }
            }
        }
        return -1;
    }
    
    private static int getCol(int position,int[][]board){
        
        for(int row = 0; row < 8; row++){
            for(int col = 0; col < 8; col++){
                if(board[row][col] == position){
                    return col;
                }
            }
        }
        return -1;
    }
    
}

class Square{
    int numMoves;
    int id;
    
    public Square(int id, int numMoves){
        this.numMoves = numMoves;
        this.id = id;
    }
}
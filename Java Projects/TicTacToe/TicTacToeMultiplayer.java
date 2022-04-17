
/**
 * Write a description of TicTacToe here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class TicTacToeMultiplayer {
    
    static ArrayList<Integer> playerPositions1 = new ArrayList<Integer>();
    static ArrayList<Integer> playerPositions2 = new ArrayList<Integer>();
    static String[] names = {"topRow","midRow","botRow","leftCol","midCol","rightCol","negativeDiag","positiveDiag"};
    static boolean run = true;
    
    public static void main(String[] args){
        char[][] gameBoard = {{' ','|',' ','|',' '},
            {'-','+','-','+','-'},
            {' ','|',' ','|',' ',},
            {'-','+','-','+','-'},
            {' ','|',' ','|',' '}};
            
        printGameBoard(gameBoard);
        System.out.println("Enter your placement 1-9");
        String result;
        a:while(run){
            System.out.println("Player1 turn");
            Scanner input = new Scanner(System.in);
            int playerPos1 = input.nextInt();
            while(playerPositions1.contains(playerPos1) || playerPositions2.contains(playerPos1)){
                System.out.println("positon taken! Enter a correct Position");
                playerPos1 = input.nextInt();
            }
            while(playerPos1 < 1 || playerPos1 > 9){
                System.out.println("Player 1 enter a position between 1-9 that is not taken");
                playerPos1 = input.nextInt();
            }
            placePiece(gameBoard, playerPos1, "player1");
            printGameBoard(gameBoard);
            
            //checking for winner after user plays
            result = checkWinner();
            System.out.println(result);
            if(!result.isEmpty()){
                break a;
            }
            
            System.out.println("Player2 turn");
            Random rand = new Random();
            int playerPos2 = input.nextInt();
            while(playerPositions1.contains(playerPos2) || playerPositions2.contains(playerPos2)){
                System.out.println("Enter a position between 1-9 that is not taken");
                playerPos2 = input.nextInt();
            }
            while(playerPos2 < 1 || playerPos2 > 9){
                System.out.println("Player 2 enter a position between 1-9 that is not taken");
                playerPos1 = input.nextInt();
            }
            placePiece(gameBoard, playerPos2, "player2");
            printGameBoard(gameBoard); //try doing this later with multiplayer kocha instead of play aginst CPU
            
            //checking for winner after CPU plays.
            result = checkWinner();
            System.out.println(result);
            if(!result.isEmpty()){
                break a;
            }
        }
        
    }
    
    public static void printGameBoard(char[][] gameBoard){
        for(char[] row : gameBoard){
            for(char c: row){
                System.out.print(c);
            }
            System.out.println();
        }
    }
    
    public static void placePiece(char[][] gameBoard, int pos, String user){
        
        char symbol = ' ';
        if(user.equals("player1")){
            symbol = 'X';
            playerPositions1.add(pos);
        }else if(user.equals("player2")){
            symbol = 'O';
            playerPositions2.add(pos);
        }
        
        switch(pos){
            case 1:
                gameBoard[0][0] = symbol;
                break;
            case 2:
                gameBoard[0][2] = symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][4] = symbol;
                break;
            case 7:
                gameBoard[4][0] = symbol;
                break;
            case 8:
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;
                break;
            default:
                break;
        }
    }
    
    public static String checkWinner(){
        List topRow = Arrays.asList(1,2,3);
        List midRow = Arrays.asList(4,5,6);
        List botRow = Arrays.asList(7,8,9);
        
        List leftCol = Arrays.asList(1,4,7);
        List midCol = Arrays.asList(2,5,8);
        List rightCol = Arrays.asList(3,6,9);
        
        List negativeDiag = Arrays.asList(1,5,9);
        List positiveDiag = Arrays.asList(7,5,3);
        
        ArrayList<List> winning = new ArrayList<List>();
        winning.add(topRow);
        winning.add(midRow);
        winning.add(botRow);
        winning.add(leftCol);
        winning.add(midCol);
        winning.add(rightCol);
        winning.add(negativeDiag);
        winning.add(positiveDiag);
        
        for(List l : winning){
            if(playerPositions1.containsAll(l)){
                String x = "Congratulations Player1 won! You won with ";
                String y = getDiagnosis(winning.indexOf(l));
                return x+y;
            }else if(playerPositions2.containsAll(l)){
                String x = "Congratulations Player2 won! You won with ";
                String y = getDiagnosis(winning.indexOf(l));
                return x+y;
            }
        }
        if(playerPositions1.size() + playerPositions2.size() == 9){
            return "It is a tie try again";
        }
        return "";
    }
    
    public static String getDiagnosis(int pos){
        return names[pos];
    }
    
}


/**
 * Write a description of TicTacToe here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class TicTacToeCpu {
    
    static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();
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
            Scanner input = new Scanner(System.in);
            int playerPos = input.nextInt();
            while(playerPositions.contains(playerPos) || cpuPositions.contains(playerPos)){
                System.out.println("positon taken! Enter a correct Position");
                playerPos = input.nextInt();
            }
            while(playerPos < 1 || playerPos > 9){
                System.out.println("Enter a position between 1-9 that is not taken");
                playerPos = input.nextInt();
            }
            placePiece(gameBoard, playerPos, "player");
            printGameBoard(gameBoard);
            
            //checking for winner after user plays
            result = checkWinner();
            System.out.println(result);
            if(!result.isEmpty()){
                break a;
            }
            
            Random rand = new Random();
            int cpuPos = rand.nextInt(9)+1;
            while(playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)){
                cpuPos = rand.nextInt(9)+1;
            }
            placePiece(gameBoard, cpuPos, "cpu");
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
        if(user.equals("player")){
            symbol = 'X';
            playerPositions.add(pos);
        }else if(user.equals("cpu")){
            symbol = 'O';
            cpuPositions.add(pos);
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
            if(playerPositions.containsAll(l)){
                String x = "Congratulations you won! You won with ";
                String y = getDiagnosis(winning.indexOf(l));
                return x+y;
            }else if(cpuPositions.containsAll(l)){
                String x = "Sorry you lost CPU wins! CPU won with ";
                String y = getDiagnosis(winning.indexOf(l));
                return x+y;
            }
        }
        if(playerPositions.size() + cpuPositions.size() == 9){
            return "It is a tie try again";
        }
        return "";
    }
    
    public static String getDiagnosis(int pos){
        return names[pos];
    }
    
}

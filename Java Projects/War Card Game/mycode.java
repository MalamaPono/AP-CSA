import java.util.ArrayList;
import java.util.*;

/*
IF I WANT TO AND HAVE MORE OUTPUT SPACE ON THE COUNSOL, I CAN ADD A COUNTER FOR THE AMOUNT 
OF WAR TIMES AND THE AMOUNT OF REGULAR COMPARISONS. THIS CAN BE DONE WITH A SIMPLE VARIABLE COUNTER
THAT IS INCREMENTED EACH TIME, RIGHT BEFORE ONE OF OF THE TWO SITUATIONS HAPPENS. THIS IS IN ROB
SCHULZ'S WAR CODE IF I WANT TO ADD IT.
*/

public class MyClass {
    public static void main(String args[]) {
        
        //create a deck of cards having 5 suis (ech with 13 values)
        String[] suits = {"Red", "Green", "Blue", "Yellow", "Purple"};
        ArrayList<Card> deck = new ArrayList<Card>();
        for(String s :suits){
            for(int value = 0; value<= 13; value++){
                deck.add(new Card(s,value));
            }
        }
        //print out deck
        System.out.println("Display new Deck");
        displayDeck(deck);
        
        //shuffle and print out shuffled deck
        System.out.println("\n This is Shufled Deck");
        perfectShuffle(deck);
        regularShuffle(deck);
        
        displayDeck(deck);
        
        //dealing out cards to 2 players
        ArrayList<Card> p1Hand = new ArrayList<Card>();
        ArrayList<Card> p2Hand = new ArrayList<Card>();
        while(deck.size() > 0){
            p1Hand.add(deck.remove(0));
            p2Hand.add(deck.remove(0));
        }
        
        System.out.println("\nThis is Player 1's hand: ");
        displayDeck(p1Hand);
        System.out.println("\nThis is Player 2's hand: ");
        displayDeck(p2Hand);
        //sorting each player's hand, will be commented out in this program becuase in war, each player's hand is not sorted, it is random.
        /*
        selectionSort(p1Hand);
        selectionSort(p2Hand);
        System.out.println("\n This is Player 1's Sorted Hand");
        displayDeck(p1Hand);
        System.out.println("\n This is Player 2's Sorted Hand");
        displayDeck(p2Hand);
        */
        
        int numCardPlayer1 = 0;
        int numCardPlayer2 = 0;
        while(p1Hand.size() > 0 && p2Hand.size() > 0){
            Card p1Card = p1Hand.get(0);
            Card p2Card = p2Hand.get(0);
            System.out.println("Player 1 shows a: " + p1Card.getValue() + " of " + p1Card.getSuit());
            System.out.println("Player 2 shows a: " + p2Card.getValue() + " of " + p2Card.getSuit());
            A:if(p1Card.getValue() > p2Card.getValue()){
                System.out.println("Player 1 won this one");
                p1Hand.remove(p1Card);
                p2Hand.remove(p2Card);
                p1Hand.add(p1Card);
                p1Hand.add(p2Card);
            }else if(p2Card.getValue() > p1Card.getValue()){
                System.out.println("Player 2 won this one");
                p2Hand.remove(p2Card);
                p1Hand.remove(p1Card);
                p2Hand.add(p1Card);
                p2Hand.add(p2Card);
                }else{
                System.out.println("***War***");
                if(p1Hand.size() < 4){
                    for(int i = 0; i < p1Hand.size(); i++){
                        p2Hand.add(p1Hand.remove(0));
                        i-=1;
                    }
                    break A;
                }else if(p2Hand.size() < 4){
                    for(int i = 0; i < p2Hand.size(); i++){
                        p1Hand.add(p2Hand.remove(0));
                        i-=1;
                    }
                    break A;
                }
                ArrayList<Card> temp = new ArrayList<Card>(6);
                if(p1Hand.size() > 4 && p2Hand.size() > 4){
                temp.add(p1Hand.remove(0));
                temp.add(p2Hand.remove(0));
                temp.add(p1Hand.remove(0));
                temp.add(p2Hand.remove(0));
                temp.add(p1Hand.remove(0));
                temp.add(p2Hand.remove(0));
                
                if(p1Hand.get(0).getValue() > p2Hand.get(0).getValue()){
                    System.out.println("Player 1 wins war");
                    for(int i = 0; i < temp.size(); i++){
                        p1Hand.add(temp.remove(0));
                        i-=1;
                    }
                    p1Hand.add(p1Hand.remove(0));
                    p1Hand.add(p2Hand.remove(0));
                }else if(p1Hand.get(0).getValue() < p2Hand.get(0).getValue()){
                    System.out.println("Player 2 wins war");
                    for(int i = 0; i < temp.size(); i++){
                        p2Hand.add(temp.remove(0));
                        i-=1; //either do this with temp.size() or just replace this and temp.size() with just 6 becuase we know temp.size() is always 6 and we need to take out index 0 of temp 6 times and add it all to either player 1 or player 2. 
                    }
                    p2Hand.add(p1Hand.remove(0));
                    p2Hand.add(p2Hand.remove(0));
                }else{
                    int guess = (int)(Math.random() *10 + 1);
                    if(guess > 5){
                    System.out.println("Double war picking random to win all");
                    System.out.println("Player 1 wins war");
                    for(int i = 0; i < temp.size(); i++){
                        p1Hand.add(temp.remove(0));
                        i-=1;
                    }
                    p1Hand.add(p1Hand.remove(0));
                    p1Hand.add(p2Hand.remove(0));
                    }else{
                    System.out.println("Double war picking random to win all");
                    System.out.println("Player 2 wins war");
                    for(int i = 0; i < temp.size(); i++){
                        p2Hand.add(temp.remove(0));
                        i-=1;
                    }
                    p2Hand.add(p1Hand.remove(0));
                    p2Hand.add(p2Hand.remove(0));
                    }
                }
             }
            }
            numCardPlayer1 = p1Hand.size();
            numCardPlayer2 = p2Hand.size();
            System.out.println("\nPlayer 1 has " + numCardPlayer1 + " cards");
            System.out.println("Player 2 has " + numCardPlayer2 + " cards");
            
        } 
        if(numCardPlayer1 == 0){
            System.out.println("Player 2 has won");
        }else{
            System.out.println("Player 1 has won");
        }
    }
    
    
     public static void displayDeck(ArrayList<Card> d){
        for(int i = 0; i < d.size(); i++){
            System.out.println(d.get(i));
        }
    }
    
    public static void selectionSort(ArrayList<Card> d){
        for(int i = 0; i < d.size()-1; i++){ //this one is minus 1 becuase the j for loop checks the card after it
            int min = d.get(i).getValue();
            int minLoc = i;
            for(int j = i+1; j < d.size(); j++){
                if(d.get(j).getValue() < min){
                    min = d.get(j).getValue();
                    minLoc = j;
                }
            }
            //swaping cards
            Card temp = d.get(i);
            d.set(i, d.get(minLoc));
            d.set(minLoc, temp);
            
        }
    }
    
    public static void regularShuffle(ArrayList<Card> x){
        for(int i = 0; i < 1000000; i++){
            x.add(0, x.remove((int)(Math.random() * x.size())));
        }
    }
    
    public static void perfectShuffle(ArrayList<Card> x){ //this shuffle is like when a dealer splits the deck into half and perflectly swaps one by one in the birdge like shuffle. This is because the top of the deck is one half of the deck and the other half of the deck is starting at the halfway point of the deck. 
        for(int i = 0; i < 10000; i++){
            ArrayList<Card> tempDeck = new ArrayList<Card>();
            while(x.size() > 0){
                if(x.size() % 2 != 0){
                    tempDeck.add(x.remove(0));
            }else{
                tempDeck.add(x.remove(x.size()/2));
            }
        }
        while(tempDeck.size() > 0){ //taking all of the tempoprary deck putting it back into your deck which is now shuffled
            x.add(tempDeck.remove(0));
        }
    }
 }
 }
import java.util.*;
public class MyClass {
    public static void main(String args[]) {
        
        //adding mutiple suits in a deck
        ArrayList<Card> deck = new ArrayList<Card>();
        String[] suits = {"Dog", "Cat", "Hippo", "Turtle"};
        for(String s: suits){
            for(int i = 1; i <=14; i++){
                deck.add(new Card(s, i));
            }
        }
        
    //shuffle then display deck
    regularShuffle(deck);
    System.out.println("Shufled deck");
    displayDeck(deck);
    
    //deal out cards
    ArrayList<Card> p1Hand = new ArrayList<Card>();
    ArrayList<Card> p2Hand = new ArrayList<Card>();
    ArrayList<Card> p3Hand = new ArrayList<Card>();
    ArrayList<Card> p4Hand = new ArrayList<Card>();
    
    for(int counter = 0; counter < 28; counter++){
        if(deck.size()%4 == 0){
            p1Hand.add(deck.remove(0));
        }else if(deck.size()%4 == 1){
            p2Hand.add(deck.remove(0));
        }else if(deck.size()%4 == 2){
            p3Hand.add(deck.remove(0));
        }else{
            p4Hand.add(deck.remove(0));
        }
    }
    /*
    This is the code on how I would deal cards. The other way is the more complex Rob Schultz way.
    int counter = 0;
    while(counter < 28){
        p1Hand.add(deck.remove(0));
        counter++;
        p2Hand.add(deck.remove(0));
        counter++;
        p3Hand.add(deck.remove(0));
        counter++;
        p4Hand.add(deck.remove(0));
        counter++;
        
    }
    */
    
    //display each player's hand
    System.out.println("\nP1 Hand");
    displayDeck(p1Hand);
    System.out.println("\nP2 Hand");
    displayDeck(p2Hand);
    System.out.println("\nP3 Hand");
    displayDeck(p3Hand);
    System.out.println("\nP4 Hand");
    displayDeck(p4Hand);
    
    ArrayList<Card> discardPile = new ArrayList<Card>();
    discardPile.add(deck.remove(0));
    boolean gameOver = false;
    int currentPlayer = 1;
    ArrayList<Card> currentHand = p1Hand; //currentHand will refer to the hand in play. So if something is added to the current hand, it will also be added to whatever player's hand it is equal to.

    while(gameOver == false){
        if(currentPlayer == 1){
            currentHand = p1Hand;
        }else if(currentPlayer == 2){
            currentHand = p2Hand;
        }else if(currentPlayer == 3){
            currentHand = p3Hand;
        }else{
            currentHand = p4Hand;
        }
        Card cardToMatch = discardPile.get(0);
        System.out.println("Card to match: " + cardToMatch);
        int loc = findCardLocation(currentHand, cardToMatch);
        if(loc > -1){
            System.out.println("Player " + currentPlayer + " played a " + currentHand.get(loc));
            discardPile.add(0, currentHand.remove(loc)); //adding the removed card from the players hand to the top of discard pile.
            if(currentHand.size() == 0){
                System.out.println("YEEEET!!! Player " + currentPlayer + " is the WINNER!!!");
                gameOver = true;
            }
        }else{
            boolean draw = true;
            while(draw == true){
                if(deck.size() == 0){
                    while(discardPile.size() > 1){
                        deck.add(discardPile.remove(1));
                    }
                }else{
                    Card drawnCard = deck.remove(0);
                    System.out.println("Player " + currentPlayer + " drew a " + drawnCard);
                    if(drawnCard.getValue() == cardToMatch.getValue() || drawnCard.getSuit().equals(cardToMatch.getSuit()) || drawnCard.getValue() == 8){
                        discardPile.add(0, drawnCard);
                        System.out.println("Player " + currentPlayer + " played a " + drawnCard);
                        draw = false;
                    }else{
                    currentHand.add(drawnCard);
                }
            }
        }
    }
        
    System.out.println("End of turn for player " + currentPlayer + ": " + currentHand.size() + " cards remaining");
    currentPlayer++;
    if(currentPlayer > 4){
        currentPlayer = 1;
         }
    }   //end of while loop for entire game
}
    public static void displayDeck(ArrayList<Card> d){
        for(int i = 0; i < d.size(); i++){
            System.out.println(d.get(i));
        }
    }
    
    public static void selectionSort(ArrayList<Card> d){
        for(int i = 0; i < d.size(); i++){ //this one is minus 1 becuase the j for loop checks the card after it
            int min = d.get(i).getValue();
            int minLoc = i;
            for(int j = i+1; j < d.size(); j++){
                if(d.get(j).getValue() < min){
                    min = d.get(j).getValue();
                    minLoc = j;
                }
            }
            //swaping cards
            Card temp = d.set(i, d.get(minLoc));
            d.set(minLoc, temp);
            
            
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
 
    public static int findCardLocation(ArrayList<Card> d, Card c){
        for(int i = 0; i < d.size(); i++){
            if(d.get(i).getValue() == c.getValue() || d.get(i).getValue() == 8 || d.get(i).getSuit() == c.getSuit()){
                return i;
            }
        }
        return -1;
    }
 
    //basic shuffling deck by taking random card in the deck and putting it on top
    public static void regularShuffle(ArrayList<Card> x){
        for(int i = 0; i < 1000000; i++){
            x.add(0, x.remove((int)(Math.random() * x.size())));
        }
    }
}
  
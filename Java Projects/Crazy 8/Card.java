public class Card{
    
    private String suit;
    private int value;
    
    //set suit and value of card
    public Card(String s, int v){
        suit = s;
        value = v;
    }
    
    //get the value of the card
    public int getValue(){
        return value;
    }
    
    //get the suit of the card
    public String getSuit() {
        return suit;
    }
    
    public String toString(){
        return value + " of " + suit + "s";
    }
    
}
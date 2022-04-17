import java.util.*;

public class MyClass {
    public static void main(String args[]) {
      
      Scanner kbReader = new Scanner(System.in);
     
      String answer = "hello"; //this gets all the letters in alphabet
      //this is the phrase right here that is the answer.
      
      WofF game = new WofF(answer);
      game.displayPhrase();
      
      String userGuess = "";
      while(!userGuess.equals("stop")){
          System.out.print("please enter a single letter: ");
          userGuess = kbReader.nextLine();
          if(!userGuess.equals("stop")){
              userGuess = userGuess.substring(0,1);
              game.guessLetter(userGuess);
          }
      }
      System.out.println("what is your guess?");
      String finalAnswer = kbReader.nextLine();
      if(finalAnswer.equals(answer)){
          System.out.println("\n" + "yay you got it correct the answer was: " + answer);
          System.out.println("thank you for playing");
      }else{
          System.out.println("Sorry that was incorect this was the answer: " + answer);
      }
     
      
    }
}
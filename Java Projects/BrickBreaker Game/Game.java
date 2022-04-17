
/**
 * Write a description of Game here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import javax.swing.JFrame;

public class Game extends JFrame{
    public static void main(String args[]) {
        
        JFrame obj = new JFrame();
        Gameplay gamePlay = new Gameplay(); //creating an instace of Gameplay class which is a panel
        obj.setBounds(10, 10, 700, 600);
        obj.setTitle("Breakout Ball");
        obj.setResizable(false);
        obj.setVisible(true);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.add(gamePlay); //adding a panel onto the larger JFrame which is like the entire window
        
    }
}

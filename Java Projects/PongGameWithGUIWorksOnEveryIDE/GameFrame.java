
/**
 * Write a description of GameFrame here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
public class GameFrame extends JFrame{
    
    GamePanel panel;
    
    public GameFrame(){
        panel = new GamePanel();
        this.add(panel);
        this.setTitle("Pong Game");
        this.setResizable(false);
        this.setBackground(Color.black);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack(); //adjusts size to fit panel nicely
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

}

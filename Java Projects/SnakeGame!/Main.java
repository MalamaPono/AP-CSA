
/**
 * Write a description of Main here.
 * 
 * @author (your name) 
  * @version (a version number or a date)
 */
import java.awt.*;
import javax.swing.*;


public class Main{
    
    public static void main(String[] args){
        JFrame obj = new JFrame();
        Gameplay gameplay = new Gameplay();
        
        obj.setBounds(10,10,905,700);
        obj.setBackground(Color.DARK_GRAY);
        obj.setResizable(false);
        obj.setVisible(true);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.add(gameplay);
    }

}
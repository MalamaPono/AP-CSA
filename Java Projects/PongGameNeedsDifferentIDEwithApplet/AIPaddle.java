
/**
 * Write a description of HumanPaddle here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.awt.*;

//as this is right now the AI is unbeatable and will never miss the ball, so you will always lose or the game will go forever
public class AIPaddle implements Paddle { //instead of using AI you can also adapt this to make a new and different PaddleClass with different keys on the left side of the keyboard so it can become a mutiplayer game. The other paddle will be exact same just different cordinates.
    
    double y;
    double yVel;
    boolean upAccel;
    boolean downAccel;
    int player;
    int x;
    final double GRAVITY = 0.94;
    Ball b1;
    
    public AIPaddle(int player, Ball b){
        upAccel = false;
        b1 = b;
        downAccel = false;
        y = 210;
        yVel = 0;
        if(player == 1){
            x = 20;
        }else{
            x = 660;
        }
    }
    
    public void draw(Graphics g){
        g.setColor(Color.white);
        g.fillRect(x,(int)y,20,80);
    }
    
    public void move(){
        y = b1.getY() - 40;
        
        if(y<0){
            y = 0;
        }if(y>420){
            y = 420;
        }
    }
    
    public int getY(){
        return (int)y;
    }

}

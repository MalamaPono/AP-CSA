import javax.swing.JPanel;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import javax.swing.Timer;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.*;
import java.awt.Rectangle;
import java.awt.Graphics2D;

public class Gameplay extends JPanel implements KeyListener, ActionListener{
    private boolean play = false; //will be set to true when game needs to start
    private int score = 0; //score absed on the tiles you break
    
    private int totalBricks = 21; //amount of bricks on screen that you need to break
    
    private Timer timer; //timer object
    private int delay = 8; //delay on how the timer reduces
   
    private int playerX = 310; //starting position of slider
    
    private int ballposX = 120; //starting x position of the ball
    private int ballposY = 350; //starting y position of the ball
    private int ballXdir = -1; //direction of ball movement for x axis
    private int ballYdir = -2; //direction of ball movement for y axis

    private MapGenerator map;
    
    public Gameplay(){
        map = new MapGenerator(3,7);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
    }
    
    public void paint(Graphics g){
        //background
        g.setColor(Color.BLACK);
        g.fillRect(1, 1, 692, 592); //(starting x position, startin y position, length, height)
        
        //drawing map(bricks)
        map.draw((Graphics2D)g);
        
        //boarders
        g.setColor(Color.YELLOW);
        g.fillRect(0,0,3,592); //left boarder
        g.fillRect(0,0,692,3); //top boarder
        g.fillRect(691,0,3,592); //right boarder
        //no boarder on bottom becuase that is where ball will fall and end game
    
        //scores
        g.setColor(Color.WHITE);
        g.setFont(new Font("serif", Font.BOLD, 25));
        g.drawString("" + score, 500, 30);
    
        //the paddle (place that ball bounces on before reaching void)
        g.setColor(Color.GREEN);
        g.fillRect(playerX, 550, 100, 8);
        
        //the ball
        g.setColor(Color.YELLOW);
        g.fillOval(ballposX, ballposY, 20, 20);
        
        if(totalBricks <= 0){
            play = false;
            ballXdir = 0;
            ballYdir = 0;
            g.setColor(Color.RED);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("You Won: " + score, 200, 300);
            g.setFont(new Font("serif", Font.BOLD, 20));
            g.drawString("Press Enter to Restart " + score, 250, 350);
        }
        
        if(ballposY > 570){
            play = false;
            ballXdir = 0;
            ballYdir = 0;
            g.setColor(Color.RED);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("Game Over, Score: " + score, 190, 300);
            g.setFont(new Font("serif", Font.BOLD, 20));
            g.drawString("Press Enter to Restart " + score, 250, 350);
        }
        
        g.dispose(); //will dispose of drawing all this string, only to be redrawn right after without user even knowing
    }
    
    public void actionPerformed(ActionEvent e){
        timer.start();
        if(play == true){
            if(new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX, 550, 100, 8))){ //this checks if ball intersects paddle
                ballYdir = -ballYdir; //moves ball when it hits the paddle
            }
            
            A: for(int i = 0; i < map.map.length; i++){ //map is the name of the object which is an instance of the MapGenerator class. The second map is the actual 2D map array that is a public variable from the map generator class.
                for(int j = 0; j<map.map[0].length; j++){
                    if(map.map[i][j] > 0){
                        int brickX = j*map.brickWidth + 80;
                        int brickY = i*map.brickHeight + 50;
                        int brickWidth = map.brickWidth;
                        int brickHeight = map.brickHeight;
                        
                        Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
                        Rectangle ballRect = new Rectangle(ballposX, ballposY, 20, 20);
                        Rectangle brickRect = rect;
                        
                        if(ballRect.intersects(brickRect)){
                            map.setBrickValue(0, i, j);
                            totalBricks--;
                            score += 5;
                            
                            if(ballposX + 19 <= brickRect.x || ballposX + 1 >= brickRect.x + brickRect.width){
                                ballXdir = -ballXdir;
                            }else{
                                ballYdir = -ballYdir;
                            }
                            break A;
                        }
                        
                    }
                }
            }
            ballposX += ballXdir; //moves ball position based on the Xdirection
            ballposY += ballYdir; //moves ball position based on the Ydirection
            if(ballposX < 0){ //this is when it hits left boarder, it will move the ball
                ballXdir = -ballXdir; //direction of ball movement is continues and will repeat, until the direction is altered by hitting a different boarder, or the paddle
            }
            if(ballposY < 0){ //this is when it hits top boarder, it will move the ball
                ballYdir = -ballYdir;
            }
            if(ballposX > 670){ //this is when it hits the right boarder, it will move the ball
                ballXdir = -ballXdir;
            }
        }
        repaint(); //recall paint method and will draw everything after event is performed, this will move the objects every time the paddle is move, ball is bounced, brick is removed, or anything else.
    }
    
    public void keyTyped(KeyEvent e){} //these are methods not used in this program, but needed to be overidden in order for us to implement ActionListener and KeyListener
    public void keyReleased(KeyEvent e){}
    
    
    public void keyPressed(KeyEvent e){ //waits and identifies left arrow or right arrow to be clicked. This moves paddle.
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){ //is user clicks right key
            if(playerX >= 600){ //makes sure they don't go out of bounds, so this is the max right the paddle can go on the window
                playerX = 600;
            }else{
                moveRight();
            }
            
        }if(e.getKeyCode() == KeyEvent.VK_LEFT){
            if(playerX < 10){ //makes sure they don't go out of bounds, so this is the max left the paddle can go on the window
                playerX = 10;
            }else{
                moveLeft();
            }
        }
        
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            if(play == false){
                play = true;
                ballposX = 120;
                ballposY = 350;
                ballXdir = -1;
                ballYdir = -2;
                playerX = 310;
                score = 0;
                totalBricks = 21;
                map = new MapGenerator(3,7);
                
                repaint();
            }
        }
    }
    
    public void moveRight(){
        play = true;
        playerX+=20; //this moves the x 20 spaces (right) every time right arrow is clicked
    }
   
    public void moveLeft(){ 
        play = true;
        playerX-=20; //this moves the x -20 spaces (left) every time left arrow is clicked
    }
    
}

/**
 * Write a description of MapGenerator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.BasicStroke;


public class MapGenerator{
    
    public int map[][]; //this contains all the bricks
    public int brickWidth;
    public int brickHeight;
    
    public MapGenerator(int row, int col){
        map = new int[row][col];
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[0].length; j++){
                map[i][j] = 1; //setting each brick to 1 will show that it hasn't been broken yet
            }
        }
        
        brickWidth = 540/col; //makes sure the bricks always take up a certain amount of space. The less bricks the bigger each brick is, the more bricks the smaller each brick is. 
        brickHeight = 150/row;
    }
    
    public void draw(Graphics2D g){ //loops through every brick in the map and if it is still 1, then draw it.
         for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[0].length; j++){
                if(map[i][j] > 0){ //draws the bricks as long as they have the value 1
                    g.setColor(Color.WHITE);
                    g.fillRect(j*brickWidth+80, i*brickHeight + 50, brickWidth, brickHeight);
                
                
                    //this draws the boarders between each individual brick in our 2D brick map
                    g.setStroke(new BasicStroke(3));
                    g.setColor(Color.BLACK);
                    g.drawRect(j*brickWidth+80, i*brickHeight + 50, brickWidth, brickHeight);
                }
                
            }
            
         }
    }
    
    public void setBrickValue(int value, int row, int col){ //going to change the value of each brick (index[row][col] in map 2Darray) to 0, so it won't be drawn anymore. This method will be called when the ball intersects a certain brick which will be coded as an event.
        map[row][col] = value;
    }
    
}

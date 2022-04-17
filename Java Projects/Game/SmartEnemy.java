/*

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.*;
import java.awt.image.BufferedImage;

public class SmartEnemy extends GameObject{

    private Handler handler;
    private GameObject player;
    private BufferedImage player_image;

    public SmartEnemy(int x, int y, ID id, Handler handler){
        super(x, y, id);
        this.handler = handler;
        
    }
    
    public Rectangle getBounds(){
        return new Rectangle(x, y, 16, 16);
    }
    
    public void tick(){
        x += velX;
        y += velY;
        
        //trouble here
        int diffX = x - player.getX() - 8;
        float diffY = y - player.getY() - 8;
        float distance = (float) Math.sqrt((x-player.getX())*x-player.getX()) + (y-player.getY())*(y-player.getY());
        
        velX = (float)((-1.0/distance) *diffX);
        velY = (float)((-1.0/distance) *diffY);
        
        if(y <= 0 || y>= Game.HEIGHT - 32){
            velY *= -1; //once it hits top or bottom it will reverse velocity and bounce the other way
        }
        if(x<= 0 || x>= Game.WIDTH -16){
            velX *= -1;
        }
        handler.addObject(new Trail(x, y, ID.Trail, Color.green, 16, 16, 0.02f, handler));
    }
    
    public void render(Graphics g){
        g.setColor(Color.green);
        g.fillRect(x, y, 16, 16);
    }
}
*/
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;

public class Trail extends GameObject{
    
    private float alpha = 1;
    private float life;
    
    private Handler handler;
    private Color color;
    private int width;
    private int height;
    
    
    //life is between 0.001 and 0.1
    
    public Trail(int x, int y, ID id, Color color, int width, int height, float life, Handler handler){
        super(x, y, id);
        this.color = color;
        this.height = height;
        this.width = width;
        this.handler = handler;
        this.life = life;
    }
    
    public void tick(){
        if(alpha > life){
            alpha -= (life - 0.0001f);
        }else{
            handler.removeObject(this);
        }
    }
    
    public void render(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setComposite(makeTransparent(alpha));
        
        g.setColor(color);
        g.fillRect(x, y, width, height);
        
        g2d.setComposite(makeTransparent(1));
    }
    
    private AlphaComposite makeTransparent(float alpha){
        int type = AlphaComposite.SRC_OVER;
        return(AlphaComposite.getInstance(type, alpha));
    }
    
    public Rectangle getBounds(){
        return null; //no need for bounds for this object
    }
}
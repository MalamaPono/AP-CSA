import java.awt.Rectangle;
import java.awt.Color;
import java.awt.*;

public abstract class GameObject{ //all game objects inherit from this class for certain functions and details that is common to all. For example x and y locaion.
    
//protected variables mean that it can be used and inherited by any class that extends this class without beind extended publically to any other class
    protected int x;
    protected int y;
    protected ID id;
    protected int velX;
    protected int velY;
    
//Since parameter and variable x in our class both have the same name, this keyword is used to say that the x is refering to the instance varible in the class. If not it would just set the parameter to the parameter. Th
    public GameObject(int x, int y, ID id){
        this.x = x; 
        this.y = y;
        this.id = id;
    }
    
    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract Rectangle getBounds();
    
    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public void setId(ID id){
        this.id = id;
    }
    public ID getId(){
        return id;
    }
    public void setVelX(int velX){
        this.velX = velX;
    }
    public void setVelY(int velY){
        this.velY = velY;
    }
    public int getVelX(){
        return velX;
    }
    public int getVelY(){
        return velY;
    }
    
}

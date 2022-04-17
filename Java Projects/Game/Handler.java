import java.util.*;
import java.awt.*;

public class Handler{ //loop thorugh all objects in game and individually update them and run certain code when event happens. Also renders it on screen.
    
    LinkedList<GameObject> object = new LinkedList<GameObject>();
    
    public int spd = 5;
    
    public void tick(){ //updates all objects
        for(int i = 0; i < object.size(); i++){
            GameObject tempObject = object.get(i);
            tempObject.tick();
        }
    }
    
    public void render(Graphics g){ //renders and displays graphics of all objects
        for(int i = 0; i < object.size(); i++){
            GameObject tempObject = object.get(i);
            tempObject.render(g);
        }
    }
    
    public void clearEnemys(){
        for(int i = 0; i < object.size(); i++){
            GameObject tempObject = object.get(i);
            
            if(tempObject.getId() == ID.Player){
                object.clear();
                if(Game.gameState != Game.STATE.End){
                addObject(new Player(tempObject.getX(), tempObject.getY(), ID.Player, this));
                }
            }
        }
    }
    
    public void addObject(GameObject object){
        this.object.add(object);
    }
    
    public void removeObject(GameObject object){
        this.object.remove(object);
    }
}
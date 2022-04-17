import java.awt.event.MouseAdapter;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.util.Random;
import java.awt.*;

public class Menu extends MouseAdapter{
    
    private Game game;
    private Handler handler;
    private Random r = new Random();
    private HUD hud;
    
    public Menu(Game game, Handler handler, HUD hud){
        this.game = game;
        this.hud = hud;
        this.handler = handler;
    }
    
    public void mousePressed(MouseEvent e){
        int mx = e.getX(); //these 2 lines get the x and y values when you click your mouse and store them in variables mx and my
        int my = e.getY();
        
        if(game.gameState == Game.STATE.Menu){

        //play button
            if(mouseOver(mx, my, 210, 150, 200, 64)){
                game.gameState = Game.STATE.Select;
                
                return;
            }
        //help button
            if(mouseOver(mx, my, 210, 250, 200, 64)){
                game.gameState = Game.STATE.Help;
            }
            
        //quit button
            if(mouseOver(mx, my, 210, 350, 200, 64)){
                System.exit(1);
            }
        }
        
        if(game.gameState == Game.STATE.Select){

        //normal button
            if(mouseOver(mx, my, 210, 150, 200, 64)){
                game.gameState = Game.STATE.Game;
                handler.addObject(new Player(Game.WIDTH/2-12, Game.HEIGHT/2-32, ID.Player, handler)); //middle of screen
                handler.clearEnemys();  
                handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH-50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
                
                game.diff = 0;
            }
            
        //hard button
            if(mouseOver(mx, my, 210, 250, 200, 64)){
                game.gameState = Game.STATE.Game;
                handler.addObject(new Player(Game.WIDTH/2-12, Game.HEIGHT/2-32, ID.Player, handler)); //middle of screen
                handler.clearEnemys();  
                handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH-50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
                
                game.diff = 1;
            }
            
        //back button
            if(mouseOver(mx, my, 210, 350, 200, 64)){
                game.gameState = Game.STATE.Menu;
                return;
            }
        }
        
        //back button for help
        if(game.gameState == Game.STATE.Help){
            if(mouseOver(mx, my, 210, 350, 200, 64)){
                game.gameState = Game.STATE.Menu;
                return;
            }
        }
        
        //try again button at end screen
        if(game.gameState == Game.STATE.End){
            if(mouseOver(mx, my, 210, 350, 200, 64)){
                game.gameState = Game.STATE.Menu;
                hud.setLevel(1);
                hud.setScore(0);
            }
        }
    }
    
    public void mouseReleased(MouseEvent e){
        
    }
    
    private boolean mouseOver(int mx, int my, int x, int y, int width, int height){
        if(mx > x && mx < x + width){
            if(my > y && my < y + height){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    public void render(Graphics g){
        if(game.gameState == Game.STATE.Menu){
            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 30);
        
            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("Wave", 248, 70);
        
            g.setFont(fnt2);
            g.drawRect(210, 150, 288, 64);
            g.drawString("Play", 270, 190);
        
            g.drawRect(210, 250, 288, 64);
            g.drawString("Help", 270, 290);
        
            g.drawRect(210, 350, 288, 64);
            g.drawString("Quit", 270, 390);
        }else if(game.gameState == Game.STATE.Help){
            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 30);
            Font fnt3 = new Font("arial", 1, 20);
        
            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("Help", 248, 70);
            
            g.setFont(fnt3);
            g.drawString("Use WASD keys to move player and dodge enemies", 50, 200);
            
            g.setFont(fnt2);
            g.drawRect(210, 350, 288, 64);
            g.drawString("Back", 270, 390);
        }else if(game.gameState == Game.STATE.End){
            //end screen
            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 30);
            Font fnt3 = new Font("arial", 1, 20);
        
            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("Game over", 180, 70);
            
            g.setFont(fnt3);
            g.drawString("You lost with a score of " + hud.getScore(), 175, 200);
            
            g.setFont(fnt2);
            g.drawRect(210, 350, 288, 64);
            g.drawString("Try Again", 245, 390);
        }else if(game.gameState == Game.STATE.Select){
            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 30);
        
            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("SELECT DIFFICULTY", 140, 70);
        
            g.setFont(fnt2);
            g.drawRect(210, 150, 288, 64);
            g.drawString("Normal", 270, 190);
        
            g.drawRect(210, 250, 288, 64);
            g.drawString("Hard", 270, 290);
        
            g.drawRect(210, 350, 288, 64);
            g.drawString("Back", 270, 390);
        }
}
    
    public void tick(){
        
    }
    
    
}
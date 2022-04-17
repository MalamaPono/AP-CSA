import java.util.*;

public class Spawn{
    
    private Handler handler;
    private HUD hud;
    private Random r = new Random();
    private Game game;
    
    private int scoreKeep = 0;
    
    public Spawn(Handler handler, HUD hud, Game game){
        this.handler = handler;
        this.hud = hud;
        this.game = game;
    }
    
    public void tick(){
        scoreKeep++;
        
        if(scoreKeep >= 500){
            scoreKeep = 0;
            hud.setLevel(hud.getLevel() + 1);
            
            
            //if you want to clear certain enemys only kocha, you must loop through handler LinkedList and remove the certain enemys you want and how much of them you want to remove. To find the enemy you want to remove, just locate the ID!!!
            if(game.diff == 0){
                if(hud.getLevel() == 2){
                handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH-50), r.nextInt(Game.HEIGHT-50), ID.BasicEnemy, handler));
                }else if(hud.getLevel() == 3){
                handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH-50), r.nextInt(Game.HEIGHT-50), ID.BasicEnemy, handler));
                }else if(hud.getLevel() == 4){
                 handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH-50), r.nextInt(Game.HEIGHT-50), ID.FastEnemy, handler));
                }else if(hud.getLevel() == 5){
                 handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH-50), r.nextInt(Game.HEIGHT-50), ID.SmartEnemy, handler));
                }else if(hud.getLevel() == 6){
                handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH-50), r.nextInt(Game.HEIGHT-50), ID.FastEnemy, handler));
                }else if(hud.getLevel() == 7){
                handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH-50), r.nextInt(Game.HEIGHT-50), ID.FastEnemy, handler));
                }else if(hud.getLevel() == 10){
                handler.clearEnemys();
                handler.addObject(new EnemyBoss((Game.WIDTH/2)-48, -200, ID.EnemyBoss, handler, hud));
                }
                if(hud.getLevel() == 12){
                    handler.clearEnemys();
                    handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH-50), r.nextInt(Game.HEIGHT-50), ID.FastEnemy, handler));
                }
                if(hud.getLevel() == 13){
                    handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH-50), r.nextInt(Game.HEIGHT-50), ID.FastEnemy, handler));
                }
                if(hud.getLevel() == 14){
                    handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH-50), r.nextInt(Game.HEIGHT-50), ID.FastEnemy, handler));
                }
                if(hud.getLevel() == 15){ 
                    handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH-50), r.nextInt(Game.HEIGHT-50), ID.FastEnemy, handler));
                    handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH-50), r.nextInt(Game.HEIGHT-50), ID.FastEnemy, handler));
                    handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH-50), r.nextInt(Game.HEIGHT-50), ID.FastEnemy, handler));
                    handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH-50), r.nextInt(Game.HEIGHT-50), ID.FastEnemy, handler));
                }
                if(hud.getLevel() == 16){
                    handler.addObject(new EnemyBoss((Game.WIDTH/2)-48, -200, ID.EnemyBoss, handler, hud));
                }
            }else if(game.diff == 1){
                if(hud.getLevel() == 2){
                handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH-50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
                }else if(hud.getLevel() == 3){
                handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH-50), r.nextInt(Game.HEIGHT-50), ID.BasicEnemy, handler));
                }else if(hud.getLevel() == 4){
                 handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH-50), r.nextInt(Game.HEIGHT-50), ID.FastEnemy, handler));
                }else if(hud.getLevel() == 5){
                 handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH-50), r.nextInt(Game.HEIGHT-50), ID.SmartEnemy, handler));
                }else if(hud.getLevel() == 6){
                handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH-50), r.nextInt(Game.HEIGHT-50), ID.FastEnemy, handler));
                }else if(hud.getLevel() == 7){
                handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH-50), r.nextInt(Game.HEIGHT-50), ID.FastEnemy, handler));
                }else if(hud.getLevel() == 10){
                handler.clearEnemys();
                handler.addObject(new EnemyBoss((Game.WIDTH/2)-48, -120, ID.EnemyBoss, handler, hud));
                }
            }
            
        }
    }
}
import java.awt.Canvas;
import java.util.*;
import java.awt.*;
import java.awt.image.BufferStrategy;


public class Game extends Canvas implements Runnable{
    
    private static final long serialVersionUID = 1550691097823471818L;
    public static final int WIDTH = 640;
    public static final int HEIGHT = WIDTH / 12 * 9;
    private Thread thread;
    private boolean running = false;
    
    public static boolean paused = false;
    public int diff = 0;
    //0 is normal 
    //1 is hard
    
    private Random r;
    private Handler handler;
    private HUD hud;
    private Spawn spawner;
    private Menu menu;
    private Shop shop;
    
    public enum STATE{
        Menu,
        Select,
        Help,
        Shop,
        Game,
        End
    };
    
    public static STATE gameState = STATE.Menu;
    
    public Game(){
        
        handler = new Handler();
        hud = new HUD();
        shop = new Shop(handler, hud);
        menu = new Menu(this, handler, hud);
        this.addKeyListener(new KeyInput(handler, this));
        this.addMouseListener(menu);
        this.addMouseListener(shop);
        
        new Window(WIDTH, HEIGHT, "Letʻs Build a Game!", this); //this refers to this game we are passing in to window constructor
        
        spawner = new Spawn(handler, hud, this);
        r = new Random();
        
        if(gameState == STATE.Game){
            handler.addObject(new Player(WIDTH/2-12, HEIGHT/2-32, ID.Player, handler)); //middle of screen
            handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH-50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
        }else{
            for(int i = 0; i < 10; i++){
                handler.addObject(new MenuParticle(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.MenuParticle, handler)); //middle of screen
            }
        }
    }
    
    public synchronized void start(){
        thread = new Thread(this); //initializing thread to this instance of game becuase it is going to be run in game class
        thread.start(); //starting thread
        running = true;
    }
    public synchronized void stop(){
        try{
            thread.join(); //killing off the thread
            running = false;
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void run(){ //heartbeat and game loop that keeps game going. (every game needs this)
    //this game loop/run code is very popular and used by many developors becuase it is simple, easy, and efficient
        
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000/amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        
        while(running == true){
            long now = System.nanoTime();
            delta += (now - lastTime)/ns;
            lastTime = now;
        while(delta>=1){
            tick();
            delta--;
        }
        if(running){
            render();
        }
        frames++;
        
        if(System.currentTimeMillis() - timer > 1000){
            timer += 1000;
            System.out.println("FPS: " + frames);
            frames = 0;
        }
    }
    stop();
}
    
    private void tick(){
        if(gameState == STATE.Game){
            
            if(paused == false){
                handler.tick();
                hud.tick();
                spawner.tick();
                
                    if(HUD.HEALTH <= 0){
                    HUD.HEALTH = 100;
                    gameState = STATE.End;
                    handler.clearEnemys();
                    for(int i = 0; i < 10; i++){
                    handler.addObject(new MenuParticle(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.MenuParticle, handler)); //middle of screen
                    }
                }
            }
        }else if(gameState == STATE.Menu || gameState == STATE.End || gameState == STATE.Select){
            handler.tick();
            menu.tick();
            
        }
        
    }
    
    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        
        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        
        if(paused){
            g.setColor(Color.white);
            g.drawString("PAUSED", 100, 100);
        }
        
        if(gameState == STATE.Game){
            hud.render(g);
            handler.render(g);
        }else if(gameState == STATE.Shop){
            shop.render(g);
        }else if(gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End || gameState == STATE.Select){
            handler.render(g);
            menu.render(g);
            
        }
        
        g.dispose();
        bs.show();
    }
    
//this clamp method can be used by any object to set a min and max value to restrict how far the object can go and canʻt pass those barriers of min and max
    public static int clamp(int var, int min, int max){
        if(var >= max){
            return var = max;
        }else if(var <= min){
            return var = min;
        }else{
            return var;
        }
    }
    
    public static void main(String args[]) {
        new Game();
        
        
    }
}
package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.MinesManager;
import entity.MonstersManager;
import entity.Player;
import screens.EndScreen;
import screens.StartScreen;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{
	
	final int originalTileSize = 16;
	final int scale = 3;
	
	public final int tileSize = originalTileSize * scale;
	public final int maxScreenCol = 19;
	public final int maxScreenRow = 16;
	public final int screenWidth = tileSize * maxScreenCol;
	public final int screenHeight = tileSize * maxScreenRow;
	
	int panelState;
	public final int START = 0;
	public final int GAME = 1;
	public final int END = 2;

	final int FPS = 60;
	
	public KeyHandler keyH = new KeyHandler(this);
	public UI ui = new UI(this);
	public StartScreen ss = new StartScreen(this);
	public EndScreen es = new EndScreen(this);
	public TileManager tileM = new TileManager(this);
	public CollisionChecker cChecker = new CollisionChecker(this);
	public BattleManager battleM = new BattleManager(this);
	public MonstersManager monstersM = new MonstersManager(this);
	public MinesManager minesM = new MinesManager(this);
	public Player player = new Player(this);
	Thread gameThread;
		
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	public void startGameThread() {
		gameThread = new Thread(this);
		panelState = START;
		gameThread.start();
	}

	@Override
	public void run() {
		
		double drawInterval = 1_000_000_000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		int drawCounter = 0;
		
		while(gameThread != null) {
			currentTime = System.nanoTime();
			
			delta += (currentTime - lastTime)/drawInterval;
			timer += (currentTime - lastTime);
			lastTime = currentTime;
			
			if(delta >= 1) {
				update();
				repaint();
				delta--;
				drawCounter++;
			}
			
			if(timer >= 1_000_000_000) {
//				System.out.println("FPS " + drawCounter);
				drawCounter = 0;
				timer = 0;
			}
	
		}
		
		System.exit(0);
	}
	
	public void update() {

	    if(panelState != GAME)
	        return;
	    
        player.update();
        battleM.update();   
        
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		if(panelState == START) {
		    ss.draw(g2);
        }
        else if(panelState == GAME) {
            tileM.draw(g2);
            monstersM.draw(g2);
            player.draw(g2);
            ui.draw(g2);
        }
        else if(panelState == END) {
            es.draw(g2);
        }
		
		g2.dispose();
	}
	
	void restart() {
	    monstersM = new MonstersManager(this);
	    minesM = new MinesManager(this);
	    player = new Player(this);
	}

}

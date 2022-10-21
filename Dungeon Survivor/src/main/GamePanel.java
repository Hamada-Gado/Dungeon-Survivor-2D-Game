package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.MonstersManager;
import entity.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{
	
	final int originalTileSize = 16;
	final int scale = 3;
	
	public final int tileSize = originalTileSize * scale;
	public final int maxScreenCol = 19;
	public final int maxScreenRow = 16;
	final int screenWidth = tileSize * maxScreenCol;
	final int screenHeight = tileSize * maxScreenRow;
	
	// state 0 = move, state 1 = battle
	public int state;
	public final int MOVE = 0;
	public final int BATTLE = 1;

	final int FPS = 60;
	
	public KeyHandler keyH = new KeyHandler(this);
	public UI ui = new UI(this);
	public TileManager tileM = new TileManager(this);
	public CollisionChecker cChecker = new CollisionChecker(this);
	public BattleManager battleM = new BattleManager(this);
	MonstersManager monstersM = new MonstersManager(this);
	Player player = new Player(this);
	Thread gameThread;
		
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	public void startGameThread() {
		state = MOVE;
		gameThread = new Thread(this);
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
				System.out.println("FPS " + drawCounter);
				drawCounter = 0;
				timer = 0;
			}
	
		}
		
		System.exit(0);
	}
	
	public void update() {

	    player.update();
        battleM.update();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		tileM.draw(g2);
		monstersM.draw(g2);
		player.draw(g2);
		ui.draw(g2);
		
		g2.dispose();
	}
	
	

}

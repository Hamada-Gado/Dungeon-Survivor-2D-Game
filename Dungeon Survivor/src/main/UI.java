package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class UI{
	
	GamePanel gp;
	Font font;
	BufferedImage[] redDice;
	BufferedImage[] blueDice;

	
	public	UI(GamePanel gp) {
		this.gp = gp;
		this.font = new Font("Arial", Font.PLAIN, 40);
		getDiceImage();
	}
	
	public void getDiceImage() {
	    redDice = new BufferedImage[6];
	    blueDice = new BufferedImage[6];
	    
	    try {
	        redDice[0] = ImageIO.read(getClass().getResourceAsStream("/dice/red 1.png"));
	        redDice[1] = ImageIO.read(getClass().getResourceAsStream("/dice/red 2.png"));
	        redDice[2] = ImageIO.read(getClass().getResourceAsStream("/dice/red 3.png"));
	        redDice[3] = ImageIO.read(getClass().getResourceAsStream("/dice/red 4.png"));
	        redDice[4] = ImageIO.read(getClass().getResourceAsStream("/dice/red 5.png"));
	        redDice[5] = ImageIO.read(getClass().getResourceAsStream("/dice/red 6.png"));
	        
	        blueDice[0] = ImageIO.read(getClass().getResourceAsStream("/dice/blue 1.png"));
            blueDice[1] = ImageIO.read(getClass().getResourceAsStream("/dice/blue 2.png"));
            blueDice[2] = ImageIO.read(getClass().getResourceAsStream("/dice/blue 3.png"));
            blueDice[3] = ImageIO.read(getClass().getResourceAsStream("/dice/blue 4.png"));
            blueDice[4] = ImageIO.read(getClass().getResourceAsStream("/dice/blue 5.png"));
            blueDice[5] = ImageIO.read(getClass().getResourceAsStream("/dice/blue 6.png"));
	    }catch(IOException e) {
	        e.printStackTrace();
	    }
	}
	
	public void update() {
		
	}
	
	public void draw(Graphics2D g2) {
		
		g2.setFont(font);
		g2.setColor(Color.white);
		g2.drawString("HP: " + gp.player.hit_point, 20, 40);
		g2.drawString("Steps: " + gp.player.steps, 220, 40);
		
//		if(gp.player.rolling || gp.player.steps != 0) {
//		    g2.drawImage(redDice[gp.player.dice[0]], 400, 0, gp.tileSize, gp.tileSize, null);
//            g2.drawImage(blueDice[gp.player.dice[1]], 455, 0, gp.tileSize, gp.tileSize, null);
//		}
		
		if(gp.state == gp.BATTLE) {
		    g2.drawImage(redDice[gp.player.dice[0]], 400, gp.tileSize, gp.tileSize, gp.tileSize, null);
            g2.drawImage(blueDice[gp.player.dice[1]], 455, gp.tileSize, gp.tileSize, gp.tileSize, null);
        
		    g2.drawString("HP: " + gp.monstersM.monsters[gp.battleM.monsterIndex].hit_point, 10, 370);
		    g2.drawImage(redDice[gp.monstersM.monsters[gp.battleM.monsterIndex].dice[0]], 25, gp.tileSize*8, gp.tileSize, gp.tileSize, null);
		    g2.drawImage(blueDice[gp.monstersM.monsters[gp.battleM.monsterIndex].dice[1]], 80, gp.tileSize*8, gp.tileSize, gp.tileSize, null);
		}
		
		g2.dispose();
	}
	
}

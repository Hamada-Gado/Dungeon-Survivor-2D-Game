package entity;

import java.awt.Color;
import java.awt.Graphics2D;

import main.GamePanel;

public class Mine extends Entity{
    
    GamePanel gp;
    static final int number = 5;
    final int damage = 100;
    
    public Mine(GamePanel gp, int col, int row){
        this.gp = gp;
        this.x = col*gp.tileSize;
        this.y = row*gp.tileSize;
        this.symbol = MINE;
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.black);
        g2.fillRect(x, y, gp.tileSize, gp.tileSize);
    }
}
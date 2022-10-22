package entity;

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

}

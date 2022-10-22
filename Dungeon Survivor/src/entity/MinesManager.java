package entity;

import java.util.Random;

import main.GamePanel;
import tile.TileManager;

public class MinesManager {

    GamePanel gp;
    public Mine[] mines;
    int total_num;
    
    public MinesManager(GamePanel gp) {
        this.gp = gp;
        setMines();
    }
    
    void setMines() {
        total_num = Mine.number;
        mines = new Mine[total_num];
        
        boolean allowedPlacement;
        Random random = new Random();
        int col, row, tileNum;      
        
        while(total_num > 0) {
            col = random.nextInt(gp.maxScreenCol);
            row = random.nextInt(gp.maxScreenRow);
            tileNum = gp.tileM.mapTileNum[col][row];
            
            // check if this placement is allowed
            if(col*gp.tileSize == 17*gp.tileSize && row*gp.tileSize == 1*gp.tileSize)
                continue;
            allowedPlacement = true;
            for(int i = gp.monstersM.monsters.length-1; i >= 0 && allowedPlacement; i--) {
                if(gp.monstersM.monsters[i] == null)
                    break;
                if(gp.monstersM.monsters[i].x == col*gp.tileSize && gp.monstersM.monsters[i].y == row*gp.tileSize)
                    allowedPlacement = false;
            }
            for(int i = mines.length-1; i >= 0 && allowedPlacement; i--) {
                if(mines[i] == null)
                    break;
                if(mines[i].x == col*gp.tileSize && mines[i].y == row*gp.tileSize)
                    allowedPlacement = false;
            }
            
            if(gp.tileM.tiles[tileNum].symbol == TileManager.map_tile && allowedPlacement) {
                mines[--total_num] = new Mine(gp, col, row);
            }
        }
    }
    
}

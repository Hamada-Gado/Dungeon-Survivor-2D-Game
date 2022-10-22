package entity;

import java.awt.Graphics2D;
import java.util.Random;

import main.GamePanel;
import tile.TileManager;

public class MonstersManager {
    
    GamePanel gp;
    public Entity[] monsters;
    int total_num;
    
    public MonstersManager(GamePanel gp) {
        this.gp = gp;
        setMonsters();
    }
    
    public void setMonsters() {
        int dragonNum = Dragon.number;
        int gargoyleNum = Gargoyle.number;
        int goblinNum = Goblin.number;
        int zorkNum = Zork.number;

        total_num = Dragon.number + Gargoyle.number + Goblin.number;
        monsters = new Entity[total_num];
        
        char[] symbols = {Entity.DRAGON, Entity.GARGOYLE, Entity.GOBLIN, Entity.ZORK, Entity.MINE};
        char symbol;

        boolean allowedPlacement;
        boolean flag;
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
            for(int i = monsters.length-1; i >= 0 && allowedPlacement; i--) {
                if(monsters[i] == null)
                    break;
                if(monsters[i].x == col*gp.tileSize && monsters[i].y == row*gp.tileSize)
                    allowedPlacement = false;
                if(monsters[i].x == (col-1)*gp.tileSize && monsters[i].y == row*gp.tileSize)
                    allowedPlacement = false;
                if(monsters[i].x == (col+1)*gp.tileSize && monsters[i].y == row*gp.tileSize)
                    allowedPlacement = false;
                if(monsters[i].x == col*gp.tileSize && monsters[i].y == (row-1)*gp.tileSize)
                    allowedPlacement = false;
                if(monsters[i].x == col*gp.tileSize && monsters[i].y == (row+1)*gp.tileSize)
                    allowedPlacement = false;
            }
            if(gp.tileM.tiles[tileNum].symbol == TileManager.map_tile && allowedPlacement) {
                do {
                    flag = true;
                    symbol = symbols[random.nextInt(symbols.length)];
                    switch(symbol) {
                        case Entity.DRAGON:
                            if(dragonNum == 0)
                                flag = false;
                            break;
                        case Entity.GARGOYLE:
                            if(gargoyleNum == 0)
                                flag = false;
                            break;
                        case Entity.GOBLIN:
                            if(goblinNum == 0)
                                flag = false;
                            break;
                        case Entity.ZORK:
                            if(zorkNum == 0)
                                flag = false;
                            break;
                        default:
                            flag = true;
                    }
                }while(!flag);

                switch(symbol) {
                    case Entity.DRAGON:
                        monsters[--total_num] = new Dragon(gp, col, row);
                        dragonNum--;
                        break;
                    case Entity.GARGOYLE:
                        monsters[--total_num] = new Gargoyle(gp, col, row);
                        gargoyleNum--;
                        break;
                    case Entity.GOBLIN:
                        monsters[--total_num] = new Goblin(gp, col, row);
                        goblinNum--;
                        break;
                    case Entity.ZORK:
                        monsters[--total_num] = new Goblin(gp, col, row);
                        zorkNum--;
                        break;
                }
            }
        }
    }
    
    public void draw(Graphics2D g2) {
        
        for(Entity e : monsters) {
            if(e != null) {
                e.draw(g2);
                e.visable = true;
            }
        }
        
    }
    
}

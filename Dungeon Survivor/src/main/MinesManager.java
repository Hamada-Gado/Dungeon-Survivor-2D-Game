package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import entity.Mine;
import tile.TileManager;

public class MinesManager {

    GamePanel gp;
    public Mine[] mines;
    int total_num;
    boolean explosion;
    int explosionX;
    int explosionY;
    int explosionCounter;
    BufferedImage[] images;
    
    public MinesManager(GamePanel gp) {
        this.gp = gp;
        explosionCounter = 0;
        setMines();
        setImages();
    }
    
    void setImages() {
        images = new BufferedImage[13];
        try {
            images[0] = ImageIO.read(getClass().getResourceAsStream("/explosion/1.png"));
            images[1] = ImageIO.read(getClass().getResourceAsStream("/explosion/2.png"));
            images[2] = ImageIO.read(getClass().getResourceAsStream("/explosion/3.png"));
            images[3] = ImageIO.read(getClass().getResourceAsStream("/explosion/4.png"));
            images[4] = ImageIO.read(getClass().getResourceAsStream("/explosion/5.png"));
            images[5] = ImageIO.read(getClass().getResourceAsStream("/explosion/6.png"));
            images[6] = ImageIO.read(getClass().getResourceAsStream("/explosion/7.png"));
            images[7] = ImageIO.read(getClass().getResourceAsStream("/explosion/8.png"));
            images[8] = ImageIO.read(getClass().getResourceAsStream("/explosion/9.png"));
            images[9] = ImageIO.read(getClass().getResourceAsStream("/explosion/10.png"));
            images[10] = ImageIO.read(getClass().getResourceAsStream("/explosion/11.png"));
            images[11] = ImageIO.read(getClass().getResourceAsStream("/explosion/12.png"));            
            images[12] = ImageIO.read(getClass().getResourceAsStream("/explosion/13.png"));  

        } catch (IOException e) {
            e.printStackTrace();
        }
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
            if(// Player Start zone
               col*gp.tileSize == 17*gp.tileSize && row*gp.tileSize == 1*gp.tileSize ||
               col*gp.tileSize == 16*gp.tileSize && row*gp.tileSize == 1*gp.tileSize ||
               col*gp.tileSize == 17*gp.tileSize && row*gp.tileSize == 2*gp.tileSize ||
               col*gp.tileSize == 16*gp.tileSize && row*gp.tileSize == 2*gp.tileSize ||
               // Zork zone
               col*gp.tileSize == 1*gp.tileSize && row*gp.tileSize == 10*gp.tileSize ||
               col*gp.tileSize == 1*gp.tileSize && row*gp.tileSize == 11*gp.tileSize ||
               col*gp.tileSize == 2*gp.tileSize && row*gp.tileSize == 10*gp.tileSize ||
               col*gp.tileSize == 2*gp.tileSize && row*gp.tileSize == 11*gp.tileSize)
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

    public void setExplosion(Mine mine) {
        
        explosion = true;
        explosionX = mine.x;
        explosionY = mine.y;
        gp.playSE(3);
        
    }
    
    public void explode(Graphics2D g2) {
        
        if(!explosion)
            return;
            
        explosionCounter++;
        g2.drawImage(images[explosionCounter%13], explosionX, explosionY, gp.tileSize, gp.tileSize, null);
        if(explosionCounter == 38) {
            explosionCounter = 0;
            explosion = false;
            if(gp.player.hit_point <= 0) {
                gp.player.hit_point = 0;
                gp.es.setTitleTexts("You Died :(\nNumber of steps: " + gp.player.steps);
                gp.panelState = gp.END;
            }
        } 
    }
    
}

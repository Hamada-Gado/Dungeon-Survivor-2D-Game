package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Goblin extends Entity{
    
    GamePanel gp;
    static final int number = 10;
    
    public Goblin(GamePanel gp, int col, int row) {
        this.gp = gp;
        setDefaultValues(col, row);
        getImage();
    }
    
    public void setDefaultValues(int col, int row) {
        this.x = col*gp.tileSize;
        this.y = row*gp.tileSize;
        hit_point = 100;
        visable = false;
        direction = UP;
        symbol = GOBLIN;
    }
    
    public void getImage() {
        try {
            standingUp = new BufferedImage[2];
            standingUp[0] = ImageIO.read(getClass().getResourceAsStream("/hero/standing up 1.png"));
            standingUp[1] = ImageIO.read(getClass().getResourceAsStream("/hero/standing up 2.png"));
            
            standingDown = new BufferedImage[2];
            standingDown[0] = ImageIO.read(getClass().getResourceAsStream("/hero/standing down 1.png"));
            standingDown[1] = ImageIO.read(getClass().getResourceAsStream("/hero/standing down 2.png"));
            
            standingLeft = new BufferedImage[6];
            standingLeft[0] = ImageIO.read(getClass().getResourceAsStream("/hero/standing left 1.png"));
            standingLeft[1] = ImageIO.read(getClass().getResourceAsStream("/hero/standing left 2.png"));
            standingLeft[2] = ImageIO.read(getClass().getResourceAsStream("/hero/standing left 3.png"));
            standingLeft[3] = ImageIO.read(getClass().getResourceAsStream("/hero/standing left 4.png"));
            standingLeft[4] = ImageIO.read(getClass().getResourceAsStream("/hero/standing left 5.png"));
            standingLeft[5] = ImageIO.read(getClass().getResourceAsStream("/hero/standing left 6.png"));
            
            standingRight = new BufferedImage[6];
            standingRight[0] = ImageIO.read(getClass().getResourceAsStream("/hero/standing right 1.png"));
            standingRight[1] = ImageIO.read(getClass().getResourceAsStream("/hero/standing right 2.png"));
            standingRight[2] = ImageIO.read(getClass().getResourceAsStream("/hero/standing right 3.png"));
            standingRight[3] = ImageIO.read(getClass().getResourceAsStream("/hero/standing right 4.png"));
            standingRight[4] = ImageIO.read(getClass().getResourceAsStream("/hero/standing right 5.png"));
            standingRight[5] = ImageIO.read(getClass().getResourceAsStream("/hero/standing right 6.png"));
            
            
        }catch(IOException e) {
            e.printStackTrace();            
        }
    }
    
    public void draw(Graphics2D g2) {
        if(!visable)
            return;
        
//        spriteCounter++;
//        if(spriteCounter > 12) {
//            if(spriteNum == 5)
//                spriteNum = 0;
//            else spriteNum++;
//            spriteCounter = 0;
//        }
//        
//        BufferedImage image = null;
//        
//        
//        switch(direction) {
//            case UP:
//                image = standingUp[spriteNum%2];
//                break;
//            case DOWN:
//                image = standingDown[spriteNum%2]; 
//                break;
//            case LEFT:
//                image = standingLeft[spriteNum];
//                break;
//            case RIGHT:
//                image = standingRight[spriteNum];
//                break;
//        }
//        
//        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
        

        g2.setColor(Color.green);
        g2.fillRect(x, y, gp.tileSize, gp.tileSize);
    }

}
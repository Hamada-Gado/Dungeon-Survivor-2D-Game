package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Dragon extends Entity{
    
    GamePanel gp;
    static int number = 5;
    
    public Dragon(GamePanel gp, int x, int y) {
        this.gp = gp;
        setDefaultValues(x, y);
        getDragonImage();
    }
    
    public void setDefaultValues(int x, int y) {
        this.x = x*gp.tileSize;
        this.y = y*gp.tileSize;
        hit_point = 200;
        visable = false;
        direction = DOWN;
        symbol = DRAGON;
    }
    
    public void getDragonImage() {
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
        
        g2.setColor(Color.red);
        g2.fillRect(x, y, gp.tileSize, gp.tileSize);
    }

}

package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Dragon extends Entity{
    
    GamePanel gp;
    public static final int number = 5;
    
    public Dragon(GamePanel gp, int col, int row) {
        this.gp = gp;
        setDefaultValues(col, row);
        getImage();
    }
    
    public void setDefaultValues(int col, int row) {
        this.x = col*gp.tileSize;
        this.y = row*gp.tileSize;
        hit_point = 200;
        visable = false;
        direction = DOWN;
        symbol = DRAGON;
    }
    
    public void getImage() {
        try {
            standingUp = new BufferedImage[2];
            standingUp[0] = ImageIO.read(getClass().getResourceAsStream("/dragon/standing up 1.png"));
            standingUp[1] = ImageIO.read(getClass().getResourceAsStream("/dragon/standing up 2.png"));
            
            standingDown = new BufferedImage[2];
            standingDown[0] = ImageIO.read(getClass().getResourceAsStream("/dragon/standing down 1.png"));
            standingDown[1] = ImageIO.read(getClass().getResourceAsStream("/dragon/standing down 2.png"));
            
            standingLeft = new BufferedImage[2];
            standingLeft[0] = ImageIO.read(getClass().getResourceAsStream("/dragon/standing left 1.png"));
            standingLeft[1] = ImageIO.read(getClass().getResourceAsStream("/dragon/standing left 2.png"));
            
            standingRight = new BufferedImage[2];
            standingRight[0] = ImageIO.read(getClass().getResourceAsStream("/dragon/standing right 1.png"));
            standingRight[1] = ImageIO.read(getClass().getResourceAsStream("/dragon/standing right 2.png"));
            
        }catch(IOException e) {
            e.printStackTrace();            
        }
    }
    
    public void draw(Graphics2D g2) {
        if(!visable)
            return;
        
        spriteCounter++;
        if(spriteCounter > 12) {
            if(spriteNum == 1)
                spriteNum = 0;
            else spriteNum++;
            spriteCounter = 0;
        }
        
        BufferedImage image = null;
       
        switch(direction) {
            case UP:
                image = standingUp[spriteNum%2];
                break;
            case DOWN:
                image = standingDown[spriteNum%2]; 
                break;
            case LEFT:
                image = standingLeft[spriteNum];
                break;
            case RIGHT:
                image = standingRight[spriteNum];
                break;
        }
        
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
        
//        g2.setColor(Color.red);
//        g2.fillRect(x, y, gp.tileSize, gp.tileSize);
    }

}

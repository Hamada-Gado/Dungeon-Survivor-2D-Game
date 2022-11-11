package screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import main.GamePanel;

public class LevelScreen {
    
    GamePanel gp;
    Font buttonsFont;
    Font returnFont;
    String returnText;
    public int level;

    public int commandNum = 0;
    
    public LevelScreen(GamePanel gp) {
        this.gp = gp;
        this.buttonsFont = new Font("ink free", Font.PLAIN, 65);
        this.returnFont = new Font("ink free", Font.BOLD, 72);
        returnText = "Press Enter to return";
        level = 400;
    }
    
    public void draw(Graphics2D g2) {
        g2.setColor(Color.black);
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        
        g2.setFont(returnFont); 
        int length = (int) g2.getFontMetrics().getStringBounds(""+returnText, g2).getWidth();
        int x = (gp.screenWidth - length)/2;
        
        int y = gp.tileSize*7;

        // shadow
        g2.setColor(Color.gray);
        g2.drawString(returnText, x+5, y+5);        
        // main
        g2.setColor(Color.white);
        g2.drawString(returnText, x, y);
        
        g2.setFont(buttonsFont);
        g2.setColor(Color.white);
        
        length = (int) g2.getFontMetrics().getStringBounds(""+level, g2).getWidth();
        x = (gp.screenWidth - length)/2;
        
        y -= gp.tileSize*2;
        g2.drawString(""+level, x, y);
        
        switch(commandNum) {
            case 0:
                g2.drawString(">", x+gp.tileSize+length, y);
                break;
            case 1:
                g2.drawString("<", x-gp.tileSize, y);
                g2.drawString(">", x+gp.tileSize+length, y);
                break;
            case 2:
                g2.drawString("<", x-gp.tileSize, y);
                break;
        }        
    }
       
}

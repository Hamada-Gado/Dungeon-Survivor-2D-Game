package screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import main.GamePanel;

public class StartScreen {

    GamePanel gp;
    Font buttonsFont;
    Font titleFont;
    String titleText;
    String playText;
    String levelText;
    String quitText;
    public int commandNum = 0;
    
    public StartScreen(GamePanel gp) {
        this.gp = gp;
        this.buttonsFont = new Font("ink free", Font.PLAIN, 65);
        this.titleFont = new Font("ink free", Font.BOLD, 90);
        titleText = "Dungeon Survivor";
        playText = "PLAY";
        levelText = "LEVEL";
        quitText = "QUIT";
    }
    
    public void draw(Graphics2D g2) {
        g2.setColor(Color.black);
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        
        g2.setFont(titleFont); 
        int x = getXforCenteredText(titleText, g2);
        int y = gp.tileSize*3;

        // shadow
        g2.setColor(Color.gray);
        g2.drawString(titleText, x+5, y+5);        
        // main
        g2.setColor(Color.white);
        g2.drawString(titleText, x, y);
        
        g2.setFont(buttonsFont);
        g2.setColor(Color.white);
        
        x = getXforCenteredText(playText, g2);
        y += gp.tileSize*4;
        g2.drawString(playText, x, y);
        if(commandNum == 0) {
            g2.drawString(">", x-gp.tileSize, y);
        }
        
        x = getXforCenteredText(levelText, g2);
        y += gp.tileSize*2;
        g2.drawString(levelText, x, y);
        if(commandNum == 1) {
            g2.drawString(">", x-gp.tileSize, y);
        }
        
        x = getXforCenteredText(quitText, g2);
        y += gp.tileSize*2;
        g2.setColor(Color.white);
        g2.drawString(quitText, x, y);
        if(commandNum == 2) {
            g2.drawString(">", x-gp.tileSize, y);
        }

        
    }
    
    public int getXforCenteredText(String text, Graphics2D g2) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = (gp.screenWidth - length)/2;
        
        return x;
    }
    
}

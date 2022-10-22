package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Player extends Entity{
	
	GamePanel gp;
//	Random randomGen;
//	public boolean rolling;
//	public int diceCounter;
	public int state;
	
	public Player(GamePanel gp) {
		this.gp = gp;
//		randomGen = new Random();
		setDefaultValues();
		getPlayerImage();
	}
	
	public void setDefaultValues() {
		x = 17 * gp.tileSize;
		y = 1 * gp.tileSize;
		speed = 4;
		steps = 0;
		facing = true;
		direction = DOWN;
		hit_point = 400;
		symbol = HERO;
//		diceCounter = 0;
		state = 0;
	}
	
	public void getPlayerImage() {
		try {
			runningUp = new BufferedImage[4];
			runningUp[0] = ImageIO.read(getClass().getResourceAsStream("/hero/running up 1.png"));
			runningUp[1] = ImageIO.read(getClass().getResourceAsStream("/hero/running up 2.png"));
			runningUp[2] = ImageIO.read(getClass().getResourceAsStream("/hero/running up 3.png"));
			runningUp[3] = ImageIO.read(getClass().getResourceAsStream("/hero/running up 4.png"));
			
			runningDown = new BufferedImage[4];
			runningDown[0] = ImageIO.read(getClass().getResourceAsStream("/hero/running down 1.png"));
			runningDown[1] = ImageIO.read(getClass().getResourceAsStream("/hero/running down 2.png"));
			runningDown[2] = ImageIO.read(getClass().getResourceAsStream("/hero/running down 3.png"));
			runningDown[3] = ImageIO.read(getClass().getResourceAsStream("/hero/running down 4.png"));
			
			runningLeft = new BufferedImage[4];
			runningLeft[0] = ImageIO.read(getClass().getResourceAsStream("/hero/running left 1.png"));
			runningLeft[1] = ImageIO.read(getClass().getResourceAsStream("/hero/running left 2.png"));
			runningLeft[2] = ImageIO.read(getClass().getResourceAsStream("/hero/running left 3.png"));
			runningLeft[3] = ImageIO.read(getClass().getResourceAsStream("/hero/running left 4.png"));
			
			runningRight = new BufferedImage[4];
			runningRight[0] = ImageIO.read(getClass().getResourceAsStream("/hero/running right 1.png"));
			runningRight[1] = ImageIO.read(getClass().getResourceAsStream("/hero/running right 2.png"));
			runningRight[2] = ImageIO.read(getClass().getResourceAsStream("/hero/running right 3.png"));
			runningRight[3] = ImageIO.read(getClass().getResourceAsStream("/hero/running right 4.png"));
			
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
	
// Grid based movement
	
	public void update() {
	    
//	    if(gp.keyH.rPressed && gp.state == gp.MOVE && steps == 0) {
//	        rolling = true;            
//	    }
//       
//	    if(rolling) {
//	        if(diceCounter < 50) {
//	            dice[0] = randomGen.nextInt(6);
//	            dice[1] = randomGen.nextInt(6);
//	            diceCounter++;
//	        }
//	        else {
//	            switch(dice[0]+1) {
//	                case 1:
//	                case 4:
//	                    steps += 1;
//	                    break;
//	                case 2:
//	                case 5:
//	                    steps += 2;
//	                    break;
//	                case 3:
//	                case 6:
//	                    steps += 3;
//	                    break;
//	            }
//               
//	            switch(dice[1]+1) {
//	                case 1:
//	                case 4:
//	                    steps += 1;
//	                    break;
//	                case 2:
//	                case 5:
//	                    steps += 2;
//	                    break;
//	                case 3:
//	                case 6:
//	                    steps += 3;
//	                    break;
//	            }
//	            rolling = false;
//	            diceCounter = 0;
//	        }
//	    }
    	
    	if(!moving) {
    		
    		spriteCounter++;
    		if(spriteCounter > 12) {
    			if(spriteNum >= 5)
    				spriteNum = 0;
    			else spriteNum++;
    			spriteCounter = 0;
    		}
    		
    		// allow the idle animation only when in battle
    		if(state == 1)
                return;
    		
    		if(gp.keyH.upPressed || gp.keyH.downPressed 
    		|| gp.keyH.leftPressed || gp.keyH.rightPressed) {
    
    			if(gp.keyH.upPressed) {
    				direction = UP;
    			}
    			else if(gp.keyH.downPressed) {
    				direction = DOWN;
    			}
    			else if(gp.keyH.rightPressed) {
    				direction = RIGHT;
    			}
    			else if(gp.keyH.leftPressed) {
    				direction = LEFT;
    			}
    			
    			gp.cChecker.checkTile();
    			if(!collisionOn) { //  && steps != 0
    				moving = true;
    				spriteNum = 1;
    			}
    		}
    	}
    	
    	if(moving) {
    		
    		spriteCounter++;
    		if(spriteCounter > 12) {
    			if(spriteNum >= 3)
    				spriteNum = 0;
    			else spriteNum++;
    			spriteCounter = 0;
    		}	
    		
    		switch(direction) {
    			case UP:
    				y -= speed;
    				break;
    			case DOWN:
    				y += speed;
    				break;
    			case RIGHT:
    				x += speed;
    				break;
    			case LEFT:
    				x -= speed;
    				break;
    		}
    		
    		movingCounter += speed;
    		if(movingCounter >= 48) {
    			movingCounter = 0;
    			moving = false;
    			spriteNum = 0;
    			steps++;
//    			steps--;
    			gp.cChecker.checkVillages();
    			gp.cChecker.checkMines();
    			
    			// allow the player to move only one time before battle begins 
    			if(state == 0 && gp.state == gp.BATTLE)
    			    state = 1;
    		}
    	}
    }

	@Override
	public void draw(Graphics2D g2) {
		
		BufferedImage image = null;
		
		if(facing) {
		    switch(direction) {
	            case UP:
	                if(moving)
	                    image = runningUp[spriteNum];
	                else image = standingUp[spriteNum%2];
	                break;
	            case DOWN:
	                if(moving)
	                    image = runningDown[spriteNum];
	                else image = standingDown[spriteNum%2]; 
	                break;
	            case LEFT:
	                if(moving)
	                    image = runningLeft[spriteNum];
	                else image = standingLeft[spriteNum];
	                break;
	            case RIGHT:
	                if(moving)
	                    image = runningRight[spriteNum];
	                else image = standingRight[spriteNum];
	                break;
	        }
		}
		else {
		    switch(direction) {
	            case DOWN:
	                if(moving)
	                    image = runningUp[spriteNum];
	                else image = standingUp[spriteNum%2];
	                break;
	            case UP:
	                if(moving)
	                    image = runningDown[spriteNum];
	                else image = standingDown[spriteNum%2]; 
	                break;
	            case RIGHT:
	                if(moving)
	                    image = runningLeft[spriteNum];
	                else image = standingLeft[spriteNum];
	                break;
	            case LEFT:
	                if(moving)
	                    image = runningRight[spriteNum];
	                else image = standingRight[spriteNum];
	                break;
	        }

		}
				
		g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
	}

	public void changeFacing() {
        facing = !facing;
        switch (direction){
            case UP:
                direction = Entity.DOWN;
                break;
            case DOWN:
                direction = Entity.UP;
                break;
            case LEFT:
                direction = Entity.RIGHT;
                break;
            case RIGHT:
                direction = Entity.LEFT;
                break;
        }
	}

}
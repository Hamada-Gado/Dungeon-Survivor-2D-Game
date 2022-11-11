package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Player extends Entity{
	
	GamePanel gp;
	// state = 0 does nothing, state = 1 allow the idle animation only when in battle
	public int movingState;
	// state 0 = move, state 1 = battle
    public int gameState;
    public final int MOVE = 0;
    public final int BATTLE = 1;
    
    // if i want to move the player 
    // from the code not the keys input
    public boolean forcedMove;
	
	public Player(GamePanel gp) {
		this.gp = gp;
		setDefaultValues();
		getPlayerImage();
	}
	
	public void setDefaultValues() {
		x = 17 * gp.tileSize;
		y = 1 * gp.tileSize;
		speed = 1;
		steps = 0;
		facing = true;
		direction = DOWN;
		hit_point = 400;
		symbol = HERO;
		gameState = MOVE;
		movingState = 0;
		forcedMove = false;
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
	    
	    // to not change move in draw therefore make continuous movement smooth
	    if(movingCounter == 0)
	        moving = false;
	    
	    if(forcedMove) {            
            forcedMove = false;
            moving = true;
            
            // to not throw exception index out of bound when starting to move
            if(spriteNum > 3)
                spriteNum = 0;
        }
	       	
    	if(!moving) {
    	    
    		if((gp.keyH.upPressed || gp.keyH.downPressed 
    		|| gp.keyH.leftPressed || gp.keyH.rightPressed) && movingState == 0) {
    
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
    			if(!collisionOn) {
    			    gp.playSE(2);
    				moving = true;
    				// to not throw exception index out of bound when starting to move
    	            if(spriteNum > 3)
    	                spriteNum = 0;
    			}
    		}
    		
    		// to not throw exception index out of bound when starting to move
            if(!moving) {
                spriteCounter++;
                if(spriteCounter > 12) {
                    if(spriteNum >= 5)
                        spriteNum = 0;
                    else spriteNum++;
                    spriteCounter = 0;
                }
            }
    	}
    	
    	if(moving) {
    	    
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
    			steps++;
    			gp.cChecker.checkVillages();
    			gp.cChecker.checkMines();
    			
    			// allow the player to move only one time before battle begins 
    			if(movingState == 0 && gameState == BATTLE)
    			    movingState = 1;
    		}
    		
		    spriteCounter++;
              if(spriteCounter > 12) {
                  if(spriteNum >= 3)
                      spriteNum = 0;
                  else spriteNum++;
                  spriteCounter = 0;
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
package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
	
	public boolean upPressed, downPressed, leftPressed, rightPressed, rPressed;
	GamePanel gp;
	
	public KeyHandler(GamePanel gp) {
	    this.gp = gp;
    }

	@Override
	public void keyTyped(KeyEvent e) {
	
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_ESCAPE)
            gp.gameThread = null;
		
		if(gp.panelState == gp.START) {
		    if(code == KeyEvent.VK_W) {
                gp.ss.commandNum--;
                if(gp.ss.commandNum == -1)
                    gp.ss.commandNum = 2;
            }
            else if(code == KeyEvent.VK_S) {
                gp.ss.commandNum++;
                if(gp.ss.commandNum == 3)
                    gp.ss.commandNum = 0;
            }
		    if(code == KeyEvent.VK_ENTER) {
		        switch(gp.ss.commandNum) {
		            case 0:
		                gp.panelState = gp.GAME;
		                break;
		            case 1:
		                // TODO change the level of the game
		                break;
		            case 2:
		                gp.gameThread = null;
		        }
		    }
		}
		else if(gp.panelState == gp.GAME) {
		    if(code == KeyEvent.VK_W) {
	            upPressed = true;
	        }
	        else if(code == KeyEvent.VK_S) {
	            downPressed = true;
	        }
	        else if(code == KeyEvent.VK_D) {
	            rightPressed = true;
	        }
	        else if(code == KeyEvent.VK_A) {
	            leftPressed = true;
	        }
	        else if(code == KeyEvent.VK_R) {
	            rPressed = true;
	        }   
		}
		else if(gp.panelState == gp.END) {
		    if(code == KeyEvent.VK_W) {
                gp.es.commandNum--;
                if(gp.es.commandNum == -1)
                    gp.es.commandNum = 1;
            }
            else if(code == KeyEvent.VK_S) {
                gp.es.commandNum++;
                if(gp.es.commandNum == 2)
                    gp.es.commandNum = 0;
            }
            if(code == KeyEvent.VK_ENTER) {
                switch(gp.es.commandNum) {
                    case 0:
                        gp.panelState = gp.START;
                        gp.restart();
                        break;
                    case 1:
                        gp.gameThread = null;
                        break;
                }
            }
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_W) {
			upPressed = false;
		}
		else if(code == KeyEvent.VK_S) {
			downPressed = false;
		}
		else if(code == KeyEvent.VK_D) {
			rightPressed = false;
		}
		else if(code == KeyEvent.VK_A) {
			leftPressed = false;
		}
		else if(code == KeyEvent.VK_R) {
            rPressed = false;
        }
	}

}

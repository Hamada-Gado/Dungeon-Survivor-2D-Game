package main;

import entity.Entity;
import tile.TileManager;

public class CollisionChecker {
    
	GamePanel gp;

	public CollisionChecker(GamePanel gp) {
		this.gp = gp;
	}
	
	public void checkTile() {
		gp.player.collisionOn = false;
		
		int playerCol = gp.player.x/gp.tileSize;
		int playerRow = gp.player.y/gp.tileSize;
		
		int tileNum;
		
		switch(gp.player.direction) {
			case Entity.UP:
				tileNum = gp.tileM.mapTileNum[playerCol][playerRow-1];
				if(gp.tileM.tiles[tileNum].collision)
					gp.player.collisionOn = true;
				break;
			case Entity.DOWN:
				tileNum = gp.tileM.mapTileNum[playerCol][playerRow+1];
				if(gp.tileM.tiles[tileNum].collision)
					gp.player.collisionOn = true;
				break;
			case Entity.RIGHT:
				tileNum = gp.tileM.mapTileNum[playerCol+1][playerRow];
				if(gp.tileM.tiles[tileNum].collision)
					gp.player.collisionOn = true;
				break;
			case Entity.LEFT:
				tileNum = gp.tileM.mapTileNum[playerCol-1][playerRow];
				if(gp.tileM.tiles[tileNum].collision)
					gp.player.collisionOn = true;
				break;
			
		}
	}
	
	public void checkVillages() {
	    int playerCol = gp.player.x/gp.tileSize;
        int playerRow = gp.player.y/gp.tileSize;
        int tileNum = gp.tileM.mapTileNum[playerCol][playerRow];
        
        
        switch(gp.tileM.tiles[tileNum].symbol) {
            case TileManager.village_a:
                gp.player.hit_point += 200;
                gp.tileM.tiles[tileNum].symbol = ' ';
                break;
            case TileManager.village_b:
                gp.player.hit_point += 150;
                gp.tileM.tiles[tileNum].symbol = ' ';
                break;
            case TileManager.village_c:
                gp.player.hit_point += 100;
                gp.tileM.tiles[tileNum].symbol = ' ';
                break;
        }
	}
}

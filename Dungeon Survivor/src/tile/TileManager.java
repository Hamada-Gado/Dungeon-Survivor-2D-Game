package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {    

    // constant symbols of tiles
    public static final char map_tile  = 'm';
    public static final char boss_tile = 'B';
    public static final char village_a = 'a';
    public static final char village_b = 'b';
    public static final char village_c = 'c';
	
	GamePanel gp;
	public Tile[] tiles;
	public int mapTileNum[][];
	
	public TileManager(GamePanel gp) {
		this.gp = gp;
		tiles = new Tile[10];
		
		mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];
		
		getTileImage();
		loadMap("/maps/map.txt");
	}
	
	public void getTileImage() {
		
		try {
			
			tiles[0] = new Tile();
			tiles[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/000.png"));
			tiles[0].collision = true;
			
			tiles[1] = new Tile();
			tiles[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/boarder tile.png"));
			tiles[1].collision = true;
			
			tiles[2] = new Tile();
			tiles[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/map tile.png"));
			tiles[2].symbol = map_tile;
			
			tiles[3] = new Tile();
			tiles[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/mountain tile.png"));
			tiles[3].collision = true;
			
			tiles[4] = new Tile();
			tiles[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/villageA.png"));
			tiles[4].symbol = village_a;
			
			tiles[5] = new Tile();
			tiles[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/villageB.png"));
			tiles[5].symbol = village_b;
			
			tiles[6] = new Tile();
			tiles[6].image = ImageIO.read(getClass().getResourceAsStream("/tiles/villageC.png"));
			tiles[6].symbol = village_c;
			
			tiles[7] = new Tile();
			tiles[7].image = ImageIO.read(getClass().getResourceAsStream("/tiles/boss tile.png"));
			tiles[7].symbol = boss_tile;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadMap(String path) {
		
		InputStream	is = getClass().getResourceAsStream(path);
		BufferedReader br = new BufferedReader(new InputStreamReader(is));

		int col = 0;
		int row = 0;
		
		while(col < gp.maxScreenCol && row <gp.maxScreenRow) {
			try {
				String line = br.readLine();
				String[] numbers = line.split(" ");
				
				for(;col < gp.maxScreenCol; col++) {
					int num = Integer.parseInt(numbers[col]);
					mapTileNum[col][row] = num;
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}	
			
			if(col == gp.maxScreenCol) {
				col = 0;
				row++;
			}
		}
	}
	
	public void draw(Graphics2D g2) {
		
		int col = 0;
		int row = 0;
		int x = 0;
		int y = 0;
		
		while(col < gp.maxScreenCol && row < gp.maxScreenRow) {
			int tileNum = mapTileNum[col][row];
			
			g2.drawImage(tiles[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
			col++;
			x += gp.tileSize;
			
			if(col == gp.maxScreenCol) {
				col = 0;
				x = 0;
				row++;
				y += gp.tileSize;
			}
		}
		
	}
}

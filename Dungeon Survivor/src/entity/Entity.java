package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Entity {
    
    public static final char DRAGON = 'd';
    public static final char GOBLIN = 'g';
    public static final char GARGOYLE = 'G';
    public static final char ZORK = 'z';
    public static final char MINES = 'm';
    public static final char HERO = 'h';
	
	public static final String DOWN = "down";
	public static final String UP = "up";
	public static final String LEFT = "left";
	public static final String RIGHT = "right";
	
	public char symbol;
	public int hit_point;
	public int[] dice = new int[2];
	public int x, y, speed, movingCounter, steps;
	public boolean moving;
	protected boolean facing;
	public boolean visable;
	
	public BufferedImage[] runningUp;
	public BufferedImage[] runningDown;
	public BufferedImage[] runningLeft;
	public BufferedImage[] runningRight;
	
	public BufferedImage[] standingUp;
	public BufferedImage[] standingDown;
	public BufferedImage[] standingLeft;
	public BufferedImage[] standingRight;
	
	public String direction;
	public boolean collisionOn;
	
	public int spriteCounter = 0;
	public int spriteNum = 0;
	
	public void draw(Graphics2D g2) {}
}

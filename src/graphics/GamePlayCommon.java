package graphics;

import java.awt.Graphics;

public interface GamePlayCommon {
	
	final int maxRow = 25;
	final int maxCol = 79;
	
	public static final int WIDTH = 640;
	public static final int HEIGHT = 480;
	
	public static final int ROW = 15;
	public static final int COL = 20;
	
	//Each Grid has resolution 32x32;
	public static final int CS = 32;
	
	public void draw(Graphics g);
}

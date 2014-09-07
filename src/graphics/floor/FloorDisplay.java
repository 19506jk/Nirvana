package graphics.floor;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import mechanics.enums.Square;
import mechanics.floor.Cell;
import mechanics.floor.Floor;
import graphics.GamePlayCommon;
import graphics.MainPanel;
import graphics.util.TilePixelCoordFinder;

public class FloorDisplay implements GamePlayCommon {
	
	private Image floorImage;
	private final String floorImagePath = "images/Dungeon_A5.png";
	private Image wallImage;
	private final String wallImagePath = "images/Dungeon_A4.png";
	private Image doorImage;
	private final String doorImagePath = "images/Outside_B.png";
	
	MainPanel panel;
	
	public FloorDisplay(MainPanel panel){
		this.panel = panel;
		loadImage();
	}
	
	@Override
	public void draw(Graphics g){
		Cell[][] layout = Floor.getInst().getLayout();
		
		//Note here r is the row number, which corresponds to y coordinate, and c is column number, which corresponds to x
		Square content;
		for (int r = 0; r < ROW; r++){
			for (int c = 0; c < COL; c++){
				Image tempImage;
				content = layout[r][c].getType();
				if (content.equals(Square.DOOR)){
					tempImage = doorImage;
				}
				else if(content.equals(Square.HORIW) || content.equals(Square.VERTIW)){
					tempImage = wallImage;
				}
				else{
					tempImage = floorImage;
				}
				g.drawImage(tempImage, 
								c * CS, 
								r * CS,
								c * CS + CS, 
								r * CS + CS, 
								TilePixelCoordFinder.getCoordOfSquare(content).getX() * CS,
								TilePixelCoordFinder.getCoordOfSquare(content).getY() * CS,
								TilePixelCoordFinder.getCoordOfSquare(content).getX() * CS + CS,
								TilePixelCoordFinder.getCoordOfSquare(content).getY() * CS + CS, 
							panel);
			}
		}
	}
	
	private void loadImage(){
		
		ImageIcon icon = new ImageIcon(getClass().getResource(floorImagePath));
		floorImage = icon.getImage();
		
		icon = new ImageIcon(getClass().getResource(wallImagePath));
		wallImage = icon.getImage();
		
		icon = new ImageIcon(getClass().getResource(doorImagePath));
		doorImage = icon.getImage();
		
	}
}

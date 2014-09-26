package graphics.character;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import mechanics.character.Enemy;
import mechanics.enums.MobType;
import mechanics.floor.Cell;
import mechanics.floor.Floor;
import graphics.GamePlayCommon;
import graphics.MainPanel;
import graphics.util.EnemyPixelCoordFinder;

/*
 * The algorithm of this class is undesirable. It loops through the displayed floor area again to search for any enemy,
 * then draw the enemies. However, looping through the displayed floor has already been done in painting floor step.
 * It would be good if we can merge these two steps together so that we do not have to loop twice,
 * or figure out a way to pinpoint the exact positions of the enemies, and then display them.
 */
public class EnemyDisplay implements GamePlayCommon {

	private Image enemyImage;
	private final String enemyImagePath = "images/Monster3.png";
	
	MainPanel panel;
	
	public EnemyDisplay(MainPanel panel){
		this.panel = panel;
		loadImage();
	}
	
	@Override
	public void draw(Graphics g, int offsetX, int offsetY) {
		Cell[][] layout = Floor.getInst().getLayout();
		
		//Get current location of the map in the general larger map
		int firstTileX = -offsetX/32;
		int lastTileX = firstTileX + 20 + 1;
		lastTileX = Math.min(lastTileX, 79);
		
		int firstTileY = -offsetY/32;
		int lastTileY = firstTileY + 15 + 1;
		lastTileY = Math.min(lastTileY, 25);
		
		for (int r = firstTileY; r < lastTileY; r++){
			for (int c = firstTileX; c < lastTileX; c++){
				if (layout[r][c].getOnCell() != null && layout[r][c].getOnCell().getType() == 1){
					Enemy enemy = (Enemy) layout[r][c].getOnCell();
					g.drawImage(enemyImage, 
							c * CS + offsetX, 
							r * CS + offsetY,
							c * CS + CS + offsetX, 
							r * CS + CS + offsetY, 
							EnemyPixelCoordFinder.getCoordOfMobType(enemy.getMobType()).getX() * CS,
							EnemyPixelCoordFinder.getCoordOfMobType(enemy.getMobType()).getY() * CS,
							EnemyPixelCoordFinder.getCoordOfMobType(enemy.getMobType()).getX() * CS + CS,
							EnemyPixelCoordFinder.getCoordOfMobType(enemy.getMobType()).getY() * CS + CS, 
						panel);
				}
			}
		}
	}

	private void loadImage(){
		
		ImageIcon icon = new ImageIcon(getClass().getResource(enemyImagePath));
		enemyImage = icon.getImage();
		
	}
}

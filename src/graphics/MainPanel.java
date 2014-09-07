package graphics;

import graphics.character.EnemyDisplay;
import graphics.character.PlayerDisplay;
import graphics.floor.FloorDisplay;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class MainPanel extends JPanel implements KeyListener {

	private static final int WIDTH = 640;
	private static final int HEIGHT = 480;
	
	private static final int ROW = 15;
	private static final int COL = 20;
	
	//Each Grid has resolution 32x32;
	private static final int CS = 32;
	
	private Image enemyImage;
	private final String enemyImagePath = "graphics/characters/Monster1.png";
	
	private FloorDisplay floorDisplay;
	private PlayerDisplay playerDisplay;
	private EnemyDisplay enemyDisplay;
	
	public MainPanel() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		floorDisplay = new FloorDisplay(this);
		playerDisplay = new PlayerDisplay(this);
		enemyDisplay = new EnemyDisplay(this);
		
		setFocusable(true);
		addKeyListener(this);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		floorDisplay.draw(g);
		playerDisplay.draw(g);
		enemyDisplay.draw(g);
	}
	
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}

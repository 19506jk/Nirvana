package graphics;

import graphics.character.EnemyDisplay;
import graphics.character.PlayerDisplay;
import graphics.floor.FloorDisplay;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import mechanics.character.Player;

public class MainPanel extends JPanel{

	private static final int WIDTH = 640;
	private static final int HEIGHT = 480;

	private static final int ROW = 15;
	private static final int COL = 20;

	// Each Grid has resolution 32x32;
	private static final int CS = 32;

	private Image enemyImage;
	private final String enemyImagePath = "graphics/characters/Monster1.png";

	private FloorDisplay floorDisplay;
	private PlayerDisplay playerDisplay;
	private EnemyDisplay enemyDisplay;

	private String curAction = "mov"; // Stores the current player action
										// (move,attack,etc)

	// private boolean needDirec = false; //Stores if the current key pressed
	// need a direction (like attacking or using a skill)

	public MainPanel() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));

		floorDisplay = new FloorDisplay(this);
		playerDisplay = new PlayerDisplay(this);
		enemyDisplay = new EnemyDisplay(this);

		setFocusable(true);
	}

	public void paintComponent(Graphics g) {

		// Calculates the offset of the player and create the map around it
		Player player = Player.getPlayer();

		// calculates offsetX
		int offsetX = WIDTH / 2 - player.getpc() * CS;
		offsetX = Math.min(offsetX, 0);
		offsetX = Math.max(offsetX, WIDTH - 79 * CS);

		// calculates offsetY
		int offsetY = HEIGHT / 2 - player.getpr() * CS;
		offsetY = Math.min(offsetY, 0);
		offsetY = Math.max(offsetY, HEIGHT - 25 * CS);

		super.paintComponent(g);

		floorDisplay.draw(g, offsetX, offsetY);
		playerDisplay.draw(g, offsetX, offsetY);
		enemyDisplay.draw(g, offsetX, offsetY);
	}

}

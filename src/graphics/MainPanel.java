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

import mechanics.character.Enemy;
import mechanics.character.Obj;
import mechanics.character.Player;
import mechanics.floor.Floor;

public class MainPanel extends JPanel implements KeyListener {

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

	// New stuff added for key listener
	final int maxRow = 25;
	final int maxCol = 79;
	private Floor pFloor = null;
	private int level = 1;
	private boolean ascended = false;
	private boolean won = false;
	
	private String curAction = "mov";  //Stores the current player action (move,attack,etc)
	//private boolean needDirec = false;  //Stores if the current key pressed need a direction (like attacking or using a skill)
	

	public MainPanel() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));

		floorDisplay = new FloorDisplay(this);
		playerDisplay = new PlayerDisplay(this);
		enemyDisplay = new EnemyDisplay(this);

		setFocusable(true);
		addKeyListener(this);
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

	@Override
	public void keyPressed(KeyEvent e) { 
		// TODO Auto-generated method stub
		Player player = Player.getPlayer();
		pFloor = Floor.getInst();
		Obj tempObj;
		String cmd = null;

		int key = e.getKeyCode();
		
		switch (key) {
		case KeyEvent.VK_UP: // Press Up
			cmd = "no";
			break;
		case KeyEvent.VK_DOWN: // Press Down
			cmd = "so";
			break;
		case KeyEvent.VK_LEFT: // Press Left
			cmd = "we";
			break;
		case KeyEvent.VK_RIGHT: // Press Right
			cmd = "ea";
			break;
		case 65:  //the key code for a, which is attack here
			cmd = "a";
			curAction = "atk";
			break;
		default:
			cmd = "invalid";
			break;
		}
		
		if (curAction == "mov") {
			int pr = player.getpr();  // player R
			int pc = player.getpc();  // player C
			
			switch (cmd) {
			case "no":
				tempObj = pFloor.getObj(pr - 1, pc);
				break;
			case "so":
				tempObj = pFloor.getObj(pr + 1, pc);
				break;
			case "we":
				tempObj = pFloor.getObj(pr, pc - 1);
				break;
			case "ea":
				tempObj = pFloor.getObj(pr, pc + 1);
				break;
			default:
				cmd = "invalid";
				break;
			}
			if(cmd!="invalid")
				player.makeMove(cmd);
		}
		else if (curAction == "atk"){
			if(cmd != "a"){
				player.attack(cmd);
				curAction = "mov";
			}
		}
		
		if (!won && !("r".equals(cmd))){
			for (int r = 0; r < maxRow; r++){
				for (int c = 0; c < maxCol; c++){
					tempObj = pFloor.getObj(r, c);
					if (tempObj != null){
						if (tempObj.getType() == 1){
							Enemy enemy = (Enemy) tempObj;
							enemy.resetMoved();
						}
					}
				}
			}
			if (!ascended){
				for (int r = 0; r < maxRow; r++){
					for (int c = 0; c < maxCol; c++){
						tempObj = pFloor.getObj(r, c);
						if (tempObj != null){
							if (tempObj.getType() == 1){
								Enemy enemy = (Enemy) tempObj;
								enemy.randMove();
							}
						}
					}
				}
			}
			display();
			repaint();
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	private void display() {
		Player player = Player.getPlayer();
		pFloor.display();
		System.out.printf("Race: " + player.getRace() + " ");
		System.out.printf(" Gold " + player.getGold());
		System.out.println("\t\t\t\t\t\tFloor " + level);
		System.out.println("HP: " + player.getHp());
        System.out.println("MP: " + player.getMp());
		System.out.println("Atk: " + player.getAtk());
		System.out.println("Def: " + player.getDef());
        System.out.println("Str: " + player.getStr() + "  Dex: " + player.getDex() + " Int: " + player.getInt());
        System.out.println("Job: " + player.getJob().toLowerCase());
        System.out.println("s1. " + player.getS1() + "   s2. " + player.getS2());
        System.out.print("Action: ");
		pFloor.showMsg();
		System.out.println();
		pFloor.clearmsg();
	}
	
	//Still need this for more the mechanics
	private boolean checkStair(Floor floor, Obj obj, Player ply) {
		if (obj != null) {
			if (obj.getType() == 4) {
				if (level == 10) {
					System.out.println("You Win");
					return true;
				} else {
					level++;
					floor.ascend();
					floor.spawn();

					ply.resetBuff();
					floor.changemsg("Player character has spawned ");
					ascended = true;
					return true;
				}
			}
		}

		return false;
	}
}

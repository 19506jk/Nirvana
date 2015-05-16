package graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;

import mechanics.App;
import mechanics.character.Enemy;
import mechanics.character.Obj;
import mechanics.character.Player;
import mechanics.floor.Floor;

public class PlayerAction extends AbstractAction implements ActionListener {

	private String playerAction;
	final int maxRow = 25;
	final int maxCol = 79;
	private Floor pFloor = null;
	private int level = 1;
	private boolean ascended = false;
	private boolean won = false;

	public PlayerAction(String name, String action) {
		super(name);
		playerAction = action;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Player player = Player.getPlayer();
		pFloor = Floor.getInst();
		Obj tempObj;
		String cmd;

		cmd = playerAction;

		int pr = player.getpr(); // player R
		int pc = player.getpc(); // player C

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
		if (cmd != "invalid")
			player.makeMove(cmd);

		if (!won && !("r".equals(cmd))) {
			for (int r = 0; r < maxRow; r++) {
				for (int c = 0; c < maxCol; c++) {
					tempObj = pFloor.getObj(r, c);
					if (tempObj != null) {
						if (tempObj.getType() == 1) {
							Enemy enemy = (Enemy) tempObj;
							enemy.resetMoved();
						}
					}
				}
			}
			if (!ascended) {
				for (int r = 0; r < maxRow; r++) {
					for (int c = 0; c < maxCol; c++) {
						tempObj = pFloor.getObj(r, c);
						if (tempObj != null) {
							if (tempObj.getType() == 1) {
								Enemy enemy = (Enemy) tempObj;
								enemy.randMove();
							}
						}
					}
				}
			}
			MainPanel panel = App.getPanel();
			panel.repaint();
			display();
		}
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
		System.out.println("Str: " + player.getStr() + "  Dex: "
				+ player.getDex() + " Int: " + player.getInt());
		System.out.println("Job: " + player.getJob().toLowerCase());
		System.out
				.println("s1. " + player.getS1() + "   s2. " + player.getS2());
		System.out.print("Action: ");
		pFloor.showMsg();
		System.out.println();
		pFloor.clearmsg();
	}
}

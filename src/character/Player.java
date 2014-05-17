package character;

import enums.PotionType;
import floor.Floor;

public class Player implements Obj {
	int hp, atk, def, gold;
	String races;
	Race race = null;
	Buff buff = null;
	int[] Potiontrack = null;
	PlayerInteract action = null;
	
	
	static Player player = null;

	public Player(int choice) {
		gold = 0;
		Potiontrack = new int[16];
		buff = new Buff();
		action = new PlayerInteract();
		
		if (choice == 0)
		{
			hp = 140;
			atk = 20;
			def = 20;
			races = "Human";
			race = new Human();
		}
			
		if (choice == 1)
		{
			hp = 100;
			atk = 20;
			def = 30;
			races = "Dwarf";
			race = new Dwarf();
		}
		
		if (choice == 2)
		{
			hp = 140;
			atk = 30;
			def = 10;
			races = "Elves";
			race = new Elves();
		}

		if (choice == 3)
		{
			hp = 180;
			atk = 30;
			def = 25;
			races = "Orc";
			race = new Orc();
		}
		
		for (int i = 0; i < 6; i++)
		{
			Potiontrack[i] = 0;
		}
	}
	
	static void cleanup() {
		player = null;
	}
	
	public static Player getPlayer() {
		return getPlayer(0);
	}
	
	public static Player getPlayer(int choice) {
		if (player == null)
		{
			player = new Player(choice);
		}
		return player;
	}
	
	public static void resetPlayer() {
		if (player != null)
		{
			player = null;
		}
	}
	
	public void changehp(int num) {
		hp -= num;
	}
	
	public char getRep() {
		return '@';
	}

	public int getType() {
		return 0;
	}
	
	public void changegold(int amt) {
		gold += race.addgold(amt);
	}

	public void changebuff(PotionType type, int amt) {
		if (type == PotionType.RH || type == PotionType.PH)
		{
			if (races == "Elves")
			{
				hp += Math.abs(amt);
			}
			else
			{
				hp += amt;
			}

			if (hp > race.maxHP())
			{
				hp = race.maxHP();
			}
		}

		if (type == PotionType.BA || type == PotionType.WA)
		{
			race.addbuffatk(buff, amt);
		}
		if (type == PotionType.BD || type == PotionType.WD)
		{
			race.addbuffdef(buff, amt);
		}

	}

	public void trackPotion(int type) {
		Potiontrack[type] = 1;
	}

	public int checkPotion(int type) {
		return Potiontrack[type];
	}

	public void resetbuff() {
		buff = new Buff();
	}

	public int getatk() {
		return atk + buff.getatk();
	}

	public int getdef() {
		return def + buff.getdef();
	}

	public int gethp() { return hp; }

	public int getgold() { return gold; }

	public String getRace() { return races; }

	public int getpr() { return action.getRow(); }

	public int getpc() { return action.getCol(); }

	public void setFloor(Floor floor) {
		action.setFloor(floor);
	}

	public void setCoord(int r, int c) {
		action.setCurrentDir(r, c);
	}

	public void attack(String dir) {
		action.setDirection(dir);
		action.combat(this);
	}

	public void makemove(String dir) {
		action.setDirection(dir);
		action.move(this);
	}

	public void getPotion(String dir) {
		action.setDirection(dir);
		action.usePotion(this);
	}

}

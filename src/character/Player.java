package character;

import enums.PotionType;
import floor.Floor;

public class Player implements Obj {
	int hp, atk, def, gold;
    int str, dex, intel;
	String races;
	Race race = null;
	Buff buff = null;
	int[] potionTrack = null;
	PlayerInteract action = null;
	static Player player = null;

	private Player(int choice) {
		gold = 0;
		potionTrack = new int[16];
		buff = new Buff();
		action = new PlayerInteract();

        //TODO: need to have different stats for different race
		
		if (choice == 0)
		{
			hp = 140;
            str = 5;
            dex = 5;
            intel = 5;
			races = "Human";
			race = new Human();
		}
			
		if (choice == 1)
		{
			hp = 100;
            str = 5;
            dex = 5;
            intel = 5;
			races = "Dwarf";
			race = new Dwarf();
		}
		
		if (choice == 2)
		{
			hp = 140;
            str = 5;
            dex = 5;
            intel = 5;
			races = "Elves";
			race = new Elves();
		}

		if (choice == 3)
		{
			hp = 180;
            str = 5;
            dex = 5;
            intel = 5;
			races = "Orc";
			race = new Orc();
		}
		
		for (int i = 0; i < 6; i++)
		{
			potionTrack[i] = 0;
		}

        updateAtk();
        updateDef();
	}
	
	public static void resetPlayer() {
        if (player != null) {
            player = null;
        }
    }


    /*
     *  Setters
     */

    public void incStr(int amt) { str += amt; }

    public void incDex(int amt) { dex += amt; }

    public void incInt(int amt) { intel += amt; }
	
	public void changeGold(int amt) {
		gold += race.addgold(amt);
	}

	public void changebuff(PotionType type, int amt) {
		if (type == PotionType.RH || type == PotionType.PH)
		{
			if (races.equals("Elves"))
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

    public void changehp(int num) {
        hp -= num;
    }

    public void setFloor(Floor floor) {
        action.setFloor(floor);
    }

    public void setCoord(int r, int c) {
        action.setCurrentDir(r, c);
    }

    public void updateAtk() {
        atk = (int) Math.round(str * Math.pow(1.03, str));
    }

    public void updateDef() {
        def = (int) Math.round(dex * Math.pow(1.03, dex));
    }

    /*
     *  Getters
     */

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

    public char getRep() {
        return '@';
    }

    public int getType() {
        return 0;
    }

	public int getatk() {
		return atk + buff.getatk();
	}

	public int getdef() {
		return def + buff.getdef();
	}

	public int gethp() { return hp; }

	public int getgold() { return gold; }

    public int getStr() { return str; }

    public int getDex() { return dex; }

    public int getInt() { return intel; }

	public String getRace() { return races; }

	public int getpr() { return action.getRow(); }

	public int getpc() { return action.getCol(); }

	public void getPotion(String dir) {
		action.setDirection(dir);
		action.usePotion(this);
	}

    /*
        Methods
     */

    public void trackPotion(int type) {
        potionTrack[type] = 1;
    }

    public int checkPotion(int type) {
        return potionTrack[type];
    }

    public void resetbuff() {
        buff = new Buff();
    }

    public void attack(String dir) {
        action.setDirection(dir);
        action.combat(this);
    }

    public void makemove(String dir) {
        action.setDirection(dir);
        action.move(this);
    }

}

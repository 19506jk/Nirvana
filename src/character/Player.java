package character;

import character.races.*;
import floor.Floor;

public class Player implements Obj {
	int hp, atk, def, gold;
    int str, dex, intel;
	String races;
	Race race = null;
	Buff buff = null;
    PlayerInteract action = null;
	static Player player = null;

	private Player(int choice) {
		gold = 0;
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

		if (choice == 3) {
            hp = 180;
            str = 5;
            dex = 5;
            intel = 5;
            races = "Orc";
            race = new Orc();
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

	public void changeBuff(String name, int rounds, int amt) {
        if (name.equals("atk")) {
            buff.setAtk(amt);
        }
        else {
            buff.setDef(amt);
        }

        buff.setTimer(name, rounds);
        buff.setEffects(name, amt);
    }

    public void changeHp(int num) {
        hp -= num;
    }

    public void addHp(int num) {
        if (hp < race.maxHP()) {
            hp += num;
            if (hp > race.maxHP()) {
                hp = race.maxHP();
            }
        }
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

    public void setJob(int choice) { action.setJob(choice); }


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

	public int getAtk() {
		return atk + buff.getAtk();
	}

	public int getDef() {
		return def + buff.getDef();
	}

	public int getHp() { return hp; }

	public int getGold() { return gold; }

    public int getStr() { return str; }

    public int getDex() { return dex; }

    public int getInt() { return intel; }

	public String getRace() { return races; }

	public int getpr() { return action.getRow(); }

	public int getpc() { return action.getCol(); }

    public String getJob() { return action.getJobName(); }

    public String getS1() { return action.getS1(); }

    public String getS1Type() { return action.getS1Type(); }

    public String getS2() { return action.getS2(); }

    public String getS2Type() { return action.getS2Type(); }

    public String getS1Info() { return action.getS1Info(); }

    public String getS2Info() { return action.getS2Info(); }

    public String getPassive() { return action.getPassive(); }


    /*
        Methods
     */

    public void resetBuff() {
        buff = new Buff();
    }

    public void attack(String dir) {
        buff.countDown();
        action.setDirection(dir);
        action.combat(this);
    }

    public void makeMove(String dir) {
        buff.countDown();
        action.setDirection(dir);
        action.move(this);
    }

    public void castSkill(String dir, int choice) {
        buff.countDown();
        action.setDirection(dir);
        action.castSkill(this, choice);
    }
}

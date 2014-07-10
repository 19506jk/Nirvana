package character;

import character.races.*;
import floor.Floor;

public class Player implements Obj {
	int hp, atk, def, gold, mp;
    int str, dex, intel;
	String races;
	Race race = null;
	Buff buff = null;
    PlayerInteract action = null;
	static Player player = null;

	private Player(int choice) {
		gold = 0;
		action = new PlayerInteract();

        // Initialize buff timer
        buff = new Buff();
        buff.setTimer("atk", 0);
        buff.setTimer("def", 0);
        buff.setEffects("atk", 0);
        buff.setEffects("def", 0);

        //TODO: need to have different stats for different race
		
		if (choice == 0)
		{
			hp = 140;
            str = 10;
            dex = 10;
            intel = 10;
			races = "Human";
			race = new Human();
		}
			
		if (choice == 1)
		{
			hp = 100;
            str = 9;
            dex = 11;
            intel = 9;
			races = "Dwarf";
			race = new Dwarf();
		}
		
		if (choice == 2)
		{
			hp = 140;
            str = 7;
            dex = 8;
            intel = 13;
			races = "Elves";
			race = new Elves();
		}

		if (choice == 3) {
            hp = 180;
            str = 11;
            dex = 8;
            intel = 7;
            races = "Orc";
            race = new Orc();
        }

        updateAtk();
        updateDef();
        updateMp();
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

    public void changeMp(int num) {
        mp -= num;

        if (mp < 0) {
            mp = 0;
        }
    }

    public void addHp(int num) {
        if (hp < race.maxHp()) {
            hp += num;
            if (hp > race.maxHp()) {
                hp = race.maxHp();
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

    public void updateMp() { mp = intel * 8; }

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

    public int getMaxHp() { return race.maxHp(); }

    public int getMp() { return mp; }

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

    public int checkBuffTimer(String name) {
        return buff.getBuffTimer(name);
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

    public void listSkills() {
        System.out.println("Skills:");
        System.out.println("-------------------------------------");
        System.out.println("Passive: " + getPassive());
        System.out.println();
        System.out.println("1. " + getS1());
        System.out.println("Type: " + getS1Type());
        System.out.println(getS1Info());
        System.out.println();
        System.out.println("2. " + getS2());
        System.out.println("Type: " + getS2Type());
        System.out.println(getS2Info());
        System.out.println("-------------------------------------");
    }
}

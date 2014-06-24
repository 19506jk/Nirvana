package character;

import floor.Floor;
import java.util.Random;
import enums.MobType;

public class Enemy implements Obj {
	
	MobType type;
	int hp, atk, def, gold, rep, level, stunned;
    String name;
	boolean moved;
	MobInteract action = null;
	
	public Enemy(int type) {
		this.type = MobType.values()[type];
		moved = false; 
		action = new MobInteract();
        stunned = 0;

		if (type == MobType.VAMP.ordinal())
		{
			hp = 50;
			atk = 25;
			def = 25;
            level = 3;
			rep = 'V';
            name = "Vampire";
		}
		if (type == MobType.WEREW.ordinal())
		{
			hp = 120;
			atk = 30;
			def = 5;
			rep = 'W';
            name = "Werewolf";
		}
		if (type == MobType.TROLL.ordinal())
		{
			hp = 120;
			atk = 25;
			def = 15;
            level = 4;
			rep = 'T';
            name = "Troll";
		}
		if (type == MobType.GOBLIN.ordinal())
		{
			hp = 70;
			atk = 5;
			def = 10;
            level = 1;
			rep = 'N';
            name = "Goblin";
		}
		if (type == MobType.PHOEN.ordinal())
		{
			hp = 50;
			atk = 35;
			def = 20;
            level = 5;
			rep = 'X';
            name = "Phoenix";
		}
		if (type == MobType.MERCH.ordinal())
		{
			hp = 30;
			atk = 70;
			def = 5;
            level = 3;
			rep = 'M';
            name = "Merchant";
		}
		if (type == MobType.DRAGON.ordinal())
		{
			hp = 150;
			atk = 20;
			def = 20;
            level = 2;
			rep = 'D';
            name = "Dragon";
		}
        gold = calcGold(level);
	}

    /*
        Getters
     */

	public void changehp(int amt) { hp -= amt;}

	public int getAtk() { return atk; }

	public int getDef() { return def; }

    public int getLvl() { return level; }

    public char getRep() {
        return (char) rep;
    }

    public int getType() {
        return 1;
    }

    public int getHP() { return hp; }

    public String getName() { return name; }

    public int getStunned() { return stunned; }

    /*
        Setters
     */

    public void setCoord(int r, int c) {
        action.setCurrentDir(r, c);
    }

    public void setFloor(Floor f) { action.setFloor(f); }

    public void setStunned(int n) { stunned = n; }

    /*
        Methods
     */

	public void randmove() {
		if (moved)
		{
			return;
		}
        else if (stunned > 0) {
            stunned--;
            return;
        }
		else if (action.scan() != null)
		{
			action.combat(this);
		}
		else
		{
			action.move(this);
		}
		moved = true;
	}

	public void attack() {
		action.combat(this);
	}

	public int giveGold() { return gold; }

	public void resetMoved() {
		moved = false;
	}

    public int calcGold(int lvl) {
        Random rand = new Random();
        return (int) (rand.nextGaussian() * (0.7 * lvl) + (5 * lvl));
    }

}

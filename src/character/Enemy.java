package character;

import floor.Floor;
import java.util.Random;

public class Enemy implements Obj {
	
	MobType type;
	int hp, atk, def, gold, rep, level;
	boolean moved;
	MobInteract action = null;
	
	public enum MobType { VAMP, WEREW, TROLL, GOBLIN, PHOEN, MERCH, DRAGON };

	public char getRep() {
		return (char) rep;
	}

	public int getType() {
		return 1;
	}

    public int calcGold(int lvl) {
        Random rand = new Random();
        return (int) (rand.nextGaussian() * (0.7 * lvl) + (5 * lvl));
    }

	public Enemy(int type) {
		this.type = MobType.values()[type];
		moved = false; 
		action = new MobInteract();

		if (type == MobType.VAMP.ordinal())
		{
			hp = 50;
			atk = 25;
			def = 25;
            level = 3;
			rep = 'V';
		}
		if (type == MobType.WEREW.ordinal())
		{
			hp = 120;
			atk = 30;
			def = 5;
			rep = 'W';
		}
		if (type == MobType.TROLL.ordinal())
		{
			hp = 120;
			atk = 25;
			def = 15;
            level = 4;
			rep = 'T';
		}
		if (type == MobType.GOBLIN.ordinal())
		{
			hp = 70;
			atk = 5;
			def = 10;
            level = 1;
			rep = 'N';
		}
		if (type == MobType.PHOEN.ordinal())
		{
			hp = 50;
			atk = 35;
			def = 20;
            level = 5;
			rep = 'X';
		}
		if (type == MobType.MERCH.ordinal())
		{
			hp = 30;
			atk = 70;
			def = 5;
            level = 3;
			rep = 'M';
		}
		if (type == MobType.DRAGON.ordinal())
		{
			hp = 150;
			atk = 20;
			def = 20;
            level = 2;
			rep = 'D';
		}
        gold = calcGold(level);
	}

	public void changehp(int amt) { hp -= amt;}

	public int getAtk() { return atk; }

	public int getDef() { return def; }

    public int getLvl() { return level; }

	public void randmove() {
		if (moved)
		{
			return;
		}

		if (action.scan() != null)
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

	public int getHP() { return hp; }

	public int giveGold() { return gold; }

	public void setCoord(int r, int c) {
		action.setCurrentDir(r, c);
	}

	public void setFloor(Floor f) { action.setFloor(f); }

	public void resetMoved() {
		moved = false;
	}

}

package character;

import floor.Floor;

public class Enemy implements Obj {
	
	MobType type;
	int hp, atk, def, gold, rep, exp;
	boolean moved;
	MobInteract action = null;
	
	public enum MobType { VAMP, WEREW, TROLL, GOBLIN, PHOEN, MERCH, DRAGON };

	public char getRep() {
		return (char) rep;
	}

	public int getType() {
		return 1;
	}

	public Enemy(int type) {
		this.type = MobType.values()[type];
		gold = 1;
		moved = false; 
		action = new MobInteract();

        exp = 10; //TODO: different mob types should give different amount of exp
	
		if (type == MobType.VAMP.ordinal())
		{
			hp = 50;
			atk = 25;
			def = 25;
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
			rep = 'T';
		}
		if (type == MobType.GOBLIN.ordinal())
		{
			hp = 70;
			atk = 5;
			def = 10;
			rep = 'N';
		}
		if (type == MobType.PHOEN.ordinal())
		{
			hp = 50;
			atk = 35;
			def = 20;
			rep = 'X';

		}
		if (type == MobType.MERCH.ordinal())
		{
			hp = 30;
			atk = 70;
			def = 5;
			gold = 0;
			rep = 'M';
		}
		if (type == MobType.DRAGON.ordinal())
		{
			hp = 150;
			atk = 20;
			def = 20;
			gold = 0;
			rep = 'D';
		}
	}

	public void changehp(int amt) { hp -= amt;}

	public int getAtk() { return atk; }

	public int getDef() { return def; }

    public int getExp() { return exp; }

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

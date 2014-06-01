package character;

import enums.MobType;

public class Merchant extends Enemy {
	static boolean attacked = false;

	public Merchant() {
		super(MobType.MERCH.ordinal());
	}
	
	public void changestatus() { attacked = true;}

	public boolean status() { return attacked; }

	public void randmove() {
		if (moved == true)
		{
			return;
		}

		if (action.scan() != null && attacked == true)
		{
			action.combat(this);
		}

		else
		{
			action.move(this);
		}
		moved = true;
	}

}

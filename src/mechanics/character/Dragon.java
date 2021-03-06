package mechanics.character;

import mechanics.enums.MobType;

class Dragon extends Enemy {
	boolean playernear;
	Dhorde horde = null;

	public Dragon(Dhorde dh) {
		super(MobType.DRAGON.ordinal());
		playernear = false;
		horde = dh;
	}
	
	void changestatus() { playernear = !playernear; }
	
	public void Notify() {
		   horde.changestatus();
		}

	public void randMove() {
			if (action.scan() != null)
			{
				playernear = true;
				action.combat(this);
			}
		}

}

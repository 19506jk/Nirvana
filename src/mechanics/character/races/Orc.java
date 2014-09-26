package mechanics.character.races;

import mechanics.character.Buff;

public class Orc implements Race {
	int goldcount;
	
	public Orc() {
		goldcount = 2;
	}

	public int addgold(int golds) {
		if (golds == 1)
		{
			goldcount++;
			if (goldcount % 2 == 0)
			{
				return 1;
			}
			else
			{
				return 0;
			}
		}
		else
		{
			return golds / 2;
		}
	}

	public void addbuffatk(Buff b, int amt) {
		b.setAtk(amt);
	}

	public void addbuffdef(Buff b, int amt) {
		b.setDef(amt);
	}

}

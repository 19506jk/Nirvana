package character.races;

import character.Buff;

public class Elves implements Race {

	public int addgold(int golds) {
		return golds;
	}

	public int maxHp() {
		return 140;
	}

	public void addbuffatk(Buff b, int amt) {
		b.setAtk(Math.abs(amt));

	}

	public void addbuffdef(Buff b, int amt) {
		b.setDef(Math.abs(amt));

	}

}

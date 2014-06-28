package character.races;

import character.Buff;

public class Dwarf implements Race {

	public int addgold(int golds) {
		return golds;
	}

	public int maxHP() {
		return 100;
	}

	public void addbuffatk(Buff b, int amt) {
		b.setAtk(amt);
	}

	public void addbuffdef(Buff b, int amt) {
		b.setDef(amt);
	}

}

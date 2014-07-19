package character.races;

import character.Buff;

public class Human implements Race {

	public int addgold(int golds) {
		return golds;
	}

	public void addbuffatk(Buff b, int amt) {
		b.setAtk(amt);
	}

	public void addbuffdef(Buff b, int amt) {
		b.setDef(amt);
	}

}

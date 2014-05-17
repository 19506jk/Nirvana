package character;

public class Dwarf implements Race {

	public int addgold(int golds) {
		return golds * 2;
	}

	public int maxHP() {
		return 100;
	}

	public void addbuffatk(Buff b, int amt) {
		b.setatk(amt);
	}

	public void addbuffdef(Buff b, int amt) {
		b.setdef(amt);
	}

}

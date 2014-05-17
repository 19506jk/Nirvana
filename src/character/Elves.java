package character;

class Elves implements Race {

	public int addgold(int golds) {
		return golds;
	}

	public int maxHP() {
		return 140;
	}

	public void addbuffatk(Buff b, int amt) {
		b.setatk(Math.abs(amt));

	}

	public void addbuffdef(Buff b, int amt) {
		b.setdef(Math.abs(amt));

	}

}

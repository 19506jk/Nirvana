package character;

class Human implements Race {

	public int addgold(int golds) {
		return golds;
	}

	public int maxHP() {
		return 140;
	}

	public void addbuffatk(Buff b, int amt) {
		b.setatk(amt);
	}

	public void addbuffdef(Buff b, int amt) {
		b.setdef(amt);
	}

}

package character;

class Buff {
	int atk, def;
	
	public Buff() {
		atk = 0;
		def = 0;
	}
	
	public void setatk(int amt) {
		atk += amt;
	}

	public void setdef(int amt) {
		def += amt;
	}

	public int getatk() {
		return atk;
	}

	public int getdef() {
		return def;
	}

}

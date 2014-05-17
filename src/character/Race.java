package character;

public interface Race {
	public int addgold(int golds);
	public int maxHP();
	public void addbuffatk(Buff b, int amt);
	public void addbuffdef(Buff b, int amt);
}

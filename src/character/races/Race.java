package character.races;

import character.Buff;

public interface Race {
	public int addgold(int golds);
	public void addbuffatk(Buff b, int amt);
	public void addbuffdef(Buff b, int amt);
}

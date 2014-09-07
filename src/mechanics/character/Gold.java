package mechanics.character;

public class Gold extends Treasure {
	int type, value;
	
	public Gold(int type) {
		this.type = type;
		
		if (type == 0)
		{
			value = 1;
		}
		if (type == 1)
		{
			value = 2;
		}
		if (type == 2)
		{
			value = 4;
		}
		if (type == 3)
		{
			value = 6;
		}
	}
	
	public char getRep() {
		return 'G';
	}
	
	public int giveGold() { return value; }
	
	public int getGoldType() { return type; }

}

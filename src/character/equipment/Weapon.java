package character.equipment;

import character.Enemy;
import character.Player;

public interface Weapon extends Equipment {
	
	public int getAtk();
	
	public void special(Player player, Enemy enemy, int itemNum);
	
}

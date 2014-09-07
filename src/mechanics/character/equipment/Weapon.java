package mechanics.character.equipment;

import mechanics.character.Enemy;
import mechanics.character.Player;

public interface Weapon extends Equipment {
	
	public int getAtk();
	
	public void special(Player player, Enemy enemy, int itemNum);
	
}

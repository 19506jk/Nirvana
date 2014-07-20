package character.equipment;

import character.Player;

public interface Equipment {

	public boolean isEquipable(Player player);
	
	public boolean isUnlocked();
	
	public void unlockEquip();
	
}

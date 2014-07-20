package character.equipment;

import character.Enemy;
import character.Player;

public enum Swords implements Weapon {
	WOODEN_SWORD(1, new ReqStat(3,1,0), true), 
	DAGGER(3, new ReqStat(7,2,0), true), 
	IRON_SPATHA(6, new ReqStat(11,4,0), true), 
	STEEL_SHORTSWORD(10, new ReqStat(16,5,0), true);
	
	private int atk;
	private ReqStat stat;
	private boolean unlocked;
	
	private Swords(int atk, ReqStat stat, boolean unlocked){
		this.atk = atk;
		this.stat = stat;
		this.unlocked = unlocked;
	}
	
	public int getAtk(){ return atk; }
	
	//Weapons currently have no special effects
	@Override
	public void special(Player player, Enemy enemy, int itemNum){
		return;
	}
	
	@Override
	public boolean isEquipable(Player player){
		return stat.meetsRequirement(new ReqStat(player.getStr(),player.getDex(),player.getInt()));
	}

	@Override
	public boolean isUnlocked() {
		return unlocked;
	}

	@Override
	public void unlockEquip() { unlocked = true; }
}

package mechanics.character.equipment;

import mechanics.character.Enemy;
import mechanics.character.Player;

public enum ArmorSet implements Equipment{
	ADVENTURER(3, new ReqStat(0,0,0), true),
	SQUIRE(8, new ReqStat(7,3,0), true),
	THIEF(5, new ReqStat(3,5,0), true),
	STUDENT(3, new ReqStat(0,2,8), true),
	PROTECTOR(14, new ReqStat(12,6,0), true),
	BANDIT(9, new ReqStat(7,11,0), true),
	ASSOCIATE(5, new ReqStat(0,5,15), true);
	
	private int def;
	private ReqStat stat;
	private boolean unlocked;
	
	private ArmorSet(int def, ReqStat stat, boolean unlocked){
		this.def = def;
		this.stat = stat;
		this.unlocked = unlocked;
	}
	
	public int getDef() { return def; }
	
	@Override
	public boolean isUnlocked() { return unlocked; }
	
	@Override
	public void unlockEquip() { unlocked = true; }
	
	public void special(Player player, Enemy enemy, int itemNum){
		return;
	}
	
	@Override
	public boolean isEquipable(Player player){
		return stat.meetsRequirement(new ReqStat(player.getStr(),player.getDex(),player.getInt()));
	}
}

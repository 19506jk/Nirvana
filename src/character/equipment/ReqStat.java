package character.equipment;

/**
 * A class that is used to store the required stat for a specific equipment.
 * To construct the class: ReqStat(str, dex, intel)
 * @author rainybreeze777
 * @param str the required strength point
 * @param dex the required dexterity point
 * @param intel the required intelligence point
 */
public class ReqStat {
	
	private int str, dex, intel;
	
	public ReqStat(int str, int dex, int intel){
		this.str = str;
		this.dex = dex;
		this.intel = intel;
	}
	
	public int getStr() { return str; }
	
	public int getDex() { return dex; }
	
	public int getInt() { return intel; }

	public boolean meetsRequirement(ReqStat stat) {
		if (stat.str >= str && 
				stat.dex >= dex && 
				stat.intel >= intel){
			return true;
		}
		else {
			return false;
		}
	}
	
}

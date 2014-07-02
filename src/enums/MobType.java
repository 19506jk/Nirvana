package enums;

/**
 * Created by Sai on 2014/5/31.
 */
public enum MobType { VAMP(0), WEREW(1), TROLL(2), GOBLIN(3), PHOEN(4), DRAGON(5);
	int numeric;

	private MobType(int val){
		numeric = val;
	}
	
	public int getNum(){ return numeric; }
	
	public static MobType numToType(int value){
		switch(value){
		case 0:
			return VAMP;
		case 1:
			return WEREW;
		case 2:
			return TROLL;
		case 3: 
			return GOBLIN;
		case 4:
			return PHOEN;
		case 5:
			return DRAGON;
		default:
			return null;
		}
	}	
}

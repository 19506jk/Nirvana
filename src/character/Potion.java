package character;

import enums.PotionType;

public class Potion extends Item {
	int val;
	PotionType effect = null;
	String name;
	
	public Potion(String type) {
		count = 1;
		
		if (type == "RH")
		{
			effect = PotionType.RH;
			val = 50;
			name = "RH";
		}
		
		if (type == "RM")
		{
			effect = PotionType.RM;
			val = 50;
			name = "RM";
		}
	}

    /*
        Getters
     */
	public PotionType getEffect() { return effect; }

	public int getVal() { return val; }
	
}

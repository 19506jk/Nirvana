package inv;

import enums.PotionType;

public class Potion extends Item {
	int val;
	PotionType effect = null;

	public Potion(String type) {
		count = 1;
		family = "potion";

		if (type == "RH") {
			effect = PotionType.RH;
			val = 50;
			name = "RH";
		}

		if (type == "RM") {
			effect = PotionType.RM;
			val = 50;
			name = "RM";
		}

		if (type == "RHM") {
			effect = PotionType.RHM;
			val = 50;
			name = "RHM";
		}
	}

	/*
	 * Getters
	 */
	public PotionType getEffect() {
		return effect;
	}

	public int getVal() {
		return val;
	}

}

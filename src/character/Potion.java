package character;

import enums.PotionType;

public class Potion implements Obj {
	int val;
	PotionType effect = null;
	String name;
	
	public Potion() {
		int roll = (int) (Math.random() * 6);
		if (roll == PotionType.RH.ordinal())
		{
			effect = PotionType.RH;
			int k = (int) (Math.random() * 11);
			val = k;
			name = "RH";
		}
		
		if (roll == PotionType.BA.ordinal())
		{
			effect = PotionType.BA;
			val = 5;
			name = "BA";
		}

		if (roll == PotionType.BD.ordinal())
		{
			effect = PotionType.BD;
			val = 5;
			name = "BD";
		}

		if (roll == PotionType.PH.ordinal())
		{
			effect = PotionType.PH;
			int k = -1 * (int) (Math.random() * 11);
			val = k;
			name = "PH";
		}

		if (roll == PotionType.WA.ordinal())
		{
			effect = PotionType.WA;
			val = -5;
			name = "WA";
		}

		if (roll == PotionType.WD.ordinal())
		{
			effect = PotionType.WD;
			val = -5;
			name = "WD";
		}
	}

    /*
        Getters
     */
			
	public int getType() { return 3; }

	public PotionType getEffect() { return effect; }

	public int getVal() { return val; }

	public char getRep() { return 'P'; }

	public String getName() { return name; }

}

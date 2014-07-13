package town;

import character.Player;

public class Inn {
	
	public static void purchase(){
		int cost = 20;
		int maxHp = Player.getPlayer().getMaxHp();
		int mpRegen = 50;
		
		Player.getPlayer().addHp(maxHp);
		Player.getPlayer().changeMp(-mpRegen);
		Player.getPlayer().changeGold(-cost);
		
		System.out.println("Hp and Mp restored.");
	}
}

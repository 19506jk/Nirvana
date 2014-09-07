package mechanics.town;

import mechanics.character.Player;

public class Inn {
	
	public static void purchase(){
		int cost = 20;
		int maxHp = Player.getPlayer().getMaxHp();
		int mpRegen = Player.getPlayer().getMaxMp();

        if (Player.getPlayer().getGold() >= cost) {
            Player.getPlayer().addHp(maxHp);
            Player.getPlayer().addMp(mpRegen);
            Player.getPlayer().changeGold(-cost);
            System.out.println("Hp and Mp restored.");
        }

        else {
            System.out.println("Player does not have enough gold!");
        }

	}
}

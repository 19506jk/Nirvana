package inv;


import java.util.Scanner;
import character.Player;

public class Inventory {
	public static Scanner input = new Scanner(System.in);
	
	public static void showItems(){
		int cmd;
		int hpCount = Player.getPlayer().getPotionCount("RH");
		int mpCount = Player.getPlayer().getPotionCount("RM");
		
		System.out.println("You have the following items:");
		System.out.println("----------------------------------");
		System.out.println("1. Health Potions: " + hpCount);
		System.out.println("2. Mana Potions: " + mpCount);
		System.out.println("----------------------------------");
		System.out.println("Would you like to use one of the item?");
		
		cmd = input.nextInt();
		
		switch (cmd){
			case 1:
				Player.getPlayer().drink("RH");
				break;
			case 2:
				Player.getPlayer().drink("RM");
				break;
			default:
				break;
		}
	}

}

package inv;

import java.util.Scanner;

import character.Player;

public class ItemInteract {
	public static Scanner input = new Scanner(System.in);
	static Inventory i = new Inventory();  //initialization with one HP and one MP potion

	public static void showItems() {
		int cmd;
		i.addItem("RHM", 0); //You can add certain potions are already defined, like this HP/MP potion

		System.out.println("You have the following items:");
		System.out.println("----------------------------------");
		System.out.println("1. Health Potions: " + i.getItem("RH").getCount());
		System.out.println("2. Mana Potions: " + i.getItem("RM").getCount());
		System.out.println("3. Purple Potions: " + i.getItem("RHM").getCount());
		System.out.println("----------------------------------");
		System.out.println("Would you like to use one of the item?");
		System.out.println("Type 87 for extra potions.");

		cmd = input.nextInt();

		switch (cmd) {
		case 1:
			useItem("RH");
			break;
		case 2:
			useItem("RM");
			break;
		case 3:
			useItem("RHM");
			break;
		case 87:
			i.addItem("RH", 1);  //To show case the add potion function
			i.addItem("RM", 1);
			i.addItem("RHM", 1);
			break;
		default:
			break;
		}
	}

	private static void useItem(String item) {
		Player p = Player.getPlayer();
		Item temp = i.getItem(item);
		Potion pot1 = (Potion) temp;

		if (temp.getCount() > 0) {
			if (pot1.getName().equals("RH"))
				p.addHp(pot1.getVal());
			else if (pot1.getName().equals("RM"))
				p.addMp(pot1.getVal());
			else{
				p.addHp(pot1.getVal());
				p.addMp(pot1.getVal());
			}
				
			temp.setCount(temp.getCount() - 1);
		}
	}
}

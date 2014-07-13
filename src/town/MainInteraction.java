package town;

import java.util.Scanner;
import town.Trainer;

public class MainInteraction {

	public static Scanner input = new Scanner(System.in);
	
	public static void visitShop(){
		int cmd;
		
		System.out.println("Which shop would you like to visit?");
		System.out.println("1. Attribute");
		System.out.println("2. Inn");
	
		cmd = input.nextInt();
		
		switch (cmd){
			case 1: 
				Trainer.purchase();
				break;
			case 2:
				Inn.purchase();
				break;
			default:
				break;
		}
	}
}
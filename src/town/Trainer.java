package town;

import java.lang.Math;
import character.Player;

public class Trainer {

	static Trainer thisTrainer = null; //TODO: this field is not used
	private static int currStr, currDex, currInt;
	private static int strPrice, dexPrice, intPrice;
	
	private static int priceCalc(double attri){
		return (int) Math.pow(Math.log(attri) + attri, 2)/5;
	}
	
	private static void setUp(){
		currStr = Player.getPlayer().getStr();
		currDex = Player.getPlayer().getDex();
		currInt = Player.getPlayer().getInt();
	
		strPrice = priceCalc((double) currStr);
		dexPrice = priceCalc((double) currDex);
		intPrice = priceCalc((double) currInt);
	}
	
	public static void purchase(){
		int choice;
		int playerGold;
		setUp();
		do{
			playerGold = Player.getPlayer().getgold();
			
			displayStat();
			System.out.println();
			System.out.println("Gold: " + playerGold);
			System.out.println("Which Attribute would you like to increase?");
			System.out.println("0. Leave");
			System.out.println("1. Strength: " + strPrice + " gold");
			System.out.println("2. Dexterity: " + dexPrice + " gold");
			System.out.println("3. Intelligence: " + intPrice + " gold");
			choice = MainInteraction.input.nextInt();

			switch (choice){
				case 1:
					if (playerGold < strPrice){
						System.out.println("You do not have enough Gold!");
					}
					else {
						Player.getPlayer().changeGold(-strPrice);
						Player.getPlayer().incStr(1);
						currStr++;
						strPrice = priceCalc((double) currStr);
						System.out.println("Strength has been Increased");
					}
					break;
				case 2:
					if (playerGold < dexPrice){
						System.out.println("You do not have enough Gold!");
					}
					else {
						Player.getPlayer().changeGold(-dexPrice);
						Player.getPlayer().incDex(1);
						currDex++;
						dexPrice = priceCalc((double) currDex);
						System.out.println("Dexterity has been Increased");
					}
					break;
				case 3:
					if (playerGold < intPrice){
						System.out.println("You do not have enough Gold!");
					}
					else {
						Player.getPlayer().changeGold(-intPrice);
						Player.getPlayer().incInt(1);
						currInt++;
						intPrice = priceCalc((double) currInt);
						System.out.println("Intelligence has been Increased");
					}
					break;
				default:
					break;
			}
		}while(choice != 0);
        Player.getPlayer().updateAtk();
        Player.getPlayer().updateDef();
	}
	
	public static void displayStat(){
		System.out.println("Current Status: ");
		System.out.println("Strength: " + currStr);
		System.out.println("Dexterity: " + currDex);
		System.out.println("Intelligence: " + currInt);
	}
}

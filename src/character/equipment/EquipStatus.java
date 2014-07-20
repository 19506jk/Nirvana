package character.equipment;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import character.Player;

public class EquipStatus {
	
	private Weapon equippedWeapon;
	private ArmorSet equippedArmor;
	private Player player;
	private static EquipStatus status;
	
	private EquipStatus(){
		equippedWeapon = null;
		equippedArmor = ArmorSet.ADVENTURER;
		player = Player.getPlayer();
	}
	
	public static EquipStatus getEquipStatus(){
		if (status == null){
			status = new EquipStatus();
		}
		return status;
	}
	
	public static Scanner input = new Scanner(System.in);
	
	public int getEquipAtk(){
		if (equippedWeapon == null)
			return 0;
		else
			return equippedWeapon.getAtk();
	}
	
	public int getEquipDef(){
		if (equippedArmor == null)
			return 0;
		else
			return equippedArmor.getDef();
	}
	
	public void equipMainMenu(){
		System.out.println("Currently equipped:");
		System.out.println("Main hand: " + ((equippedWeapon == null) ? "Empty" : equippedWeapon));
		System.out.println("Armor: " + ((equippedArmor == null) ? "Empty" : equippedArmor));
		System.out.println("View which type?");
		System.out.println("1. Swords");
		System.out.println("2. Armor Set");

		int inputChar = input.nextInt();
		switch(inputChar){
			case 1:
				equipSubMenu(unlockedList(Swords.class));
				break;
			case 2:
				equipSubMenu(unlockedList(ArmorSet.class));
				break;
			default:
				break;
		}
	}
	
	private void equipSubMenu(List<Equipment> equipList){
		
		int menuIndex = 0;
		
		System.out.println("Equip which one?");
		System.out.println(menuIndex++ + ". Exit");
		for (Equipment e : equipList){
			System.out.println(menuIndex++ + ". " + e);
		}
		
		boolean success = false;
		int choice = input.nextInt();
		if (choice == 0){
			return;
		}
		if (equipList.get(choice-1) instanceof Weapon){
			Weapon weapon = (Weapon) equipList.get(choice-1);
			if (weapon.isEquipable(player)){
				equippedWeapon = weapon;
				success = true;
			}
		}
		else if (equipList.get(choice-1) instanceof ArmorSet) {
			ArmorSet armor = (ArmorSet) equipList.get(choice-1);
			if (armor.isEquipable(player)){
				equippedArmor = armor;
				success = true;
			}
		}
		
		if (success)
			System.out.println(equipList.get(choice-1) + " equipped.");
		else {
			System.out.println("You do not meet the required stats!");
		}
	}
	
	private List<Equipment> unlockedList(Class<? extends Equipment> equipClass){
		List<Equipment> unlockedList = new ArrayList<Equipment>();
		for (Equipment oneEquip : equipClass.getEnumConstants()){
			if (oneEquip.isUnlocked()){
				unlockedList.add(oneEquip);
			}
		}
		
		return unlockedList;
	}
}

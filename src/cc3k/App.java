package cc3k;

import character.*;
import floor.*;
import java.util.*;
import town.MainInteraction;


/*
 * CC3K Ver 0.0.1
 * Danny & Louis version
 * This is the main App class, still under construction
 * All the required class still needs to be converted from C++ to java
 * Lots of static methods from C++ requires to be fixed, but not sure how
 * due to the design of those static class methods have not been implemented yet.
 * Game CANNOT be run.
 */
public class App {

	final int maxRow = 25;
	final int maxCol = 79;
	private int level = 1;
	private boolean won = false;
	private boolean ascended = false;
	private Floor pFloor = null;
	Scanner input = new Scanner(System.in);

	
	private int calcScore(Player player){
		int score;
		if (player.getRace().equals("Human")){
			score = (int) (player.getgold() * 1.5);
		}
		else
        {
            score = player.getgold();
        }

		return score;
	}
	
	private boolean checkStair(Floor floor,Obj obj, Player ply){
		if (obj != null){
			if(obj.getType() == 4){
				if (level == 10){
					System.out.println("You Win");
					System.out.println("Score: " + calcScore(ply));
					won = true;
					return true;
				}
				else{
					level++;
					floor.resetFloor();
					floor.spawn();

					ply.resetbuff();
					floor.changemsg("Player character has spawned ");
					ascended = true;
					return true;
				}
			}
		}

		return false;
	}
	
	private boolean direcTrap(String direc){
		
		if(!"no".equals(direc) && 
				!"we".equals(direc) && 
				!"so".equals(direc) && 
				!"ea".equals(direc) && 
				!"nw".equals(direc) &&
				!"ne".equals(direc) &&
				!"se".equals(direc) &&
				!"sw".equals(direc)){
			System.err.println("Bad Input");
			return false;
		}
		else
			return true;
	}
	
	private void startGame(){

		String cmd;
		String direc;
		Player player;
		Obj tempObj;

		
		player = newGame();
		pFloor = Floor.getInst(); 

		while(true){
			
			//
			// Checking for death/victory
			//
			if (player.gethp() <= 0){
				System.out.println("You Died");
				System.out.println("Score: " + calcScore(player));
				System.out.println("Play again? y/n");
							
				cmd = input.next();
							
				if ("y".equals(cmd))
					cmd = "r";
				else
					cmd = "q";
			}
			else{
				if(won){
					won = false;
					System.out.println("Play again? y/n");

					cmd = input.next(); 

					if ("y".equals(cmd))
						cmd = "r";
					else
						cmd = "q";
				}
				else
					cmd = input.next();
			}
			
			//
			//cmd recognition and action
			//
			
			if ("q".equals(cmd)){
				pFloor.resetFloor();
				break;
			}
			else if ("r".equals(cmd)){
				pFloor.resetFloor();
				player = newGame();
				ascended = false;
			}
			else if ("town".equals(cmd)){
				MainInteraction.visitShop();
			}
            else if ("add".equals(cmd))
            {
                String type = input.next();
                String amount = input.next();

                if (isInteger(amount)) {
                    int amt = Integer.parseInt(amount);
                }

                else
                {
                    System.err.println("Bad Input: add [attribute type] [amount]");
                    continue;
                }
            }

			else if ("u".equals(cmd) || "a".equals(cmd)){
				direc = input.next();
				while(!direcTrap(direc)){
					direc = input.next();
				}
				ascended = false;
				if ("u".equals(cmd)){
					player.getPotion(direc);
				}
				else
					player.attack(direc);
			}
			else{
				if(direcTrap(cmd)){
					int r = player.getpr();
					int c = player.getpc();
					if (cmd.equals("no"))
						tempObj = pFloor.getObj(r-1, c);
					else if (cmd.equals("ne"))
						tempObj = pFloor.getObj(r-1, c+1);
					else if (cmd.equals("ea"))
						tempObj = pFloor.getObj(r, c+1);
					else if (cmd.equals("se"))
						tempObj = pFloor.getObj(r+1, c+1);
					else if (cmd.equals("so"))
						tempObj = pFloor.getObj(r+1, c);
					else if (cmd.equals("sw"))
						tempObj = pFloor.getObj(r+1, c-1);
					else if (cmd.equals("we"))
						tempObj = pFloor.getObj(r, c-1);
					else
						tempObj = pFloor.getObj(r-1, c-1);
					if(!checkStair(pFloor, tempObj, player)){
						player.makemove(cmd);
						ascended = false;
					}				
				}   
				else
					continue;
			}
			if (!won && !("r".equals(cmd))){
				for (int r = 0; r < maxRow; r++){
					for (int c = 0; c < maxCol; c++){
						tempObj = pFloor.getObj(r, c);
						if (tempObj != null){
							if (tempObj.getType() == 1){
								Enemy enemy = (Enemy) tempObj;
								enemy.resetMoved();
							}
						}
					}
				}
				if (!ascended){
					for (int r = 0; r < maxRow; r++){
						for (int c = 0; c < maxCol; c++){
							tempObj = pFloor.getObj(r, c);
							if (tempObj != null){
								if (tempObj.getType() == 1){
									Enemy enemy = (Enemy) tempObj;
									enemy.randmove();
								}
							}
						}
					}
				}
				display();
			}
		}
	}
	
	/*
	 This function may have lots of pass by value/reference error occuring, due to the c++
	 version applied a lot of reference techniques.
	 */
	private Player newGame(){

		Player player = null;
		String cmd;
		boolean badinput;
		for (int x = 0; x < 50; x++)
			System.out.println('\n');

		System.out.println("Welcome to CC3K");
		System.out.println("Choose your race:");
		System.out.println("Human(h), Elves(e), Dwarf(d), Orc(o)");
		System.out.println();
		System.out.println("Quit(q) Help(?)");

		do{			
			cmd = input.next(); // Scanner in?
			
			if (!("h".equals(cmd)) && 
					!("e".equals(cmd)) && 
					!("d".equals(cmd)) && 
					!("o".equals(cmd)) &&
					!("q".equals(cmd)) &&
					!("?".equals(cmd))){
				System.out.println("Bad Input, try again\n");
				badinput = true;
			}
			else{
				badinput = false;
				Player.resetPlayer(); 
				if ("h".equals(cmd))
					player = Player.getPlayer(0); 
				else if ("e".equals(cmd))
					player = Player.getPlayer(2); 
				else if ("d".equals(cmd))
					player = Player.getPlayer(1); 
				else if ("o".equals(cmd))
					player = Player.getPlayer(3);
				else if ("q".equals(cmd)){
					System.exit(0);
				}
				else if ("?".equals(cmd)){
					help();
					badinput = true; 
					//Setting badinput here to be true is to
					//loop the input one more time, let player rechoose.
				}
			}
		}while(badinput);
		level = 1;
		pFloor = Floor.getInst(); 
		pFloor.spawn();
		pFloor.changemsg("Player character has spawned");
		display();
		return player;
	}
	
	private void display(){
		Player player = Player.getPlayer(); //Static method to be fixed
		pFloor.display();
		System.out.printf("Race: " + player.getRace());
		System.out.printf(" Gold " + player.getgold());
		System.out.println("\t\t\t\t\t\tFloor " + level);
		System.out.println("HP: " + player.gethp());
		System.out.println("Atk: " + player.getatk());
		System.out.println("Def: " + player.getdef());
        System.out.println("Str: " + player.getStr() + "  Dex: " + player.getDex() + " Int: " + player.getInt());
		System.out.print("Action: ");
		//Under construction here
		//System.out.println("Job: " + player.getJob());
		pFloor.showMsg();
		System.out.println();
		pFloor.clearmsg();
	}

	private void help(){
		System.out.println("Move: ");
		System.out.println("no = move north");
		System.out.println("ea = move east");
		System.out.println("so = move south");
		System.out.println("we = move west");
		System.out.println("nw = move northwest");
		System.out.println("ne = move northeast");
		System.out.println("se = move southeast");
		System.out.println("sw = move southwest");
		System.out.println();
		System.out.println("Attack: ");
		System.out.println("input 'a' followed by direction");
		System.out.println("e.g.: ano = attack north");
		System.out.println();
		System.out.println("Use potion: ");
		System.out.println("input 'u' followed by direction");
	}
	
	public static void main(String[] args) {
		new App().startGame();
		System.exit(0);
	}

    public boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException e) {
            return false;
        }
        return true;
    }

}

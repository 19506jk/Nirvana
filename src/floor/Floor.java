package floor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import character.Obj;

public class Floor {
	
	final int maxRow = 25;
	final int maxCol = 79;
	String action;
	static Floor thisFloor;
	Cell[][] layout;
	BufferedReader br;
	FileReader fileReader;
	//Original declaration
	//Cell layout[maxRow];

	/*
	 *Clean up function not needed in java
	void Floor::cleanup(){
		delete thisFloor;
	}
	*/

	private Floor(){
		layout = new Cell[maxRow][maxCol];
		action = "";
		char read;
		int cham;
		Square type;
		
		try {
			fileReader = new FileReader("floorPlan.txt");
			br = new BufferedReader(fileReader);
			for (int r = 0; r < maxRow; r++){
				for (int c = 0; c < maxCol; c++){
					read = (char)br.read(); //read() returns the ASCII for that char
					if (read == 10){ //if the read in char is newline, skip to next char
						read = (char)br.read();
					}
					switch (read) {
						case ' ':
							type = Square.VOID;
							break;
						case '-':
							type = Square.HORIW;
							break;
						case '|':
							type = Square.VERTIW;
							break;
						case '+':
							type = Square.DOOR;
							break;
						case '#':
							type = Square.PASSAGE;
							break;
						case '.':
							type = Square.TILE;
							break;
					}
					if (c >= 2 && c <= 29 && r >= 2 && r <= 7)
						cham = 1;
					else if (r >= 2 && r <= 7 && c >= 38 && c <= 59)
						cham = 2;
					else if (r >= 2 && r <= 13 && c >= 60 && c <= 76)
						cham = 2;
					else if (r >= 9 && r <= 13 && c >= 37 && c <= 50)
						cham = 3;
					else if (r >= 14 && r <= 22 && c >= 2 && c <= 25)
						cham = 4;
					else if (r >= 15 && r <= 17 && c >= 64 && c <= 76)
						cham = 5;
					else if (r >= 18 && r <= 22 && c >= 36 && c <= 76)
						cham = 5;
					else
						cham = 0;
					layout[r][c] = new Cell(type, cham);
				}
			}
			fileReader.close();
		}
		catch (IOException e){
			e.printStackTrace();
		}
		
	}

	public static Floor getInst(){
		if (thisFloor != null){
			thisFloor = new Floor();
			//C++ code, may be useful
			//atexit(cleanup);
		}
		return thisFloor;
	}
	
	public Obj getObj(int r, int c){
		return layout[r][c].getOnCell();
	}

	public void display(){
		Square content;
		for (int r = 0; r < maxRow; r++){
			for (int c = 0; c < maxCol; c++){
				if (layout[r][c].getOnCell() == null){
					content = layout[r][c].getType();
					switch (content) {
					case VOID:
						System.out.println(" ");
						break;
					case HORIW:
						System.out.println("-");
						break;
					case VERTIW:
						System.out.println("|");
						break;
					case DOOR:
						System.out.println("+");
						break;
					case PASSAGE:
						System.out.println("#");
						break;
					case TILE:
						System.out.println(".");
						break;
					}
				}
				else{
					System.out.println(layout[r][c].getOnCell().getRep());
				}
			}
			System.out.println();
		}
	}
	
	//
	//IMPORTANT
	//
	/*
	 * The necessity of the existence of this function requires to be investigated,
	 * For future considerations, maybe the floor should not be reseted when ascending,
	 * in case of a return to previous floor.
	 * Now that java have garbage collectors, we no longer need to delete the Obj
	 * on the cell before setting the cell to null in order to prevent memory leaks.
	 * However, it could be possible that Java misinterpret the set to null call on player Grid,
	 * thus causing Player to be deleted, even though it should not been.
	 */
	public void resetFloor(){
		for (int r = 0; r < maxRow; r++){
			for (int c = 0; c < maxCol; c++){
				if (layout[r][c].getOnCell() != null){
				layout[r][c].setOnCell(null); //Possibly cause memory leak if garbage
											//collector does not clean up
				}
			}
		}
	}

	public void setObj(int r, int c, Obj obj){
		layout[r][c].setOnCell(obj);
	}

	public void clearCell(int r, int c){
		if(layout[r][c].getOnCell() != null){
			//Since java object no longer needs to be deleted prior to set to null,
			//immediately setting cell to null is sufficient.
			//Again, could cause memory leaks if the garbage collector does not cleanup
			
			//delete layout[r][c]->getOnCell();
			layout[r][c].setOnCell(null);
		}
	}

	//
	// IMPORTANT
	//
	/* 
	 * The following function may be broken, for in C++ it utilizes the defined enum Player = 0;
	 * However, since Java enum is different from that of C++ and not yet implemented,
	 * it is highly possible that Player no longer has the value 0, 
	 * thus causing an error in comparison check, leading to the unexpected result 
	 * where null is always returned.
	 */
	public Obj scanPlayer(int r, int c){
		for (int x = r-1; x <= r+1; x++){
			for (int y = c-1; y <= c+1; y++){
				if (x == r && y == c)
					continue;
				if (layout[x][y].getOnCell() == null)
					continue;
				else if (layout[x][y].getOnCell().getType() == 0){ //ERROR IN COMPARISON HERE
					return layout[x][y].getOnCell();
				}
			}
		}
		return null;
	}

	//Broken function with uncertain meaning, requires to be investigated and fixed
	Object** Floor::scanPotion(int r, int c){
		Object** array = new Object*[8];
		for (int x = 0; x < 8; x++){
			array[x] = NULL;
		}
		int count = 0;
		for (int x = r-1; x <= r+1; x++){
			for (int y = c-1; y <= c+1; y++){
				if (x == r && y == c)
					continue;
				if (layout[x][y]->getOnCell() == NULL)
					continue;
				else if (layout[x][y]->getOnCell()->getType() == 3){
					array[count] = layout[x][y]->getOnCell();
					count++;
				}
			}
		}
		return array;
	}

	public Square getFloorType(int r, int c){
		return layout[r][c].getType();
	}

	public void changemsg(String msg){
		action = msg;
	}

	public void addmsg(String s) {
			action += s;
	}

	public void clearmsg(){
		action = "";
	}

	public void showMsg(){
		System.out.println(action);
	}

	//Randomization functions that requires seeding
	boolean chamSpawn(int chamID, Obj obj){//chamber overflow?
		int row, column;
		int count = 0;
		do{
			switch (chamID){
				case 1:
					row = rand() % 4 + 3;
					column = rand() % 26 + 3;
					break;
				case 2:
					column = rand() % 37 + 39;
					if (column >= 39 && column <= 60)
						row = rand() % 4 + 3;
					else if (column == 61)
						row = rand() % 10 + 3;
					else if (column >= 62 && column <= 69)
						row = rand() % 8 + 5;
					else if (column >= 70 && column <= 72)
						row = rand() % 7 + 6;
					else
						row = rand() % 6 + 7;
					break;
				case 3:
					row = rand() % 3 + 10;
					column = rand() % 12 + 38;
					break;
				case 4:
					row = rand() % 7 + 15;
					column = rand() % 21 + 4;
					break;
				case 5:
					row = rand() % 6 + 16;
					if (row >= 16 && row <= 18){
						column = rand() % 11 + 65;
					}
					else
						column = rand() % 39 + 37;
					break;
			}
			count++;
			if (count > 100)
				return false;
		}while(layout[row][column].getOnCell() != null);
		
		if (obj.getType() == 0){
			Player* temp = static_cast<Player*>(obj);
			temp->setCoord(row, column);
			temp->setFloor(this);
		}

		if (obj->getType() == 1){
			Enemy* temp = static_cast<Enemy*>(obj);
			temp->setCoord(row, column);
			temp->setFloor(this);
		}
		
		else if (obj->getType() == 2){
			Gold* temp = static_cast<Gold*>(obj);
			Dhorde* temp2;
			if (temp->getGoldType() == 3){
				temp2 = static_cast<Dhorde*>(temp);
				temp2->setCoord(row, column);
				temp2->spawnDrag(); //places surrounding dragon horde full?
			}
		}
		

		layout[row][column]->setOnCell(obj);
		return true;
	}

	int Floor::reCalcRan(int random){
		int newRan;
		do{
			newRan = rand() % 5 + 1;
		}while(random == newRan);

		return newRan;
	}

	void Floor::spawn(int seed){
		//random gen = 0
		if (seed == 0){
			int random = rand() % 5 + 1;
			int temp = random;
			Object* newObj;
			//Player
			chamSpawn(random, Player::getPlayer(0));
			//Stair
			while(random == temp)
				random = rand() % 5 + 1;
			chamSpawn(random, new Stair);
			//Potion
			for (int x = 0; x < 10; x++){
				random = rand() % 5 + 1;
				newObj = new Potion;
				while(!chamSpawn(random, newObj)){
					random = reCalcRan(random);
				}
			}
			//Gold
			for (int x = 0; x < 10; x++){
				random = rand() % 5 + 1;
				temp = rand() % 8 + 1;//denominator
				if (temp >= 1 && temp <= 5) //normal
					newObj = new Gold(0);
				else if (temp == 6)// dragon horde
					newObj = new Dhorde(this);
				else //small
					newObj = new Gold(1);
				while(!chamSpawn(random, newObj)){
					random = reCalcRan(random);
				}
			}
			//Enemy
			for (int x = 0; x < 20; x++){
				random = rand() % 5 + 1;
				temp = rand() % 18 + 1; //denominator
				if (temp >= 1 && temp <= 4) //Werewolf
					newObj = new Enemy(1);
				else if (temp >= 5 && temp <= 7) //Vampire
					newObj = new Enemy(0);
				else if (temp >= 8 && temp <= 12)//Goblin
					newObj = new Enemy(3);
				else if (temp == 13 || temp == 14)//Troll
					newObj = new Enemy(2);
				else if (temp == 15 || temp == 16)//Phoenix
					newObj = new Enemy(4);
				else //Merchant
					newObj = new Merchant;
				while(!chamSpawn(random, newObj)){
					random = reCalcRan(random);
				}
			}
		}
		//fixed gen type 1 = 1
		else if (seed == 1){
			//Player
			layout[3][10]->setOnCell(Player::getPlayer(0));//default param don't work?
			//Stair
			layout[11][43]->setOnCell(new Stair);
			//Potion
			//Gold
			layout[5][15]->setOnCell(new Gold(0));
			layout[3][60]->setOnCell(new Gold(0));

//			layout[8][69]->setOnCell(new Dhorde);//dragon horde
			
			layout[10][47]->setOnCell(new Gold(0));
			layout[17][14]->setOnCell(new Gold(0));
			layout[21][9]->setOnCell(new Gold(0));
			layout[17][68]->setOnCell(new Gold(0));
			layout[17][71]->setOnCell(new Gold(0));
			//Enemy
			layout[4][23]->setOnCell(new Enemy(1));
			layout[6][10]->setOnCell(new Enemy(0));
//			layout[6][24]->setOnCell(new Enemy(5));
			layout[3][49]->setOnCell(new Enemy(2));
			layout[5][49]->setOnCell(new Enemy(3));
			layout[5][60]->setOnCell(new Enemy(3));
			layout[9][63]->setOnCell(new Enemy(4));
			layout[4][23]->setOnCell(new Enemy(1));

		}
	}

	void Floor::spawnGold(int r, int c){
		layout[r][c]->setOnCell(new Gold(2));
	}
	
}

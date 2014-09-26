package mechanics.floor;

import java.util.Random;

import mechanics.character.Gold;
import mechanics.character.Obj;
import mechanics.character.Player;
import mechanics.enums.Square;
import mechanics.floor.plans.F1;
import mechanics.floor.plans.F2;

public class Floor {
	
	final int maxRow = 25;
	final int maxCol = 79;
	String action;
	static Floor currentFloor;
	Cell[][] layout;
	Random ranGen = new Random();
	static int currentLevel;
	
	private Floor(int[][] map, int level){
		updateFloor(map, level);
	}
	
	private void updateFloor(int[][] map, int level){
		if (layout == null){
			layout = new Cell[maxRow][maxCol];
		}
		for (int r = 0; r < maxRow; r++){
			for (int c = 0; c < maxCol; c++){
				if (layout[r][c] == null){
					layout[r][c] = new Cell(Square.numToSquare(map[r][c]));
				}
				else {
					layout[r][c].setType(Square.numToSquare(map[r][c]));
				}
			}
		}
		
		currentLevel = level;
	}
	
	public static Floor getInst(){
		if (currentFloor == null){
			currentFloor = new Floor(F1.getMap(), 1);
			Player.getPlayer().setFloor(currentFloor);
			
		}
		return currentFloor;
	}
	
	public void ascend(){
		switch (currentLevel){
		case 1:
			updateFloor(F2.getMap(), 2);
		}
	};
	
	public Obj getObj(int r, int c){
		return layout[r][c].getOnCell();
	}

	public void setObj(int r, int c, Obj obj){
		layout[r][c].setOnCell(obj);
	}
	
	public void display(){
		Square content;
		for (int r = 0; r < maxRow; r++){
			for (int c = 0; c < maxCol; c++){
				if (layout[r][c].getOnCell() == null){
					content = layout[r][c].getType();
					switch (content) {
					case VOID:
						System.out.print(" ");
						break;
					case HORIW:
						System.out.print("-");
						break;
					case VERTIW:
						System.out.print("|");
						break;
					case DOOR:
						System.out.print("+");
						break;
					case GRASS:
						System.out.print("#");
						break;
					case TILE:
						System.out.print(".");
						break;
					}
				}
				else{
					System.out.print(layout[r][c].getOnCell().getRep());
				}
			}
			System.out.println();
		}
	}
	
	public void clearCell(int r, int c){
		if(layout[r][c].getOnCell() != null){
			//Since java object no longer needs to be deleted prior to set to null,
			//immediately setting cell to null is sufficient.
			//Again, could cause memory leaks if the garbage collector does not cleanup
			layout[r][c].setOnCell(null);
		}
	}
	
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

	public Obj[] scanPotion(int r, int c){
		Obj[] array = new Obj[8];
		for (int x = 0; x < 8; x++){
			array[x] = null;
		}
		int count = 0;
		for (int x = r-1; x <= r+1; x++){
			for (int y = c-1; y <= c+1; y++){
				if (x == r && y == c)
					continue;
				if (layout[x][y].getOnCell() == null)
					continue;
				else if (layout[x][y].getOnCell().getType() == 3){
					array[count] = layout[x][y].getOnCell();
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
	
	public void spawn(){
		switch(currentLevel){
		case 1:
			F1.spawn(layout);
			break;
		case 2:
			F2.spawn(layout);
			break;
		default:
			return;
		}
	}
	
	public void spawnGold(int r, int c){
		layout[r][c].setOnCell(new Gold(2));
	}
	
	public Cell[][] getLayout(){
		return layout;
	}
}

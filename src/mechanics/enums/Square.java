package mechanics.enums;

public enum Square {
	VOID(0), HORIW(1), VERTIW(2), DOOR(3), GRASS(4), TILE(5);
	
	int numeric;
	
	private Square(int val){
		numeric = val;
	}
	
	public int getNum(){ return numeric; }
	
	public static Square numToSquare(int value){
		switch(value){
		case 0:
			return VOID;
		case 1:
			return HORIW;
		case 2:
			return VERTIW;
		case 3: 
			return DOOR;
		case 4:
			return GRASS;
		case 5:
			return TILE;
		default:
			return null;
		}
	}
}

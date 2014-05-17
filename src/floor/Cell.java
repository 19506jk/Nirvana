package floor;

import character.Obj;
import enums.Square;

public final class Cell {
	
	private Square type; // Cell type, using the enum class Square
	private int chamberID; //chamber ID of the current cell, refer to diagram for ID
	private Obj onCell; //the current object that is on this cell
	
	public Cell(Square type, int id){
		this.type = type;
		chamberID = id;
		onCell = null;
	}

	public Square getType() { return type; }

	public int getChamID() { return chamberID; }

	public Obj getOnCell(){ 
		if (onCell == null)
			return null;
		else
			return onCell;
	}

	public void setOnCell(Obj obj){ onCell = obj; }
	
}

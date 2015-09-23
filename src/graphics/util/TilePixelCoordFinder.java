package graphics.util;

import mechanics.enums.Square;

/**
 * This Enum class is used for finding the respective pixel coordinates of a specified tile type.
 * Note that this enum class only stores the correct coordinates, but ignores the file location,
 * therefore it is the user's responsibility to keep track of the correct file to use.
 * Otherwise, the consequences is possibly wrong image being displayed.
 * 
 * @author rainybreeze777
 */
public enum TilePixelCoordFinder {
	VOID(new Coordinate(0,0)), 
	HORIW(new Coordinate(0,5)),
	VERTIW(new Coordinate(0,8)),
	DOOR(new Coordinate(2,7)),
	GRASS(new Coordinate(4,2)),
	TILE(new Coordinate(0,6));
	
	private Coordinate coord;
	
	private TilePixelCoordFinder(Coordinate coord){
		this.coord = coord;
	}
	
	public static Coordinate getCoordOfSquare(Square sq){
		switch(sq){
		case VOID:
			return TilePixelCoordFinder.VOID.coord;
		case HORIW:
			return TilePixelCoordFinder.HORIW.coord;
		case VERTIW:
			return TilePixelCoordFinder.VERTIW.coord;
		case DOOR:
			return TilePixelCoordFinder.DOOR.coord;
		case GRASS:
			return TilePixelCoordFinder.GRASS.coord;
		case TILE:
			return TilePixelCoordFinder.TILE.coord;
		default:
			return null;
		}
	}
}

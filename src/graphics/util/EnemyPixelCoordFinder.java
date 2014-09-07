package graphics.util;

import mechanics.enums.MobType;

/**
 * This Enum class is used for finding the respective pixel coordinates of a specified enemy type.
 * Note that this enum class only stores the correct coordinates, but ignores the file location,
 * therefore it is the user's responsibility to keep track of the correct file to use.
 * Otherwise, the consequences is possibly wrong image being displayed.
 * 
 * @author rainybreeze777
 */
public enum EnemyPixelCoordFinder {
	GOBLIN(new Coordinate(0,0));
	
	private Coordinate coord;
	
	private EnemyPixelCoordFinder(Coordinate coord){
		this.coord = coord;
	}
	
	public static Coordinate getCoordOfMobType(MobType type){
		switch(type){
		case GOBLIN:
			return EnemyPixelCoordFinder.GOBLIN.coord;
		default:
			return null;
		}
	}
}

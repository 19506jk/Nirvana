package mechanics.character;

import mechanics.enums.Direction;
import mechanics.floor.Floor;

public class Dhorde extends Gold {
	int r, c;
	boolean openable;
	Floor floor;	
	Dragon guard;

	public Dhorde(Floor f) {
		super(3);
		r = 0;
		c = 0;
		floor = f;
		openable = false;
		guard = null;
	}
	
	public boolean canopen() {
		return openable;
	}

	public void changestatus() {
		openable = !openable;
	}

	public void setCoord(int r, int c) {
		this.r = r;
		this.c = c;
	}
	
	public void spawnDrag() {
		Dragon d = new Dragon(this);
		guard = d;
		int dr = 0;
		int dc = 0;

		while(true) {
			int location = (int) (Math.random() * 8);

			if (location == Direction.NO.ordinal())
			{
				dr = r - 1;
				dc = c;
			}

			if (location == Direction.SO.ordinal())
			{
				dr = r + 1;
				dc = c;
			}

			if (location == Direction.EA.ordinal())
			{
				dr = r;
				dc = c + 1;
			}

			if (location == Direction.WE.ordinal())
			{
				dr = r;
				dc = c - 1;
			}

			if (location == Direction.NE.ordinal())
			{
				dr = r - 1;
				dc = c + 1;
			}

			if (location == Direction.NW.ordinal())
			{
				dr = r - 1;
				dc = c - 1;
			}

			if (location == Direction.SE.ordinal())
			{
				dr = r + 1;
				dc = c + 1;
			}

			if (location == Direction.SW.ordinal())
			{
				dr = r + 1;
				dc = c - 1;
			}

			if (floor.getFloorType(dr, dc).ordinal() == 5 && floor.getObj(dr, dc) == null)
			{
				d.setCoord(r, c);
				d.setFloor(floor);
				floor.setObj(dr, dc, d);
				break;
			}
			else
			{
				continue;
			}
		}
	}

	void scout() {
		if (floor.scanPlayer(r, c) != null)
		{
			guard.changestatus();
			guard.randMove();
		}
	}


	int getGuardHP() {
		return guard.getHP();
	}

}

package mechanics.character;
import mechanics.floor.Floor;

class Interaction {
	int pr, pc;
	int tr, tc;
	String direction;
	Floor floor = null;
	Obj target = null;
	
	public Interaction() {
		pr = 0;
		pc = 0;
		tr = 0;
		tc = 0;
		direction = "";
		floor = null;
		target = null;
	}
	
	public void move(Obj o) { return; }
	public void combat(Obj o) { return; }

    /*
        Setters
     */

	public void setDirection(String dir) {
		if ("no".equals(dir))
		{
			tr = pr - 1;
			tc = pc;
			direction = "North";
		}

		else if ("so".equals(dir))
		{
			tr = pr + 1;
			tc = pc;
			direction = "South";
		}

		else if ("ea".equals(dir))
		{
			tr = pr;
			tc = pc + 1;
			direction = "East";
		}

		else if ("we".equals(dir))
		{
			tr = pr;
			tc = pc -1;
			direction = "West";
		}

		else if ("ne".equals(dir))
		{
			tr = pr - 1;
			tc = pc + 1;
			direction = "Northeast";
		}

		else if ("nw".equals(dir))
		{
			tr = pr - 1;
			tc = pc - 1;
			direction = "Northwest";
		}

		else if ("se".equals(dir))
		{
			tr = pr + 1;
			tc = pc + 1;
			direction = "Southeast";
		}

		else // else if ("sw".equals(dir))
		{
			tr = pr + 1;
			tc = pc - 1;
			direction = "Southwest";
		}

		target = floor.getObj(tr,tc);
	}

	public void setCurrentDir(int r, int c) {
		pr = r;
		pc = c;
	}

	public void setFloor(Floor f) {
		floor = f;
	}
	
}

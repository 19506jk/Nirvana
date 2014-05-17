package character;
import floor.Floor;

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
	
	public void setDirection(String dir) {
		if (dir == "no")
		{
			tr = pr - 1;
			tc = pc;
			direction = "North";
		}

		if (dir == "so")
		{
			tr = pr + 1;
			tc = pc;
			direction = "South";
		}

		if (dir == "ea")
		{
			tr = pr;
			tc = pc + 1;
			direction = "East";
		}

		if (dir == "we")
		{
			tr = pr;
			tc = pc -1;
			direction = "West";
		}

		if (dir == "ne")
		{
			tr = pr - 1;
			tc = pc + 1;
			direction = "Northeast";
		}

		if (dir == "nw")
		{
			tr = pr - 1;
			tc = pc - 1;
			direction = "Northwest";
		}

		if (dir == "se")
		{
			tr = pr + 1;
			tc = pc + 1;
			direction = "Southeast";
		}

		if (dir == "sw")
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

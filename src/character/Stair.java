package character;

public class Stair implements Obj {

	/*
	 * Note by Louis:
	 * I think the return of these two functions should be swapped,
	 * Manually changed it, waiting to be verified by Danny
	public char getRep() {
		return (char) 4;
	}

	public int getType() {
		return 92;
	}
	*/
	public char getRep() {
		return (char) 92;
	}

	public int getType() {
		return 4;
	}
}

package inv;

import character.Obj;

public class Item implements Obj {
	int count;
	String name;
	String family;

	// Getters
	public int getType() {
		return 3;
	}

	public int getCount() {
		return count;
	}

	public String getFamily() {
		return family;
	}

	public char getRep() {
		return 'I';
	}

	public String getName() {
		return name;
	}

	// Setters

	public void setCount(int c) {
		count = c;
	}
}

package character;


public class Item implements Obj {
	int count;
	String name;
	
	// Getters
	public int getType() { return 3; }
	
	public int getCount(){ return count;}
	
	public char getRep() { return 'I'; }
	
	public String getName() { return name; }
	
	//Setters
	
	public void setCount(int c) {count = c;}
}

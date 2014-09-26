package mechanics.inv;

import java.util.*;

public class Inventory {

	Hashtable<String, Item> items = new Hashtable<String, Item>();

	Inventory() {
		initiate();
	}

	private void initiate() {
		items.put("RH", new Potion("RH"));
		items.put("RM", new Potion("RM"));
	}

	public void addItem(String type, int count) {
		if (items.containsKey(type)) {
			Item temp = items.get(type);
			temp.setCount(temp.getCount() + count);
		} else {
			items.put(type, new Potion(type));
			Item temp = items.get(type);
			temp.setCount(temp.getCount() + count);
		}
	}

	public Item getItem(String type) {
		return items.get(type);
	}

}

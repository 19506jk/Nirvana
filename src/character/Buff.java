package character;

import java.util.HashMap;
import java.util.Map;

public class Buff {
	int atk, def;
    HashMap<String, Integer> timer = new HashMap<String, Integer>();
    HashMap<String, Integer> effects = new HashMap<String, Integer>();
	
	public Buff() {
		atk = 0;
		def = 0;
	}

    /*
        Getters
     */

    public int getAtk() {
        return atk;
    }

    public int getDef() {
        return def;
    }

    public int getBuffTimer(String name) {
        return timer.get(name);
    }

    /*
        Setters
     */

	public void setAtk(int amt) {
		atk += amt;
	}

	public void setDef(int amt) {
		def += amt;
	}

    public void setTimer(String name, int rounds) {
        timer.put(name, rounds);
    }

    public void setEffects(String name, int amt) { effects.put(name, amt); }

    /*
        Other methods
     */

    public void countDown() {
        for (Map.Entry<String, Integer> entry : timer.entrySet()) {
            int rounds = entry.getValue() - 1;
            if (rounds == 0) {
                int amt = effects.get(entry.getKey());
                effects.put(entry.getKey(), 0);
                if (entry.getKey().equals("atk")) {
                    atk -= amt;
                }
                else {
                    def -= amt;
                }
                timer.put(entry.getKey(), rounds);
            }
            else if (rounds > 0) {
                 timer.put(entry.getKey(), rounds);
            }
        }
    }

}

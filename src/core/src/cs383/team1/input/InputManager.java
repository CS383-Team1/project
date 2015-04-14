package cs383.team1.input;

import java.util.ArrayList;

public class InputManager {
 	public ArrayList<Integer> keys;
		
	public InputManager() {
		keys = new ArrayList<Integer>();
	}
	
	public boolean consumable() {
		return !keys.isEmpty();
	}

        public void limitList() {
                if (keys.size() > 1)
                        keys.subList(2, keys.size()).clear();                
        }
}

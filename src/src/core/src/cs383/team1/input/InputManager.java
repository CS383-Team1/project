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
	
}

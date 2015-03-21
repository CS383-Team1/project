package cs383.team1.input;

import java.util.Queue;
import java.util.LinkedList;

public class Input {
	public Queue<Character> keys;

	public Input() {
		keys = new LinkedList<Character>();
	}

	public boolean consumable() {
		return keys.peek() != null;
	}
}

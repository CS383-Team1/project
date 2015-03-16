package cs383.team1.model;

public abstract class State {
	public int ID;

	public State() {
		ID = 0;
	}

	public State(int newID) {
		if(newID < 0) {
			throw new IllegalArgumentException("State:ID must be positive"); 
		}

		ID = newID;
	}

	public abstract int act();
}

package cs383.team1.model;

import java.util.List;
import java.util.ArrayList;
import cs383.team1.model.State;

public final class StateManager {
	public static final StateManager instance = new StateManager();

	public List<State> states;
	public State current;

	private StateManager() {
		if(instance != null) {
			throw new IllegalStateException("Singleton already instantiated");
		}

		states = new ArrayList<State>();
		current = null;
	}

	public void transition() {
		current = states.get(current.act());
	}
}

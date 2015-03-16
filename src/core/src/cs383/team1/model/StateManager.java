package cs383.team1.model;

import java.util.List;
import java.util.ArrayList;
import com.badlogic.gdx.Gdx;
import cs383.team1.model.State;

public final class StateManager {
	public static final StateManager instance = new StateManager();

	public List<State> states;
	public State current;

	private StateManager() {
		if(instance != null) {
			Gdx.app.error("StateManager:StateManager",
			  "reinstantiating singleton StateManager");
			throw new IllegalStateException("Singleton already instantiated");
		}

		Gdx.app.debug("StateManager:StateManager", "instantiating class");
		states = new ArrayList<State>();
		current = null;
	}

	public void transition() {
		Gdx.app.debug("StateManager:update", "transitioning states");
		current = states.get(current.act());
	}
}

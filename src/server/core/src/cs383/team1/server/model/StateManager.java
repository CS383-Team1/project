package cs383.team1.server.model;

import java.util.Map;
import java.util.HashMap;
import com.badlogic.gdx.Gdx;

public final class StateManager {
	public static final StateManager instance = new StateManager();

	public Map<Integer, State> states;
	public State current;

	private StateManager() {
		if(instance != null) {
			Gdx.app.error("StateManager:StateManager",
			  "reinstantiating singleton StateManager");
			throw new IllegalStateException("Singleton already instantiated");
		}

		Gdx.app.debug("StateManager:StateManager", "instantiating class");
		states = new HashMap<Integer, State>();
		states.put(DemoState.STATE_ID, new DemoState());

		current = states.get(DemoState.STATE_ID);
	}

	public void transition() {
		Gdx.app.debug("StateManager:update", "transitioning states");
		current = states.get(current.act());
	}
}

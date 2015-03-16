package cs383.team1.model;

import cs383.team1.model.State;
import cs383.team1.model.StateManager;
import cs383.team1.model.overworld.AreaManager;

public final class GameManager {
	public static final GameManager instance = new GameManager();

	public AreaManager areas;
	public StateManager states;

	private GameManager() {
		if(instance != null) {
			throw new IllegalStateException("Singleton already instantiated");
		}

		areas = AreaManager.instance;
		states = StateManager.instance;
	}

	public void update() {
		states.transition();
	}
}

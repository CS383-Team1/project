package cs383.team1.model;

import com.badlogic.gdx.Gdx;
import cs383.team1.model.State;
import cs383.team1.model.StateManager;
import cs383.team1.model.overworld.AreaManager;

public final class GameManager {
	public static final GameManager instance = new GameManager();

	public AreaManager areas;
	public StateManager states;

	private GameManager() {
		if(instance != null) {
			Gdx.app.error("GameManager:GameManager",
			  "reinstantiating singleton GameManager");
			throw new IllegalStateException("reinstantiating singleton");
		}

		Gdx.app.debug("GameManager:GameManager", "instantiating class");
		areas = AreaManager.instance;
		states = StateManager.instance;
	}

	public void update() {
		Gdx.app.debug("GameManager:update", "transitioning states");
		states.transition();
	}
}

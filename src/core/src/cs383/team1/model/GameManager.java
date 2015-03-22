package cs383.team1.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import cs383.team1.input.InputManager;
import cs383.team1.model.State;
import cs383.team1.model.StateManager;
import cs383.team1.model.overworld.AreaManager;
import cs383.team1.model.overworld.Player;
import cs383.team1.render.DemoDisplay;

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
                
		load();
	}

	public void load() {
		int index;
		String fname;
		FileHandle areaDir;

		Gdx.app.log("GameManager:load", "Loading areas");

		index = -1;
		areaDir = Gdx.files.internal("area/");

		for(FileHandle f : areaDir.list()) {
			fname = new String("area/" + f.name());
			Gdx.app.debug("GameManager:load", "Loading area " + fname);
			index = areas.areas.indexOf(areas.loadArea(fname));
		}
		areas.current = index != -1 ? areas.areas.get(index) : null;
	}

	public void update(InputManager inputManager, Player player) {
		while(inputManager.consumable()) {
                    inputManager.processInput(player, areas.current);
		}

		Gdx.app.debug("GameManager:update", "transitioning states");
		states.transition();
	}
}

package cs383.team1.server.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.Input.Keys;
import cs383.team1.server.model.overworld.*;

public final class GameManager {
	public static final GameManager instance = new GameManager();

	public AreaManager areas;
	public StateManager states;

	private GameManager() {
		if (instance != null) {
			Gdx.app.error("GameManager:GameManager",
				"reinstantiating singleton GameManager");
			throw new IllegalStateException(
				"reinstantiating singleton");
		}

		Gdx.app.debug("GameManager:GameManager", "instantiating class");
		areas = AreaManager.instance;
		states = StateManager.instance;

		load();
	}

	public void load() {
		String fname;
		FileHandle areaDir;

		Gdx.app.log("GameManager:load", "Loading areas");

		areaDir = Gdx.files.internal("area/");

		for (FileHandle f : areaDir.list()) {
			fname = new String("area/" + f.name());
			Gdx.app.debug("GameManager:load",
				"Loading area " + fname);
                        areas.loadArea(fname);
		}

		areas.changeArea("demo");
	}

	public String update(String str) {
		int keycode;
		int numVals;
		int offset;
		int type;
		int x;
		int y;
		String[] vals;

		Gdx.app.log("GameManager:update", "Parsing input string");

		vals = str.trim().split("\\s+");
		offset = 0;

		numVals = Integer.parseInt(vals[offset++]);

		if (vals[offset].equals("KEY")) {
			type = 1;
		} else if (vals[offset].equals("CLICK")) {
			type = 2;
		} else {
			type = 0;
			Gdx.app.error("GameManager:update",
				"Invalid input event");
		}

		offset++;

		switch (type) {
			case 1:
				keycode = Integer.parseInt(vals[offset++]);
				break;
			case 2:
				x = Integer.parseInt(vals[offset++]);
				y = Integer.parseInt(vals[offset++]);
				break;
		}

		return areas.current.toString();
	}
}

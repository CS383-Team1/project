package cs383.team1.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.Input.Keys;
import cs383.team1.input.InputManager;
import cs383.team1.model.State;
import cs383.team1.model.StateManager;
import cs383.team1.model.overworld.AreaManager;
import cs383.team1.model.overworld.Player;
import cs383.team1.model.overworld.Position;
import cs383.team1.model.overworld.Tile;

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
		String index;
		String fname;
		FileHandle areaDir;

		Gdx.app.log("GameManager:load", "Loading areas");

		index = null;
		areaDir = Gdx.files.internal("area/");

		for(FileHandle f : areaDir.list()) {
			fname = new String("area/" + f.name());
			Gdx.app.debug("GameManager:load", "Loading area " + fname);
                        areas.loadArea(fname);
			index = fname;
		}
		areas.current = index != null ? areas.areas.get(index) : null;
	}

	public void update(InputManager in) {
		Player player;
		Position next;
		Tile target;

		player = areas.current.player;

		while(in.consumable()) {
			switch(in.keys.remove(0)) {
				case Keys.LEFT:
					next = new Position(player.pos.x - 1, player.pos.y);
					break;
				case Keys.RIGHT:
					next = new Position(player.pos.x + 1, player.pos.y);
					break;
				case Keys.UP:
					next = new Position(player.pos.x, player.pos.y + 1);
					break;
				case Keys.DOWN:
					next = new Position(player.pos.x, player.pos.y - 1);
					break;
                                case Keys.P:
                                        areas.current = "area/aa.txt" != null ? areas.areas.get("area/aa.txt") : null;
                                        next = new Position(player.pos.x, player.pos.y);
                                        break;
				default:
					continue;
			}

			target = null;
			for(Tile t : areas.current.tiles) {
				if(t.pos().x == next.x && t.pos().y == next.y) {
					target = t;
					break;
				}
			}

			if(target == null) {
				Gdx.app.error("GameManager:update", "invalid move");
				continue;
			}

			if(target.passable()) {
				player.pos = next;
			}
                        
                        if(target.type() == 3){
                                System.out.println("ONASTAIRS");
                        }
		}

		/* TODO: move the keyhandling code to the StateManager */
		/*
		Gdx.app.debug("GameManager:update", "transitioning states");
		states.transition();
		*/
	}
}

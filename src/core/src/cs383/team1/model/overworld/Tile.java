package cs383.team1.model.overworld;

import com.badlogic.gdx.Gdx;
import cs383.team1.model.overworld.Entity;

public class Tile extends Entity {
	public boolean passable;

	public Tile(boolean p) {
		Gdx.app.debug("Tile:Tile", "instantiating class");
		passable = p;
	}
}

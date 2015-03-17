package cs383.team1.model.overworld;

import com.badlogic.gdx.Gdx;
import cs383.team1.model.overworld.Entity;
import cs383.team1.model.overworld.Position;

public abstract class Tile extends Entity {
	public boolean passable;
	public Position pos;

	public Tile() {
		passable = true;
		pos = new Position(0, 0);
	}

	public Tile(boolean p, Position pos_0) {
		passable = p;
		pos = pos_0;
	}
}

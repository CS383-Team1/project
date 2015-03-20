package cs383.team1.model.overworld;

import com.badlogic.gdx.Gdx;
import cs383.team1.model.overworld.Entity;
import cs383.team1.model.overworld.Position;

public class DemoTile implements Tile {
	public static final int TYPE = 1;

	private boolean passable;
	private Position pos;

	public DemoTile() {
		this(new Position(0, 0));
	}

	public DemoTile(Position p) {
		Gdx.app.debug("DemoTile:DemoTile", "instantiating class");

		passable = true;
		pos = p;
	}

	public int type() {
		return TYPE;
	}

	public Position pos() {
		return pos;
	}

	public boolean passable() {
		return passable;
	}
}

package cs383.team1.client.model.overworld;

import com.badlogic.gdx.Gdx;

public class Wall implements Tile {
	public static final int TYPE = 2;
	public static final boolean PASSABLE = false;

	private Position pos;

	public Wall() {
		this(new Position(0, 0));
	}

	public Wall(Position p) {
		Gdx.app.debug("Wall:Wall", "instantiating class");
		pos = p;
	}

	public int type() {
		return TYPE;
	}

	public Position pos() {
		return pos;
	}

	public boolean passable() {
		return PASSABLE;
	}
}

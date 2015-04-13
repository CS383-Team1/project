package cs383.team1.server.model.overworld;

import com.badlogic.gdx.Gdx;

public class Field implements Tile {
	public static final int TYPE = 4;
	public static final boolean PASSABLE = true;

	private Position pos;

	public Field() {
		this(new Position(0, 0));
	}

	public Field(Position p) {
		Gdx.app.debug("Field:Field", "instantiating class");
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

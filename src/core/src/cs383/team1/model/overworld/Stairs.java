package cs383.team1.model.overworld;

import com.badlogic.gdx.Gdx;
import cs383.team1.model.overworld.Entity;
import cs383.team1.model.overworld.Position;

public class Stairs implements Tile {
	public static final int TYPE = 3;
	public static final boolean PASSABLE = true;

	private Position pos;

	public Stairs() {
		this(new Position(0, 0));
	}

	public Stairs(Position p) {
		Gdx.app.debug("Stairs:Stairs", "instantiating class");
		pos = p;
	}

	public int type() {
		return TYPE;
	}

	public Position pos() {return pos;}

	public boolean passable() {
		return PASSABLE;
	}
}

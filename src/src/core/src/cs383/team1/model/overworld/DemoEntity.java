package cs383.team1.model.overworld;

import com.badlogic.gdx.Gdx;
import cs383.team1.model.overworld.Entity;
import cs383.team1.model.overworld.Position;

public class DemoEntity implements Entity {
	public static final int TYPE = 1;

	private Position pos;

	public DemoEntity() {
		this(new Position(0, 0));
	}

	public DemoEntity(Position p) {
		Gdx.app.debug("DemoEntity:DemoEntity", "instantiating class");
		pos = p;
	}

	public int type() {
		return TYPE;
	}

	public Position pos() {
		return pos;
	}
}

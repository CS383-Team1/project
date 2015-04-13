package cs383.team1.server.model.overworld;

import com.badlogic.gdx.Gdx;

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

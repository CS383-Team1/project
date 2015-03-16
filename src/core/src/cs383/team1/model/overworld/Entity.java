package cs383.team1.model.overworld;

import com.badlogic.gdx.Gdx;
import cs383.team1.model.overworld.Position;

public abstract class Entity {
	public Position pos;

	public Entity() {
		Gdx.app.debug("Entity:Entity", "instantiating class");
		pos = new Position(0, 0);
	}

	public Entity(Position p) {
		pos = p;
	}
}

package cs383.team1.model.overworld;

import com.badlogic.gdx.Gdx;
import cs383.team1.model.overworld.Entity;

public class DemoEntity extends Entity {
	public DemoEntity() {
		super();

		Gdx.app.debug("DemoEntity:DemoEntity", "instantiating class");
	}

	public DemoEntity(Position p) {
		super(p);

		Gdx.app.debug("DemoEntity:DemoEntity", "instantiating class");
	}
}

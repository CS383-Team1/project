package cs383.team1.model.overworld;

import com.badlogic.gdx.Gdx;
import cs383.team1.model.overworld.Entity;
import cs383.team1.model.overworld.Position;

public abstract class InteractableEntity extends Entity {
	public InteractableEntity() {
		super();

		Gdx.app.debug("InteractableEntity:InteractableEntity",
		  "instantiating class");
	}

	public InteractableEntity(Position p) {
		super(p);

		Gdx.app.debug("InteractableEntity:InteractableEntity",
		  "instantiating class");
	}
}

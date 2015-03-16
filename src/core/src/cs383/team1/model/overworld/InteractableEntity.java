package cs383.team1.model.overworld;

import cs383.team1.model.overworld.Entity;
import cs383.team1.model.overworld.Position;

public abstract class InteractableEntity extends Entity {
	public InteractableEntity() {
		super();
	}

	public InteractableEntity(Position p) {
		super(p);
	}
}

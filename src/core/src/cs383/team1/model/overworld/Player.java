package cs383.team1.model.overworld;

import cs383.team1.model.overworld.Entity;
import cs383.team1.model.overworld.Position;

public class Player extends Entity {
	public int hp;
	public int mp;
	public int ap;

	public Player() {
		super();
	}

	public Player(Position p) {
		super(p);
	}
}

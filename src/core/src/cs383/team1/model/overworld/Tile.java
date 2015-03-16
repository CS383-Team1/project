package cs383.team1.model.overworld;

import cs383.team1.model.overworld.Entity;

public class Tile extends Entity {
	public boolean passable;

	public Tile(boolean p) {
		passable = p;
	}
}

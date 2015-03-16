package cs383.team1.model.overworld;

import com.badlogic.gdx.Gdx;
import cs383.team1.model.overworld.Entity;
import cs383.team1.model.overworld.Position;

public class Player extends Entity {
	public int hp;
	public int mp;
	public int ap;

	public Player() {
		super();

		Gdx.app.debug("Player:Player", "instantiating class");
	}

	public Player(Position p) {
		super(p);

		Gdx.app.debug("Player:Player", "instantiating class");
	}
}

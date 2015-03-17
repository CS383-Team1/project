package cs383.team1.model.overworld;

import com.badlogic.gdx.Gdx;
import cs383.team1.model.overworld.Entity;
import cs383.team1.model.overworld.Position;

public class DemoTile extends Tile {
	public static final int ID = 1;

	public DemoTile() {
		super(true, new Position(0, 0));
		Gdx.app.debug("DemoTile:DemoTile", "instantiating class");
	}

	public DemoTile(Position p) {
		super(true, p);
		Gdx.app.debug("DemoTile:DemoTile", "instantiating class");
	}
}

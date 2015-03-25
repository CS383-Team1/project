package cs383.team1.model.overworld;

import com.badlogic.gdx.Gdx;
import cs383.team1.model.overworld.Entity;
import cs383.team1.model.overworld.Position;

public interface Tile extends Entity {
	public static final int WIDTH = 32;
	public static final int HEIGHT = 32;

	public boolean passable();
}

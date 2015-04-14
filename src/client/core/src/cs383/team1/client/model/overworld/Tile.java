package cs383.team1.client.model.overworld;

import com.badlogic.gdx.Gdx;

public interface Tile extends Entity {
	public int WIDTH = 32;
	public int HEIGHT = 32;

	public boolean passable();
}

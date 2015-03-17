package cs383.team1.model.overworld;

import java.util.List;
import java.util.ArrayList;
import com.badlogic.gdx.Gdx;
import cs383.team1.model.overworld.Entity;
import cs383.team1.model.overworld.Tile;

public class Area {
	public List<Tile> tiles;
	public List<Entity> entities;

	public Area() {
		Gdx.app.debug("Area:Area", "instantiating class");
		tiles = new ArrayList<Tile>();
		entities = new ArrayList<Entity>();
	}

	public Area(List<Tile> tileList, List<Entity> entityList) {
		Gdx.app.debug("Area:Area", "instantiating class");
		tiles = tileList;
		entities = entityList;
	}
}

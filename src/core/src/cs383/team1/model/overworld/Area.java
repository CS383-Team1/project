package cs383.team1.model.overworld;

import java.util.List;
import java.util.ArrayList;
import com.badlogic.gdx.Gdx;

public class Area {
	public List<Tile> tiles;
	public List<Entity> entities;
        public Player player;
        
	public Area() {
            this(new ArrayList<Tile>(), new ArrayList<Entity>(), new Player());
	}

	public Area(List<Tile> tileList, List<Entity> entityList, Player p) {
		Gdx.app.debug("Area:Area", "instantiating class");
		tiles = tileList;
		entities = entityList;
                player = p;
                
	}
        
        public Area(List<Tile> tileList, List<Entity> entityList) {
		Gdx.app.debug("Area:Area", "instantiating class");
		tiles = tileList;
		entities = entityList;
	}
}

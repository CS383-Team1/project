package cs383.team1.model.overworld;

import java.util.List;
import java.util.ArrayList;
import com.badlogic.gdx.Gdx;
import java.util.HashMap;
import java.util.Map;

public class Area {
	public List<Tile> tiles;
	public List<Entity> entities;
        public Player player;
        

        public Map<Integer, Player> players;
        public String name;
        
	public Area() {
            this(new ArrayList<Tile>(), new ArrayList<Entity>(), new Player());
	}

	public Area(List<Tile> tileList, List<Entity> entityList, Player p) {
		Gdx.app.debug("Area:Area", "instantiating class");
		tiles = tileList;
		entities = entityList;
//players = new ArrayList();
                players = new HashMap<Integer, Player>();
                //players.add(p);
                //for(int i = 0; i < 27; i++){
                  //players.put(i, p);
                //}
	}
        
        public Area(List<Tile> tileList, List<Entity> entityList) {
		Gdx.app.debug("Area:Area", "instantiating class");
		tiles = tileList;
		entities = entityList;
		//players = new ArrayList();
                players = new HashMap<Integer, Player>();
                //Player p = new Player();
                //for(int i = 0; i < 27; i++){
                  //  players.put(i, p);
                //}

	}
}

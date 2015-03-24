package cs383.team1.model.overworld;

import java.util.List;
import java.util.ArrayList;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import cs383.team1.model.overworld.Area;
import cs383.team1.model.overworld.DemoEntity;
import cs383.team1.model.overworld.Entity;
import cs383.team1.model.overworld.Position;

public final class AreaManager {
	public static final AreaManager instance = new AreaManager();

	public List<Area> areas;
	public Area current;

	private AreaManager() {
		if(instance != null) {
			Gdx.app.error("AreaManager:AreaManager",
			  "reinstantiating singleton AreaManager");
			throw new IllegalStateException("reinstantiating singleton");
		}

		Gdx.app.debug("AreaManager:AreaManager", "instantiating class");
		areas = new ArrayList<Area>();
		current = null;
	}

	public Area loadArea(String fname) {
		boolean passable;
		int i;
		int x;
		int y;
		int type;
		int numEntities;
		int numTiles;
		int offset;
		Area a;
		String fcontents;
		String[] vals;
		FileHandle fin;
		List<Entity> entities;
		List<Tile> tiles;
		Position pos;

		offset = 0;
		entities = new ArrayList<Entity>();
		tiles = new ArrayList<Tile>();

		Gdx.app.log("AreaManager:loadArea", "Loading " + fname);

		fin = Gdx.files.internal(fname);
		fcontents = fin.readString();
		vals = fcontents.trim().split("\\s+");

		Gdx.app.debug("AreaManager:loadArea", "Loading tiles");

		numTiles = Integer.parseInt(vals[offset++]);
		for(i = offset; i < offset + (numTiles * 3); i += 3) {
			x = Integer.parseInt(vals[i + 0]);
			y = Integer.parseInt(vals[i + 1]);
			type = Integer.parseInt(vals[i + 2]);

			pos = new Position(x, y);

			switch(type) {
                                case 0:
                                    Gdx.app.debug("AreaManager:loadArea",
					  "Loading DemoTile (" + vals[i] + "," + vals[i + 1] + ")");
					tiles.add(new DemoTile(pos, false));
					break;
                            
                                case 1:
					Gdx.app.debug("AreaManager:loadArea",
					  "Loading DemoTile (" + vals[i] + "," + vals[i + 1] + ")");
					tiles.add(new DemoTile(pos, true));
					break;
                                default:
					Gdx.app.error("AreaManager:loadArea",
					  "invalid tile type " + vals[i + 2]);
			}
		}
		offset += numTiles * 3;

		Gdx.app.debug("AreaManager:loadArea", "Loading entities");

		numEntities = Integer.parseInt(vals[offset++]);
		for(i = offset; i < offset + (numEntities * 3); i += 3) {
			x = Integer.parseInt(vals[i + 0]);
			y = Integer.parseInt(vals[i + 1]);
			type = Integer.parseInt(vals[i + 2]);

			pos = new Position(x, y);

			switch(type) {
				case DemoEntity.TYPE:
					Gdx.app.debug("AreaManager:loadArea", "Loading DemoEntity");
					entities.add(new DemoEntity(pos));
					break;
				default:
					Gdx.app.error("AreaManager:loadArea",
					  "invalid entity type " + vals[i + 2]);
			}
		}
		offset += numEntities * 3;

		a = new Area(tiles, entities);
		areas.add(a);

		return a;
	}

	public void createArea() {
		Gdx.app.log("AreaManager:createArea", "** depreciated **");
		Area a;

		a = new Area();
		a.entities.add(new DemoEntity(new Position(100, 100)));

		areas.add(a);
		current = a;
	}
}

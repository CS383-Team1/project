package cs383.team1.server.model.overworld;

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

	private <T> void addWord(StringBuilder sb, T word) {
		sb.append(word);
		sb.append(" ");
	}

	public String toString() {
		int i;
		StringBuilder sb;

		sb = new StringBuilder();

		addWord(sb, tiles.size());
		for (Tile t : tiles) {
			addWord(sb, t.pos().x);
			addWord(sb, t.pos().y);
			addWord(sb, t.type());
		}

		addWord(sb, entities.size());
		for (Entity e : entities) {
			addWord(sb, e.pos().x);
			addWord(sb, e.pos().y);
			addWord(sb, e.type());
		}

		return sb.toString();
	}
}

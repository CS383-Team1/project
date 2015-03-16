package cs383.team1.model.overworld;

import java.util.List;
import java.util.ArrayList;
import com.badlogic.gdx.Gdx;
import cs383.team1.model.overworld.Area;
import cs383.team1.model.overworld.DemoEntity;
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

	public void createArea() {
		Area a;

		a = new Area();
		a.entities.add(new DemoEntity(new Position(100, 100)));

		areas.add(a);
		current = a;
	}
}

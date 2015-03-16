package cs383.team1.model.overworld;

import java.util.List;
import java.util.ArrayList;
import cs383.team1.model.overworld.Area;

public final class AreaManager {
	public static final AreaManager instance = new AreaManager();

	public List<Area> areas;

	private AreaManager() {
		if(instance != null) {
			throw new IllegalStateException("Singleton already instantiated");
		}

		areas = new ArrayList<Area>();
	}
}

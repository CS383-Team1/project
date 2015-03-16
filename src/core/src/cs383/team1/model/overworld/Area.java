package cs383.team1.model.overworld;

import java.util.List;
import java.util.ArrayList;
import com.badlogic.gdx.Gdx;
import cs383.team1.model.overworld.Entity;

public class Area {
	public List<Entity> entities;

	public Area() {
		Gdx.app.debug("Area:Area", "instantiating class");
		entities = new ArrayList<Entity>();
	}
}

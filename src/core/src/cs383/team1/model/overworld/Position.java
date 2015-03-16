package cs383.team1.model.overworld;

import com.badlogic.gdx.Gdx;

public class Position {
	public int x;
	public int y;

	public Position() {
		Gdx.app.debug("Position:Position", "instantiating class");
		x = 0;
		y = 0;
	}

	public Position(int x_0, int y_0) {
		Gdx.app.debug("Position:Position", "instantiating class");
		x = x_0;
		y = y_0;
	}
}

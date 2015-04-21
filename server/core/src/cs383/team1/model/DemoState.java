package cs383.team1.model;

import com.badlogic.gdx.Gdx;
import cs383.team1.model.State;

public class DemoState extends State {
	public static final int STATE_ID = 1;
	public DemoState() {
		super(DemoState.STATE_ID);
	}

	public int act() {
		Gdx.app.debug("DemoState:act", "transitioning to DemoState");
		return DemoState.STATE_ID;
	}
}

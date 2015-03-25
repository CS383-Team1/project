package cs383.team1.model;

import com.badlogic.gdx.Gdx;

public abstract class State {
	public int ID;

	public State() {
		ID = 0;
	}

	public State(int newID) {
		if(newID < 0) {
			Gdx.app.error("State:State", "illegal state ID");
			throw new IllegalArgumentException("illegal state ID");
		}

		ID = newID;
	}

	public abstract int act();
}

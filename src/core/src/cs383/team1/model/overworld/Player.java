package cs383.team1.model.overworld;

import com.badlogic.gdx.Gdx;
import cs383.team1.model.overworld.Entity;
import cs383.team1.model.overworld.Position;

public final class Player implements Entity {
	public static final int TYPE = 1;
	public Position pos;
        
	public int hp;
	public int mp;
	public int ap;

	public Player() {
		this(new Position(0, 0), 0, 0, 0);
	}

	public Player(Position p, int hp_0, int mp_0, int ap_0) {
		Gdx.app.debug("Player:Player", "instantiating class");
		pos = p;
		hp = hp_0 > 0 ? hp_0 : 0;
		mp = mp_0 > 0 ? mp_0 : 0;
		ap = ap_0 > 0 ? ap_0 : 0;
	}

	public int type() {
		return TYPE;
	}

	public Position pos() {
		return pos;
	}
}

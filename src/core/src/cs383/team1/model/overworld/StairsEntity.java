package cs383.team1.model.overworld;

import com.badlogic.gdx.Gdx;
import cs383.team1.model.overworld.Entity;
import cs383.team1.model.overworld.Position;

public class StairsEntity implements Entity {
	public static final int TYPE = 2;

	private Position pos;
        private String destination;

	public StairsEntity() {
		this(new Position(0, 0), "null");
	}

	public StairsEntity(Position p, String s) {
		Gdx.app.debug("StairsEntity:StairsEntity", "instantiating class");
		pos = p;
                destination = s;
	}

	public int type() {
		return TYPE;
	}

	public Position pos() {
		return pos;
	}
        
        public String destination(){
                return destination;
        }
}

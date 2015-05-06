package cs383.team1.model.overworld;

import com.badlogic.gdx.Gdx;
import cs383.team1.model.overworld.Entity;
import cs383.team1.model.overworld.Position;

public class Firepit1 implements Tile {
    public static final int TYPE = 23;
    public static final boolean PASSABLE = false;


    private Position pos;

    public Firepit1() { this(new Position(0, 0)); }

    public Firepit1(Position p) {
        Gdx.app.debug("Firepit1:Firepit1", "instantiating class");
        pos = p;
    }

    public int type() {return TYPE;}

    public Position pos() {return pos;}

    public boolean passable() {return PASSABLE;}
}

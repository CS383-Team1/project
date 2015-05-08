package cs383.team1.model.overworld;

import com.badlogic.gdx.Gdx;
import cs383.team1.model.overworld.Entity;
import cs383.team1.model.overworld.Position;

public class Desk1 implements Tile {
    public static final int TYPE = 19;
    public static final boolean PASSABLE = false;


    private Position pos;

    public Desk1() { this(new Position(0, 0)); }

    public Desk1(Position p) {
        Gdx.app.debug("Desk1:Desk1", "instantiating class");
        pos = p;
    }

    public int type() {return TYPE;}

    public Position pos() {return pos;}

    public boolean passable() {return PASSABLE;}
}

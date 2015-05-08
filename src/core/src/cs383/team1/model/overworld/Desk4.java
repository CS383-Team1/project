package cs383.team1.model.overworld;

import com.badlogic.gdx.Gdx;
import cs383.team1.model.overworld.Entity;
import cs383.team1.model.overworld.Position;

public class Desk4 implements Tile {
    public static final int TYPE = 22;
    public static final boolean PASSABLE = false;


    private Position pos;

    public Desk4() { this(new Position(0, 0)); }

    public Desk4(Position p) {
        Gdx.app.debug("Desk4:Desk4", "instantiating class");
        pos = p;
    }

    public int type() {return TYPE;}

    public Position pos() {return pos;}

    public boolean passable() {return PASSABLE;}
}

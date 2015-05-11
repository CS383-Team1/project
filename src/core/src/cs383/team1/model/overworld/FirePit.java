package cs383.team1.model.overworld;

import com.badlogic.gdx.Gdx;
import cs383.team1.model.overworld.Entity;
import cs383.team1.model.overworld.Position;

public class FirePit implements Tile {
    public static final int TYPE = 8;
    public static final boolean PASSABLE = false;


    private Position pos;

    public FirePit() { this(new Position(0, 0)); }

    public FirePit(Position p) {
        Gdx.app.debug("FirePit:FirePit", "instantiating class");
        pos = p;
    }

    public int type() {return TYPE;}

    public Position pos() {return pos;}

    public boolean passable() {return PASSABLE;}
}

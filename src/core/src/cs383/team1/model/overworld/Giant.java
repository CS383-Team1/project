package cs383.team1.model.overworld;

import com.badlogic.gdx.Gdx;
import cs383.team1.model.overworld.Entity;
import cs383.team1.model.overworld.Position;

public class Giant implements Tile {
    public static final int TYPE = 9;
    public static final boolean PASSABLE = false;


    private Position pos;

    public Giant() { this(new Position(0, 0)); }

    public Giant(Position p) {
        Gdx.app.debug("Giant:Giant", "instantiating class");
        pos = p;
    }

    public int type() {return TYPE;}

    public Position pos() {return pos;}

    public boolean passable() {return PASSABLE;}
}

package cs383.team1.model.overworld;

import com.badlogic.gdx.Gdx;
import cs383.team1.model.overworld.Entity;
import cs383.team1.model.overworld.Position;

public class Desk2 implements Tile {
    public static final int TYPE = 20;
    public static final boolean PASSABLE = false;


    private Position pos;

    public Desk2() { this(new Position(0, 0)); }

    public Desk2(Position p) {
        Gdx.app.debug("Desk2:Desk2", "instantiating class");
        pos = p;
    }

    public int type() {return TYPE;}

    public Position pos() {return pos;}

    public boolean passable() {return PASSABLE;}
}

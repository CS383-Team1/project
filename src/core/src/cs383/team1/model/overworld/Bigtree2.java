package cs383.team1.model.overworld;

import com.badlogic.gdx.Gdx;
import cs383.team1.model.overworld.Entity;
import cs383.team1.model.overworld.Position;

public class Bigtree2 implements Tile {
    public static final int TYPE = 30;
    public static final boolean PASSABLE = false;


    private Position pos;

    public Bigtree2() { this(new Position(0, 0)); }

    public Bigtree2(Position p) {
        Gdx.app.debug("Bigtree2:Bigtree2", "instantiating class");
        pos = p;
    }

    public int type() {return TYPE;}

    public Position pos() {return pos;}

    public boolean passable() {return PASSABLE;}
}

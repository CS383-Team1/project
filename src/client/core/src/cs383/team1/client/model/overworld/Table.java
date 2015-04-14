package cs383.team1.client.model.overworld;

import com.badlogic.gdx.Gdx;

public class Table implements Tile {
    public static final int TYPE = 5;
    public static final boolean PASSABLE = false;


    private Position pos;

    public Table() { this(new Position(0, 0)); }

    public Table(Position p) {
        Gdx.app.debug("Table:Table", "instantiating class");
        pos = p;
    }

    public int type() {return TYPE;}

    public Position pos() {return pos;}

    public boolean passable() {return PASSABLE;}
}

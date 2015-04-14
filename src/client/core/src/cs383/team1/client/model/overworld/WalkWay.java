package cs383.team1.client.model.overworld;

import com.badlogic.gdx.Gdx;

public class WalkWay implements Tile {
    public static final int TYPE = 8;
    public static final boolean PASSABLE = true;


    private Position pos;

    public WalkWay() { this(new Position(0, 0)); }

    public WalkWay(Position p) {
        Gdx.app.debug("WalkWay:WalkWay", "instantiating class");
        pos = p;
    }

    public int type() {return TYPE;}

    public Position pos() {return pos;}

    public boolean passable() {return PASSABLE;}
}

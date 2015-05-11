package cs383.team1.model.overworld;

import com.badlogic.gdx.Gdx;
import cs383.team1.model.overworld.Entity;
import cs383.team1.model.overworld.Position;

public class CyberTile implements Tile {
    public static final int TYPE = 27;
    public static final boolean PASSABLE = true;


    private Position pos;

    public CyberTile() { this(new Position(0, 0)); }

    public CyberTile(Position p) {
        Gdx.app.debug("CyberTile:CyberTile", "instantiating class");
        pos = p;
    }

    public int type() {return TYPE;}

    public Position pos() {return pos;}

    public boolean passable() {return PASSABLE;}
}

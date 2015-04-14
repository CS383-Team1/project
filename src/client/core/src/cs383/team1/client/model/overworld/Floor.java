package cs383.team1.client.model.overworld;

        import com.badlogic.gdx.Gdx;

public class Floor implements Tile {
    public static final int TYPE = 1;
    public static final boolean PASSABLE = true;

    private Position pos;

    public Floor() { this(new Position(0, 0)); }

    public Floor(Position p) {
        Gdx.app.debug("Floor:Floor", "instantiating class");
        pos = p;
    }

    public int type() {return TYPE;}

    public Position pos() {return pos;}

    public boolean passable() {return PASSABLE;}
}

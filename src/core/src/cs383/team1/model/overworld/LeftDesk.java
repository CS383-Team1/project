package cs383.team1.model.overworld;

        import com.badlogic.gdx.Gdx;
        import cs383.team1.model.overworld.Entity;
        import cs383.team1.model.overworld.Position;

public class LeftDesk implements Tile {
    public static final int TYPE = 6;
    public static final boolean PASSABLE = false;


    private Position pos;

    public LeftDesk() { this(new Position(0, 0)); }

    public LeftDesk(Position p) {
        Gdx.app.debug("LeftDesk:LeftDesk", "instantiating class");
        pos = p;
    }

    public int type() {return TYPE;}

    public Position pos() {return pos;}

    public boolean passable() {return PASSABLE;}
}

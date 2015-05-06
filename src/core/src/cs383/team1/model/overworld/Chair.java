package cs383.team1.model.overworld;

        import com.badlogic.gdx.Gdx;
        import cs383.team1.model.overworld.Entity;
        import cs383.team1.model.overworld.Position;

public class Chair implements Tile {
    public static final int TYPE = 10;
    public static final boolean PASSABLE = false;


    private Position pos;

    public Chair() { this(new Position(0, 0)); }

    public Chair(Position p) {
        Gdx.app.debug("Chair:Chair", "instantiating class");
        pos = p;
    }

    public int type() {return TYPE;}

    public Position pos() {return pos;}

    public boolean passable() {return PASSABLE;}
}

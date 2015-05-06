package cs383.team1.model.overworld;

        import com.badlogic.gdx.Gdx;
        import cs383.team1.model.overworld.Entity;
        import cs383.team1.model.overworld.Position;

public class Desk implements Tile {
    public static final int TYPE = 7;
    public static final boolean PASSABLE = false;


    private Position pos;

    public Desk() { this(new Position(0, 0)); }

    public Desk(Position p) {
        Gdx.app.debug("Desk:Desk", "instantiating class");
        pos = p;
    }

    public int type() {return TYPE;}

    public Position pos() {return pos;}

    public boolean passable() {return PASSABLE;}
}

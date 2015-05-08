package cs383.team1.model.overworld;

        import com.badlogic.gdx.Gdx;
        import cs383.team1.model.overworld.Entity;
        import cs383.team1.model.overworld.Position;

public class Bench1 implements Tile {
    public static final int TYPE = 18;
    public static final boolean PASSABLE = false;


    private Position pos;

    public Bench1() { this(new Position(0, 0)); }

    public Bench1(Position p) {
        Gdx.app.debug("Bench1:Bench1", "instantiating class");
        pos = p;
    }

    public int type() {return TYPE;}

    public Position pos() {return pos;}

    public boolean passable() {return PASSABLE;}
}

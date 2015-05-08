package cs383.team1.model.overworld;

        import com.badlogic.gdx.Gdx;
        import cs383.team1.model.overworld.Entity;
        import cs383.team1.model.overworld.Position;

public class Flower2 implements Tile {
    public static final int TYPE = 12;
    public static final boolean PASSABLE = false;


    private Position pos;

    public Flower2() { this(new Position(0, 0)); }

    public Flower2(Position p) {
        Gdx.app.debug("Flower2:Flower2", "instantiating class");
        pos = p;
    }

    public int type() {return TYPE;}

    public Position pos() {return pos;}

    public boolean passable() {return PASSABLE;}
}

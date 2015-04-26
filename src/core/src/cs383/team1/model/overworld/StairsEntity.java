package cs383.team1.model.overworld;

import com.badlogic.gdx.Gdx;
import cs383.team1.model.overworld.Entity;
import cs383.team1.model.overworld.Position;

public class StairsEntity implements Entity {
	public static final int TYPE = 2;

	private Position pos;
        //Destination parameters for level transitioning (well3112)
        private String dest;
        private Position destPos;

        //Default constructor, should never let the default name pass through, 
        //will cause errors (well3112)
	public StairsEntity() {
		this(new Position(0, 0), "");
	}

	public StairsEntity(Position p, String s) {
		Gdx.app.debug("StairsEntity:StairsEntity", "instantiating class");
		pos = p;
                //Get the destination that exists between the two strings (well3112)
                //Indexes are used to allow for varying name and variable lengths (well3112)

		if (s != "") {
                	dest  = s.substring(13, s.indexOf(",destinationX:"));
	                destPos = new Position(
	                        Integer.parseInt(s.substring(
	                                s.indexOf("destinationX:")+13, 
	                                s.indexOf(",destinationY:"))),
	                        Integer.parseInt(s.substring(
	                                s.indexOf("destinationY:")+13, 
	                                s.indexOf("}")))
	                );
		} else {
			dest = "";
			destPos = new Position(0,0);
		}
	}

	public int type() {
		return TYPE;
	}

	public Position pos() {
		return pos;
	}
        
        public String destination(){
                return dest;
        }
        
        public Position destinationPos(){
                return destPos;
        }
}

package cs383.team1.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.Input.Keys;
import cs383.team1.input.InputManager;
import cs383.team1.model.State;
import cs383.team1.model.StateManager;
import cs383.team1.model.overworld.AreaManager;
import cs383.team1.model.overworld.Entity;
import cs383.team1.model.overworld.Player;
import cs383.team1.model.overworld.Position;
import cs383.team1.model.overworld.StairsEntity;
import cs383.team1.model.overworld.Tile;

public final class GameManager {
	public static final GameManager instance = new GameManager();

	public AreaManager areas;
	public StateManager states;

	private GameManager() {
		if(instance != null) {
			Gdx.app.error("GameManager:GameManager",
			  "reinstantiating singleton GameManager");
			throw new IllegalStateException("reinstantiating singleton");
		}

		Gdx.app.debug("GameManager:GameManager", "instantiating class");
		areas = AreaManager.instance;
		states = StateManager.instance;

		load();
	}

	public void load() {
		String fname;
		FileHandle areaDir;

		Gdx.app.log("GameManager:load", "Loading areas");

		areaDir = Gdx.files.internal("area/");

		for(FileHandle f : areaDir.list()) {
			fname = new String("area/" + f.name());
			Gdx.app.debug("GameManager:load", "Loading area " + fname);
                        areas.loadArea(fname);
		}
		changeArea("demo");
	}

	public void update(InputManager in) {
		Player player;
		Position next;
		Tile target;
                //This object is, like, super gross and should be removed once some
                //super smart, awesome person comes up with an alternative solution (well3112)
                StairsEntity se;


		player = areas.current.player;

		while(in.consumable()) {
			switch(in.keys.remove(0)) {
				case Keys.LEFT:
					next = new Position(player.pos.x - 1, player.pos.y);
					break;
				case Keys.RIGHT:
					next = new Position(player.pos.x + 1, player.pos.y);
					break;
				case Keys.UP:
					next = new Position(player.pos.x, player.pos.y + 1);
					break;
				case Keys.DOWN:
					next = new Position(player.pos.x, player.pos.y - 1);
					break;
				default:
					continue;
			}

			target = null;
			for(Tile t : areas.current.tiles) {
				if(t.pos().x == next.x && t.pos().y == next.y) {
					target = t;
					break;
				}
			}

			if(target == null) {
				Gdx.app.error("GameManager:update", "invalid move");
				continue;
			}

			if(target.passable()) {
				player.pos = next;
			}
                        
                        //Check if the current/targeted tile is a stairs tile (well3112)
                        if(target.type() == 3){
                                //Iterate through all entities (well3112)
                                for (Entity e : areas.current.entities) {
                                    if(
                                            //Find a stairs entity at the current
                                            //position of the player (well3112)
                                            e.pos().x == player.pos().x &&
                                            e.pos().y == player.pos().y &&
                                            e.type() == 2)
                                    {
                                            //Typecast the discovered entity into
                                            //a stairsentity object (well3112)
                                            se = (StairsEntity) e;
                                            //Change the area and display an
                                            //error if the transition is invalid (well3112)
                                            if (!changeArea(
                                                    se.destination(),
                                                    se.destinationPos()))
                                                    Gdx.app.error(
                                                            "GameManager:update",
                                                            "invalid stairs transition");
                                    }
                                }
                        }
		}

		/* TODO: move the keyhandling code to the StateManager */
		/*
		Gdx.app.debug("GameManager:update", "transitioning states");
		states.transition();
		*/
	}
        
        //Method to change the current area and set the future position (well3112)
        public boolean changeArea(String s, Position pos)
        {
            //Need support/major changes for multiplayer?
            //Each level currently uses baked-in character data
            //May need to change this to take a player parameter as well (well3112)
            
            //Check if the changearea method was successful (well3112)
            if(changeArea(s))
            {
                    //Add the appropriate level transition formatting 
                    //and set the player position (well3112)
                    areas.areas.get("area/".concat(s.concat(".txt"))).player.pos = pos;
                    //The function was successful, return true (well3112)
                    return true;
            }
            //Level doesn't exist, return false (well3112)
            return false;
        }
        
        //Method to change the current area (well3112)
        public boolean changeArea(String s)
        {
            //Add the appropriate level transition formatting (well3112)
            String sFull = "area/".concat(s.concat(".txt"));
            //Check if the level to transition to exists (well3112)
            if(areas.areas.containsKey(sFull))
            {
                    //Change the current area and return true (well3112)
                    areas.current = sFull != null ? areas.areas.get(sFull) : null;
                    return true;
            }
            //Level doesn't exist, return false (well3112)
            return false;
        }
}

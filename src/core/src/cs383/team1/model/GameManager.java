package cs383.team1.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.utils.Timer;
import cs383.team1.input.DialogueBox;
import cs383.team1.input.InputManager;
import cs383.team1.input.ui.Notification;
import cs383.team1.model.State;
import cs383.team1.model.StateManager;
import cs383.team1.model.overworld.AreaManager;
import cs383.team1.model.overworld.Entity;
import cs383.team1.model.overworld.Npc;
import cs383.team1.model.overworld.Player;
import cs383.team1.model.overworld.Position;
import cs383.team1.model.overworld.StairsEntity;
import cs383.team1.model.overworld.Tile;

public final class GameManager {
	public static final GameManager instance = new GameManager();

	public AreaManager areas;
	public StateManager states;

//        public DialogueBox chatBox = new DialogueBox();
        public String msg;
        public Notification notice = new Notification();
        
        
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
		areas.changeArea("demo");
	}

	public void update(InputManager in) {
		Player player;
		Position next;
		Tile target;
                Npc npc;

		player = areas.current.player;

		while(in.consumable()) {
			switch(in.keys.remove(0)) {
				case Keys.LEFT:
					next = new Position(player.pos.x - 1, player.pos.y);
                                        player.facing = 3;
					break;
				case Keys.RIGHT:
					next = new Position(player.pos.x + 1, player.pos.y);
                                        player.facing = 1;
					break;
				case Keys.UP:
					next = new Position(player.pos.x, player.pos.y + 1);
                                        player.facing = 0;
					break;
				case Keys.DOWN:
					next = new Position(player.pos.x, player.pos.y - 1);
                                        player.facing = 2;
					break;
                                case Keys.BACKSPACE:
                                        player.setNotice(new Notification("ui/back.png", "You pressed backspace!"));
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

                        //Interact with an NPC (nullifies last attempted move)
                        if ((npc = (Npc)areas.findEntity(next, 3)) != null) {
                                msg = npc.readNext();
                                System.out.println("GameManager: NPC Interaction: " + msg);
//                                chatBox.addMessage(npc.readNext());
                                next = player.pos;
                        }
                        
                        //Try to use stairs entity on a stairs tile (well3112)
                        if (target.type() == 3)
                                areas.useStairs(next);
                        
			if(target.passable()) {
                                player.floatPos = new Position((player.pos().x-next.x) * Tile.WIDTH, (player.pos().y-next.y) * Tile.HEIGHT);
				player.pos = next;
			}
                        
                        //Try to use stairs entity on a stairs tile (well3112)
                        if (target.type() == 3)
                                areas.useStairs(next);
		}

		/* TODO: move the keyhandling code to the StateManager */
		/*
		Gdx.app.debug("GameManager:update", "transitioning states");
		states.transition();
		*/
	}
        
}

package cs383.team1.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.Input.Keys;
import cs383.team1.combat.CombatManager;
import com.badlogic.gdx.utils.Timer;
import cs383.team1.input.DialogueBox;
import cs383.team1.input.InputManager;
import cs383.team1.inventory.Item;
import cs383.team1.model.State;
import cs383.team1.model.StateManager;
import cs383.team1.model.overworld.AreaManager;
import cs383.team1.model.overworld.Entity;
import cs383.team1.model.overworld.Npc;
import cs383.team1.model.overworld.Player;
import cs383.team1.model.overworld.Position;
import cs383.team1.model.overworld.StairsEntity;
import cs383.team1.model.overworld.Tile;
import java.util.ArrayList;

public final class GameManager {
	public static final GameManager instance = new GameManager();

	public AreaManager areas;
	public StateManager states;
        DialogueBox dialogue;
        public CombatManager combat;
        Position tempPos;
        Position returnPos;
        Entity temp;
        

        public DialogueBox chatBox = new DialogueBox();
        

        public int keyPressed;
//        public boolean parseInput = true;

        public ArrayList <String> msg;

        
	private GameManager() {
                this.keyPressed = 0;
                this.msg = new ArrayList();
		if(instance != null) {
			Gdx.app.error("GameManager:GameManager",
			  "reinstantiating singleton GameManager");
			throw new IllegalStateException("reinstantiating singleton");
		}

		Gdx.app.debug("GameManager:GameManager", "instantiating class");
		areas = AreaManager.instance;
		states = StateManager.instance;
                dialogue = new DialogueBox();
                combat = new CombatManager();
                tempPos = new Position();
                returnPos = new Position();

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
                
                //Load battles
                /*
                areaDir = Gdx.files.internal("combat/");

		for(FileHandle f : areaDir.list()) {
			fname = new String("combat/" + f.name());
			Gdx.app.debug("GameManager:load", "Loading combat " + fname);
                        //combat.loadCombat(fname);
		}
                */
	}

	public void update(InputManager in) {
		Player player;
		Position next;
		Tile target;
                Npc npc;
                Item item;
                //for(Entity e : areas.current.entities){
                  //  if(e.type() == 1){
                        player = areas.current.player;
                    //}
                //}
                int x = player.pos.x;
                int y = player.pos.y;
		
                in.limitList();
		while(
                        player.hp > 0 &&
                        in.consumable() &&
                        player.roaming == true &&
                        player.zeroFloat()) {

                        switch(in.keys.remove(0)) {
				case Keys.LEFT:
					next = new Position(x - 1, y);
                                        player.facing = 3;
					break;
				case Keys.RIGHT:
					next = new Position(x + 1, y);
                                        player.facing = 1;
					break;
				case Keys.UP:
					next = new Position(x, y + 1);
                                        player.facing = 0;
					break;
				case Keys.DOWN:
					next = new Position(x, y - 1);
                                        player.facing = 2;
					break;
                                case Keys.E:
                                        if((item = (Item)areas.findItem(player.pos(), 16)) != null) {
                                            System.out.println("Picked up Item");
                                            temp = areas.findItem(player.pos(), 16);
                                            player.addMove(item);
                                            player.inventory.pickUp(item);
                                            msg.add("Picked up " + item.name);
                                            areas.current.entities.remove(temp);
                                        }
                                        next = player.pos;
                                        break;
                                case Keys.D:
                                        //Drop Item: not really sure how to implement this with UI
                                        Item droppedItem = new Item(); 
                                        player.inventory.drop(droppedItem);
                                
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
                                               
/*
	                //Interact with an NPC (nullifies last attempted move)
                        if((npc = (Npc)areas.findEntity(next, 3)) != null) {
                                keyPressed = 0;
                                msg = npc.readNext();
                                System.out.println("GameManager: NPC Interaction: " + msg);
                                next = player.pos;
                        }
  */                      
                        //Try to use stairs entity on a stairs tile (well3112)
                        if (target.type() == 3){
                            areas.useStairs(next, player);
                        }
			if(target.passable()) {
                                player.floatPos = new Position((x-next.x) *
                                        Tile.WIDTH, (y-next.y) * Tile.HEIGHT);
				player.pos = next;
			}
                        
                        //Start combat with an NPC if they are next to the player
                        if ((npc = (Npc)areas.findCombatant(player.pos(), 3)) != null){
                                System.out.println("Starting combat");
                                tempPos = npc.pos();
                                returnPos = player.pos();
                                player.pos.x = (tempPos.x + 2);
                                player.pos.y = tempPos.y;
                                areas.getCombatArea(player.pos(), player, npc);
                                combat.encounter(areas.current.player, npc);
                        }
                        
                }
        }
}

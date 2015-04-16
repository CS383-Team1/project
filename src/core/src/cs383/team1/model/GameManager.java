package cs383.team1.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.Input.Keys;
import cs383.team1.combat.CombatManager;
import cs383.team1.input.DialogueBox;
import cs383.team1.input.InputManager;
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
        DialogueBox dialogue;
        CombatManager combat;
        Position tempPos;
        Position returnPos;
        

        public DialogueBox chatBox = new DialogueBox();
        
        
	private GameManager() {
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
	}

	public void update(InputManager in) {
		Player player;
		Position next;
		Tile target;
                Npc npc;
		player = areas.current.player;
                
		while(in.consumable() && player.roaming == true) {
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
/*
                        //Interact with an NPC (nullifies last attempted move)
                        if ((npc = (Npc)areas.findEntity(next, 3)) != null) {
                                chatBox.addMessage(npc.readNext());
                                next = player.pos;
                        }
  */                      
                        //Try to use stairs entity on a stairs tile (well3112)
                        if (target.type() == 3)
                                areas.useStairs(next);
                        
			if(target.passable()) {
				player.pos = next;
			}
                        
                        //Start combat with combat NPC
                        /*
                        if((npc = (Npc)areas.findCombatant(player.pos(), 3)) != null){
                            System.out.println("Starting combat");
                            areas.startCombat(player.pos(), player);
                        }
                        */
                        
                        //Start combat with an NPC if they are next to the player
                        if ((npc = (Npc)areas.findCombatant(player.pos(), 3)) != null) {
                                System.out.println("Starting combat");
                                System.out.println("Pringing npc.hp in GameManager: " + npc.hp);
                                //npc = (Npc)areas.findEntity(next,3);
                                tempPos = npc.pos();
                                returnPos = player.pos();
                                player.pos.x = (tempPos.x + 2);
                                player.pos.y = tempPos.y;
                                areas.getCombatArea(player.pos(), player, npc);
                                combat.addCombatants(player);
                                combat.addCombatants(npc);
                                combat.turn();
                        }
                        
                }
                
                //Combat input system
                while(in.consumable() && player.roaming == false) {
                        int selection = 0;
                        combat.turn();
                        
                        switch(in.keys.remove(0)) {
                            case Keys.NUM_0:
                                player.addAttack(player.moves.get(0));
                                selection = 0;
                                break;
                            case Keys.NUM_1:
                                player.addAttack(player.moves.get(1));
                                selection = 1;
                                break;
                            case Keys.NUM_2:
                                player.addAttack(player.moves.get(2));
                                selection = 2;
                                break;
                            case Keys.NUM_3:
                                player.addAttack(player.moves.get(3));
                                selection = 3;
                                break;
                            case Keys.NUM_4:
                                player.addAttack(player.moves.get(4));
                                selection = 4;
                                break;
                            case Keys.NUM_5:
                                player.addAttack(player.moves.get(5));
                                selection = 5;
                                break;
                            case Keys.NUM_6:
                                player.addAttack(player.moves.get(6));
                                selection = 6;
                                break;
                            case Keys.NUM_7:
                                player.addAttack(player.moves.get(7));
                                selection = 7;
                                break;
                            case Keys.NUM_8:
                                player.addAttack(player.moves.get(8));
                                selection = 8; 
                                break;
                            case Keys.NUM_9:
                                player.addAttack(player.moves.get(9));
                                selection = 9; 
                                break;
                            case Keys.E:
                                player.roaming = true;
                                player.pos = returnPos;
                                areas.endCombat(player);
                                selection = 0;
                                break;
                                default:
                                    selection = 0;
                                    player.addAttack(player.moves.get(0));
					continue;
                            }
                        
                        if(selection < player.moves.size()){
                            chatBox.addMessage("Player chooses: " + player.moves.get(selection).name);
                            chatBox.addMessage("Player hp: " + player.hp);
                        }
                        
                        
                }
                

		/* TODO: move the keyhandling code to the StateManager */
		/*
		Gdx.app.debug("GameManager:update", "transitioning states");
		states.transition();
		*/
	}
        
}

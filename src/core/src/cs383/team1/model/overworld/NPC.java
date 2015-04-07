/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs383.team1.model.overworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import cs383.team1.model.overworld.Entity;
import cs383.team1.model.overworld.Position;
import cs383.team1.model.overworld.Tile;
import java.util.ArrayList;
import java.util.Random;

public final class NPC implements Entity {
	public static final int TYPE = 2;
	public Position pos;
        Position next;
        Tile target;
        
	public int hp;
	public int mp;
	public int ap;
        boolean roaming = true;
        public ArrayList<String> quests = new ArrayList<String>();
        String quest;
	public NPC() {
	    this(new Position(0, 0), new String());
	}

        public NPC(Position p, String q) {
		Gdx.app.debug("Player:Player", "instantiating class");
		pos = p;
                quests.add(q);
                System.out.println("Printing quest in NPC: " + quests.get(0));
        }
        
	public int type() {
		return TYPE;
	}

	public Position pos() {
		return pos;
	}
        
        public String getQuest(){
            return quests.get(0);
        }
        
        //Add quest to arraylist of quests
        public void addQuest(String quest){
            quests.add(quest);
        }
        
        //Delete first quest in arraylist of quests
        public void deleteQuest(){
            quests.remove(0);
        }
        
        //Return first quest in list
        public String displayQuest(){
            return quests.get(0);
        }
        
        public void overworldAI(Area area) {
            Random randomDirection = new Random();
            int randomint = randomDirection.nextInt(4);
            System.out.println("Printing random direction: " + randomint);
            while(roaming == true){
                switch(randomint) {
				case 0:
                                        //Move Left
					next = new Position(pos.x - 1, pos.y);
					break;
				case 1:
                                        //Move right
					next = new Position(pos.x + 1, pos.y);
					break;
				case 2:
                                        //Move up
					next = new Position(pos.x, pos.y + 1);
					break;
				case 3:
                                        //Move down
					next = new Position(pos.x, pos.y - 1);
					break;
                                
			}

			target = null;
			for(Tile t : area.tiles) {
				if(t.pos().x == next.x && t.pos().y == next.y) {
					target = t;
					break;
				}
                                //if(t.pos().x == player.pos.x && t.pos().y == player.y){
                                    
                                //}
			}

			if(target == null) {
				Gdx.app.error("GameManager:update", "invalid move");
				
			}

			if(target.passable()) {
				pos = next;
			}
                    }
            }
}
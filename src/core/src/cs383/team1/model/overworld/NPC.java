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
import java.util.Random;

public final class NPC implements Entity {
	public static final int TYPE = 2;
	public Position pos;
        Position next;
        Tile target;
        
	public int hp;
	public int mp;
	public int ap;
                  

	public NPC() {
		//this(new Position(0, 0), 0, 0, 0);
            this(new Position(0, 0));
	}

	
        public NPC(Position p) {
		Gdx.app.debug("Player:Player", "instantiating class");
		pos = p;
        }

	public int type() {
		return TYPE;
	}

	public Position pos() {
		return pos;
	}
        
        public void ai(Area area) {
            Random randomDirection = new Random();
            int randomint = randomDirection.nextInt(4);
            System.out.println("Printing random direction: " + randomint);
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
			}

			if(target == null) {
				Gdx.app.error("GameManager:update", "invalid move");
				
			}

			if(target.passable()) {
				pos = next;
			}
		}
}
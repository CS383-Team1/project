/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs383.team1.input;

import java.util.ArrayList;
import cs383.team1.model.overworld.Player;
import com.badlogic.gdx.Input.Keys;


/**
 *
 * @author Casey
 */
public class InputManager {
 	public ArrayList<Integer> keys;
        float moveAmount = 32.0f;
        public boolean running = true;
        
	public InputManager() {
            keys = new ArrayList<Integer>();
	}
        
    public void processInput(Player player){
        int currentKey = keys.get(0);        
        
        
        if(currentKey == Keys.RIGHT){
            translateXRight(player);
        }
        if(currentKey == Keys.LEFT)
            translateXLeft(player);
        if(currentKey == Keys.DOWN)
            translateYDown(player);
        if(currentKey == Keys.UP)
            translateYUp(player);
        if(currentKey == Keys.ESCAPE){
            if(running == true){
                //Pause game, then launch main menu    
                running = false;
                //Launch Main Menu
            }
        
            //If game is already paused, then escape will resume game
            if(running == false){
                running = true;
                //Exit main menu
            }
        }
        keys.remove(0);
        
    }
    
    public void add (int key){
        keys.add(key);
    }
    
    public void delete(int key){
        keys.remove(key);
    }
    
    public void translateXLeft(Player player){
        player.pos.x -= moveAmount;
    }
    
    public void translateXRight(Player player){
        player.pos.x += moveAmount;
    }
    
    public void translateYUp(Player player){
            player.pos.y += moveAmount;
    }
    
    public void translateYDown(Player player){
            player.pos.y -= moveAmount;
    }

    
	public boolean consumable() {
		return !keys.isEmpty();
	}
    
}
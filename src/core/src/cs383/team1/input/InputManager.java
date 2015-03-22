/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs383.team1.input;

import java.util.ArrayList;
import cs383.team1.model.overworld.Player;
import com.badlogic.gdx.Input.Keys;
import cs383.team1.model.overworld.Area;
import cs383.team1.model.overworld.Position;
import cs383.team1.model.overworld.Tile;
import cs383.team1.model.overworld.DemoTile;
/**
 *
 * @author Casey
 */
public class InputManager {
 	public ArrayList<Integer> keys;
        float moveAmount = 32.0f;
        public boolean running = true;
        Position currentPosition;
        
	public InputManager() {
            keys = new ArrayList<Integer>();
         
        }
        
    public void processInput(Player player, Area area){
        int currentKey = keys.get(0);        
        currentPosition = player.pos;
        Tile currentTile = new DemoTile();
        Tile leftTile = new DemoTile();;
        Tile rightTile = new DemoTile();
        Tile upTile = new DemoTile();
        Tile downTile = new DemoTile();
        
             
        
        for(int i = 0; i < area.tiles.size(); i++){
            if((player.pos.x == area.tiles.get(i).pos().x) && (player.pos.y == area.tiles.get(i).pos().y)){
                 currentTile = area.tiles.get(i);
                 
            }
            if((player.pos.x == (area.tiles.get(i).pos().x) - 32) && (player.pos.y == area.tiles.get(i).pos().y)){
                 rightTile = area.tiles.get(i);
                 
            }
            if((player.pos.x == (area.tiles.get(i).pos().x) + 32) && (player.pos.y == area.tiles.get(i).pos().y)){
                 leftTile = area.tiles.get(i);
                 
            }
            if((player.pos.x == area.tiles.get(i).pos().x) && (player.pos.y == (area.tiles.get(i).pos().y) - 32)){
                 upTile = area.tiles.get(i);
                 //if(upTile.pos().y < screen)
                 
            }
            if((player.pos.x == area.tiles.get(i).pos().y) && (player.pos.y == (area.tiles.get(i).pos().y) + 32)){
                 downTile = area.tiles.get(i);
                 
            }
        }
        
        if((currentKey == Keys.RIGHT) && (rightTile.passable() != false)){
            translateXRight(player);
            player.playerDirection = "right";
        }
        if((currentKey == Keys.LEFT) && (leftTile.passable() != false)){
            translateXLeft(player);
            player.playerDirection = "left";
        }
        if((currentKey == Keys.DOWN) && (downTile.passable() != false)){
            translateYDown(player);
            player.playerDirection = "down";
        }
        if((currentKey == Keys.UP) && (upTile.passable() != false)){
            translateYUp(player);
            player.playerDirection = "up";
        }
        if(currentKey == Keys.ESCAPE){
         
                //Pause game, then launch main menu    
         
                //Launch Main Menu
         
            //If game is already paused, then escape will resume game
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
        //Check to see if player is moving out of bounds
        
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
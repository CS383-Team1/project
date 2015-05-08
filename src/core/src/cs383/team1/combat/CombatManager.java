/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs383.team1.combat;


import com.badlogic.gdx.files.FileHandle;
import cs383.team1.model.overworld.Player;
import cs383.team1.model.overworld.Npc;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *
 * @author Casey
 */
public class CombatManager {
    Combat currentBattle;
    Map<Integer, Player> participants = new HashMap<Integer, Player>();
    
    public ArrayList<Combat> battles = new ArrayList<Combat>();
    
    public CombatManager(){
        //battles.add(new Combat());
    }
    
    public void encounter(Player player, Npc npc, Map<Integer, Player> otherPlayers){
        battles.add(new Combat());
        battles.get(0).reward = npc.inventory;
        //Put first player and his allies into battle
        battles.get(0).allies.members.put(player.playerID, player);
        //for(Map.Entry<Integer, Player> e : otherPlayers.entrySet()){
          //  battles.get(0).allies.members.put(e.getKey(), e.getValue());
        //}
        battles.get(0).enemies.members.put(0, npc);
//        System.out.println("Inside CombatManager.encounter()");
               
    }
   }

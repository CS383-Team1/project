/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs383.team1.model.combat;


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
        battles.get(0).allies.members.put(0, player);
        for(Map.Entry<Integer, Player> e : otherPlayers.entrySet()){
            battles.get(0).allies.members.put(e.getKey(), e.getValue());
        }
        battles.get(0).enemies.members.put(0, npc);
    }
    
    /*
    public void loadCombat(String fname){
        String fcontents;
                String entityData;
		
		Player player;
		String[] vals;
		FileHandle fin;
		int numBattles;
                int i;
		int offset = 0;
                String first;
                String second;
                String third;
		
		Gdx.app.log("AreaManager:loadCombat", "Loading " + fname);

		fin = Gdx.files.internal(fname);
		fcontents = fin.readString();
		vals = fcontents.trim().split("\\s+");

		Gdx.app.debug("AreaManager:loadCombat", "Loading Npcs");
                int k = 0;
		numBattles = Integer.parseInt(vals[offset++]);
		for(i = offset; i < offset + (numBattles * 3); i += 3) {
			first = vals[i + 0];
			second = vals[i + 1];
			third = vals[i + 2];
                        
			Gdx.app.debug("CombatManager:loadCombat", "Loading NpcEntity");
                        battles.get(k).enemies.addCombatants();
                }
    }
    */
   }

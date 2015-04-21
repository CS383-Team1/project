/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs383.team1.combat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import cs383.team1.model.overworld.Entity;
import cs383.team1.model.overworld.Player;
import cs383.team1.model.overworld.Npc;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Casey
 */
public class CombatManager {
    Combat currentBattle;
    ArrayList<Player> participants = new ArrayList<Player>();
    //Will change this when file read in is figured out
    ArrayList<Npc> e = new ArrayList<Npc>();
    
    public ArrayList<Combat> battles = new ArrayList<Combat>();
    
    public CombatManager(){
        battles.add(new Combat());
    }
    
    public void encounter(Player player, Npc npc){
        participants.add(player);
        battles.get(0).allies.members.add(player);
        battles.get(0).enemies.members.add(npc);
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

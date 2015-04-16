/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs383.team1.combat;

import cs383.team1.model.overworld.Entity;
import cs383.team1.model.overworld.Player;
import cs383.team1.model.overworld.Npc;
import java.util.ArrayList;

/**
 *
 * @author Casey
 */
public class CombatManager {
    private ArrayList<Entity> combatants = new ArrayList<Entity>();
    int lastPlayerDamage;
    Move playerMove;
    int lastPlayerMove;
    int playerBlockPercent;
    Player player;
    Npc npc;
    
    public CombatManager(){
        
        lastPlayerDamage = 0;
        playerMove = new Move();
        lastPlayerMove = 0;
        playerBlockPercent = 1;
    }
    
    public void addCombatants(Entity e){
        combatants.add(e);
    }
    
    public void removeCombatatnts(Entity e){
        combatants.remove(e);
    }
    
    public int turn(){
        //System.out.println("Printing player.hp : npc.hp : " + player.hp + " " + npc.hp);
        //while((combatants.size() > 1) && (player.hp > 0) && npc.hp > 0){
        int damage;
        for(Entity e : combatants){
                //while((System.currentTimeMillis() - startTime) < 5000){
                if(e.type() == 1){
                    player = (Player)e;
                }
                else if(e.type() == 3){
                    npc = (Npc)e;
                }
        }
        
        
        if((combatants.size() > 1) && (player.hp > 0) && npc.hp > 0){
            playerBlockPercent = 1;
            long startTime = System.currentTimeMillis();
            
            for(Entity e : combatants){
                if(e.type() == 1){
                    player = (Player)e;
                    if(player.consumableAttack()){
                    playerMove = player.attacks.get(0);
                    lastPlayerMove = player.attacks.indexOf(playerMove);
                    playerBlockPercent = playerMove.getBlockPercent();
                    //If damage attribute in move instance is positive, then deal to npc hp, else add to player hp
                    if(player.attacks.get(0).getDamage() >= 0){
                        npc.hp -= player.attacks.get(0).getDamage();
                    }
                    else{
                        player.hp -= player.attacks.get(0).getDamage();
                    }
                        System.out.println("Player attacking with move " + player.attacks.get(0).name);
                        player.removeAttack();
                    }
                }
                if(e.type() == 3){
                    npc = (Npc)e;
                    damage = npc.combatAI(lastPlayerDamage, lastPlayerMove);
                    if(npc.consumableAttack()){
                
                        System.out.println("Printing lastPlayerDamage: " + lastPlayerDamage);
                        lastPlayerDamage = npc.attacks.get(0).getDamage();
                        //If damage attribute in move instance is positive, then deal to player hp, else add to npc hp
                        if(npc.attacks.get(0).getDamage() >= 0){
                            player.hp -= damage / playerBlockPercent;
                        }else{
                            npc.hp -= player.attacks.get(0).getDamage();
                        }
                
                        System.out.println("NPC attacking with move " + npc.attacks.get(0).name);
                        npc.removeAttack();
                    }
                }
                }
             System.out.println("Printing player.hp : npc.hp : " + player.hp + " " + npc.hp);
        }else{
            player.roaming = true;
            npc.hp = 100;
            combatants.remove(player);
            combatants.remove(npc);
            return 0;
        }
     return 0;   
    }
}

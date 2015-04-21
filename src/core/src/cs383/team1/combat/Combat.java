/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs383.team1.combat;

import cs383.team1.model.overworld.Entity;
import cs383.team1.model.overworld.Npc;
import cs383.team1.model.overworld.Player;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Casey
 */
public class Combat {
    Combatants allies = new Combatants();
    Combatants enemies;
    double lastPlayerDamage;
    Move playerMove;
    Move npcMove;
    int lastPlayerMove;
    int playerBlockPercent;
    int npcBlockPercent;
    Player player;
    Npc npc;
    Random selectionGen; 
    int random;
    
    //Item reward;
    
    public Combat(){
        allies = new Combatants();
        enemies = new Combatants();
        lastPlayerDamage = 0.0;
        playerMove = new Move();
        npcMove = new Move();
        lastPlayerMove = 0;
        playerBlockPercent = 1;
        npcBlockPercent = 1;
        selectionGen = new Random();
        //reward = new Item();
    }
    
    public int turn(){
                
        double damage;
        //Implement this when muliplayer is added
        //for(Entity e : allies.members){
                
                    player = (Player)allies.members.get(0);
                
        //}
        for(Entity e : enemies.members){
            if(e.type() == 3){
                npc = (Npc)e;
            }
        }
        
        if(((allies.members.size() + enemies.members.size()) > 1) && (player.hp > 0) && npc.hp > 0){
            playerBlockPercent = 1;
            for(Entity e : allies.members){
                
                    player = (Player)e;
                    //Choose random Npc to attack
                    random = selectionGen.nextInt(allies.members.size());
                    npc = (Npc)enemies.members.get(random);
                    
                    if(player.consumableAttack()){
                    playerMove = player.attacks.get(0);
                    lastPlayerMove = player.attacks.indexOf(playerMove);
                    playerBlockPercent = playerMove.getBlockPercent();
                    //If damage attribute in move instance is positive, then deal to npc hp, else add to player hp
                    if(player.attacks.get(0).getDamage() >= 0){
                        npc.hp -= player.attacks.get(0).getDamage() / npcBlockPercent;
                    }
                    else{
                        player.hp -= player.attacks.get(0).getDamage();
                    }
                        System.out.println("Player attacking with move " + player.attacks.get(0).name);
                        player.removeAttack();
                    }
                    
            }
            for(Entity e : enemies.members){
                    
                    npc = (Npc)e;
                    damage = npc.combatAI(lastPlayerDamage, lastPlayerMove);
                    if(npc.consumableAttack()){
                        npcMove = npc.attacks.get(0);
                        npcBlockPercent = npcMove.getBlockPercent();
                        System.out.println("Printing lastPlayerDamage: " + lastPlayerDamage);
                        lastPlayerDamage = npc.attacks.get(0).getDamage();
                        //Picking random player to attack
                        random = selectionGen.nextInt(allies.members.size());
                        player = (Player)allies.members.get(random);
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
        
             System.out.println("Printing player.hp : npc.hp : " + player.hp + " " + npc.hp);
        }else{
            player.roaming = true;
            
            //Will need to iterate over this list to remove all allies
                allies.removeCombatants(player);
            
            //Will need to iterate over this list to remove all enemies
                enemies.removeCombatants(npc);
            
            return 0;
        }
     return 0;   
    }
    
}

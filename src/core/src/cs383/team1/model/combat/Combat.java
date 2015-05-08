package cs383.team1.model.combat;

import cs383.team1.model.inventory.Inventory;
import cs383.team1.model.inventory.Item;
import cs383.team1.Main;
import cs383.team1.model.GameManager;
import cs383.team1.model.overworld.Entity;
import cs383.team1.model.overworld.Npc;
import cs383.team1.model.overworld.Player;
import java.util.Map;
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
    
    public Inventory reward = new Inventory("Combat reward");
    
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
        //reward = new Inventory("NPC");
    }
    
    public Combat(Inventory inventory){
        allies = new Combatants();
        enemies = new Combatants();
        lastPlayerDamage = 0.0;
        playerMove = new Move();
        npcMove = new Move();
        lastPlayerMove = 0;
        playerBlockPercent = 1;
        npcBlockPercent = 1;
        selectionGen = new Random();
        reward = inventory;
    }
    
    
    public int turn(){
                
        double damage;
        //Implement this when muliplayer is added
         if(allies.members.size() > 0){            
            for(Integer i : allies.members.keySet()){
                player = (Player)allies.members.get(i);
            }
        }else
            return 0;
       
        if(enemies.members.size() > 0){
                npc = (Npc)enemies.members.get(0);
        }
        System.out.println("Printing player and NPC health: " + player.hp + " : " + npc.hp);
        
        if(((allies.members.size() + enemies.members.size()) > 1) 
                && (player.hp > 0) && npc.hp > 0){
            playerBlockPercent = 1;
            for(Map.Entry<Integer, Entity> e : allies.members.entrySet()){
                
                    player = (Player)e.getValue();
                    //Choose random Npc to attack
                    random = selectionGen.nextInt(enemies.members.size());
                    npc = (Npc)enemies.members.get(random);
                    
                    if(player.consumableAttack()){
                    playerMove = player.attacks.get(0);
                    lastPlayerMove = player.attacks.indexOf(playerMove);
                    playerBlockPercent = playerMove.getBlockPercent();
                    //If damage attribute in move instance is positive, 
                    //then deal to npc hp, else add to player hp
                    if(player.attacks.get(0).getDamage() >= 0){
                        npc.hp -= player.attacks.get(0).getDamage() / npcBlockPercent;
                    }
                    else{
                        player.hp -= player.attacks.get(0).getDamage();
                    }
                        System.out.println("Player attacking with move " 
                                + player.attacks.get(0).name);
                        player.removeAttack();
                    }
                    
            }
            for(Map.Entry<Integer, Entity> e : enemies.members.entrySet()){
                    
                    npc = (Npc)e.getValue();
                    damage = npc.combatAI(lastPlayerDamage, lastPlayerMove);
                    if(npc.consumableAttack()){
                        npcMove = npc.attacks.get(0);
                        npcBlockPercent = npcMove.getBlockPercent();
                        System.out.println("Printing lastPlayerDamage: " 
                                + lastPlayerDamage);
                        lastPlayerDamage = npc.attacks.get(0).getDamage();
                        //Picking random player to attack
                        random = selectionGen.nextInt(allies.members.size());
                        //player = (Player)allies.members.get(random);
                        //If damage attribute in move instance is positive, 
                        //then deal to player hp, else add to npc hp
                        if(npc.attacks.get(0).getDamage() >= 0){
                            player.hp -= damage / playerBlockPercent;
                        }else{
                            npc.hp -= player.attacks.get(0).getDamage();
                        }
                
                        System.out.println("NPC attacking with move " 
                                + npc.attacks.get(0).name);
                        npc.removeAttack();
                    }
                }
        
             System.out.println("Printing player.hp : npc.hp : " 
                     + player.hp + " " + npc.hp);
		Main.gm.addMessage("Player HP: " 
                        + player.hp + "; NPC HP: " + npc.hp);

        }else{
            //Add all items in reward inventory to player's inventory
            //if player wins
            if(npc.hp <= 0){
                
                for(Item i : reward.contents){
                    System.out.println("Giving reward: " + i.name);
                    player.inventory.pickUp(i);
                    player.addMove(i);
                    //npc.inventory.drop(i);
                }
            }
            
            player.roaming = true;
         
            allies.members.remove(1);
            int count = 0;
                while(allies.members.size() > 1){
                    allies.members.remove(count);
                    count++;
                }
                
            //NPC is at key value 0
            enemies.members.remove(0);
            return 0;
        }
     return 0;   
    }
}

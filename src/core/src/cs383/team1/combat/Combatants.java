/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs383.team1.combat;

import cs383.team1.model.overworld.Entity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Casey
 */
public class Combatants {
    public Map<Integer, Entity> members = new HashMap<Integer, Entity>();
    
    public void Combatants(){
        
    }
    
    /*
    public void addCombatants(Entity e){
        members.put(e);
    }
    */
    
    public void removeCombatants(Integer key){
        members.remove(key);
    }
    
}

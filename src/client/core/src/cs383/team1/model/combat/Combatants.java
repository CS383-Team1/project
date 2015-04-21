/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs383.team1.model.combat;

import cs383.team1.model.overworld.Entity;
import java.util.ArrayList;

/**
 *
 * @author Casey
 */
public class Combatants {
    public ArrayList<Entity> members = new ArrayList<Entity>();
    
    public void Combatants(){
        
    }
    
    
    public void addCombatants(Entity e){
        members.add(e);
    }
    
    public void removeCombatants(Entity e){
        members.remove(e);
    }
    
}

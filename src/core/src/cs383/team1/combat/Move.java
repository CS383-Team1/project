/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs383.team1.combat;

/**
 *
 * @author Casey
 */
public class Move {
    public String name = new String();
    private int damage;
    private int blockPercent;
    
    public Move(){
        
    }
    
    public Move(String n, int d, int b){
        name = n;
        damage = d;
        blockPercent = b;
    }
    
    public int getDamage(){
        return damage;
    }
    
    public int getBlockPercent(){
        return blockPercent;
    }
    
}

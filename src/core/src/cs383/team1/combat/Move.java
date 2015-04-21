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
    private double damage;
    private int blockPercent;
    
    public Move(){
        name = "null";
        damage = 0;
        blockPercent = 1;
    }
    
    public Move(String n, double d, int b){
        this.name = n;
        this.damage = d;
        this.blockPercent = b;
        
    }
    
    public double getDamage(){
        return damage;
    }
    
    public int getBlockPercent(){
        return blockPercent;
    }
    
}

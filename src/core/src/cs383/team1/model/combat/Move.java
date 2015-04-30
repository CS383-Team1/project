/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs383.team1.model.combat;

/**
 *
 * @author Casey
 */
public class Move {
    public String name = new String();
    private double damage;
    private int blockPercent;
    private double healing;
    
    public Move(){
        name = "null";
        damage = 0;
        blockPercent = 1;
        healing = 0;
    }
    
    public Move(String n, double d, int b){
        this.name = n;
        this.damage = d;
        this.blockPercent = b;
        healing = 0;
    }
    
    //Constructor for moves that have healing
    public Move(String n, double d, int b, int h){
        this.name = n;
        this.damage = d;
        this.blockPercent = b;
        healing = h;
    }
    
    public double getDamage(){
        return damage;
    }
    
    public int getBlockPercent(){
        return blockPercent;
    }
}

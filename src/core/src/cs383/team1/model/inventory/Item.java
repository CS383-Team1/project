
package cs383.team1.model.inventory;

import cs383.team1.model.combat.Move;
import cs383.team1.model.overworld.Entity;
import cs383.team1.model.overworld.Position;

/**
 *
 * @author Tessa
 */
public class Item implements Entity {
    // object for sprite?
    public String name;
    public String description;
    public String type; //need not be string. Type could be ints or something
    
    public Double hitChance; //0.0 to 1.0
    public Double critChance; 
    public Double critMultiplier;
    public Double range;
    public Double damage;
    public Move requiredMove;
    private Position pos;
    
    
    public Item(){
        this.name = "Unknown";
        this.description = "eh, now it's got yellow on it.";
        this.type = "Unknown";
        this.hitChance = 0.0;
        this.critChance = 0.0;
        this.critMultiplier = 0.0;
        this.damage = 0.0;
        this.range = 0.0;
        this.requiredMove = new Move(name, damage.intValue(), 1);
        pos = new Position();
        System.out.println("Made unnamed Item");
    }
    public Item(String n, String desc, String t){
        this.name = n;
        this.description = desc;
        this.type = t;
        this.hitChance = 0.0;
        this.critChance = 0.0;
        this.critMultiplier = 0.0;
        this.damage = 0.0;
        this.range = 0.0;
        
        System.out.println("Made Item named " + n);
    }
    
    public Item(String n, String desc, String t, Position p){
        this.name = n;
        this.description = desc;
        this.type = t;
        this.hitChance = 0.0;
        this.critChance = 0.0;
        this.critMultiplier = 0.0;
        this.damage = 0.0;
        this.range = 0.0;
        pos = p;
        System.out.println("Made Item named " + n);
    }
    
    //Constructor that uses string scanning
    public Item(Position p, String s){
       
        this.name  = s.substring(6, s.indexOf(",desc:"));
        this.description = s.substring(s.indexOf(",desc:")+6, 
                                       s.indexOf(",type:"));
        System.out.println("Printing description: " + description);
        this.type = s.substring(s.indexOf(",type:")+6, 
                                s.indexOf(",hit:"));
        System.out.println("Printing type: " + type);
        this.hitChance = Double.parseDouble(s.substring(
                                s.indexOf(",hit:")+5,
                                s.indexOf(",critC:")));
        System.out.println("Printing hitChance: " + hitChance);
        this.critChance = Double.parseDouble(s.substring(
                                s.indexOf(",critC:")+7,
                                s.indexOf(",critM:")));                
        this.critMultiplier = Double.parseDouble(s.substring(
                                s.indexOf(",critM:")+7,
                                s.indexOf(",d:")));        
        this.damage = Double.parseDouble(s.substring(
                                s.indexOf(",d:")+3,
                                s.indexOf(",r")));
        this.range = Double.parseDouble(s.substring(
                                s.indexOf(",r:")+3,
                                s.indexOf("}")));
        pos = p;
        System.out.println("Printing pos in item: " + pos.x + " " + pos.y);
        System.out.println("Made Item named " + this.name);
            
    }
    public Item(String n, String desc,String t,Double d,Double hit,Double crit,
            Double critM, Double r, Position p){
        this.name = n;
        this.description = desc;
        this.type = t;
        this.hitChance = hit;
        this.critChance = crit;
        this.critMultiplier = critM;
        this.damage = d;
        this.range = r;
        pos = p;
        System.out.println("Made Item named " + n);
    }
    public Double damage(){
        Double d = 0.0;
        if( Math.random() < critChance ){
            d = damage;
        }
        if(Math.random() < critChance){
            d = d*critMultiplier;
        }
        return d;
    }

    @Override
    public int type() {
        int type = 16;
        return type;
    }

    
    public Position pos() {
        return pos;
    }
}

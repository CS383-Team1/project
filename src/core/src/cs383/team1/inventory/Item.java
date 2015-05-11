
package cs383.team1.inventory;

import cs383.team1.combat.Move;
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
    public Double hp;
    public Double mp;
    public Double ap;
    
    public Item(){
        this.name = "Unknown";
        this.description = "eh, now it's got yellow on it.";
        this.type = "Unknown";
        this.hitChance = 0.0;
        this.critChance = 0.0;
        this.critMultiplier = 0.0;
        this.damage = 0.0;
        this.range = 0.0;
	this.hp = 0.0;
	this.mp = 0.0;
	this.ap = 0.0;
        this.requiredMove = new Move(name, damage.intValue(), 1);
        pos = new Position();
//        System.out.println("Made unnamed Item");
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
	this.hp = 0.0;
	this.mp = 0.0;
	this.ap = 0.0;
        
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
	this.hp = 0.0;
	this.mp = 0.0;
	this.ap = 0.0;
        pos = p;
        System.out.println("Made Item named " + n);
    }
    
    //Constructor that uses string scanning
    public Item(Position p, String s){
//	    String [] fields = s.split("[a-zA-Z]+:");
//	    for (int i = 0; i < fields.length; i++) {
//		    System.out.println(fields[i]);
//	    }
       
        this.name  = s.substring(6, s.indexOf(",desc:"));
        this.description = s.substring(s.indexOf(",desc:")+6, 
                                       s.indexOf(",type:"));
        this.type = s.substring(s.indexOf(",type:")+6, 
                                s.indexOf(",stats:{"));
	
	if (	type.contains("weapon") ||
		type.contains("ring") ||
		type.contains("consumable")) {

		this.hitChance = readStat(s, ",hit:");
		this.critChance = readStat(s, ",critC:");
		this.critMultiplier = readStat(s, ",critM:");
		this.damage = readStat(s, ",d:");
		this.range = readStat(s, ",r:");
	} else {
		this.hp = readStat(s, ",hp:");
		this.mp = readStat(s, ",mp:");
		this.ap = readStat(s, ",ap:");
	}
        pos = p;
        System.out.println("Printing pos in item: " + pos.x + " " + pos.y);
        System.out.println("Made Item named " + this.name);
            
    }
    
    /*
    Read a stat from a string serialization
    */
    private Double readStat(String s, String p) {
	    int start = 0;
	    int end;
	    
	    if (s.contains("p"))
		    start = s.indexOf(p)+p.length();

	    if (s.substring(start).contains(",")) end = s.indexOf(",", start);
	    else if (s.substring(start).contains("}"))
		    end = s.indexOf("}",start);
	    else end = s.length()-1;

	    if (s.substring(start, end).matches("[0-9]+\\.?[0-9]+"))
		    return Double.parseDouble(s.substring(start, end));
	    else return 0.0;
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

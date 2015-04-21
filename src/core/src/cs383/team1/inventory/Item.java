
package cs383.team1.inventory;

/**
 *
 * @author Tessa
 */
public class Item {
    // object for sprite?
    public String name;
    public String description;
    public String type; //need not be string. Type could be ints or something
    public Double hitChance; //0.0 to 1.0
    public Double critChance; 
    public Double critMultiplier;
    public Double range;
    public Double damage;
    
    
    public Item(){
        this.name = "Unknown";
        this.description = "eh, now it's got yellow on it.";
        this.type = "Unknown";
        this.hitChance = 0.0;
        this.critChance = 0.0;
        this.critMultiplier = 0.0;
        this.damage = 0.0;
        this.range = 0.0;
        System.out.println("Made unnamed Item");
    }
    public Item(String n, String desc,String t){
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
    public Item(String n, String desc,String t,Double d,Double hit,Double crit,
            Double critM, Double r){
        this.name = n;
        this.description = desc;
        this.type = t;
        this.hitChance = hit;
        this.critChance = crit;
        this.critMultiplier = critM;
        this.damage = d;
        this.range = r;
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
}

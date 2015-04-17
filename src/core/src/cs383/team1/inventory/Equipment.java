
package cs383.team1.inventory;
import java.util.ArrayList;
/**
 *
 * @author Tessa
 */
public class Equipment {
    public int maxSlots = 4;
    public int maxRings = 2;
    public String owner;
    // clothing/accessories slots
    public Item head; // glasses/helmet
    public Item chest; // suit jacket/blouse
    public Item legs; // suit pants/skirt
    public Item hands; // gloves/braclets
    public ArrayList<Item> rings; // rings, max 2?
    public Item feet; // shoes
    public Item neck; // necklace
    
    public Item rightWeapon; // main weapon
    public Item leftWeapon; // if two handed weapon is equiped, unequip this
    public ArrayList<Item> quickSlots ; // maybe max 4?
    
    public Equipment(){
        head = null;
        chest = null;
        legs = null;
        hands = null;
        rings = new ArrayList();
        feet = null;
        neck = null;
        quickSlots = new ArrayList();
        System.out.println("made Equipment");
    }
    
    public int size(){
        int count = 0;
        if(head != null){count++;}
        if(chest != null){count++;}
        if(legs != null){count++;}
        count+=rings.size();
        if(feet != null){count++;}
        if(neck != null){count++;}
        count+=quickSlots.size();
        return count;
    }
    
    public Item equip(Item n){
        Item ret = null;
        if("head".equals(n.type)){ret = head; head = n;}else 
        if("chest".equals(n.type)){ret = chest;chest = n;}else
        if("legs".equals(n.type)){ret = legs;chest = n;}else
        if("feet".equals(n.type)){ret = feet;feet = n;}else
        if("neck".equals(n.type)){ret = neck;neck = n;}else
        if("rings".equals(n.type)){
            if( rings.size() < maxRings){
                rings.add(n);
            }else{
                ret = rings.get(0);
                rings.remove(0);
                rings.add(n);
            }
        }else
        if("consumable".equals(n.type)){
            if(quickSlots.size() < maxSlots){
                quickSlots.add(n);
            }else{
                ret = quickSlots.get(0);
                quickSlots.remove(0);
                quickSlots.add(n);
            }
        }
        
        return ret;
    }
    
    public Item unequip(String t){
        Item ret = null;
        if("head".equals(t)){ret = head; head = null;}else 
        if("chest".equals(t)){ret = chest;chest = null;}else
        if("legs".equals(t)){ret = legs;chest = null;}else
        if("feet".equals(t)){ret = feet;feet = null;}else
        if("neck".equals(t)){ret = neck;neck = null;}else
        if("rings".equals(t)){
            if( rings.size() < maxRings){
                ret = rings.get(0);
                rings.remove(0);
            }
        }else
        if("consumable".equals(t)){
            if(quickSlots.size() < maxSlots){
                ret = quickSlots.get(0);
                quickSlots.remove(0);
            }
        }
        
        return ret;
    }
}

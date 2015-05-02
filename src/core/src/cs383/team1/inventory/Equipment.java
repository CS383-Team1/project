
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
        head = new Item();
        chest = new Item();
        legs = new Item();
        hands = new Item();
        rings = new ArrayList();
        feet = new Item();
        neck = new Item();
	rightWeapon = new Item();
	leftWeapon = new Item();
	    
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
        System.out.println("equipping " + n.name);
        if(n.type.contains("head")){ret = head; head = n;}else 
        if(n.type.contains("chest")){ret = chest;chest = n;}else
	if(n.type.contains("hands")){ret = hands;hands = n;}else
        if(n.type.contains("legs")){ret = legs;legs = n;}else
        if(n.type.contains("feet")){ret = feet;feet = n;}else
        if(n.type.contains("neck")){ret = neck;neck = n;}else
        if(n.type.contains("rings")){
            if( rings.size() < maxRings){
                rings.add(n);
            }else{
                ret = rings.get(0);
                rings.remove(0);
                rings.add(n);
            }
        }else
        if(n.type.contains("consumable")){
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
    
    public boolean equipWeapon(Item n, String side){
        if(n.type.contains( "weapon")){
            if(n.type.contains("two-handed")){
                // remove any existing weapons
                unequip(this.rightWeapon);
                unequip(this.leftWeapon);
                rightWeapon = n;
            }else if("right".equals(side)){
                unequip(this.rightWeapon);
                rightWeapon = n;
            }else if ("left".equals(side)){
                unequip(this.leftWeapon);
                leftWeapon = n;
            }else{
                if(rightWeapon == null){
                    rightWeapon = n;
                }else if(leftWeapon == null){
                    leftWeapon = n;
                }else{
                    return false;
                }
            }
            return true;
        }else{
            return false;
        }
    }
    
    public Item unequip(Item t){
        Item ret = new Item();
        if(head.equals(t)){ret = head;head = new Item();}else 
        if(chest.equals(t)){ret = chest;chest = new Item();}else
        if(legs.equals(t)){ret = legs;legs = new Item();}else
        if(hands.equals(t)){ret = hands;hands = new Item();}else
        if(feet.equals(t)){ret = feet;feet = new Item();}else
        if(neck.equals(t)){ret = neck;neck = new Item();}
        for(int i=0;i<rings.size();i++){
            if(rings.get(i).equals(t)){
                ret = rings.get(i);
                rings.remove(i);
                return ret;
            }
        }
        for(int i=0;i<quickSlots.size();i++){
            if(quickSlots.get(i).equals(t)){
                ret = quickSlots.get(i);
                quickSlots.remove(i);
                return ret;
            }
        }
        if(rightWeapon.equals(t)){
            ret = rightWeapon;
            rightWeapon = new Item();
            return ret;
        }else if(leftWeapon.equals(t)){
            ret = leftWeapon;
            leftWeapon = new Item();
            return ret;
        }
        return ret;
    }
    
    public String listEquipment(){
        String ret = "";
        if(head != null){ret = ret + head.name + ",";}
        if(chest != null){ret = ret + chest.name + ",";}
        if(legs != null){ret = ret + legs.name + ",";}
        if(hands != null){ret = ret + legs.name + ",";}
        if(feet != null){ret = ret + feet.name + ",";}
        if(neck != null){ret = ret + neck.name + ",";}
        for (Item ring : rings) {
            ret = ret + ring.name + ",";
        }
        for (Item quickSlot : this.quickSlots) {
            ret = ret + quickSlot.name + ",";
        }
        if(rightWeapon != null){ret = ret + rightWeapon.name + ",";}
        if(leftWeapon != null){ret = ret + leftWeapon.name ;}
        
        if(",".equals(ret.substring(ret.length() - 1)) ){
            ret = ret.substring(0,ret.length() - 1); 
        }
        System.out.println("equipment consists of: " + ret);
        return ret;
    }
}

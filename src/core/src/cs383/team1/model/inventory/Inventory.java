
package cs383.team1.model.inventory;
import java.util.ArrayList;
/**
 *
 * @author Tessa
 */

public class Inventory {
    public int maxSize = 30;
    public String owner;
    public ArrayList<Item> contents;
    public Equipment equiped;
    
    
    public Inventory(String o){
        owner = o;
        contents = new ArrayList();
        equiped = new Equipment();
        System.out.println("made Inventory for " + o);
    }
    
    public Item drop(Item n){
        Item ret = null;
        if(contents.contains(n)){
            contents.remove(n);
            ret = n;
        }
        return ret;
    }
    
    public boolean pickUp(Item n){
        if (contents.size() + equiped.size() < maxSize){
            contents.add(n);
            System.out.println("picked up " + n.name);
            return true;
        }else{
            System.out.println("Inventory full: could not pick up " + n.name);
            return false;
        }
    }
}


package cs383.team1.inventory;
import java.util.ArrayList;
/**
 *
 * @author Tessa
 */

public class Inventory {
    public int maxSize;
    public String owner;
    public ArrayList<Item> contents;
    public Equipment equiped;
    public Inventory(){
        contents = new ArrayList();
        System.out.println("made Inventory");
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

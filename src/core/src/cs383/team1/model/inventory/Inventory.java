package cs383.team1.model.inventory;

import com.badlogic.gdx.Gdx;
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
    
	public Inventory() {
		this("");
	}
    
    public Inventory(String o){
        owner = o;
        contents = new ArrayList();
        equiped = new Equipment();
        Gdx.app.debug("Inventory:Inventory", "made Inventory for " + o);
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
        if (       n != null
		&& contents.size() + equiped.size() < maxSize
		&& !n.name.equals("Unknown")){

            contents.add(n);
            Gdx.app.debug("Inventory:pickUp", "picked up " + n.name);
            return true;
        }else{
            Gdx.app.debug("Inventory:pickUp", "Inventory full: could not pick up " + n.name);
            return false;
        }
    }
    
    public boolean addItem(Item n)
    {
	    if (n != null && !n.name.equals("Unknown")){
		    contents.add(n);
		    System.out.println("picked up " + n.name);
		    return true;
	    } else return false;
    }
}

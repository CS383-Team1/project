/*
 Since we decided to go with the bair minimum of stats, I left off the 
plain craft object. You can just craft anything.
 */
package cs383.team1.inventory;
import java.util.ArrayList;
/**
 *
 * @author Tessa
 */
// Last Item in a list is the product
public class CraftedItemFactory {
    // Please add to these lists
    String [][] craftList = {
        {"paper", "paper airplane"},
        {"coffee","milk", "latte"},
        {"paperclip","string", "paperclip necklace"},
        {"paper","pen","report"}
    };
    String [] descList = {
        "poorly folded",
        "foamy",
        "Are you really this bored?",
        "'I needed this on my desk yesterday'"
    };
    String [] typeList = {
        "consumable,ranged",
        "consumable",
        "neck",
        "normal"
    };
    Double [][] statList = {
        {10.0,  0.5,  0.05,  1.5, 10.0,  0.0,  0.0},
        { 0.0,  0.0,  0.0,   0.0,  0.0,  5.0, 20.0},
        { 0.0,  0.0,  0.07,  0.0,  0.0,  0.0,  0.0},
        { 0.0,  0.1,  0.0,   0.0,  0.0,  0.0,  0.0}
    };
    
    public Item craft(ArrayList<Item> iList){
        Item ret = null;
        for(int i=0;i<craftList.length;i++){
            ArrayList<String> temp= new ArrayList();
            for (Item iList1 : iList) {
                temp.add(iList1.name);
            }
            for(int j=0;j<craftList[i].length-1;j++){
                if(temp.contains(craftList[i][j])){
                    temp.remove(craftList[i][j]);
                }else{
                    Item trash = new Item("trash",
                    "nothing useful", "trash");
                    return trash;
                }
            }
            if(temp.isEmpty()){
                Item newItem = new Item(craftList[i][craftList[i].length],
                descList[i],typeList[i], statList[i]);
                return newItem;
            }
        }
            
        return ret;    
    }
}

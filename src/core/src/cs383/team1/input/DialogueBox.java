/* DialogueBox.java
   
 */
package cs383.team1.input;

import java.util.ArrayList;

/**
 *
 * @author Casey
 */
public class DialogueBox{
    
    public ArrayList<String> messages = new ArrayList();;
    
     public DialogueBox(){
        messages.add("Test Text");
        messages.add("Testing line two");
        System.out.println(messages.get(0));
    }
    
    public void addMessage(String text){
        messages.add(text);
    }
    
    public void removeMessage(){
        messages.remove(0);
    }
    
    public boolean consumable(){
        return !messages.isEmpty();
    }
    
}

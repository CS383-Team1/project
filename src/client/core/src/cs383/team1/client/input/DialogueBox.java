package cs383.team1.client.input;

import java.util.ArrayList;

public class DialogueBox{
    
    public ArrayList<String> messages = new ArrayList();;
    
     public DialogueBox(){
        //System.out.println(messages.get(0));
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

/* DialogueBox.java
   Creates TextArea based on user input
   
 */
package cs383.team1.input;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import java.lang.String;
import java.util.ArrayList;

/**
 *
 * @author Casey
 */
public class DialogueBox {
    Skin skin = new Skin();
    TextArea textBox;
    public TextButton button;
    String text;
    public ArrayList<String> messages;
    Label textDisplay;

    public void DialogueBox(){
        textBox = new TextArea("", skin);   
        textBox = new TextArea("", skin);
        button = new TextButton("Send", skin);  
        button.addListener(new ClickListener());
        messages = new ArrayList();
        text = new String();
   }
    
    public void clicked(InputEvent event, float x, float y){
        
        if(textBox.getText().length() > 0){
            text = textBox.getText();
        }
        messages.add(text);
        textDisplay = new Label(messages.get(0), skin);
    }

    public void removeMessage(){
        messages.remove(text);
    }
    
    public boolean consumable(){
        return !messages.isEmpty();
    }
    
}

package cs383.team1.input.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import java.util.ArrayList;

/**
 *
 * @author Lance
 */
public class MessageBox {
        ArrayList <String> text;
        Label l;
        Table t;
        ScrollPane sp;
        Drawable bg;
        
        public MessageBox(Skin sk)
        {
                text = new ArrayList();

                t = new Table(sk);
                l = new Label("",sk);
                sp = new ScrollPane(t, sk);
                sp.setWidth(Gdx.graphics.getWidth());
                sp.setHeight(Gdx.graphics.getWidth()/6);
                sp.setX(0);
                sp.setY(0);
                t.setFillParent(true);
                t.left().bottom().add(l).width(sp.getWidth());
                t.pad(10);
                t.padBottom(15);
                l.setWrap(true);
                
                sp.setScrollingDisabled(true, false);
                sp.setOverscroll(false, false);
                sp.setSmoothScrolling(false);
        }
        
        public ScrollPane sp(){
                return sp;
        }
        
        //Add a message from the input
        public void addMessage(String s)
        {
                text.add(s);
                updateMessages();
        }
        
        //Update messages list
        public void updateMessages()
        {
                String s = "";
                if (text.size() > 100)
                        text.remove(0);
                for (int i = 0; i < text.size(); i++) {
                        s = s.concat(text.get(i)).concat("\n");
                }
                l.setText(s);
                sp.setScrollPercentY(1);
        }
}

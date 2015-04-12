package cs383.team1.input.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Lance
 */
public class MessageBox {
        ArrayList <String> text;
        Label l;
        Table t;
        Drawable bg;
        
        public MessageBox(Skin sk)
        {
                text = new ArrayList();
                bg = new NinePatchDrawable(getNinePatch("ui/test.9.png"));

                t = new Table(sk);
                t.setWidth(Gdx.graphics.getWidth());
                t.setHeight(Gdx.graphics.getHeight()/5);
                t.setX(0);
                t.setY(0);

                t.setBackground(bg);
                
                l = new Label("",sk);
                t.left().bottom().add(l);
                l.setWrap(true);
        }
        
        public Table t(){
                return t;
        }
        
        public void addMessage(String s)
        {
                int wrapLength = 83;
                //Handle text wrapping
                if (s.length() > wrapLength && s.matches("[^\\s]{83}.+"))
                        for (int i = 0; i < s.length(); i += Math.min(wrapLength, Math.abs(s.length() - i))) {
                                text.add(s.substring(i,i+Math.min(wrapLength, Math.abs(s.length() - i))));
                        }
                else if (s.length() > wrapLength)
                        text.addAll(Arrays.asList(s.split("\\s", wrapLength)));
                else
                        text.add(s);
                if (text.size() > 6) {
                        text.remove(0);
                }
                updateMessages();
        }
        
        public void updateMessages()
        {
                String s = "";
                for (int i = 0; i < text.size(); i++) {
                        s = s.concat(text.get(i)).concat("\n");
                }
                l.setText(s);
        }
        
	private NinePatch getNinePatch(String fname) {
	    
		// Get the image
		final Texture t = new Texture(Gdx.files.internal(fname));
	    
		// create a new texture region, otherwise black pixels will show up too, we are simply cropping the image
		// last 4 numbers respresent the length of how much can each corner can draw,
		// for example if your image is 50px and you set the numbers 50, your whole image will be drawn in each corner
		// so what number should be good?, well a little less than half would be nice
	    return new NinePatch( new TextureRegion(t, 1, 1 , t.getWidth() - 2, t.getWidth() - 2), 10, 10, 10, 10);
	}
}

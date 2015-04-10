package cs383.team1.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.Timer;

/**
 *
 * @author Lance
 */
public class NotificationBox {
        Window window;
        Skin skin;
        Stage stage;
        TextureRegion tR;
        
        //Constructor, should be used exclusively by the UIDisplay
        public NotificationBox(Stage st, Skin sk)
        {
                skin = sk;
                stage = st;
                window = new Window("Notice", skin);
                initNotice();
        }
        
        //Initializes, this is separate just for the sake of readibility
        private void initNotice()
        {
                window.setY(Gdx.graphics.getHeight());
                window.setVisible(false);
                
                window.addListener(new ClickListener() {
                        @Override
                        public void clicked(InputEvent event, float x, float y) {
                                System.out.println("PRESSEDBUTTON");
                                clearNotice();
                                super.clicked(event, x, y);
                        }
                });
        }

        //Alternative to specifying an image and message
        public void addNotice(Notification n)
        {
                addNotice (n.sprite, n.msg);
        }
        
        //Adds/updates the current notice.
        //Also makes the box visible again.
        public void addNotice(String iS, String s)
        {
                window.clearChildren();
                window.setVisible(true);
                Image i;
                Label l;
                tR = new TextureRegion(new Texture(Gdx.files.internal(iS)));

                window.left();
                window.top();

                i = new Image(tR);
                i.setScaling(Scaling.fit);
                window.add(i).width(tR.getRegionWidth()).height(tR.getRegionHeight());

                l = new Label(s, skin);
                window.add(l);

                window.setHeight( (float) Math.ceil( tR.getRegionHeight() * 1.5 ) );
                window.setWidth( (float) Math.ceil( ( tR.getRegionWidth() + l.getWidth() ) * 1.1 ) );
        }
        
        //Default. This should be removed once it's no longer need for testing
        public void addNotice()
        {
                window.clearChildren();
                window.setVisible(true);
                Image i;
                Label l;
                tR = new TextureRegion(new Texture(Gdx.files.internal("ui/quest.png")));

                window.left();
                window.top();

                i = new Image(tR);
                i.setScaling(Scaling.fit);
                window.add(i).width(tR.getRegionWidth()).height(tR.getRegionHeight());

                l = new Label("New Quest!", skin);
                window.add(l);
                
                window.setHeight((float) Math.ceil(tR.getRegionHeight()*1.5));
        }
        
        //Removes the notice and makes the window invisible.
        //This should happen a set amount of time after addNotice
        public void clearNotice()
        {
                window.clearChildren();
                window.setVisible(false);
                window.setY(Gdx.graphics.getHeight());
                window.setX(0);
        }
        
        public Window window() {
                return window;
        }
        
        public boolean isVisible() {
                return window.isVisible();
        }
}

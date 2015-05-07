package cs383.team1.input.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;

/**
 *
 * @author Lance
 */
public class MenuKey {
        private Window w;
        private Label l;
        private Skin skin;
        
        public MenuKey(Skin sk, String m, String k)
        {
                skin = sk;
                w = new Window(m, skin, "menu");
                l = new Label(k, skin, "big");
                
                w.add(l);
                w.setHeight(48);
                w.setWidth( max ( m.length(), k.length() ) * 16 );
                w.setX(0);
                w.setY(Gdx.graphics.getHeight());
                w.setMovable(false);
                w.setResizable(false);
                w.setVisible(true);
        }
        
        public int max(int x, int y) {
                if (x > y) return x; else return y;
        }
        
        public Window w() {
                return w;
        }
        
        public void setVisible(boolean vis) {
                w.setVisible(vis);
        }
}

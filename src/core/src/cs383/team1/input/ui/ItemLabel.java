package cs383.team1.input.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 *
 * @author Lance
 */
public class ItemLabel {
        String name;
        String icon;
        
        public ItemLabel(String s, String i) {
                name = s;
                icon = i;
        }
        
        public String name() {
                return name;
        }
        
        public String icon() {
                return icon;
        }
}

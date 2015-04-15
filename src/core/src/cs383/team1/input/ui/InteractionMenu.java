package cs383.team1.input.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import cs383.team1.model.overworld.LeftDesk;
import cs383.team1.model.overworld.Npc;
import cs383.team1.model.overworld.RightDesk;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lance
 */
public class InteractionMenu {
        Skin skin;
        Window interact;
        List listOptions;
        //Talk, Buy/Sell, Examine, Pickup, Interact
        boolean [] options = {false, false, true, false, false};
        
        public InteractionMenu(Skin sk) {
                interact = new Window("", sk);
        }
        
        public void setTitle(String s) {
                interact.setTitle(s);
        }
        
        private void setPerms(boolean tlk, boolean shp, boolean exm, boolean pck, boolean act) {
                listOptions.clear();
                if (tlk)
                        listOptions.add("Talk");
                if (shp)
                        listOptions.add("Shop");
                if (exm)
                        listOptions.add("Examine");
                if (pck)
                        listOptions.add("Pickup");
                if (act)
                        listOptions.add("Interact");
        }
        
        public void useMenu(Object o) {
                if ( o instanceof Npc )
                        System.out.println("NPC");
                else if ( o instanceof LeftDesk || o instanceof RightDesk )
                        System.out.println("Desk");
                else
                        System.out.println("NYI or Other");
        }
}

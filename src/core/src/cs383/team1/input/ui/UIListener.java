package cs383.team1.input.ui;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import cs383.team1.model.GameManager;
import cs383.team1.model.overworld.AreaManager;
import cs383.team1.model.overworld.Player;
import cs383.team1.model.overworld.Position;

/**
 *
 * @author Lance
 */
public class UIListener extends InputListener{
        Stage stage;
        MainMenu menu;
        MessageBox msg;
        Player player;
        InteractionMenu interact;
        
        final AreaManager areas = GameManager.instance.areas;

        
        public UIListener(
                Stage s,
                Player p,
                MainMenu m,
                MessageBox mb,
                InteractionMenu i)
        {
                stage = s;
                menu = m;
                msg = mb;
                player = p;
                interact = i;
        }
        
        @Override
        public boolean keyDown( InputEvent event, int keyCode ) {
                int x;
                int y;

                switch (keyCode) {
                case (Input.Keys.ENTER) :
                        if (
                                stage.getKeyboardFocus() == msg.input) {
                                if (!msg.input.getText().equals(""))
                                        msg.addMessage(msg.input.getText());
                                msg.input.setText("");
                                stage.setKeyboardFocus(null);
                                return true;
                        } else {
                                stage.setKeyboardFocus(msg.input);
                        }
                        break;
                case (Input.Keys.ESCAPE) :
                        if (menu.menu.isVisible())
                                menu.menu().setVisible(false);
                        else
                                menu.menu().setVisible(true);
                        return true;
                case (Input.Keys.SPACE) :
                        if (stage.getKeyboardFocus() != msg.input){
                                x = player.pos.x;
                                y = player.pos.y;
                                switch (player.facing) {
                                case (0) :
                                        useMenu(new Position(x, y + 1));
                                        break;
                                case (1) :
                                        useMenu(new Position(x + 1, y));
                                        break;
                                case (2) :
                                        useMenu(new Position(x, y - 1));
                                        break;
                                case (3) :
                                        useMenu(new Position(x - 1, y ));
                                        break;
                                } return true;
                        }
                        break;
                case (Input.Keys.DOWN) :
                        interact.getNext();
                        return (interact.interact.isVisible());
                case (Input.Keys.UP) :
                        interact.getPrevious();
                        return (interact.interact.isVisible());
                case (Input.Keys.LEFT)  :
                case (Input.Keys.RIGHT) :
                        return (interact.interact.isVisible());
                default:
                        return false;
                }
                return false;
        }
        
        private void useMenu(Position p)
        {
                interact.useMenu(areas.findEntity(p), player.facing);
        }
}

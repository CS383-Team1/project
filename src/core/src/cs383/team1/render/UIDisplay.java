package cs383.team1.render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import cs383.team1.input.ui.InteractionMenu;
import cs383.team1.input.ui.MainMenu;
import cs383.team1.input.ui.MessageBox;
import cs383.team1.model.GameManager;
import cs383.team1.model.overworld.Area;
import cs383.team1.model.overworld.AreaManager;
import cs383.team1.model.overworld.Player;
import cs383.team1.model.overworld.Position;

/**
 *
 * @author Lance
 */
public class UIDisplay extends Display{
        
        Stage stage;
        Skin skin;
        MessageBox msg;
        MainMenu menu;
        InteractionMenu interact;
        
        final Player player = GameManager.instance.areas.current.player;
        final AreaManager areas = GameManager.instance.areas;


        @Override
        public void render() {
                //Read messages sent to the GameManager to the chat
                if ( GameManager.instance.msg != null) {
                        msg.addMessage(GameManager.instance.msg);
                        GameManager.instance.msg = null;
                }
                stage.draw();
                stage.act();
                

        }
        
        public void dispose()
        {
                stage.dispose();
                skin.dispose();
        }


        public UIDisplay(Stage s) {

		skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
		stage = s;
                msg = new MessageBox(skin);
                menu = new MainMenu(skin);
                interact = new InteractionMenu(skin);

                stage.addActor(msg.msg());
                stage.addActor(menu.menu());
                stage.addActor(interact.interact());

                menu.menu().setVisible(false);
                interact.interact().setVisible(false);
                
                stage.addListener(new InputListener() {
                        @Override
                        public boolean keyDown( InputEvent event, int keyCode ) {
                                if (keyCode == Input.Keys.ENTER) {
                                        if (!msg.input.equals("") && stage.getKeyboardFocus() == msg.input) {
                                                msg.addMessage(msg.input.getText());
                                                msg.input.setText("");
                                                stage.setKeyboardFocus(null);
                                                return true;
                                        } else {
                                                stage.setKeyboardFocus(msg.input);
                                        }
                                        
                                } else if ( keyCode == Input.Keys.ESCAPE && menu.menu().isVisible() ) {
                                        menu.menu().setVisible(false);
                                        return true;
                                } else if ( keyCode == Input.Keys.ESCAPE ) {
                                        menu.menu().setVisible(true);
                                        return true;
                                        // && keyboard focus != messagebox
                                } else if (keyCode == Input.Keys.SPACE && stage.getKeyboardFocus() != msg.input) {
                                        if (player.facing == 0)
                                                useMenu(new Position(player.pos.x, player.pos.y + 1));
                                        else if (player.facing == 1)
                                                useMenu(new Position(player.pos.x + 1, player.pos.y));
                                        else if (player.facing == 2)
                                                useMenu(new Position(player.pos.x, player.pos.y - 1));
                                        else if (player.facing == 3)
                                                useMenu(new Position(player.pos.x - 1, player.pos.y));
                                        return true;
                                } else if (keyCode == Input.Keys.DOWN) {
                                        interact.getNext();
                                        return (interact.interact.isVisible());
                                } else if (keyCode == Input.Keys.UP) {
                                        interact.getPrevious();
                                        return (interact.interact.isVisible());
                                } else if(keyCode == Input.Keys.LEFT || keyCode == Input.Keys.RIGHT) {
                                        return (interact.interact.isVisible());
                                }
                                return false;
                        }
                });
        }
        
        private void useMenu(Position p)
        {
                interact.useMenu(areas.findEntity(p), player.facing);
        }
}

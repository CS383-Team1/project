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
import cs383.team1.model.overworld.Player;

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

        @Override
        public void render() {
                Player player = GameManager.instance.areas.current.player;
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
                final Player player = GameManager.instance.areas.current.player;

		skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
		stage = s;
                msg = new MessageBox(skin);
                menu = new MainMenu(skin);
                interact = new InteractionMenu(skin);

                stage.addActor(msg.msg());
                stage.addActor(menu.menu());

                menu.menu().setVisible(false);
                
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
                                        
                                }
                                else if ( keyCode == Input.Keys.ESCAPE && menu.menu().isVisible() ) {
                                        menu.menu().setVisible(false);
                                        return true;
                                }
                                else if ( keyCode == Input.Keys.ESCAPE ) {
                                        menu.menu().setVisible(true);
                                        return true;
                                }
                                return false;
                        }
                });
        }
}

package cs383.team1.render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import cs383.team1.input.ui.MainMenu;
import cs383.team1.input.ui.MessageBox;
import cs383.team1.input.ui.NotificationBox;
import cs383.team1.model.GameManager;
import cs383.team1.model.overworld.Player;

/**
 *
 * @author Lance
 */
public class UIDisplay extends Display{
        
        Stage stage;
        Skin skin;
        NotificationBox noticeBox;
        MessageBox msg;
        MainMenu menu;

        @Override
        public void render() {
                Player player = GameManager.instance.areas.current.player;
                if ( player.notice!=null && !noticeBox.isVisible() ) {
                        noticeBox.addNotice(player.notice);
                        player.notice = null;
                } else if (player.notice != null && noticeBox.isVisible()) {
                        noticeBox.clearNotice();
                        noticeBox.addNotice(player.notice);
                        player.notice = null;
                }
                if (GameManager.instance.msg != null) {
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
                noticeBox = new NotificationBox(skin);
                msg = new MessageBox(skin);
                menu = new MainMenu(skin);

                stage.addActor(noticeBox.window());
                stage.addActor(msg.sp());
                stage.addActor(menu.menu());
                

                menu.menu().setVisible(false);

                stage.addListener(new InputListener () {
                        @Override
                        public boolean keyDown( InputEvent event, int keyCode ) {
                                if ( keyCode == Input.Keys.ESCAPE && menu.menu().isVisible() ) {
                                        menu.menu().setVisible(false);
                                        return true;
                                }
                                if ( keyCode == Input.Keys.ESCAPE ) {
                                        menu.menu().setVisible(true);
                                        return true;
                                }
                                return false;
                        }
                });
        }
}

package cs383.team1.render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.SplitPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
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
        TextureRegion tR;
        SplitPane sp;

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
        }
        
        public void dispose()
        {
                stage.dispose();
                skin.dispose();
                tR.getTexture().dispose();
        }


        public UIDisplay(Stage s) {
		skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
		stage = s;
                noticeBox = new NotificationBox(skin);
                msg = new MessageBox(skin);
                sp = new SplitPane(new ScrollPane(new Table()), new Table(), true, skin);
                
                stage.addActor(noticeBox.window());
                stage.addActor(msg.t());
                stage.addActor(sp);
        }
}

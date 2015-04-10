package cs383.team1.render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.Scaling;
import cs383.team1.input.NotificationBox;
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
        TextureRegion tR;

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
                stage.draw();
        }
        
        public void dispose()
        {
                stage.dispose();
                skin.dispose();
                tR.getTexture().dispose();
        }


        public UIDisplay(Stage s) {
		skin = new Skin(Gdx.files.internal("uiskin.json"));
		stage = s;
                noticeBox = new NotificationBox(stage, skin);

                stage.addActor(noticeBox.window());
        }
        
}

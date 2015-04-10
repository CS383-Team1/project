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
import cs383.team1.model.GameManager;

/**
 *
 * @author Lance
 */
public class UIDisplay extends Display{
        
        Stage stage;
        Skin skin;
        Window notice;
        TextureRegion tR;

        @Override
        public void render() {
                if (!GameManager.instance.notice.msg().equals(""))
                        addNotice(GameManager.instance.notice.sprite(),
                                GameManager.instance.notice.msg());
                else
                        clearNotice();
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
                notice = new Window("Notice", skin);

                stage.addActor(notice);
                initNotice();
        }
        
        private void initNotice()
        {
                notice.setY(Gdx.graphics.getHeight());
                notice.setVisible(false);
        }
        
        public void addNotice(String iS, String s)
        {
                notice.clearChildren();
                notice.setVisible(true);
                Image i;
                Label l;
                tR = new TextureRegion(new Texture(Gdx.files.internal(iS)));

                notice.left();
                notice.top();

                i = new Image(tR);
                i.setScaling(Scaling.fit);
                notice.add(i).width(tR.getRegionWidth()).height(tR.getRegionHeight());

                l = new Label(s, skin);
                notice.add(l);
                
                notice.setHeight((float) Math.ceil(tR.getRegionHeight()*1.5));
        }
        
        public void addNotice()
        {
                notice.clearChildren();
                notice.setVisible(true);
                Image i;
                Label l;
                tR = new TextureRegion(new Texture(Gdx.files.internal("ui/quest.png")));

                notice.left();
                notice.top();

                i = new Image(tR);
                i.setScaling(Scaling.fit);
                notice.add(i).width(tR.getRegionWidth()).height(tR.getRegionHeight());

                l = new Label("New Quest!", skin);
                notice.add(l);
                
                notice.setHeight((float) Math.ceil(tR.getRegionHeight()*1.5));
        }
        
        public void clearNotice()
        {
                notice.clearChildren();
                notice.setVisible(false);
                notice.setY(Gdx.graphics.getHeight());
        }
}

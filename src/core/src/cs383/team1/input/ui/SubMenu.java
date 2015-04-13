package cs383.team1.input.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 *
 * @author Lance
 */
public class SubMenu {
        //Get an image from its filename
        public Image getImage( String s )
        {
                return (new Image(
                        new TextureRegion(
                                new Texture(
                                        Gdx.files.internal(
                                                "ui/" + s + ".png") ) ) ) );
        }
}

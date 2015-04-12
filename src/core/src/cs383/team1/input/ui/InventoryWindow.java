package cs383.team1.input.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane.ScrollPaneStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.SplitPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.utils.Scaling;

/**
 *
 * @author Lance
 */
public class InventoryWindow {
        Window w;
        SplitPane sp;
        Label equipLabel;
        Image equipHand;
        Image equipArmor;
        Table t;
        
        ScrollPane itemsPane;
        List<String> items;
        
        Skin skin;
        
        public InventoryWindow(Skin sk)
        {
                skin = sk;
                items = new List(skin);
                items.setItems("This", "That", "Potion", "Bagel", "Shoe", "Diamond", "DeLorean", "Laser Gun", "LaZer Gun", "Another thing", "???", "Lots of stuff", "Potions?", "Cube");
                itemsPane = new ScrollPane(items, skin);
                t = new Table();
                
                w = new Window("INVENTORY", sk);
                w.setHeight(220);
                w.setWidth(200);
                
                w.top();

                t.left().add( new Image( new TextureRegion( new Texture( Gdx.files.internal( "ui/weaponS.png" ) ) ) ) );
                t.left().add(new Label("weapon name", skin)).padLeft(10).row();
                t.left().add( new Image( new TextureRegion( new Texture( Gdx.files.internal( "ui/bar.png" ) ) ) ) ).fill(20, 1).padBottom(5).row();

                t.left().add( new Image( new TextureRegion( new Texture( Gdx.files.internal( "ui/armorS.png" ) ) ) ) );
                t.left().add(new Label("armor name", skin)).padLeft(10).row();
                t.left().add( new Image( new TextureRegion( new Texture( Gdx.files.internal( "ui/bar.png" ) ) ) ) ).fill(20, 1).padBottom(5).row();


                sp = new SplitPane(t, itemsPane, true, skin);
                sp.setSplitAmount((float) 0.3);
                sp.setMaxSplitAmount((float) 0.3);
                sp.setMinSplitAmount((float) 0.2999);
                w.add(sp);
        }
        
        public Window w(){
                return w;
        }
}

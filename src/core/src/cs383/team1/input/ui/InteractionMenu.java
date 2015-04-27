package cs383.team1.input.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import cs383.team1.inventory.Item;
import cs383.team1.model.GameManager;
import cs383.team1.model.overworld.Area;
import cs383.team1.model.overworld.CoWorker;
import cs383.team1.model.overworld.Entity;
import cs383.team1.model.overworld.Position;
import cs383.team1.model.overworld.Tile;

/**
 *
 * @author Lance
 */
public class InteractionMenu {
        Skin skin;
        public Window interact;
        Table t;
        public List<String> listOptions;
        public Object target;
	GameManager gm = GameManager.instance;
        
        public InteractionMenu(Skin sk) {
                listOptions = new List(sk);
                t = new Table();
                interact = new Window("", sk, "interact");
                interact.setWidth(100);
                interact.setHeight(50);
                
                t.add(listOptions).fill().expand();
                interact.add(t).fill().expand();
                listOptions.setFillParent(true);
  
                /*Should do this in the UIDisplay, it can then create new
                menus there much more easily by reading the target & interact
                option chosen in the list*/

                listOptions.addListener(new InputListener() {
                        @Override
                        public boolean keyDown( InputEvent event, int keyCode ){
				int si = listOptions.getSelectedIndex();
                                System.out.println("Test");
                                if (keyCode == Keys.DOWN) {
                                        listOptions.setSelectedIndex(si - 1);
                                        return true;
                                } else if (keyCode == Keys.UP) {
                                        listOptions.setSelectedIndex(si + 1);
                                        return true;
                                } else if (keyCode == Keys.ENTER) {
                                        interact.setVisible(false);
                                        return true;
                                }
                                return false;
                        }
                });
        }
        
        public Window interact() {
                return interact;
        }
        
        public List listOptions() {
                return listOptions;
        }
        
        public void setTitle(String s) {
                interact.setTitle(s);
        }

        public void useMenu(Object o, int facing) {
                target = o;
		Area a = gm.areas.current;
                if (interact.isVisible()) {
                        if (listOptions.getSelected().equals("Pickup")) {
                                a.player.inventory.pickUp((Item) target);
                                a.player.addMove((Item) target);
                                gm.msg.add("Picked up " + ((Item) target).name);
                                a.entities.remove(target);
                        }
                        System.out.println(listOptions.getSelected());
                        interact.setVisible(false);
                } else if (o instanceof Entity)
                        spawnMenu(facing);
        }

        public void spawnMenu(int facing) {
                int centerX = Gdx.graphics.getWidth() / 2;
                int centerY = Gdx.graphics.getHeight() / 2;

                switch (facing) {
                case 0:
                        interact.setX( centerX + Tile.WIDTH);
                        interact.setY( centerY + (float)( Tile.HEIGHT * 1.5 ) );
                        break;
                case 1:
                        interact.setX( centerX + ( Tile.WIDTH * 2 ) );
                        interact.setY( centerY + ( Tile.HEIGHT / 2));
                        break;
                case 2:
                        interact.setX( centerX + Tile.WIDTH );
                        interact.setY( centerY - ( Tile.HEIGHT / 2) );
                        break;
                case 3:
                        interact.setX( centerX-Tile.WIDTH-interact.getWidth());
                        interact.setY( centerY + ( Tile.HEIGHT / 2) );
                        break;
                default:
                        interact.setX( centerX );
                        interact.setY( centerY );
                        break;
                }
                listOptions.clear();

                if (target instanceof CoWorker) {
                        setTitle(((CoWorker) target).name());
                        listOptions.setItems(
				"Talk", "Buy/Sell", "Examine", "Cancel");
                } else if (target instanceof Item) {
                        setTitle(((Item) target).name);
                        listOptions.setItems(
				"Pickup", "Cancel");
                }
		int s = listOptions.getItems().size;
                interact.setHeight(s*((float)(Tile.HEIGHT * 0.4))+Tile.HEIGHT);
                interact.setVisible(true);
        }
        
        public void getNext()
        {
                int index = listOptions.getSelectedIndex();
                int size = listOptions.getItems().size;
                if (index != size - 1 && size > 0)
                        listOptions.setSelectedIndex(index+1);
        }
        
        public void getPrevious()
        {
                int index = listOptions.getSelectedIndex();
                int size = listOptions.getItems().size;
                if (index != 0 && size > 0)
                        listOptions.setSelectedIndex(index-1);
        }
}

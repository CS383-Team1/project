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
import cs383.team1.model.GameManager;
import cs383.team1.model.overworld.Npc;
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

//                //Add a listener for changing submenus
//		listOptions.addListener(new ClickListener(){
//                        @Override
//			public void clicked(InputEvent event, float x, float y)
//                        {
//                                changeMenu(menuList.getSelected());
//				super.clicked(event, x, y);
//			}
//		});
                listOptions.addListener(new InputListener() {
                        @Override
                        public boolean keyDown( InputEvent event, int keyCode ) {
                                System.out.println("Test");
                                if (keyCode == Keys.DOWN) {
                                        listOptions.setSelectedIndex(listOptions.getSelectedIndex() - 1);
                                        return true;
                                } else if (keyCode == Keys.UP) {
                                        listOptions.setSelectedIndex(listOptions.getSelectedIndex() + 1);
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
                if (interact.isVisible()) {
                        System.out.println(listOptions.getSelected());
                        interact.setVisible(false);
                } else if (o instanceof Npc)
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
                        interact.setX( centerX - Tile.WIDTH - interact.getWidth() );
                        interact.setY( centerY + ( Tile.HEIGHT / 2) );
                        break;
                default:
                        interact.setX( centerX );
                        interact.setY( centerY );
                        break;
                };
                listOptions.clear();

                if (target instanceof Npc) {
                        setTitle("NPC");
                        listOptions.setItems("Talk", "Buy/Sell", "Examine");
                }
                interact.setHeight(listOptions.getItems().size * 22);
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

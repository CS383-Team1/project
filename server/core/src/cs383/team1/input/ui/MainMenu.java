package cs383.team1.input.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.SplitPane;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 *
 * @author Lance
 */
public class MainMenu {
        Skin skin;

        Window menu;
        SplitPane menuSp;
        List<String> menuList;

        MenuCharacter menuC;
        MenuInventory menuI;
        MenuQuests    menuQ;

        public MainMenu(Skin sk)
        {
                skin = sk;
                menu = new Window("Menu", skin, "menu");
                menu.setFillParent(true);

                menuList = new List(skin, "big");
                menuList.setItems("CHARACTER", "INVENTORY", "QUESTS");

                //Add a listener for changing submenus
		menuList.addListener(new ClickListener(){
                        @Override
			public void clicked(InputEvent event, float x, float y)
                        {
                                changeMenu(menuList.getSelected());
				super.clicked(event, x, y);
			}
		});
                
                menuC = new MenuCharacter(skin);
                menuI = new MenuInventory(skin);
                menuQ = new MenuQuests(skin);

                menuSp = new SplitPane(menuList, menuC.charSp(), false, skin);
                menuSp.setSplitAmount   ((float) 0.2500);
                menuSp.setMaxSplitAmount((float) 0.2500);
                menuSp.setMinSplitAmount((float) 0.2499);

                menu.top().left().add(menuSp).fill().expand();
        }

        public Window menu() {
                return menu;
        }

        //Change the "submenu" to the one specified by 's'
        private void changeMenu( String s )
        {
                if (s.equals("INVENTORY"))
                        menuSp.setSecondWidget(menuI.invSp());
                else if (s.equals("CHARACTER"))
                        menuSp.setSecondWidget(menuC.charSp());
                else if (s.equals("QUESTS"))
                        menuSp.setSecondWidget(menuQ.questScroll());
                else
                        Gdx.app.error("Menu changeMenu", "NYI option: " + s);
        }
        
        public void updateStats(int hp, int atk, int def, int rep)
        {
                menuC.updateStats(hp, atk, def, rep);
                menuI.updateStats(atk, def);
        }
}
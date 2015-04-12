package cs383.team1.input.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.SplitPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Scaling;
import java.util.ArrayList;

/**
 *
 * @author Lance
 */
public class MainMenu {
        Skin skin;

        Window menu;
        SplitPane menuSp;
        List<String> menuList;

        SplitPane charSp;
        Table charNameTable;
        Table charStatTable;
        int statHp;
        int statAtk;
        int statDef;
        int statRep;
        
        SplitPane invSp;
        ScrollPane invScroll;
        Table invEquipTable;
        Table invItemsTable;
        ArrayList<ItemLabel> itemsList;
        
        public MainMenu(Skin sk)
        {
                skin = sk;
                menu = new Window("Menu", skin, "menu");
                menu.setFillParent(true);

                menuList = new List(skin, "big");
                menuList.setItems("CHARACTER", "INVENTORY", "QUESTS");

		menuList.addListener(new ClickListener(){
                        @Override
			public void clicked(InputEvent event, float x, float y) {
                                changeMenu(menuList.getSelected());
				super.clicked(event, x, y);
			}
		});
                
                charNameTable = new Table();
                charNameTable.add( new Label( "CHARACTER", skin, "big" ) ).row();
                charNameTable.add( getImage("bar") ).fill(20, 1).padBottom(5).row();
                charNameTable.add( new Label( "JOHN SMITH", skin, "big" ) ).row();
                
                charStatTable = new Table().padTop(20);
                charStatTable.add( getImage("statHpBig") ).padBottom(20);
                charStatTable.add( new Label( "HP:  ", skin, "big" ) ).padLeft(10).padBottom(20);
                charStatTable.add( new Label( Integer.toString(0), skin, "big" ) ).padBottom(20).row();
                charStatTable.add( getImage("statDefBig") ).padBottom(20);
                charStatTable.add( new Label( "DEF:  ", skin, "big" ) ).padLeft(10);
                charStatTable.add( new Label( Integer.toString(0), skin, "big" ) ).padBottom(20).row();
                charStatTable.add( getImage("statAtkBig") ).padBottom(20);
                charStatTable.add( new Label( "ATK:  ", skin, "big" ) ).padLeft(10).padBottom(20);
                charStatTable.add( new Label( Integer.toString(0), skin, "big" ) ).padBottom(20).row();
                charStatTable.add( getImage("statRepBig") ).padBottom(20);
                charStatTable.add( new Label( "REP:  ", skin, "big" ) ).padLeft(10).padBottom(20);
                charStatTable.add( new Label( Integer.toString(0), skin, "big" ) ).padBottom(20).row();

                charSp = new SplitPane(charNameTable, charStatTable, true, skin);
                charSp.setSplitAmount   ((float) 0.2500);
                charSp.setMaxSplitAmount((float) 0.2500);
                charSp.setMinSplitAmount((float) 0.2499);



                menuSp = new SplitPane(menuList, charSp, false, skin);
                menuSp.setSplitAmount   ((float) 0.2500);
                menuSp.setMaxSplitAmount((float) 0.2500);
                menuSp.setMinSplitAmount((float) 0.2499);
                
                invEquipTable = new Table();
                invEquipTable.add(new Label( "EQUIPPED", skin, "big" ) ).colspan(2).row();
                invEquipTable.left();
                invEquipTable.add( getImage("bar") ).fill(20, 1).padBottom(5).row();
                invEquipTable.add(getImage( "itemMeleeBig" ) ).padBottom(20);
                invEquipTable.add( new Label( "'DAT SWORD", skin, "big" ) ).padBottom(20).row();
                invEquipTable.add(getImage( "itemArmorBig" ) ).padBottom(20);
                invEquipTable.add( new Label( "'DIS ARMOR", skin, "big" ) ).padBottom(20).row();
                
                itemsList = new ArrayList();
                
                invItemsTable = new Table();
                getDemoItems();
                updateItems();
                invScroll = new ScrollPane(invItemsTable, skin);
//                invItemsTable.add(new Label( "PLACEHOLDER", skin, "big" ) );
                
                invSp = new SplitPane(invEquipTable, invScroll, true, skin);
                invSp.setSplitAmount   ((float) 0.3500);
                invSp.setMaxSplitAmount((float) 0.3500);
                invSp.setMinSplitAmount((float) 0.3499);
                
                menu.top().left().add(menuSp).fill().expand();
//                menuSp.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

//                itemsList = new ArrayList();

//                updateItems();

//                updateStats(0, 0, 0, 0);
//                equipTable = new Table();
//                statTable = new Table();
//                
//                w = new Window("CHARACTER", sk);
//                w.setHeight(200);
//                w.setWidth(200);
//                
//                inv = new Window("INVENTORY", sk);
//                inv.setHeight(200);
//                inv.setWidth(200);
//                
//                w.top();
//
//                //HitPoints Stat
//                statTable.left().add( new Image( new TextureRegion( new Texture( Gdx.files.internal( "ui/statHp.png" ) ) ) ) );
//                statTable.left().add( new Label( "HIT: ", skin )).padLeft(10);
//                statTable.left().add( statHp ).row();
//                //Attack Stat
//                statTable.left().add( new Image( new TextureRegion( new Texture( Gdx.files.internal( "ui/statAtk.png" ) ) ) ) );
//                statTable.left().add( new Label( "ATK: ", skin )).padLeft(10);
//                statTable.left().add( statAtk ).row();
//                //Defence Stat
//                statTable.left().add( new Image( new TextureRegion( new Texture( Gdx.files.internal( "ui/statDef.png" ) ) ) ) );
//                statTable.left().add( new Label( "DEF: ", skin )).padLeft(10);
//                statTable.left().add( statDef ).row();
//                //Reputation Stat
//                statTable.left().add( new Image( new TextureRegion( new Texture( Gdx.files.internal( "ui/statRep.png" ) ) ) ) );
//                statTable.left().add( new Label( "REP: ", skin )).padLeft(10);
//                statTable.left().add( statRep ).row();
//
//                //Equipped Row
//                equipTable.add( new Label( "", skin ) );
//                equipTable.add( new Label( "", skin ) );
//                equipTable.add( new Label ( "EQUIPPED", skin ) ).row();
//                equipTable.left().add( new Image( new TextureRegion( new Texture( Gdx.files.internal( "ui/bar.png" ) ) ) ) ).fill(20, 1).padBottom(5).row();
//                //Create Top Weapons Row
//                equipTable.left().add( itemMelee );
//                equipTable.left().add( new Label( "WEP", skin ) );
//                equipTable.left().add( new Label( "weapon name", skin)).padLeft(10).row();
//                equipTable.left().add( new Image( new TextureRegion( new Texture( Gdx.files.internal( "ui/bar.png" ) ) ) ) ).fill(20, 1).padBottom(5).row();
//                //Create Top Armor Row
//                equipTable.left().add( itemArmor );
//                equipTable.left().add( new Label( "ARM", skin ) );
//                equipTable.left().add( new Label("armor name", skin)).padLeft(10).row();
//                
//                //Add tables into splitpane
//                charSp = new SplitPane(statTable, equipTable, true, skin);
//                charSp.setSplitAmount((float) 0.5599);
//                charSp.setMaxSplitAmount((float) 0.5599);
//                charSp.setMinSplitAmount((float) 0.5598);
//
//                itemsPane.setOverscroll(false, false);
//                itemsPane.setFadeScrollBars(false);
//                itemsPane.setScrollingDisabled(true, false);
//
//                itemsPane.setWidth(9999);
//                inv.left().add(itemsTable);
//
//                w.add(charSp);
//                w.setFillParent(true);
        }
        
        public Window menu() {
                return menu;
        }
        
//        public Window inv() {
//                return inv;
//        }
        
        private Image getImage( String s ) {
                return (new Image(
                        new TextureRegion(
                                new Texture(
                                        Gdx.files.internal(
                                                "ui/" + s + ".png") ) ) ) );
        }
        
        private void changeMenu( String s ) {
                if (s.equals("INVENTORY"))
                        menuSp.setSecondWidget(invSp);
                else if (s.equals("CHARACTER"))
                        menuSp.setSecondWidget(charSp);
                else
                        Gdx.app.error("Menu changeMenu", "NYI menu option: " + s);
        }
        
        private void updateItems()
        {
                Image img;
                invItemsTable.clearChildren();
                invItemsTable.right();
                for ( int i = 0; i < itemsList.size(); i++ ) {
                        img = new Image( new TextureRegion( new Texture( Gdx.files.internal( "ui/item" + itemsList.get(i).icon() + "Big.png" ) ) ) );
                        img.setScaling(Scaling.none);
                        invItemsTable.right().add(img).expand();
                        invItemsTable.right().add( new Label( itemsList.get(i).name(), skin, "big" ) ).expand().fill().padLeft(20).padRight(50).row();
                }
        }
        
        private void getDemoItems()
        {
                itemsList.add( new ItemLabel("This", "ranged") );
                itemsList.add( new ItemLabel("That", "ranged") );
                itemsList.add( new ItemLabel("Laser Gun", "ranged") );
                itemsList.add( new ItemLabel("LaZer Gun", "ranged") );
                itemsList.add( new ItemLabel("LaZ0r G()n", "ranged") );
                itemsList.add( new ItemLabel("L@Z3R G()N", "ranged") );
                itemsList.add( new ItemLabel("Me Arrrrmor", "armor") );
                itemsList.add( new ItemLabel("'Dis Armor", "armor") );
                itemsList.add( new ItemLabel("'Dat Armor", "armor") );
                itemsList.add( new ItemLabel("Good Armor", "armor") );
                itemsList.add( new ItemLabel("Bad Armor", "armor") );
                itemsList.add( new ItemLabel("Ugly Armor", "armor") );
                itemsList.add( new ItemLabel("Punting Stapler", "melee") );
                itemsList.add( new ItemLabel("Sturdy Briefase", "melee") );
                itemsList.add( new ItemLabel("Potion", "consumable") );
                itemsList.add( new ItemLabel("Bagel", "consumable") );
                itemsList.add( new ItemLabel("Staple", "consumable") );
                itemsList.add( new ItemLabel("Pushpin", "consumable") );
                itemsList.add( new ItemLabel("Donut", "consumable") );
        }
        
        private void readItems(ArrayList<ItemLabel> aL)
        {
                itemsList = aL;
        }
        
//        private void updateStats(int hp, int atk, int def, int rep)
//        {
//                statHp  = new Label( Integer.toString(hp) , skin );
//                statAtk = new Label( Integer.toString(atk), skin );
//                statDef = new Label( Integer.toString(def), skin );
//                statRep = new Label( Integer.toString(rep), skin );
//        }
        
//        private Image stringToImage(String s)
//        {
//                /*Unfortunately, strings aren't supported in switch statements
//                as of this version.*/
//                if (s.equals("ranged"))
//                        return itemRanged;
//                if (s.equals("melee"))
//                        return itemMelee;
//                if (s.equals("armor"))
//                        return itemArmor;
//                if (s.equals("consumable"))
//                        return itemConsumable;
//                Gdx.app.error("InventoryWindow: Unsupported Item Type", s);
//                return itemConsumable;
//        }
}

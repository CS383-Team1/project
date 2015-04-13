package cs383.team1.input.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.SplitPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
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
        Label statHp;
        Label statAtk;
        Label statDef;
        Label statRep;
        
        SplitPane invSp;
        ScrollPane invScroll;
        Table invEquipTable;
        Table invItemsTable;
        ArrayList<ItemLabel> itemsList;
        Label equipAtk;
        Label equipDef;

        ScrollPane questScroll;
        Table questTable;
        ArrayList<QuestLabel> questList;

        Label equipWeapon;
        Label equipArmor;
        
        public MainMenu(Skin sk)
        {
                skin = sk;

                //Will need to remove/update once reading from player inventory
                equipWeapon = new Label( "Leather Briefcase", skin, "big" );
                equipArmor = new Label( "Snazzy Suit", skin, "big" );
                
                statHp = new Label("10", skin, "big");
                statAtk = new Label("5", skin, "big");
                statDef = new Label("4", skin, "big");
                statRep = new Label("5", skin, "big");
                
                equipAtk = new Label("5", skin, "big");
                equipDef = new Label("4", skin, "big");
                
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
                
                charNameTable = new Table();
                charNameTable.add( new Label( "CHARACTER", skin, "big" ) )
                        .row();
                charNameTable.add( getImage("bar") ).fill(20, 1).padBottom(5)
                        .row();
                charNameTable.add( new Label( "JOHN SMITH", skin, "big" ) )
                        .row();
                
                charStatTable = new Table()
                        .padTop(20);
                charStatTable.add( getImage("statHpBig") )
                        .padBottom(20);
                charStatTable.add( new Label( "HP:  ", skin, "big" ) )
                        .padLeft(10).padBottom(20);
                charStatTable.add( statHp ).padBottom(20)
                        .row();
                charStatTable.add( getImage("statDefBig") )
                        .padBottom(20);
                charStatTable.add( new Label( "DEF:  ", skin, "big" ) )
                        .padLeft(10);
                charStatTable.add( statDef ).padBottom(20)
                        .row();
                charStatTable.add( getImage("statAtkBig") )
                        .padBottom(20);
                charStatTable.add( new Label( "ATK:  ", skin, "big" ) )
                        .padLeft(10).padBottom(20);
                charStatTable.add( statAtk ).padBottom(20)
                        .row();
                charStatTable.add( getImage("statRepBig") )
                        .padBottom(20);
                charStatTable.add( new Label( "REP:  ", skin, "big" ) )
                        .padLeft(10).padBottom(20);
                charStatTable.add( statRep )
                        .padBottom(20).row();

                charSp = new SplitPane(charNameTable, charStatTable, true,skin);
                charSp.setSplitAmount   ((float) 0.2500);
                charSp.setMaxSplitAmount((float) 0.2500);
                charSp.setMinSplitAmount((float) 0.2499);



                menuSp = new SplitPane(menuList, charSp, false, skin);
                menuSp.setSplitAmount   ((float) 0.2500);
                menuSp.setMaxSplitAmount((float) 0.2500);
                menuSp.setMinSplitAmount((float) 0.2499);
                
                invEquipTable = new Table();
                invEquipTable.padRight(40).padLeft(40);
                invEquipTable.add(new Label( "EQUIPPED", skin, "big" ) )
                        .colspan(2).row();
                invEquipTable.left();
                invEquipTable.add( getImage("bar") ).fill(20, 1)
                        .padBottom(5).row();
                invEquipTable.add(getImage( "itemMeleeBig" ) )
                        .padBottom(10);
                invEquipTable.add( equipWeapon ).padLeft(10)
                        .padBottom(10).expand();
                invEquipTable.add( equipAtk ).row();
                invEquipTable.add(getImage( "itemArmorBig" ) )
                        .padBottom(10);
                invEquipTable.add( equipArmor ).padLeft(10)
                        .padBottom(10).expand();
                invEquipTable.add( equipDef )
                        .row();
                
                itemsList = new ArrayList();
                invItemsTable = new Table();
                getDemoItems();
                updateItems();
                invScroll = new ScrollPane( invItemsTable, skin );
                invScroll.setFadeScrollBars(false);
                invScroll.setOverscroll(false, false);
                
                invSp = new SplitPane(invEquipTable, invScroll, true, skin);
                invSp.setSplitAmount   ((float) 0.3500);
                invSp.setMaxSplitAmount((float) 0.3500);
                invSp.setMinSplitAmount((float) 0.3499);



                questList = new ArrayList();
                questTable = new Table();
                getDemoQuests();
                updateQuests();
                questScroll = new ScrollPane( questTable, skin );
                questScroll.setFadeScrollBars(false);
                questScroll.setOverscroll(false, false);

                menu.top().left().add(menuSp).fill().expand();
        }
        
        public Window menu() {
                return menu;
        }
        
        //Get an image from its filename
        private Image getImage( String s )
        {
                return (new Image(
                        new TextureRegion(
                                new Texture(
                                        Gdx.files.internal(
                                                "ui/" + s + ".png") ) ) ) );
        }
        
        //Change the "submenu" to the one specified by 's'
        private void changeMenu( String s )
        {
                if (s.equals("INVENTORY"))
                        menuSp.setSecondWidget(invSp);
                else if (s.equals("CHARACTER"))
                        menuSp.setSecondWidget(charSp);
                else if (s.equals("QUESTS"))
                        menuSp.setSecondWidget(questScroll);
                else
                        Gdx.app.error("Menu changeMenu", "NYI option: " + s);
        }
        
        //Update the inventory list based on the itemsList List
        /*This should happen after the list reads the player's inventory,
        which should happen once the menu is opened*/
        //Also adds "equip" button functionality for each item in the list
        private void updateItems()
        {
                Image img;
                TextButton equip;
                TextButton drop;
                int atk = getNum(statAtk);
                int def = getNum(statDef);
                invItemsTable.clearChildren();
                invItemsTable.right();
                for (int i = 0; i < itemsList.size(); i++) {
                        final String index = itemsList.get(i).name();
                        int stat = itemsList.get(i).stat();
                        String icon = itemsList.get(i).icon();
                        img = getImage("item" + icon + "Big");

                        //Add Image
                        img.setScaling(Scaling.none);
                        invItemsTable.right().add(img).expand();

                        //Add item name
                        invItemsTable.right().add(
                                new Label(
                                        itemsList.get(i).name(), skin, "big" ) )
                                .expand().fill().padLeft(20).padRight(50);

                        //Add stat identifier
                        if (icon.equals("ranged") || icon.equals("melee"))
                                if (stat > atk) {
                                        invItemsTable.add( cmpItem( atk, stat, true ) );
                                } else
                                        invItemsTable.add( cmpItem( atk, stat, false ) );
                        if ( icon.equals("armor") && stat > def)
                                invItemsTable.add( cmpItem( def, stat, true ) );
                        if ( icon.equals("armor") && stat <= def)
                                invItemsTable.add( cmpItem( atk, stat, false ) );
                        if (icon.equals("consumable"))
                                invItemsTable.add( new Label( "x " + Integer.toString(stat), skin, "big" ) );
                        invItemsTable.row();

                        //Add use/equip button
                        if (icon.equals("consumable"))
                                equip = new TextButton("Use", skin);
                        else
                                equip = new TextButton("Equip", skin);
                        equip.addListener( new ClickListener() {
                                @Override
                                public void clicked( InputEvent event, float x, float y ) {
                                        //TODO: Make this equip/use an item
                                        System.out.println (index + " equip");
                                }
                        });
                        
                        drop = new TextButton("Drop", skin);
                        drop.addListener( new ClickListener() {
                           @Override
                           public void clicked( InputEvent event, float x, float y ) {
                                   //TODO: Make this drop an item
                                   System.out.println(index + " drop");
                           }     
                        });
                        invItemsTable.add(equip);
                        invItemsTable.add(new Label("", skin, "big"));
                        invItemsTable.add(drop).row();
                }
        }
        
        private void updateQuests()
        {
                Image img;
                TextButton abandon;
                String details;
                String icon;
                int totCount;
                int curCount;

                questTable.clearChildren();
                questTable.right();
                for ( int i =0; i < questList.size(); i++ ) {
                        final String name = questList.get(i).name();
                        details = questList.get(i).details();
                        icon = questList.get(i).icon();
                        totCount = questList.get(i).totCount();
                        curCount = questList.get(i).curCount();

                        img = getImage("quest" + icon );
                        img.setScaling(Scaling.none);
                        questTable.add(img).padTop(10).expand();

                        questTable.add( new Label( name, skin, "big" ) ).expand().fill().padLeft(20).padRight(50).row();
                        questTable.add( new Label( details, skin ) ).colspan(2).row();
                        questTable.add( new Label( (Integer.toString(curCount) + "/" + Integer.toString(totCount) ), skin ) );

                        abandon = new TextButton( "abandon" , skin);
                        abandon.addListener( new ClickListener() {
                                @Override
                                public void clicked( InputEvent event, float x, float y ) {
                                        //TODO: Make this equip/use an item
                                        System.out.println (name + " abandon");
                                }
                        });
                        questTable.add( abandon ).right().row();

                        questTable.add( getImage( "bar" ) ).colspan(2).fill().expand().row();
                }
        }
        
        private int getNum( Label l )
        {
                return Integer.parseInt(l.getText().toString());
        }
        
        private Label cmpItem( int stat, int item, boolean positive )
        {
                if (positive)
                        return ( new Label(
                                ( "+" + Integer.toString( item - stat ) ),
                                skin,
                                "green" ) );
                return ( new Label(
                        ( "-" + Integer.toString( stat - item ) ),
                        skin,
                        "red" ) );
        }
        
        //Generate a list of items to demonstrate the inventory list
        private void getDemoItems()
        {
                itemsList.clear();
                itemsList.add( new ItemLabel("This", "ranged", 3) );
                itemsList.add( new ItemLabel("That", "ranged", 5) );
                itemsList.add( new ItemLabel("Laser Gun", "ranged", 7) );
                itemsList.add( new ItemLabel("LaZer Gun", "ranged", 9) );
                itemsList.add( new ItemLabel("LaZ0r G()n", "ranged", 11) );
                itemsList.add( new ItemLabel("L@Z3R G()N", "ranged", 9001) );
                itemsList.add( new ItemLabel("'Dis Armor", "armor", 3) );
                itemsList.add( new ItemLabel("'Dat Armor", "armor", 1) );
                itemsList.add( new ItemLabel("Good Armor", "armor", 9) );
                itemsList.add( new ItemLabel("Bad Armor", "armor", 1) );
                itemsList.add( new ItemLabel("Ugly Armor", "armor", 4) );
                itemsList.add( new ItemLabel("Punting Stapler", "melee", 3) );
                itemsList.add( new ItemLabel("Sturdy Briefase", "melee", 1) );
                itemsList.add( new ItemLabel("Potion", "consumable", 6) );
                itemsList.add( new ItemLabel("Bagel", "consumable", 4) );
                itemsList.add( new ItemLabel("Staple", "consumable", 2) );
                itemsList.add( new ItemLabel("Pushpin", "consumable", 6) );
                itemsList.add( new ItemLabel("Donut", "consumable", 1) );
                
        }
        
        
        private void getDemoQuests()
        {
                questList.clear();
                questList.add( new QuestLabel("Chair Murder", "Murder ALL the chairs!", "slay", 0, 20) );
                questList.add( new QuestLabel("Talk to HR", "Hustle yo' butt on over to the HR rep.", "talk", 0, 1) );
                questList.add( new QuestLabel("Go Outside", "Take a peek into the great outdoors.", "talk", 0, 1) );
                questList.add( new QuestLabel("Office Cleaner", "Show the intruders the way out.", "slay", 0, 7) );
                questList.add( new QuestLabel("VR Collector", "Get the V.R. headsets from R&D.", "fetch", 0, 10) );
                questList.add( new QuestLabel("Quest quest", "Quest for the quest in the quest.", "talk", 0, 1) );
                questList.add( new QuestLabel("Smack Down", "Lay the hurt down, brother.", "fetch", 0, 18) );
                questList.add( new QuestLabel("Office Slave", "Clean up the loose paper.", "fetch", 0, 14) );
        }
        
        //Copy the list of items from some source
        private void readItems(ArrayList<ItemLabel> aL)
        {
                itemsList = aL;
        }
        
        //Accessible option to feed the inventory menu the player's inventory
        public void getItems(ArrayList<ItemLabel> aL)
        {
                readItems(aL);
                updateItems();
        }
        
        //Update the player's stats to the new values
        private void updateStats(int hp, int atk, int def, int rep)
        {
                statHp.setText(Integer.toString(hp));
                statAtk.setText(Integer.toString(atk));
                statDef.setText(Integer.toString(def));
                statRep.setText(Integer.toString(rep));
                
                equipAtk.setText(Integer.toString(atk));
                equipDef.setText(Integer.toString(def));
        }
        
        //Public alias for updateStats
        public void getStats(int hp, int atk, int def, int rep)
        {
                updateStats(hp, atk, def, rep);
        }
}

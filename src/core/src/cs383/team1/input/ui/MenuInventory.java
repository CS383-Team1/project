package cs383.team1.input.ui;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.SplitPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Scaling;
import java.util.ArrayList;

/**
 *
 * @author Lance
 */
public class MenuInventory extends SubMenu {
        private SplitPane invSp;
        private ScrollPane invScroll;
        private Table invEquipTable;
        private Table invItemsTable;
        private ArrayList<ItemLabel> itemsList;
        private Label equipAtk;
        private Label equipDef;
        private Skin skin;
        
        private Label equipWeapon;
        private Label equipArmor;
        

        public MenuInventory(Skin sk)
        {
                skin = sk;
                
                equipWeapon = new Label( "Sturdy Briefcase", skin, "big" );
                equipArmor = new Label( "Snazzy Suit", skin, "big" );
                
                equipAtk = new Label( "5", skin, "big");
                equipDef = new Label( "4", skin, "big" );

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
        }
        
        public SplitPane invSp() {
                return invSp;
        }
        
        //Update the player's stats to the new values
        public void updateStats( int atk, int def )
        {
                equipAtk.setText(Integer.toString(atk));
                equipDef.setText(Integer.toString(def));
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

                int atk = 5;
                int def = 4;
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
                                   dropItem( index );
                                   updateItems();
                           }     
                        });
                        invItemsTable.add(equip);
                        invItemsTable.add(new Label("", skin, "big"));
                        invItemsTable.add(drop).row();
                        invItemsTable.add( getImage( "bar" ) ).colspan(3).fillX().expand().row();
                }
        }
        
        
        private int dropItem( String n )
        {
                for (int i = 0; i < itemsList.size(); i++) {
                        if (itemsList.get(i).name().equals(n)) {
                                itemsList.remove(i);
                                return 0;
                        }
                }
                return -1;
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
}

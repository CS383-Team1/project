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
import cs383.team1.inventory.Item;
import cs383.team1.model.GameManager;
import cs383.team1.model.overworld.Player;
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
//        private ArrayList<ItemLabel> itemsList;
        private ArrayList<Item> itemsList;
        private Label equipAtk;
        private Label equipDef;
        private Skin skin;
        
        private Label equipWeapon;
        private Label equipArmor;
	
	private Player player;
        

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
                invEquipTable.add(getImage( "itemMelee" ) )
                        .padBottom(10);
                invEquipTable.add( equipWeapon ).padLeft(10)
                        .padBottom(10).expand();
                invEquipTable.add( equipAtk ).row();
                invEquipTable.add(getImage( "itemArmor" ) )
                        .padBottom(10);
                invEquipTable.add( equipArmor ).padLeft(10)
                        .padBottom(10).expand();
                invEquipTable.add( equipDef )
                        .row();
                
                itemsList = new ArrayList();
                invItemsTable = new Table();
//                getDemoItems();
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
        public void updateItems()
        {
                Table imgTable;
                Table txtTable;
                Image img;
                TextButton equip;
                TextButton drop;

                int atk = 5;
                int def = 4;
                invItemsTable.clearChildren();
                invItemsTable.right();
                for (int i = 0; i < itemsList.size(); i++) {
                        final String name = itemsList.get(i).name;
			String desc = itemsList.get(i).description;
                        String type = itemsList.get(i).type;
			String hitChance = itemsList.get(i).hitChance.toString();
			String critChance = itemsList.get(i).critChance.toString();
			String critMult = itemsList.get(i).critMultiplier.toString();
			String range = itemsList.get(i).range.toString();
			String damage = itemsList.get(i).damage.toString();
//                        img = getIcon(type);


                        //Add Image
//                        img.setScaling(Scaling.none);
                        imgTable = new Table();
                        txtTable = new Table();
//                        imgTable.add(img).row();
                        invItemsTable.add(imgTable).right();

			
//                        img = getIcon(type);
//                        img.setScaling(Scaling.none);
//                        imgTable.add(img).row();
			
			
                        txtTable.padLeft(20);
                        
                        //Add item name
                        txtTable.left().add(
                                new Label( name, skin, "big" ) )
                                .colspan(5).expand().fillX();
                        
                        txtTable.row();
                        txtTable.left().add(new Label(desc, skin)).colspan(5).fillX().expand().row();
			addIcon("statHitChance", txtTable);
                        txtTable.left().add(new Label(hitChance, skin)).fillX().expand();
			addIcon("statCritChance", txtTable);
                        txtTable.left().add(new Label(critChance, skin)).fillX().expand();
			addIcon("statCritMult", txtTable);
                        txtTable.left().add(new Label(critMult, skin)).fillX().expand().row();
			addIcon("statRange", txtTable);
                        txtTable.left().add(new Label(range, skin)).fillX().expand();
			addIcon("statDamage", txtTable);
                        txtTable.left().add(new Label(damage, skin)).fillX().expand();

                        //Add stat identifier
                        invItemsTable.add(txtTable).expand().fillX();
//                        invItemsTable.row();

                        //Add use/equip button
                        if (
				type.equals("armor") ||
				type.equals("melee") ||
				type.equals("ranged"))
                                equip = new TextButton("Equip", skin, "exp");
                        else
                                equip = new TextButton("Use", skin, "exp");
                        equip.addListener( new ClickListener() {
                                @Override
                                public void clicked(
					InputEvent event, float x, float y ) {
                                        //TODO: Make this equip/use an item
                                        System.out.println (name + " equip");
                                }
                        });
                        
                        drop = new TextButton("Drop", skin, "exp");
                        drop.addListener( new ClickListener() {
                           @Override
                           public void clicked(
				   InputEvent event, float x, float y ) {
                                   //TODO: Make this drop an item
                                   System.out.println(name + " drop");
                                   dropItem( name );
                                   updateItems();
                           }     
                        });
			

//                        imgTable.add(drop).padBottom(5).fillX().expand().row();

                        img = getIcon(type);
                        img.setScaling(Scaling.none);
                        imgTable.add(img).row();

//                        imgTable.add(equip).padTop(5).fillX().expand();

			txtTable.add(drop).fillX().expand();
			txtTable.add(equip).fillX().expand();

//                        invItemsTable.add(equip);
//                        imgTable.add(equip);
                        invItemsTable.add(new Label("", skin, "big"));
//                        invItemsTable.add(drop).row();
			txtTable.add(new Label("", skin));
//                        txtTable.add(drop).fillX().expand().row();
			invItemsTable.row();
                        invItemsTable.add( getImage( "bar" ) ).colspan(3)
				.fillX().expand().row();
                }
        }
        
        
        private int dropItem( String n )
        {
                for (int i = 0; i < itemsList.size(); i++) {
                        if (itemsList.get(i).name.equals(n)) {
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

        //Copy the list of items from some source
        private void readItems(ArrayList<Item> aL)
        {
                itemsList = aL;
        }
        
        //Accessible option to feed the inventory menu the player's inventory
        public void getItems(ArrayList<Item> aL)
        {
                readItems(aL);
                updateItems();
        }
	
	public void getPlayerItems()
	{
		itemsList = GameManager.instance.areas.current.player.inventory.contents;
	}
	
	private Image getIcon(String s)
	{
		if (
			s.equals("melee") ||
			s.equals("ranged") ||
			s.equals("armor") ||
			s.equals("consumable"))
			return getImage("item" + s);
		else
			return getImage("itemUnknown");
	}
	
	public void sayItems()
	{
		for (int i = 0; i < itemsList.size(); i++)
			System.out.println(itemsList.get(i).name);
	}
	
	private void addIcon(String s, Table txtTable)
	{
		Image img;

		img = getImage(s);
		img.setScaling(Scaling.none);
		txtTable.left().add(img).fillX().expand();
	}
}

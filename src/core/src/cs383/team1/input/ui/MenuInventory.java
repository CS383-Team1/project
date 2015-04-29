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
import cs383.team1.inventory.Inventory;
import cs383.team1.inventory.Item;
import cs383.team1.model.GameManager;
import cs383.team1.model.overworld.Player;
import cs383.team1.model.overworld.Position;
import java.util.ArrayList;

/**
 *
 * @author Lance
 */
public class MenuInventory extends SubMenu {
        private SplitPane invSp;
        private ScrollPane invScroll;
        private Table invItemsTable;
        private ArrayList<Item> itemsList;
        private Skin skin;
	private Table invSize;
	private Label itemCount;
	private int maxCount;
  
	private Player player;
        
	GameManager gm;

        public MenuInventory(Skin sk)
        {
		gm = GameManager.instance;
                skin = sk;
		invSize = new Table();
		itemCount = new Label("(0/30)", skin, "big");
		invSize.add(itemCount);
                itemsList = new ArrayList();
                invItemsTable = new Table();
                updateItems();
                invScroll = new ScrollPane( invItemsTable, skin );
                invScroll.setFadeScrollBars(false);
                invScroll.setOverscroll(false, false);
                
                invSp = new SplitPane(invSize, invScroll, true, skin);
                invSp.setSplitAmount   ((float) 0.1000);
                invSp.setMaxSplitAmount((float) 0.1000);
                invSp.setMinSplitAmount((float) 0.0999);
        }
        
        public SplitPane invSp() {
                return invSp;
        }
        
        //Update the inventory list based on the itemsList List
        /*This should happen after the list reads the player's inventory,
        which should happen once the menu is opened*/
        //Also adds "equip" button functionality for each item in the list
        public final void updateItems()
        {
                Table imgTable;
                Table txtTable;
                Image img;
                TextButton equip;
                TextButton drop;

                invItemsTable.clearChildren();
                invItemsTable.right();
                for (int i = 0; i < itemsList.size(); i++) {
			//Get String values from item for labels
                        final String name = itemsList.get(i).name;
			String desc = itemsList.get(i).description;
                        String type = itemsList.get(i).type;
			String hitChance = itemsList.get(i).hitChance
				.toString();
			String critChance = itemsList.get(i).critChance
				.toString();
			String critMult = itemsList.get(i).critMultiplier
				.toString();
			String range = itemsList.get(i).range
				.toString();
			String damage = itemsList.get(i).damage
				.toString();

                        //Create nested tables
                        imgTable = new Table();
                        txtTable = new Table();
                        invItemsTable.add(imgTable).right();
                        invItemsTable.add(txtTable).expandX().fillX();
			
			//Add left-side padding to item descriptive table
                        txtTable.padLeft(20);
                        
                        //Add item name
                        txtTable.left().add(
                                new Label( name.replace("_", " "), skin, "big" ) )
                                .colspan(5).expandX().fillX();
                        
			//Setup Text Table
                        txtTable.row();
                        txtTable.left().add(new Label(desc.replace("_", " "), skin))
				.colspan(5).fillX().expandX().row();
			addIcon("statDamage", txtTable);
                        txtTable.left().add(new Label(damage, skin))
				.fillX().expandX();
			addIcon("statHitChance", txtTable);
                        txtTable.left().add(new Label(hitChance, skin))
				.fillX().expandX();
			addIcon("statRange", txtTable);
                        txtTable.left().add(new Label(range, skin))
				.fillX().expandX().row();
			addIcon("statCritChance", txtTable);
                        txtTable.left().add(new Label(critChance, skin))
				.fillX().expandX();
			addIcon("statCritMult", txtTable);
                        txtTable.left().add(new Label(critMult, skin))
				.fillX().expandX();



                        //Create use/equip button
                        if (
				type.equals("armor") ||
				type.equals("melee") ||
				type.equals("ranged"))
                                equip = new TextButton("Equip", skin, "exp");
                        else
                                equip = new TextButton("Use", skin, "exp");
			
			//Add use/equip listener
                        equip.addListener( new ClickListener() {
                                @Override
                                public void clicked(
					InputEvent event, float x, float y ) {
                                        //TODO: Make this equip/use an item
                                        System.out.println (name + " equip");
                                }
                        });
                        
			//Create drop button
                        drop = new TextButton("Drop", skin, "exp");
			
			//Add drop listener
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
			
			//Add the type icon
                        img = getIcon(type);
                        img.setScaling(Scaling.none);
                        imgTable.add(img).row();

			//Add the drop and equip buttons
			txtTable.add(equip).width(65).expandX();
			txtTable.add(drop).width(65).expandX();

			//Add the dividing bar to the bottom of the item label
			invItemsTable.row();
                        invItemsTable.add( getImage( "bar" ) ).colspan(3)
				.fillX().expandX().row();
                }
        }
        
        
        private int dropItem( String n )
        {
		Item item;
                for (int i = 0; i < itemsList.size(); i++) {
                        if (itemsList.get(i).name.equals(n)) {
				item = itemsList.get(i);
                                itemsList.remove(item);
				gm.areas.current.entities.add(new Item(
					item.name,
					item.description,
					item.type,
					item.damage,
					item.hitChance,
					item.critChance,
					item.critMultiplier,
					item.range,
					gm.areas.current.player.pos()
				));
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
		Inventory inv = GameManager.instance
			.areas.current.player.inventory;
		itemsList = inv.contents;
		
		maxCount = inv.maxSize;
		
		itemCount.setText("(" + Integer.toString(itemsList.size()) + "/"
			+ Integer.toString(maxCount) + ") Items");
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
		txtTable.left().add(img).fillX().expandX().width(24);
	}

}
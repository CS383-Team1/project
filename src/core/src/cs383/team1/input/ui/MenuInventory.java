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
  
	GameManager gm;
	
	Player p;

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

		//Prevents the SplitPane from being manually resized
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
		TextButton use = new TextButton("ERROR", skin);
		
		Table buttonT;
		
		Label descL;

		invItemsTable.clearChildren();
		invItemsTable.right();
		
		//If there are any items in the list, add a white bar to the top
		if (itemsList.size() > 0)
			invItemsTable.add( getImage( "bar" ) ).colspan(3)
				.fillX().expandX().row();
		
		for (int i = 0; i < itemsList.size(); i++) {
			//Get String values from item for labels
			final String name = itemsList.get(i).name;
			final Item itm = itemsList.get(i);
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
				new Label( name.replace("_", " "), skin, "big"))
				.colspan(5).expandX().fillX();
			
			//Setup Text Table
			txtTable.row();
			descL = new Label(desc.replace("_", " ") , skin);
			descL.setWrap(true);

			txtTable.left().add(descL)
				.colspan(6).fillX().expandX().row();

			if (type.contains("weapon")) {
				addIcon("statDamage", txtTable);
				txtTable.left().add(new Label(damage, skin))
					.left().fillX().expandX();
//				.left().fillX().expandX().width(24)
				addIcon("statHitChance", txtTable);
				txtTable.left().add(new Label(hitChance, skin))
					.left().fillX().expandX();
				addIcon("statRange", txtTable);
				txtTable.left().add(new Label(range, skin))
					.left().fillX().expandX().row();
				addIcon("statCritChance", txtTable);
				txtTable.left().add(new Label(critChance, skin))
					.left().fillX().expandX();
				addIcon("statCritMult", txtTable);
				txtTable.left().add(new Label(critMult, skin))
					.left().fillX().expandX().row();
			} else {
				txtTable.add(new Label("", skin)).padBottom(48).row();
			}
				

//			//Create use/equip button
			equip = new TextButton("Equip", skin, "exp");
			
			//Add use/equip listener
			if (!type.contains("two-handed"))
				equip.addListener( new ClickListener() {
					@Override
					public void clicked(
						InputEvent event, float x, float y ) {
						p.inventory.equiped.equip(itm);
					}
				});
			else
				equip.addListener( new ClickListener() {
					@Override
					public void clicked(
						InputEvent event, float x, float y ) {
						p.inventory.equiped.equipWeapon(itm, "right");
					}
				});
			
			if (!type.equals("consumable")) {
				use = new TextButton("Use", skin, "exp");
				use.addListener( new ClickListener() {
					@Override
					public void clicked(
						InputEvent event,
						float x,
						float y ) {
						System.out.println (name + " use");
					}
				});
			}
			
			//Create drop button
			drop = new TextButton("Drop", skin, "exp");
			
			//Add drop listener
			drop.addListener( new ClickListener() {
			   @Override
			   public void clicked(
				   InputEvent event, float x, float y ) {
				   dropItem( name );
				   updateItems();
			   }     
			});
			
			//Add the type icon
			if (!type.contains("weapon"))
				img = getIcon(type);
			else img = getWepIcon(Double.parseDouble(range));
			img.setScaling(Scaling.none);
			imgTable.add(img).row();

			//Add the button table
			buttonT = new Table();
			if (type.contains("consumable")) {
				buttonT.add(equip).left().width(65).fillX().expandX();
				buttonT.add(use).center().width(65).padLeft(15).expandX();
			} else if (!type.contains("weapon") || type.contains("two-handed"))
				buttonT.add(equip).left().width(65).fillX().expandX();
			else
				wepButtons(itm, buttonT);
			buttonT.add(drop).right().width(65).fillX().expandX();

			//Add the dividing bar to the bottom of the item label
			invItemsTable.row();
			invItemsTable.add(buttonT).fillX().expandX().colspan(3).row();
			invItemsTable.add( getImage( "bar" ) ).colspan(3)
				.fillX().expandX().row();
		}
	}
	
	//Drop an item from the inventory
	//Dropped items end up on the floor in the current room
	private int dropItem( String n )
	{
		Item item;
		//Find the item from the list
		for (int i = 0; i < itemsList.size(); i++) {

			if (itemsList.get(i).name.equals(n)) {
				item = itemsList.get(i);
				itemsList.remove(item);
				
				/*Allocate a copy of the item, but change the
				position to the player's current position*/
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
				
				//Update the item count label
				itemCount.setText(
					"(" + Integer.toString(itemsList.size())
					+ "/" + Integer.toString(maxCount)
					+ ") Items");
				return 0;
			}

		}
		return -1;
	}
	
	/*Currently deprecated, but still here for potential, Frankensteined
	re-implementation*/
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
	
	//Get the list of items and the inventory size from the player
	public void getPlayerItems()
	{
		p = gm.areas.current.player;
		Inventory inv = p.inventory;
		itemsList = inv.contents;
		
		maxCount = inv.maxSize;
		
		itemCount.setText("(" + Integer.toString(itemsList.size()) + "/"
			+ Integer.toString(maxCount) + ") Items");
	}
	
	//Get the item's overall type icon
	private Image getIcon(String s)
	{
		if (
			s.equals("consumable"))
			return getImage("item" + s);
		else if (
			s.contains("head") ||
			s.contains("neck") ||
			s.contains("chest") ||
			s.contains("hands") ||
			s.contains("legs") ||
			s.contains("feet") ||
			s.contains("rings"))
			return getImage("itemArmor");
		else
			return getImage("itemUnknown");
	}
	
	private Image getWepIcon(Double r)
	{
		if (r > 1)
			return getImage("itemRanged");
		else
			return getImage("itemMelee");
	}
	
	//Add the stat icon to the current item;  Used for line shortening
	private void addIcon(String s, Table txtTable)
	{
		Image img;

		img = getImage(s);
		img.setScaling(Scaling.none);
		txtTable.add(img).left().fillX().expandX().width(24);
	}

	private void wepButtons(final Item itm, Table t)
	{
		TextButton l;
		TextButton r;

		l = new TextButton("Equip L", skin, "exp");
		r = new TextButton("Equip R", skin, "exp");
		
		l.addListener(new ClickListener() {
			@Override
			public void clicked(
				InputEvent event, float x, float y ) {
				System.out.println(itm.name + "L hand");
				p.inventory.equiped.equipWeapon(itm, "left");
			}
		});
		
		r.addListener(new ClickListener() {
			@Override
			public void clicked(
				InputEvent event, float x, float y ) {
				System.out.println(itm.name + "R hand");
				p.inventory.equiped.equipWeapon(itm, "right");
			}
		});
		
		t.add(l).left().width(65).fillX().expandX().colspan(2);
		t.add(r).center().width(65).fillX().expandX().colspan(2);
	}
}
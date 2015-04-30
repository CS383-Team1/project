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
//TODO:
/*
- Make items display relative stats (+1, -2, etc.)
- Make relative stat display work for BOTH hands for weapons (if 1h)
- Make icons reflect 1-h or 2-h weapons
- Make equipping an item remove it from the inventory
- Make unequipping an item add it back to the inventory
- (NOT HERE) Add unequip buttons to the character/equip menu
- (NOT HERE) Add ring/consumable scrollpane in character/equip menu
- (NOT HERE) Make Interaction Menu just display the option for the faced entity
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
		invItemsTable.clearChildren();
		invItemsTable.right();
		
		//If there are any items in the list, add a white bar to the top
		if (itemsList.size() > 0)
			invItemsTable.add( getImage( "bar" ) ).colspan(3)
				.fillX().expandX().row();
		
		for (int i = 0; i < itemsList.size(); i++) {
			addItem(itemsList.get(i));
		}
	}
	/*
	Add an item and its button components to the end of the inventory table
	*/
	private void addItem(final Item itm)
	{
		//Get String values from item for labels
		final String name	= itm.name;
		String desc		= itm.description;
		String type		= itm.type;
		String hitChance	= itm.hitChance.toString();
		String critChance	= itm.critChance.toString();
		String critMult		= itm.critMultiplier.toString();
		String range		= itm.range.toString();
		String damage		= itm.damage.toString();

		//Create nested tables
		Table imgTable = new Table();
		Table txtTable = new Table();
		Table buttonT = new Table();
		Label descL = new Label(desc.replace("_", " ") , skin);

		TextButton equip;
		TextButton drop;
		TextButton use = new TextButton("ERROR", skin);
		Image img;

		invItemsTable.add(imgTable).right();
		invItemsTable.add(txtTable).expandX().fillX();
		txtTable.padLeft(20);

		//Add item name
		txtTable.left().add(
			new Label( name.replace("_", " "), skin, "big"))
			.colspan(5).expandX().fillX();
		txtTable.row();
		descL.setWrap(true);

		//Add description
		txtTable.left().add(descL).padBottom(5).colspan(6)
			.fillX().expandX().row();

		//Add stats
		if (type.contains("weapon")) {
			addStat(txtTable, "statDamage", damage);
			addStat(txtTable, "stathitChance", hitChance);
			addStat(txtTable, "statRange", range);
			addStat(txtTable, "statCritChance", critChance);
			addStat(txtTable, "statCritMult", critMult);
		} else {
			txtTable.add(new Label("", skin)).padBottom(48).row();
		}

		//Create use/equip button
		equip = new TextButton("Equip", skin, "exp");
		if (!type.contains("two-handed"))
			equip.addListener( new InvListener("equip", p, itm));
		else
			equip.addListener( new InvListener("equipR", p, itm));
		if (type.contains("consumable")) {
			use = new TextButton("Use", skin, "exp");
			use.addListener(new InvListener("use", p, itm));
		}
		
		//Create drop button
		drop = new TextButton("Drop", skin, "exp");
		drop.addListener( new ClickListener() {
		   @Override
		   public void clicked(
			   InputEvent event, float x, float y ) {
			   dropItem( name );
			   updateItems();
		   }     
		});

		//Add the type icon
		img = getIcon(itm);
		img.setScaling(Scaling.none);
		imgTable.add(img).row();

		//Add the button table
		if (type.contains("consumable")) {
			buttonT.add(equip).right().width(65).padLeft(5);
			buttonT.add(use).right().width(65).padLeft(5);
		} else if (
				!type.contains("weapon")||
				type.contains("two-handed")
			  )
			buttonT.add(equip).right().width(65).padLeft(5);
		else
			wepButtons(itm, buttonT);
		buttonT.add(drop).right().width(65).padLeft(5);

		//Add the dividing bar to the bottom of the item label
		invItemsTable.row();
		invItemsTable.add(buttonT).right().padTop(15)
			.expandX().colspan(2).row();
		invItemsTable.add(getImage("bar")).colspan(3)
			.fillX().expandX().row();
	}
	
	private void addStat(Table t, String s, String stat)
	{
		addIcon(s, t);
		t.left().add(new Label(stat, skin)).left().fillX().expandX();
	}
	
	/*
	Drop an item from the inventory
	Dropped items end up on the floor in the current room
	*/
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
	private Image getIcon(Item itm)
	{
		String s = itm.type;
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
		else if (s.contains("weapon")) {
			if (itm.range > 1 &&
			   !itm.type.contains("two-handed"))
				return getImage("itemRanged");
			else if (itm.range > 1)
				return getImage("itemRanged2h");
			else if (itm.range <= 1 &&
			        !itm.type.contains("two-handed"))
				return getImage("itemMelee");
			else if (itm.range <= 1)
				return getImage("itemMelee2h");
		}
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
		
		l.addListener(new InvListener("equipL", p, itm));
		r.addListener(new InvListener("equipR", p, itm));
		
		t.add(l).right().width(65);
		t.add(r).right().width(65).padLeft(5);
	}
}
package cs383.team1.input.ui;

import com.badlogic.gdx.graphics.Color;
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
import cs383.team1.inventory.Equipment;
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
		invItemsTable.clearChildren();
		invItemsTable.right();
		
		//If there are any items in the list, add a white bar to the top
		if (itemsList.size() > 0)
			invItemsTable.add( getImage( "bar" ) ).colspan(3)
				.fillX().expandX().row();
		
		for (int i = 0; i < itemsList.size(); i++)
			addItem(itemsList.get(i));
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

		//Create nested tables
		Table imgTable = new Table();
		Table txtTable = new Table();
		Table buttonT = new Table();
		Label descL = new Label(desc.replace("_", " ") , skin);

		TextButton equip;
		TextButton drop;
//		TextButton use = new TextButton("ERROR", skin);
		Image img;
		
		//Add the image and text tables to the main table
		invItemsTable.add(imgTable).right();
		invItemsTable.add(txtTable).expandX().fillX();
		txtTable.padLeft(20);

		//Add item name
		txtTable.left().add(
			new Label( name.replace("_", " "), skin, "big"))
			.colspan(10).expandX().fillX();
		txtTable.row();
		descL.setWrap(true);

		//Add description
		txtTable.left().add(descL).padBottom(5).colspan(10)
			.fillX().expandX().row();

		//Add stats
		if (type.contains("weapon")) {
			statWpn(txtTable, itm);
			txtTable.row();
		} else if (type.contains("consumable") || type.contains("ring")) {
			statCsm(txtTable, itm);
			txtTable.row();
		} else if (
			type.contains("head") ||
			type.contains("neck") ||
			type.contains("chest") ||
			type.contains("hands") ||
			type.contains("legs") ||
			type.contains("feet")){
			statArm(txtTable, itm);
			txtTable.row();
		} else
			txtTable.add(new Label("", skin)).padBottom(48).row();

		//Create use/equip button
		equip = new TextButton("Equip", skin, "exp");
		if (type.contains("two-handed"))
			equip.addListener(new InvListener(this,"equip" ,p,itm));
		else if (type.contains("weapon"))
			equip.addListener(new InvListener(this,"equipR",p,itm));
		else
			equip.addListener(new InvListener(this,"equip",p,itm));
//		if (type.contains("consumable")) {
//			use = new TextButton("Use", skin, "exp");
//			use.addListener(new InvListener(this, "use", p, itm));
//		}
		
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
//			buttonT.add(use).right().width(65).padLeft(5);
		} else if (!type.contains("weapon")||
			    type.contains("two-handed"))
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
	
	/*
	Adds the stat icons, numbers, and comparisons to the given table t
	*/
	private void statWpn(Table t, Item itm)
	{
		Table sub;
		
		Item e1 = p.inventory.equiped.leftWeapon;
		Item e2 = p.inventory.equiped.rightWeapon;
		
		sub = new Table();
		sub.add(new Label("L: ", skin)).bottom().row();
		sub.add(new Label("R: ", skin)).bottom().row();
		t.add(sub).bottom();
		
		t.add(statComp("statDamage",
			itm.damage,
			e1.damage,
			e2.damage))
			.fillX().expandX();
		t.add(statComp("statHitChance",
			itm.hitChance,
			e1.hitChance,
			e2.hitChance))
			.fillX().expandX();
		t.add(statComp("statRange",
			itm.range,
			e1.range,
			e2.range))
			.fillX().expandX();
		t.add(statComp("statCritChance",
			itm.critChance,
			e1.critChance,
			e2.critChance))
			.fillX().expandX();
		t.add(statComp(
			"statCritMult",
			itm.critMultiplier,
			e1.critMultiplier,
			e2.critMultiplier))
			.fillX().expandX();
	}
	
	private void statCsm(Table t, Item itm)
	{
		Table sub;
		
		sub = new Table();
		t.add(sub).bottom();
		
		t.add(statComp("statDamage", itm.damage))
			.fillX().expandX();
		t.add(statComp("statHitChance",itm.hitChance))
			.fillX().expandX();
		t.add(statComp("statRange",itm.range))
			.fillX().expandX();
		t.add(statComp("statCritChance",itm.critChance))
			.fillX().expandX();
		t.add(statComp("statCritMult",itm.critMultiplier))
			.fillX().expandX();
	}
	
	private void statArm(Table t, Item itm)
	{
		Item a;
		
		if (itm.type.equals("head"))
			a = p.inventory.equiped.head;
		else if (itm.type.equals("neck"))
			a = p.inventory.equiped.neck;
		else if (itm.type.equals("chest"))
			a = p.inventory.equiped.chest;
		else if (itm.type.equals("hands"))
			a = p.inventory.equiped.hands;
		else if (itm.type.equals("legs"))
			a = p.inventory.equiped.legs;
		else if (itm.type.equals("feet"))
			a = p.inventory.equiped.feet;
		else
			a = new Item();
		
		t.add(statComp("statHp", itm.hp, a.hp)).fillX().expandX();
		t.add(statComp("statMp", itm.mp, a.mp)).fillX().expandX();
		t.add(statComp("statMp", itm.ap, a.ap)).fillX().expandX();
	}
	
	/* 
	Adds the stat and its comparative stats to the given table t
	*/
	private Table statComp(String icn, Double i, double e1, double e2)
	{
		Table t;
		t = new Table();

		addIcon(icn, t);
		t.left().add(new Label(i.toString(), skin))
			.left().fillX().expandX().row();
		t.add(cmpStat(e1, i)).colspan(2).right().expandX();
		t.row();
		t.add(cmpStat(e2, i)).colspan(2).right().expandX();
		
		t.padRight(15);
		
		return t;
	}
	
	private Table statComp(String icn, Double i, double e1)
	{
		Table t;
		t = new Table();

		addIcon(icn, t);
		t.left().add(new Label(i.toString(), skin))
			.left().fillX().expandX().row();
		t.add(cmpStat(e1, i)).colspan(2).right().expandX();
		
		t.padRight(15);
		
		return t;
	}
	
	private Table statComp(String icn, Double i)
	{
		Table t;
		t = new Table();

		addIcon(icn, t);
		t.left().add(new Label(i.toString(), skin))
			.left().fillX().expandX().row();

		t.padRight(15);
		
		return t;
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
	
	private Label cmpStat(Double d1, Double d2) {
		Double diff = d2 - d1;
		String out = Double.toString(diff);
		Color c;
		if (diff < 0) {
			c = new Color(1, 0, 0, 1);
		} else if (diff > 0) {
			c = new Color(0, 1, 0, 1);
			out = "+".concat(out);
		} else {
			c = new Color(1, 1, 1, 1);
			out = "+".concat(out);
		}
		return new Label(out, skin, "default-font", c);
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
	
	//Add the stat icon to the current item;  Used for line shortening
	private void addIcon(String s, Table t)
	{
		Image img;

		img = getImage(s);
		img.setScaling(Scaling.none);
		t.add(img).left().fillX().expandX().width(24);
	}

	private void wepButtons(final Item itm, Table t)
	{
		TextButton l;
		TextButton r;

		l = new TextButton("Equip L", skin, "exp");
		r = new TextButton("Equip R", skin, "exp");
		
		l.addListener(new InvListener(this, "equipL", p, itm));
		r.addListener(new InvListener(this, "equipR", p, itm));
		
		t.add(l).right().width(65);
		t.add(r).right().width(65).padLeft(5);
	}
}
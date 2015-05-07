/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs383.team1.input.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Scaling;
import static cs383.team1.input.ui.SubMenu.getImage;
import cs383.team1.inventory.Equipment;
import cs383.team1.inventory.Item;
import cs383.team1.model.GameManager;
import cs383.team1.model.overworld.Player;

/**
 *
 * @author Lance
 */
public class SubEquip extends ItemsExtension {
	ScrollPane subSP;
	Skin skin;
	Table subT;
	Player p;
	Equipment e;
	MenuEquip menu;
	
	public SubEquip(Skin sk, MenuEquip m)
	{
		skin = sk;
		subT = new Table();
		menu = m;
		
		subSP = new ScrollPane(subT, skin);
		subSP.setFadeScrollBars(false);
		subSP.setScrollingDisabled(true, false);
		subSP.setOverscroll(false, false);
		
		updateItems();
	}
	
	public void updateItems()
	{
		subT.clearChildren();
		p = GameManager.instance.areas.current.player;
		e = p.inventory.equiped;
		
		if (e.leftWeapon!=null && !e.leftWeapon.name.equals("Unknown")){
			subT.add(addItem(e.leftWeapon, "left")).fillX().expandX().row();
		}
		if (e.rightWeapon != null&& !e.rightWeapon.name.equals("Unknown")){
			subT.add(addItem(e.rightWeapon, "right")).fillX().expandX().row();
		}
		for (int i = 0; i < e.rings.size(); i++)      {
			subT.add(addItem(e.rings.get(i))).fillX().expandX().row();
		}
		for (int i = 0; i < e.quickSlots.size(); i++) {
			subT.add(addItem(e.quickSlots.get(i))).fillX().expandX().row();
		}
	}
	
	public Table addItem(Item itm, String s)
	{
		Table t;
		TextButton b = new TextButton("UNEQUIP", skin, "exp");
		
		t = new Table();
		addTypeIcon(t, itm);
		
		t.add(new Label(itm.name, skin, "big")).left().expandX().row();
		if (s.equals("left"))
			b.addListener(new EquipListener("left", menu, itm, p));
		else if (s.equals("right"))
			b.addListener(new EquipListener("rite", menu, itm, p));
		else
			b.addListener(new EquipListener(menu, itm, p));
		t.add(b).left().expandX().colspan(2).row();
		t.add(getImage("bar")).expandX().fillX().colspan(2);
		
		return t;
	}
	
	public Table addItem(Item itm)
	{
		return addItem(itm, "NONE");
	}
	
	public void addTypeIcon(Table t, Item itm)
	{
		Image img;

		if (itm.type.contains("weapon")) {
			if (itm.range >= 1) {
				if (itm.type.contains("two-hand")) {
					img = getImage("itemMelee2h");
				} else
					img = getImage("itemMelee");
			} else
				if (itm.type.contains("two-hand")) {
					img = getImage("itemRanged2h");
				} else 
					img = getImage("itemRanged");
		} else if (itm.type.contains("consumable")) {
			img = getImage("itemConsumable");
		} else
			img = getImage("itemArmor");

		img.setScaling(Scaling.none);
		t.add(img).left().expandX();
	}
	
	public Table subT() {
		return subT;
	}
}

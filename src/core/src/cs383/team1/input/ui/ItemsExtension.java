/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs383.team1.input.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Scaling;
import static cs383.team1.input.ui.SubMenu.getImage;
import cs383.team1.model.inventory.Item;
import cs383.team1.model.GameManager;
import cs383.team1.model.overworld.CPlayer;
import cs383.team1.model.overworld.Player;

/**
 *
 * @author Lance
 */
public class ItemsExtension {
	private Skin skin;
	/*
	Adds the stat icons, numbers, and comparisons to the given table t
	*/
	private void statWpn(Table t, Item itm)
	{
		Player p = CPlayer.ownPlayer;
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
	
	private void statArm(Table t, Item itm)
	{
		Player p = CPlayer.ownPlayer;
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
	
	private void addIcon(String s, Table t)
	{
		Image img;

		img = getImage(s);
		img.setScaling(Scaling.none);
		t.add(img).left().fillX().expandX().width(24);
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
}

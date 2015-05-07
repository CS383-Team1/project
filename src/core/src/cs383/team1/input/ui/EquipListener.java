package cs383.team1.input.ui;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import cs383.team1.inventory.Equipment;
import cs383.team1.inventory.Item;
import cs383.team1.model.overworld.Player;

/**
 *
 * @author Lance
 */
public class EquipListener extends ClickListener{
	
	private Player p;
	private MenuEquip menu;
	private Item item;
	private String type;
	
	public EquipListener(MenuEquip m, Item itm, Player player) {
		p = player;
		menu = m;
		type = "else";
		if (itm == null) {
			itm = new Item();
			item = new Item();
		} else item = itm;
	}
	
	public EquipListener(String s, MenuEquip m, Item itm, Player player) {
		p = player;
		menu = m;
		type = s;
		if (itm == null) {
			itm = new Item();
			item = new Item();
		} else item = itm;
	}
	
	@Override
	public void clicked(InputEvent event, float x, float y) {
		if (!getEquipped(item))
			System.out.println("NOT SUPPORTED OR EMPTY");
	}
	
	private boolean getEquipped(Item itm){
		boolean gotten = false;
		Item ret;
		Equipment e = p.inventory.equiped;

		     if (type.contains("head"))
			{ret = e.unequip(e.head)       ; p.removeMove(itm);}
		else if (type.contains("neck"))
			{ret = e.unequip(e.neck)       ; p.removeMove(itm);}
		else if (type.contains("body"))
			{ret = e.unequip(e.chest)      ; p.removeMove(itm);}
		else if (type.contains("hand"))
			{ret = e.unequip(e.hands)      ; p.removeMove(itm);}
		else if (type.contains("legs"))
			{ret = e.unequip(e.legs)       ; p.removeMove(itm);}
		else if (type.contains("feet"))
			{ret = e.unequip(e.feet)       ; p.removeMove(itm);}
		else if (type.contains("left"))
			{ret = e.unequip(e.leftWeapon) ; p.removeMove(itm);}
		else if (type.contains("rite"))
			{ret = e.unequip(e.rightWeapon); p.removeMove(itm);}
		else {
			if (itm == null) {
				item = new Item();
			} else {
				gotten = p.inventory.addItem(
					p.inventory.equiped.unequip(item));
			}
			menu.update();
			return gotten;
		}
		p.inventory.addItem(ret);
		menu.update();
		return true;
	}
}

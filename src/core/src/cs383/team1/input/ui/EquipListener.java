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
	
	public EquipListener(MenuEquip m, Item itm, Player player) {
		p = player;
		menu = m;
		if (itm == null) {
			itm = new Item();
			item = new Item();
			System.out.println("THIS");
		}
	}
	
	@Override
	public void clicked(InputEvent event, float x, float y) {
		Equipment e = p.inventory.equiped;
		String type;
		if (!getEquipped(item))
			System.out.println("NOT SUPPORTED OR EMPTY");
	}
	
	private boolean getEquipped(Item itm){
		boolean gotten;
		Item ret;
		Equipment e = p.inventory.equiped;

		if (itm != null) {
			     if (itm.type.contains("head"))  ret = e.head;
			else if (itm.type.contains("neck"))  ret = e.neck;
			else if (itm.type.contains("chest")) ret = e.chest;
			else if (itm.type.contains("hands")) ret = e.hands;
			else if (itm.type.contains("legs"))  ret = e.legs;
			else if (itm.type.contains("feet"))  ret = e.feet;
			else ret = itm;
		} else ret = new Item();

		gotten = p.inventory.addItem(p.inventory.equiped.unequip(ret));
		menu.update();
		return gotten;
	}
}

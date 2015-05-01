package cs383.team1.input.ui;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import cs383.team1.inventory.Item;
import cs383.team1.model.overworld.Player;

/**
 *
 * @author Lance
 */
public class InvListener extends ClickListener{
	
	private Player p;
	private Item itm;
	private MenuInventory menu;
	private String type;
	
	public InvListener(MenuInventory m, String s, Player player, Item i) {
		p = player;
		itm = i;
		menu = m;
		if (s.contains("equip")||s.contains("use"))
			type = s;
		else
			type = "use";
	}
	
	@Override
	public void clicked(InputEvent event, float x, float y) {
		if (type.equals("equip")) {
			p.inventory.equiped.equip(itm);
			menu.updateItems();
		} else if (type.equals("equipL")) {
			p.inventory.equiped.equipWeapon(itm, "left");
			menu.updateItems();
		} else if (type.equals("equipR")) {
			p.inventory.equiped.equipWeapon(itm, "right");
			menu.updateItems();
		} else if (type.equals("use")) {
			System.out.println("USE ITEM");
			menu.updateItems();
		} else
			System.out.println("NOT SUPPORTED BUTTON");
	}
	
}

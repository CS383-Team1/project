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
		Equipment e = p.inventory.equiped;

		if (type.equals("equip")) {
			if (itm.type.contains("two-handed")) {
				p.removeMove(e.leftWeapon);
				p.removeMove(e.rightWeapon);
				p.addMove(itm);              }
			e.equip(itm);
			menu.updateItems();
		} else if (type.equals("equipL")) {
			p.removeMove(e.leftWeapon);
			p.addMove(itm);
			e.equipWeapon(itm, "left");
			menu.updateItems();
		} else if (type.equals("equipR")) {
			p.removeMove(e.rightWeapon);
			p.addMove(itm);
			e.equipWeapon(itm, "right");
			menu.updateItems();
		} else if (type.equals("use")) {
			System.out.println("USE ITEM");
			menu.updateItems();
		} else
			System.out.println("NOT SUPPORTED BUTTON");

	}
	
}

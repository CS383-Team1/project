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
	private String buttonType;
	
	public InvListener(MenuInventory m, String s, Player player, Item i) {
		p = player;
		itm = i;
		menu = m;
		if (s.contains("equip")||s.contains("use"))
			buttonType = s;
		else
			buttonType = "use";
	}
	
	@Override
	public void clicked(InputEvent event, float x, float y) {
		Equipment e = p.inventory.equiped;
		p.inventory.contents.remove(itm);

		if (buttonType.equals("equip")) {
			System.out.println("EQUIP");
			if (itm.type.contains("two")) {
				getItem(e.leftWeapon);
				getItem(e.rightWeapon);
				p.removeMove(e.leftWeapon);
				p.removeMove(e.rightWeapon);
                                e.equipWeapon(itm, "right");
				p.addMove(itm);              }
			else e.equip(itm);
			menu.updateItems();
		} else if (buttonType.equals("equipL")) {
			System.out.println("EQUIPL");
			if (e.rightWeapon.type.contains("two-handed")) {
				getItem(e.rightWeapon);
				e.unequip(e.rightWeapon);
			}
			getItem(e.leftWeapon);
			p.removeMove(e.leftWeapon);
			p.addMove(itm);
			e.equipWeapon(itm, "left");
			menu.updateItems();
		} else if (buttonType.equals("equipR")) {
			System.out.println("EQUIPR");
			getItem(e.rightWeapon);
			p.removeMove(e.rightWeapon);
			p.addMove(itm);
			e.equipWeapon(itm, "right");
			menu.updateItems();
		} else if (buttonType.equals("use")) {
			System.out.println("USE ITEM");
			menu.updateItems();
		} else
			System.out.println("NOT SUPPORTED BUTTON");

	}
	private void getItem(Item itm)
        {
                if (itm!= null && !itm.name.equals("Unknown")) {
                        p.inventory.contents.add(itm);
                }
        }
}

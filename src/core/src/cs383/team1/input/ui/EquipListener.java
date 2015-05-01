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
	private String type;
	
	public EquipListener(MenuEquip m, String s, Player player) {
		p = player;
		menu = m;
		if (	s.contains("head") || 
			s.contains("neck") || 
			s.contains("body") || 
			s.contains("hand") || 
			s.contains("legs") || 
			s.contains("feet")  )
			type = s;
		else
			type = "UNSUPPORTED";
	}
	
	@Override
	public void clicked(InputEvent event, float x, float y) {
		Equipment e = p.inventory.equiped;
		if (type.equals("head")) {
			p.inventory.equiped.unequip(e.head);
			menu.update();
		} else if (type.equals("neck")) {
			p.inventory.equiped.unequip(e.neck);
			menu.update();
		} else if (type.equals("body")) {
			p.inventory.equiped.unequip(e.chest);
			menu.update();
		} else if (type.equals("hand")) {
			p.inventory.equiped.unequip(e.hands);
			menu.update();
		} else if (type.equals("legs")) {
			p.inventory.equiped.unequip(e.legs);
			menu.update();
		} else if (type.equals("feet")) {
			p.inventory.equiped.unequip(e.feet);
			menu.update();
		} else
			System.out.println("NOT SUPPORTED BUTTON");
	}
	
}

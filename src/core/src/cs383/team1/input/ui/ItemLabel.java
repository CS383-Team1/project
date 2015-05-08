package cs383.team1.input.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 *
 * @author Lance
 */
public class ItemLabel {
	private String name;
	private String icon;
	private int stat;
	
	public ItemLabel(String s, String i, int st) {
		name = s;
		icon = i;
		stat = st;
	}
	
	public String name() {
		return name;
	}
	
	public String icon() {
		return icon;
	}
	
	public int stat() {
		return stat;
	}
}

package cs383.team1.input.ui;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import cs383.team1.model.GameManager;
import cs383.team1.model.overworld.AreaManager;
import cs383.team1.model.overworld.Player;
import cs383.team1.model.overworld.Position;

/**
 *
 * @author Lance
 */
public class UIListener extends InputListener{
	Stage stage;
	MainMenu menu;
	MessageBox msg;
	Player player;
	InteractionMenu interact;
	Interaction interaction;

	final AreaManager areas = GameManager.instance.areas;
	
	public UIListener(
		Stage s,
		Player p,
		MainMenu m,
		MessageBox mb,
		Interaction inter)
	{
		stage = s;
		menu = m;
		msg = mb;
		player = p;
//		interact = i;
		interaction = inter;
	}
	
	@Override
	public boolean keyDown( InputEvent event, int keyCode ) {
		int x;
		int y;

		switch (keyCode) {
		case (Input.Keys.ENTER) :
			if (
				stage.getKeyboardFocus() == msg.input) {
				if (!msg.input.getText().equals(""))
					msg.addMessage(msg.input.getText());
				msg.input.setText("");
				stage.setKeyboardFocus(null);
				return true;
			} else {
				stage.setKeyboardFocus(msg.input);
			}
			break;
		case (Input.Keys.ESCAPE) :
			interaction.setVisible(false);
			if (menu.menu.isVisible()) {
				menu.menu().setVisible(false);
			} else {
				menu.menu().setVisible(true);
				menu.menuI.getPlayerItems();
				menu.menuI.updateItems();
				menu.menuE.update();
			}
			return true;
		case (Input.Keys.SPACE) :
			if (stage.getKeyboardFocus() != msg.input){
				x = player.pos.x;
				y = player.pos.y;
				switch (player.facing) {
				case (0) :
					useMenu(new Position(x, y + 1));
					break;
				case (1) :
					useMenu(new Position(x + 1, y));
					break;
				case (2) :
					useMenu(new Position(x, y - 1));
					break;
				case (3) :
					useMenu(new Position(x - 1, y ));
					break;
				} return true;
			}
			break;
//		case (Input.Keys.DOWN) :
//			checkEntity(player.pos.x, player.pos.y-2);
//			break;
////			interact.getNext();
////			return (interact.interact.isVisible());
//		case (Input.Keys.UP) :
//			checkEntity(player.pos.x, player.pos.y+2);
//			break;
////			interact.getPrevious();
////			return (interact.interact.isVisible());
//		case (Input.Keys.LEFT)  :
//			checkEntity(player.pos.x-2, player.pos.y);
//			break;
//		case (Input.Keys.RIGHT) :
//			checkEntity(player.pos.x+2, player.pos.y);
//			break;
////			return (interact.interact.isVisible());
////			interaction
////			interaction.areas.findEntity(pos)
		default:
			return false;
		}
		return false;
	}
	
	private void useMenu(Position pos)
	{
//		interact.useMenu(areas.findEntity(pos), player.facing);
		interaction.interact();
	}
	
	public void checkEntity(int facing) {
		int x = player.pos.x;
		int y = player.pos.y;

		switch (facing){
		case 0: y+=1; break;
		case 1: x+=1; break;
		case 2: y-=1; break;
		case 3: x-=1; break;
		}

		Position pos = new Position(x,  y);
		Object o = areas.findEntity(pos);
		if ((o!=null))
			interaction.setupInteract(o, player.facing);
		else
			interaction.w.setVisible(false);
	}
}

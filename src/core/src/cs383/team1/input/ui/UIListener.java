package cs383.team1.input.ui;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import cs383.team1.Main;
import cs383.team1.model.GameManager;
import cs383.team1.model.overworld.AreaManager;
import cs383.team1.model.overworld.Player;
import cs383.team1.model.overworld.Position;

/**
 *
 * @author Lance
 */
public class UIListener extends InputListener{
	Sound click = Gdx.audio.newSound(Gdx.files.internal("sound/chatbeep.ogg"));
	Sound mainmenubeep = Gdx.audio.newSound(Gdx.files.internal("sound/mainmenubeep.ogg"));
	Stage stage;
	MainMenu menu;
	MessageBox msg;
	Player player;
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
				click.play();
				if (!msg.input.getText().equals(""))
					msg.addMessage(msg.input.getText());
				msg.input.setText("Press [ENTER] to chat");
				stage.setKeyboardFocus(null);
				return true;
			} else {
				msg.input.setText("");
				stage.setKeyboardFocus(msg.input);
			}
			break;
		case (Input.Keys.ESCAPE) :
			interaction.setVisible(false);
			if (menu.menu.isVisible()) {
				mainmenubeep.play();
				menu.menu().setVisible(false);
			} else {
				mainmenubeep.play();

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
		case (Input.Keys.DOWN) :
		case (Input.Keys.UP) :
		case (Input.Keys.LEFT)  :
		case (Input.Keys.RIGHT) :
			interaction.setVisible(false);
			return false;
		default:
			return false;
		}
		return false;
	}
	
	private void useMenu(Position pos)
	{
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
                else if (interaction.w.isVisible())
			interaction.w.setVisible(false);
	}
}

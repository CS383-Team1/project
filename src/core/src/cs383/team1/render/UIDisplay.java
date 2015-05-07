package cs383.team1.render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import cs383.team1.input.ui.CombatMenu;
import cs383.team1.input.ui.Interaction;
import cs383.team1.input.ui.MainMenu;
import cs383.team1.input.ui.MenuKey;
import cs383.team1.input.ui.MessageBox;
import cs383.team1.input.ui.UIListener;
import cs383.team1.model.GameManager;
import cs383.team1.model.overworld.AreaManager;
import cs383.team1.model.overworld.Player;

/**
 *
 * @author Lance
 */
public class UIDisplay extends Display{
	
	Stage stage;
	Skin skin;
	MessageBox msg;
	MainMenu menu;
	Interaction interaction;
	CombatMenu combat;
	UIListener uiListen;
        MenuKey mKey;
	
	Player player = GameManager.instance.areas.current.player;
	final AreaManager areas = GameManager.instance.areas;


	@Override
	public void render() {
		player = GameManager.instance.areas.current.player;
		//Read messages sent to the GameManager to the chat
		if ( GameManager.instance.msg != null &&
			GameManager.instance.msg.size()>0) {
			msg.addMessage(GameManager.instance.msg.get(0));
			GameManager.instance.msg.remove(0);
		}
		if ( !player.roaming && !combat.visible()) {
			combat.combat().setVisible(true);
			combat.updateAttacks();
		} else if ( player.roaming && combat.visible()) {
			combat.combat().setVisible(false);
		}
		if (player.zeroFloat())
			uiListen.checkEntity(player.facing);
		else if (interaction.w.isVisible())
			interaction.w.setVisible(false);
		stage.act();
		stage.draw();
		if (msg.toBottom())
			msg.scrollBottom();
		combat.incProgress();
	}
	
	public void dispose()
	{
		stage.dispose();
		skin.dispose();
	}

	public UIDisplay(Stage s) {
		skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
		stage = s;
		msg = new MessageBox(skin);
		menu = new MainMenu(skin);
		combat = new CombatMenu(skin);
		interaction = new Interaction(skin, player);
                mKey = new MenuKey(skin, "Inventory", "[ESC]");
		
		uiListen = new UIListener(stage, player, menu, msg, interaction, mKey);

		stage.addActor(msg.msg());
		stage.addActor(menu.menu());
		stage.addActor(combat.combat());
		stage.addActor(interaction.interaction());
                stage.addActor(mKey.w());

		menu.menu().setVisible(false);
		combat.combat().setVisible(false);
		
		stage.addListener(uiListen);
	}
}

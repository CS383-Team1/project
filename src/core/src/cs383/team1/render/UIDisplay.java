package cs383.team1.render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import cs383.team1.Main;
import cs383.team1.input.ui.CombatMenu;
import cs383.team1.input.ui.InteractionMenu;
import cs383.team1.input.ui.MainMenu;
import cs383.team1.input.ui.MessageBox;
import cs383.team1.input.ui.UIListener;
import cs383.team1.model.GameManager;
import cs383.team1.model.overworld.Area;
import cs383.team1.model.overworld.AreaManager;
import cs383.team1.model.overworld.Player;
import cs383.team1.model.overworld.Position;

/**
 *
 * @author Lance
 */
public class UIDisplay extends Display{
        
        Stage stage;
        Skin skin;
        MessageBox msg;
        MainMenu menu;
        InteractionMenu interact;
        CombatMenu combat;
        UIListener uiListen;
        
/*      Player player = GameManager.instance.areas.current.player;
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
                if ( !player.roaming && !combat.visible())
*/


        Player player = null;
        AreaManager areas = null;

        @Override
        public void render() {
		if(player == null) player = Player.ownPlayer;
		if(areas == null) areas = Main.gm.areas();
                //Read messages sent to the GameManager to the chat
                msg.addMessage(Main.gm.getMessage(0));
                Main.gm.removeMessage(0);

                if ( !player.roaming )
                        combat.combat().setVisible(true);
                else if ( player.roaming && combat.visible()) {
                        combat.combat().setVisible(false);
                        combat.changeMenu("MAIN");
		}
                stage.draw();
                stage.act();
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
                interact = new InteractionMenu(skin);
                combat = new CombatMenu(skin);
                
                uiListen = new UIListener(stage, player, menu, msg, interact);

                stage.addActor(msg.msg());
                stage.addActor(menu.menu());
                stage.addActor(interact.interact());
                stage.addActor(combat.combat());

                menu.menu().setVisible(false);
                interact.interact().setVisible(false);
                combat.combat().setVisible(false);
                
                stage.addListener(uiListen);
        }
}

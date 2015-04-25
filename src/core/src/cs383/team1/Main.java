package cs383.team1;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.rmi.ObjectSpace;
import com.esotericsoftware.minlog.Log;
import cs383.team1.input.InputManager;
import cs383.team1.model.GameManagerInterface;
import cs383.team1.net.Network;
import cs383.team1.render.DemoDisplay;
import cs383.team1.render.UIDisplay;
import java.io.IOException;

public class Main implements ApplicationListener, InputProcessor {
	public static InputManager inputManager;
	public static GameManagerInterface gm;
	public DemoDisplay screen;
        public UIDisplay ui;
        public Stage stage;
	public OrthographicCamera camera;
	public Client client;



	@Override
	public void create () {
		Gdx.app.setLogLevel(Application.LOG_INFO);
                
		Gdx.app.debug("Main:create", "Connecting to server");

		client = new Client();
		client.start();
		try {
			client.connect(10000, "127.0.0.1",
				Network.port);
		} catch (IOException ex) {
			Gdx.app.error("Main:create", "Failed to connect!");
			ex.printStackTrace();
			Gdx.app.exit();
		}
		Network.registerKryo(client);

		gm = ObjectSpace.getRemoteObject(client, Network.GM_ID,
			GameManagerInterface.class);	

		Gdx.app.debug("Main:create", "instantiating DemoDisplay");

                stage = new Stage(new ScreenViewport());
		inputManager = new InputManager();
                InputMultiplexer im = new InputMultiplexer(stage, this);
		screen = new DemoDisplay();
		ui = new UIDisplay(stage);
                
                Gdx.input.setInputProcessor(im);
	}


	@Override
	public void dispose() {
		Gdx.app.debug("Main:dispose", "disposing screen");
		screen.dispose();
	}

	@Override
	public void render() {
		if(inputManager.consumable()) {
			Gdx.app.debug("Main:render", "Updating GameManager");
			gm.update(inputManager);
		}
		screen.render();
                ui.render();
                if (gm.currentArea().player.zeroFloat() 
                        && gm.currentArea().player.roaming == true) {
                        inputManager.keys.add(gm.getKey());
                }
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public boolean keyDown (int key) {
		inputManager.keys.add(key);
                gm.setKey(key);
		return false;
	}

	@Override
	public boolean keyUp (int key) {
                if (key == gm.getKey()) {
                        gm.setKey(0);
		}
		return true;
	}

	@Override
	public boolean keyTyped (char ch) {
                
		return false;
	}

	@Override
	public boolean touchDown (int x, int y, int ptr, int btn) {
		return false;
	}

	@Override
	public boolean touchUp (int x, int y, int ptr, int btn) {
		return false;
	}

	@Override
	public boolean touchDragged (int x, int y, int ptr) {
		return false;
	}

	@Override
	public boolean mouseMoved (int x, int y) {
		return false;
	}

	@Override
	public boolean scrolled (int val) {
		return false;
	}
}

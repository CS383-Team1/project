package cs383.team1;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
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
import cs383.team1.Main;
import cs383.team1.MenuScreen;
import cs383.team1.input.InputManager;
import cs383.team1.model.GameManager;
import cs383.team1.model.GameManagerInterface;
import cs383.team1.model.overworld.Entity;
import cs383.team1.model.overworld.Player;
import cs383.team1.model.overworld.PlayerInterface;
import cs383.team1.net.Network;
import cs383.team1.net.GameClient;
import cs383.team1.render.DemoDisplay;
import cs383.team1.render.UIDisplay;
import java.io.IOException;
import java.rmi.server.RemoteObject;

public class JoinScreen implements Screen, InputProcessor, ApplicationListener{
	private boolean clientFail;
	private DemoDisplay screen;
	private GameClient client;
	private GameManagerInterface gm;
	private PlayerInterface p;
	public InputManager inputManager;
	private Main game;
	private OrthographicCamera camera;
	private Stage stage;
	private UIDisplay ui;
	
	GameManager g = GameManager.instance;

	public JoinScreen(Main m) {
		InputMultiplexer im;
         	Gdx.app.log("JoinScreen:JoinScreen", "Initializing");

		game = m;

		client = new GameClient();
		clientFail = !client.connect("127.0.0.1", Network.port);
		
		Gdx.app.log("JoinScreen:JoinScreen", "Connecting...");
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}

		gm = ObjectSpace.getRemoteObject(client.client, Network.GM_ID,
			GameManagerInterface.class);
		p = ObjectSpace.getRemoteObject(client.client, Network.P_ID,
			PlayerInterface.class);
		
		screen = new DemoDisplay();

		stage = new Stage(new ScreenViewport());

		ui = new UIDisplay(stage);

		inputManager = new InputManager();
		im = new InputMultiplexer(stage, this);
		Gdx.input.setInputProcessor(im);

        }

	@Override
	public void render(float delta) {
		update();
		draw();
	}

	void update() {
		Player player = Player.ownPlayer;

		if (clientFail) {
			Gdx.app.log("JoinScreen:update", "Client failure");
			game.setScreen(new MenuScreen(game));
		}
//		g.areas().current.player = gm.areas().current.player;

		g.update(inputManager);

//		if (g.currentArea().player.zeroFloat() 
//			&& g.currentArea().player.roaming == true) {
			inputManager.keys.add(g.getKey());
//		}

		if (g.keyPressed != 0) {
			p.setPos(player.pos.x,
				player.pos.y,
				player.floatPos.x,
				player.floatPos.y,
				player.facing);
		} else 	client.sendRequest();
	}

	void draw() {
		screen.render();
		ui.render();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public boolean keyDown (int key) {
//		inputManager.keys.add(key);
//		gm.setKey(key);
//		g.setKey(key);
		return false;
	}

	@Override
	public boolean keyUp (int key) {
//		if (key == gm.getKey()) {
//			gm.setKey(0);
//		}
		
		if (key == g.getKey()) {
			g.setKey(0);
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

	@Override
	public void hide() {
	}

	@Override
	public void show() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
		screen.dispose();
	}

    @Override
    public void create() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void render() {
        update();
	draw();
    }
    
}

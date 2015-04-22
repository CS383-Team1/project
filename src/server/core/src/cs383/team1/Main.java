package cs383.team1;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputMultiplexer;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.kryonet.rmi.ObjectSpace;
import com.esotericsoftware.minlog.Log;
import cs383.team1.input.InputManager;
import cs383.team1.model.GameManager;
import cs383.team1.net.Network;
import java.io.IOException;

public class Main implements ApplicationListener {
	public GameManager gm;
	public Server server;

	@Override
	public void create () {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		Log.set(Log.LEVEL_TRACE);

		Gdx.app.debug("Main:create", "instantiating GameManager");
		gm = GameManager.instance;

		Gdx.app.debug("Main:create", "Launching server");
		try {
			server = new Server();
			Network.registerKryo(server);
			server.bind(Network.port);
			server.start();
		} catch (IOException e) {
			Gdx.app.error("Main:create", "Unable to start server");
			Gdx.app.exit();
		}
	}


	@Override
	public void dispose() {
	}

	@Override
	public void render() {
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
}

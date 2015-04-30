package cs383.team1;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputMultiplexer;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.kryonet.rmi.ObjectSpace;
import com.esotericsoftware.minlog.Log;
import cs383.team1.Main;
import cs383.team1.input.InputManager;
import cs383.team1.model.GameManager;
import cs383.team1.net.Network;
import cs383.team1.net.GameServer;
import java.io.IOException;

public class HostScreen implements Screen {
	public Main game;
	public GameServer server;

	public HostScreen(Main m) {
		Log.set(Log.LEVEL_DEBUG);

		game = m;

		Gdx.app.debug("HostScreen:HostScreen", "Launching server");
		try {
			server = new GameServer();
		} catch (IOException e) {
			Gdx.app.error("HostScreen:HostScreen",
				"Unable to start server");
			Gdx.app.exit();
		}
	}

	@Override
	public void render(float delta) {
	}

	@Override
	public void resize(int width, int height) {
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
	}
}

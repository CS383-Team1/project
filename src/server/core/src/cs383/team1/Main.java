package cs383.team1;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputMultiplexer;
import cs383.team1.input.InputManager;
import cs383.team1.model.GameManager;

public class Main implements ApplicationListener {
	public GameManager gm;

	@Override
	public void create () {
		/* Gdx.app.setLogLevel(Application.LOG_INFO); */
		Gdx.app.setLogLevel(Application.LOG_DEBUG);

		Gdx.app.debug("Main:create", "instantiating GameManager");
		gm = GameManager.instance;
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

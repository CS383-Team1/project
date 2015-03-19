package cs383.team1;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import cs383.team1.model.GameManager;
import cs383.team1.DemoDisplay;

public class Main implements ApplicationListener, InputProcessor {
	public GameManager gm;
	public DemoDisplay screen;

	@Override
	public void create () {
		/* Gdx.app.setLogLevel(Application.LOG_INFO); */
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		Gdx.input.setInputProcessor(this);

		Gdx.app.debug("Main:create", "instantiating GameManager");
		gm = GameManager.instance;

		Gdx.app.debug("Main:create", "instantiating DemoDisplay");
		screen = new DemoDisplay();
	}

	@Override
	public void dispose() {
		Gdx.app.debug("Main:dispose", "disposing screen");
		screen.dispose();
	}

	@Override
	public void render() {
		screen.render();
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
		return false;
	}

	@Override
	public boolean keyUp (int key) {
		return false;
	}

	@Override
	public boolean keyTyped (char ch) {
		boolean res;

		res = false;

		if(ch == ' ') {
			gm.update();
			res = true;
		}

		return res;
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

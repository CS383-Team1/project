package cs383.team1.net;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;

public class Main implements ApplicationListener, InputProcessor {
	Client c;

	@Override
	public void create () {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		Gdx.input.setInputProcessor(this);

		try {
			(new Thread(new Server())).start();
			c = new Client("127.0.0.1", Server.PORT);
		} catch (Exception e) {
			Gdx.app.debug("Main:create",
				"I just don't know what went wrong...");
			Gdx.app.exit();
		}

	}

	@Override
	public void dispose() {
	}

	@Override
	public void render() {
		String str;

		str = c.update();

		if (!(str.equals(""))) {
			Gdx.app.log("incoming message", str);
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
		return false;
	}

	@Override
	public boolean keyUp (int key) {
		return false;
	}

	@Override
	public boolean keyTyped (char ch) {
		c.addChar(ch);

		return true;
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

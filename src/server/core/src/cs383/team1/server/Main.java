package cs383.team1.server;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import cs383.team1.server.model.GameManager;
import cs383.team1.server.net.Server;
import cs383.team1.server.util.MessageQueue;
import cs383.team1.server.util.Observer;

public class Main implements ApplicationListener, InputProcessor, Observer {
	private GameManager gm;
	private MessageQueue incoming;
	private MessageQueue outgoing;

	@Override
	public void create () {
		/* Gdx.app.setLogLevel(Application.LOG_INFO); */
		Gdx.app.setLogLevel(Application.LOG_DEBUG);

		Gdx.input.setInputProcessor(this);

		gm = GameManager.instance;
		incoming = new MessageQueue();
		outgoing = new MessageQueue();

		incoming.attach(this);

		Gdx.app.log("Main:create", "Launching server thread");
		(new Thread(new Server(incoming, outgoing))).start();
	}

	@Override
	public void dispose() {
	}

	@Override
	public void render() {
	}

	public void update(String str) {
		outgoing.addMessage(gm.update(str));
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

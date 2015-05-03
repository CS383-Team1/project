package cs383.team1;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import cs383.team1.model.GameManager;
import cs383.team1.model.GameManagerInterface;

public class Main extends Game {
	public static GameManager gm;

	public void create () {
		Gdx.app.setLogLevel(Application.LOG_INFO);
		gm = GameManager.instance;
		setScreen(new MenuScreen(this));
	}

	public void render () {
		GL20 gl = Gdx.gl;
		gl.glClearColor(0.0f, 1.0f, 0.0f, 0.0f);
		gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		super.render();
	}
}
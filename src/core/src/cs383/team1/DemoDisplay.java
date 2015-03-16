package cs383.team1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import cs383.team1.Display;
import cs383.team1.model.GameManager;
import cs383.team1.model.overworld.Entity;

public class DemoDisplay extends Display {
	private static final String FNAME = "img/demo.png";

	private SpriteBatch batch;
	private Sprite sprite;
	private Texture tex;

	public DemoDisplay() {
		Gdx.app.debug("DemoDisplay:DemoDisplay", "intantiating class");
		batch = new SpriteBatch();
		tex = new Texture(Gdx.files.internal(FNAME));
	}

	public void dispose() {
		Gdx.app.debug("DemoDisplay:dispose", "disposing texture");
		tex.dispose();
	}

	public void render() {
		Gdx.app.debug("DemoDisplay:render", "clearing display");
		Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		Gdx.app.debug("DemoDisplay:render", "setting blending");
		batch.enableBlending();
		batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

		batch.begin();

		for(Entity e : GameManager.instance.areas.current.entities) {
			Gdx.app.debug("DemoDisplay:render", "drawing entity");
			sprite = new Sprite(tex);
			sprite.setPosition(e.pos.x, e.pos.y);
			sprite.draw(batch);
		}

		batch.end();
	}
}

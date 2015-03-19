package cs383.team1;

import java.util.Map;
import java.util.HashMap;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import cs383.team1.Display;
import cs383.team1.model.GameManager;
import cs383.team1.model.overworld.Entity;
import cs383.team1.model.overworld.Tile;
import cs383.team1.model.overworld.DemoEntity;
import cs383.team1.model.overworld.DemoTile;

public class DemoDisplay extends Display {
	private static final String FNAME = "img/demo.png";

	private SpriteBatch batch;
	private Sprite sprite;
	private Map<String, Texture> texMap;

	private Texture getTexture(String str) {
		if(texMap.get(str) == null) {
			Gdx.app.log("DemoDisplay:getTexture", "loading texture " + str);
			texMap.put(str, new Texture(Gdx.files.internal(str)));
		}

		return texMap.get(str);
	}

	public DemoDisplay() {
		Gdx.app.debug("DemoDisplay:DemoDisplay", "intantiating class");
		batch = new SpriteBatch();
		texMap = new HashMap<String, Texture>();
	}

	public void dispose() {
		Gdx.app.debug("DemoDisplay:dispose", "disposing textures");
		for(Map.Entry item : texMap.entrySet()) {
			((Texture) item.getValue()).dispose();
		}
	}

	public void render() {
		Texture tex;

		Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.enableBlending();
		batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

		batch.begin();

		for(Entity e : GameManager.instance.areas.current.tiles) {
			switch(e.type()) {
				case DemoTile.TYPE:
					tex = getTexture("img/demo_tile.png");
					break;
				default:
					tex = null;
			}

			if(tex == null) {
				continue;
			}

			sprite = new Sprite(tex);
			sprite.setPosition(e.pos().x * Tile.WIDTH, e.pos().y * Tile.HEIGHT);
			sprite.draw(batch);
		}

		for(Entity e : GameManager.instance.areas.current.entities) {
			switch(e.type()) {
				case DemoEntity.TYPE:
					tex = getTexture("img/demo_entity.png");
					break;
				default:
					tex = null;
			}

			if(tex == null) {
				continue;
			}

			sprite = new Sprite(tex);
			sprite.setPosition(e.pos().x * Tile.WIDTH,
			  (e.pos().y * Tile.HEIGHT) + (int) (0.33 * Tile.HEIGHT));
			sprite.draw(batch);
		}

		batch.end();
	}
}

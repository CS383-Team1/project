package cs383.team1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import cs383.team1.ClientScreen;
import cs383.team1.ServerScreen;

public class MenuScreen extends ScreenAdapter {
	private BitmapFont font;
	private FreeTypeFontGenerator ttfGen;
	private Main game;
	private OrthographicCamera guiCam;
	private Rectangle hostBounds;
	private Rectangle joinBounds;
	private Rectangle exitBounds;
	private SpriteBatch batcher;

	public MenuScreen(Main m) {
		int width;
		int height;

		Gdx.app.log("MenuScreen:MenuScreen", "Initializing");

		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();

		ttfGen = new FreeTypeFontGenerator(Gdx.files.internal(
			"fonts/VCR_OSD_MONO_1.001.ttf"));

		font = ttfGen.generateFont(20);
		font.setColor(Color.WHITE);

		game = m;

		guiCam = new OrthographicCamera(width, height);
		guiCam.position.set(width / 2, height / 2, 0);

		hostBounds = new Rectangle(0, 100, width / 2, height - 100);
		joinBounds = new Rectangle(width / 2, 100,
			width / 2, height - 100);
		exitBounds = new Rectangle(0, 0, width, 100);

		batcher = new SpriteBatch();
	}

	@Override
	public void render(float delta) {
		update();
		draw();
	}

	public void update() {
		Vector3 clickPos;

		if (Gdx.input.justTouched()) {
			clickPos = new Vector3(Gdx.input.getX(),
				Gdx.input.getY(), 0);
			guiCam.unproject(clickPos);

			if (hostBounds.contains(clickPos.x, clickPos.y)) {
				// game.setScreen(new ServerScreen(game));
			}

			if (joinBounds.contains(clickPos.x, clickPos.y)) {
				// game.setScreen(new ClientScreen(game));
			}

			if (exitBounds.contains(clickPos.x, clickPos.y)) {
				Gdx.app.exit();
			}
		}
	}

	public void draw() {
		GL20 gl = Gdx.gl;
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		guiCam.update();
		batcher.setProjectionMatrix(guiCam.combined);

		batcher.disableBlending();
		batcher.begin();

		font.draw(batcher, "HOST", 100,
			Gdx.graphics.getHeight() - 100);
		font.draw(batcher, "JOIN", Gdx.graphics.getWidth() / 2,
			Gdx.graphics.getHeight() - 100);
		font.draw(batcher, "EXIT", Gdx.graphics.getWidth() / 2, 5);

		batcher.end();
	}
}

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
import cs383.team1.HostScreen;
import cs383.team1.JoinScreen;

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

		font = ttfGen.generateFont(30);
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
				//game.setScreen(new HostScreen(game));
			}

			if (joinBounds.contains(clickPos.x, clickPos.y)) {
				//game.setScreen(new JoinScreen(game));
			}

			if (exitBounds.contains(clickPos.x, clickPos.y)) {
				Gdx.app.exit();
			}
		}
	}

	public void draw() {
		GL20 gl = Gdx.gl;
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT |
			GL20.GL_DEPTH_BUFFER_BIT);
		Gdx.gl20.glEnable(GL20.GL_BLEND);
		Gdx.gl20.glBlendFunc(GL20.GL_SRC_ALPHA,
			GL20.GL_ONE_MINUS_SRC_ALPHA);
		Gdx.gl20.glEnable(GL20.GL_TEXTURE_2D);
		Gdx.gl20.glBlendEquation(GL20.GL_BLEND);

		guiCam.update();
		batcher.setProjectionMatrix(guiCam.combined);

		batcher.disableBlending();
		batcher.begin();

		font.draw(batcher, "HOST", Gdx.graphics.getWidth() / 4,
			Gdx.graphics.getHeight() - 100);
		font.draw(batcher, "JOIN", 3 * Gdx.graphics.getWidth() / 4,
			Gdx.graphics.getHeight() - 100);
		font.draw(batcher, "EXIT", Gdx.graphics.getWidth() / 2, 35);

		batcher.end();
	}
}

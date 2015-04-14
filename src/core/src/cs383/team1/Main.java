package cs383.team1;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import cs383.team1.input.InputManager;
import cs383.team1.model.GameManager;
import cs383.team1.render.DemoDisplay;
import cs383.team1.render.UIDisplay;

public class Main implements ApplicationListener, InputProcessor {
	public InputManager inputManager;
	public GameManager gm;
	public DemoDisplay screen;
        public UIDisplay ui;
        public Stage stage;

    OrthographicCamera camera;

	@Override
	public void create () {
		Gdx.app.setLogLevel(Application.LOG_INFO);
		/* Gdx.app.setLogLevel(Application.LOG_DEBUG); */
//		Gdx.input.setInputProcessor(this);
                
                stage = new Stage(new ScreenViewport());
                
                inputManager = new InputManager();


                InputMultiplexer im = new InputMultiplexer(stage, this);
                		
		Gdx.app.debug("Main:create", "instantiating GameManager");
		gm = GameManager.instance;

		Gdx.app.debug("Main:create", "instantiating DemoDisplay");
		screen = new DemoDisplay();
		ui = new UIDisplay(stage);

                
                Gdx.input.setInputProcessor(im);
	}

	@Override
	public void dispose() {
		Gdx.app.debug("Main:dispose", "disposing screen");
		screen.dispose();
	}

	@Override
	public void render() {
		if(inputManager.consumable()) {
			Gdx.app.debug("Main:render", "Updating GameManager");
			gm.update(inputManager);
		}
		screen.render();
                ui.render();
                if (GameManager.instance.areas.current.player.zeroFloat()) {
                        inputManager.keys.add(GameManager.instance.keyPressed);
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
		inputManager.keys.add(key);
                GameManager.instance.keyPressed = key;
		return false;
	}

	@Override
	public boolean keyUp (int key) {
                if (key == GameManager.instance.keyPressed)
                        GameManager.instance.keyPressed = 0;
		return true;
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

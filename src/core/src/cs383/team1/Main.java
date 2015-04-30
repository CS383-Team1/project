package cs383.team1;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import cs383.team1.input.InputManager;
import cs383.team1.model.GameManager;
import cs383.team1.model.GameManagerInterface;
import cs383.team1.render.DemoDisplay;
import cs383.team1.render.UIDisplay;

public class Main extends Game implements ApplicationListener, InputProcessor{
	public static GameManagerInterface gm;
        public Stage stage;
        public InputManager inputManager;
	//public GameManager gm;
	public DemoDisplay screen;
        public UIDisplay ui;

	public void create () {
		Gdx.app.setLogLevel(Application.LOG_INFO);
		gm = GameManager.instance;
		setScreen(new MenuScreen(this));
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

	public void render () {
		GL20 gl = Gdx.gl;
		gl.glClearColor(0.0f, 1.0f, 0.0f, 0.0f);
		gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
                
               if(inputManager.consumable()) {
			Gdx.app.debug("Main:render", "Updating GameManager");
			gm.update(inputManager);
		}
		screen.render();
                ui.render();
                if (GameManager.instance.areas.current.player.zeroFloat() 
                        && GameManager.instance.areas.current.player.roaming == true) {
                        inputManager.keys.add(GameManager.instance.keyPressed);
                }


		super.render();
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
	public void dispose() {
		Gdx.app.debug("Main:dispose", "disposing screen");
		screen.dispose();
	}

    @Override
    public boolean keyDown(int key) {
        inputManager.keys.add(key);
                GameManager.instance.keyPressed = key;
		return false;
    }

    @Override
    public boolean keyUp(int key) {
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

package cs383.team1.input;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import cs383.team1.model.GameManager;
import cs383.team1.render.DemoDisplay;
import java.util.ArrayList;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import cs383.team1.input.InputManager;
import cs383.team1.model.GameManager;
import cs383.team1.render.DemoDisplay;

public class InputManager implements ApplicationListener, InputProcessor{
 	public ArrayList<Integer> keys;
        public ArrayList<String> keycodes;
		
	public InputManager() {
		keys = new ArrayList<Integer>();
                keycodes = new ArrayList<String>();
                keycodes.add("move north\n");
                keycodes.add("move east\n");
                keycodes.add("move south\n");
                keycodes.add("move west\n");
                keycodes.add("interact");
                keycodes.add("interact");
                keycodes.add("attack 1");
                keycodes.add("attack 2");
                keycodes.add("attack 3");
                keycodes.add("attack 4");
                
                
                keycodes.add("unknown\n");
                
	}
	
	public boolean consumable() {
		return !keys.isEmpty();
	}
	public InputManager inputManager;
	public GameManager gm;
	public DemoDisplay screen;

	@Override
	public void create () {
                //System.out.println("HELP ME!!!");
		Gdx.app.setLogLevel(Application.LOG_INFO);
		/* Gdx.app.setLogLevel(Application.LOG_DEBUG); */
		Gdx.input.setInputProcessor(this);

		inputManager = new InputManager();
		
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
		if(inputManager.consumable()) {
			Gdx.app.debug("Main:render", "Updating GameManager");
			gm.update(inputManager);
		}

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
		inputManager.keys.add(key);
            switch(key){ 
                case Input.Keys.A:
                    System.out.print("A KEY ");
                    break;
                case Input.Keys.W:
                    System.out.print("W KEY ");
                    break;
                case Input.Keys.S:
                    System.out.print("S KEY ");
                    break;
                case Input.Keys.D:
                    System.out.print("D KEY ");
                    break;
                case Input.Keys.UP:
                    System.out.print("UP KEY ");
                    break;
                case Input.Keys.DOWN:
                    System.out.print("DOWN KEY ");
                    break;
                case Input.Keys.LEFT:
                    System.out.print("LEFT KEY ");
                    break;
                case Input.Keys.RIGHT:
                    System.out.print("RIGHT KEY ");
                    break;

            }
            System.out.println("DOWN");
		return true;
	}

	@Override
	public boolean keyUp (int key) {
            switch(key){ 
                case Input.Keys.A:
                    System.out.print("A KEY ");
                    break;
                case Input.Keys.W:
                    System.out.print("W KEY ");
                    break;
                case Input.Keys.S:
                    System.out.print("S KEY ");
                    break;
                case Input.Keys.D:
                    System.out.print("D KEY ");
                    break;
                case Input.Keys.UP:
                    System.out.print("UP KEY ");
                    break;
                case Input.Keys.DOWN:
                    System.out.print("DOWN KEY ");
                    break;
                case Input.Keys.LEFT:
                    System.out.print("LEFT KEY ");
                    break;
                case Input.Keys.RIGHT:
                    System.out.print("RIGHT KEY ");
                    break;

            }
            System.out.println("UP");
		return true;
	}

	@Override
	public boolean keyTyped (char ch) {
            System.out.println("Typed " + ch);
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
            System.out.println("HELP MOUSEMOVED");
		return false;
	}

	@Override
	public boolean scrolled (int val) {
		return false;
	}
}

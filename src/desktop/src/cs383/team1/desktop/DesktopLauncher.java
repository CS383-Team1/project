package cs383.team1.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import cs383.team1.input.InputManager;

public class DesktopLauncher {
	public static void main (String[] arg) {
            System.out.println("HERP DERP");
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new InputManager(), config);
	}
}

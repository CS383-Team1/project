package cs383.team1.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import cs383.team1.Main;
import cs383.team1.SpaceOfficeAdventure;


public class DesktopLauncher {
	public static void main (String[] arg) {
            LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Space Office Adventure";
        config.addIcon("MainMenu/icon32.png", Files.FileType.Internal);
        //config.fullscreen=true;
        config.resizable=false;

        config.addIcon("MainMenu/icon256.png", Files.FileType.Internal);
        config.addIcon("MainMenu/icon128.png", Files.FileType.Internal);
        config.addIcon("MainMenu/icon64.png", Files.FileType.Internal);
        config.addIcon("MainMenu/icon16.png", Files.FileType.Internal);




        new LwjglApplication(new SpaceOfficeAdventure(), config);
	}
}

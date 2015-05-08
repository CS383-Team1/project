package cs383.team1;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.concurrent.TimeUnit;

public class Splash{

	static Texture splashimg = new Texture(Gdx.files.internal("MainMenu/Splash.jpg"));
	static Texture space = new Texture(Gdx.files.internal("MainMenu/pressSpace.png"));
        static Texture pc1 = new Texture(Gdx.files.internal("MainMenu/PlayerControl1.png"));
	static Texture pc2 = new Texture(Gdx.files.internal("MainMenu/PlayerControl2.png"));
	static Texture pc3 = new Texture(Gdx.files.internal("MainMenu/PlayerControl3.png"));
	static Texture pc4 = new Texture(Gdx.files.internal("MainMenu/PlayerControl4.png"));
        static Texture BG = new Texture(Gdx.files.internal("MainMenu/menuBG.png"));

	static long time=0;
	static boolean toggle = false;


	Splash(){
		create();
	}

	public void create () {


	}

	static void displaySplash(){

		SpaceOfficeAdventure.batch.draw(splashimg, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());


		if(System.nanoTime()-time>= TimeUnit.SECONDS.toNanos(1)){
			time=System.nanoTime();
			toggle=!toggle;
		}
		if(toggle){
			SpaceOfficeAdventure.batch.draw(space, Gdx.graphics.getWidth()/2-space.getWidth()/2, 30);
		}

	}
        static void displayAbout(){

		SpaceOfficeAdventure.batch.draw(BG, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		if(SpaceOfficeAdventure.subState==0){
			SpaceOfficeAdventure.batch.draw(pc1,0,0 , Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		}else if(SpaceOfficeAdventure.subState==1){
			SpaceOfficeAdventure.batch.draw(pc2, 0,0 , Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		}else if(SpaceOfficeAdventure.subState==2){
			SpaceOfficeAdventure.batch.draw(pc3, 0,0 , Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		}else if(SpaceOfficeAdventure.subState==3){
			SpaceOfficeAdventure.batch.draw(pc4, 0,0 , Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		}else if(SpaceOfficeAdventure.subState==4){

		}

	}




}

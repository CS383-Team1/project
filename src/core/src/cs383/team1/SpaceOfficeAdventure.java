package cs383.team1;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import cs383.team1.model.GameManager;

/**
 * Created by Windows on 5/7/2015.
 */
public class SpaceOfficeAdventure extends ApplicationAdapter  {

    static SpriteBatch batch;
    public static GameManager gm;
    int mousex;
    int mousey;
    static int STATE=0;

    boolean stay=false;
    Main m;
    HostScreen host;
    JoinScreen join;


    @Override
    public void create() {
        batch= new SpriteBatch();
       // gm = GameManager.instance;
        m=new Main();
        m.create();


    }



    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        SpaceOfficeAdventure.batch.begin();
        mousex=Gdx.input.getX();
        mousey=Gdx.input.getY();
        /*
        * 0 = splash screen
        * 1 = main menu
        * 2 = host game
        * 3 = join game
        * 4 = about
        * 5 = quit
        *
        *
        * */

        if(STATE==0){
            Splash.displaySplash();
            if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
                STATE=1;
            }
        }else if(STATE==1){
            MainMenu.displayMainMenu(mousex,Gdx.graphics.getHeight()-mousey);
        }else if(STATE==2){

           if(!stay){
            host = new HostScreen();
               stay=true;
           }
            host.display();
        }else if(STATE==3){

            if(!stay){
                join = new JoinScreen();
                stay=true;
            }
            join.display();

        }else if(STATE==4){
           // setScreen(new JoinScreen(m));
            STATE=0;
            //Gdx.app.exit();
        }else if(STATE==5){
            //setScreen(new JoinScreen());
            Gdx.app.exit();
        }



        SpaceOfficeAdventure.batch.end();
        //super.render();
    }





    @Override
    public void dispose() {

    }
}

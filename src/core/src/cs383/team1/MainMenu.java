package cs383.team1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

import java.util.concurrent.TimeUnit;

/**
 * Created by Windows on 5/7/2015.
 */
public class MainMenu {

    static Texture menuBG = new  Texture(Gdx.files.internal("MainMenu/menuBG.png"));
    static Texture hostButton = new Texture(Gdx.files.internal("MainMenu/hostButton.png"));
    static Texture joinButton = new Texture((Gdx.files.internal("MainMenu/joinButton.png")));
    static Texture aboutButton = new Texture((Gdx.files.internal("MainMenu/aboutButton.png")));
    static Texture quitButton = new Texture((Gdx.files.internal("MainMenu/quitButton.png")));
    static Texture title = new Texture(Gdx.files.internal("MainMenu/title.png"));

    static Texture hostSelected = new Texture(Gdx.files.internal("MainMenu/hostSelected.png"));
    static Texture joinSelected = new Texture(Gdx.files.internal("MainMenu/joinSelected.png"));
    static Texture aboutSelected = new Texture(Gdx.files.internal("MainMenu/aboutSelected.png"));
    static Texture quitSelected = new Texture(Gdx.files.internal("MainMenu/quitSelected.png"));

    static Texture brandy = new Texture(Gdx.files.internal("MainMenu/brandy.png"));
    static Texture quote1 = new Texture(Gdx.files.internal("MainMenu/quote1.png"));
    static Texture quote2 = new Texture(Gdx.files.internal("MainMenu/quote2.png"));
    static Texture quote4 = new Texture(Gdx.files.internal("MainMenu/quote4.png"));

    static float buttonW = (float)(hostButton.getWidth()*0.5);
    static float buttonH = (float)(hostButton.getHeight()*0.5);
    static float width = Gdx.graphics.getWidth();
    static float height = Gdx.graphics.getHeight();

    static long time=0;
    static boolean toggle = false;
    static boolean changed=true;
    static int prev=0;

    static Sound newButton = Gdx.audio.newSound(Gdx.files.internal("sound/chatbeep.ogg"));

    static void displayMainMenu(int mousex, int mousey){
        SpaceOfficeAdventure.batch.draw(menuBG,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        SpaceOfficeAdventure.batch.draw(title, 0,4*buttonH+35,(float)(title.getWidth()*0.70),(float)(title.getHeight()*0.70));




        SpaceOfficeAdventure.batch.draw(hostButton, (int) ((width / 2) - (buttonW / 2)), 3 * buttonH + 10, buttonW, buttonH);
        SpaceOfficeAdventure.batch.draw(joinButton, (int) ((width / 2) - (buttonW / 2)), 2 * buttonH + 10, buttonW, buttonH);
        SpaceOfficeAdventure.batch.draw(aboutButton, (int) ((width / 2) - (buttonW / 2)), buttonH + 10, buttonW, buttonH);
        SpaceOfficeAdventure.batch.draw(quitButton, (int) ((width / 2) - (buttonW / 2)), 0 + 10, buttonW, buttonH);

        if(mousex<=(int)((width/2)-(buttonW/2))+buttonW && mousex>(int)((width/2)-(buttonW/2))){
            if(mousey>=((int)(3*buttonH+10)) && mousey<=((int)(3*buttonH+10+buttonH))){ //host
                SpaceOfficeAdventure.batch.draw(hostSelected, (int) ((width / 2) - (buttonW / 2)), 3 * buttonH + 10, buttonW, buttonH);
                displayBrandy((int) ((width / 2) - buttonW),(int)(3*buttonH+10),1);
                if(prev==1 && prev!=0){
                    changed=false;
                }else{
                    newButton.play();
                    changed=true;
                }
                prev=1;
                if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
                    SpaceOfficeAdventure.STATE=2;
                }

            }else if(mousey>=((int)(2*buttonH+10)) && mousey<=((int)(2*buttonH+10+buttonH))){
                SpaceOfficeAdventure.batch.draw(joinSelected, (int) ((width / 2) - (buttonW / 2)), 2 * buttonH + 10, buttonW, buttonH);
                displayBrandy((int) ((width / 2) - buttonW), (int) (2 * buttonH + 10), 2);

                if(prev==2){
                    changed=false;
                }else{
                    newButton.play();
                    changed=true;

                }
                prev=2;
                if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
                    SpaceOfficeAdventure.STATE=3;
                }

            }else if(mousey>=((int)(1*buttonH+10)) && mousey<=((int)(1*buttonH+10+buttonH))){
                SpaceOfficeAdventure.batch.draw(aboutSelected, (int) ((width / 2) - (buttonW / 2)), buttonH + 10, buttonW, buttonH);
                displayBrandy((int) ((width / 2) - buttonW), (int) (1 * buttonH + 10), 3);
                if(prev==3){
                    changed=false;
                }else{
                    newButton.play();
                    changed=true;
                }
                prev=3;
                if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
                    SpaceOfficeAdventure.STATE=4;
                }

            }else if(mousey>=10 && mousey<=((int)(buttonH+10+buttonH))){
                SpaceOfficeAdventure.batch.draw(quitSelected, (int) ((width / 2) - (buttonW / 2)), 0 + 10, buttonW, buttonH);
                displayBrandy((int) ((width / 2) - buttonW), 10, 4);
                if(prev==4){
                    changed=false;
                }else{
                    newButton.play();
                    changed=true;
                }
                prev=4;
                if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
                    SpaceOfficeAdventure.STATE=5;
                }

            }else{
                prev=0;
                changed=true;
            }






   }else{
            prev=0;
            changed=true;
        }
    }

    static void displayBrandy(int x, int y,int option){

        SpaceOfficeAdventure.batch.draw(brandy,x-brandy.getWidth() ,y, 100, 100);

        if(changed){
            time=System.nanoTime();
            toggle=true;
        }
        if(System.nanoTime()-time>= TimeUnit.SECONDS.toNanos(3)){
            //time=System.nanoTime();
            if(time==0){
                toggle=true;
            }else {
                toggle = false;

            }
        }


       if(toggle){
            if(option==1){
                SpaceOfficeAdventure.batch.draw(quote1,x+brandy.getWidth() ,(int)(y+brandy.getHeight()/2)+30,(float)(quote1.getWidth()*0.6),(float)(quote1.getHeight()*0.6));
            }else if(option==2){
                SpaceOfficeAdventure.batch.draw(quote2,x+brandy.getWidth() ,(int)(y+brandy.getHeight()/2)+30,(float)(quote1.getWidth()*0.6),(float)(quote1.getHeight()*0.6));

            }else if(option==3){
               // SpaceOfficeAdventure.batch.draw(quote2,x+brandy.getWidth() ,(int)(y+brandy.getHeight()/2)+30,(float)(quote1.getWidth()*0.6),(float)(quote1.getHeight()*0.6));

            }else if(option==4){
                SpaceOfficeAdventure.batch.draw(quote4,x+brandy.getWidth() ,(int)(y+brandy.getHeight()/2)+30,(float)(quote1.getWidth()*0.6),(float)(quote1.getHeight()*0.6));

            }

    }
    }

}

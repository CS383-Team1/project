package cs383.team1.input.ui;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Scaling;
import java.util.ArrayList;

/**
 *
 * @author Lance
 */
public class MenuQuests extends SubMenu {
        private ScrollPane questScroll;
        private Table questTable;
        private ArrayList<QuestLabel> questList;
        private Skin skin;

        public MenuQuests(Skin sk)
        {
                skin = sk;

                questList = new ArrayList();
                questTable = new Table();
                getDemoQuests();
                updateQuests();
                questScroll = new ScrollPane( questTable, skin );
                questScroll.setFadeScrollBars(false);
                questScroll.setOverscroll(false, false);
        }
        
        public ScrollPane questScroll() {
                return questScroll;
        }
        
        private void updateQuests()
        {
                Image img;
                TextButton abandon;
                String details;
                String icon;
                int totCount;
                int curCount;

                questTable.clearChildren();
                questTable.right();
                for ( int i =0; i < questList.size(); i++ ) {
                        final String name = questList.get(i).name();
                        details = questList.get(i).details();
                        icon = questList.get(i).icon();
                        totCount = questList.get(i).totCount();
                        curCount = questList.get(i).curCount();

                        img = getImage("quest" + icon );
                        img.setScaling(Scaling.none);
                        questTable.add(img).padTop(10).expand();

                        questTable.add( new Label( name, skin, "big" ) ).expand().fill().padLeft(20).padRight(50).row();
                        questTable.add( new Label( details, skin ) ).colspan(2).row();
                        questTable.add( new Label( (Integer.toString(curCount) + "/" + Integer.toString(totCount) ), skin ) );

                        abandon = new TextButton( "abandon" , skin);
                        abandon.addListener( new ClickListener() {
                                @Override
                                public void clicked( InputEvent event, float x, float y ) {
                                        //TODO: Make this abandon a quest
                                        System.out.println (name + " abandon");
                                        abandonQuest( name );
                                        updateQuests();
                                }
                        });
                        questTable.add( abandon ).right().row();

                        questTable.add( getImage( "bar" ) ).colspan(2).fillX().expand().row();
                }
        }
        
        private int abandonQuest( String n )
        {
                for (int i = 0; i < questList.size(); i++) {
                        if (questList.get(i).name().equals(n)) {
                                questList.remove(i);
                                return 0;
                        }
                }
                return -1;
        }
        
        private void getDemoQuests()
        {
                questList.clear();
                questList.add( new QuestLabel("Chair Murder", "Murder ALL the chairs!", "slay", 0, 20) );
                questList.add( new QuestLabel("Talk to HR", "Hustle yo' butt on over to the HR rep.", "talk", 0, 1) );
                questList.add( new QuestLabel("Go Outside", "Take a peek into the great outdoors.", "talk", 0, 1) );
                questList.add( new QuestLabel("Office Cleaner", "Show the intruders the way out.", "slay", 0, 7) );
                questList.add( new QuestLabel("VR Collector", "Get the V.R. headsets from R&D.", "fetch", 0, 10) );
                questList.add( new QuestLabel("Quest quest", "Quest for the quest in the quest.", "talk", 0, 1) );
                questList.add( new QuestLabel("Smack Down", "Lay the hurt down, brother.", "fetch", 0, 18) );
                questList.add( new QuestLabel("Office Slave", "Clean up the loose paper.", "fetch", 0, 14) );
        }
}

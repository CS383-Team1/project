package cs383.team1.input.ui;

import com.badlogic.gdx.graphics.Color;
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
		Table imgTable;
		Table txtTable;
		Image img;
		TextButton abandon;
		String details;
		String icon;
		Label count;
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
			
			imgTable = new Table();
			txtTable = new Table();

			img = getImage("quest" + icon );
			img.setScaling(Scaling.none);
			imgTable.add(img).padTop(10).expand().row();

			txtTable.left().add( new Label( name, skin, "big" ) )
				.fillX().expand().row();
			txtTable.left().add( new Label( details, skin ) )
				.fillX().expand().row();
			
			
//			imgTable.left().add( new Label( (Integer.toString(curCount)
//				+ "/" + Integer.toString(totCount) ), skin ) ).row();
			
			count = new Label( (Integer.toString(curCount)
				+ "/" + Integer.toString(totCount) ), skin );
			if (totCount == curCount)
				count.setColor(Color.GREEN);
			imgTable.left().add(count);

			abandon = new TextButton( "abandon" , skin, "exp");
			abandon.addListener( new ClickListener() {
				@Override
				public void clicked(
					InputEvent event, float x, float y ) {
					//TODO: Make this abandon a quest
					System.out.println (name + " abandon");
					abandonQuest( name );
					updateQuests();
				}
			});
			txtTable.add(abandon).right();
			questTable.add(imgTable).padRight(10);
			questTable.add(txtTable).fillX().expand().row();

			questTable.add( getImage( "bar" ) ).colspan(2)
				.fillX().expand().row();
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
		questList.add( new QuestLabel("Chair Murder",
			"Murder ALL the chairs!", "Slay", 0, 20) );
		questList.add( new QuestLabel("Talk to HR",
			"Hustle yo' butt to the HR rep.", "Talk", 1, 1) );
		questList.add( new QuestLabel("Go Outside",
			"Take a peek into the great outdoors.", "Talk", 0, 1) );
		questList.add( new QuestLabel("Office Cleaner",
			"Show the intruders the way out.", "Slay", 0, 7) );
		questList.add( new QuestLabel("VR Collector",
			"Get the V.R. headsets from R&D.", "Fetch", 10, 10) );
		questList.add( new QuestLabel("Quest quest",
			"Quest for the quest in the quest.", "Talk", 0, 1) );
		questList.add( new QuestLabel("Smack Down",
			"Lay the hurt down, brother.", "Fetch", 0, 18) );
		questList.add( new QuestLabel("Office Slave",
			"Clean up the loose paper.", "Fetch", 0, 14) );

	}
}

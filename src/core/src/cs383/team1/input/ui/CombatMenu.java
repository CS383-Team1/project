package cs383.team1.input.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.SplitPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import cs383.team1.combat.Move;
import static cs383.team1.input.ui.SubMenu.getImage;
import cs383.team1.model.GameManager;
import cs383.team1.model.overworld.Player;
import java.util.ArrayList;

/**
 *
 * @author Lance
 */
public class CombatMenu {
	SplitPane combat;
	ScrollPane cmdScroll;
	Table cmdGroup;
	List cmdList;
	Table cmdTable;
	Table hud;
	ProgressBar countdown;
	ArrayList <Move> attacks;
	Table atkTable;
	
	Skin skin;
	
	Player player = GameManager.instance.areas.current.player;
	
	GameManager gm = GameManager.instance;
	
	public CombatMenu(Skin sk) {
		attacks = new ArrayList();
		atkTable = new Table();
		skin = sk;
		cmdList = new List(skin, "big");
		
		cmdList.setItems("ATTACK", "DEFEND", "ITEM", "FLEE");
		
		cmdGroup = new Table();
		cmdGroup.add(cmdList).fillX().expand();
		
		cmdList.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y)
			{
				changeMenu(cmdList.getSelected().toString());
				super.clicked(event, x, y);
			}
		});

		cmdScroll = new ScrollPane(cmdGroup, skin);
		cmdScroll.setScrollingDisabled(true, false);
		cmdScroll.setFadeScrollBars(false);

		countdown = new ProgressBar(0, 100, 1, false, skin);
		hud = new Table();
		hud.add(countdown).left().top().fillX().expand();
		hud.setX(0);
		
		combat = new SplitPane(hud, cmdScroll, false, skin);
		
		setSplit(combat, (float) 0.65);

		combat.setX(0);
		combat.setY((int)Gdx.graphics.getHeight()/(float)5.8);
		combat.setWidth(Gdx.graphics.getWidth());
		combat.setHeight((int)Gdx.graphics.getHeight()*(float)(4.8/5.8));
		
		//Ignoring selection menu (until more stuff is implemented)
		changeMenu("ATTACK");
	}
	
	public SplitPane combat(){
		return combat;
	}
	
	public void setSplit(SplitPane s, float f)
	{
		s.setSplitAmount   ( f );
		s.setMaxSplitAmount( f );
		s.setMinSplitAmount( (float) ( f-(0.001) ) );
	}
	
	//Change the "submenu" to the one specified by 's'
	public void changeMenu( String s )
	{
		if (s.equals("MAIN"))
			cmdScroll.setWidget(cmdGroup);
		else if (s.equals("ATTACK")) {
//			combat.setSecondWidget(atkTable);
			cmdScroll.setWidget(atkTable);
			updateAttacks();
		}
		else
			Gdx.app.error("Menu changeMenu", "NYI option: " + s);
	}
	
	public void updateAttacks() {
		TextButton atkButton;
		attacks.clear();
		attacks.addAll(player.moves);
//		atkTable.top();
		
		atkTable.clearChildren();
		
//		atkTable.add( getImage( "bar" ) )
//			.colspan(2).padBottom(5).fillX().expandX().row();
//		atkTable.add( getImage( "bar" ) )
//			.colspan(2).padBottom(5).fillX().expandX().row();
			
		for (int i = 0; i < attacks.size(); i++) {
			final int index = i;
			final String atk = atkName(attacks.get(i).name);
			
			if (atk.toLowerCase().equals("null"))
				continue;
			String dmg = Double.toString(attacks.get(i)
				.getDamage());
			String blk = Integer.toString(attacks.get(i)
				.getBlockPercent());

			atkButton = new TextButton( atk, skin, "expBig" );
			if (atk.length() > 12)
				atkButton.setText(atk.substring(0, 12) + "_");

			atkButton.addListener(new ClickListener() {
				@Override
				public void clicked(
					InputEvent event, float x, float y ) {
					gm.combat.battles.get(0).turn();
					player.addAttack(attacks.get(index));
					gm.msg.add("Player uses: " + atk);
				}
			});
			atkTable.add(atkButton).colspan(2)
				.expandX().fillX().row();
			
			if (attacks.get(i).getDamage() > -1)
				atkTable.add(new Label(dmg + " dmg", skin, "red"))
					.padLeft(10).left().row();
			else
				atkTable.add(new Label("+" + dmg.substring(1) +
					" health", skin, "green")).padLeft(10).left().row();

			atkTable.add(new Label(blk + "% block", skin)).padLeft(10).padBottom(15)
				.left().row();
			
		}
	}
	
	public boolean visible() {
		return combat.isVisible();
	}
	
	private String atkName(String n) {
		return n.substring(0, 1).toUpperCase().concat(n.substring(1));
	}
}
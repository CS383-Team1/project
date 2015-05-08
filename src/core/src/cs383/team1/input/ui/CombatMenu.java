package cs383.team1.input.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
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
import cs383.team1.Main;
import cs383.team1.model.combat.Move;
import static cs383.team1.input.ui.SubMenu.getImage;
import cs383.team1.model.GameManager;
import cs383.team1.model.overworld.CPlayer;
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
	
	Player p = CPlayer.ownPlayer;
	
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

		countdown = new ProgressBar(0, 100, 1, false, skin, "custom-horizontal");
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
		countdown.setVisible(false);
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
			cmdScroll.setWidget(atkTable);
			updateAttacks();
		}
		else
			Gdx.app.error("Menu changeMenu", "NYI option: " + s);
	}
	
	/*
	Update the attack UI to reflect current move list
	*/
	public void updateAttacks() {
		attacks.clear();
		attacks.addAll(p.moves);

		atkTable.clearChildren();

		atkTable.add(getImage("bar")).colspan(2).padBottom(10)
			.fillX().expandX().row();

		for (int i = 0; i < attacks.size(); i++) {
			if (!attacks.get(i).name.toLowerCase().equals("null"))
				addMove(attacks.get(i));
		}
	}
	
	/*
	Add a move to the end of the attack list menu
	*/
	private void addMove(final Move move) {
		final String atk = atkName(move.name);
		String dmg  = Double.toString(move.getDamage());
		String blk  = Double.toString(move.getBlockPercent());
		String type = moveType(move);
		Image img;

		TextButton atkButton = new TextButton("SELECT", skin, "exp");
		Label name = new Label(atk, skin, "big");
		Label effect = new Label(dmg, skin, "big");
		
		//Add move type icon
		img = getImage("move" + type);
		img.setScaling(Scaling.none);
		atkTable.add(img).left().fillX().expandX();
		atkTable.add(name).left().fillX().expandX().row();

		//Cut move names to 12 characters
		if (atk.length() > 12)
			name.setText(atk.subSequence(0, 12));

		//Add the effects of the move as a label (X.X damage, etc.)
		atkTable.add(effect).colspan(2).padLeft(15).left().row();
		//Change the name scheme/color to reflect move effect
		if (move.getDamage() > 0){
			effect.setColor(1, 0, 0, 1);
			effect.setText(dmg + " damage");
		} else if (move.getDamage() < 0) {
			effect.setColor(0, 1, 0, 1);
			effect.setText("+" + dmg.substring(1) + " health");
		} else if (move.getDamage() == 0) {
			effect.setText("");
			atkTable.add(new Label("", skin)).colspan(2)
				.padBottom(32).row();
		}
		
		//Add the block chance
		atkTable.add(new Label(blk + "% block chance", skin))
			.colspan(2).padLeft(10).padBottom(15).left().row();

		//Create the atkButton listener - selects the specified move
		atkButton.addListener(new ClickListener() {
			@Override
			public void clicked(
			InputEvent event, float x, float y ) {
				gm.combat.battles.get(0).turn();
				p.addAttack(move);
				gm.msg.add("Player uses: " + atk);
				updateAttacks();
			}
		});
		
		//Adds the "SELECT" button to the UI
		atkTable.add(atkButton).colspan(2).padBottom(10)
			.fillX().expandX().row();
	}
	
	/*
	Get the current move type. For use with move icon type
	*/
	private String moveType(Move m)
	{
		Double d = m.getDamage();
		int i = m.getBlockPercent();
		if (d < 0)
			return ("Heal");
		else if (d == 0 || i >= 50)
			return ("Block");
		else
			return ("Attack");
	}
	
	public boolean visible() {
		return combat.isVisible();
	}
	
	private String atkName(String n) {
		return n.substring(0, 1).toUpperCase().concat(n.substring(1));
	}
	
	public void incProgress() {
		countdown.setValue(countdown.getValue()+1);
	}
}

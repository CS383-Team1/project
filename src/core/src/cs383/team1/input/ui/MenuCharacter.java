package cs383.team1.input.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.SplitPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

/**
 *
 * @author Lance
 */
public class MenuCharacter extends SubMenu {
	private SplitPane charSp;
	private Table charNameTable;
	private Table charStatTable;
	private Label statHp;
	private Label statAtk;
	private Label statDef;
	private Label statRep;
	private Skin skin;
	
	public MenuCharacter(Skin sk) {
		skin = sk;
		
		statHp = new Label("10", skin, "big");
		statAtk = new Label("5", skin, "big");
		statDef = new Label("4", skin, "big");
		statRep = new Label("5", skin, "big");

		charNameTable = new Table();
		charNameTable.add( new Label( "CHARACTER", skin, "big" ) )
			.row();
		charNameTable.add( getImage("bar") ).fill(20, 1).padBottom(5)
			.row();
		charNameTable.add( new Label( "JOHN SMITH", skin, "big" ) )
			.row();
		
		charStatTable = new Table()
			.padTop(20);
		charStatTable.add( getImage("statHp") )
			.padBottom(20);
		charStatTable.add( new Label( "HP:  ", skin, "big" ) )
			.padLeft(10).padBottom(20);
		charStatTable.add( statHp ).padBottom(20)
			.row();
		charStatTable.add( getImage("statDef") )
			.padBottom(20);
		charStatTable.add( new Label( "DEF:  ", skin, "big" ) )
			.padLeft(10);
		charStatTable.add( statDef ).padBottom(20)
			.row();
		charStatTable.add( getImage("statAtk") )
			.padBottom(20);
		charStatTable.add( new Label( "ATK:  ", skin, "big" ) )
			.padLeft(10).padBottom(20);
		charStatTable.add( statAtk ).padBottom(20)
			.row();
		charStatTable.add( getImage("statRep") )
			.padBottom(20);
		charStatTable.add( new Label( "REP:  ", skin, "big" ) )
			.padLeft(10).padBottom(20);
		charStatTable.add( statRep )
			.padBottom(20).row();

		charSp = new SplitPane(charNameTable, charStatTable, true,skin);
		charSp.setSplitAmount   ((float) 0.2500);
		charSp.setMaxSplitAmount((float) 0.2500);
		charSp.setMinSplitAmount((float) 0.2499);
	}
	
	public SplitPane charSp() {
		return charSp;
	}
	
	//Update the player's stats to the new values
	public void updateStats(int hp, int atk, int def, int rep)
	{
		statHp.setText(Integer.toString(hp));
		statAtk.setText(Integer.toString(atk));
		statDef.setText(Integer.toString(def));
		statRep.setText(Integer.toString(rep));
	}
}

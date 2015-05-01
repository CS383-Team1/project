package cs383.team1.input.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Scaling;
import cs383.team1.inventory.Equipment;
import cs383.team1.inventory.Item;
import cs383.team1.model.GameManager;
import cs383.team1.model.overworld.Player;

/**
 *
 * @author Lance
 */
public class MenuEquip extends SubMenu{
	private Table equipT;
	private Table statT;
	private Table displayT;
	private Table armorT;
	private Table itemLT;
	private Table itemRT;
	
	private Label headL;
	private Label neckL;
	private Label bodyL;
	private Label handL;
	private Label legsL;
	private Label feetL;
	
	private Table items;
	private Item itemL;
	private Label itemLName;
	private Item itemR;
	private Label itemRName;
	
	private Label hpL;
	private Label mpL;
	private Label apL;
	
	Skin skin;
	
	Player p;
	
	GameManager gm;
	
	public MenuEquip(Skin sk) {
		gm = GameManager.instance;

		skin = sk;
		
		equipT = new Table();
		statT = new Table();
		displayT = new Table();
		armorT = new Table();
		itemLT = new Table();
		itemRT = new Table();
		
		headL = new Label("N/A", skin, "big");
		neckL = new Label("N/A", skin, "big");
		bodyL = new Label("N/A", skin, "big");
		handL = new Label("N/A", skin, "big");
		legsL = new Label("N/A", skin, "big");
		feetL = new Label("N/A", skin, "big");
		
		hpL = new Label("0", skin, "big");
		mpL = new Label("0", skin, "big");
		apL = new Label("0", skin, "big");
		
		itemLName = new Label("", skin, "big");
		itemRName = new Label("", skin, "big");
		
		items = new Table();
		
		getPlayer();
		Equipment e = p.inventory.equiped;
		
		//Generate display table
		Image img = getImage("equip");
		img.setScaling(Scaling.none);
		displayT.add(img).fill().expand().left();
		
		//Generate armor table
		addArmor("HEAD: ", headL);
		addArmor("NECK: ", neckL);
		addArmor("BODY: ", bodyL);
		addArmor("HAND: ", handL);
		addArmor("LEGS: ", legsL);
		addArmor("FEET: ", feetL);

		//Generate stat table
		updateStats();
		
		statT.add(new Label("HP", skin)).colspan(2);
		statT.add(new Label("MP", skin)).colspan(2);
		statT.add(new Label("AP", skin)).colspan(2).row();
		statT.add(getImage("bar")).colspan(6).fillX().expandX().row();
		
		img = getImage("statHp");
		img.setScaling(Scaling.none);
		statT.add(img).fillX().expandX();
		statT.add(hpL).fillX().expandX();

		img = getImage("statMp");
		img.setScaling(Scaling.none);
		statT.add(img).fillX().expandX();
		statT.add(mpL).fillX().expandX();

		img = getImage("statAp");
		img.setScaling(Scaling.none);
		statT.add(img).fillX().expandX();
		statT.add(apL).fillX().expandX().row();
		statT.add(getImage("bar")).colspan(6).fillX().expandX().row();

		//Generate left item table
		if (itemL.range > 1) img = getImage("itemRanged");
		else img = getImage("itemMelee");
		img.setScaling(Scaling.none);
		itemLT.add(img).left().fillX().expandX();
		itemLT.add(itemLName).left().fillX().expandX();
		
		//Generate right item table
		if (itemR.range > 1) img = getImage("itemRanged");
		else img = getImage("itemMelee");
		img.setScaling(Scaling.none);
		itemRT.add(img).left().fillX().expandX();
		itemRT.add(itemRName).left().fillX().expandX();
		
		//Generate items table
		items.add(itemLT).fillX().expandX().left();
		items.add(itemRT).fillX().expandX().right();
		
		//Generate overall table
		equipT.add(statT).colspan(2).fillX().expandX().row();
		equipT.add(displayT).left().padRight(20);
		equipT.add(armorT).left().fillX().expand().row();
		equipT.add(getImage("bar")).colspan(2).fillX().expandX().row();
		equipT.add(items).fillX().expandX().colspan(2);
	}
	
	private void addArmor(String s, Label l)
	{
		TextButton b = new TextButton("UNEQUIP", skin, "exp");

		armorT.add(new Label(s, skin, "big"));
		armorT.add(l).width(30).expandX().left().row();
		armorT.add(b).padBottom(2).left().row();
		b.addListener(new EquipListener(this, 
			s.substring(0, 4).toLowerCase(), p));
	}
	
	public Table equipT(){
		return equipT;
	}
	
	private void getPlayer()
	{
		//Should be spelled "equiPPed"
		p = gm.areas.current.player;
		Equipment e = p.inventory.equiped;

		if (e.head!= null && !e.head.name.equals("Unknown"))
			headL.setText(e.head.name.replace("_", " "));
		else headL.setText("EMPTY");

		if (e.neck!= null && !e.neck.name.equals("Unknown"))
			neckL.setText(e.neck.name.replace("_", " "));
		else neckL.setText("EMPTY");

		
		if (e.chest!= null && !e.chest.name.equals("Unknown"))
			bodyL.setText(e.chest.name.replace("_", " "));
		else bodyL.setText("EMPTY");


		if (e.hands!= null && !e.hands.name.equals("Unknown"))
			handL.setText(e.hands.name.replace("_", " "));
		else handL.setText("EMPTY");


		if (e.legs!= null && !e.legs.name.equals("Unknown"))
			legsL.setText(e.legs.name.replace("_", " "));
		else legsL.setText("EMPTY");

		if (e.feet!= null && !e.feet.name.equals("Unknown"))
			feetL.setText(e.feet.name.replace("_", " "));
		else feetL.setText("EMPTY");
		
		if (e.leftWeapon!= null && !e.leftWeapon.name.equals("Unknown"))
			itemL = e.leftWeapon;
		else { itemL = new Item("EMPTY", "", "weapon"); }
		itemLName.setText(itemL.name.replace("_", " "));
		
		if (e.rightWeapon!= null && !e.rightWeapon.name.equals("Unknown"))
			itemR = e.rightWeapon;
		else { itemR = new Item("EMPTY", "", "weapon"); }
		itemRName.setText(itemR.name.replace("_", " "));

//		updateButtons();
	}
	
	private void updateStats(){
		hpL.setText(Integer.toString(p.hp));
		mpL.setText(Integer.toString(p.mp));
		apL.setText(Integer.toString(p.ap));
	}
	
//	private void updateButtons(){
//		Equipment e = p.inventory.equiped;
//		((EquipListener) bHead.getListeners().get(0)).setItem(e.head);
//		((EquipListener) bNeck.getListeners().get(0)).setItem(e.neck);
//		((EquipListener) bBody.getListeners().get(0)).setItem(e.chest);
//		((EquipListener) bHand.getListeners().get(0)).setItem(e.hands);
//		((EquipListener) bLegs.getListeners().get(0)).setItem(e.legs);
//		((EquipListener) bFeet.getListeners().get(0)).setItem(e.feet);
//	}
	
	public void update() {
		getPlayer();
		updateStats();
	}
}

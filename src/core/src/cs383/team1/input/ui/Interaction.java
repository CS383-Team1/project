package cs383.team1.input.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import cs383.team1.model.GameManager;
import cs383.team1.model.inventory.Item;
import cs383.team1.model.overworld.Area;
import cs383.team1.model.overworld.CoWorker;
import cs383.team1.model.overworld.Entity;
import cs383.team1.model.overworld.Player;
import cs383.team1.model.overworld.Tile;

/**
 *
 * @author Lance
 */
public class Interaction {
	Skin skin;
	public Table w;
	Label l;
	public Object target;
	GameManager gm;
	Player p;
	
	public Interaction(Skin sk, Player player){
		skin = sk;
		w = new Table();
		w.setX(0);
		w.setY(Gdx.graphics.getHeight());
		l = new Label("[SPACE]", skin);
		w.add(l);
		target = null;
		p = player;
		gm = GameManager.instance;
	}
	
	public Table interaction(){
		return w;
	}
	
	public void setVisible(boolean v)
	{
		w.setVisible(v);
	}
	
	public void spawnMenu(int facing) {
		int centerX = Gdx.graphics.getWidth() / 2;
		int centerY = Gdx.graphics.getHeight() / 2;

		switch (facing) {
		case 0:
			w.setX( centerX + (float)( Tile.WIDTH * 0.5 ));
			w.setY( centerY + (float)( Tile.HEIGHT * 2.5 ));
			break;
		case 1:
			w.setX( centerX + (float)( Tile.WIDTH * 1.5 ));
			w.setY( centerY - (float)( Tile.HEIGHT * 0.5));
			break;
		case 2:
			w.setX( centerX + (float)( Tile.WIDTH * 0.5 ));
			w.setY( centerY - (float) ( Tile.HEIGHT * 1.5 ) );
			break;
		case 3:
			w.setX( centerX - (float)( Tile.WIDTH * 0.5 ));
			w.setY( centerY - (float)( Tile.HEIGHT * 0.5 ));
			break;
		default:
			w.setX( centerX );
			w.setY( centerY );
			break;
		}
		w.setVisible(true);
	}
	
	public void setupInteract(Object o, int facing) {
		target = o;

		if (o instanceof CoWorker || o instanceof Item) {
			spawnMenu(facing);		
		}
	}
	
	public void interact() {
		if (target instanceof CoWorker) {
			gm.msg.add(((CoWorker)target).readNext());
		} else if (target instanceof Item) {
			p.inventory.pickUp((Item) target);
			gm.msg.add("Picked up " + ((Item) target).name);
			gm.areas.current.entities.remove((Entity)target);
		}
		target = null;
	}
	
}

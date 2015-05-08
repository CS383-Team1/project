package cs383.team1.model.overworld;

import com.badlogic.gdx.Gdx;
import cs383.team1.model.combat.Move;
import cs383.team1.model.inventory.Inventory;
import cs383.team1.model.inventory.Item;
import cs383.team1.model.overworld.Entity;
import cs383.team1.model.overworld.Position;
import java.util.ArrayList;


public interface PlayerInterface extends Entity{
	public int type();
        public int aType();
	public Position pos();
	public void setPos(int x, int y, int fx, int fy, int f);
        public String getQuest();
        public void addMove(String name, double damage, int blockPercent);
        public void addMove(Move move);
        public void addMove(Item i);
        public void removeMove(int index);
        public void addAttack(Move move);
        public void removeAttack();
        public boolean consumableAttack();
        public Position floatPos();
        public void setFloatPos(Position p);
        public boolean zeroFloat();
        public void decFloatPos(int i);
        public void setImage();
}

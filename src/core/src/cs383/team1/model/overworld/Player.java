package cs383.team1.model.overworld;

import com.badlogic.gdx.Gdx;
import cs383.team1.model.overworld.Entity;
import cs383.team1.model.overworld.Position;
import cs383.team1.combat.Move;
import java.util.ArrayList;


public final class Player implements Entity {
	public static final int TYPE = 1;
	public Position pos;
        public boolean roaming = true;
        
	public int hp;
	public int mp;
	public int ap;
        public ArrayList<String> possibleQuests = new ArrayList<String>();
        public ArrayList<String> acceptedQuests = new ArrayList<String>();
        public ArrayList<Move> moves = new ArrayList<Move>();
        public ArrayList<Move> attacks = new ArrayList<Move>();

	public Player() {
		this(new Position(0, 0), 0, 0, 0);
	}

	public Player(Position p, int hp_0, int mp_0, int ap_0) {
		Gdx.app.debug("Player:Player", "instantiating class");
		pos = p;
		hp = hp_0 > 0 ? hp_0 : 0;
		mp = mp_0 > 0 ? mp_0 : 0;
		ap = ap_0 > 0 ? ap_0 : 0;
                addMove("block", 0, 50);
                addMove("staple", 10, 1);
                addMove("throw coffee in face", 5, 1);
                System.out.println("Printing first move " + moves.get(0).name);
	}

	public int type() {
		return TYPE;
	}

	public Position pos() {
		return pos;
	}
        
        public String getQuest(){
            return acceptedQuests.get(0);
        }
        
        public void addMove(String name, int damage, int blockPercent){
            Move move = new Move(name, damage, blockPercent);
            moves.add(move);
        }
        
        public void removeMove(int index){
            moves.remove(index);
        }
        
        public void addAttack(Move move){
            attacks.add(move);
        }
        
        public void removeAttack(){
            attacks.remove(0);
        }
        public boolean consumableAttack(){
            return !attacks.isEmpty();
        }
}

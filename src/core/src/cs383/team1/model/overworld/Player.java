package cs383.team1.model.overworld;

import com.badlogic.gdx.Gdx;
import cs383.team1.model.GameManager;
import cs383.team1.model.combat.Move;
import cs383.team1.model.inventory.Inventory;
import cs383.team1.model.inventory.Item;
import cs383.team1.model.overworld.Entity;
import cs383.team1.model.overworld.Position;
import java.util.ArrayList;


public final class Player implements PlayerInterface {
	public static final Player ownPlayer = new Player();
	
	public static final int TYPE = 1;
        public static int aType = 4;
        public int facing = 2;
	public Position pos;
        public boolean roaming = true;
        public Position floatPos = new Position(0, 0);
	public int hp;
	public int mp;
	public int ap;
        public ArrayList<String> possibleQuests = new ArrayList<String>();
        public ArrayList<String> acceptedQuests = new ArrayList<String>();
        public ArrayList<Move> moves = new ArrayList<Move>();
        public ArrayList<Move> attacks = new ArrayList<Move>();
        public Inventory inventory;

	
        
	public Player() {
		this(new Position(1, 1), 0, 0, 0);
	}

	public Player(Position p, int hp_0, int mp_0, int ap_0) {
		Gdx.app.debug("Player:Player", "instantiating class");
		pos = p;
		hp = hp_0 > 0 ? hp_0 : 0;
		mp = mp_0 > 0 ? mp_0 : 0;
		ap = ap_0 > 0 ? ap_0 : 0;
                addMove("block", 0, 50);
                addMove("throw coffee in face", 5, 1);
                for(int i = 3; i < 10; i++ ){
                    addMove(new Move());
                }
                inventory = new Inventory("me");
                
	}

	public int type() {
		return TYPE;
	}
        
        public int aType() {
		return aType;
	}

	public Position pos() {
		return pos;
	}
        
        public String getQuest(){
            return acceptedQuests.get(0);
        }
        
        public void addMove(String name, double damage, int blockPercent){
            Move move = new Move(name, damage, blockPercent);
             for(Move m : this.moves){
                if("null".equals(m.name)){
                    int index = moves.indexOf(m);
                    moves.set(index, move);
                    return;
                }
            }
            moves.add(move);
        }
        
        public void addMove(Move move){
            moves.add(move);
        }
        
        public void addMove(Item i){
            Move move = new Move(i.name, i.damage, 1);
            for(Move m : this.moves){
                if("null".equals(m.name)){
                    int index = moves.indexOf(m);
                    moves.set(index, move);
                    return;
                }
            }
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
        
        public Position floatPos() {
                return floatPos;
        }
        
        public void setFloatPos(Position p) {
                floatPos = p;
        }
        
        public boolean zeroFloat() {
                return (floatPos.x == 0 && floatPos.y == 0);
        }
        
        public void decFloatPos(int i) {
                setImage();
                if (floatPos.x!=0 && Math.abs(floatPos.x) > i) {
                        if (floatPos.x > 1)
                                floatPos.x -= i;
                        else
                                floatPos.x += i;
                }
                else
                        floatPos.x = 0;
                if (floatPos.y!=0 && Math.abs(floatPos.y) > i) {
                        if (floatPos.y > 1)
                                floatPos.y -= i;
                        else
                                floatPos.y += i;                        
                }
                else
                        floatPos.y = 0;
        }
        
        public void setImage() {
                final int animDiv = 28;
                int anim;
                if ((Math.max(Math.abs(floatPos.x), Math.abs(floatPos.y)) % animDiv) >= Math.ceil(animDiv / 2))
                        anim = 0;
                else
                        anim = 1;
                
                if (floatPos.x == 0 && floatPos.y == 0) {
                        switch (facing) {
                        case 0:
                                aType = 5;
                                break;
                        case 1:
                                aType = 7;
                                break;
                        case 2:
                                aType = 4;
                                break;
                        case 3:
                                aType = 6;
                                break;
                        default:
                                aType = 4;
                                break;
                        }
                } else if (anim == 0) {
                        switch (facing) {
                        case 0:
                                aType = 9;
                                break;
                        case 1:
                                aType = 11;
                                break;
                        case 2:
                                aType = 8;
                                break;
                        case 3:
                                aType = 10;
                                break;
                        default:
                                aType = 8;
                                break;
                        }
                } else if (anim == 1) {
                        switch (facing) {
                        case 0:
                                aType = 13;
                                break;
                        case 1:
                                aType = 15;
                                break;
                        case 2:
                                aType = 12;
                                break;
                        case 3:
                                aType = 14;
                                break;
                        default:
                                aType = 12;
                                break;
                        }
                }

        }

	@Override
	public void setPos(int x, int y, int fx, int fy, int f) {
		pos = new Position(x, y);
		setFloatPos(new Position(fx, fy));
		facing = f;
	}
}

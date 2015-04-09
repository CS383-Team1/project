package cs383.team1.model.overworld;

import com.badlogic.gdx.Gdx;
import cs383.team1.model.overworld.Entity;
import cs383.team1.model.overworld.Position;

public final class Player implements Entity {
	public static final int TYPE = 1;
        public static int aType = 4;
        public int facing = 2;
	public Position pos;
        public Position floatPos = new Position(0, 0);
        
	public int hp;
	public int mp;
	public int ap;

	public Player() {
		this(new Position(0, 0), 0, 0, 0);
	}

	public Player(Position p, int hp_0, int mp_0, int ap_0) {
		Gdx.app.debug("Player:Player", "instantiating class");
		pos = p;
		hp = hp_0 > 0 ? hp_0 : 0;
		mp = mp_0 > 0 ? mp_0 : 0;
		ap = ap_0 > 0 ? ap_0 : 0;
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
        
        public Position floatPos() {
                return floatPos;
        }
        
        public void setFloatPos(Position p) {
                floatPos = p;
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
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs383.team1.model.overworld;

import com.badlogic.gdx.Gdx;

/**
 *
 * @author Casey
 */
public class ReturnEntity implements Entity {
    //public static final int TYPE = 5;
    public static final int TYPE = 3;
    public static final boolean PASSABLE = true;
    private Position pos;

    public ReturnEntity(){
        this(new Position(0, 0), "null");
    }

    public ReturnEntity(Position p, String s) {
		Gdx.app.debug("ReturnEntity:ReturnEntity", "instantiating class");
		pos = p;
    }
    
    
    @Override
    public int type() {
        return TYPE;
    }

    @Override
    public Position pos() {
        return pos;
    }
    
    
}

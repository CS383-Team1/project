package cs383.team1.net;

import cs383.team1.model.overworld.Position;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Lance
 */
public class PosResponse {
	public Position pos;
	public Position floatPos;
	public int facing;
        public boolean roaming;
        String areaName;
        int playerID;
        public Map<Integer, Position> otherPlayerPositions 
                = new HashMap<Integer, Position>();
	
	public PosResponse(){
	}
}

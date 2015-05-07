package cs383.team1.net;

import cs383.team1.model.overworld.Position;

/**
 *
 * @author Lance
 */
public class PosRequest {
	public Position pos;
	public Position floatPos;
	public int facing;
        public boolean roaming;
        String areaName;
	
	public PosRequest(){
	}
}

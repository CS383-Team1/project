/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs383.team1.net;

import cs383.team1.model.overworld.Position;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Casey
 */
public class PlayerData {
    public Map<Integer, PosResponse> others 
                = new HashMap<Integer, PosResponse>();
    
    public Map<Integer, PosRequest> response 
                = new HashMap<Integer, PosRequest>();
    public PlayerData(){
    }
}

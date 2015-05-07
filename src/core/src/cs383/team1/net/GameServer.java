package cs383.team1.net;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.kryonet.rmi.ObjectSpace;
import com.esotericsoftware.minlog.Log;
import cs383.team1.Main;
import cs383.team1.model.GameManager;
import cs383.team1.model.GameManagerInterface;
import cs383.team1.model.overworld.Area;
import cs383.team1.model.overworld.Npc;
import cs383.team1.model.overworld.Player;
import cs383.team1.model.overworld.Position;
import cs383.team1.model.overworld.StairsEntity;
import java.io.IOException;
import java.util.Map;


public class GameServer{
        public Server server;
	private boolean running;
        StairsEntity stairs;
        Position tempPos;
        public Integer connectCount = 0;
        
        public GameServer(final GameManagerInterface gameManager) throws IOException {
		Log.set(Log.LEVEL_DEBUG);
                stairs = new StairsEntity();
		server = new Server();
                tempPos  = new Position();
                Player.ownPlayer.playerID = 1;
                
		Network.registerKryo(server);

		server.addListener(new Listener() {
			public void connected(Connection c) {
				new ObjectSpace(c).register(Network.GM_ID,
					GameManager.instance);
                        }
		});
		server.addListener(new Listener() {
			public void connected(Connection c) {
				new ObjectSpace(c).register(Network.P_ID,
					GameManager.instance.areas.current.players);
                        }
		});
                server.addListener(new Listener() {
			public void received (Connection connection, Object object) {
                            if (object instanceof ConnectRequest) {
                                ConnectRequest conRequest = (ConnectRequest)object;
                                
                                ConnectResponse conResponse = new ConnectResponse();
                                connectCount++;
                                Player p = new Player();
                                
                                for(Map.Entry<String, Area> a : Main.gm.areas.areas.entrySet()){
                                    Main.gm.areas.current.players.put(connectCount, p);
                                }
                                
                                
                                conResponse.playerAmount = Main.gm.areas.current.players.size();
                                conResponse.assignedID = connectCount;
                                connection.sendTCP(conResponse);
                            }
                            
                        }

                });

                server.addListener(new Listener() {
			public void received (Connection connection, Object object) {
				if (object instanceof PosRequest) {
					PosRequest r = (PosRequest)object;
                                        Player p = GameManager.instance.areas.current.players.get(r.playerID);
                                        Npc npc;
                                        if(!GameManager.instance.areas.current.players.containsKey(r.playerID)){
                                            return;
                                        }
                                            
                                        //areas.current.player is a map, so get returns the value of the specified key
                                        //First player is "0", so look for "1"
                                        if(!Main.gm.areas.current.players.containsKey(r.playerID)){
                                            Player addedPlayer = new Player();
                                            for(Map.Entry<String, Area> a : Main.gm.areas.areas.entrySet()){
                                                Main.gm.areas.current.players.put(r.playerID, addedPlayer);
                                            }
                                        }
                                        
                                        //for(Map.Entry<Integer, Player> otherPlayers : Main.gm.areas.current.players.entrySet()){
                                            //if(otherPlayers.getValue().playerID == r.playerID){
                                                //p = otherPlayers.getValue();
                                                p.pos = r.pos;
                                                p.floatPos = r.floatPos;
                                                p.facing = r.facing;
                                                p.currentArea = r.areaName;
    //                                          System.out.println(r.s);
                                            
                                                if(r.roaming != true){
                                                    tempPos = r.pos;
                                                    tempPos.x--;
                                                    
                                                    if ((npc = (Npc)Main.gm.areas.findCombatant(
                                                        r.pos, 3)) != null){
                                            
                                                        Player.ownPlayer.roaming = false;
                                                        //Player.ownPlayer.pos.x = r.pos.x - 1;
                                                        Player.ownPlayer.pos.x = r.pos.x;
                                                        Player.ownPlayer.pos.y = r.pos.y;
                                                        Player.ownPlayer.pos.y = r.pos.y - 1;
                                                        Main.gm.combat.encounter(Player.ownPlayer, npc, Main.gm.areas.current.players);
                                                    }
                                                }

                                                PosResponse pr = new PosResponse();
                                                //for(Map.Entry<Integer, Player> otherPlayers : Main.gm.areas.current.players.entrySet()){
                                                  //  pr.otherPlayerPositions.put(otherPlayers.getKey(), otherPlayers.getValue().pos);
                                                //}
                                                
                                                //First send server's position information. Then send other player's position info
                                                 pr.pos = Player.ownPlayer.pos;
                                                    pr.floatPos = Player.ownPlayer.floatPos;
                                                    pr.facing = Player.ownPlayer.facing;
                                                    pr.roaming = Player.ownPlayer.roaming;
                                                    pr.areaName = Player.ownPlayer.currentArea;
                                                    pr.playerID = Player.ownPlayer.playerID;
                                                    connection.sendTCP(pr);
                                                    
                                                for(Map.Entry<Integer, Player> otherPlayers : Main.gm.areas.current.players.entrySet()){
                                                    if(otherPlayers.getKey() < connectCount){
                                                        pr.pos = otherPlayers.getValue().pos;//Player.ownPlayer.pos;
                                                        pr.floatPos = otherPlayers.getValue().floatPos;
                                                        pr.facing = otherPlayers.getValue().facing;
                                                        pr.roaming = otherPlayers.getValue().roaming;
                                                        pr.areaName = otherPlayers.getValue().currentArea;
                                                        pr.playerID = otherPlayers.getValue().playerID;
                                                        connection.sendTCP(pr);
                                                    }
                                                }
                                        
                                            }
                                        //}
				//}
			}
		});
                server.bind(Network.port);
		server.start();

		running = true;
	}

	public void listen(Listener l) {
		server.addListener(l);
	}
}

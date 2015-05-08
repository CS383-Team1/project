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
import cs383.team1.model.overworld.CPlayer;
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
                CPlayer.ownPlayer.playerID = 1;
                
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
                                
                                conResponse.playerAmount = Main.gm.areas.current.players.size();
                                conResponse.assignedID = connectCount;
                                connection.sendTCP(conResponse);
                            } else if (object instanceof MsgRequest) {
                                    MsgRequest mr = (MsgRequest)object;
                                    
                                    MsgResponse ms = new MsgResponse();
                                    ms.msg = mr.msg;
                                    GameManager.instance.msg.add(mr.msg);
                                    
                                    server.sendToAllTCP(ms);
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
                                        
                                                p.pos = r.pos;
                                                p.floatPos = r.floatPos;
                                                p.facing = r.facing;
                                                p.currentArea = r.areaName;
    //                                          System.out.println(r.s);
                                            
                                                if(r.roaming != true){
                                                    tempPos = r.pos;
                                                    //tempPos.x--;
                                                    
                                                    if ((npc = (Npc)Main.gm.areas.findCombatant(
                                                        r.pos, 3)) != null){
                                            
                                                        CPlayer.ownPlayer.roaming = false;
                                                        //Player.ownPlayer.pos.x = r.pos.x - 1;
                                                        CPlayer.ownPlayer.pos.x = r.pos.x;
                                                        CPlayer.ownPlayer.pos.y = r.pos.y;
                                                        CPlayer.ownPlayer.pos.y = r.pos.y - 1;
                                                        Main.gm.combat.encounter(CPlayer.ownPlayer, npc, Main.gm.areas.current.players);
                                                    }
                                                }

                                                PosResponse pr = new PosResponse();
                                                //First send server's position information. Then send other player's position info
                                                 pr.pos = CPlayer.ownPlayer.pos;
                                                    pr.floatPos = CPlayer.ownPlayer.floatPos;
                                                    pr.facing = CPlayer.ownPlayer.facing;
                                                    pr.roaming = CPlayer.ownPlayer.roaming;
                                                    pr.areaName = CPlayer.ownPlayer.currentArea;
                                                    pr.playerID = CPlayer.ownPlayer.playerID;
                                                    connection.sendTCP(pr);
                                                    
                                                for(Map.Entry<Integer, Player> otherPlayers : Main.gm.areas.current.players.entrySet()){
                                                    if(otherPlayers.getKey() < connectCount 
                                                            && otherPlayers.getValue().currentArea.equals(CPlayer.ownPlayer.currentArea)
                                                            && otherPlayers.getValue().playerID != CPlayer.ownPlayer.playerID){
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
        
        public void sendMsg(String s){
                MsgResponse m = new MsgResponse();
                m.msg = s;
                
                server.sendToAllTCP(m);
        }
}

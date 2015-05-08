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
//        public Integer connectCount = 0;
        
        public GameServer(final GameManagerInterface gameManager) throws IOException {
		Log.set(Log.LEVEL_INFO);
                stairs = new StairsEntity();
		server = new Server();
                tempPos  = new Position();
                CPlayer.ownPlayer.playerID = 0;
                
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
                                ConnectResponse conResponse = new ConnectResponse();
                                Player p = new Player();
				p.playerID = connection.getID();
				GameManager.instance.areas.current.players.put(connection.getID(), p);
                                conResponse.playerAmount = Main.gm.areas.current.players.size();
                                conResponse.assignedID = connection.getID();
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
					p.pos = r.pos;
					p.floatPos = r.floatPos;
					p.facing = r.facing;
					p.currentArea = r.areaName;
					if(r.roaming != true){
						tempPos = r.pos;
						if ((npc = (Npc)Main.gm.areas.findCombatant(
							r.pos, 3)) != null){
							CPlayer.ownPlayer.roaming = false;
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
						if(otherPlayers.getKey() < server.getConnections().length + 1
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
				} else if (object instanceof AtkRequest) {
					AtkRequest ar = (AtkRequest)object;
					Player p = CPlayer.ownPlayer;
//					System.out.println("ATKREQUEST: " + ar.npchp);
					GameManager.instance.combat.battles.get(0).setNpcHp(ar.npchp);
					if (ar.pid!=p.playerID)
						Main.gm.addMessage("Player " + ar.pid + " attacks!");
					Main.gm.addMessage("Player HP: " + p.hp + "; NPC HP: " + ar.npchp);
					sendAtk(ar.npchp, ar.pid);
				}
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
	
	public void sendAtk(String s){
		AtkResponse a = new AtkResponse();
		a.npchp = s;
		a.pid = CPlayer.ownPlayer.playerID;
		
		server.sendToAllTCP(a);
	}
	
	public void sendAtk(String s, int pid){
		AtkResponse a = new AtkResponse();
		a.npchp = s;
		a.pid = pid;
		
		server.sendToAllTCP(a);
	}
}

package cs383.team1.net;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.kryonet.rmi.ObjectSpace;
import com.esotericsoftware.minlog.Log;
import cs383.team1.Main;
import cs383.team1.model.GameManager;
import cs383.team1.model.GameManagerInterface;
import cs383.team1.model.overworld.Npc;
import cs383.team1.model.overworld.Player;
import cs383.team1.model.overworld.Position;
import cs383.team1.model.overworld.StairsEntity;
import java.io.IOException;


public class GameServer{
        public Server server;
	private boolean running;
        StairsEntity stairs;
        Position tempPos;
        
        

	public GameServer(final GameManagerInterface gameManager) throws IOException {
		Log.set(Log.LEVEL_DEBUG);
                stairs = new StairsEntity();
		server = new Server();
                tempPos  = new Position();

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
				if (object instanceof PosRequest) {
					PosRequest r = (PosRequest)object;
                                        Player p;
                                        Npc npc;
                                        //areas.current.player is a map, so get returns the value of the specified key
                                        //First player is "0", so look for "1"
                                        
                                        if(!GameManager.instance.areas.current.players.isEmpty()){
                                            p = GameManager.instance.areas.current.players.get(1);
                                        }else{
                                            return;
                                        }
                                        
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
                                            pr.pos = Player.ownPlayer.pos;
                                            pr.floatPos = Player.ownPlayer.floatPos;
                                            pr.facing = Player.ownPlayer.facing;
                                            pr.roaming = Player.ownPlayer.roaming;
                                            pr.areaName = Player.ownPlayer.currentArea;
                                            connection.sendTCP(pr);
                                        
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
}

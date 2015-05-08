package cs383.team1.net;

import com.badlogic.gdx.Gdx;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.rmi.ObjectSpace;
import com.esotericsoftware.minlog.Log;
import cs383.team1.Main;
import cs383.team1.model.GameManager;
import cs383.team1.model.overworld.Area;
import cs383.team1.model.overworld.CPlayer;
import cs383.team1.model.overworld.Npc;
import cs383.team1.model.overworld.Player;
import cs383.team1.model.overworld.Position;
import cs383.team1.net.Network;
import java.io.IOException;
import java.util.Map;

public class GameClient {
	private static final int TIMEOUT = 30000;
	private static final int STATE_NEW = 1;
	private static final int STATE_READY = 2;
	private static final int STATE_CONNECTED = 3;
	private static final int STATE_DISCONNECTED = 4;
	private static final int BUFFER_SIZE = 1024 * 8;
        Position tempPos;
        Npc npc;
        Integer connectRequestCount = 0;

	public Client client;
	private int state;

	public GameClient() {
		Log.set(Log.LEVEL_INFO);
                tempPos  = new Position();
                
		/* state = STATE_NEW; */
		state = STATE_READY;

		client = new Client(BUFFER_SIZE, BUFFER_SIZE);
		client.start();
		
		Network.registerKryo(client);
		
		client.addListener(new Listener() {
			public void received (Connection connection, Object object) {
				if (object instanceof Player) {
					System.out.println(((Player)object).pos.x);
				}
			}
		});
        
	}
        

	public void listen(Listener l) {
		client.addListener(l);
		
		if (state == STATE_NEW) {
			state = STATE_READY;
		}
	}

	public boolean connect(final String host, final int port) {
		if (state != STATE_READY) {
			return false;
		}

		new Thread("Client") {
			public void run() {
				try {
					client.connect(TIMEOUT, host, port);
					state = STATE_CONNECTED;

					while (true) {
						client.update(0);
					}
				} catch (IOException e) {
					e.printStackTrace();
					System.exit(1);
				}
			}
		}.start();
                
                client.addListener(new Listener() {
			public void received (Connection connection, Object object) {
				if (object instanceof ConnectResponse) {
                                        
                                        ConnectResponse c = (ConnectResponse)object;
					Player p;

                                        CPlayer.ownPlayer.playerID = c.assignedID;
					for (int i = 0; i < c.playerAmount; i++) {
						if (i!=CPlayer.ownPlayer.playerID) {
							p = new Player();
							p.playerID = i;
							GameManager.instance.areas.current.players.put(i, p);
						}
					}
                                        System.out.println("Printing playerID in GameClient: " + CPlayer.ownPlayer.playerID);
				}
			}
		});
		
		client.addListener(new Listener() {
			public void received (Connection connection, Object object) {
				if (object instanceof PosResponse) {
					PosResponse pr = (PosResponse)object;
					
					if (GameManager.instance.areas.current.players.get(pr.playerID)==null){
						Player p = new Player();
						p.setPos(pr.pos.x, pr.pos.y, pr.floatPos.x, pr.floatPos.y, pr.facing);
					} else {
						Player p = GameManager.instance.areas.current.players.get(pr.playerID);
                                                p.pos = pr.pos;
                                                p.floatPos = pr.floatPos;
                                                p.facing = pr.facing;
                                                p.currentArea = pr.areaName;
//						p.playerID = pr.playerID;
                                            
                                                if(pr.roaming != true){
							tempPos = pr.pos;
							tempPos.x--;
							if ((npc = (Npc)Main.gm.areas.findCombatant(
								pr.pos, 3)) != null){
								CPlayer.ownPlayer.roaming = false;
								CPlayer.ownPlayer.pos.x = pr.pos.x;
								CPlayer.ownPlayer.pos.y = pr.pos.y;
								CPlayer.ownPlayer.pos.y = pr.pos.y - 1;
								Main.gm.combat.encounter(CPlayer.ownPlayer, npc, Main.gm.areas.current.players);
							}
						}
					}
				}
			}
		});
		return true;
	}
        
        public void sendRequest() {
		PosRequest r = new PosRequest();
		r.pos = CPlayer.ownPlayer.pos;
		r.floatPos = CPlayer.ownPlayer.floatPos;
		r.facing = CPlayer.ownPlayer.facing;
                r.roaming = CPlayer.ownPlayer.roaming;
                r.areaName = CPlayer.ownPlayer.currentArea;
                r.playerID = CPlayer.ownPlayer.playerID;
		client.sendTCP(r);
	}

        public void sendConnectionRequest(){
            
                ConnectRequest conRequest = new ConnectRequest();
                client.sendTCP(conRequest);                        
        }
        
}

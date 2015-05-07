package cs383.team1.net;

import com.badlogic.gdx.Gdx;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.rmi.ObjectSpace;
import com.esotericsoftware.minlog.Log;
import cs383.team1.Main;
import cs383.team1.model.GameManager;
import cs383.team1.model.overworld.Npc;
import cs383.team1.model.overworld.Player;
import cs383.team1.model.overworld.Position;
import cs383.team1.net.Network;
import java.io.IOException;

public class GameClient {
	private static final int TIMEOUT = 3000;
	private static final int STATE_NEW = 1;
	private static final int STATE_READY = 2;
	private static final int STATE_CONNECTED = 3;
	private static final int STATE_DISCONNECTED = 4;
	private static final int BUFFER_SIZE = 1024 * 8;
        Position tempPos;
        Npc npc;

	public Client client;
	private int state;

	public GameClient() {
		Log.set(Log.LEVEL_DEBUG);
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
				if (object instanceof PosResponse) {
					PosResponse pr = (PosResponse)object;
					Player p = GameManager.instance.areas.current.players.get(1);
					p.pos = pr.pos;
					p.floatPos = pr.floatPos;
					p.facing = pr.facing;
                                        p.currentArea = pr.areaName;
                                        
                                        if(pr.roaming != true){
                                            tempPos = pr.pos;
                                            tempPos.x--;
                                            
                                            if ((npc = (Npc)Main.gm.areas.findCombatant(
                                                pr.pos, 3)) != null){
                                            
                                                Player.ownPlayer.roaming = false;
                                                //Player.ownPlayer.pos.x = pr.pos.x - 1;
                                                Player.ownPlayer.pos.x = pr.pos.x;
                                                Player.ownPlayer.pos.y = pr.pos.y;
                                                Player.ownPlayer.pos.y = pr.pos.y - 1;
                                                Main.gm.combat.encounter(Player.ownPlayer, npc, Main.gm.areas.current.players);
                                            }
                                        }else {
                                            //Player.ownPlayer.roaming = true;
                                        }
				}
			}
		});
		return true;
	}
        
        public void sendRequest() {
		PosRequest r = new PosRequest();
		r.pos = Player.ownPlayer.pos;
		r.floatPos = Player.ownPlayer.floatPos;
		r.facing = Player.ownPlayer.facing;
                r.roaming = Player.ownPlayer.roaming;
                r.areaName = Player.ownPlayer.currentArea;
		client.sendTCP(r);
	}
        
}
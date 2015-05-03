package cs383.team1.net;

import com.badlogic.gdx.Gdx;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.rmi.ObjectSpace;
import com.esotericsoftware.minlog.Log;
import cs383.team1.model.GameManager;
import cs383.team1.model.overworld.Player;
import cs383.team1.net.Network;
import java.io.IOException;

public class GameClient {
	private static final int TIMEOUT = 3000;
	private static final int STATE_NEW = 1;
	private static final int STATE_READY = 2;
	private static final int STATE_CONNECTED = 3;
	private static final int STATE_DISCONNECTED = 4;
	private static final int BUFFER_SIZE = 1024 * 8;

	public Client client;
	private int state;

	public GameClient() {
		Log.set(Log.LEVEL_DEBUG);

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
					Player p = GameManager.instance.areas.current.players.get(0);
					p.pos = pr.pos;
					p.floatPos = pr.floatPos;
					p.facing = pr.facing;
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
		client.sendTCP(r);
	}
        
}

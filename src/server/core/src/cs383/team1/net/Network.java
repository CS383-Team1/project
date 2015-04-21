package cs383.team1.net;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;
import com.esotericsoftware.kryonet.rmi.ObjectSpace;
import cs383.team1.model.GameManager;

public class Network {
	static public final int port = 54777;

	static public final short PLAYER = 1;
	static public final short CHAT_FRAME = 2;

	static public void register (EndPoint endPoint) {
		Kryo kryo = endPoint.getKryo();
		ObjectSpace.registerClasses(kryo);
		kryo.register(GameManager.class);
	}
}

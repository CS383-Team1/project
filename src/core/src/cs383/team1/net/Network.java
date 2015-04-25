package cs383.team1.net;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.EndPoint;
import com.esotericsoftware.kryonet.rmi.ObjectSpace;
import cs383.team1.input.InputManager;
import cs383.team1.model.GameManagerInterface;
import cs383.team1.model.combat.CombatManager;
import cs383.team1.model.overworld.Area;
import cs383.team1.model.overworld.AreaManager;

public class Network {
	static public final int port = 13370;

	static public final short GM_ID = 100;

	static public void registerKryo (EndPoint endPoint) {
		Kryo kryo = endPoint.getKryo();
		ObjectSpace.registerClasses(kryo);
		ObjectSpace os = new ObjectSpace();
		os.register(100, GameManagerInterface.class);
		os.register(101, Area.class);
		os.register(102, AreaManager.class);
		os.register(103, CombatManager.class);
		os.register(104, Integer.class);
		os.register(105, InputManager.class);
		os.register(106, String.class);
	}
}

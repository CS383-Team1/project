package cs383.team1.net;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.EndPoint;
import com.esotericsoftware.kryonet.rmi.ObjectSpace;
import cs383.team1.input.InputManager;
import cs383.team1.model.GameManagerInterface;
import cs383.team1.combat.Combat;
import cs383.team1.combat.CombatManager;
import cs383.team1.combat.Combatants;
import cs383.team1.combat.Move;
import cs383.team1.model.inventory.CraftedItemFactory;
import cs383.team1.model.inventory.Equipment;
import cs383.team1.model.inventory.Inventory;
import cs383.team1.model.inventory.Item;
import cs383.team1.model.overworld.Area;
import cs383.team1.model.overworld.AreaManager;
import cs383.team1.model.overworld.DemoEntity;
import cs383.team1.model.overworld.Entity;
import cs383.team1.model.overworld.Field;
import cs383.team1.model.overworld.Floor;
import cs383.team1.model.overworld.InteractableEntity;
import cs383.team1.model.overworld.LeftDesk;
import cs383.team1.model.overworld.Npc;
import cs383.team1.model.overworld.OutsideWall;
import cs383.team1.model.overworld.Player;
import cs383.team1.model.overworld.PlayerInterface;
import cs383.team1.model.overworld.Position;
import cs383.team1.model.overworld.ReturnEntity;
import cs383.team1.model.overworld.RightDesk;
import cs383.team1.model.overworld.Stairs;
import cs383.team1.model.overworld.StairsEntity;
import cs383.team1.model.overworld.Table;
import cs383.team1.model.overworld.Tile;
import cs383.team1.model.overworld.WalkWay;
import cs383.team1.model.overworld.Wall;
import java.util.ArrayList;

public class Network {
	static public final int port = 7777;

	static public final short GM_ID = 100;
	static public final short P_ID = 107;

	static public void registerKryo (EndPoint endPoint) {
		Kryo kryo = endPoint.getKryo();
		kryo.register(AtkRequest.class);
		kryo.register(AtkResponse.class);
                kryo.register(ConnectRequest.class);
                kryo.register(ConnectResponse.class);
                kryo.register(java.util.HashMap.class);
                kryo.register(java.util.HashSet.class);
		kryo.register(CraftedItemFactory.class);
		kryo.register(Equipment.class);
		kryo.register(Inventory.class);
		kryo.register(Item.class);
		kryo.register(Combat.class);
		kryo.register(CombatManager.class);
		kryo.register(Combatants.class);
		kryo.register(Move.class);
		kryo.register(Area.class);
		kryo.register(AreaManager.class);
		kryo.register(ArrayList.class);
		kryo.register(CombatManager.class);
		kryo.register(DemoEntity.class);
		kryo.register(Entity.class);
		kryo.register(Field.class);
		kryo.register(Floor.class);
		kryo.register(GameManagerInterface.class);
		kryo.register(InputManager.class);
		kryo.register(Integer.class);
		kryo.register(InteractableEntity.class);
		kryo.register(LeftDesk.class);
                kryo.register(MsgRequest.class);
                kryo.register(MsgResponse.class);
		kryo.register(Npc.class);
		kryo.register(OutsideWall.class);
		kryo.register(Player.class);
		kryo.register(PlayerInterface.class);
		kryo.register(Position.class);
		kryo.register(PosRequest.class);
		kryo.register(PosResponse.class);
		kryo.register(ReturnEntity.class);
		kryo.register(Request.class);
		kryo.register(RightDesk.class);
		kryo.register(Stairs.class);
		kryo.register(StairsEntity.class);
		kryo.register(String.class);
		kryo.register(Table.class);
		kryo.register(Tile.class);
		kryo.register(WalkWay.class);
		kryo.register(Wall.class);
		ObjectSpace.registerClasses(kryo);
		ObjectSpace os = new ObjectSpace();
		os.register(100, GameManagerInterface.class);
		os.register(101, Area.class);
		os.register(102, AreaManager.class);
		os.register(103, CombatManager.class);
		os.register(104, Integer.class);
		os.register(105, InputManager.class);
		os.register(106, String.class);
		os.register(107, PlayerInterface.class);
		os.register(108, Request.class);
	}
}

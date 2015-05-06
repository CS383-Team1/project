package cs383.team1.model.overworld;

import java.util.List;
import java.util.ArrayList;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import cs383.team1.inventory.Item;
import cs383.team1.model.overworld.Area;
import cs383.team1.model.overworld.DemoEntity;
import cs383.team1.model.overworld.Entity;
import cs383.team1.model.overworld.Field;
import cs383.team1.model.overworld.Position;
import cs383.team1.model.overworld.Player;
import cs383.team1.model.overworld.Tile;
import cs383.team1.model.overworld.Wall;
import cs383.team1.model.overworld.Floor;
import cs383.team1.model.overworld.WalkWay;
import cs383.team1.model.overworld.OutsideWall;
import cs383.team1.model.overworld.Desk;
import cs383.team1.model.overworld.FirePit;
import cs383.team1.model.overworld.Giant;
import cs383.team1.model.overworld.Tree;
import cs383.team1.model.overworld.Flower1;
import cs383.team1.model.overworld.Flower2;
import cs383.team1.model.overworld.Flower3;
import cs383.team1.model.overworld.Flower4;
import cs383.team1.model.overworld.Flower5;
import cs383.team1.model.overworld.Flower6;
import cs383.team1.model.overworld.Flower7;
import cs383.team1.model.overworld.Bench1;
import cs383.team1.model.overworld.Desk1;
import cs383.team1.model.overworld.Desk2;
import cs383.team1.model.overworld.Desk3;
import cs383.team1.model.overworld.Desk4;
import cs383.team1.model.overworld.Firepit1;
import cs383.team1.model.overworld.Firepit2;
import cs383.team1.model.overworld.Firepit3;
import cs383.team1.model.overworld.Firepit4;
import cs383.team1.model.overworld.MonitorPortal;
import cs383.team1.model.overworld.Bigtree1;
import cs383.team1.model.overworld.Bigtree2;
import cs383.team1.model.overworld.Bigtree3;
import cs383.team1.model.overworld.Bigtree4;

import java.util.HashMap;
import java.util.Map;

public final class AreaManager {
	public static final AreaManager instance = new AreaManager();

	public Map<String, Area> areas;
	public Area current;
        Area returnArea;
        ReturnEntity returnPos;
        Area combatArea;
        String originalArea;
        
	private AreaManager() {
		if(instance != null) {
			Gdx.app.error("AreaManager:AreaManager",
			  "reinstantiating singleton AreaManager");
			throw new IllegalStateException("reinstantiating singleton");
		}

		Gdx.app.debug("AreaManager:AreaManager", "instantiating class");
		areas = new HashMap<String, Area>();
		current = null;
                combatArea = new Area();
                returnArea = new Area();
                returnPos = new ReturnEntity();
                originalArea = new String();
	}

	public Area loadArea(String fname) {
		boolean passable;
		int i;
		int x;
		int y;
		int type;
		int hp;
		int mp;
		int ap;
		int numEntities;
		int numTiles;
		int offset;
		Area a;
		String fcontents;
        String entityData = new String();
		Position pos;
		Player player;
		String[] vals;
		FileHandle fin;
		List<Entity> entities;
		List<Tile> tiles;
                String questFile;
                String questList;

		offset = 0;
		entities = new ArrayList<Entity>();
		tiles = new ArrayList<Tile>();

		Gdx.app.log("AreaManager:loadArea", "Loading " + fname);

		fin = Gdx.files.internal(fname);
		fcontents = fin.readString();
		vals = fcontents.trim().split("\\s+");

		Gdx.app.debug("AreaManager:loadArea", "Loading tiles");

		numTiles = Integer.parseInt(vals[offset++]);
		for(i = offset; i < offset + (numTiles * 3); i += 3) {
			x = Integer.parseInt(vals[i + 0]);
			y = Integer.parseInt(vals[i + 1]);
			type = Integer.parseInt(vals[i + 2]);

			pos = new Position(x, y);

			switch(type) {
			case 1:
				Gdx.app.debug("AreaManager:loadArea",
				  "Loading Floor (" + vals[i] + "," + vals[i + 1] + ")");
				tiles.add(new Floor(pos));
				break;
			case 2:
				Gdx.app.debug("AreaManager:loadArea",
				  "Loading Wall (" + vals[i] + "," + vals[i + 1] + ")");
				tiles.add(new Wall(pos));
				break;
			case 3:
				Gdx.app.debug("AreaManager:loadArea",
				  "Loading Stairs (" + vals[i] + "," + vals[i + 1] + ")");
				tiles.add(new Stairs(pos));
				break;
            case 4:
                Gdx.app.debug("AreaManager:loadArea",
                  "Loading Field (" + vals[i] + "," + vals[i + 1] + ")");
                tiles.add(new Field(pos));
                break;
            case 5:
                Gdx.app.debug("AreaManager:loadArea",
                  "Loading WalkWay (" + vals[i] + "," + vals[i + 1] + ")");
                tiles.add(new WalkWay(pos));
                break;
            case 6:
                Gdx.app.debug("AreaManager:loadArea",
                  "Loading OutsideWall (" + vals[i] + "," + vals[i + 1] + ")");
                tiles.add(new OutsideWall(pos));
                break;
            case 7:
                Gdx.app.debug("AreaManager:loadArea",
                        "Loading Desk (" + vals[i] + "," + vals[i + 1] + ")");
                tiles.add(new Desk(pos));
                    break;
            case 8:
                Gdx.app.debug("AreaManager:loadArea",
                        "Loading Firepit (" + vals[i] + "," + vals[i + 1] + ")");
                tiles.add(new FirePit(pos));
                break;
            case 9:
                Gdx.app.debug("AreaManager:loadArea",
                        "Loading Giant (" + vals[i] + "," + vals[i + 1] + ")");
                tiles.add(new Giant(pos));
                break;
            case 10:
                Gdx.app.debug("AreaManager:loadArea",
                        "Loading Tree (" + vals[i] + "," + vals[i + 1] + ")");
                tiles.add(new Tree(pos));
                break;
            case 11:
                Gdx.app.debug("AreaManager:loadArea",
                        "Loading Flower1 (" + vals[i] + "," + vals[i + 1] + ")");
                tiles.add(new Flower1(pos));
                break;
            case 12:
                Gdx.app.debug("AreaManager:loadArea",
                        "Loading Flower2 (" + vals[i] + "," + vals[i + 1] + ")");
                tiles.add(new Flower2(pos));
                break;
            case 13:
                Gdx.app.debug("AreaManager:loadArea",
                        "Loading Flower3 (" + vals[i] + "," + vals[i + 1] + ")");
                tiles.add(new Flower3(pos));
                break;
            case 14:
                Gdx.app.debug("AreaManager:loadArea",
                        "Loading Flower4 (" + vals[i] + "," + vals[i + 1] + ")");
                tiles.add(new Flower4(pos));
                break;
            case 15:
                Gdx.app.debug("AreaManager:loadArea",
                        "Loading Flower5 (" + vals[i] + "," + vals[i + 1] + ")");
                tiles.add(new Flower5(pos));
                break;
            case 16:
                Gdx.app.debug("AreaManager:loadArea",
                        "Loading Flower6 (" + vals[i] + "," + vals[i + 1] + ")");
                tiles.add(new Flower6(pos));
                break;
            case 17:
                Gdx.app.debug("AreaManager:loadArea",
                        "Loading Flower7 (" + vals[i] + "," + vals[i + 1] + ")");
                tiles.add(new Flower7(pos));
                break;
            case 18:
                Gdx.app.debug("AreaManager:loadArea",
                        "Loading Bench1 (" + vals[i] + "," + vals[i + 1] + ")");
                tiles.add(new Bench1(pos));
                break;
            case 19:
                Gdx.app.debug("AreaManager:loadArea",
                        "Loading Desk1 (" + vals[i] + "," + vals[i + 1] + ")");
                tiles.add(new Desk1(pos));
                break;
            case 20:
                Gdx.app.debug("AreaManager:loadArea",
                        "Loading Desk2 (" + vals[i] + "," + vals[i + 1] + ")");
                tiles.add(new Desk2(pos));
                break;
            case 21:
                Gdx.app.debug("AreaManager:loadArea",
                    "Loading Desk3 (" + vals[i] + "," + vals[i + 1] + ")");
                tiles.add(new Desk3(pos));
                break;
            case 22:
                Gdx.app.debug("AreaManager:loadArea",
                        "Loading Desk4 (" + vals[i] + "," + vals[i + 1] + ")");
                tiles.add(new Desk4(pos));
                break;
            case 23:
                Gdx.app.debug("AreaManager:loadArea",
                        "Loading Firepit1 (" + vals[i] + "," + vals[i + 1] + ")");
                tiles.add(new Firepit1(pos));
                break;
            case 24:
                Gdx.app.debug("AreaManager:loadArea",
                        "Loading Firepit2 (" + vals[i] + "," + vals[i + 1] + ")");
                tiles.add(new Firepit2(pos));
                break;
            case 25:
                Gdx.app.debug("AreaManager:loadArea",
                        "Loading Firepit3 (" + vals[i] + "," + vals[i + 1] + ")");
                tiles.add(new Firepit3(pos));
                break;
            case 26:
                Gdx.app.debug("AreaManager:loadArea",
                        "Loading Firepit4 (" + vals[i] + "," + vals[i + 1] + ")");
                tiles.add(new Firepit4(pos));
                break;
            case 27:
                Gdx.app.debug("AreaManager:loadArea",
                        "Loading CyberTile (" + vals[i] + "," + vals[i + 1] + ")");
                tiles.add(new CyberTile(pos));
                break;
            case 28:
                Gdx.app.debug("AreaManager:loadArea",
                        "Loading MonitorPortal (" + vals[i] + "," + vals[i + 1] + ")");
                tiles.add(new MonitorPortal(pos));
                break;
            case 29:
                Gdx.app.debug("AreaManager:loadArea",
                        "Loading Bigtree1 (" + vals[i] + "," + vals[i + 1] + ")");
                tiles.add(new Bigtree1(pos));
                break;
            case 30:
                Gdx.app.debug("AreaManager:loadArea",
                        "Loading Bigtree2 (" + vals[i] + "," + vals[i + 1] + ")");
                tiles.add(new Bigtree2(pos));
                break;
            case 31:
                Gdx.app.debug("AreaManager:loadArea",
                        "Loading Bigtree3 (" + vals[i] + "," + vals[i + 1] + ")");
                tiles.add(new Bigtree3(pos));
                break;
            case 32:
                Gdx.app.debug("AreaManager:loadArea",
                        "Loading Bigtree4 (" + vals[i] + "," + vals[i + 1] + ")");
                tiles.add(new Bigtree4(pos));
                break;
			default:
				Gdx.app.error("AreaManager:loadArea",
				  "invalid tile type " + vals[i + 2]);
			}
		}
		offset += numTiles * 3;
                
                Gdx.app.debug("AreaManager:loadArea", "Loading quests");

                //Get quests from file and give them to specific NPC's
                String []readInQuests;
                questFile = "quests/npc1.txt";
                fin = Gdx.files.internal(questFile);
		questList = fin.readString();
		readInQuests = questList.trim().split("\n");
                System.out.println("Printing quest: " + readInQuests[0]);
                

		Gdx.app.debug("AreaManager:loadArea", "Loading entities");
                int k = 0;
		numEntities = Integer.parseInt(vals[offset++]);
		for(i = offset; i < offset + (numEntities * 4); i += 4) {
			x = Integer.parseInt(vals[i + 0]);
			y = Integer.parseInt(vals[i + 1]);
			type = Integer.parseInt(vals[i + 2]);
                        entityData = vals[i+3];

			pos = new Position(x, y);
                        
			System.out.println(entityData);
			
			switch(type) {
				case DemoEntity.TYPE:
					Gdx.app.debug("AreaManager:loadArea", "Loading DemoEntity");
					entities.add(new DemoEntity(pos));
					break;
                                case StairsEntity.TYPE:
					Gdx.app.debug("AreaManager:loadArea", "Loading StairsEntity");
                                        entities.add(new StairsEntity(pos,entityData));
                                        break;
                                case Npc.TYPE:
					Gdx.app.debug("AreaManager:loadArea", "Loading NpcEntity");
                                        entities.add(new Npc(pos,entityData));
                                        break;
                                case 16:
                                        Gdx.app.debug("AreaManager:loadArea", "Loading Item Entity");
                                        System.out.println("Printing entitydata: " + entityData);
                                        entities.add(new Item(pos,entityData));
                                        break;
                                case CoWorker.TYPE:
                                        Gdx.app.debug("AreaManager:loadArea", "Loading CoWorkerEntity");
                                        entities.add(new CoWorker(pos,entityData));
                                        break;
				default:
					Gdx.app.error("AreaManager:loadArea",
					  "invalid entity type " + vals[i + 2]);
			}
                        k++;
		}
		offset += numEntities * 4;

		Gdx.app.debug("AreaManager:loadArea", "Loading Player");

		x = Integer.parseInt(vals[offset++]);
		y = Integer.parseInt(vals[offset++]);
		hp = Integer.parseInt(vals[offset++]);
		mp = Integer.parseInt(vals[offset++]);
		ap = Integer.parseInt(vals[offset++]);
		pos = new Position(x, y);
		player = new Player(pos, hp, mp, ap);

		a = new Area(tiles, entities, player);
		areas.put(fname, a);

		return a;
	}
        
        public int useStairs(Position p)
        {
                StairsEntity se;
                if ((se = (StairsEntity)findEntity(p, 2)) !=null) {
                        changeArea(se.destination(),se.destinationPos());
                        return 0;
                }
                return -1;
        }
        
        //Find an entity of a given type at position p (well3112)
        public Entity findEntity(Position p, int t)
        {
                for (Entity e : current.entities) {
                        if(e.pos().x == p.x && e.pos().y == p.y && e.type() == t)
                                return e;
                }
                return null;
        }
        
        //Find the first entity at position p
        public Entity findEntity(Position p)
        {
                for (Entity e : current.entities) {
                        if(e.pos().x == p.x && e.pos().y == p.y)
                                return e;
                }
                return null;
        }
        
        //Method to change the current area and set the future position (well3112)
        public int changeArea(String s, Position pos)
        {
            /*Need support/major changes for multiplayer?
            Each level currently uses baked-in character data
            May need to change this to take a player parameter as well (well3112)*/
            
            if(changeArea(s) == 0) {
                    areas.get("area/".concat(s.concat(".txt"))).player.pos = pos;
                    return 0;
            }
            return -1;
        }
        
        //Method to change the current area (well3112)
        public int changeArea(String s)
        {
            String sFull = "area/".concat(s.concat(".txt"));
            if(areas.containsKey(sFull)) {
                    current = sFull != null ? areas.get(sFull) : null;
                    return 0;
            }
            Gdx.app.error("GameManager:update","invalid stairs transition");
            return -1;
        }
        
        public void getCombatArea(Position p, Player player, Npc npc){
            
            player.roaming = false;
            returnArea = current;
            for(Tile t : current.tiles){
                combatArea.tiles.add(t);
            }
            
            for(Entity e : current.entities){
                if(e.pos() == p){
                    combatArea.entities.add(e);
                }else if(e.pos() == npc.pos()){
                    combatArea.entities.add(e);
                    }
            }
        }
        
        public int endCombat(Player player){
            for(Entity e : current.entities){
                if(e.type() == 1){
                    returnArea.entities.add(e);
                    current.entities.remove(e);
                }
            }
            current = returnArea;
            //Possibly have transition animation here before changing current
            
            return 0;    
        }
        
        
        public Entity findCombatant(Position p, int t){
                for (Entity e : current.entities) {
                        if(e.pos().x  == (p.x + 1) && e.pos().y == p.y && e.type() == t)
                                return e;
                        else if(e.pos().x == (p.x - 1) && e.pos().y == p.y && e.type() == t)
                                return e;
                        else if(e.pos().x == p.x && e.pos().y == (p.y + 1) && e.type() == t)
                                return e;
                        else if(e.pos().x == p.x && e.pos().y == (p.y - 1) && e.type() == t)
                                return e;
                }
                return null;
        }
        
        public Entity findItem(Position p, int t){
                for (Entity e : current.entities) {
                        if(e.pos().x  == (p.x + 1) && e.pos().y == p.y && e.type() == t)
                                return e;
                        else if(e.pos().x == (p.x - 1) && e.pos().y == p.y && e.type() == t)
                                return e;
                        else if(e.pos().x == p.x && e.pos().y == (p.y + 1) && e.type() == t)
                                return e;
                        else if(e.pos().x == p.x && e.pos().y == (p.y - 1) && e.type() == t)
                                return e;
                }
                return null;
        }
}

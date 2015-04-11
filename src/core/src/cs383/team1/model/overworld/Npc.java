package cs383.team1.model.overworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import java.util.ArrayList;
import java.util.Arrays;

public class Npc implements Entity {
	public static final int TYPE = 3;

	private final Position pos;
        private final ArrayList<String> talk = new ArrayList();
        private int nextLine = -1;
        
	public Npc()
        {
                pos = new Position();
	}

        //Basic Constructor for NPC
        //Takes a position and a dialogue text file name to use as a "script"
	public Npc(Position p, String f)
        {
                String [] lines;
                String fileContents;
                
                f = f.substring(f.indexOf("quests:")+7, f.indexOf("}"));
                
		FileHandle fin = Gdx.files.internal(f);
                if (!fin.exists()) {
                        Gdx.app.error("Npc:", "Invalid Dialogue Filename");
                        talk.add("IAMERROR");
                } else {
                        fileContents = fin.readString();
                        lines = fileContents.trim().split("\n");
                        for (String line : lines) talk.add(line.trim());
                }
                
                Gdx.app.debug("StairsEntity:StairsEntity", "instantiating class");
		pos = p;
	}

        @Override
	public int type()
        {
		return TYPE;
	}

        @Override
	public Position pos()
        {
		return pos;
	}
        
        //Reads the entire script into System.out
        public ArrayList readScript() 
        {
                for (String talk1 : talk) {
                        System.out.println(talk1);
                }
                return talk;
        }
        
        //Reads the line specified by 'i'
        public String readLine(int i)
        {
                if (talk.size() > i)
                        return talk.get(i);
                return talk.get(talk.size()-1);
        }
        
        //Reads the line after the last one just read
        public String readNext()
        {
                nextLine++;
                return readLine(nextLine);
        }

	public void overworldAI(Area area) {
            Random randomDirection = new Random();
            int randomint = randomDirection.nextInt(4);
            System.out.println("Printing random direction: " + randomint);
            while(roaming == true){
                switch(randomint) {
				case 0:
                                        //Move Left
					next = new Position(pos.x - 1, pos.y);
					break;
				case 1:
                                        //Move right
					next = new Position(pos.x + 1, pos.y);
					break;
				case 2:
                                        //Move up
					next = new Position(pos.x, pos.y + 1);
					break;
				case 3:
                                        //Move down
					next = new Position(pos.x, pos.y - 1);
					break;
                                
			}

			target = null;
			for(Tile t : area.tiles) {
				if(t.pos().x == next.x && t.pos().y == next.y) {
					target = t;
					break;
				}
                                //if(t.pos().x == player.pos.x && t.pos().y == player.y){
                                    
                                //}
			}

			if(target == null) {
				Gdx.app.error("GameManager:update", "invalid move");
				
			}

			if(target.passable()) {
				pos = next;
			}
                    }
            }
}

package cs383.team1.model.overworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import cs383.team1.combat.Move;
import java.util.ArrayList;
import java.util.Random;

public class CoWorker implements Entity{
	public static final int TYPE = 17;
        String name;
        

	private Position pos;
        private final ArrayList<String> talk = new ArrayList();
        public ArrayList<Move> moves = new ArrayList<Move>();
        public ArrayList<Move> attacks = new ArrayList<Move>();

        private int nextLine = -1;
        
	public CoWorker(){
                pos = new Position();
                name = new String();
	}

        //Basic Constructor for NPC
        //Takes a position and a dialogue text file name to use as a "script"
	public CoWorker(Position p, String f)
        {
                String [] lines;
                String fileContents;
                
		name = f.substring(f.indexOf("name:")+5, f.indexOf("}"));
                f = f.substring(f.indexOf("quests:")+7, f.indexOf(",name:"));
                
		FileHandle fin = Gdx.files.internal(f);
                if (!fin.exists()) {
                        Gdx.app.error("Npc:", "Invalid Dialogue Filename");
                        talk.add("IAMERROR");
                } else {
                        fileContents = fin.readString();
                        lines = fileContents.trim().split("\n");
                        for (String line : lines) talk.add(name + ": " + line.trim());
                }
                
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

}

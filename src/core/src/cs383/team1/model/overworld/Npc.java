package cs383.team1.model.overworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import java.util.ArrayList;
import java.util.Arrays;

public class Npc implements Entity {
	public static final int TYPE = 3;

	private final Position pos;
        private final ArrayList<String> talk = new ArrayList();
        
	public Npc()
        {
                pos = new Position();
	}

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
        
        public void readScript() 
        {
                for (String talk1 : talk) {
                        System.out.println(talk1);
                }
        }
}

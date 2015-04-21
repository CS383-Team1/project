package cs383.team1.model.overworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import cs383.team1.model.combat.Move;
import java.util.ArrayList;
import java.util.Random;

public class Npc implements Entity{
	public static final int TYPE = 3;
        public boolean roaming = true;
        public int hp;
	public int mp;
	public int ap;
        int lastPlayerMove;
        double fitness;
        double mutatedFitness;
        String name;
        

	private Position pos;
        private final ArrayList<String> talk = new ArrayList();
        public ArrayList<Move> moves = new ArrayList<Move>();
        public ArrayList<Move> attacks = new ArrayList<Move>();

        private int nextLine = -1;
        
	public Npc(){
                pos = new Position();
                hp = 100;
                lastPlayerMove = 0;
                fitness = 1.0;
                mutatedFitness = 0.0;
                name = new String();
                addMove("block", 0, 50);
                addMove("staple", 10, 1);
                addMove("throw coffee in face", 5, 1);
                addMove("drink coffee", -5, 1);
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

                hp = 100;
                lastPlayerMove = 0;
                fitness = 1.0;
                mutatedFitness = 0.0;
                addMove("block", 0, 50);
                addMove("staple", 10, 1);
                addMove("throw coffee in face", 5, 1);
                
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

/*
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
        */
        
        public double combatAI(double lastPlayerDamage, int lastPlayerExpectedDamage){
            
                        
                Random selectionGen = new Random();
                int selection;
                double selectedMoveDamage;
                double moveDamage = 0.0;
                //add random default attack
                int lastNpcMove;
                
                addAttack(moves.get(selectionGen.nextInt(moves.size())));
                moveDamage = attacks.get(0).getDamage();
                /*
                //Getting default attack damage
                
                moveDamage = moves.get(0).getDamage();
                //Check fitness of default attack
                fitness = checkAttackFitness(lastPlayerExpectedDamage, lastPlayerDamage);
               
                while(fitness > mutatedFitness){
                selection = selectionGen.nextInt(moves.size());
                Move moveSelected = moves.get(selection);
                selectedMoveDamage = moveSelected.getDamage();
                System.out.println("Printing random direction: " + selection);
              
                //fitness = checkAttackFitness(lastPlayerMove, lastPlayerDamage);
                //Selected move damage will not work here as second variable: will return 0
                mutatedFitness = checkAttackFitness(selection, (selectedMoveDamage * fitness));
                //Probably need to limit this loop by a delta time value (5 seconds)
                //while(fitness < mutatedFitness){
                if(mutatedFitness < fitness){
                    lastNpcMove = selection;
                    fitness = mutatedFitness;
                    moveDamage = selectedMoveDamage;
                    addAttack(moves.get(selection));
                }
                }
                //Not sure if this is what to return
                        */
                return moveDamage;
            
        }
        //Returns percentage of player
        public double checkAttackFitness(int expectedDamage, int actualDamage){
             
            double result = Math.pow((expectedDamage - actualDamage), 2);
            
            return result;
        }
        
        //Adds move to list of available moves
        public void addMove(String name, int damage, int blockPercent){
            Move move = new Move(name, damage, blockPercent);
            moves.add(move);
        }
        
        public void addMove(Move move){
            moves.add(move);
        }
        
        public void removeMove(int index){
            moves.remove(index);
        }
        
        //Adds chosen move to list of attacks waiting to be processed by CombatManager
        public void addAttack(Move move){
            attacks.add(move);
        }
        
        //Removes first move in list of attacks waiting to be processed by CombatManager
        public void removeAttack(){
            attacks.remove(0);
        }
        
         public boolean consumableAttack(){
            return !attacks.isEmpty();
        }
}

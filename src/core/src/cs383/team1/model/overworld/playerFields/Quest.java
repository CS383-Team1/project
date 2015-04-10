package cs383.team1.model.overworld.playerFields;

/**
 *
 * @author Lance
 */
public class Quest {
        String name;
        String obj;
        
        public Quest(String s, String s2)
        {
                name=s;
                obj=s2;
        }
        
        public String name(){
                return name;
        }
        
        public String obj(){
                return obj;
        }
}


package cs383.team1.input;

import cs383.team1.model.overworld.Player;
import java.io.*;
/**
 * @author Tessa
 */

/*
    LoadFromFile
    load file into Player
    Throws Exception on Failure
*/ 
public class Load{
    public void LoadFile(String FileName, Player P) throws IOException{
      FileInputStream in = null;

      try {
         in = new FileInputStream("input.txt");
         
         int c;
         while ((c = in.read()) != -1) {
             // will have to decide save file format 
             // parse save file
            /*P.ap = ;
             P.hp = ;
             P.mp = ;
             P.pos = ;
             P.equipment = ;
             P.name = ;*/
         }
      }finally {
         if (in != null) {
            in.close();
         }
      }
        
    }
    
    /*
    LoadString
    load string into Player
    Practically the same as LoadFromFile, but the string can already be loaded
    */ 
    public boolean LoadString(String s, Player P){
        // Parse string here 
        /*P.ap = ;
        P.hp = ;
        P.mp = ;
        P.pos = ;
        P.equipment = ;
        P.name = ;*/
        return false;
    }
    
}

package cs383.team1.input.ui;

/**
 *
 * @author Lance
 */
public class Notification {
        String sprite;
        String msg;
        
        public Notification(String s1, String s2)
        {
                sprite = s1;
                msg = s2;
        }

        public Notification() {
                this ("img/null", "");
        }
        
        public String sprite() {
                return sprite;
        }
        
        public String msg() {
                return msg;
        }
        
        public void setSprite(String s) {
                sprite = s;
        }
        
        public void setMsg(String s) {
                msg = s;
        }
        
        public void clearAll() {
                sprite = "img/null";
                msg = "";
        }
}

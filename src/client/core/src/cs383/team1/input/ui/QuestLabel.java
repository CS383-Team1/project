package cs383.team1.input.ui;

/**
 *
 * @author Lance
 */
public class QuestLabel {
        private String name;
        private String details;
        private String icon;
        private int totCount;
        private int curCount;
        
        public QuestLabel(String n, String d, String i, int c, int t) {
                name = n;
                details = d;
                icon = i;
                totCount = t;
                curCount = c;
        }
        
        public String name() {
                return name;
        }
        
        public String details() {
                return details;
        }
        
        public String icon() {
                return icon;
        }
        
        public int totCount() {
                return totCount;
        }
        
        public int curCount() {
                return curCount;
        }
}

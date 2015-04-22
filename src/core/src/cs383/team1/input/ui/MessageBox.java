package cs383.team1.input.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.SplitPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import java.util.ArrayList;

/**
 *
 * @author Lance
 */
public class MessageBox {
        private final ArrayList <String> text;
        private final Label mText;
        private final Table mTable;
        private final ScrollPane mScroll;
        private final SplitPane mHistory;
        private final Table bar;
        
        private final SplitPane msg;
        public TextField input;
        
        private boolean toBottom;
        
        public MessageBox(Skin sk)
        {
                text = new ArrayList();
                mTable = new Table(sk);
                mText = new Label("",sk);
                input = new TextField("", sk);
                
                //Create the "Chat History" section
                mScroll = new ScrollPane(mTable, sk, "chat");
                mTable.padTop(30);
                mText.setWrap(true);
                
                mScroll.setScrollingDisabled(true, false);
                mScroll.setOverscroll(false, false);
                mScroll.setSmoothScrolling(false);
                
                //Add the top bar to the chat window
                bar = new Table();
                bar.add(SubMenu.getImage("bar")).fillX();
                mHistory = new SplitPane(bar, mScroll, true, sk);
                bar.setFillParent(true);
                mHistory.setSplitAmount   ((float) 0.2500);
                mHistory.setMaxSplitAmount((float) 0.2500);
                mHistory.setMinSplitAmount((float) 0.2499);
                
                //Create the overall window's splitpane
                msg = new SplitPane(mHistory, input, true, sk);

                msg.setWidth(Gdx.graphics.getWidth());
                msg.setHeight(Gdx.graphics.getWidth()/6);
                msg.setX(0);
                msg.setY(0);

                msg.setSplitAmount   ((float) 0.8500);
                msg.setMaxSplitAmount((float) 0.8500);
                msg.setMinSplitAmount((float) 0.8499);

                mScroll.setFillParent(true);
                mTable.setFillParent(true);
                mTable.left().bottom().add(mText).width(msg.getWidth());
        }
        
        //Return the overall pane for the UIDisplay
        public SplitPane msg(){
                return msg;
        }
        
        //Add a message from the input
        public void addMessage(String s)
        {
                if (s.equals("?") || s.toLowerCase().equals("help")) {
                     text.add("Pickup item: E");
                     text.add("Move: Arrow Keys");
                     text.add("Main Menu: ESC");
                     text.add("Chat: Enter");
                } else text.add(s);
                updateMessages();
        }
        
        //Update messages list
        //This should be updated so it doesn't have to reconstruct the entire
        //chat history each time
        public void updateMessages()
        {
                String s = "";
                if (text.size() > 100)
                        text.remove(0);
                for (int i = 0; i < text.size(); i++) {
                        s = s.concat(text.get(i)).concat("\n");
                }
                mText.setText(s);
                toBottom = true;
        }
        
        public boolean toBottom() {
                return toBottom;
        }
        
        public void scrollBottom() {
                mScroll.setScrollPercentY(1);
                toBottom = false;
        }
}

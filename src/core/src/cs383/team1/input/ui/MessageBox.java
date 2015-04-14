package cs383.team1.input.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.SplitPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import java.util.ArrayList;

/**
 *
 * @author Lance
 */
public class MessageBox {
        private ArrayList <String> text;
        private Label mText;
        private Table mTable;
        private ScrollPane mScroll;
        private SplitPane mHistory;
        private Table bar;
        
        private SplitPane msg;
        public TextField input;
        
        public MessageBox(Skin sk)
        {
                text = new ArrayList();

                mTable = new Table(sk);
                mText = new Label("",sk);
                input = new TextField("", sk);
                
                mScroll = new ScrollPane(mTable, sk, "chat");
                mTable.pad(10);
                mTable.padBottom(15);
                mText.setWrap(true);
                
                mScroll.setScrollingDisabled(true, false);
                mScroll.setOverscroll(false, false);
                mScroll.setSmoothScrolling(false);
                
                bar = new Table();
                bar.add(SubMenu.getImage("bar")).fillX();
                
                mHistory = new SplitPane(bar, mScroll, true, sk);
                bar.setFillParent(true);
                mHistory.setSplitAmount   ((float) 0.2500);
                mHistory.setMaxSplitAmount((float) 0.2500);
                mHistory.setMinSplitAmount((float) 0.2499);
                
//                msg = new SplitPane(mScroll, input, true, sk);
                msg = new SplitPane(mHistory, input, true, sk);

                msg.setWidth(Gdx.graphics.getWidth());
                msg.setHeight(Gdx.graphics.getWidth()/6);
                msg.setX(0);
                msg.setY(0);
                
                System.out.println((float)Gdx.graphics.getWidth()/42);
                msg.setSplitAmount   ((float) 0.8500);
                msg.setMaxSplitAmount((float) 0.8500);
                msg.setMinSplitAmount((float) 0.8499);

                mScroll.setFillParent(true);
                mTable.setFillParent(true);
                mTable.left().bottom().add(mText).width(msg.getWidth());
        }
        
        public SplitPane msg(){
                return msg;
        }
        
        //Add a message from the input
        public void addMessage(String s)
        {
                text.add(s);
                updateMessages();
        }
        
        //Update messages list
        public void updateMessages()
        {
                String s = "";
                if (text.size() > 100)
                        text.remove(0);
                for (int i = 0; i < text.size(); i++) {
                        s = s.concat(text.get(i)).concat("\n");
                }
                mText.setText(s);
                mScroll.setScrollPercentY(1);
        }
}

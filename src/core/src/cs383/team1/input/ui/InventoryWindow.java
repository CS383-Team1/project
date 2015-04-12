package cs383.team1.input.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.SplitPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import java.util.ArrayList;

/**
 *
 * @author Lance
 */
public class InventoryWindow {
        Window w;
        Window inv;
        SplitPane sp;
        SplitPane charSp;
        Label equipLabel;
        Image equipHand;
        Image equipArmor;
        Table equipTable;
        Table statTable;
        
        ScrollPane itemsPane;
        Table itemsTable;
        ArrayList<ItemLabel> itemList;
        List<String> items;
        
        Label statHp;
        Label statAtk;
        Label statDef;
        Label statRep;
        
        Skin skin;
        
        Image itemRanged;
        Image itemMelee;
        Image itemArmor;
        Image itemConsumable;
        
        public InventoryWindow(Skin sk)
        {
                skin = sk;

                itemsTable = new Table();

                itemRanged     = new Image( new TextureRegion( new Texture( Gdx.files.internal( "ui/itemRanged.png" ) ) ) );
                itemMelee      = new Image( new TextureRegion( new Texture( Gdx.files.internal( "ui/itemMelee.png" ) ) ) );
                itemArmor      = new Image( new TextureRegion( new Texture( Gdx.files.internal( "ui/itemArmor.png" ) ) ) );
                itemConsumable = new Image( new TextureRegion( new Texture( Gdx.files.internal( "ui/itemConsumable.png" ) ) ) );
                
                itemList = new ArrayList();
                
                
                itemList.add( new ItemLabel("This", "ranged") );
                itemList.add( new ItemLabel("That", "ranged") );
                itemList.add( new ItemLabel("Laser Gun", "ranged") );
                itemList.add( new ItemLabel("LaZer Gun", "ranged") );
                itemList.add( new ItemLabel("LaZ0r G()n", "ranged") );
                itemList.add( new ItemLabel("L@Z3R G()N", "ranged") );
                itemList.add( new ItemLabel("Me Arrrrmor", "armor") );
                itemList.add( new ItemLabel("'Dis Armor", "armor") );
                itemList.add( new ItemLabel("'Dat Armor", "armor") );
                itemList.add( new ItemLabel("Good Armor", "armor") );
                itemList.add( new ItemLabel("Bad Armor", "armor") );
                itemList.add( new ItemLabel("Ugly Armor", "armor") );
                itemList.add( new ItemLabel("Punting Stapler", "melee") );
                itemList.add( new ItemLabel("Sturdy Briefase", "melee") );
                itemList.add( new ItemLabel("Potion", "consumable") );
                itemList.add( new ItemLabel("Bagel", "consumable") );
                itemList.add( new ItemLabel("Staple", "consumable") );
                itemList.add( new ItemLabel("Pushpin", "consumable") );
                itemList.add( new ItemLabel("Donut", "consumable") );
                
                updateItems();


                itemList = new ArrayList();
                updateStats(0, 0, 0, 0);
                items = new List(skin);
                items.setItems("This", "That", "Potion", "Bagel", "Shoe", "Diamond", "DeLorean", "Laser Gun", "LaZer Gun", "Another thing", "???", "Lots of stuff", "Potions?", "Cube");
//                itemsPane = new ScrollPane( items, skin );
                itemsPane = new ScrollPane( itemsTable );
                equipTable = new Table();
                statTable = new Table();
                
                w = new Window("CHARACTER", sk);
                w.setHeight(200);
                w.setWidth(200);
                
                inv = new Window("INVENTORY", sk);
                inv.setHeight(200);
                inv.setWidth(200);
                
                w.top();

                //HitPoints Stat
                statTable.left().add( new Image( new TextureRegion( new Texture( Gdx.files.internal( "ui/statHp.png" ) ) ) ) );
                statTable.left().add( new Label( "HIT: ", skin )).padLeft(10);
                statTable.left().add( statHp ).row();

                //Attack Stat
                statTable.left().add( new Image( new TextureRegion( new Texture( Gdx.files.internal( "ui/statAtk.png" ) ) ) ) );
                statTable.left().add( new Label( "ATK: ", skin )).padLeft(10);
                statTable.left().add( statAtk ).row();

                //Defence Stat
                statTable.left().add( new Image( new TextureRegion( new Texture( Gdx.files.internal( "ui/statDef.png" ) ) ) ) );
                statTable.left().add( new Label( "DEF: ", skin )).padLeft(10);
                statTable.left().add( statDef ).row();

                //Reputation Stat
                statTable.left().add( new Image( new TextureRegion( new Texture( Gdx.files.internal( "ui/statRep.png" ) ) ) ) );
                statTable.left().add( new Label( "REP: ", skin )).padLeft(10);
                statTable.left().add( statRep ).row();

                equipTable.add( new Label( "", skin ) );
                equipTable.add( new Label( "", skin ) );
                equipTable.add( new Label ( "EQUIPPED", skin ) ).row();
                equipTable.left().add( new Image( new TextureRegion( new Texture( Gdx.files.internal( "ui/bar.png" ) ) ) ) ).fill(20, 1).padBottom(5).row();
                
                //Create Top Weapons Row
                equipTable.left().add( itemMelee );
                equipTable.left().add( new Label( "WEP", skin ) );
                equipTable.left().add( new Label( "weapon name", skin)).padLeft(10).row();
                equipTable.left().add( new Image( new TextureRegion( new Texture( Gdx.files.internal( "ui/bar.png" ) ) ) ) ).fill(20, 1).padBottom(5).row();

                //Create Top Armor Row
                equipTable.left().add( itemArmor );
                equipTable.left().add( new Label( "ARM", skin ) );
                equipTable.left().add( new Label("armor name", skin)).padLeft(10).row();
                
                charSp = new SplitPane(statTable, equipTable, true, skin);
                charSp.setSplitAmount((float) 0.5599);
                charSp.setMaxSplitAmount((float) 0.5599);
                charSp.setMinSplitAmount((float) 0.5598);

                //Generate the Main SplitPane
//                sp = new SplitPane(charSp, itemsPane, true, skin);
//                itemsPane.setFillParent(true);
//                sp.setSplitAmount((float) 0.5999);
//                sp.setMaxSplitAmount((float) 0.5999);
//                sp.setMinSplitAmount((float) 0.5998);
                itemsPane.setFlickScroll(false);


                inv.left().add(itemsPane);
                w.add(charSp);
        }
        
        public Window w() {
                return w;
        }
        
        public Window inv() {
                return inv;
        }
        
        private void updateItems()
        {
                itemsTable.clearChildren();
                itemsTable.left();
                for ( int i = 0; i < itemList.size(); i++ ) {
                        itemsTable.left().add( new Image( new TextureRegion( new Texture( Gdx.files.internal( "ui/item" + itemList.get(i).icon() + ".png" ) ) ) ) );
                        itemsTable.left().add( new Label( itemList.get(i).name(), skin ) ).padLeft(10).row();
                }
        }
        
        private void readItems(ArrayList<ItemLabel> aL)
        {
                itemList = aL;
        }
        
        private void updateStats(int hp, int atk, int def, int rep)
        {
                statHp  = new Label( Integer.toString(hp) , skin );
                statAtk = new Label( Integer.toString(atk), skin );
                statDef = new Label( Integer.toString(def), skin );
                statRep = new Label( Integer.toString(rep), skin );
        }
        
        private Image stringToImage(String s)
        {
                /*Unfortunately, strings aren't supported in switch statements
                as of this version.*/
                if (s.equals("ranged"))
                        return itemRanged;
                if (s.equals("melee"))
                        return itemMelee;
                if (s.equals("armor"))
                        return itemArmor;
                if (s.equals("consumable"))
                        return itemConsumable;
                Gdx.app.error("InventoryWindow: Unsupported Item Type", s);
                return itemConsumable;
        }
}

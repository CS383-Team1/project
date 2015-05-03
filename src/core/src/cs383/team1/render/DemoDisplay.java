package cs383.team1.render;

import java.util.Map;
import java.util.HashMap;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.graphics.OrthographicCamera;
import cs383.team1.Main;
import cs383.team1.model.GameManager;
import cs383.team1.model.overworld.Area;
import cs383.team1.model.overworld.Entity;
import cs383.team1.model.overworld.Tile;
import cs383.team1.model.overworld.DemoEntity;
import cs383.team1.model.overworld.Player;
import cs383.team1.render.Display;
import cs383.team1.input.DialogueBox;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class DemoDisplay extends Display {
	private static final String FNAME = "img/demo.png";

	private SpriteBatch batch;
	private Sprite sprite;
        
	private Map<Integer, String> tileSprites;
	private Map<Integer, Texture> tileTextures;
	private Map<Integer, String> entitySprites;
	private Map<Integer, Texture> entityTextures;
        DialogueBox chatBox;
        String fileName;
        private FreeTypeFontGenerator fontGen;
        BitmapFont font;
        
//        Stage stage;
//        Skin skin;
//        Window notice;
        public OrthographicCamera camera;
        
	private Texture getTileTexture(int i) {
		String fname;

		if(tileTextures.get(i) == null) {
			if((fname = tileSprites.get(i)) == null) {
				Gdx.app.error("DemoDisplay:getTileTexture",
				  "invalid id " + i);
				throw new IndexOutOfBoundsException();
			}

			Gdx.app.log("DemoDisplay:getTileTexture",
			  "loading texture " + fname);
			tileTextures.put(i, new Texture(Gdx.files.internal(fname)));
		}

		return tileTextures.get(i);
	}

	private Texture getEntityTexture(int i) {
		String fname;

		if(entityTextures.get(i) == null) {
			if((fname = entitySprites.get(i)) == null) {
				Gdx.app.error("DemoDisplay:getEntityTexture",
				  "invalid id " + i);
				throw new IndexOutOfBoundsException();
			}

			Gdx.app.log("DemoDisplay:getEntityTexture",
			  "loading texture " + fname);
			entityTextures.put(i, new Texture(Gdx.files.internal(fname)));
		}

		return entityTextures.get(i);
	}

	private void loadTileMaps() {
		int i;
		int index;
		int offset;
		int numEntities;
		String fname;
		String fcontents;
		String sprite;
		String[] vals;
		FileHandle fin;

		fname = "img/tiles.txt";
		offset = 0;

		Gdx.app.log("DemoDisplay:loadTileMaps", "Loading " + fname);

		fin = Gdx.files.internal(fname);
		fcontents = fin.readString();
		vals = fcontents.trim().split("\\s+");

		numEntities = Integer.parseInt(vals[offset++]);
		for(i = offset; i < offset + (numEntities * 2); i += 2) {
			index = Integer.parseInt(vals[i + 0]);
			sprite = vals[i + 1];

			tileSprites.put(index, sprite);
		}
		offset += numEntities * 2;
	}

	private void loadEntityMaps() {
		int i;
		int index;
		int offset;
		int numEntities;
		String fname;
		String fcontents;
		String sprite;
		String[] vals;
		FileHandle fin;

		fname = "img/entities.txt";
		offset = 0;

		Gdx.app.log("DemoDisplay:loadEntityMaps", "Loading " + fname);

		fin = Gdx.files.internal(fname);
		fcontents = fin.readString();
		vals = fcontents.trim().split("\\s+");

		numEntities = Integer.parseInt(vals[offset++]);
		for(i = offset; i < offset + (numEntities * 2); i += 2) {
			index = Integer.parseInt(vals[i + 0]);
			sprite = vals[i + 1];

			entitySprites.put(index, sprite);
		}
		offset += numEntities * 2;
	}

	private void loadSpriteMaps() {
		loadTileMaps();
		loadEntityMaps();
	}

	public DemoDisplay() {
            
		Gdx.app.debug("DemoDisplay:DemoDisplay", "intantiating class");
		batch = new SpriteBatch();
                font = new BitmapFont();
		tileSprites = new HashMap<Integer, String>();
		tileTextures = new HashMap<Integer, Texture>();
		entitySprites = new HashMap<Integer, String>();
		entityTextures = new HashMap<Integer, Texture>();
                fileName = "fonts/VCR_OSD_MONO_1.001.ttf";
		fontGen = new FreeTypeFontGenerator(Gdx.files.internal(fileName));
                font = fontGen.generateFont(20);
                chatBox = new DialogueBox();
		loadSpriteMaps();
	}

	public void dispose() {
		Gdx.app.debug("DemoDisplay:dispose", "disposing textures");

		for(Map.Entry item : tileTextures.entrySet()) {
			((Texture) item.getValue()).dispose();
		}

		for(Map.Entry item : entityTextures.entrySet()) {
			((Texture) item.getValue()).dispose();
		}
	}

	public void render() {
		Area area = Main.gm.currentArea();
		Player player;

		Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
                batch.enableBlending();
		batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

		batch.begin();

		for(Entity e : area.tiles) {
			sprite = new Sprite(getTileTexture(e.type()));
			sprite.setPosition(e.pos().x * Tile.WIDTH, e.pos().y * Tile.HEIGHT);
			sprite.draw(batch);
		}

		for(Entity e : area.entities) {
			sprite = new Sprite(getEntityTexture(e.type()));
			sprite.setPosition(e.pos().x * Tile.WIDTH,
			  (e.pos().y * Tile.HEIGHT) + (int) (0.33 * Tile.HEIGHT));
			sprite.draw(batch);
                        
		}
		
		for(Player p : area.players) {
			sprite = new Sprite(getEntityTexture(p.aType()));
			sprite.setPosition(p.pos().x * Tile.WIDTH + (int) p.floatPos().x,
				(p.pos().y * Tile.HEIGHT) + (int) (0.33 * Tile.HEIGHT) + (int) p.floatPos().y);
			sprite.draw(batch);
	}

		//player = GameManager.instance.areas.current.player;
		player = Player.ownPlayer;
//                chatBox = GameManager.instance.chatBox;

		sprite = new Sprite(getEntityTexture(player.aType()));
		sprite.setPosition(
                        player.pos().x * Tile.WIDTH + (int) player.floatPos().x,
		  (player.pos().y * Tile.HEIGHT) + (int) (0.33 * Tile.HEIGHT) + (int) player.floatPos().y);

                player.decFloatPos(2);
                camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
                camera.setToOrtho(false);
                camera.position.set(sprite.getX(), sprite.getY(), 0);
                camera.update();
                batch.setProjectionMatrix(camera.combined);
                
                drawChatBox(chatBox);
                
		sprite.draw(batch);
		batch.end();

	}
        
        public void drawChatBox(DialogueBox db)
        {
                //Draw dialogue text on to screen
                if(db.consumable()){
                    for(int i = 0; i < db.messages.size(); i++) {
                        /*The math below adjusts the chatBox to the player's
                        relative position so that it is drawn consistently in
                        the lower left-hand corner of the screen (well3112)*/
                        font.draw(batch, db.messages.get(i), 
                                (sprite.getX()-(Gdx.graphics.getWidth()/2)) +
                                        (1 * Tile.WIDTH),
                                (sprite.getY()-(Gdx.graphics.getHeight()/2)) +
                                        (1 * Tile.HEIGHT + (18 * (db.messages.size() - i))));
                    
                        //If more than 10 messages, delete oldest one
                        if(chatBox.messages.size() > 10){
                            db.removeMessage();
                        }
                    }
                }
        }
}

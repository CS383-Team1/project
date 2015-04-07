package cs383.team1.render;

import java.util.Map;
import java.util.HashMap;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import cs383.team1.model.GameManager;
import cs383.team1.model.overworld.Entity;
import cs383.team1.model.overworld.Tile;
import cs383.team1.model.overworld.DemoEntity;
import cs383.team1.model.overworld.Player;
import cs383.team1.render.Display;
import cs383.team1.input.DialogueBox;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

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
                fileName = "fonts/Marathon.ttf";
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
		Player player;

		Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
                batch.enableBlending();
		batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

		batch.begin();

		for(Entity e : GameManager.instance.areas.current.tiles) {
			sprite = new Sprite(getTileTexture(e.type()));
			sprite.setPosition(e.pos().x * Tile.WIDTH, e.pos().y * Tile.HEIGHT);
			sprite.draw(batch);
		}

		for(Entity e : GameManager.instance.areas.current.entities) {
			sprite = new Sprite(getEntityTexture(e.type()));
			sprite.setPosition(e.pos().x * Tile.WIDTH,
			  (e.pos().y * Tile.HEIGHT) + (int) (0.33 * Tile.HEIGHT));
			sprite.draw(batch);
                        
		}

		player = GameManager.instance.areas.current.player;
		sprite = new Sprite(getEntityTexture(player.type()));
		sprite.setPosition(player.pos().x * Tile.WIDTH,
		  (player.pos().y * Tile.HEIGHT) + (int) (0.33 * Tile.HEIGHT));
		sprite.draw(batch);
                
                //Draw dialogue text on to screen
                if(chatBox.consumable()){
                    for(int i = 0; i < chatBox.messages.size(); i++) {
                        font.draw(batch, chatBox.messages.get(i), 1 * Tile.WIDTH, 1 * Tile.HEIGHT + (15 * i));
		
                    
                        //If more than 10 messages, delete oldest one
                        if(chatBox.messages.size() > 10){
                            chatBox.removeMessage();
                        }
                    }
                }
		batch.end();
	}
}

package com.mygdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame implements ApplicationListener {
    private SpriteBatch batch;
    private Texture craftTexture;
    private Sprite craftSprite;
    Craft character = new Craft();
    
    private final int CRAFT_SIZE = 20;

    
    @Override
    public void create() {        
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        
        batch = new SpriteBatch();
        craftTexture = new Texture(Gdx.files.internal("craft.png"));
        craftSprite = new Sprite(craftTexture);
        craftSprite.setPosition(w/2 -craftSprite.getWidth()/2, h/2 - craftSprite.getHeight()/2);
    }

    @Override
    public void dispose() {
        batch.dispose();
        craftTexture.dispose();
    }

    @Override
    public void render() {        
        Gdx.gl.glClearColor(128.0f / 255.0f, 128.0f / 255.0f, 128.0f / 255.0f, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
                craftSprite.translateX(-3.0f);
                character.directionFacing = "left";
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
                craftSprite.translateX(3.0f);
                character.directionFacing = "right";
        }
        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
                craftSprite.translateY(3.0f);
                character.directionFacing = "up";
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
                craftSprite.translateY(-3.0f);
                character.directionFacing = "down";
        }
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
                //character.fire();
        }

        
        batch.begin();
        craftSprite.draw(batch);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }
}
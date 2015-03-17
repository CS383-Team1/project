package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class MyGdxGame extends Game {
    public SpriteBatch batch;
    public BitmapFont font;
    
    
    @Override
    public void create() {        
        
        batch = new SpriteBatch();
        font = new BitmapFont();
        this.setScreen(new MainMenu(this));
    }

    @Override
    public void dispose() {
        batch.dispose();
        //craftTexture.dispose();
        font.dispose();
    }

    @Override
    public void render() {        
     super.render();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
        //
        
    }

    @Override
    public void resume() {
    }
}
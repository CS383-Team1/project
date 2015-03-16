/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.Screen;

/**
 *
 * @author Casey
 */
public class Board implements Screen{
    MyGdxGame game;
    OrthographicCamera camera;
    Texture bucketImage;
    Rectangle bucket;
    public SpriteBatch batch;
    public BitmapFont font;
    
    
    private Texture characterTexture;
    private Sprite characterSprite;
    Character character = new Character();
    private final int CHARACTER_SIZE = 20;
    
    
    public Board(final MyGdxGame game){
        this.game = game;
        
        //craftTexture = new Texture(Gdx.files.internal("craft.png"));
    
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        
                
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        
        batch = new SpriteBatch();
        font = new BitmapFont();
        characterTexture = new Texture(Gdx.files.internal("character.png"));
        
        //-----------------------------------------------------------
        
        characterSprite = new Sprite(characterTexture);
        characterSprite.setPosition(w/2 -characterSprite.getWidth()/2, h/2 - characterSprite.getHeight()/2);
    }
    
    public void dispose() {
        batch.dispose();
        characterTexture.dispose();
        font.dispose();
    }

  
    public void render(float delta) {        
        Gdx.gl.glClearColor(128.0f / 255.0f, 128.0f / 255.0f, 128.0f / 255.0f, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
                characterSprite.translateX(-3.0f);
                character.directionFacing = "left";
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
                characterSprite.translateX(3.0f);
                character.directionFacing = "right";
        }
        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
                characterSprite.translateY(3.0f);
                character.directionFacing = "up";
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
                characterSprite.translateY(-3.0f);
                character.directionFacing = "down";
        }
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
                //character.fire();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            pause();
        
        }

        
        batch.begin();
        characterSprite.draw(batch);
        batch.end();
    }

   
    public void resize(int width, int height) {
    }

     @Override
         public void hide(){
             
         }         
         @Override
         public void resume(){
             
         } 
         @Override
         public void pause(){
             
         }     
        
             
         
                @Override
         public void show(){
             
         }
    
}
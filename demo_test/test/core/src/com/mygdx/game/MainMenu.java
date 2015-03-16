/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import static com.badlogic.gdx.graphics.Color.WHITE;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

/**
 *
 * @author Casey
 */
public class MainMenu implements Screen {

       MyGdxGame game;
       
       OrthographicCamera camera;
       Texture background = new Texture(Gdx.files.internal("background.jpg")); ;
       TextButton startButton;
       TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
       
       public MainMenu(final MyGdxGame gameOne){
          game = gameOne;
          style.font = new BitmapFont();
          style.fontColor = WHITE;
          camera = new OrthographicCamera();
          camera.setToOrtho(false, 500, 400);
          startButton = new TextButton("Start", style);
          
       }

     
       public void render(float delta){
           Gdx.gl.glClearColor(0, 0, 0.2f, 1);
           Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
           
           camera.update();
           game.batch.setProjectionMatrix(camera.combined);
           game.batch.begin();
           game.batch.draw(background, 0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
           //startButton.draw(game.batch, style, 100, 150);
           game.font.draw(game.batch, "^Teamname", 100, 150);
           game.font.draw(game.batch, "Click to begin!", 100, 100);
           game.batch.end();
           
           if(Gdx.input.isTouched()){
               game.setScreen(new Board(game));
               dispose();
           }
           
           
       }
       
         @Override
         public void dispose(){
             
         
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
         public void resize(int x, int y){
             
         }
                @Override
         public void show(){
             
         }
}

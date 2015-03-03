/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import java.awt.Image;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class Missile {

    private float x, y;
    private Image image;
    boolean visible;
    String direction;

    private SpriteBatch batch;
    private Texture texture;
    private Sprite sprite;
    float w = Gdx.graphics.getWidth();
    float h = Gdx.graphics.getHeight();

    
    private final int screenWidth = 900;
    private final int screenHeight = 500;
    private final int missileSpeed = 2;

    public Missile(float x, float y, String direction) {
        //ImageIcon missileImage;
        if(direction == "left"){
            texture = new Texture(Gdx.files.internal("missileLeft.png"));
            sprite = new Sprite(texture);
            sprite.setPosition(w/2 -sprite.getWidth()/2, h/2 - sprite.getHeight()/2);    
        }
        if(direction == "right"){
            texture = new Texture(Gdx.files.internal("missileRight.png"));
            sprite = new Sprite(texture);
            sprite.setPosition(w/2 -sprite.getWidth()/2, h/2 - sprite.getHeight()/2);    
        }
        if(direction == "up"){
            texture = new Texture(Gdx.files.internal("missileUp.png"));
            sprite = new Sprite(texture);
            sprite.setPosition(w/2 -sprite.getWidth()/2, h/2 - sprite.getHeight()/2);    
        }
        if(direction == "down"){
            texture = new Texture(Gdx.files.internal("missileDown.png"));
            sprite = new Sprite(texture);
            sprite.setPosition(w/2 -sprite.getWidth()/2, h/2 - sprite.getHeight()/2);    
        }
        
        
        //ImageIcon missileImage = new ImageIcon(this.getClass().getResource("images/missile.jpg"));
        //image = missileImage.getImage();
        visible = true;
        this.x = x;
        this.y = y;
        this.direction = direction;
    }
public void create() {        
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
          
}
/*
public void move(Sprite sprite) {
                
        if(direction == "left"){
                sprite.translateX(-10.0f);
        }
        if(direction == "right"){
                sprite.translateX(10.0f);
        }
        if(direction == "up"){
                sprite.translateY(10.0f);
        }
        if(direction == "down"){
                sprite.translateY(-10.0f);
        }
        batch.begin();
        sprite.draw(batch);
        batch.end();
    }

*/
    public Texture getImage() {
        return texture;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public boolean isVisible() {
        return visible;
    }

    
    public void move() {
        if(direction == "left"){
            x -= missileSpeed;
        }
        if(direction == "right"){
                x += missileSpeed;
        }
        if(direction == "up"){
                y -= missileSpeed;
        }
        if(direction == "down"){
                y += missileSpeed;
        }
       
       
        //If missile moves to end of screen, then make invisible
        if (x > screenWidth)
            visible = false;
        if (x < 0)
            visible = false;
        if (y > screenHeight)
            visible = false;
        if (y < 0)
            visible = false;
    }
    
}
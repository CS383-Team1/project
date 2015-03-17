/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Character extends ApplicationAdapter {
	
	Texture img;
        

    
    private int x;
    private int y;
    String directionFacing = "left";
    
    
    private final int CHARACTER_SIZE = 20;

    public Character() {
        
        x = 40;
        y = 60;
    }
    
    public Texture getTexture() {
        return img;
    }
    
}
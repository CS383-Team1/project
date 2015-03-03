/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package space;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Craft {

    private String craft = "images/craft.jpg";

    private int deltaX;
    private int deltaY;
    private int x;
    private int y;
    String directionFacing = new String("left");
    private Image image;
    int width = 150;
    int height = 155;
    
    private ArrayList firedMissiles;

    private final int CRAFT_SIZE = 20;

    public Craft() {
        ImageIcon ship = new ImageIcon(this.getClass().getResource(craft));
        image = ship.getImage();
        firedMissiles = new ArrayList();
        x = 40;
        y = 60;
    }


    public void move() {
        x += deltaX;
        y += deltaY;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    //If missile has been fired, then add missile object to firedMissiles ArrayList
    public void fire() {
        firedMissiles.add(new Missile(x + CRAFT_SIZE + 65, y + CRAFT_SIZE * 3, directionFacing));
    }

    public Image getImage() {
        return image;
    }
    
    public ArrayList getMissiles() {
        return firedMissiles;
    }

    //Check to see if keys were pressed
    public void keyPressed(KeyEvent a) {

        int key = a.getKeyCode();

        if (key == KeyEvent.VK_SPACE) {
            fire();
        }

        if (key == KeyEvent.VK_LEFT) {
            deltaX = -3;
            directionFacing = "left";
        }

        if (key == KeyEvent.VK_RIGHT) {
            deltaX = 3;
            directionFacing = "right";
        }

        if (key == KeyEvent.VK_UP) {
            deltaY = -3;
            directionFacing = "up";
        }

        if (key == KeyEvent.VK_DOWN) {
            deltaY = 3;
            directionFacing = "down";
        }
    }

    //Check to see if keys were released
    public void keyReleased(KeyEvent a) {
        int key = a.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            deltaX = 0;
            
        }

        if (key == KeyEvent.VK_RIGHT) {
            deltaX = 0;
            
        }

        if (key == KeyEvent.VK_UP) {
            deltaY = 0;
       
        }

        if (key == KeyEvent.VK_DOWN) {
            deltaY = 0;
       
        }
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package space;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Missile {

    private int x, y;
    private Image image;
    boolean visible;
    String direction;

    private final int screenWidth = 900;
    private final int screenHeight = 500;
    private final int missileSpeed = 8;

    public Missile(int x, int y, String direction) {
        //ImageIcon missileImage;
        if(direction == "left"){
            ImageIcon missileImage = missileImage = new ImageIcon(this.getClass().getResource("images/missileLeft.jpg"));
            image = missileImage.getImage();
        }
        if(direction == "right"){
            ImageIcon missileImage = missileImage = new ImageIcon(this.getClass().getResource("images/missileRight.jpg"));
            image = missileImage.getImage();
        }
        if(direction == "up"){
            ImageIcon missileImage = missileImage = new ImageIcon(this.getClass().getResource("images/missileUp.jpg"));
            image = missileImage.getImage();
        }
        if(direction == "down"){
            ImageIcon missileImage = missileImage = new ImageIcon(this.getClass().getResource("images/missileDown.jpg"));
            image = missileImage.getImage();
        }
        
        
        //ImageIcon missileImage = new ImageIcon(this.getClass().getResource("images/missile.jpg"));
        //image = missileImage.getImage();
        visible = true;
        this.x = x;
        this.y = y;
        this.direction = direction;
    }


    public Image getImage() {
        return image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
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
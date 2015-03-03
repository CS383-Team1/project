/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package space;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;


public class Board extends JPanel implements ActionListener {

    private Timer timer;
    private Craft craft;

    public Board() {

        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.gray);
        setDoubleBuffered(true);

        craft = new Craft();

        timer = new Timer(5, this);
        timer.start();
    }


    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D)g;
        g2d.drawImage(craft.getImage(), craft.getX(), craft.getY(), this);
        
        ArrayList firedMissiles = craft.getMissiles();

        for(int i = 0; i < firedMissiles.size(); i++ ) {
            //Get missile objects from array list if they have been added
            Missile missile = (Missile) firedMissiles.get(i);
            //Draw missiles on screen where current coorinates are
            g2d.drawImage(missile.getImage(), missile.getX(), missile.getY(), this);
        }

        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }


    public void actionPerformed(ActionEvent e) {
        ArrayList missileList = craft.getMissiles();

        for (int i = 0; i < missileList.size(); i++) {
            Missile missile = (Missile) missileList.get(i);
            //If missile is still on the screen, then move it. If not, then remove it from the list
            if (missile.isVisible()) 
                missile.move();
            else missileList.remove(i);
        }

        //Move the ship, then redraw the screen
        craft.move();
        repaint();  
    }


    private class TAdapter extends KeyAdapter {

        public void keyReleased(KeyEvent e) {
            craft.keyReleased(e);
        }

        public void keyPressed(KeyEvent e) {
            craft.keyPressed(e);
        }
    }

}
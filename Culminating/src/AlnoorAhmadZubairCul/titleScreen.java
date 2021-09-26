/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AlnoorAhmadZubairCul;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
/**
 * Holds the title screen of the game
 */
public class titleScreen extends Game implements MouseListener{

    JLabel title;
    Rectangle game;
    
    //Creates the title screen window
    public titleScreen() {
        super("Start Page", 800, 600);
        addMouseListener(this);
        
    }

    //Displays the game's title along with the Play Game option on the screen
    public void paint(Graphics brush) {
        brush.setColor(Color.black);
        brush.fillRect(0, 0, width, height);
        
        brush.setColor(Color.white);
        brush.setFont(new Font("Arial", Font.BOLD, 90));
        brush.drawString("Asteroids", 200, 200);
        
        brush.setFont(new Font("Arial", Font.BOLD, 40));
        brush.setColor(Color.GREEN);
        brush.drawString("Play Game", 300, 350);
        
        game = new Rectangle(300, 320, 200, 30);
        
    }

    //Checks if the Play Game option is clicked and launches the game if it is
    public void mouseClicked(MouseEvent me) {
        if(game.contains(me.getPoint())){
            new Asteroids();
            frame.setVisible(false);
            frame.dispose();
        }
    }

    public void mousePressed(MouseEvent me) {
    }

    public void mouseReleased(MouseEvent me) {
    }

    public void mouseEntered(MouseEvent me) {
    }

    public void mouseExited(MouseEvent me) {
    }
    
}

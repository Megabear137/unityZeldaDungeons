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
import javax.swing.JLabel;

/**
 * This class holds the game over screen which is shown when the player runs out of lives
 */
public class gameOver extends Game implements MouseListener {

    Rectangle PlayAgain, Exit;
    int intScore;
    
    //Creates the game over screen
    public gameOver(int intScore) {
        super("Game Over", 800, 600);
        this.intScore = intScore;
        addMouseListener(this);
    }

    //Displays a game over message, the player's score, along with an option to play again or exit
    public void paint(Graphics brush) {
        brush.setColor(Color.black);
        brush.fillRect(0, 0, width, height);

        brush.setColor(Color.red);
        brush.setFont(new Font("Arial", Font.BOLD, 90));
        brush.drawString("Game Over", 175, 175);

        brush.setColor(Color.white);
        brush.setFont(new Font("Arial", Font.BOLD, 50));
        brush.drawString("Score: " + intScore, 250, 275);
        PlayAgain = new Rectangle(290, 270, 100, 35);
        
        brush.setColor(Color.green);
        brush.setFont(new Font("Arial", Font.BOLD, 40));
        brush.drawString("Play Again", 290, 350);
        PlayAgain = new Rectangle(290, 320, 200, 35);

        brush.setColor(Color.red);
        brush.setFont(new Font("Arial", Font.BOLD, 40));
        brush.drawString("Exit", 350, 450);
        Exit = new Rectangle(350, 415, 70, 30);
    }

    //Checks to see if either the play again option or exit option is clicked, and will either run the game again or ecit it.
    public void mouseClicked(MouseEvent me) {
        if (PlayAgain.contains(me.getPoint())) {
            new Asteroids();
            frame.dispose();
        }
        else if(Exit.contains(me.getPoint())){
            System.exit(1);
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

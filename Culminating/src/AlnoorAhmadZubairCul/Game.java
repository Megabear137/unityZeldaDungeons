/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AlnoorAhmadZubairCul;

/**
 *
 * @author 202432
 */
/*
CLASS: Game
DESCRIPTION: A painted canvas in its own window, updated every tenth second.
USAGE: Extended by Asteroids.
NOTE: You don't need to understand the details here, no fiddling neccessary.
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

abstract class Game extends Canvas {

    protected boolean on = true;
    protected int width, height;
    protected Image buffer;
    JFrame frame;
    
    public Game(String name, int inWidth, int inHeight) {
        width = inWidth;
        height = inHeight;

        // Frame can be read as 'window' here.
        
        frame = new JFrame(name);
        frame.add(this);
        frame.setSize(width, height);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setFocusable(on); 
        frame.requestFocus();
        frame.setLocationRelativeTo(null);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        buffer = createImage(width, height);
    }

    // 'paint' will be called every tenth of a second that the game is on.
    abstract public void paint(Graphics brush);

    // 'update' paints to a buffer then to the screen, then waits a tenth of
    // a second before repeating itself, assuming the game is on. This is done
    // to avoid a choppy painting experience if repainted in pieces.
    public void update(Graphics brush) {
        paint(buffer.getGraphics());
        brush.drawImage(buffer, 0, 0, this);
        if (on) {
            sleep(10);
            repaint();
        }
    }

    // 'sleep' is a simple helper function used in 'update'.
    private void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (Exception exc) {
        };
    }
}

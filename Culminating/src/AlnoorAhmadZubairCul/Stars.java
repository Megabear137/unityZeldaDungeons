/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AlnoorAhmadZubairCul;

import java.awt.Graphics;

/**
 * This class draws the circles which makes the stars in the background of the game screen
 */
public class Stars extends Circle {

    
    public Stars(Point origin, int intRadius) {
        super(origin,intRadius);
    }
    
    //Paints the stars to the screen
    public void paint(Graphics brush) {
        brush.drawOval((int)origin.x, (int)origin.y, intRadius, intRadius);
    }

}

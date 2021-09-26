/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AlnoorAhmadZubairCul;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 * Creates and explosion animation whenever the ship or an asteroid breaks. Each explosion consists of 4 circles going in 
 * an x direction 
 */
public class Explosion extends Circle{
    
    //The origin of the bullet
    Point origin;
    
    //A counter used to remove the circles after a little while
    int intCount;
    
    //The velocity of the bullet
    double dblVelX, dblVelY;
    
    //The array list holding all of the explosion
    ArrayList<Explosion> explosions;
    
    public Explosion(Point origin, int intRadius, ArrayList<Explosion> explosions,double VelX, double VelY) {
        //Initializes all of the values
        super(origin, intRadius);
        this.origin = origin;
        dblVelX = VelX;
        dblVelY = VelY;
        this.explosions = explosions;
    }
    
    //Paints the explosion object
    public void paint(Graphics brush) {
            brush.setColor(Color.white);
            origin.x += dblVelX;
            origin.y += dblVelY;
            brush.fillOval((int) origin.x, (int) origin.y, intRadius, intRadius);
            intCount++;
            if(intCount == 30)
               explosions.remove(this);
    }
    
}

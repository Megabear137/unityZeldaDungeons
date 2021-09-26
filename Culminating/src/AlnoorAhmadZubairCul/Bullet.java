/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AlnoorAhmadZubairCul;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 * This class holds the bullet objects of the game
 */
public class Bullet extends Circle {

    //Stores the origin and radius of the bullet
    Point origin;
    int intRadius;

    //Holds the velocity of the bullet
    double dblVelX;
    double dblVelY;

    //Checks whether the bullet should be painted or not
    boolean shot = false;
    
    //Holds the asteroids and bullet objects
    ArrayList<Asteroid> asteroids;
    ArrayList<Bullet> bullets;

    //Holds a rectangle which encompases the borders of the bullet
    Rectangle bulletBounds;

    //Holds the main game instance of Asteroids
    Asteroids a1;

    /**
     * 
     * @param a1 the main game instance of Asteroids
     * @param origin the origin of the bullet
     * @param intRadius the bullet's radius
     * @param orientation the ship's orientation/angle when the bullet was fired
     * @param asteroids holds the array of asteroid objects
     * @param bullets holds the array of bullet objects
     */
    public Bullet(Asteroids a1, Point origin, int intRadius, int orientation, ArrayList<Asteroid> asteroids, ArrayList<Bullet> bullets) {
        //Initializes all of the values.
        super(origin, intRadius);
        this.origin = origin;
        this.intRadius = intRadius;
        setVelocity(orientation);
        this.asteroids = asteroids;
        this.bullets = bullets;
        this.a1 = a1;
        shot = true;
    }

    /**
     * Paints the bullet objects
     * @param brush the paint which paints the screen
     */
    public void paint(Graphics brush) {
        if (shot) {
            brush.setColor(Color.white);
            origin.x += dblVelX * 10;
            origin.y += dblVelY * 10;
            brush.drawOval((int) origin.x, (int) origin.y, intRadius, intRadius);
            bulletBounds = new Rectangle((int) origin.x, (int) origin.y, intRadius, intRadius);
            //Checks if the bullet has hit anything
            hit();
            
            //Checks if the bullet has gone offscreen
            border();
        }
    }

    /**
     * If the bullet intersects an asteroid, the asteroid is destroyed and the bullet also disappears
     */
    public void hit() {
        for (int i = 0; i < asteroids.size(); i++) {
            if (asteroids.get(i).path.intersects(bulletBounds)) {
                //Increases the score 
                if (asteroids.get(i).size.equals("large")) {
                    a1.intScore += 25;
                } else if (asteroids.get(i).size.equals("medium")) {
                    a1.intScore += 50;
                } else {
                    a1.intScore += 100;
                }
                //Deletes the bullet
                bullets.remove(this);
                Point origin = asteroids.get(i).findCenter();
                //Creates an explosion originating from the asteroid
                asteroids.get(i).burst(asteroids);
                a1.explosions.add(new Explosion(new Point(origin.x + 1, origin.y + 1), 5, a1.explosions, 1, 1));
                a1.explosions.add(new Explosion(new Point(origin.x + 1, origin.y + 1), 5, a1.explosions, 1, -1));
                a1.explosions.add(new Explosion(new Point(origin.x - 1, origin.y + 1), 5, a1.explosions, -1, 1));
                a1.explosions.add(new Explosion(new Point(origin.x - 1, origin.y - 1), 5, a1.explosions, -1, -1));
            }
        }
    }

    //If the bullet goes offscreen, it is removed from the array list of bullets
    public void border() {
        if (this.bulletBounds.x > 800) {
            bullets.remove(this);
        }
        if (this.bulletBounds.x < 0) {
            bullets.remove(this);
        }
        if (this.bulletBounds.y > 600) {
            bullets.remove(this);
        }
        if (this.bulletBounds.y < 0) {
            bullets.remove(this);
        }
    }

    //Sets the velocity of the bullet depending on the angle of the ship so that it goes directly in front of the ship
    public void setVelocity(int orientation) {

        double distance = 10;

        if (orientation == 0) {
            dblVelY = -0.9;
        } else if (orientation == 90) {
            dblVelX = 0.9;
        } else if (orientation == 180) {
            dblVelY = 0.9;
        } else if (orientation == 270) {
            dblVelX = -0.9;
        } else if (orientation < 90) {
            double dblY = Math.abs(distance * Math.sin(Math.toRadians(90 - orientation))) * 0.1;
            double dblX = Math.abs(distance * Math.cos(Math.toRadians(90 - orientation))) * 0.1;

            dblVelX = dblX / 2;
            dblVelY = -dblY / 2;
        } else if (orientation < 180) {
            double dblX = Math.abs(distance * Math.sin(Math.toRadians(180 - orientation))) * 0.1;
            double dblY = Math.abs(distance * Math.cos(Math.toRadians(180 - orientation))) * 0.1;

            dblVelX = dblX / 2;
            dblVelY = dblY / 2;
        } else if (orientation < 270) {
            double dblX = Math.abs(distance * Math.sin(Math.toRadians(180 - orientation))) * 0.1;
            double dblY = Math.abs(distance * Math.cos(Math.toRadians(180 - orientation))) * 0.1;

            dblVelX = -dblX / 2;
            dblVelY = dblY / 2;
        } else if (orientation < 360) {
            double dblX = Math.abs(distance * Math.sin(Math.toRadians(180 - orientation))) * 0.1;
            double dblY = Math.abs(distance * Math.cos(Math.toRadians(180 - orientation))) * 0.1;

            dblVelX = -dblX / 2;
            dblVelY = -dblY / 2;
        }
    }

}

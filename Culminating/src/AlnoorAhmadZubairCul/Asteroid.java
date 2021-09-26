/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AlnoorAhmadZubairCul;

import java.awt.*;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class hold the asteroid objects of the game
 */
public class Asteroid extends Polygon {

    //Creates a new Path object which will be used to draw the asteroid
    Path2D path = new Path2D.Double();

    //Stores the velocity of the asteroid
    double dblVelX, dblVelY;
    
    //Stores a rectangle which encompasses the entire asteroid. This is used when checking for collisions
    Rectangle asteroidBounds;
    
    //Hold the size of the asteroid; large, medium, or small
    String size;

    //Stores the Asteroids game instance to access values from it
    Asteroids a1;

    /**
     * 
     * @param inShape a Point array which holds the coordinates of the asteroid
     * @param VelX the velocity in the x direction
     * @param VelY the velocity in the y direction
     * @param size the size of the asteroid
     * @param a1 the instance of the Asteroids game
     */
    public Asteroid(Point[] inShape, double VelX, double VelY, String size, Asteroids a1) {
        super(inShape);
        //Initializes all the values
        dblVelX = VelX;
        dblVelY = VelY;
        this.size = size;
        this.a1 = a1;
        
        //If the asteroid spawns where the ship is supposed to spawn, the asteroid is prevented from spawning
        try{
        if(this.path.intersects(a1.s1.spawnArea)){
            a1.asteroids.remove(this);
        }
        }catch(NullPointerException n){}
    }

    /**
     * Paints the ship object
     * @param brush the brush which paints the ship
     */
    public void paint(Graphics brush) {

        //Resets the drawing of the ship
        path.reset();
        
        //Checks if the ship has passed the border on either side of the screen
        border();
        
        //Moves the ship depending on the velocity
        move();
        
        //Sets the brush color to white
        brush.setColor(Color.white);
        Graphics2D brush2D = (Graphics2D) brush;

        //Draws a series of lines between each point of the asteroid's coordinates to draw the shape
        path.moveTo(shape[0].x, shape[0].y);
        for (int i = 0; i < shape.length; i++) {
            path.lineTo(this.shape[i].x, this.shape[i].y);
        }
        
        //Gets the bounds of the ship shape
        asteroidBounds = path.getBounds();
        path.closePath();

        //Draws the shape to the game screen
        brush2D.draw(path);
    }

    /**
     * Breaks the asteroid into 2 parts or makes it disappear when it collides with a ship or bullet
     * @param asteroids the array of asteroid objects
     */
    public void burst(ArrayList<Asteroid> asteroids) {

        //Creates 2 new asteroid objects which originate from the center of the previous asteroid and removes
        //the current asteroid from the array of asteroids
        Point origin;
        origin = this.findCenter();

        Point[] points = new Point[shape.length];
        Point[] point1 = new Point[shape.length];
        Point[] point2 = new Point[shape.length];

        for (int i = 0; i < points.length; i++) {
            points[i] = new Point(0, 0);
            point1[i] = new Point(0, 0);
            point2[i] = new Point(0, 0);
        }

        if (size.equals("large")) {

            for (int i = 0; i < shape.length; i++) {
                points[i].x = shape[i].x / 1.5;
                points[i].y = shape[i].y / 1.5;
            }

            for (int i = 0; i < point1.length; i++) {
                point1[i].x = points[i].x + origin.x / 3;
                point1[i].y = points[i].y + origin.y / 3;
                point2[i].x = points[i].x + origin.x / 3;
                point2[i].y = points[i].y + origin.y / 3;
            }
            
            asteroids.add(new Asteroid(point1, Math.random() * 2 - 1, Math.random() * 2 - 1, "medium", a1));
            asteroids.add(new Asteroid(point2, Math.random() * 2 - 1, Math.random() * 2 - 1, "medium", a1));
            asteroids.remove(this);

        } else if (size.equals("medium")) {
            for (int i = 0; i < shape.length; i++) {
                points[i].x = shape[i].x / 1.5;
                points[i].y = shape[i].y / 1.5;
            }

            for (int i = 0; i < point1.length; i++) {
                point1[i].x = points[i].x + origin.x / 3;
                point1[i].y = points[i].y + origin.y / 3;
                point2[i].x = points[i].x + origin.x / 3;
                point2[i].y = points[i].y + origin.y / 3;
            }

            asteroids.add(new Asteroid(point1, Math.random() * 2 - 1, Math.random() * 2 - 1, "small", a1));
            asteroids.add(new Asteroid(point2, Math.random() * 2 - 1, Math.random() * 2 - 1, "small", a1));
            asteroids.remove(this);
        } else {
            asteroids.remove(this);
        }

    }

    //Moves the asteroid
    public void move() {
        for (int i = 0; i < shape.length; i++) {
            shape[i].x += dblVelX;
            shape[i].y += dblVelY;
        }
    }

    /**
     * Checks if the asteroid has gone past the border on any side of the game screen
     */
    public void border() {

        int intCheck = 0;

        //If every point of the asteroid has passed the border of the game screen, each of its points
        //are oriented so that it goes back to being on the game screen
        for (int i = 0; i < shape.length; i++) {
            if (shape[i].x > 800) {
                intCheck++;
            }
        }
        if (intCheck == shape.length) {
            for (int i = 0; i < shape.length; i++) {
                shape[i].x -= 850;

            }
        }

        intCheck = 0;
        for (int i = 0; i < shape.length; i++) {
            if (shape[i].y > 600) {
                intCheck++;
            }
        }
        if (intCheck == shape.length) {
            for (int i = 0; i < shape.length; i++) {
                shape[i].y -= 650;

            }
        }

        intCheck = 0;
        for (int i = 0; i < shape.length; i++) {
            if (shape[i].x < 0) {
                intCheck++;
            }
        }
        if (intCheck == shape.length) {
            for (int i = 0; i < shape.length; i++) {
                shape[i].x += 850;

            }
        }

        intCheck = 0;
        for (int i = 0; i < shape.length; i++) {
            if (shape[i].y < 0) {
                intCheck++;
            }
        }
        if (intCheck == shape.length) {
            for (int i = 0; i < shape.length; i++) {
                shape[i].y += 650;

            }
        }
    }

}

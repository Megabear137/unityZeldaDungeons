/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AlnoorAhmadZubairCul;

import java.awt.*;
import java.awt.geom.Path2D;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class Ship extends Polygon {

    //Holds the starting position of the ship
    Point[] originalPosition = {new Point(400, 290), new Point(390, 325), new Point(400, 315), new Point(410, 325)};

    //Stores the velocity of the ship
    double dblVelX;
    double dblVelY;

    //Checks if the forward button is being pressed
    boolean forwardPressed = false;

    //Creates the path object which will draw the ship
    Path2D path = new Path2D.Double();

    //Holds the bounds of the ship along with the ship's spawn area
    Rectangle shipBounds;
    Rectangle spawnArea;

    public Ship(Point[] inShape) {
        //Initialzes the values
        super(inShape);
        spawnArea = new Rectangle(275, 375, 50, 50);
    }

    //Draws the ship onto the game screen
    public void paint(Graphics brush) {
        path.reset();

        //Rotates the ship
        rotate(this);

        //Checks if the ship has gone offscreen
        border();

        //Moves the ship
        move();

        brush.setColor(Color.white);
        Graphics2D brush2D = (Graphics2D) brush;

        //Creates lines from each point on the ship to the other one to create the shape
        path.moveTo(shape[0].x, shape[0].y);
        for (int i = 0; i < shape.length; i++) {
            path.lineTo(shape[i].x, shape[i].y);
        }
        //Gets the bounds of the ship to be used for checking collisions
        shipBounds = path.getBounds();
        path.closePath();

        //draws the ship to the screen
        brush2D.draw(path);
    }

    //Resets the position of the ship if it collides with an asteroid
    public void burst() {
        for (int i = 0; i < shape.length; i++) {
            shape[i].x = originalPosition[i].x;
            shape[i].y = originalPosition[i].y;
        }
        orientation = 0;
        dblVelX = 0;
        dblVelY = 0;

    }

    //Checks if the ship has gone offscreen and puts it back onto the screen
    public void border() {
        if (shape[1].x > 800) {
            for (int i = 0; i < shape.length; i++) {
                shape[i].x -= 800;
            }
        }
        if (shape[1].x < 0) {
            for (int i = 0; i < shape.length; i++) {
                shape[i].x += 800;
            }
        }
        if (shape[1].y > 600) {
            for (int i = 0; i < shape.length; i++) {
                shape[i].y -= 600;
            }
        }
        if (shape[1].y < 0) {
            for (int i = 0; i < shape.length; i++) {
                shape[i].y += 600;
            }
        }
    }

    //Moves the ship depending on the velocity
    public void move() {
        for (int i = 0; i < shape.length; i++) {
            shape[i].x += dblVelX * 2;
        }
        for (int i = 0; i < shape.length; i++) {
            shape[i].y += dblVelY * 2;

        }
    }

    //Moves the ship forward
    public void forward() {

        Point center = findCenter();
        double distance = Math.sqrt((Math.pow(center.x - shape[0].x, 2)) + Math.pow(center.y - shape[0].y, 2)) + 1;

        //Takes the orientation of the ship and calculates the velocity x and velocity y needed to make the ship continue moving
        //forward at that angle
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

    //Makes the ship move slowly instead of stopping when the w key is released to imitate zero gravity
    public void forwardStop() {
        dblVelX /= 2;
        dblVelY /= 2;
    }

}

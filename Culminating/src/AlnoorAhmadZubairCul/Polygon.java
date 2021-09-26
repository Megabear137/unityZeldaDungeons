/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AlnoorAhmadZubairCul;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Holds the different polygons in the game
 */
abstract class Polygon{

    //The coordinates of the polygon
    Point[] shape;
    
    //The angle of the ship
    int orientation = 0;
    
    //A variable for checking which direction the ship needs to rotate
    int intDegree;

    public Polygon(Point[] inShape) {
        //Initializes the values
        shape = inShape;
    }

    //Makes sure the orientation stays between 0 and 360
    public void setOrient(int degree) {
        orientation = degree;
        if (orientation > 360) {
            orientation -= 360;
        }
        if (orientation < 0) {
            orientation += 360;
        }
        if (orientation == 360) {
            orientation = 0;
        }
    }

    //Rotates the ship depending on the intDegree variable
    public void rotate(Ship s1) {
        if (intDegree == 1) {
            rotation(3, s1);
            setOrient(orientation + 3);
        } else if (intDegree == -1) {
            rotation(-3, s1);
            setOrient(orientation - 3);
        } else {
            rotation(0, s1);
        }
    }

    //Rotates the polygon a certain amount of degrees.
    public void rotation(int degrees, Ship s1) {
        Point center = findCenter();
        Point[] points = new Point[shape.length];
        int i = 0;
        for (Point p : s1.shape) {
            double x = ((p.x - center.x) * Math.cos(Math.toRadians(degrees)))
                    - ((p.y - center.y) * Math.sin(Math.toRadians(degrees)))
                    + center.x;

            double y = ((p.x - center.x) * Math.sin(Math.toRadians(degrees)))
                    + ((p.y - center.y) * Math.cos(Math.toRadians(degrees)))
                    + center.y;

            points[i] = new Point(x, y);
            i++;

            if (s1.forwardPressed) {
                s1.forward();
            }
        }

        for (int j = 0; j < points.length; j++) {
            shape[j].x = points[j].x;
            shape[j].y = points[j].y;
        }
    }

    //Some math used for calculations
    public Point findCenter() {
        Point sum = new Point(0, 0);
        for (int i = 0, j = 1; i < shape.length; i++, j = (j + 1) % shape.length) {
            sum.x += (shape[i].x + shape[j].x)
                    * (shape[i].x * shape[j].y - shape[j].x * shape[i].y);
            sum.y += (shape[i].y + shape[j].y)
                    * (shape[i].x * shape[j].y - shape[j].x * shape[i].y);
        }
        double area = findArea();
        return new Point(Math.abs(sum.x / (6 * area)), Math.abs(sum.y / (6 * area)));
    }

    private double findArea() {
        double sum = 0;
        for (int i = 0, j = 1; i < shape.length; i++, j = (j + 1) % shape.length) {
            sum += shape[i].x * shape[j].y - shape[j].x * shape[i].y;
        }
        return Math.abs(sum / 2);
    }
}

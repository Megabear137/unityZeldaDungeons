/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AlnoorAhmadZubairCul;

/**
 * This abstract class holds the information needed to make a circle
 */
abstract class Circle{

    Point origin;
    int intRadius;

    public Circle(Point origin, int intRadius) {
        this.origin = origin;
        this.intRadius = intRadius;
    }
}

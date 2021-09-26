/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AlnoorAhmadZubairCul;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import javax.sound.sampled.*;

/**
 * Culminating.java
 * Ahmad Zubair Alnoor
 * This is the main class of the Asteroids game.
 * The controls are as follows:
 *  - W to move forward
 *  - A to rotate to the left
 *  - D to rotate to the right
 *  - Space to shoot. A maximum of 6 bullets can be shot at once
 *  - Escape to close program
 * 
 * NOTE: The window must be clicked for focus before the controls begin to work
 * 
 * Each asteroid has 3 versions of it: A large version, a medium version, and a small version. The default version is large. When shot, an asteroid breaks into
 * two smaller parts, and if it is already the smallest version, it disappears. The large version rewards 25 points, the medium rewards
 * 50 points, and the smallest rewards 100 points. When all the asteroids have been destroyed, new ones spawn. The game ends when the player dies 
 * 3 times. The goal is to get the highest score.
 * 
 * The structure of this code is not the same as the sample code provided. Methods from the sample code, such as FindCenter() and FindArea() are used, but the 
 * majority of the structure is made from scratch. The class hierarchy is mostly the same. However, there is no shape class and there are some extra 
 * classes added such as explosions, which is a subclass of the circle class and acts as an animation for the asteroid exploding.
 * 
 * The code starts off by making a new Window called "Start Page", which displays the title "Asteroids", and has an option to start playing.
 * Once the play option is clicked, the game starts.
 * 
 */
public class Asteroids extends Game implements KeyListener {

    //These point arrays store the default coordinates of the Ship and the 2 different types of asteroids
    Point[] shipPoints = {new Point(400, 290), new Point(390, 325), new Point(400, 315), new Point(410, 325)};
    Point[] asteroidPoints1 = {new Point(20, 21), new Point(30, 15), new Point(45, 21), new Point(67, 17), new Point(88, 20), new Point(90, 27), new Point(80, 50), new Point(91, 61), new Point(83, 90), new Point(37, 87), new Point(20, 65), new Point(29, 50)};
    Point[] asteroidPoints2 = {new Point(6, 19), new Point(45, 19), new Point(25, 0), new Point(45, 0), new Point(90, 22), new Point(50, 40), new Point(90, 61), new Point(70, 80), new Point(48, 70), new Point(10, 80), new Point(0, 40)};
   
    //Creates the instance of ship which will be used throughout the game
    Ship s1;

    //Stores the player's score
    int intScore;
    
    //Stores the number of lives the player has
    int intLives;
    
    //Declares the different ArrayLists which will hold the game objects (such as bullets, asteroids, and stars)
    ArrayList<Bullet> bullets;
    ArrayList<Asteroid> asteroids;
    ArrayList<Stars> stars;
    ArrayList<Explosion> explosions;

    //Declares a random object to be used for randomizing coordinates
    Random r = new Random();
    
    //Opens up the start page of the game
    public static void main(String[] args) {
        new titleScreen();
    }
    
    /**
     * Creates the game screen
     */
    public Asteroids() {
        //Creates a window named Asteroids with the dimensions 800 by 600
        super("Asteroids!", 800, 600);
        
        //Initialzies the different instances and ArrayLists
        s1 = new Ship(shipPoints);
        asteroids = new ArrayList<>();
        bullets = new ArrayList<>();
        explosions = new ArrayList<>();
        stars = new ArrayList();
        
        //Creates 50 stars in the background of the game
        for (int i = 0; i < 50; i++) {
            stars.add(new Stars(new Point(r.nextInt(800 - 1) + 1,r.nextInt(600 - 1) + 1),r.nextInt(5)));
        }
        
        //Spawns the asteroids into the game
        spawnAsteroids();
        
        //Adds a key listener to this game
        addKeyListener(this);
        
        //Sets the number of lives to 3
        intLives = 3;
        
        //Sets the initial score to 0
        intScore = 0;

    }

    /**
     * Spawns the asteroids into the game
     */
    public void spawnAsteroids() {
        
        //This makes a 2 dimensional array which will hold 4 slightly different versions of the same shaped asteroid. While their shapes and
        //sizes are the same, their position on the game screen are different. Their specific position on the game screen is based
        //on a randomly generated number. Once the 4 different asteroid coordinates are made, they are used to make 4 different asteroid
        //objects. This is done twice with a different version of the coordinates to make a total of 8 different asteroid objects.
        
        Point[][] asteroidPointsA = new Point[4][asteroidPoints1.length];
        Point origin = new Point(0, 0);

        for (int i = 0; i < asteroidPointsA.length; i++) {
            origin.x = Math.random() * 800;
            origin.y = Math.random() * 600;
            //This if statement makes sure the asteroids do not spawn in the same place as the ship
            if(origin.x > 300 && origin.x < 400 && origin.y > 200 && origin.y < 300){
                origin.x = Math.random() * 800;
                origin.y = Math.random() * 600;
            }
            for (int j = 0; j < asteroidPointsA[0].length; j++) {
                asteroidPointsA[i][j] = new Point(asteroidPoints1[j].x + origin.x, asteroidPoints1[j].y + origin.y);
            }
        }
        
        //This for loop adds the asteroid objects to the astroid object array list
        for (int i = 0; i < asteroidPointsA.length; i++) {
            asteroids.add(new Asteroid(asteroidPointsA[i], Math.random() * 2 - 1, Math.random() * 2 - 1, "large", this));
        }

        Point[][] asteroidPointsB = new Point[4][asteroidPoints2.length];

        for (int i = 0; i < asteroidPointsB.length; i++) {
            origin.x = Math.random() * 800;
            origin.y = Math.random() * 600;
            if(origin.x > 300 && origin.x < 400 && origin.y > 200 && origin.y < 300){
                origin.x = Math.random() * 800;
                origin.y = Math.random() * 600;
            }
            for (int j = 0; j < asteroidPointsB[0].length; j++) {
                asteroidPointsB[i][j] = new Point(asteroidPoints2[j].x + origin.x, asteroidPoints2[j].y + origin.y);
            }
        }

        for (int i = 0; i < asteroidPointsB.length; i++) {
            asteroids.add(new Asteroid(asteroidPointsB[i], Math.random() * 2 - 1, Math.random() * 2 - 1, "large", this));
        }

    }

    /**
     * This paint method is called every tenth of a second to paint the parts of the game screen
     * @param brush takes the brush needed to paint the screen
     */
    public void paint(Graphics brush) {
        //Paints the black background
        brush.setColor(Color.black);
        brush.fillRect(0, 0, width, height);

        //Creates a 2D brush
        Graphics2D brush2D = (Graphics2D) brush;
        brush2D.drawRect(0, 0, width, height);
        
        //This try and catch block is used to prevent any possible NullPointerExceptions that may be caused by an object being
        //referenced before being initialized
        try {
            //Paints the ship
            s1.paint(brush);
            
            //Goes through the asteroid array and paints each asteroid object
            for (int i = 0; i < asteroids.size(); i++) {
                asteroids.get(i).paint(brush);
            }
            
            //Goes through the bullet array and paints each bullet
            for (int i = 0; i < bullets.size(); i++) {
                bullets.get(i).paint(brush);
            }
            
            //Draws any explosion objects
            for (int i = 0; i < explosions.size(); i++) {
                explosions.get(i).paint(brush);
            }
            
            //Paints all the stars
            for (int i = 0; i < stars.size(); i++) {
                stars.get(i).paint(brush);
            }
            
            //If all the asteroids are destroyed, a new set of asteroids are spawned
            if (asteroids.size() == 0) {
                spawnAsteroids();
            }
        } catch (NullPointerException n) {
        };

        //Draws the score to the game screen
        brush.setFont(new Font("Arial", Font.BOLD, 25));
        brush.setColor(Color.green);
        brush.drawString(intScore + "", 1, 25);
        
        //Draws the player's lives to the screen
        brush.setColor(Color.white);
        brush.drawString("Lives: " + intLives, 1, 51);
        
        //If the user runs out of lives, the game screen is closed and the game over screen is pulled up
        if(intLives <= 0){
            new gameOver(intScore);
            frame.dispose();
        }
        
        //Calls the collision method which checks for any collisions between ships and asteroids
        collision();
        
        //Repaints the screen
        repaint();
    }

    /**
     * Checks for collisions between the ship and asteroids
     */
    public void collision() {
        try {
            //Goes through the asteroids array and checks if it has collided with the ship
            for (int i = 0; i < asteroids.size(); i++) {
                if (s1.path.intersects(asteroids.get(i).asteroidBounds)) {
                    //Creates an explosion whcih originates from the ship
                    Point origin = s1.findCenter();
                    explosions.add(new Explosion(new Point(origin.x + 1, origin.y + 1), 5, explosions, 1, 1));
                    explosions.add(new Explosion(new Point(origin.x + 1, origin.y + 1), 5, explosions, 1, -1));
                    explosions.add(new Explosion(new Point(origin.x - 1, origin.y + 1), 5, explosions, -1, 1));
                    explosions.add(new Explosion(new Point(origin.x - 1, origin.y - 1), 5, explosions, -1, -1));
                    
                    //Calls the burst method in the ship class to reset the ship's position
                    s1.burst();
                    
                    //Takes away one life
                    intLives--;
                    
                    //Increases the score depending on the size of the asteroid
                    if (asteroids.get(i).size.equals("large")) {
                        intScore += 25;
                    } else if (asteroids.get(i).size.equals("medium")) {
                        intScore += 50;
                    } else {
                        intScore += 100;
                    }
                    
                    origin = asteroids.get(i).findCenter();
                    
                    //Calls the burst method in the asteroids class so that the asteroid either breaks apart or disappears
                    asteroids.get(i).burst(asteroids);
                    
                    //Creates an explosion originating from the asteroid
                    explosions.add(new Explosion(new Point(origin.x + 1, origin.y + 1), 5, explosions, 1, 1));
                    explosions.add(new Explosion(new Point(origin.x + 1, origin.y + 1), 5, explosions, 1, -1));
                    explosions.add(new Explosion(new Point(origin.x - 1, origin.y + 1), 5, explosions, -1, 1));
                    explosions.add(new Explosion(new Point(origin.x - 1, origin.y - 1), 5, explosions, -1, -1));
                }

            }
        } catch (NullPointerException n) {
        }
    }

    /**
     * Checks which key has been pressed to execute the right command
     * @param ke the key event
     */
    public void keyPressed(KeyEvent ke) {
        int key = ke.getKeyCode();
        
        //Makes the ship move forward
        if (key == KeyEvent.VK_W) {
            s1.forwardPressed = true;
            s1.forward();
        }
        
        //Sets the intDegree variable in the Ship class to 1 which will make it rotate to the right
        if (key == KeyEvent.VK_D) {
            s1.intDegree = 1;
        }
        
        //Sets the intDegree variable in the Ship class to -1 which will make it rotate to the left
        if (key == KeyEvent.VK_A) {
            s1.intDegree = -1;
        }
        
        //Creates a new bullet object which is shot whenever space is pressed
        if (key == KeyEvent.VK_SPACE) {
            if (bullets.size() < 6) {
                bullets.add(new Bullet(this, new Point(s1.shape[0].x, s1.shape[0].y), 3, s1.orientation, asteroids, bullets));
            }
        }
        
        //Closes the screen when escape is pressed
        if (key == KeyEvent.VK_ESCAPE) {
            System.exit(1);
        }

    }

    public void keyReleased(KeyEvent ke) {
        int key = ke.getKeyCode();
        //Stops the ship from moving forward
        if (key == KeyEvent.VK_W) {
            s1.forwardPressed = false;
            s1.forwardStop();
            
        //Makes the ship rotating
        } else if (key == KeyEvent.VK_D) {
            s1.intDegree = 0;
        } else if (key == KeyEvent.VK_A) {
            s1.intDegree = 0;
        }
    }

    public void keyTyped(KeyEvent ke) {
    }

}

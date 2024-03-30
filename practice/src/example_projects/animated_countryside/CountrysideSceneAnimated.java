package example_projects.animated_countryside;

import java.awt.Color;

import uwcse.graphics.GWindow;
import uwcse.graphics.GWindowEvent;
import uwcse.graphics.GWindowEventAdapter;
import uwcse.graphics.Rectangle;

/**
 * CSC 142 homework 2
 *
 * A CountrysideScene object displays apple trees, cars, mountains, and a fourth
 * element of your choice in a graphics window.
 *
 * Add a line to declare an instance field of type the new type that you defined
 * in hw1, then complete the method addGraphicsElements. Leave all the rest of
 * the code unchanged.
 *
 * @author Manyi Zhao
 *
 * Reqirements
 * * CountrysideScene - I added all the lines that were needed to complete the class and changed some of the element
 *                      sizes and coordinates.
 * * Apple class - I created a ripen method that changes the color of the apples between red green and yellow.
 * * Car class - I created a move method that drives back and forth in a straight line
 * * Mountain class - I created a meltSnow method that deletes and adds a smaller snow triangle for at least 2 cycles
 * * My Element - I added a doAction method that makes the cloud(s) slowly shink/expand and move right
 *
 */

public class CountrysideSceneAnimated extends GWindowEventAdapter {

    // The graphics window that displays the picture
    private GWindow window;

    // The elements in the picture
    // 2 cars that move back and forth
    private CarAnimated car1, car2;
    // 3 mountains with receding snow cover
    private MountainAnimated mountain1, mountain2, mountain3;
    // 3 apple trees whose apples change colors
    private AppleTreeAnimated tree1, tree2, tree3;

    // Add here the declaration of an instance field of the type
    // that you created in hw1
    // YOU MUST NAME THIS VARIABLE: myElement.
    // Thus your statement should be (replacing
    // classname with the name of your class)
    // private classname myElement; (e.g. private Moon myElement if your fourth
    // element is a Moon).
    private Clouds myElement;

    // To keep track of the duration of the animation
    private int animationCounter;

    /**
     * Creates a countryside scene
     */
    public CountrysideSceneAnimated() {
        // The graphics window
        // the dimensions of the window is 500 by 400 pixels.
        window = new GWindow("Countryside scene");
        this.window.setExitOnClose();

        // The ground
        Color groundColor = new Color(152, 251, 152); // pale green color
        // to select color, use for example
        // https://www.rapidtables.com/web/color/index.html
        Rectangle ground = new Rectangle(0, 0, window.getWindowWidth(), window.getWindowHeight(), groundColor, true);
        this.window.add(ground);

        // the sky covers the upper quarter of the window
        Color skyColor = new Color(135, 206, 250); // light sky blue
        Rectangle sky = new Rectangle(0, 0, window.getWindowWidth(), window.getWindowHeight() / 4, skyColor, true);
        this.window.add(sky);

        // Add the graphics elements
        this.addGraphicsElements();

        // Code to do the animation
        this.window.addEventHandler(this);
        this.window.startTimerEvents(150);
    }

    /**
     * One step of the animation
     */
    public void timerExpired(GWindowEvent we) {
        this.window.suspendRepaints();

        // Move the cars
        this.car1.move();
        this.car2.move();

        // Change the size of the snow on the mountains
        this.mountain1.meltSnow();
        this.mountain2.meltSnow();
        this.mountain3.meltSnow();

        // Change the colors of the apples on the trees
        this.tree1.ripen();
        this.tree2.ripen();
        this.tree3.ripen();

        // moves the clouds to the right
        this.myElement.doAction();

        this.window.resumeRepaints();

        // Run the animation 100 times (about 15 s)
        this.animationCounter++;
        if (this.animationCounter >= 100)
            this.window.stopTimerEvents();
    }

    /**
     * Instantiates in this method the elements of the scene.
     * This is the only method that you need to modify in this class
     */
    private void addGraphicsElements() {
        // You can change the coordinates and scales that appear
        // in the constructors (but don't change the names of the variables)

        this.car1 = new CarAnimated(90, 225, 0.5, CarAnimated.LEFT_MOVING, window);
        this.car2 = new CarAnimated(200, 250, 0.54, CarAnimated.RIGHT_MOVING, window);

        this.mountain1 = new MountainAnimated(50, 70, 0.9, this.window);
        this.mountain2 = new MountainAnimated(110, 5, 1.8, this.window);
        this.mountain3 = new MountainAnimated(250, 25, 1.5, this.window);

        this.tree1 = new AppleTreeAnimated(250, 90, .6, this.window);
        this.tree2 = new AppleTreeAnimated(380, 120, .5, this.window);
        this.tree3 = new AppleTreeAnimated(20, 220, .9, this.window);

        // Complete the line of code below to instantiate an object of the type
        // that you created in homework #1
        // this.myElement = new ???;
        this.myElement = new Clouds(150, 50, 1.2, this.window);
    }

    /**
     * Starts the application
     */
    public static void main(String[] args) {
        new CountrysideSceneAnimated();
    }

}
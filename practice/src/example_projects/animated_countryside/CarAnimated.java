package example_projects.animated_countryside;

import java.awt.*;
import java.util.ArrayList;

import uwcse.graphics.GWindow;
import uwcse.graphics.Oval;
import uwcse.graphics.Rectangle;
import uwcse.graphics.Shape;

public class CarAnimated {

    // Declare your instance fields here
    private int x, y, direction;
    private double scale;
    private GWindow window;

    // empty Shape arraylist that will be filled with parts when draw() is called
    private ArrayList<Shape> parts = new ArrayList<>();

    // constants that help determine which way the car will face
    public static final int RIGHT_MOVING = 1;
    public static final int LEFT_MOVING = 0;
    /**
     * Creates a Car
     *
     * @param x      the x coordinate of the car location
     * @param y      the y coordinate of the car location
     * @param scale  the scale of the drawing of the car
     * @param direction determines the direction the car moves when animated
     * @param window the graphics window that displays the car
     */
    public CarAnimated(int x, int y, double scale, int direction, GWindow window) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.scale = scale;
        this.window = window;

        // draw the car
        draw();
    }

    /**
     * Draws a car
     */
    private void draw() {
        // defines the base of the car
        int baseHeight = (int) (50 * scale), baseWidth = baseHeight * 4;
        int baseY = y + baseHeight;
        Rectangle carBase = new Rectangle(x, baseY, baseWidth, baseHeight, Color.RED, true);
        parts.add(carBase);

        // defines the top of the car
        int topHeight = (int) (75 * scale), topWidth = (int) (100 * scale);
        int topX = x + (int) (50 * scale);
        Rectangle carTop = new Rectangle(topX, y, topWidth, topHeight, Color.RED, true);
        parts.add(carTop);

        // add to window
        int winSize = (int) (40 * scale);
        int xWin1 = topX + (int) (5 * scale), xWin2 = xWin1 + winSize + (int) (10 * scale);
        int winY = y + (int) (5 * scale);
        Rectangle win1 = new Rectangle(xWin1, winY, winSize, winSize, Color.CYAN, true);
        Rectangle win2 = new Rectangle(xWin2, winY, winSize, winSize, Color.CYAN, true);
        parts.add(win1);
        parts.add(win2);

        // defines both wheels
        int xWheel1 = x + (int) (25 * scale), xWheel2 = x + (int) (125 * scale);
        int wheelY = y + (int) (75 * scale);
        int wheelSize = (int) (50 * scale);
        Oval wheel1 = new Oval(xWheel1, wheelY, wheelSize, wheelSize, Color.BLACK, true);
        Oval wheel2 = new Oval(xWheel2, wheelY, wheelSize, wheelSize, Color.BLACK, true);
        parts.add(wheel1);
        parts.add(wheel2);

        // defines the lights
        int lightX, lightY = y + (int) (60 * scale), lightSize = (int) (20 * scale);
        if (direction == LEFT_MOVING)
            lightX = x;
        else
            lightX = x + (int) (180 * scale);
        Oval light = new Oval(lightX, lightY, lightSize, lightSize, Color.YELLOW, true);
        parts.add(light);

        // adds all the parts to the window
        for (Shape carParts: parts)
            window.add(carParts);

    }

    //makes the car drive across the screen and turn when it reaches the edge of the screen
    public void move(){
        // determines how many pixels the car moves each frame
        int driveSpeed = 16;

        // this checks if the car will hit the edge of the window to change direction
        if (parts.get(0).getX() < driveSpeed || parts.get(0).getX() > 500 - (200 * scale + driveSpeed)){
            direction++;

            if (direction % 2 == LEFT_MOVING)
                parts.get(6).moveBy((int) (-180 * scale), 0);
            else
                parts.get(6).moveBy((int) (180 * scale), 0);
        }

        // checks if the car is facing left because moving left requires a negative number
        if (direction % 2 == LEFT_MOVING)
            driveSpeed = -driveSpeed;

        for (Shape carParts: parts)
            carParts.moveBy(driveSpeed, 0);

    }

}
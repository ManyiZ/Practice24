package example_projects.static_countryside;

import java.awt.Color;

import uwcse.graphics.GWindow;
import uwcse.graphics.Oval;
import uwcse.graphics.Rectangle;

public class Car {


    // Declare your instance fields here
    private int x, y;
    private double scale;
    private GWindow window;
    /**
     * Creates a Car
     *
     * @param x      the x coordinate of the car location
     * @param y      the y coordinate of the car location
     * @param scale  the scale of the drawing of the car
     * @param window the graphics window that displays the car
     */
    public Car(int x, int y, double scale, GWindow window) {
        this.x = x;
        this.y = y;
        this.scale = scale;
        this.window = window;
        // draw the car
        draw();
    }

    /**
     * Draws a car
     */
    private void draw() {
        // if its < 50 its left, if its > 50 its driving right
        int direction = (int) (Math.random() * 100);

        // defines the base of the car
        int baseHeight = (int) (50 * scale), baseWidth = baseHeight * 4;
        int baseY = y + baseHeight;
        Rectangle carBase = new Rectangle(x, baseY, baseWidth, baseHeight, Color.RED, true);

        // defines the top of the car
        int topHeight = (int) (75 * scale), topWidth = (int) (100 * scale);
        int topX = x + (int) (50 * scale);
        Rectangle carTop = new Rectangle(topX, y, topWidth, topHeight, Color.RED, true);

        // add to window
        int winSize = (int) (40 * scale);
        int xWin1 = topX + (int) (5 * scale), xWin2 = xWin1 + winSize + (int) (10 * scale);
        int winY = y + (int) (5 * scale);
        Rectangle win1 = new Rectangle(xWin1, winY, winSize, winSize, Color.CYAN, true);
        Rectangle win2 = new Rectangle(xWin2, winY, winSize, winSize, Color.CYAN, true);


        // defines both wheels
        int xWheel1 = x + (int) (25 * scale), xWheel2 = x + (int) (125 * scale);
        int wheelY = y + (int) (75 * scale);
        int wheelSize = (int) (50 * scale);
        Oval wheel1 = new Oval(xWheel1, wheelY, wheelSize, wheelSize, Color.BLACK, true);
        Oval wheel2 = new Oval(xWheel2, wheelY, wheelSize, wheelSize, Color.BLACK, true);

        // defines the lights
        int lightX, lightY = y + (int) (60 * scale), lightSize = (int) (20 * scale);
        if (direction < 50)
            lightX = x;
        else
            lightX = x + (int) (180 * scale);
        Oval light = new Oval(lightX, lightY, lightSize, lightSize, Color.YELLOW, true);

        // adds all the parts to the window
        window.add(carBase);
        window.add(carTop);
        window.add(win1);
        window.add(win2);
        window.add(wheel1);
        window.add(wheel2);
        window.add(light);
    }
}
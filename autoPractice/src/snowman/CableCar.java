package snowman;

import uwcse.graphics.*; // access the graphics utilities in the uw library
import java.awt.Color; // access the Color class


/**
 * <p>Create a cable car in a graphics window</p>  
 * @author your name here
 */

public class CableCar {

    // Your instance fields go here

    /**
     * Create a cable car at location (x,y) in the GWindow window.
     * @param x the x coordinate of the center of the cable car
     * @param y the y coordinate of the center of the cable car
     * @param scale the factor that multiplies all default dimensions for this cable car
     * (e.g. if the default size is 80, the size of this cable car is
     * scale * 80)
     * @param window the graphics window this cable car belongs to
     */
    public CableCar(int x, int y, double scale, GWindow window)
    {
        // initialize the instance fields

        // The details of the drawing are in a private method
        this.draw();
    }

    /** Draw a cable car at location (x,y) */
    private void draw()
    {
    }
}
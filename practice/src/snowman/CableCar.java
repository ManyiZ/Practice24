package snowman;

import uwcse.graphics.*; // access the graphics utilities in the uw library
import java.awt.Color; // access the Color class


/**
 * <p>Create a cable car in a graphics window</p>  
 * @author your name here
 */

public class CableCar {

    // Your instance fields go here
    int x;
    int y;
    double scale;
    GWindow window;
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
        this.x = x;
        this.y = y;
        this.scale = scale;
        this.window = window;
        // The details of the drawing are in a private method
        this.draw();
    }

    /** Draw a cable car at location (x,y) */
    private void draw()
    {
        Line cable = new Line(0, scaleY(-25),500,scaleY(-25));
        Rectangle hook = new Rectangle(scaleX(0), scaleY(-25),width(15), height(35), Color.black, true);
        Rectangle car = new Rectangle(scaleX(-75), scaleY(0), width(150), height(60), Color.blue, true);
        Rectangle window1 = new Rectangle(scaleX(-55), scaleY(15), width(30), height(30), Color.RED, true);
        Rectangle window2 = new Rectangle(scaleX(-15), scaleY(15), width(30), height(30), Color.red, true);
        Rectangle window3 = new Rectangle(scaleX(25), scaleY(15), width(30), height(30), Color.red, true);

        this.window.add(cable);
        this.window.add(hook);
        this.window.add(car);
        this.window.add(window1);
        this.window.add(window2);
        this.window.add(window3);
    }
    private int scaleX(int a){
        return (int) (this.x + a * (scale));
    }
    private int scaleY(int b){
        return (int) (this.x + b * (scale));
    }
    private int width(int  c){
        return (int) (c*this.scale);
    }
    private int height(int  d){
        return (int) (d*this.scale);
    }

    public void move() {
    }
}
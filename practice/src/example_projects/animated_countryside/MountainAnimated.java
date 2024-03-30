package example_projects.animated_countryside;

import java.awt.Color;

import uwcse.graphics.GWindow;
import uwcse.graphics.Triangle;

/**
 * A mountain in a graphics window
 */
public class MountainAnimated {
    // location of the car
    // (what point (x,y) is is up to you. Here, (x,y) is taken
    // to be the top of the mountain)
    private int x, y;
    // scale of the drawing
    private double scale;
    // graphics window that displays the mountain
    private GWindow window;

    private Triangle snow;
    private int snowCycle = ((int) (100 * scale)) / 3;
    /**
     * Draws a mountain at the given location with the given scale in the given
     * graphics window
     *
     * @param x      the x-coordinate of the location of the mountain
     * @param y      the y-coordinate of the location of the mountain
     * @param scale  the scale of the drawing the mountain
     * @param window the graphics window that displays the drawing of the mountain
     */
    public MountainAnimated(int x, int y, double scale, GWindow window) {
        this.x = x;
        this.y = y;
        this.scale = scale;
        this.window = window;

        // draw the mountain
        draw();
    }

    /**
     * Draws the mountain
     */
    private void draw() {
        // height of the mountain (100 by default)
        int h = (int) (100 * scale);
        // Mountain
        Triangle mountain = scaledTriangle(Color.GRAY, h);
        Triangle snow = scaledTriangle(Color.WHITE, h / 3);

        this.snow = snow;
        window.add(mountain);
        window.add(this.snow);
        // Add the snow on top of the mountain
    }

    // moved the given code into a method so that the same triangle can be easily scaled
    private Triangle scaledTriangle(Color color, int h){

        int x1 = x, y1 = y, x2 = x - h / 2, y2 = y + h, x3 = x + h / 2, y3 = y + h;
        Triangle triangle = new Triangle(x1, y1, x2, y2, x3, y3, color, true);

        return triangle;
    }

    // melts the snow by making the triangles progressively smaller
    public void meltSnow(){
        if (snowCycle > 0){
            int meltingSpeed = 1;
            snow.removeFromWindow();

            // new snow that is smaller to make it seem like it is melting
            snow = scaledTriangle(Color.WHITE, snowCycle);

            window.add(snow);

            snowCycle -= meltingSpeed;
        }else
            // if the snow is all melted, this restarts the cycle
            snowCycle = ((int) (100 * scale)) / 3;
    }

}
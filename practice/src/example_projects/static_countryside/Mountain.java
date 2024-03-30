package example_projects.static_countryside;

import java.awt.Color;

import uwcse.graphics.GWindow;
import uwcse.graphics.Triangle;

/**
 * A mountain in a graphics window
 */
public class Mountain {
    // location of the mountain
    // (what point (x,y) is is up to you. Here, (x,y) is taken
    // to be the top of the mountain)
    private int x, y;
    // scale of the drawing
    private double scale;
    // graphics window that displays the mountain
    private GWindow window;

    /**
     * Draws a mountain at the given location with the given scale in the given
     * graphics window
     *
     * @param x      the x-coordinate of the location of the mountain
     * @param y      the y-coordinate of the location of the mountain
     * @param scale  the scale of the drawing the mountain
     * @param window the graphics window that displays the drawing of the mountain
     */
    public Mountain(int x, int y, double scale, GWindow window) {
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

        window.add(mountain);
        window.add(snow);
        // Add the snow on top of the mountain
    }

    // moved the given code into a method so that the same triangle can be easily scaled
    private Triangle scaledTriangle(Color color, int h){

        int x1 = x, y1 = y, x2 = x - h / 2, y2 = y + h, x3 = x + h / 2, y3 = y + h;
        Triangle triangle = new Triangle(x1, y1, x2, y2, x3, y3, color, true);

        return triangle;
    }

}
package example_projects.animated_countryside;

import uwcse.graphics.Arc;
import uwcse.graphics.GWindow;
import uwcse.graphics.Shape;

import java.awt.*;
import java.util.ArrayList;

// TODO: pick something that could fit into a country side scene and program it
public class Clouds {  // Declare your instance fields here
    private int x, y;
    private double scale;
    private GWindow window;

    // makes the color of the cloud accessible to all methods
    private int gray = 235;
    private Color grayCloud = new Color(gray, gray, gray);

    private int waveCycle = 0;

    // empty arraylist that will be filled with parts of the cloud(s) when draw() is called
    private ArrayList<Shape> cloudParts = new ArrayList<>();

    /**
     * Creates cloud(s)
     *
     * @param x      the x coordinate of the cloud location
     * @param y      the bottom y coordinate of the cloud location
     * @param scale  the scale of the drawing of the cloud
     * @param window the graphics window that displays the cloud
     */

    public Clouds(int x, int y, double scale, GWindow window) {
        this.x = x;
        this.y = y;
        this.scale = scale;
        this.window = window;
        // draw the car
        draw();
    }
    private void draw() {
        // custom gray for contrast between clouds and mountains

        // determines the number of cloud puffs
        int puffNumber = (int) (Math.random() * 3) + 3;

        // generates the puffs of the cloud
        for (int i = 0; i < puffNumber; i++){
            int cloudX = x + (int) (1.5 * (i * Math.random() * 20 + 10) * scale);
            int yDifference = (int) ((i * Math.random() * 10 + 5) * scale);
            int cloudY = y - yDifference;
            int cloudWidth =  (int) ((4 * (Math.random() * 10 + 5)) * scale);
            int cloudHeight = y + yDifference + (int) (40 * scale) - cloudY;
            Arc cloud = new Arc(cloudX, cloudY, cloudWidth, cloudHeight, 0, 180, grayCloud, true);
            cloudParts.add(cloud);
            window.add(cloud);
        }
    }


    // moves the clouds to the right
    public void doAction(){
        // use cloudParts
        for (int i = 0; i < cloudParts.size(); i++){
            cloudParts.get(i).removeFromWindow();

            // variables to help calculate the new coordinates and sizes of the cloud - broken up for easy debugging
            double dif1 = 2, dif2 = -waveCycle / 70.0, dif3 = waveCycle / 5.0;
            int difX = (int) Math.round(dif1 * Math.pow(Math.E, dif2) * Math.cos(dif3) * Math.cos(dif3));
            int difY = (int) Math.round(dif1 * Math.pow(Math.E, dif2) * Math.sin(dif3) * Math.sin(dif3));
            int difMove = (int) (difX / (dif1 * Math.pow(Math.E, dif2)));

            // new cloud sizes and coordinates
            int newX = cloudParts.get(i).getX() + 2, newY = cloudParts.get(i).getY() + difMove;
            int newW = cloudParts.get(i).getWidth() - (int) (difX / Math.cos(dif3));
            int newH = cloudParts.get(i).getHeight() - difY;

            // new cloud to replace the cloud from the last frame
            Arc newCloud = new Arc(newX, newY, newW, newH, 0, 180, grayCloud, true);
            cloudParts.set(i, newCloud);
            window.add(cloudParts.get(i));
        }
        waveCycle++;
    }
}

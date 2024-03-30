package example_projects.static_countryside;

import uwcse.graphics.Arc;
import uwcse.graphics.GWindow;

import java.awt.*;

// TODO: pick something that could fit into a country side scene and program it
public class Clouds {  // Declare your instance fields here
    private int x, y;
    private double scale;
    private GWindow window;

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
        int gray = 235;
        Color grayCloud = new Color(gray, gray, gray);

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
            window.add(cloud);
        }
    }
}

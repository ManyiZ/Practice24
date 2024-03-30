package example_projects.static_countryside;

import java.awt.Color; //graphics library
import uwcse.graphics.*; // uw graphics library

/**
 * CSC 142 homework 1
 *
 * Create a landscape that features 4 different types of elements (3 of the
 * types must be a mountain, an apple tree, and a car)
 *
 * @author (Write your name here)
 */

public class CountrysideScene {
    /**
     * Creates a countryside scene
     */
    public CountrysideScene() {
        // The graphics window
        // the dimensions of the window is 500 by 400 pixels.
        GWindow window = new GWindow("Countryside scene");
        window.setExitOnClose();

        // The ground
        Color groundColor = new Color(152, 251, 152); // pale green color
        // to select color, use for example
        // https://www.rapidtables.com/web/color/index.html
        Rectangle ground = new Rectangle(0, 0, window.getWindowWidth(), window.getWindowHeight(), groundColor, true);
        window.add(ground);

        // the sky covers the upper quarter of the window
        Color skyColor = new Color(135, 206, 250); // light sky blue
        Rectangle sky = new Rectangle(0, 0, window.getWindowWidth(), window.getWindowHeight() / 3, skyColor, true);
        window.add(sky);

        // Draw the elements in the window
        // two mountains

        new Mountain(40, 50, 0.9, window);
        new Mountain(90, 0, 1.7, window);
        new Mountain(190, 60, 1.1, window);
        new Mountain(250, 25, 1.5, window);
        new Mountain(350, 35, 1.4, window);
        new Mountain(410, 15, 1.3, window);
        new Mountain(460, 25, 1.2, window);

        new Clouds(50, 80, 0.5, window);
        new Clouds(90, 35, 0.75, window);
        new Clouds(230, 50, 1, window);
        new Clouds(150, 100, 1.25, window);
        new Clouds(350, 80, 1.05, window);

        for (int i = 0; i < 10; i++){
            new AppleTree(i * 50, 115, .75, window);
        }

        new Car(90, 225, 0.5, window);
        new Car(200, 250, 0.54, window);
        new Car(300, 270, 0.59, window);
        new Car(350, 300, 0.65, window);
        new Car(20, 300, 0.65, window);

        // Show the scene
        window.doRepaint();
    }

    /**
     * Starts the application
     */
    public static void main(String[] args) {
        new CountrysideScene();
    }
}
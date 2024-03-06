package snowman;
import uwcse.graphics.*; // access the graphics utilities in the uw library
import java.awt.Color; // access the Color class

/**
 * <p>
 * A MountainScene displays snow men, trees (with ornaments), a cable car and a
 * fourth element of your choice in a graphics window
 * </p>
 *
 * @author Your name here
 */

public class MountainScene {

    /** The graphics window that displays the picture */
    private GWindow window;

    /**
     * Create an image of a mountain scene
     */
    public MountainScene() {

        // The graphics window
        // The window is by default 500 wide and 400 high
        this.window = new GWindow("Mountain scene");
        this.window.setExitOnClose(); // so that a click on the close box of the
        // window terminates the application

        // Background (cyan here)
        Rectangle bgnd = new Rectangle(0, 0, window.getWindowWidth(), window
                .getWindowHeight(), Color.cyan, true);
        this.window.add(bgnd);

        // Create the scene elements
        // e.g. a tree in the lower left area 1.5 times the normal size
        new Tree( (int) ((Math.random()) * 100 + 10), (int) ((Math.random()) * 300 + 10), 1.5, this.window);
        new SnowMan( (int) ((Math.random()) * 100 + 10), (int) ((Math.random()) * 300 + 10), ((Math.random()) * 2 + .3), this.window);
        new Tree( (int) ((Math.random()) * 300 + 200), (int) ((Math.random()) * 300 + 10), 1.5, this.window);
        new SnowMan( (int) ((Math.random()) * 400 + 200), (int) ((Math.random()) * 300 + 10), ((Math.random()) * 2 + .3), this.window);
        new Tree( (int) ((Math.random()) * 250 + 75), (int) ((Math.random()) * 300 + 10), 1.5, this.window);
        new SnowMan( (int) ((Math.random()) * 250 + 75), (int) ((Math.random()) * 300 + 10), ((Math.random()) * 2 + .3), this.window);
        new CableCar( (int) ((Math.random()) * 300 + 10),75, 1,this.window);
    }

    /**
     * Entry point of the program
     */
    public static void main(String[] args) {
        new MountainScene();
    }

}
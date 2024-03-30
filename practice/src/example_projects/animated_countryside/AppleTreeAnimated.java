package example_projects.animated_countryside;

import java.awt.*;
import java.util.ArrayList;

import uwcse.graphics.*;
import uwcse.graphics.Rectangle;

/**
 * A class to draw an apple tree
 */

public class AppleTreeAnimated {

    // Declare your instance fields here
    private int x, y;
    private double scale;
    private GWindow window;

    // empty array list that will be filled with apples when draw() is called
    private ArrayList<Oval> apples = new ArrayList<>();

    // an int counter that controls the color cycle of the apples
    private int colorCycle = 0;
    /**
     * Creates an apple tree
     *
     * @param x      the x coordinate of the tree location
     * @param y      the y coordinate of the tree location
     * @param scale  the scale of the drawing of the tree
     * @param window the graphics window that displays the tree
     */
    public AppleTreeAnimated(int x, int y, double scale, GWindow window) {
        this.x = x;
        this.y = y;
        this.scale = scale;
        this.window = window;
        // draw the apple tree
        drawTree();
    }

    /**
     * Draws an apple tree
     */
    private void drawTree() {
        // defines the coordinates and sizes of the tree trunk and then defines the rectangle for the tree trunk
        int trunkX = x + (int) (33 * scale), trunkY = y + (int) (50 * scale);
        int trunkWidth = (int) (40 * scale), trunkHeight = (int) (125 * scale);
        Color trunkBrown = new Color(110, 78, 30);
        Rectangle trunk = new Rectangle(trunkX, trunkY, trunkWidth, trunkHeight, trunkBrown, true);

        // defines the coordinates nad size of the oval used as the base of the tree leaves
        int baseX = x + (int) (10 * scale), baseY = y + (int) (scale * 10), leafBaseSize = (int) (80 * scale);
        Color leavesGreen = new Color(30, 110, 55);
        Oval leafBase = new Oval(baseX, baseY, leafBaseSize, leafBaseSize, leavesGreen, true);

        window.add(trunk);
        window.add(leafBase);

        // loop that generates extra leaves for the tree
        for (int i = 0; i < 9; i++){

            // makes the leaf sizes random around the tree
            int leafSize = (int) ((int) ((Math.random() * 10) + 30) * scale);

            // generates the coordinates for drawing leaves around the tree
            int xLeaves = x + (int) ((38 * scale) + (Math.cos(i * Math.PI / 4) * 40 * scale));
            int yLeaves = y + (int) ((38 * scale) + (Math.sin(i * Math.PI / 4) * 40 * scale));

            Oval leaves = new Oval(xLeaves, yLeaves, leafSize, leafSize, leavesGreen, true);
            window.add(leaves);
        }

        drawApples(baseX, baseY, leafBaseSize);
    }

    // randomly generates some apples on the leaves of the tree
    private void drawApples(int cornerX, int cornerY, int leafSize){
        for (int i = 0; i < 6; i++){
            int appleSize = 7 + (int) (Math.random() * 9 * scale);
            int xApple = cornerX + (int) (Math.random() * leafSize), yApple = cornerY + (int) (Math.random() * leafSize);

            Oval apple = new Oval(xApple, yApple, appleSize, appleSize, Color.RED, true);

            // adds the apples to the arraylist so that they can be accessed later
            apples.add(apple);
            window.add(apple);
        }
    }

    // changes the color of the apples in the arraylist based on the colorCycle counter
    public void ripen(){
        for (Oval apple: apples){
            if (colorCycle % 4 == 0)
                apple.setColor(Color.GREEN);
            else if (colorCycle % 4 == 1)
                apple.setColor(Color.yellow);
            else
                apple.setColor(Color.red);

        }
        colorCycle++;
    }
}
package example_projects.laggy_invaders;

import java.awt.Color;
import java.awt.Point;

import uwcse.graphics.GWindow;
import uwcse.graphics.Line;
import uwcse.graphics.Oval;
import uwcse.graphics.Rectangle;
import uwcse.graphics.Shape;

/**
 * The representation and display of an Alien
 */

public class Alien extends MovingObject {
    // Size of an Alien
    public static final int RADIUS = 5;

    private int lives;

    private int leftMax;
    private int rightMax;

    private int downCount;

    private int direction;

    private int score;

    private Color color;
    private final Color[] healthColors = {Color.red, new Color(200, 0, 150),
            new Color(150, 0, 200), new Color(100, 0, 255)};

    private boolean killedPlayer;
    /**
     * Creates an alien in the graphics window
     *
     * @param window the GWindow this Alien belongs to
     * @param center the center Point of this Alien
     * @param lives gives the health value of this Alien
     */

    public Alien(GWindow window, Point center, int lives) {
        super(window, center);
        this.color = healthColors[lives - 1];
        this.lives = lives;
        this.score = lives * 50;

        this.leftMax = this.center.x - 400;
        this.rightMax = this.center.x + 400 * 5;

        // Display this Alien
        this.draw();
    }

    /**
     * The alien is being shot. Decrement its number of lives and erase it from the
     * graphics window if it is dead.
     */
    public void isShot(int damage) {
        if (!isDead())
            lives -= damage;

        if (lives < 0)
            lives = 0;

        this.erase();
        if (lives > 0)
            this.color = (healthColors[lives - 1]);
        this.draw();
    }

    /**
     * Is this Alien dead?
     */
    public boolean isDead() {
        return this.lives <= 0;
    }

    public void killAlien(){
        this.lives = 0;
    }

    public boolean playerKilled(){
        return killedPlayer;
    }

    public int getScore(){
        return score;
    }

    public Item killDrop(){
        double drop = Math.random();
        boolean shield = drop < 1.0 / 30.0;
        boolean heal = drop > 8.0 / 15.0 && drop < 17.0 / 30.0;
        boolean powerUp = drop > 14.0 / 15.0 && drop < 29.0 / 30.0;

        if (heal){
            return new Heal(this.window, this.center);
        }else if (shield){
            return new Shield(this.window, this.center);
        }else if (powerUp){
            return new PowerUp(this.window, this.center);
        }else
            return new Item(this.window, new Point(-1, 0));
    }


    /**
     * Moves this Alien As a start make all of the aliens move downward. If an alien
     * reaches the bottom of the screen, it reappears at the top.
     */
    public void move() {
        this.erase();

        // move right
        if (this.direction % 4 == 0){
            this.center.x++;

        // move down
        } else if (this.direction % 4 == 1 || this.direction % 4 == 3) {
            this.center.y++;

        // move left
        }else {
            this.center.x--;
        }

        if (this.center.x - 10 == leftMax || this.center.x + 10 == rightMax || downCount == 50) {
            direction++;
            this.downCount = 0;
        }

        downCount++;

        this.draw();

        if (this.center.y > 500)
            killedPlayer = true;

    }

    /**
     * Displays this Alien in the graphics window
     */
    protected void draw() {
        // pick the color (according to the number of lives left)

        // Graphics elements for the display of this Alien
        // A circle on top of an X
        this.shapes = new Shape[3];
        this.shapes[0] = new Line(this.center.x - 2 * RADIUS, this.center.y - 2 * RADIUS, this.center.x + 2 * RADIUS,
                this.center.y + 2 * RADIUS, this.color);
        this.shapes[1] = new Line(this.center.x + 2 * RADIUS, this.center.y - 2 * RADIUS, this.center.x - 2 * RADIUS,
                this.center.y + 2 * RADIUS, this.color);
        this.shapes[2] = new Oval(this.center.x - RADIUS, this.center.y - RADIUS, 2 * RADIUS, 2 * RADIUS, this.color, true);

        for (int i = 0; i < this.shapes.length; i++)
            this.window.add(this.shapes[i]);

        // Bounding box of this Alien
        this.boundingBox = new Rectangle(this.center.x - 2 * RADIUS, this.center.y - 2 * RADIUS, 4 * RADIUS,
                4 * RADIUS);

        this.window.doRepaint();
    }
}
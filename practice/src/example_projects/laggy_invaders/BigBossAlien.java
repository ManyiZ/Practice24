package example_projects.laggy_invaders;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

import uwcse.graphics.*;

/**
 * The representation and display of an Alien
 */

public class BigBossAlien extends MovingObject {
    // Size of an Alien
    public static final int RADIUS = 50;

    // difficulty - for lives
    public static final int[] DIFFICULTY = {12, 24, 33};

    // health of the boss
    private int lives;

    // which phase the boss is in
    private int phase;

    // factor to divide health by to get phase
    private int phaseDivider;

    // phase timers for the boss to shoot
    private int phase1Timer;
    private int phase2Timer;
    private int phase3Timer;

    private int[] phasePause = new int[3];

    private int score;

    // scale the difficulty
    private double difficultyScale;

    private boolean direction;

    private Color color;

    private Color[] bossColor = {Color.white, Color.yellow, Color.red};


    /**
     * Creates an alien in the graphics window
     *
     * @param window the GWindow this Alien belongs to
     * @param lives the number of lives and difficulty the boss alien has
     */

    public BigBossAlien(GWindow window, int lives) {
        super(window, new Point(SpaceInvader.screenCenter, 0));
        this.score = lives * 100;
        this.lives = lives;
        this.phaseDivider = lives / (lives / 3);
        this.phase = (3 / phaseDivider);


        if (lives == DIFFICULTY[2])
            difficultyScale = .2;
        else if (lives == DIFFICULTY[1])
            difficultyScale = .5;
        else
            difficultyScale = 1;


        this.phasePause[0] = (int) (1000 * difficultyScale);
        this.phasePause[1] = (int) (300 * difficultyScale);
        this.phasePause[2] = (int) (100 * difficultyScale);
        this.color = bossColor[phase - 1];


        // Display this Alien
        this.draw();
    }

    /**
     * The alien is being shot. Decrement its number of lives and erase it from the
     * graphics window if it is dead.
     */
    public void isShot() {
        if (!isDead()) {
            lives--;

            this.phase = 3 - (lives / phaseDivider);
        }
    }

    /**
     * Checks if this alien is dead
     */
    public boolean isDead() {
        return this.lives <= 0;
    }

    /**
     *
     * @return score value of the boss
     */
    public int getScore(){
        return score;
    }

    /**
     *
     * @return phase the boss is currently in
     */
    public int getPhase(){
        return phase;
    }

    // runs phase timer to shoot at player
    public ArrayList<Bullet> phase1(){
        ArrayList<Bullet> temp = new ArrayList<Bullet>();
        if (phase1Timer % phasePause[0] == phasePause[0] / 5)
            for (int i = 0; i < 5; i++){
                temp.add(new Bullet(this.window, new Point(100 * i + 50, 50), -1));
            }
        phase1Timer++;
        return temp;
    }

    public ArrayList<Bullet> phase2(){
        ArrayList<Bullet> temp = new ArrayList<Bullet>();
        if (phase2Timer % phasePause[1] == phasePause[1] / 5) {
            if (Math.random() < .5) {
                for (int i = 0; i < 5; i++) {
                    temp.add(new Bullet(this.window, new Point(100 * i + 50, 50), -2));
                }
            } else {
                for (int i = 0; i < 6; i++) {
                    temp.add(new Bullet(this.window, new Point(100 * i, 50), -2));
                }
            }
        }
        phase2Timer++;
        return temp;
    }

    public ArrayList<Bullet> phase3(){
        ArrayList<Bullet> temp = new ArrayList<Bullet>();
        if (phase3Timer % phasePause[2] == phasePause[2] / 5)
            for (int i = 0; i < 9; i++){
                temp.add(new Bullet(this.window, new Point(50 * i + 50, 50), -5));
            }
        phase3Timer++;
        return temp;
    }

    /**
     * Moves this Alien As a start make all of the aliens move downward. If an alien
     * reaches the bottom of the screen, it reappears at the top.
     */
    public void move() {
        this.erase();

        if (this.phase < 1)
            this.phase = 1;

        if (direction)
            this.center.x++;
        else
            this.center.x--;

        if (this.center.x - RADIUS <= 0 && !direction)
            direction = true;
        else if (this.center.x + RADIUS >= 500  && direction)
            direction = false;


        this.color = bossColor[this.phase - 1];
        this.draw();
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
                this.center.y + 2 * RADIUS, Color.WHITE);
        this.shapes[1] = new Line(this.center.x + 2 * RADIUS, this.center.y - 2 * RADIUS, this.center.x - 2 * RADIUS,
                this.center.y + 2 * RADIUS, Color.WHITE);
        this.shapes[2] = new Triangle(this.center.x - RADIUS, this.center.y, this.center.x + RADIUS, this.center.y,
                this.center.x, this.center.y + 2 * RADIUS, this.color, true);

        for (int i = 0; i < this.shapes.length; i++)
            this.window.add(this.shapes[i]);

        // Bounding box of this Alien
        this.boundingBox = new Rectangle(this.center.x - 2 * RADIUS, this.center.y - 2 * RADIUS, 2 * RADIUS,
                2 * RADIUS);

        this.window.doRepaint();
    }
}
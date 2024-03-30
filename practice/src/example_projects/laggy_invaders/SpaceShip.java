package example_projects.laggy_invaders;

import java.awt.Color;
import java.awt.Point;

import uwcse.graphics.*;

public class SpaceShip extends MovingObject {
    /** Height of a space ship */
    public static final int HEIGHT = 40;

    /** Width of a space ship */
    public static final int WIDTH = 20;

    // health of the player / ship
    private int health = 3;

    // if the player has a power up
    private boolean havePowerUp;

    // power up timer
    private int powerUpTimer;

    // if player has shield
    private boolean shield;

    // shield timer
    private int shieldTimer;

    /**
     * Constructs this SpaceShip
     */
    public SpaceShip(GWindow window, Point center) {
        super(window, center);
        this.direction = MovingObject.LEFT;

        // Draw this SpaceShip
        this.draw();
    }

    /**
     * Returns the spaceship's health
     * @return
     */
    public int getHealth(){
        return this.health;
    }

    /**
     * Returns the damage that the spaceship
     * does with its bullets
     * @return
     */
    public int getDamage(){
        if (havePowerUp && powerUpTimer < 101){
            powerUpTimer++;
            return 2;
        } else if (havePowerUp){
            havePowerUp = false;
            return 1;
        } else
            return 1;
    }

    /**
     * Gives the spaceship an effect based on
     * the item picked up
     * @param item
     */
    public void useItem(Item item){
        if (item.getItemType() == SpaceInvader.HEAL)
            useHeal();
        else if (item.getItemType() == SpaceInvader.SHIELD)
            getShield();
        else if (item.getItemType() == SpaceInvader.POWER_UP)
            getPowerUp();
    }

    /**
     * An effect given by an item.
     * Gives spaceship health
     */
    private void useHeal(){
        if (health < 10)
            health++;
    }

    /**
     * An effect given by an item.
     * Gives spaceship a shield
     * that lasts a certain duration
     */
    private void getShield(){
        this.shieldTimer = 0;
        this.shield = true;
    }

    /**
     * Times the shield by incrementing
     * a time counter. Removes shield when
     * time is up
     */
    private void runShieldTimer(){
        if (shield && shieldTimer > 200){
            shield = false;
        }

        shieldTimer++;
    }

    /**
     * An effect given by an item. Gives it
     * a damage boost. Initializes the timer
     * for the power up / damage boost item.
     */
    private void getPowerUp(){
        this.powerUpTimer = 0;
        this.havePowerUp = true;
    }

    /**
     * Removes the shield when spaceship is hit
     */
    public void isHit(){
        if (shield)
            shield = false;
        else
            this.health--;
    }

    /**
     * Moves this SpaceShip. The space ship should be constantly moving. Select a
     * new direction if the space ship can't move in the current direction of
     * motion.
     */
    public void move() {
        // A space ship moves left or right
        if (this.direction != MovingObject.LEFT && this.direction != MovingObject.RIGHT) {
            throw new IllegalArgumentException("Invalid space ship direction");
        }

        // move this SpaceShip
        boolean isMoveOK;
        // Distance covered by the space ship in one step
        int step = this.boundingBox.getWidth() / 2;

        do {
            int x = this.center.x;
            switch (this.direction) {
                case LEFT:
                    x -= step;
                    break;
                case RIGHT:
                    x += step;
                    break;
            }

            // Is the new position in the window?
            if (x + this.boundingBox.getWidth() / 2 >= this.window.getWindowWidth())
            // past the right edge
            {
                isMoveOK = false;
                this.direction = MovingObject.LEFT;
            } else if (x - this.boundingBox.getWidth() / 2 <= 0) // past the
            // left edge
            {
                isMoveOK = false;
                this.direction = MovingObject.RIGHT;
            } else // it is in the window
            {
                isMoveOK = true;
                this.center.x = x;
            }
        } while (!isMoveOK);

        // Show the new location of this SpaceShip
        this.erase();
        this.draw();
    }

    /**
     * Draws this SpaceShip in the graphics window
     */
    protected void draw() {

        this.shapes = new Shape[5];


        // Body of the space ship
        Rectangle body = new Rectangle(this.center.x - SpaceShip.WIDTH / 2, this.center.y - SpaceShip.HEIGHT / 2,
                SpaceShip.WIDTH, SpaceShip.HEIGHT, Color.cyan, true);

        this.shapes[0] = body;

        // Cone on top
        int x1 = body.getX();
        int y1 = body.getY();
        int x2 = x1 + body.getWidth();
        int y2 = y1;
        int x3 = body.getCenterX();
        int y3 = y1 - body.getWidth();
        this.shapes[1] = new Triangle(x1, y1, x2, y2, x3, y3, Color.pink, true);

        // Wings on the sides
        x1 = body.getX();
        y1 = body.getY() + body.getHeight();
        x2 = body.getX() - body.getWidth() / 2;
        y2 = y1;
        x3 = x1;
        y3 = y1 - body.getWidth() / 2;
        this.shapes[2] = new Triangle(x1, y1, x2, y2, x3, y3, Color.red, true);
        x1 = body.getX() + body.getWidth();
        x2 = x1 + body.getWidth() / 2;
        x3 = x1;
        this.shapes[3] = new Triangle(x1, y1, x2, y2, x3, y3, Color.red, true);

        // The bounding box of this SpaceShip
        this.boundingBox = this.shapes[0].getBoundingBox();

        // shield
        runShieldTimer();

        if (shield)
            this.shapes[4] = new Oval(this.center.x - 45, this.center.y - 45, 90, 90, Color.cyan, false);
        else
            this.shapes[4] = null;

        // Put everything in the window
        for (Shape shape : this.shapes)
            if (shape != null)
                this.window.add(shape);

        this.window.doRepaint();
    }
}
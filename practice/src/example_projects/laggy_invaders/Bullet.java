package example_projects.laggy_invaders;

import uwcse.graphics.GWindow;
import uwcse.graphics.Oval;
import uwcse.graphics.Shape;

import java.awt.*;

public class Bullet extends MovingObject {

    // size of bullet
    private int radius = 3;

    // status of bullet
    private boolean used;

    // direction of bullet
    private int direction;

    // color of bullet - determined by direction
    private Color color;


    /**
     * Creates this MovingObject
     *
     * @param window the GWindow this MovingObject belongs to
     * @param center the center point of this MovingObject
     */
    public Bullet(GWindow window, Point center, int direction){
        super(window, center);
        this.direction = direction;

        if (direction < 0)
            color = Color.red;
        else
            color = Color.yellow;
    }

    /**
     *
     * @return center point of bullet
     */
    public Point center(){
        return center;
    }

    /**
     * sets this bullet to used
     */
    public void isUsed(){
        this.used = true;
        this.erase();
    }

    /**
     * used status of bullet
     *
     * @return if bullet is used
     */
    public boolean getUsedStatus(){
        return this.used;
    }

    @Override
    public void move() {
        this.center.y -= this.direction;

        this.erase();
        if (this.center.y >= 0 || this.center.y <= 500)
            this.draw();
    }


    @Override
    protected void draw() {
        this.shapes = new Shape[1];
        this.shapes[0] = new Oval(this.center.x - radius, this.center.y - radius,
                2 * radius, 2 * radius, color, true);

        this.window.add(shapes[0]);
    }
}

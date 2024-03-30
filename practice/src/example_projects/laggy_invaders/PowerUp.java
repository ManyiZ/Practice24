package example_projects.laggy_invaders;

import uwcse.graphics.GWindow;
import uwcse.graphics.Oval;
import uwcse.graphics.Shape;
import uwcse.graphics.Triangle;

import java.awt.*;

public class PowerUp extends Item {
    /**
     * Creates this MovingObject
     *
     * @param window the GWindow this MovingObject belongs to
     * @param center the center point of this MovingObject
     */
    public PowerUp(GWindow window, Point center) {
        super(window, center);
        this.itemType = 2;
    }

    @Override
    public void draw() {
        this.shapes = new Shape[3];

        this.shapes[0] = new Triangle(this.center.x - radius, this.center.y - radius / 2,
                this.center.x + radius, this.center.y - radius / 2, this.center.x, this.center.y + radius,
                Color.RED, true);
        this.shapes[1] = new Triangle(this.center.x - radius, this.center.y + radius / 2,
                this.center.x + radius, this.center.y + radius / 2, this.center.x, this.center.y - radius,
                Color.RED, true);
        this.shapes[2] = new Oval(this.center.x - radius / 2, this.center.y - radius / 2, radius, radius, Color.YELLOW, true);

        for (Shape s: this.shapes)
            this.window.add(s);
    }
}

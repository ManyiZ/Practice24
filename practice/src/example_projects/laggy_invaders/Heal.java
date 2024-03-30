package example_projects.laggy_invaders;

import uwcse.graphics.GWindow;
import uwcse.graphics.Rectangle;
import uwcse.graphics.Shape;

import java.awt.*;

public class Heal extends Item {
    /**
     * Creates this MovingObject
     *
     * @param window the GWindow this MovingObject belongs to
     * @param center the center point of this MovingObject
     */
    public Heal(GWindow window, Point center) {
        super(window, center);
        this.itemType = 0;
    }

    @Override
    public void draw() {
        this.shapes = new Shape[3];
        this.shapes[0] = new Rectangle(this.center.x - radius, this.center.y - radius, 2 * radius,
                2 * radius, Color.WHITE, true);

        this.shapes[1] = new Rectangle(this.center.x - radius + 2, this.center.y - 3, 16, 6,
                Color.GREEN, true);

        this.shapes[2] = new Rectangle(this.center.x - 3, this.center.y - radius + 2, 6, 16,
                Color.GREEN, true);


        for (Shape s : shapes){
            window.add(s);
        }
    }
}

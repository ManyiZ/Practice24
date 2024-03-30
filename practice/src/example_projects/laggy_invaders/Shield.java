package example_projects.laggy_invaders;

import uwcse.graphics.GWindow;
import uwcse.graphics.Line;
import uwcse.graphics.Oval;
import uwcse.graphics.Shape;

import java.awt.*;

public class Shield extends Item {
    /**
     * Creates this MovingObject
     *
     * @param window the GWindow this MovingObject belongs to
     * @param center the center point of this MovingObject
     */
    public Shield(GWindow window, Point center) {
        super(window, center);
        this.itemType = 1;
    }

    @Override
    public void draw() {
        this.shapes = new Shape[3];

        this.shapes[0] = new Oval(this.center.x - radius + 2, this.center.y - radius + 2, 2 * (radius - 2),
                2 * (radius - 2), Color.cyan, false);
        this.shapes[1] = new Line(this.center.x - radius, this.center.y - 4, this.center.x + radius,
                this.center.y - 2, Color.WHITE);
        this.shapes[2] = new Line(this.center.x - radius, this.center.y + 4, this.center.x + radius,
                this.center.y + 2, Color.WHITE);

        for (Shape s : shapes){
            window.add(s);
        }
    }
}

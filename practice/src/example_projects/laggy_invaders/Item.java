package example_projects.laggy_invaders;

import uwcse.graphics.GWindow;
import uwcse.graphics.Rectangle;

import java.awt.*;

public class Item extends MovingObject {

    /** id of the item */
    protected int itemType;

    /** radius of all items */
    protected final int radius = 10;

    /** if the items are used */
    protected boolean isUsed;

    /**
     * Creates this MovingObject
     *
     * @param window the GWindow this MovingObject belongs to
     * @param center the center point of this MovingObject
     */
    public Item(GWindow window, Point center) {
        super(window, center);

        this.boundingBox = new Rectangle(this.center.x - radius, this.center.y - radius, 2 * radius,
                2 * radius);
    }

    /**
     * gets itemType for item identification
     *
     * @return itemType for item identification
     */
    public int getItemType(){
        return itemType;
    }

    /**
     * checks if this item is used
     *
     * @return if items are used
     */
    public boolean getUsedStat(){
        return isUsed;
    }

    /**
     * sets this item as used
     */
    public void isUsed(){
        this.isUsed = true;
    }

    @Override
    public void move() {
        this.erase();
        this.center.y += 2;
        this.draw();
    }

    @Override
    protected void draw() {

    }
}

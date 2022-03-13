package sprite;

import biuoop.DrawSurface;
import collidable.Block;
import shape.Rectangle;

import java.awt.Color;

/**
 * Sprite.
 * Background class.
 * The class will create the background for each level in the game.
 *
 * @author Sagi Wilentzik.
 */
public class Background implements Sprite {

    private Rectangle rectangle;
    private Color color;

    /**
     * Constructor.
     *
     * @param rectangle is the rectangle.
     * @param color     is the main color for the background.
     */
    public Background(Rectangle rectangle, Color color) {
        this.rectangle = rectangle;
        this.color = color;
    }

    @Override
    public void drawOn(DrawSurface d) {
        Block b = new Block(rectangle, this.color);
        b.drawOn(d);
    }

    @Override
    public void timePassed() {

    }
}

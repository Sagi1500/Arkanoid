package sprite;

import biuoop.DrawSurface;

/**
 * Sprite.
 * Sprite interface.
 *
 * @author Sagi Wilentzik.
 */
public interface Sprite {

    /**
     * draw the sprite to the screen.
     *
     * @param d is the surface to draw on.
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     */
    void timePassed();
}
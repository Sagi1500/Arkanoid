package listener;

import collidable.Block;
import sprite.Ball;

/**
 * Listener.
 * HitListener interface.
 *
 * @author Sagi Wilentzik.
 */
public interface HitListener {

    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     *
     * @param beingHit is the block that hit.
     * @param hitter   is the ball that's doing the hitting.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
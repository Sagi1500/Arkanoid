package collidable;

import shape.Point;
import shape.Rectangle;
import sprite.Ball;

/**
 * Collidable.
 * Collidable interface.
 *
 * @author Sagi Wilentzik.
 */
public interface Collidable {

    /**
     * @return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     *
     * @param collisionPoint  is the collision point.
     * @param currentVelocity is the current velocity of the object.
     * @param hitter          is the ball that hit the block.
     * @return the new velocity expected after the hit (based on the force the object inflicted on us).
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);

}
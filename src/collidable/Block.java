package collidable;

import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import listener.HitListener;
import listener.HitNotifier;
import shape.Point;
import shape.Rectangle;
import shape.Line;
import game.GameLevel;
import sprite.Sprite;
import sprite.Ball;


/**
 * Collidable.
 * Block class contains  methods from Collidable.Collidable, Sprite.Sprite interfaces.
 * All related to blocks in the game.
 *
 * @author Sagi Wilentzik.
 */
public class Block implements Collidable, Sprite, HitNotifier {

    private List<HitListener> hitListeners;
    private final Rectangle rectangle;
    private final Color color;

    /**
     * Constructor.
     *
     * @param upperLeft is the upper left point for the rectangle.
     * @param width     is the rectangle width.
     * @param height    is the rectangle height.
     * @param color     is the block color.
     */
    public Block(Point upperLeft, double width, double height, Color color) {
        this.hitListeners = new ArrayList<>();
        this.rectangle = new Rectangle(upperLeft, width, height);
        this.color = color;
    }

    /**
     * Constructor.
     *
     * @param rectangle - is the block shape.
     * @param color     is the block color.
     */
    public Block(Rectangle rectangle, Color color) {
        this.hitListeners = new ArrayList<>();
        this.rectangle = rectangle;
        this.color = color;
    }

    /**
     * @return the rectangle of the block - shape of the block.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * @return the hit listeners list.
     */
    public List<HitListener> getHitListeners() {
        return this.hitListeners;
    }

    /**
     * The method will return a new velocity after a collision between the ball and the block.
     *
     * @param hitter          - is the ball hitter.
     * @param collisionPoint  - is the collision point.
     * @param currentVelocity - is the current velocity of the ball.
     * @return new velocity foe the ball.
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {

        // creating the points and lines of the blocks.
        Point upperLeft = new Point(rectangle.getUpperLeft().getX(), rectangle.getUpperLeft().getY());
        Point lowLeft = new Point(upperLeft.getX(), upperLeft.getY() - rectangle.getHeight());
        Point upperRight = new Point(upperLeft.getX() + rectangle.getWidth(), upperLeft.getY());
        Point lowRight = new Point(upperLeft.getX() + rectangle.getWidth(), upperLeft.getY() - rectangle.getHeight());
        Line l1 = new Line(upperLeft, upperRight);
        Line l2 = new Line(upperLeft, lowLeft);
        Line l3 = new Line(upperRight, lowRight);
        Line l4 = new Line(lowLeft, lowRight);

        // checking for intersections and returning velocity.
        Line testLine = new Line(collisionPoint, collisionPoint);
        if (l1.isIntersecting(testLine)) {
            currentVelocity.setDy(-currentVelocity.getDy());
        }
        if (l2.isIntersecting(testLine)) {
            currentVelocity.setDx(-currentVelocity.getDx());
        }
        if (l3.isIntersecting(testLine)) {
            currentVelocity.setDx(-currentVelocity.getDx());
        }
        if (l4.isIntersecting(testLine)) {
            currentVelocity.setDy(-currentVelocity.getDy());
        }
        this.notifyHit(hitter);
        return currentVelocity;

    }

    /**
     * The method will draw the block on the surface.
     *
     * @param drawSurface is the surface to draw on.
     */
    @Override
    public void drawOn(DrawSurface drawSurface) {
        drawSurface.setColor(Color.black);
        drawSurface.drawRectangle((int) this.rectangle.getUpperLeft().getX(),
                (int) ((int) this.rectangle.getUpperLeft().getY() - this.rectangle.getHeight()),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
        drawSurface.setColor(this.color);
        drawSurface.fillRectangle((int) this.rectangle.getUpperLeft().getX(),
                (int) (this.rectangle.getUpperLeft().getY() - this.rectangle.getHeight()),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
    }

    /**
     * timePassed is related to the Sprite.Sprite interface. Implemented but don't do anything.
     */
    @Override
    public void timePassed() {
    }

    /**
     * The method will add the block for the sprite list and the collidable list.
     *
     * @param g is the game related.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * The method will remove the block from the game.
     *
     * @param game is the current game.
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    /**
     * The method will notify that hit occurred.
     *
     * @param hitter is the ball that made the hit.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);

        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
}

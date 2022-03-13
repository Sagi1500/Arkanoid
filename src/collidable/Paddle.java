package collidable;

import game.GameLevel;
import sprite.Sprite;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import shape.Rectangle;
import shape.Point;
import shape.Line;
import sprite.Ball;


import java.awt.Color;

/**
 * Collidable.
 * Paddle is the class the create and implements the paddle for the game.
 *
 * @author Sagi Wilentzik.
 */
public class Paddle implements Sprite, Collidable {


    private biuoop.KeyboardSensor keyboard;
    private Color color;
    private Rectangle rectangle;
    private GameLevel gameLevel;

    public static final int NUMBER_OF_CHUNKS = 5;
    public static final int[] ANGLES_ARRAY = {300, 330, 0, 30, 60};

    /**
     * Constructor.
     *
     * @param rectangle is the paddle shape.
     * @param color     is the paddle color.
     * @param key       is the KeyboardSensor.
     * @param gameLevel is the game level.
     *                  The method will create a new peddle.
     */
    public Paddle(Rectangle rectangle, Color color, KeyboardSensor key, GameLevel gameLevel) {
        this.keyboard = key;
        this.color = color;
        this.rectangle = rectangle;
        this.gameLevel = gameLevel;
    }

    /**
     * The method will move the paddle to the left.
     */
    public void moveLeft() {
        double newXValue = this.rectangle.getUpperLeft().getX() - gameLevel.getLevelInformation().paddleSpeed();
        if (newXValue <= GameLevel.SCREEN_FRAME_SIZE) {
            return;
        }
        this.rectangle.getUpperLeft().setX(newXValue);
    }

    /**
     * The method will move the paddle to the right.
     */
    public void moveRight() {
        double newXValue = this.rectangle.getUpperLeft().getX() + gameLevel.getLevelInformation().paddleSpeed();
        if (newXValue >= GameLevel.SCREEN_WIDTH - GameLevel.SCREEN_FRAME_SIZE - this.rectangle.getWidth()) {
            return;
        }
        this.rectangle.getUpperLeft().setX(newXValue);
    }

    /**
     * timePassed will check if the user pressed on one of the keyboard and move the paddle.
     */
    // Sprite.Sprite
    @Override
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * The method will draw the paddle on the screen.
     *
     * @param d is the surface.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.drawRectangle((int) this.rectangle.getUpperLeft().getX(),
                (int) ((int) this.rectangle.getUpperLeft().getY() - this.rectangle.getHeight()),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
        d.setColor(this.color);
        d.fillRectangle((int) this.rectangle.getUpperLeft().getX(),
                (int) (this.rectangle.getUpperLeft().getY() - this.rectangle.getHeight()),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
    }

    /**
     * @return the collision rectangle.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * The method will change the velocity of the ball according to the collidable with the paddle.
     *
     * @param hitter          - is the ball hitter.
     * @param collisionPoint  is the collision point.
     * @param currentVelocity is the ball velocity.
     * @return the new velocity.
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {

        // create the paddle points.
        Point upperLeft = new Point(rectangle.getUpperLeft().getX(), rectangle.getUpperLeft().getY());
        Point lowLeft = new Point(upperLeft.getX(), upperLeft.getY() - rectangle.getHeight());
        Point upperRight = new Point(upperLeft.getX() + rectangle.getWidth(), upperLeft.getY());
        Point lowRight = new Point(upperLeft.getX() + rectangle.getWidth(),
                upperLeft.getY() - rectangle.getHeight());
        Line l1 = new Line(upperLeft, lowLeft);
        Line l2 = new Line(upperRight, lowRight);
        Line testLine = new Line(collisionPoint, new Point(collisionPoint.getX() + currentVelocity.getDx(),
                collisionPoint.getY() + currentVelocity.getDy()));

        // checking the intersection with the side of the paddle.
        double speed = Math.sqrt(Math.pow(currentVelocity.getDx(), 2) + Math.pow(currentVelocity.getDy(), 2));
        if (l1.isIntersecting(testLine)) {
            currentVelocity = Velocity.fromAngleAndSpeed(ANGLES_ARRAY[0], speed);
            return currentVelocity;
        }
        if (l2.isIntersecting(testLine)) {
            currentVelocity = Velocity.fromAngleAndSpeed(ANGLES_ARRAY[ANGLES_ARRAY.length - 1], speed);
            return currentVelocity;
        }

        /* checking the intersection with the top of the paddle. The method will check on which part of the line
          the ball is intersecting and change the velocity according to the angle and speed. Checking all the 5 parts.
         */
        collisionPoint.setY(upperLeft.getY());
        testLine = new Line(collisionPoint, collisionPoint);
        double range = (upperRight.getX() - upperLeft.getX()) / NUMBER_OF_CHUNKS;
        Line l3 = new Line(upperLeft, new Point(upperLeft.getX() + range, upperLeft.getY()));
        for (int i = 1; i <= NUMBER_OF_CHUNKS; i++) {
            if (l3.isIntersecting(testLine)) {
                if (ANGLES_ARRAY[i - 1] != 0) {
                    currentVelocity = Velocity.fromAngleAndSpeed(ANGLES_ARRAY[i - 1], speed);
                } else {
                    currentVelocity.setDy(-currentVelocity.getDy());
                }
                return currentVelocity;
            }
            l3.setStartPoint(l3.end());
            l3.end().setX(upperLeft.getX() + range);
        }
        return currentVelocity;
    }

    /**
     * The method add the paddle to the sprite list and the collidable list.
     *
     * @param g is the game.
     * @throws Exception in case that the game is null
     */
    public void addToGame(GameLevel g) throws Exception {
        if (g == null) {
            throw new Exception("Game.Game is not initialized, define game first.");
        }
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * The method will replace the paddle location.
     *
     * @param rectangle1 is the new location for tha paddle.
     */
    public void setPaddle(Rectangle rectangle1) {
        this.rectangle = rectangle1;
    }
}

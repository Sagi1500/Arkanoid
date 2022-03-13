package sprite;

import game.GameLevel;
import biuoop.DrawSurface;
import shape.Point;
import shape.Rectangle;
import shape.Line;
import collidable.Velocity;
import collidable.CollisionInfo;
import game.GameEnvironment;

import java.awt.Color;

/**
 * Sprite.
 * Ball is a class that contain method to create and mange the Sprite.Ball details.
 *
 * @author Sagi Wilentzik.
 */
public class Ball implements Sprite {

    private Point center;
    private final int radius;
    private final java.awt.Color color;
    private Velocity velocity;
    private GameEnvironment game;

    /**
     * Constructor.
     *
     * @param x      is the x value of the center of the ball.
     * @param y      is the y value of the center of the ball.
     * @param radius is the radius length of the ball.
     * @param color  is the ball color.
     * @param game   is the game related.
     */
    public Ball(double x, double y, int radius, java.awt.Color color, GameEnvironment game) {
        this.center = new Point(x, y);
        this.color = color;
        this.radius = radius;
        this.game = game;
    }

    /**
     * @return the ball color.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * @param surface is the draw surface.
     *                The function has no returns value, the function will draw the ball on the screen.
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.black);
        surface.drawCircle((int) this.center.getX(), (int) this.center.getY(), this.radius);
        surface.setColor(this.color);
        surface.fillCircle((int) this.center.getX(), (int) this.center.getY(), this.radius);
    }

    /**
     * timePassed will use the move one step method.
     */
    @Override
    public void timePassed() {
        try {
            this.moveOneStep();
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    /**
     * The method will add the ball to the sprite list.
     *
     * @param g is the game related.
     * @throws Exception make sure that null pointer is not use.
     */
    public void addToGame(GameLevel g) throws Exception {
        if (g == null) {
            throw new Exception("Game.Game is not initialized, define game first.");
        }
        g.addSprite(this);
    }

    /**
     * @param v is the velocity that need to be change.
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * @return the velocity.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * The method will check if the ball is inside one of the collision objects.
     *
     * @param collisionInfo is the examine object.
     * @return true if the ball is in the object, otherwise returns false.
     */
    public boolean isBallOnBlock(CollisionInfo collisionInfo) {
        double epsilon = Math.pow(10, -13);
        Rectangle rectangle = collisionInfo.collisionObject().getCollisionRectangle();
        return this.center.getX() - rectangle.getUpperLeft().getX() >= epsilon
                && this.center.getX() - rectangle.getUpperLeft().getX() + rectangle.getWidth() <= epsilon
                && this.center.getY() - rectangle.getUpperLeft().getY() <= epsilon
                && this.center.getY() - rectangle.getUpperLeft().getY() - rectangle.getHeight() <= epsilon;
    }

    /**
     * The method will check if the ball is inside one of the collision objects.
     *
     * @param collisionInfo is the examine object.
     * @return true if the ball is in the object, otherwise returns false.
     */
    public boolean isBallInBlock(CollisionInfo collisionInfo) {
        Rectangle rectangle = collisionInfo.collisionObject().getCollisionRectangle();
        return this.center.getX() >= rectangle.getUpperLeft().getX()
                && this.center.getX() <= rectangle.getUpperLeft().getX() + rectangle.getWidth()
                && this.center.getY() <= rectangle.getUpperLeft().getY()
                && this.center.getY() >= rectangle.getUpperLeft().getY() - rectangle.getHeight();
    }


    /**
     * the method will check if the point in inside the screen size (without the frame).
     *
     * @param point is the end point of the line.
     * @return true if the ball is inside the screen. otherwise returns false.
     */
    public boolean isBallInScreen(Point point) {
        return point.getX() >= -1 && point.getX() <= GameLevel.SCREEN_WIDTH + 2 * GameLevel.SCREEN_FRAME_SIZE
                && point.getY() >= -1 && point.getY()
                <= GameLevel.SCREEN_HEIGHT + 2 * GameLevel.SCREEN_FRAME_SIZE;
    }

    /**
     * the method will create a line that goes from the center of the point to the end of the screen.
     * I used this line to check the case when the ball hit by the paddle.
     *
     * @return the trajectory line.
     */
    public Line createTrajectory() {
        Point end = new Point(this.center.getX(), this.center.getY());
        this.getVelocity().applyToPoint(end);
        while (isBallInScreen(end)) {
            end = this.getVelocity().applyToPoint(end);
        }
        return new Line(this.center, end);
    }

    /**
     * The method will change the way the object moving on the screen.
     * The method has no returning value.
     *
     * @throws Exception when the velocity is null.
     */
    public void moveOneStep() throws Exception {
        double epsilon = Math.pow(10, -5);
        if (this.getVelocity() == null) {
            throw new Exception("Collidable.Velocity of the ball is null");
        }
        Point end = new Point(this.center.getX() + this.velocity.getDx(),
                this.center.getY() + this.velocity.getDy());
        Line trajectory = new Line(this.center, end);
        CollisionInfo collisionInfo = this.game.getClosestCollision(trajectory);
        if (collisionInfo == null) {
            this.center = end;
        } else {
            if (this.getVelocity().getDx() > 0 && this.getVelocity().getDy() > 0) {
                this.center = new Point(collisionInfo.collisionPoint().getX() - this.radius + epsilon,
                        collisionInfo.collisionPoint().getY() - this.radius + epsilon);
            } else if (this.getVelocity().getDx() > 0 && this.getVelocity().getDy() < 0) {
                this.center = new Point(collisionInfo.collisionPoint().getX() - this.radius + epsilon,
                        collisionInfo.collisionPoint().getY() + this.radius - epsilon);
            } else if (this.getVelocity().getDx() < 0 && this.getVelocity().getDy() < 0) {
                this.center = new Point(collisionInfo.collisionPoint().getX() + this.radius - epsilon,
                        collisionInfo.collisionPoint().getY() + this.radius - epsilon);
            } else if (this.getVelocity().getDx() < 0 && this.getVelocity().getDy() > 0) {
                this.center = new Point(collisionInfo.collisionPoint().getX() + this.radius - epsilon,
                        collisionInfo.collisionPoint().getY() - this.radius + epsilon);
            }


            this.setVelocity(collisionInfo.collisionObject().hit(this, collisionInfo.collisionPoint(),
                    this.getVelocity()));
        }
        // checking the case when the ball is in one of the blocks.
        trajectory = createTrajectory();
        collisionInfo = this.game.getClosestCollision(trajectory);
        if (collisionInfo != null) {
            boolean flag = false;
            while (isBallInBlock(collisionInfo) || isBallOnBlock(collisionInfo)) {

                this.getVelocity().setDy(Math.abs(this.getVelocity().getDy()));
                Point upperLeft = new Point(collisionInfo.collisionObject().getCollisionRectangle()
                        .getUpperLeft().getX(), collisionInfo.collisionObject().getCollisionRectangle()
                        .getUpperLeft().getY());
                Point upperRight = new Point(collisionInfo.collisionObject().getCollisionRectangle()
                        .getUpperLeft().getX() + collisionInfo.collisionObject().getCollisionRectangle().getWidth(),
                        collisionInfo.collisionObject().getCollisionRectangle().getUpperLeft().getY());

                if (!flag) {
                    if (upperLeft.distance(this.center) < upperRight.distance(this.center)) {
                        this.getVelocity().setDx(-Math.abs(this.getVelocity().getDx()));
                    } else {
                        this.getVelocity().setDx(Math.abs(this.getVelocity().getDx()));
                    }
                }

                this.center = this.getVelocity().applyToPoint(this.center);
                flag = true;
            }
            if (flag) {
                this.center = this.getVelocity().applyToPoint(this.center);
            }
        }
    }

    /**
     * The method will remove the ball from the game.
     *
     * @param game1 is the current game.
     */
    public void removeFromGame(GameLevel game1) {
        game1.removeSprite(this);
    }
}
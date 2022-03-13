package collidable;

import shape.Point;

/**
 * collidable.
 * Velocity class
 *
 * @author Sagi Wilentzik.
 */
public class Velocity {

    private double deltaX;
    private double deltaY;

    /**
     * Constructor.
     *
     * @param dx is the change in x .
     * @param dy is the change in y.
     */
    public Velocity(double dx, double dy) {
        this.deltaX = dx;
        this.deltaY = dy;
    }

    /**
     * The method receives an angle and a speed and return a new velocity.
     *
     * @param angle is the angle.
     * @param speed is the speed.
     * @return the method will return new velocity.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        angle = angle - 90;
        double dx = Math.cos(Math.toRadians(angle)) * speed;
        double dy = Math.sin(Math.toRadians(angle)) * speed;
        return new Velocity(dx, dy);
    }

    /**
     * @param p is the initial point.
     *          Take a point with position (x,y) and.
     * @return a new point with position (x+dx, y+dy)
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.deltaX, p.getY() + this.deltaY);
    }

    /**
     * @return the X value of the velocity.
     */
    public double getDx() {
        return this.deltaX;
    }

    /**
     * @return the Y value of the velocity.
     */
    public double getDy() {
        return this.deltaY;
    }

    /**
     * change the delta x value.
     *
     * @param dx is the delta in x axis.
     */
    public void setDx(double dx) {
        this.deltaX = dx;
    }

    /**
     * change the delta y value.
     *
     * @param dy is the delta in y axis.
     */
    public void setDy(double dy) {
        this.deltaY = dy;
    }


}

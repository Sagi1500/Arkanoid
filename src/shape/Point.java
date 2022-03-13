package shape;

/**
 * Shape.
 * Point class.
 *
 * @author Sagi Wilentzik.
 */

public class Point {

    private double x;
    private double y;

    /**
     * Constructor.
     *
     * @param x the x coordinate of the point.
     * @param y the y coordinate of the point.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * The method calculate the distance between 2 points.
     *
     * @param other is the second point for finding the distance.
     * @return the distance of this point to the other point.
     */
    public double distance(Point other) {
        double dx = this.x - other.getX();
        double dy = this.y - other.getY();
        return Math.sqrt((dx * dx) + (dy * dy));
    }

    /**
     * The method will check if 2 points are equals.
     *
     * @param other is the second point for comparing.
     * @return true is the points are equal, false otherwise.
     */
    public boolean equals(Point other) {
        double epsilon = Math.pow(10, -13);
        return Math.abs(this.x - other.getX()) <= epsilon && Math.abs(this.y - other.getY()) <= epsilon;
    }

    /**
     * @return the x coordinate of the point
     */
    public double getX() {
        return this.x;
    }

    /**
     * @return the y coordinate of the point
     */
    public double getY() {
        return this.y;
    }

    /**
     * The method will change the way that the point printing.
     *
     * @return the string.
     */
    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }

    /**
     * The method changes the X value.
     *
     * @param newX is the new value.
     */
    public void setX(double newX) {
        this.x = newX;
    }

    /**
     * The method changes the Y value.
     *
     * @param newY is the new value.
     */
    public void setY(double newY) {
        this.y = newY;
    }


}

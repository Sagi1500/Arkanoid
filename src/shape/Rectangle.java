package shape;

import java.util.ArrayList;
import java.util.List;

/**
 * Shape.
 * Rectangle class.
 *
 * @author Sagi Wilentzik.
 */
public class Rectangle {

    private final Point upperLeft;
    private final double width;
    private final double height;

    /**
     * Constructor.
     *
     * @param upperLeft is the top left corner of the rectangle.
     * @param width     is the width of the rectangle, change in x axis from upperLeft.
     * @param height    is the height of the rectangle, change in y axis from upperLeft.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.height = height;
        this.width = width;
        this.upperLeft = upperLeft;
    }

    /**
     * The function checks if the list contains the point.
     *
     * @param pointList is the list with the points.
     * @param p         is the examine point.
     * @return true if the point is in the list, otherwise returns false.
     */
    private static boolean isPointInList(List<Point> pointList, Point p) {
        boolean isOk = false;
        for (Point point : pointList) {
            if (point.equals(p)) {
                isOk = true;
                break;
            }
        }
        return !isOk;
    }

    /**
     * the method will create the lines of the rectangle and check if the line is intersection with him.
     *
     * @param line is the checking line.
     * @return a list with the intersection points with the specified line.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> pointList = new ArrayList<>();
        Point lowLeft = new Point(this.upperLeft.getX(), this.upperLeft.getY() - this.height);
        Point upperRight = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY());
        Point lowRight = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY() - this.height);
        Line l1 = new Line(upperLeft, upperRight);
        Line l2 = new Line(upperLeft, lowLeft);
        Line l3 = new Line(upperRight, lowRight);
        Line l4 = new Line(lowLeft, lowRight);
        Point temp = l1.intersectionWith(line);
        if (temp != null) {
            pointList.add(temp);
        }
        temp = l2.intersectionWith(line);
        if (temp != null) {
            if (isPointInList(pointList, temp)) {
                pointList.add(temp);
            }
        }
        temp = l3.intersectionWith(line);
        if (temp != null) {
            if (isPointInList(pointList, temp)) {
                pointList.add(temp);
            }
        }
        temp = l4.intersectionWith(line);
        if (temp != null) {
            if (isPointInList(pointList, temp)) {
                pointList.add(temp);
            }
        }
        return pointList;
    }

    /**
     * @return the width value.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * @return the height value.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * @return the upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

}
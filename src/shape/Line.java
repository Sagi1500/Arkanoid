package shape;

import java.util.List;

/**
 * Shape.
 * Line class.
 *
 * @author Sagi Wilentzik.
 */
public class Line {
    private final Point start;
    private final Point end;

    /**
     * Constructor.
     *
     * @param start is the starting point of the line.
     * @param end   is the ending point of the line.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Constructor.
     *
     * @param x1 is the x value of the starting point of the line.
     * @param y1 is the y value of the starting point of the line.
     * @param x2 is the x value of the ending point of the line.
     * @param y2 is the y value of the ending point of the line.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * @return the length of the line.
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * @return the middle point of the line.
     */
    public Point middle() {
        double xMiddle = ((this.start.getX() + this.end.getX()) / 2);
        double yMiddle = ((this.start.getY() + this.end.getY()) / 2);
        return new Point(xMiddle, yMiddle);
    }

    /**
     * @return the start point of the line.
     */
    public Point start() {
        return this.start;
    }

    /**
     * @return the end point of the line.
     */
    public Point end() {
        return this.end;
    }

    /**
     * The method will replace the start point with the receiving point.
     *
     * @param p is the new start point.
     */
    public void setStartPoint(Point p) {
        this.start.setX(p.getX());
        this.start.setY(p.getY());
    }

    /**
     * The method will check if the x value is in the scope of X axis of the other line.
     *
     * @param x     is the examine value.
     * @param other is the second line.
     * @return the method return true if the x value is in the scope of the X axis of the other line.
     */
    public boolean inScopeX(double x, Line other) {
        return (Math.min(this.start.getX(), this.end.getX()) <= x)
                && (x <= Math.max(this.start.getX(), this.end.getX()))
                && (Math.min(other.start.getX(), other.end.getX()) <= x)
                && (x <= Math.max(other.start.getX(), other.end.getX()));
    }

    /**
     * The method will check if the y value is in the scope of Y axis of the other line.
     *
     * @param y     is the examine value.
     * @param other is the second line.
     * @return the method return true if the y value is in the scope of the Y axis of the other line.
     */
    public boolean inScopeY(double y, Line other) {
        return (Math.min(this.start.getY(), this.end.getY()) <= y)
                && (y <= Math.max(this.start.getY(), this.end.getY()))
                && (Math.min(other.start.getY(), other.end.getY()) <= y)
                && (y <= Math.max(other.start.getY(), other.end.getY()));
    }

    /**
     * The method will check if there is an intersection be tween 2 segments.
     *
     * @param other is the second line.
     * @return true if there is an intersection, false otherwise.
     */
    public boolean isIntersecting(Line other) {
        return intersectionWith(other) != null;
    }

    /**
     * The method will check if there is an intersection between 2 segments.
     *
     * @param other is the second line.
     * @return the intersection point, null otherwise.
     */
    public Point intersectionWith(Line other) {
        Point intersectionPoint = isIntersectionWithLines(other);
        if (intersectionPoint == null) {
            return null;
        }
        if ((inScopeY(intersectionPoint.getY(), other) && inScopeX(intersectionPoint.getX(), other))) {
            return intersectionPoint;
        }
        return null;
    }

    /**
     * The method will check if there is an intersection between 2 segments if their slopes are equals.
     *
     * @param other is the second line.
     * @return the intersection point, null otherwise.
     */
    public Point checkCases(Line other) {
        if (this.start.equals(other.start)) {
            if ((this.start.getY() > this.end.getY() && other.start.getY() < other.end.getY())
                    || (this.start.getY() < this.end.getY() && other.start.getY() > other.end.getY())) {
                return this.start;
            }
            return null;
        }
        if (this.start.equals(other.end)) {
            if ((this.start.getY() > this.end.getY() && other.end.getY() < other.start.getY())
                    || (this.start.getY() < this.end.getY() && other.end.getY() > other.start.getY())) {
                return this.start;
            }
            return null;
        }
        if (this.end.equals(other.start)) {
            if ((this.end.getY() > this.start.getY() && other.start.getY() < other.end.getY())
                    || (this.end.getY() < this.start.getY() && other.start.getY() > other.end.getY())) {
                return this.end;
            }
            return null;
        }
        if (this.end.equals(other.end)) {
            if ((this.end.getY() > this.start.getY() && other.end.getY() < other.start.getY())
                    || (this.end.getY() < this.start.getY() && other.end.getY() > other.start.getY())) {
                return this.end;
            }
            return null;
        }
        return null;
    }

    /**
     * The method will check if there is an intersection point between 2 lines.
     *
     * @param other is the second line.
     * @return the intersection point, null otherwise.
     */
    public Point isIntersectionWithLines(Line other) {
        double epsilon = Math.pow(10, -13);

        // checking the case when both of the line are the same point.
        if (this.start.equals(this.end) && other.start.equals(other.end) && this.start.equals(other.start)) {
            return this.start;
        }

        double deltaY1 = this.end.getY() - this.start.getY();
        double deltaX1 = this.end.getX() - this.start.getX();
        double deltaY2 = other.end.getY() - other.start.getY();
        double deltaX2 = other.end.getX() - other.start.getX();

        double m1 = deltaY1 / deltaX1;
        double m2 = deltaY2 / deltaX2;

        double b1 = this.start.getY() - m1 * this.start.getX();
        double b2 = other.start.getY() - m2 * other.start.getX();

        // checking the case when both lines are parallel to Y axis.
        if (deltaX1 == 0 && deltaX2 == 0) {
            b1 = this.start.getX();
            b2 = other.start.getX();
            if (b1 != b2) {
                return null;
            }
            if (this.start.equals(this.end)) {
                if ((this.start.getY() <= Math.max(other.start.getY(), other.end.getY()))
                        && (this.start.getY() >= Math.min(other.start.getY(), other.end.getY()))) {
                    return this.start;
                }
            }
            if (other.start.equals(other.end)) {
                if ((other.start.getY() <= Math.max(this.start.getY(), this.end.getY()))
                        && (other.start.getY() >= Math.min(this.start.getY(), this.end.getY()))) {
                    return other.start;
                }
            }
            return checkCases(other);
        }

        // checking the case when one line is parallel to Y axis.
        if (deltaX1 == 0) {
            b1 = this.start.getX();
            if (this.start.equals(this.end)) {
                if (this.start.getY() - (m2 * b1 + b2) > epsilon) {
                    return null;
                }
            }
            return new Point(b1, m2 * b1 + b2);
        }

        if (deltaX2 == 0) {
            b2 = other.start.getX();
            if (other.start.equals(other.end)) {
                if (other.start.getY() - (m1 * b2 + b1) > epsilon) {
                    return null;
                }
            }
            return new Point(b2, m1 * b2 + b1);
        }

        // checking the case when both lines has the same slope.
        if (Math.abs(m1 - m2) <= epsilon) {
            return checkCases(other);
        }

        // checking the case that the lines are equals.
        if (equals(other)) {
            return null;
        }

        // calculate the intersection point.
        double intersectX = (b2 - b1) / (m1 - m2);
        double intersectY = m1 * intersectX + b1;
        return new Point(intersectX, intersectY);
    }

    /**
     * The method will check if the lines are equals.
     *
     * @param other is the second line.
     * @return true is the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        return (this.start.equals(other.start) && this.end.equals(other.end))
                || (this.start.equals(other.end) && this.end.equals(other.start));
    }

    /**
     * @param rect is the examine rectangle.
     * @return - If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the start of the line.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> pointList;
        pointList = rect.intersectionPoints(this);
        if (pointList.isEmpty()) {
            return null;
        }
        double minVal = this.start.distance(pointList.get(0));
        int minIndex = 0;
        for (int i = 0; i < pointList.size(); i++) {
            double temp = this.start.distance(pointList.get(i));
            if (temp < minVal) {
                minVal = temp;
                minIndex = i;
            }
        }
        return pointList.get(minIndex);
    }


}

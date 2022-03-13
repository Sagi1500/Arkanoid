package game;

import collidable.Collidable;

import java.util.ArrayList;
import java.util.List;

import shape.Point;
import shape.Line;
import collidable.CollisionInfo;


/**
 * Game.
 * GameEnvironment is contain the collidableList that saves all the collidable objects.
 *
 * @author Sagi Wilentzik.
 */
public class GameEnvironment {

    private final List<Collidable> collidableList;

    /**
     * Constructor.
     * The method will create a new Game.GameEnvironment.
     */
    public GameEnvironment() {
        this.collidableList = new ArrayList<>();
    }

    /**
     * getCollidableList.
     *
     * @return the list
     */
    public List<Collidable> getCollidableList() {
        return this.collidableList;
    }

    /**
     * the method will add a collidable to the list.
     *
     * @param c is the collidable to add
     */
    public void addCollidable(Collidable c) {
        this.collidableList.add(c);
    }

    /**
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables in this collection, return null.
     * Else, return the information about the closest collision that is going to occur.
     *
     * @param trajectory is the examine line.
     * @return the closest collision. if there are no collision points return null.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        List<Point> pointList = new ArrayList<>();
        List<Collidable> tempCollidableList = new ArrayList<>();

        // checking if there are any collision point with the line.
        for (Collidable collidable : collidableList) {
            Point temp;

            //temp = (this.collidableList.get(i).getCollisionRectangle()).intersectionPoints(trajectory);
            temp = trajectory.closestIntersectionToStartOfLine(collidable.getCollisionRectangle());
            if (temp != null) {

                // insert the results to new lists.
                pointList.add(temp);
                tempCollidableList.add(collidable);
            }
        }

        // dealing with the case when there are no intersection points at all.
        if (pointList.isEmpty()) {
            return null;
        }

        // finding the closest intersection and returning details.
        double min = trajectory.start().distance(pointList.get(0));
        int collisionIndex = 0;
        for (int i = 1; i < pointList.size(); i++) {
            double tempDistance = trajectory.start().distance(pointList.get(i));
            if (tempDistance < min) {
                min = tempDistance;
                collisionIndex = i;
            }
        }
        return new CollisionInfo(pointList.get(collisionIndex), tempCollidableList.get(collisionIndex));
    }

}
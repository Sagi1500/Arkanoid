package level;

import sprite.Sprite;
import collidable.Velocity;
import collidable.Block;

import java.util.List;

/**
 * LevelInformation interface.
 *
 * @author Sagi Wilentzik.
 */
public interface LevelInformation {

    /**
     * @return the number of balls.
     */
    int numberOfBalls();

    /**
     * @return the velocity list.
     */
    List<Velocity> initialBallVelocities();

    /**
     * @return the paddle speed.
     */
    int paddleSpeed();

    /**
     * @return the paddle width.
     */
    int paddleWidth();

    /**
     * @return the paddle height.
     */
    int paddleHeight();

    /**
     * The level name will be displayed at the top of the screen.
     *
     * @return the level name.
     */
    String levelName();

    /**
     * @return a sprite with the background of the level
     */
    Sprite getBackground();

    /**
     * The Blocks that make up this level, each block contains its size, color and location.
     *
     * @return the blocks list.
     */
    List<Block> blocks();

    /**
     * @return the number of blocks that should be removed before the level is considered to be cleared.
     */
    int numberOfBlocksToRemove();

    /**
     * The method will create the blocks for the level.
     *
     * @return list of blocks.
     */
    List<Block> createBlocks();

    /**
     * The method will create the velocity for the level.
     *
     * @return list of velocity.
     */
    List<Velocity> createVelocity();

    /**
     * The method will create the background for the level.
     *
     * @return the background.
     */
    Sprite createBackground();

    /**
     * The method will change the velocity list with another list.
     *
     * @param velocity is the new list.
     */
    void setBallVelocity(List<Velocity> velocity);

}
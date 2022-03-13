package animation;

import biuoop.DrawSurface;

/**
 * Animation.
 * Animation interface.
 *
 * @author Sagi Wilentzik.
 */
public interface Animation {

    /**
     * The method will run one frame on the screen.
     *
     * @param d is the draw surface.
     */
    void doOneFrame(DrawSurface d);

    /**
     * @return true is animation should stop. Otherwise return false.
     */
    boolean shouldStop();
}

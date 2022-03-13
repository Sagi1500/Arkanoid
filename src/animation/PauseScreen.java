package animation;

import biuoop.DrawSurface;

/**
 * Animation.
 * PauseScreen class.
 * The class will create the pause screen  screen animation.
 *
 * @author Sagi Wilentzik.
 */
public class PauseScreen implements Animation {
    private boolean stop;

    /**
     * Constructor.
     */
    public PauseScreen() {
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}

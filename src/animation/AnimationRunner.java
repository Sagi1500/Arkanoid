package animation;

import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;


/**
 * Animation.
 * Animation runner class. This class is responsible on running the animation.
 *
 * @author Sagi Wilentzik.
 */
public class AnimationRunner {

    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;

    public static final int FRAMES_PER_SECOND = 60;

    /**
     * Constructor.
     *
     * @param gui is the gui name.
     */
    public AnimationRunner(GUI gui) {
        this.gui = gui;
        this.framesPerSecond = FRAMES_PER_SECOND;
        this.sleeper = new Sleeper();
    }

    /**
     * The method will run the animation.
     *
     * @param animation is the animation name.
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / this.framesPerSecond;

        // stopping condition.
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();

            // create and run the frame.
            animation.doOneFrame(d);

            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}

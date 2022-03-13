package animation;

import biuoop.Sleeper;
import game.GameLevel;
import sprite.SpriteCollection;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * Animation.
 * CountdownAnimation class.
 * The class will create a 3,2,1 animation screen before each level in the game.
 *
 * @author Sagi Wilentzik.
 */
public class CountdownAnimation implements Animation {

    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection spriteCollection;
    private boolean stop;

    public static final int COUNT_DOWN_FONT_SIZE = 64;

    /**
     * Constructor.
     *
     * @param numOfSeconds is the number of second to wait in the countdown.
     * @param countFrom    the first number in the countdown.
     * @param gameScreen   is the sprite collection of of the game.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.spriteCollection = gameScreen;
        this.stop = false;

    }

    @Override
    public void doOneFrame(DrawSurface d) {

        // create the sleep and draw on the game details on the screen.
        Sleeper sleeper = new Sleeper();
        this.spriteCollection.drawAllOn(d);

        // create the countdown animation.
        d.setColor(Color.white);
        d.drawText(d.getWidth() / 2, d.getHeight() / 2, "" + this.countFrom, COUNT_DOWN_FONT_SIZE);
        if (countFrom != GameLevel.COUNT_FROM) {
            sleeper.sleepFor((long) (this.numOfSeconds / GameLevel.COUNT_FROM * 1000));
        }
        if (countFrom > 0) {
            countFrom = countFrom - 1;
        } else {
            this.stop = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
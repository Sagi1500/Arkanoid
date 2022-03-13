package animation;

import biuoop.DrawSurface;
import game.GameFlow;

/**
 * Animation.
 * YouWinScreen class.
 * The class will create the you win screen screen animation.
 *
 * @author Sagi Wilentzik.
 */
public class YouWinScreen implements Animation {
    private boolean stop;
    private GameFlow gameFlow;

    /**
     * Constructor.
     *
     * @param gameFlow is the game flow.
     */
    public YouWinScreen(GameFlow gameFlow) {
        this.stop = false;
        this.gameFlow = gameFlow;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(d.getWidth() / 4, d.getHeight() / 3,
                "You Win! Your score is " + this.gameFlow.getScoreIndicator()
                        .getScoreTrackingListener().getCounterCurrentScore().getValue(), 32);
        d.drawText(d.getWidth() / 3, d.getHeight() / 3 * 2, "Press space to continue ", 32);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}

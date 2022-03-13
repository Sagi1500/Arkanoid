package animation;

import biuoop.DrawSurface;
import game.GameFlow;

/**
 * Animation.
 * GameOverScreen class.
 * The class will create the Game over screen animation.
 *
 * @author Sagi Wilentzik.
 */
public class GameOverScreen implements Animation {
    private boolean stop;
    private GameFlow gameFlow;

    /**
     * Constructor.
     *
     * @param gameFlow is the game flow.
     */
    public GameOverScreen(GameFlow gameFlow) {
        this.stop = false;
        this.gameFlow = gameFlow;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(d.getWidth() / 4, d.getHeight() / 3,
                "Game Over. Your score is " + this.gameFlow.getScoreIndicator()
                        .getScoreTrackingListener().getCounterCurrentScore().getValue(), 32);
        d.drawText(d.getWidth() / 3, d.getHeight() / 3 * 2, "Press space to continue ", 32);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}

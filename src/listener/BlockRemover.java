package listener;

import collidable.Block;
import game.GameLevel;
import sprite.Ball;

/**
 * Listener.
 * BlockRemover class.
 * BlockRemover is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 *
 * @author Sagi Wilentzik.
 */
public class BlockRemover implements HitListener {

    private GameLevel game;
    private Counter remainingBlocks;

    public static final int NUM_TO_DECREASE = 1;

    /**
     * Constructor.
     *
     * @param game          is the game.
     * @param removedBlocks is the counter.
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * @return the number of the ramin blocks.
     */
    public Counter getRemainingBlocks() {
        return remainingBlocks;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeHitListener(beingHit.getHitListeners().get(beingHit.getHitListeners().indexOf(this)));
        beingHit.removeFromGame(this.game);
        this.remainingBlocks.decrease(NUM_TO_DECREASE);
        this.game.getScoreTrackingListener().hitEvent(beingHit, hitter);
    }
}

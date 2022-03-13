package game;

import animation.GameOverScreen;
import animation.KeyPressStoppableAnimation;
import animation.YouWinScreen;
import biuoop.KeyboardSensor;
import level.LevelInformation;
import animation.AnimationRunner;
import listener.Counter;
import sprite.ScoreIndicator;

import java.util.List;

/**
 * Game.
 * GameFlow class.
 * The class will create the game flow.
 *
 * @author Sagi Wilentzik.
 */
public class GameFlow {

    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private ScoreIndicator scoreIndicator;
    private Counter lives;

    public static final int INITIAL_LIFE_NUMBER = 7;

    /**
     * Constructor.
     *
     * @param animationRunner1 is tha animation.
     * @param keyboardSensor1  is the keyboard sensor.
     */
    public GameFlow(AnimationRunner animationRunner1, KeyboardSensor keyboardSensor1) {
        this.animationRunner = animationRunner1;
        this.keyboardSensor = keyboardSensor1;
        this.scoreIndicator = new ScoreIndicator();
        this.lives = new Counter(INITIAL_LIFE_NUMBER);
    }

    /**
     * @return the score indicator.
     */
    public ScoreIndicator getScoreIndicator() {
        return this.scoreIndicator;
    }

    /**
     * The method will receive the list with the levels and run them.
     *
     * @param levels is the list of levels.
     */
    public void runLevels(List<LevelInformation> levels) {

        // for loop for each level in the list.
        for (LevelInformation levelInfo : levels) {

            // create the level.
            GameLevel level = new GameLevel(levelInfo, this.animationRunner, this.keyboardSensor,
                    this.scoreIndicator, this.lives);

            // initialize the level.
            level.initialize();

            // initialize the number of blocks that need to be removed for winning the game.
            int num = level.getBlockRemover().getRemainingBlocks().getValue()
                    - level.getLevelInformation().numberOfBlocksToRemove();

            while (this.lives.getValue() > 0) {
                if (level.getBlockRemover().getRemainingBlocks().getValue() == num
                        || level.getBlockRemover().getRemainingBlocks().getValue() == 0) {
                    break;
                }

                // run the level animation
                level.run();
                if (level.getBallRemover().getRemainingBalls().getValue() == 0) {
                    this.lives.decrease(1);
                    if (this.lives.getValue() == 0) {
                        this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor,
                                KeyboardSensor.SPACE_KEY, new GameOverScreen(this)));
                    }

                    /*
                    if all the balls went out of the screen they need to be initialize again and the paddle should
                    move to the middle of the screen. This method is tacking care of that.
                     */
                    level.initializeBallAndPaddle();
                }
            }
        }
        this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor,
                KeyboardSensor.SPACE_KEY, new YouWinScreen(this)));
    }
}
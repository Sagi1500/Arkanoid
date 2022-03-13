package game;

import animation.Animation;
import animation.AnimationRunner;
import animation.CountdownAnimation;
import animation.PauseScreen;
import animation.KeyPressStoppableAnimation;
import biuoop.KeyboardSensor;
import collidable.Velocity;
import level.LevelInformation;
import collidable.Collidable;
import sprite.Sprite;
import sprite.Ball;
import sprite.LevelIndicator;
import sprite.SpriteCollection;
import sprite.ScoreIndicator;
import biuoop.DrawSurface;
import biuoop.Sleeper;
import collidable.Block;
import collidable.Paddle;
import listener.ScoreTrackingListener;
import listener.BallRemover;
import listener.BlockRemover;
import listener.Counter;
import shape.Rectangle;
import shape.Point;

import java.awt.Color;
import java.util.List;

/**
 * Game.
 * Game class will contain the methods for creating and initializing the game.
 *
 * @author Sagi Wilentzik.
 */
public class GameLevel implements Animation {

    private LevelInformation levelInformation;
    private KeyboardSensor keyboard;
    private ScoreIndicator scoreIndicator;
    private BallRemover ballRemover;
    private BlockRemover blockRemover;
    private final SpriteCollection sprites;
    private final GameEnvironment environment;
    private final Sleeper sleeper;
    private AnimationRunner runner;
    private boolean running;
    private Counter lives;
    private Paddle paddle;

    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 600;
    public static final int SCREEN_FRAME_SIZE = 25;
    public static final int DEFAULT_RADIUS_SIZE = 5;
    public static final int NUM_TO_INCREASE = 1;
    public static final int ADD_IN_WIN = 100;
    public static final double NUMBER_OF_SECONDS = 2;
    public static final int COUNT_FROM = 3;

    /**
     * Constructor.
     * The method will create a new game.
     *
     * @param levelInformation1 is the level.
     * @param animationRunner   is the animation runner
     * @param keyboard          is the keyboard.
     * @param scoreIndicator    is the game flow score indicator.
     * @param lives             is the counter for the lives in the game.
     */
    public GameLevel(LevelInformation levelInformation1, AnimationRunner animationRunner, KeyboardSensor keyboard,
                     ScoreIndicator scoreIndicator, Counter lives) {
        this.levelInformation = levelInformation1;
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.sleeper = new Sleeper();
        this.blockRemover = new BlockRemover(this, new Counter());
        this.ballRemover = new BallRemover(this, new Counter());
        this.scoreIndicator = new ScoreIndicator();
        this.runner = animationRunner;
        this.running = true;
        this.keyboard = keyboard;
        this.scoreIndicator = scoreIndicator;
        this.lives = lives;
        try {
            this.paddle = new Paddle(new Rectangle(new Point((float) (SCREEN_WIDTH / 2)
                    - this.levelInformation.paddleWidth() / (float) 2, SCREEN_HEIGHT - SCREEN_FRAME_SIZE),
                    this.levelInformation.paddleWidth(), this.levelInformation.paddleHeight()),
                    Color.yellow, this.keyboard, this);
            paddle.addToGame(this);
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    /**
     * @return the block remover.
     */
    public BlockRemover getBlockRemover() {
        return this.blockRemover;
    }

    /**
     * @return the ball remover.
     */
    public BallRemover getBallRemover() {
        return this.ballRemover;
    }

    /**
     * @return the level information.
     */
    public LevelInformation getLevelInformation() {
        return this.levelInformation;
    }

    /**
     * This method is a getter for the sprite collection.
     *
     * @return the sprite collection
     */
    public SpriteCollection getGameSpriteCollection() {
        return this.sprites;
    }

    /**
     * @return the score tracking listener.
     */
    public ScoreTrackingListener getScoreTrackingListener() {
        return this.scoreIndicator.getScoreTrackingListener();
    }

    /**
     * The method will add a collidable to the game.
     *
     * @param c is the argument to add.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * The method will add a Sprite.Sprite to the game.
     *
     * @param s is the argument to add.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * The method will create the frame for the game screen.
     * The method will also insert the blocks to the game.
     */
    public void initializeFrame() {
        Block top = new Block(new Rectangle(new Point(0, SCREEN_FRAME_SIZE * 2),
                SCREEN_WIDTH, SCREEN_FRAME_SIZE), Color.gray);
        Block bot = new Block(new Rectangle(new Point(0, SCREEN_HEIGHT + SCREEN_FRAME_SIZE),
                SCREEN_WIDTH, SCREEN_FRAME_SIZE), Color.gray);

        // add the bottom to the hitter list. When the ball hit it he should disappear.
        bot.addHitListener(ballRemover);

        Block right = new Block(new Rectangle(new Point(0, SCREEN_HEIGHT),
                SCREEN_FRAME_SIZE, SCREEN_HEIGHT - (2 * SCREEN_FRAME_SIZE)), Color.gray);
        Block left = new Block(new Rectangle(new Point(SCREEN_WIDTH - SCREEN_FRAME_SIZE,
                SCREEN_HEIGHT), SCREEN_FRAME_SIZE,
                SCREEN_HEIGHT - (2 * SCREEN_FRAME_SIZE)), Color.gray);
        Block scoreBoard = new Block(new Rectangle(new Point(0, SCREEN_FRAME_SIZE),
                SCREEN_WIDTH, SCREEN_FRAME_SIZE), Color.white);
        top.addToGame(this);
        bot.addToGame(this);
        right.addToGame(this);
        left.addToGame(this);
        scoreBoard.addToGame(this);
    }

    /**
     * The method will create the balls for the initialize method.
     */
    private void createBalls() {
        try {
            for (int i = 0; i < this.levelInformation.numberOfBalls(); i++) {
                Ball ball1 = new Ball((float) SCREEN_WIDTH / 2, SCREEN_HEIGHT - 3 * SCREEN_FRAME_SIZE,
                        DEFAULT_RADIUS_SIZE, Color.white, this.environment);
                ball1.setVelocity(this.levelInformation.initialBallVelocities().get(i));
                ball1.addToGame(this);
                ballRemover.getRemainingBalls().increase(NUM_TO_INCREASE);
            }
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    /**
     * The method will initialize a new game.
     * The method will create the surface on the screen and create all the arguments in the game.
     */
    public void initialize() {

        sprites.addSprite(this.levelInformation.getBackground());

        // creating the balls.
        createBalls();

        // initialize the frame of the game.
        initializeFrame();

        for (int i = 0; i < this.levelInformation.blocks().size(); i++) {
            this.levelInformation.blocks().get(i).addToGame(this);
            this.levelInformation.blocks().get(i).addHitListener(blockRemover);
            this.blockRemover.getRemainingBlocks().increase(NUM_TO_INCREASE);
        }

        // add the score board.
        this.scoreIndicator.addToGame(this);
        this.lives.addToGame(this);

        LevelIndicator levelIndicator = new LevelIndicator(this.levelInformation);
        levelIndicator.addToGame(this);
        try {
            this.paddle.addToGame(this);
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    /**
     * The method is initialized the paddle and the balls.
     * The method take place when the game has to reset after all the ball went out of the screen.
     */
    public void initializeBallAndPaddle() {
        //sprites.addSprite(this.levelInformation.getBackground());

        // creating the balls.
        List<Velocity> velocityList1;
        velocityList1 = this.levelInformation.createVelocity();
        this.levelInformation.setBallVelocity(velocityList1);
        createBalls();

        // add the score board.
        this.scoreIndicator.addToGame(this);
        this.lives.addToGame(this);

        LevelIndicator levelIndicator = new LevelIndicator(this.levelInformation);
        levelIndicator.addToGame(this);

        this.paddle.setPaddle(new Rectangle(new Point((float) (SCREEN_WIDTH / 2)
                - this.levelInformation.paddleWidth() / (float) 2, SCREEN_HEIGHT - SCREEN_FRAME_SIZE),
                this.levelInformation.paddleWidth(), this.levelInformation.paddleHeight()));
    }

    /**
     * The method will run the game.
     * The method will start the animation loop.
     */
    public void run() {
        // countdown before turn starts.
        this.runner.run(new CountdownAnimation(NUMBER_OF_SECONDS, COUNT_FROM, this.getGameSpriteCollection()));
        this.running = true;
        // use our runner to run the current animation -- which is one turn of the game.
        this.runner.run(this);
    }

    /**
     * The method will remove the collidable from the collidable list.
     *
     * @param c is the collidable.
     */
    public void removeCollidable(Collidable c) {
        List<Collidable> l = this.environment.getCollidableList();
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).equals(c)) {
                l.remove(l.get(i));
            }
        }
    }

    /**
     * The method will remove the sprite from the sprite list.
     *
     * @param s is the sprite.
     */
    public void removeSprite(Sprite s) {
        List<Sprite> l = this.sprites.getSpriteList();
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).equals(s)) {
                l.remove(l.get(i));
            }
        }
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;

        // checking the number of blocks.
        int num = this.getLevelInformation().blocks().size()
                - this.getLevelInformation().numberOfBlocksToRemove();

        if (this.blockRemover.getRemainingBlocks().getValue() == num
                || this.blockRemover.getRemainingBlocks().getValue() == 0) {
            this.getScoreTrackingListener().getCounterCurrentScore().increase(ADD_IN_WIN);
            this.running = false;
        }

        // checking the number of balls.
        if (this.ballRemover.getRemainingBalls().getValue() == 0) {
            this.running = false;
        }

        // checking if the user pressed on "p" and open the pause screen.
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY,
                    new PauseScreen()));
        }
        long startTime = System.currentTimeMillis(); // timing

        // printing all the sprites.
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();

        // timing
        long usedTime = System.currentTimeMillis() - startTime;
        long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
        if (milliSecondLeftToSleep > 0) {
            sleeper.sleepFor(milliSecondLeftToSleep);
        }
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }
}
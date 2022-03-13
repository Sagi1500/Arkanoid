package level;

import animation.Cloud;
import collidable.Block;
import collidable.Velocity;
import game.GameLevel;
import shape.Point;
import shape.Rectangle;
import sprite.Background;
import sprite.Sprite;

import java.util.List;
import java.util.ArrayList;
import java.awt.Color;

/**
 * FinalFourLevel class.
 * The class will create the FinalFourLevel level.
 *
 * @author Sagi Wilentzik.
 */
public class FinalFourLevel implements LevelInformation {

    private String levelName;
    private int numberOfBalls;
    private int paddleSpeed;
    private int paddleWidth;
    private int paddleHeight;
    private Sprite background;
    private List<Block> blockList;
    private List<Velocity> velocityList;


    public static final int NUMBER_OF_BALLS = 3;
    public static final int NUMBER_OF_BLOCKS = 15;
    public static final int PADDLE_WIDTH = 80;
    public static final int PADDLE_HEIGHT = 20;
    public static final int PADDLE_SPEED = 8;
    public static final int BLOCK_WIDTH = 50;
    public static final int BLOCK_HEIGHT = 20;
    public static final int BALL_VELOCITY = 5;


    private static Color[] colorsArray = new Color[]{Color.cyan, Color.pink, Color.white,
            Color.green, Color.yellow, Color.red, Color.DARK_GRAY};

    /**
     * Constructor.
     */
    public FinalFourLevel() {
        this.levelName = "Final Four";
        this.background = createBackground();
        this.numberOfBalls = NUMBER_OF_BALLS;
        this.blockList = createBlocks();
        this.velocityList = createVelocity();
        this.paddleWidth = PADDLE_WIDTH;
        this.paddleHeight = PADDLE_HEIGHT;
        this.paddleSpeed = PADDLE_SPEED;
    }

    @Override
    public List<Block> createBlocks() {
        List<Block> blockList1 = new ArrayList<>();

        for (int i = 0; i < colorsArray.length; i++) {
            for (int j = 1; j <= NUMBER_OF_BLOCKS; j++) {
                Block b1 = new Block(new Rectangle(new Point(GameLevel.SCREEN_WIDTH
                        - GameLevel.SCREEN_FRAME_SIZE - (BLOCK_WIDTH * j), (double) (GameLevel.SCREEN_WIDTH / 3)
                        - (BLOCK_HEIGHT * i)), BLOCK_WIDTH, BLOCK_HEIGHT), colorsArray[i]);
                blockList1.add(b1);
            }
        }
        return blockList1;
    }

    @Override
    public List<Velocity> createVelocity() {
        List<Velocity> velocityList1 = new ArrayList<>();
        for (int i = 0; i < numberOfBalls; i++) {
            Velocity v = Velocity.fromAngleAndSpeed(140 + (40 * i), -BALL_VELOCITY);
            velocityList1.add(v);
        }
        return velocityList1;
    }

    @Override
    public Sprite createBackground() {
        Background background1 = new Background(new Rectangle(new Point(0, GameLevel.SCREEN_HEIGHT),
                GameLevel.SCREEN_WIDTH, GameLevel.SCREEN_HEIGHT), Color.decode("#1788d0"));
        return new Cloud(background1);
    }

    @Override
    public void setBallVelocity(List<Velocity> velocity) {
        this.velocityList = velocity;
    }

    @Override
    public int numberOfBalls() {
        return numberOfBalls;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        return this.velocityList;
    }

    @Override
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    @Override
    public int paddleWidth() {
        return this.paddleWidth;
    }

    @Override
    public int paddleHeight() {
        return this.paddleHeight;
    }

    @Override
    public String levelName() {
        return this.levelName;
    }

    @Override
    public Sprite getBackground() {
        return this.background;
    }

    @Override
    public List<Block> blocks() {
        return this.blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.blockList.size();
    }
}

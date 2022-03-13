package level;

import animation.Sun;
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
 * WideEasyLevel class.
 * The class will create the WideEasyLevel level.
 *
 * @author Sagi Wilentzik.
 */
public class WideEasyLevel implements LevelInformation {

    private String levelName;
    private int numberOfBalls;
    private int paddleSpeed;
    private int paddleWidth;
    private int paddleHeight;
    private Sprite background;
    private List<Block> blockList;
    private List<Velocity> velocityList;


    public static final int NUMBER_OF_BALLS = 10;
    public static final int PADDLE_WIDTH = 600;
    public static final int PADDLE_HEIGHT = 20;
    public static final int PADDLE_SPEED = 1;
    public static final int BLOCK_WIDTH = 50;
    public static final int BLOCK_HEIGHT = 20;
    public static final int BALL_VELOCITY = 5;


    private static Color[] colorsArray = new Color[]{Color.cyan, Color.pink, Color.blue,
            Color.green, Color.yellow, Color.orange, Color.RED};

    /**
     * Constructor.
     */
    public WideEasyLevel() {
        this.levelName = "Wide Easy";
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
        int counter = 1;
        for (Color color : colorsArray) {
            if (color != Color.green) {
                for (int j = 0; j < 2; j++) {
                    Block b = new Block(new Rectangle(new Point(GameLevel.SCREEN_WIDTH
                            - GameLevel.SCREEN_FRAME_SIZE - (counter * BLOCK_WIDTH), 250),
                            BLOCK_WIDTH, BLOCK_HEIGHT), color);
                    blockList1.add(b);
                    counter++;
                }
            } else {
                for (int j = 0; j < 3; j++) {
                    Block b = new Block(new Rectangle(new Point(GameLevel.SCREEN_WIDTH
                            - GameLevel.SCREEN_FRAME_SIZE - (counter * BLOCK_WIDTH), 250),
                            BLOCK_WIDTH, BLOCK_HEIGHT), color);
                    blockList1.add(b);
                    counter++;
                }
            }
        }
        return blockList1;
    }

    @Override
    public List<Velocity> createVelocity() {
        List<Velocity> velocityList1 = new ArrayList<>();
        for (int i = 0; i < numberOfBalls; i++) {
            Velocity v = Velocity.fromAngleAndSpeed(125 + (10 * (i + 1)), -BALL_VELOCITY);
            velocityList1.add(v);
        }
        return velocityList1;
    }

    @Override
    public Sprite createBackground() {
        Background background1 = new Background(new Rectangle(new Point(0, GameLevel.SCREEN_HEIGHT),
                GameLevel.SCREEN_WIDTH, GameLevel.SCREEN_HEIGHT), Color.white);
        return new Sun(background1);
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

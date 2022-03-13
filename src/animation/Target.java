package animation;

import biuoop.DrawSurface;
import game.GameLevel;
import level.DirectHitLevel;
import sprite.Sprite;

import java.awt.Color;


/**
 * Animation.
 * Target class. This class will draw a target on the screen.
 *
 * @author Sagi Wilentzik.
 */
public class Target implements Sprite {

    private Sprite sprite;

    /**
     * Constructor.
     *
     * @param sprite1 is the sprite name.
     */
    public Target(Sprite sprite1) {
        this.sprite = sprite1;
    }

    @Override
    public void drawOn(DrawSurface d) {

        // draw on the sprite.
        sprite.drawOn(d);

        // drawing the target.
        d.setColor(Color.blue);
        d.drawLine(GameLevel.SCREEN_WIDTH / 2 - GameLevel.SCREEN_FRAME_SIZE + 35,
                GameLevel.SCREEN_HEIGHT / 3 + 170,
                GameLevel.SCREEN_WIDTH / 2 - GameLevel.SCREEN_FRAME_SIZE + 35,
                GameLevel.SCREEN_HEIGHT / 3 - 150);

        d.drawLine(DirectHitLevel.SQUARE_Y_VALUE + DirectHitLevel.SQUARE_VALUE,
                (DirectHitLevel.SQUARE_Y_VALUE - DirectHitLevel.SQUARE_VALUE / 2),
                DirectHitLevel.SQUARE_X_VALUE + DirectHitLevel.SQUARE_Y_VALUE,
                (DirectHitLevel.SQUARE_Y_VALUE - DirectHitLevel.SQUARE_VALUE / 2));

        d.drawCircle(DirectHitLevel.SQUARE_X_VALUE + (DirectHitLevel.SQUARE_VALUE / 2),
                DirectHitLevel.SQUARE_Y_VALUE - (DirectHitLevel.SQUARE_VALUE / 2), 60);
        d.drawCircle(DirectHitLevel.SQUARE_X_VALUE + (DirectHitLevel.SQUARE_VALUE / 2),
                DirectHitLevel.SQUARE_Y_VALUE - (DirectHitLevel.SQUARE_VALUE / 2), 100);
        d.drawCircle(DirectHitLevel.SQUARE_X_VALUE + (DirectHitLevel.SQUARE_VALUE / 2),
                DirectHitLevel.SQUARE_Y_VALUE - (DirectHitLevel.SQUARE_VALUE / 2), 140);
    }

    @Override
    public void timePassed() {
    }
}

package animation;

import biuoop.DrawSurface;
import game.GameLevel;
import sprite.Sprite;

import java.awt.Color;


/**
 * Animation.
 * Sun class. This class will draw a sun on the screen.
 *
 * @author Sagi Wilentzik.
 */
public class Sun implements Sprite {

    private Sprite sprite;

    /**
     * Constructor.
     *
     * @param sprite1 is the sprite name.
     */
    public Sun(Sprite sprite1) {
        this.sprite = sprite1;
    }

    @Override
    public void drawOn(DrawSurface d) {

        // draw on the sprite.
        sprite.drawOn(d);

        // drawing the sun.
        d.setColor(Color.decode("#efe7b0"));
        d.fillCircle(130, 130, 50);
        for (int i = 0; i < 80; i++) {
            d.drawLine(130, 130, GameLevel.SCREEN_WIDTH - GameLevel.SCREEN_FRAME_SIZE - (10 * i), 250);
        }
        d.setColor(Color.decode("#ecd749"));
        d.fillCircle(130, 130, 40);
        d.setColor(Color.decode("#ffe118"));
        d.fillCircle(130, 130, 30);

    }

    @Override
    public void timePassed() {
    }
}

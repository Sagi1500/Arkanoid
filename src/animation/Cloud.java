package animation;

import biuoop.DrawSurface;
import sprite.Sprite;

import java.awt.Color;


/**
 * Animation.
 * Cloud class. This class will draw a cloud on the screen.\
 *
 * @author Sagi Wilentzik.
 */
public class Cloud implements Sprite {

    private Sprite sprite;

    /**
     * Constructor.
     *
     * @param sprite1 is the sprite name.
     */
    public Cloud(Sprite sprite1) {
        this.sprite = sprite1;
    }

    @Override
    public void drawOn(DrawSurface d) {
        // draw on the sprite.
        sprite.drawOn(d);

        // drawing the cloud.
        int xCenter = 150;
        int yCenter = 400;
        for (int j = 0; j < 2; j++) {
            for (int i = 0; i < 12; i++) {
                d.setColor(Color.decode("#6cb4e1"));
                d.drawLine(xCenter + 20 - (i * 7), yCenter, xCenter - (i * 7), 600);
            }
            d.setColor(Color.decode("#aaaaaa"));
            d.fillCircle(xCenter, yCenter, 30);
            d.setColor(Color.decode("#aaaaaa"));
            d.fillCircle(xCenter + 25, yCenter + 20, 30);
            d.setColor(Color.decode("#bbbbbb"));
            d.fillCircle(xCenter - 25, yCenter - 20, 30);
            d.setColor(Color.decode("#cccccc"));
            d.fillCircle(xCenter - 40, yCenter, 30);
            d.setColor(Color.decode("#cccccc"));
            d.fillCircle(xCenter - 20, yCenter + 30, 30);

            xCenter = 700;
            yCenter = 450;
        }

    }

    @Override
    public void timePassed() {
    }
}

package listener;

import biuoop.DrawSurface;
import game.GameLevel;
import sprite.Sprite;

import java.awt.Color;

/**
 * Listener.
 * Counter class.
 *
 * @author Sagi Wilentzik.
 */
public class Counter implements Sprite {

    private int counter;

    public static final int Y_FOR_LIFE = 15;
    public static final int X_FOR_LIFE = 100;
    public static final int FONT_SIZE = 15;

    /**
     * Constructor.
     */
    public Counter() {
        this.counter = 0;
    }


    /**
     * Constructor.
     *
     * @param num is the initial amount for the counter.
     */
    public Counter(int num) {
        this.counter = num;
    }

    /**
     * add number to current count.
     *
     * @param number is the amount to add.
     */
    public void increase(int number) {
        this.counter += number;
    }

    /**
     * subtract number from current count.
     *
     * @param number is the amount to subtract.
     */
    public void decrease(int number) {
        this.counter -= number;
    }

    /**
     * @return current count.
     */
    public int getValue() {
        return this.counter;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.drawText(X_FOR_LIFE, Y_FOR_LIFE,
                "Lives:" + this.getValue(), FONT_SIZE);
    }

    @Override
    public void timePassed() {

    }

    /**
     * add the level indicator to the sprite list.
     *
     * @param game is the game.
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }
}
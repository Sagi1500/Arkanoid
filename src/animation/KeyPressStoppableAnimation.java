package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * Animation.
 * KeyPressStoppableAnimation class.
 *
 * @author Sagi Wilentzik.
 */
public class KeyPressStoppableAnimation implements Animation {

    private KeyboardSensor keyboardSensor;
    private String key;
    private Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed;

    /**
     * Constructor.
     *
     * @param sensor    is the keyboard sensor.
     * @param key       is the key to stop the animation.
     * @param animation is the animation.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.keyboardSensor = sensor;
        this.key = key;
        this.animation = animation;
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    @Override
    public void doOneFrame(DrawSurface d) {

        // check if the key is pressed.
        if (this.keyboardSensor.isPressed(key)) {
            this.stop = true;
        }
        if (!this.isAlreadyPressed) {
            animation.doOneFrame(d);
        }
        this.isAlreadyPressed = false;
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
import animation.AnimationRunner;
import biuoop.GUI;
import game.GameFlow;
import level.DirectHitLevel;
import level.LevelInformation;
import level.FinalFourLevel;
import level.WideEasyLevel;
import level.Green3Level;

import java.util.ArrayList;
import java.util.List;

/**
 * ArkanoidGame class is initialize and running the game.
 *
 * @author Sagi Wilentzik.
 */
public class ArkanoidGame {

    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 600;

    /**
     * main method for Arkanoid game.
     *
     * @param args are the main arguments.
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Arkanoid game", SCREEN_WIDTH, SCREEN_HEIGHT);
        AnimationRunner animationRunner = new AnimationRunner(gui);
        GameFlow game = new GameFlow(animationRunner, gui.getKeyboardSensor());
        List<LevelInformation> levelInformationalList = new ArrayList<>();

        // reading levels from main arguments.
        if (args.length != 0) {
            for (String arg : args) {
                switch (arg) {
                    case "1":
                        levelInformationalList.add(new DirectHitLevel());
                        break;
                    case "2":
                        levelInformationalList.add(new WideEasyLevel());
                        break;
                    case "3":
                        levelInformationalList.add(new Green3Level());
                        break;
                    case "4":
                        levelInformationalList.add(new FinalFourLevel());
                        break;
                    default:
                }
            }
        }

        /* checking the case that all the arguments are illegal. if all arguments are illegal,
         initialize with each level 1 time. In addition, if there are no arguments the list will be initialize with
         each level one time.
         */
        if (levelInformationalList.isEmpty()) {
            levelInformationalList.add(new DirectHitLevel());
            levelInformationalList.add(new WideEasyLevel());
            levelInformationalList.add(new Green3Level());
            levelInformationalList.add(new FinalFourLevel());
        }

        // running the game.
        game.runLevels(levelInformationalList);
        gui.close();
    }
}

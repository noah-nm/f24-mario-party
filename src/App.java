import java.util.ArrayList;

import DLibX.DConsole;
import menus.MainMenu;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;
import utils.AbstractGamepad;
import utils.DebugGamepad;
import utils.Gamepad;

public class App {

    // list vars
    ArrayList<AbstractGamepad> gamepads = new ArrayList<>();
    Controller[] controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();

    DConsole dc = new DConsole("Mario Party", 1200, 800, true);

    boolean running = true;

    public static void main(String[] args) throws Exception {
        App app = new App();
        app.run();
    }

    public void run() {
        gamepads.add(new DebugGamepad());

        // initialization
        initGameControllers();
        dc.setResizable(false);
        dc.setRenderingHints(DConsole.RENDER_HIGH_QUALITY);
        // main menu
        MainMenu mainMenu = new MainMenu(dc, gamepads);
        mainMenu.play();

        // clear (currently for testing purposes)
        dc.clear();

        dc.redraw();
    }

    /**
     * Initializes game controllers for use
     */
    public void initGameControllers() {

        // find gamepads
        for (Controller controller : controllers) {

            if (controller.getType() == Controller.Type.GAMEPAD) {
                System.out.println(controller.getName() + " is a gamepad");
                // add gamepad to list
                gamepads.add(new Gamepad(controller));
            }

        }

        if (gamepads.size() == 0) {
            System.out.println("No controllers found! Plug in at least one controller to run!");
            System.exit(1);
        }

    }
}

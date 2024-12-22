import java.util.ArrayList;

import DLibX.DConsole;
import menus.MainMenu;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;
import utils.Gamepad;
import menus.*;
import menus.PlayerSelect;

public class App {

    // lists
    ArrayList<Gamepad> gamepads = new ArrayList<>();
    Controller[] controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();

    DConsole dc = new DConsole("Mario Party", 1200, 800, true);

    boolean running = true;

    public static void main(String[] args) throws Exception {
        App app = new App();
        app.run();
    }

    public void run() {
        while (running) {

            // initialization
            initGameControllers();
            dc.setResizable(false);

            // main menu
            MainMenu mainMenu = new MainMenu(dc, gamepads);
            mainMenu.play();

            // player select
            PlayerSelect playerSelect = new PlayerSelect(dc, gamepads);
            playerSelect.play();

            // define new assigned players array list, this array list should be used in place of the gamepads array list for further screens
            ArrayList<Gamepad> players = playerSelect.getPlayers();

            // used to test player selection screen, can be removed once another screen is added here
            while (true) {
                dc.clear();
                dc.redraw();
            }
        }
    }

    /**
     * Initializes game controllers for use
     * 
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

    }
}

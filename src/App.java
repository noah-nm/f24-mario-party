import java.util.ArrayList;

import DLibX.DConsole;
import games.Jumping;
import menus.MainMenu;
import menus.PlayerSelect;
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
        initGameControllers();

        if(gamepads.size() == 0) {
            gamepads.add(new DebugGamepad());
        }

        // initialization
        dc.setResizable(false);
        dc.setRenderingHints(DConsole.RENDER_HIGH_QUALITY);

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

            // define new assigned players array, this array should be used in place of the gamepads array list for further screens
            AbstractGamepad[] players = playerSelect.getPlayers();

            Jumping jumping = new Jumping(dc, players, controllers);

            // used to test player selection screen, can be removed once another screen is added here
            while (true) {
                dc.clear();
                jumping.play();
                dc.redraw();
            }
        }
    }

    /**
     * Initializes game controllers for use
     */
    public void initGameControllers() {

        // find gamepads
        for (Controller controller : controllers) {

            if (controller.getType() == Controller.Type.GAMEPAD) {
                System.out.println(controller.getName() + " found");
                // add gamepad to list
                gamepads.add(new Gamepad(controller));
            }

        }
    }
}

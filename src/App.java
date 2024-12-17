import java.util.ArrayList;
import DLibX.DConsole;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;
import utils.Gamepad;
import menus.*;

public class App {

    // list vars
    ArrayList<Menu> menus = new ArrayList<>();
    ArrayList<Gamepad> gamepads = new ArrayList<>();
    Controller[] controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();

    DConsole dc = new DConsole("Mario Party", 1200, 800, true);

    boolean running = true;

    public static void main(String[] args) throws Exception {
        App app = new App();
        app.run();
    }

    public void run() {
        // initialization
        initGameControllers();
        dc.setResizable(false);

        // main menu
        MainMenu mainMenu = new MainMenu(dc, gamepads, controllers);
        mainMenu.play();

        // clear (currently for testing purposes)
        dc.clear();

        dc.redraw();
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

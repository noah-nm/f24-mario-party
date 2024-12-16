import java.util.ArrayList;

import DLibX.DConsole;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;
import utils.Gamepad;

public class App {
    ArrayList<Gamepad> gamepads = new ArrayList<>();
    Controller[] controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();

    public static void main(String[] args) throws Exception {
        App app = new App();
        app.run();
    }

    public void run() {
        // init controllers
        initGameControllers();

        while (true) {
            // ask controller for new data
            for (Controller controller : controllers) {
                controller.poll();
            }

            // handle input below

            if (gamepads.get(0).getAButton()) {
                System.out.println("A is being pressed");
            }

            DConsole.pause(10);
        }
    }

    /**
     * Initializes game controllers for use
     * 
     */
    public void initGameControllers() {
        // get all controllers

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

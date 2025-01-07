package marioparty;

import java.util.ArrayList;

import DLibX.DConsole;
import marioparty.games.Game;
import marioparty.games.GameSelect;
import marioparty.menus.MainMenu;
import marioparty.menus.PlayerSelect;
import marioparty.utils.Gamepad;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

public class App {

    // lists
    ArrayList<Gamepad> gamepads = new ArrayList<>();
    Controller[] controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();

    DConsole dc = new DConsole("Mario Party", 1200, 800, true);

    public static Game currentGame;
    private static ArrayList<Game> games = new ArrayList<>();

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
        MainMenu mainMenu = new MainMenu(dc, gamepads);
        mainMenu.play();

        // player select
        PlayerSelect playerSelect = new PlayerSelect(dc, gamepads);
        playerSelect.play();

        // define new assigned players array, this array should be used in place of the
        // gamepads array list for further screens
        Gamepad[] players = playerSelect.getPlayers();

        // horrors beyond human comprehension
        currentGame = new GameSelect(dc, players, controllers, games
            .stream()                       // convenience method
            .map(game -> game.getName())    // turn it into just the names
            .toList());                     // turn it into a list

        while (running) {
            dc.clear();

            currentGame.play();
            dc.redraw();
            DConsole.pause(15);
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

    public static void switchGame(Game game) {
        App.currentGame = game;
    }

    public static void registerGameData(Game game) {
        System.out.println("registered " + game.getName());
        App.games.add(game);
    }
}

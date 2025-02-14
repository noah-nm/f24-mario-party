package marioparty;

import java.util.ArrayList;

import DLibX.DConsole;
import marioparty.games.Game;
import marioparty.games.GameSelect;
import marioparty.menus.Leaderboard;
import marioparty.menus.MainMenu;
import marioparty.menus.PlayerSelect;
import marioparty.utils.AbstractGamepad;
import marioparty.utils.DebugGamepad;
import marioparty.utils.Gamepad;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

public class App {

    // list vars
    ArrayList<AbstractGamepad> gamepads = new ArrayList<>();
    Controller[] controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();
    int[] scores = new int[4];
    static Game currentGame;

    DConsole dc = new DConsole("Mario Party", 1200, 800, true);

    boolean running = true;

    public static void main(String[] args) throws Exception {
        App app = new App();
        app.run();
    }

    public void run() {
        initGameControllers();

        // initialization
        dc.setResizable(false);
        dc.setRenderingHints(DConsole.RENDER_HIGH_QUALITY);

        // main menu
        MainMenu mainMenu = new MainMenu(dc, gamepads);
        mainMenu.play();

        // player select
        PlayerSelect playerSelect = new PlayerSelect(dc, gamepads);
        playerSelect.play();

        // define new assigned players array, this array should be used in place of the
        // gamepads array list for further screens
        AbstractGamepad[] players = playerSelect.getPlayers();

        // leaderboard
        Leaderboard leaderboard = new Leaderboard(dc, players, scores);
        leaderboard.play();

        currentGame = new GameSelect(dc, players, scores);

        // main game loop
        while (running) {
            dc.clear();
            currentGame.play();
            dc.redraw();
        }
    }

    /**
     * Initializes game controllers for use
     */
    public void initGameControllers() {

        // find gamepads
        for (Controller controller : controllers) {

            if (controller.getType() == Controller.Type.GAMEPAD) {
                // add gamepad to list
                gamepads.add(new Gamepad(controller));
            }

        }
    }

    public static void switchGame(Game game) {
        App.currentGame = game;
    }
}
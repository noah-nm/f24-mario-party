package games;

<<<<<<< HEAD
import java.util.ArrayList;
import utils.Gamepad;
=======
>>>>>>> 73e3951fed22b850782984acc4d0af9fbe63bb17
import DLibX.DConsole;
import utils.AbstractGamepad;

public abstract class Game {

    protected DConsole dc;
    protected AbstractGamepad[] playerControllers;

    // the players param should be the array list defined in the main loop after PlayerSelect.play() is done
    public Game(DConsole dc, AbstractGamepad[] playerControllers) {
        this.dc = dc;
        this.playerControllers = playerControllers;
    }

    /**
     * Runs a given game
     */
    public abstract void play();

}

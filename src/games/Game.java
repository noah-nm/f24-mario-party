package games;

import java.util.ArrayList;

import DLibX.DConsole;
import utils.Gamepad;

public abstract class Game {

    protected DConsole dc;
    protected ArrayList<Gamepad> gamepads;

    public Game(DConsole dc, ArrayList<Gamepad> gamepads) {
        this.dc = dc;
        this.gamepads = gamepads;
    }

    /**
     * Runs a given game
     */
    public abstract void play();

}

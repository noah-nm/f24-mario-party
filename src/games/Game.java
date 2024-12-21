package games;

import java.util.ArrayList;
import utils.Gamepad;
import DLibX.DConsole;
import net.java.games.input.Controller;

public abstract class Game {

    protected DConsole dc;
    protected ArrayList<Gamepad> players;
    protected Controller[] controllers;

    // the players param should be the array list defined in the main loop after PlayerSelect.play() is done
    public Game(DConsole dc, ArrayList<Gamepad> players, Controller[] controllers) {
        this.dc = dc;
        this.players = players;
        this.controllers = controllers;
    }

    /**
     * runs a given game
     * 
     */
    public abstract void play();

}

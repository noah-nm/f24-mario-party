package games;

import java.util.ArrayList;
import utils.Gamepad;
import DLibX.DConsole;
import net.java.games.input.Controller;

public abstract class Game {

    protected DConsole dc;
    protected ArrayList<Gamepad> gamepads;
    protected Controller[] controllers;

    public Game(DConsole dc, ArrayList<Gamepad> gamepads, Controller[] controllers) {
        this.dc = dc;
        this.gamepads = gamepads;
        this.controllers = controllers;
    }

    /**
     * runs a given game
     * 
     */
    public abstract void play();

}

package marioparty.games;

import DLibX.DConsole;
import marioparty.App;
import marioparty.utils.Gamepad;
import net.java.games.input.Controller;

public abstract class Game {

    protected DConsole dc;
    protected Gamepad[] playerControllers;
    protected Controller[] controllers;

    // the players param should be the array list defined in the main loop after PlayerSelect.play() is done
    public Game(DConsole dc, Gamepad[] playerControllers, Controller[] controllers) {
        this.dc = dc;
        this.playerControllers = playerControllers;
        this.controllers = controllers;
    }
    
    public abstract String getName();

    /**
     * runs a given game
     * 
     */
    public abstract void play();
}

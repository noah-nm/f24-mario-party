package games;

import DLibX.DConsole;
import utils.AbstractGamepad;

public abstract class Game {

    protected DConsole dc;
    protected Gamepad[] playerControllers;
    protected Controller[] controllers;
    protected int[] scores;

    // the players param should be the array list defined in the main loop after PlayerSelect.play() is done
    public Game(DConsole dc, Gamepad[] playerControllers, Controller[] controllers, int[] scores) {
        this.dc = dc;
        this.playerControllers = playerControllers;
        this.controllers = controllers;
        this.scores = scores;
    }

    /**
     * Runs a given game
     */
    public abstract void play();

}

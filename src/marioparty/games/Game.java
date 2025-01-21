package marioparty.games;

import DLibX.DConsole;
import marioparty.utils.AbstractGamepad;

public abstract class Game {

    protected DConsole dc;
    protected AbstractGamepad[] playerControllers;
    protected int[] scores;

    // the players param should be the array list defined in the main loop after PlayerSelect.play() is done
    public Game(DConsole dc, AbstractGamepad[] playerControllers, int[] scores) {
        this.dc = dc;
        this.playerControllers = playerControllers;
        this.scores = scores;
    }

    /**
     * Runs a given game
     */
    public abstract void play();
}

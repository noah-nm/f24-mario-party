package marioparty.games;

import DLibX.DConsole;
import marioparty.utils.AbstractGamepad;

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

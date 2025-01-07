package marioparty.games;

import DLibX.DConsole;
import marioparty.utils.Gamepad;
import net.java.games.input.Controller;

public class GameSelect extends Game {

    public GameSelect(DConsole dc, Gamepad[] playerGamepads, Controller[] controllers) {
        super(dc, playerGamepads, controllers);
    }

    @Override
    public void play() {
        this.dc.fillEllipse(this.dc.getWidth() / 2, this.dc.getHeight() / 2, 100, 100);
    }
    
}

package marioparty.games;

import java.awt.Color;
import java.awt.Font;
import java.util.List;

import DLibX.DConsole;
import marioparty.utils.Gamepad;
import net.java.games.input.Controller;

public class GameSelect extends Game {
    private List<String> names;

    public GameSelect(DConsole dc, Gamepad[] playerGamepads, Controller[] controllers, List<String> names) {
        super(dc, playerGamepads, controllers);
        this.names = names;
    }

    @Override
    public void play() {

        Font arialTitle = new Font("arial", 1, 100);
        this.dc.drawString("Hi", 50, 200);

        for (String name : names) {
            this.dc.setFont(arialTitle);
            this.dc.setPaint(Color.BLACK);
            this.dc.drawString(name, 50, 200);
        }
        this.dc.fillRect(this.dc.getWidth() / 2, this.dc.getHeight() / 2, 100, 100);
    }

    @Override
    public String getName() {
        return "Game Select";
    }
    
}

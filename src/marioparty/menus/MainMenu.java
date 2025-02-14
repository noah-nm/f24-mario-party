package marioparty.menus;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import DLibX.DConsole;
import marioparty.utils.AbstractGamepad;
import marioparty.utils.Sound;

public class MainMenu extends Menu {

    public MainMenu(DConsole dc, ArrayList<AbstractGamepad> gamepads) {
        super(dc, gamepads);
    }

    /**
     * Runs the main menu screen
     */
    public void play() {
        boolean exitMenu = false;

        Sound bgMusic = new Sound("title.mp3");

        bgMusic.play();
        bgMusic.setLooping(true);

        while (!exitMenu) {

            // poll controller output
            for (AbstractGamepad gamepad : gamepads) {
                gamepad.poll();
            }

            this.dc.clear();

            // background
            this.dc.setOrigin(DConsole.ORIGIN_TOP_LEFT);
            this.dc.setPaint(new Color(121, 224, 218, 200));
            this.dc.fillRect(0, 0, this.dc.getWidth(), this.dc.getHeight());

            // title text
            Font arialTitle = new Font("arial", 1, 100);
            this.dc.setPaint(Color.RED);
            this.dc.setOrigin(DConsole.ORIGIN_CENTER);
            this.dc.setFont(arialTitle);
            this.dc.drawString("Mario Party!", this.dc.getWidth() / 2, this.dc.getHeight() / 2);

            // "press START to play" test
            Font arialSmall = new Font("arial", 1, 25);
            this.dc.setFont(arialSmall);
            this.dc.drawString("Press START to Play", this.dc.getWidth() / 2, (this.dc.getHeight() / 2) + 100);

            // exit main menu if start button is pressed
            if (this.gamepads.get(0).getStartButton()) {
                exitMenu = true;
                bgMusic.stop();
            }

            dc.redraw();
        }
    }
}

package marioparty.games;

import DLibX.DConsole;
import marioparty.utils.AbstractGamepad;
import java.awt.Color;
import java.awt.Font;

public class ClimbTheRope extends Game {

    public ClimbTheRope(DConsole dc, AbstractGamepad[] playerControllers, int[] scores) {
        super(dc, playerControllers, scores);
    }

    public void play() {

        boolean playing = true;

        while (playing) {

            // poll gamepads
            for (AbstractGamepad playerController : playerControllers) {
                playerController.poll();
            }

            this.drawGui();

        }

    }

    private void drawGui() {

        // fonts
        Font arialTitle = new Font("arial", 1, 80);
        Font arialSmall = new Font("arial", 1, 30);
        Font arialMedium = new Font("arial", 1, 40);
        Font arialVerySmall = new Font("arial", 1, 20);

        // colors
        Color lightBlue = new Color(121, 224, 218, 200);

        this.dc.redraw();

        // background
        this.dc.setOrigin(DConsole.ORIGIN_TOP_LEFT);
        this.dc.setPaint(lightBlue);
        this.dc.drawRect(0, 0, this.dc.getWidth(), this.dc.getHeight());

        // title / instructions
        this.dc.setOrigin(DConsole.ORIGIN_CENTER);
        this.dc.setFont(arialTitle);
        this.dc.setPaint(Color.WHITE);
        this.dc.drawString("Climb The Rope", this.dc.getWidth() / 2, this.dc.getHeight() / 2 - 200);

        DConsole.pause(20);
    }

    private void giveScores() {

    }

}

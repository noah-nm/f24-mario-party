package marioparty.games;

import java.awt.Color;

import DLibX.DConsole;
import marioparty.App;
import marioparty.menus.Leaderboard;
import marioparty.utils.AbstractGamepad;

public class HotPotato extends Game {

    public HotPotato(DConsole dc, AbstractGamepad[] playerControllers, int[] scores) {
        super(dc, playerControllers, scores);
    }

    /**
     * Runs a given game
     */
    public void play() {

        boolean playing = true;
        int potatoX = 80;
        int potatoY = 400;
        long startTime = System.currentTimeMillis();

        while (playing) {
            dc.clear();

            long timeElapsed = System.currentTimeMillis() - startTime;

            // background
            dc.setOrigin(DConsole.ORIGIN_TOP_LEFT);
            dc.setPaint(Color.BLACK);
            dc.fillRect(0, 0, 1200, 800);

            // player 1
            dc.setPaint(Color.RED);
            dc.fillEllipse(60, 350, 60, 60);
            dc.fillEllipse(60, 450, 60, 200);

            // player 2
            dc.setPaint(Color.BLUE);
            dc.fillEllipse(1140, 350, 60, 60);
            dc.fillEllipse(1140, 450, 60, 200);

            // player 3
            dc.setPaint(Color.GREEN);
            dc.fillEllipse(600, 600, 60, 60);
            dc.fillEllipse(600, 700, 60, 200);

            // player 4
            dc.setPaint(Color.YELLOW);
            dc.fillEllipse(600, 50, 60, 60);
            dc.fillEllipse(600, 150, 60, 200);

            // Potato
            dc.setPaint(new Color(183, 146, 104));
            dc.fillEllipse(potatoX, potatoY, 60, 110);

            if (timeElapsed < 15000) {
                dc.drawString(timeElapsed / 1000, 50, 50);
            }

            for (AbstractGamepad controller : playerControllers) {
                controller.poll();
            }

            if (timeElapsed < 15000) {
                if (potatoX == 80 && potatoY == 400 && playerControllers[0].getBButton() == true) {// red
                    potatoX = 650;
                    potatoY = 75;
                } else if (potatoX == 650 && potatoY == 75 && playerControllers[3].getYButton() == true) {// yellow
                    potatoX = 1120;
                    potatoY = 400;
                } else if (potatoX == 1120 && potatoY == 400 && playerControllers[1].getXButton() == true) {// blue
                    potatoX = 650;
                    potatoY = 650;
                }
                if (potatoX == 650 && potatoY == 650 && playerControllers[2].getAButton() == true) {// green
                    potatoX = 80;
                    potatoY = 400;
                }
            }

            dc.setOrigin(DConsole.ORIGIN_CENTER);
            if (potatoX == 80 && potatoY == 400 && timeElapsed > 15000) {// red
                dc.drawString("Red player loses!", 600, 400);
                playing = false;
                scores[1] += 1;
                scores[2] += 1;
                scores[3] += 1;
            } else if (potatoX == 650 && potatoY == 75 && timeElapsed > 15000) {// yellow
                dc.drawString("Yellow player loses", 600, 400);
                playing = false;
                scores[0] += 1;
                scores[1] += 1;
                scores[2] += 1;
            } else if (potatoX == 1120 && potatoY == 400 && timeElapsed > 15000) {// blue
                dc.drawString("Blue player loses", 600, 400);
                playing = false;
                scores[0] += 1;
                scores[2] += 1;
                scores[3] += 1;
            }
            if (potatoX == 650 && potatoY == 650 && timeElapsed > 15000) {// green
                dc.drawString("Green player loses", 600, 400);
                playing = false;
                scores[0] += 1;
                scores[1] += 1;
                scores[3] += 1;
            }

            dc.redraw();
        }
        App.switchGame(new Leaderboard(dc, playerControllers, scores));
    }

}

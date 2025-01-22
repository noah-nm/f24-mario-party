package marioparty.games;

import java.awt.Color;
import java.awt.Font;

import DLibX.DConsole;
import java.util.Random;

import marioparty.App;
import marioparty.menus.Leaderboard;
import marioparty.utils.AbstractGamepad;

public class EscapeGame extends Game {
    public EscapeGame(DConsole dc, AbstractGamepad[] playerControllers, int[] scores) {
        super(dc, playerControllers, scores);
    }

    public void play() {

        int p1x = 200;
        int p1y = 200;
        int p2x = 1000;
        int p2y = 200;
        int p3x = 200;
        int p3y = 700;
        int p4x = 1000;
        int p4y = 700;
        int place = 0;
        boolean p1Finished = false;
        boolean p2Finished = false;
        boolean p3Finished = false;
        boolean p4Finished = false;

        // initialize door spawn
        Random random = new Random();
        int doorX = random.nextInt(1200); // random x coordinate
        int doorY = random.nextInt(600) + 200; // random y coordinate

        // custom brown colour
        Color customColor = new Color(112, 69, 46);

        int[] winners = new int[4];

        while (place < 4) {
            for (AbstractGamepad playerController : playerControllers) {
                playerController.poll();
            }

            dc.clear();
            // text
            dc.setPaint(Color.BLACK);
            dc.setFont(new Font("Serif", Font.BOLD, 100)); // Set font
            dc.drawString("ESCAPE!", 600, 50);

            // players 1
            if (!p1Finished) {
                dc.setPaint(Color.RED);
                dc.fillRect(p1x, p1y, 40, 40);
                if (this.playerControllers[0].getLeftStickY() < -0.1) { // up
                    p1y = p1y - 3;
                }
                if (this.playerControllers[0].getLeftStickX() < -0.1) { // left
                    p1x = p1x - 3;
                }
                if (this.playerControllers[0].getLeftStickY() > 0.1) { // down
                    p1y = p1y + 3;
                }
                if (this.playerControllers[0].getLeftStickX() > 0.1) { // right
                    p1x = p1x + 3;
                }
                if (p1x + 40 > doorX && p1x < doorX + 40 && p1y + 40 > doorY && p1y < doorY + 80) {
                    winners[place] = 1;
                    place = place + 1;
                    p1Finished = true;
                }
            }

            // player 2
            if (!p2Finished) {
                dc.setPaint(Color.BLUE);
                dc.fillRect(p2x, p2y, 40, 40);
                if (this.playerControllers[1].getLeftStickY() < -0.1) {
                    p2y = p2y - 3;
                }
                if (this.playerControllers[1].getLeftStickX() < -0.1) {
                    p2x = p2x - 3;
                }
                if (this.playerControllers[1].getLeftStickY() > 0.1) {
                    p2y = p2y + 3;
                }
                if (this.playerControllers[1].getLeftStickX() > 0.1) {
                    p2x = p2x + 3;
                }
                if (p2x + 40 > doorX && p2x < doorX + 40 && p2y + 40 > doorY && p2y < doorY + 80) {
                    winners[place] = 2;
                    place = place + 1;
                    p2Finished = true;
                }
            }

            // player 3
            if (!p3Finished) {
                dc.setPaint(Color.GREEN);
                dc.fillRect(p3x, p3y, 40, 40);
                if (this.playerControllers[2].getLeftStickY() < -0.1) {
                    p3y = p3y - 3;
                }
                if (this.playerControllers[2].getLeftStickX() < -0.1) {
                    p3x = p3x - 3;
                }
                if (this.playerControllers[2].getLeftStickY() > 0.1) {
                    p3y = p3y + 3;
                }
                if (this.playerControllers[2].getLeftStickX() > 0.1) {
                    p3x = p3x + 3;
                }
                if (p3x + 40 > doorX && p3x < doorX + 40 && p3y + 40 > doorY && p3y < doorY + 80) {
                    winners[place] = 3;
                    place = place + 1;
                    p3Finished = true;
                }
            }

            // player 4
            if (!p4Finished) {
                dc.setPaint(Color.YELLOW);
                dc.fillRect(p4x, p4y, 40, 40);
                if (this.playerControllers[3].getLeftStickY() < -0.1) {
                    p4y = p4y - 3;
                }
                if (this.playerControllers[3].getLeftStickX() < -0.1) {
                    p4x = p4x - 3;
                }
                if (this.playerControllers[3].getLeftStickY() > 0.1) {
                    p4y = p4y + 3;
                }
                if (this.playerControllers[3].getLeftStickX() > 0.1) {
                    p4x = p4x + 3;
                }
                if (p4x + 40 > doorX && p4x < doorX + 40 && p4y + 40 > doorY && p4y < doorY + 80) {
                    winners[place] = 4;
                    place = place + 1;
                    p4Finished = true;
                }
            }

            // door
            dc.setPaint(customColor);
            dc.fillRect(doorX, doorY, 40, 80);
            dc.setPaint(Color.GRAY);
            dc.fillEllipse(doorX + 6, doorY, 10, 10);

            dc.redraw();
            DConsole.pause(10);
        }

        // display winners
        dc.clear();
        dc.setPaint(Color.BLACK);
        dc.setFont(new Font("Serif", Font.BOLD, 80));
        dc.drawString("Game Over! Winners:", 600, 200);

        if (winners.length > 0) {
            dc.drawString("1st Place: Player " + winners[0], 600, 300);
        }
        if (winners.length > 1) {
            dc.drawString("2nd Place: Player " + winners[1], 600, 400);
        }
        if (winners.length > 2) {
            dc.drawString("3rd Place: Player " + winners[2], 600, 500);
        }
        if (winners.length > 3) {
            dc.drawString("4th Place: Player " + winners[3], 600, 600);
        }

        // give scores
        scores[winners[0] - 1] += 4;
        scores[winners[1] - 1] += 3;
        scores[winners[2] - 1] += 2;
        scores[winners[3] - 1] += 1;

        dc.redraw();
        DConsole.pause(5000);
        App.switchGame(new Leaderboard(dc, playerControllers, scores));
    }
}

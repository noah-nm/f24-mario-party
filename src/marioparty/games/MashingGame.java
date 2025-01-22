package marioparty.games;

import java.awt.Color;
import java.awt.Font;

import DLibX.DConsole;
import marioparty.App;
import marioparty.menus.Leaderboard;
import marioparty.utils.AbstractGamepad;

public class MashingGame extends Game {

    public MashingGame(DConsole dc, AbstractGamepad[] playerControllers, int[] scores) {
        super(dc, playerControllers, scores);
    }

    public void play() {

        int x1 = 50;
        int x2 = 50;
        int x3 = 50;
        int x4 = 50;
        double w1 = 150;
        double w2 = 150;
        double w3 = 150;
        double w4 = 150;
        boolean key1WasPressed = false;
        boolean key2WasPressed = false;
        boolean key3WasPressed = false;
        boolean key4WasPressed = false;
        boolean gameOver = false;
        int winner = 0;

        while (gameOver == false) {

            for (AbstractGamepad playerController : playerControllers) {
                playerController.poll();
            }

            dc.clear();

            // text
            dc.setPaint(Color.BLACK);
            dc.setFont(new Font("Serif", Font.BOLD, 100)); // Set font
            dc.drawString("MASH!", 500, 50);
            dc.setFont(new Font("Serif", Font.BOLD, 25)); // Set font
            dc.drawString("P1", 100, 200);
            dc.drawString("P2", 100, 350);
            dc.drawString("P3", 100, 500);
            dc.drawString("P4", 100, 650);

            // Drawings
            // initalizing players
            dc.setPaint(Color.RED);
            dc.fillRect(w1, 200, x1, 50);
            dc.setPaint(Color.BLUE);
            dc.fillRect(w2, 350, x2, 50);
            dc.setPaint(Color.GREEN);
            dc.fillRect(w3, 500, x3, 50);
            dc.setPaint(Color.YELLOW);
            dc.fillRect(w4, 650, x4, 50);

            // finish line
            dc.setPaint(Color.BLACK);
            dc.fillRect(1000, 0, 25, 2000);

            // gameplay
            // Player 1
            if (this.playerControllers[0].getAButton()) {
                if (!key1WasPressed) {
                    x1 = x1 + 25;
                    w1 = w1 + 12.5;
                }
                key1WasPressed = true;
            } else {
                key1WasPressed = false;
            }

            // Player 2
            if (this.playerControllers[1].getAButton()) {
                if (!key2WasPressed) {
                    x2 = x2 + 25;
                    w2 = w2 + 12.5;
                }
                key2WasPressed = true;
            } else {
                key2WasPressed = false;
            }

            // Player 3
            if (this.playerControllers[2].getAButton()) {
                if (!key3WasPressed) {
                    x3 = x3 + 25;
                    w3 = w3 + 12.5;
                }
                key3WasPressed = true;
            } else {
                key3WasPressed = false;
            }

            // Player 4
            if (this.playerControllers[3].getAButton()) {
                if (!key4WasPressed) {
                    x4 = x4 + 25;
                    w4 = w4 + 12.5;
                }
                key4WasPressed = true;
            } else {
                key4WasPressed = false;
            }

            if (x1 >= 880) {
                showWinner(dc, "P1", Color.RED);
                gameOver = true;
                winner = 1;
            } else if (x2 >= 880) {
                showWinner(dc, "P2", Color.BLUE);
                gameOver = true;
                winner = 2;
            } else if (x3 >= 880) {
                showWinner(dc, "P3", Color.GREEN);
                gameOver = true;
                winner = 3;
            } else if (x4 >= 880) {
                showWinner(dc, "P4", Color.YELLOW);
                gameOver = true;
                winner = 4;
            }
            dc.redraw();
            dc.pause(10);
        }

         // give score
        scores[winner -1] += 4;
        dc.redraw();
        dc.pause(5000);
        App.switchGame(new Leaderboard(dc, playerControllers, scores));
    }

    private void showWinner(DConsole dc, String player, Color color) {
        dc.setPaint(color);
        dc.setFont(new Font("Serif", Font.BOLD, 25));
        dc.drawString(player + " WINS!", 1100, 400);
        dc.redraw();
    }

}

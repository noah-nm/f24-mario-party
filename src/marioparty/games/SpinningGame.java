package marioparty.games;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Collections;

import DLibX.DConsole;
import marioparty.App;
import marioparty.menus.Leaderboard;
import marioparty.utils.AbstractGamepad;

public class SpinningGame extends Game {

    public SpinningGame(DConsole dc, AbstractGamepad[] playerControllers, int[] scores) {
        super(dc, playerControllers, scores);
    }

    public void play() {
        Font titleFont = new Font("arial", Font.BOLD, 80);
        Font textFont = new Font("arial", Font.PLAIN, 30);

        int[] spins = new int[4];
        double startTime = System.currentTimeMillis();

        dc.setOrigin(DConsole.ORIGIN_TOP_LEFT);
        dc.clear();
        dc.setFont(titleFont);
        dc.drawString("Stir the pot!", 50, 100);
        dc.setFont(textFont);
        dc.drawString("Use your left stick.", 50, 190);
        dc.drawString("You have 5 seconds!", 50, 240);
        dc.drawImage("src\\marioparty\\games\\images\\Soup.png", 50, 300);
        dc.redraw();
        dc.pause(3000);

        dc.clear();
        dc.drawString("Go!", 200, 100);
        dc.redraw();

        // 5 seconds of spinning
        while (System.currentTimeMillis() - startTime < 5000) {
            for (int i = 0; i < playerControllers.length; i++) {
                playerControllers[i].poll();
                double x = playerControllers[i].getLeftStickX();
                double y = playerControllers[i].getLeftStickY();
                double magnitude = Math.sqrt(x * x + y * y);

                // Count spin only if magnitude is above a threshold
                if (magnitude > 0.8) {
                    spins[i]++;
                }

            }
            dc.pause(50);
        }

        // Determine winner
        int maxSpins = 0;
        int winner = -1;
        for (int i = 0; i < spins.length; i++) {
            if (spins[i] > maxSpins) {
                maxSpins = spins[i];
                winner = i;
            }
        }

        // Award points
        ArrayList<Integer> spinList = new ArrayList<Integer>();
        for (int i = 0; i < spins.length; i++) {
            spinList.add(spins[i]);
        }
        for (int i = 4; i > 0; i--) {
            int maxIndex = spinList.indexOf(Collections.max(spinList));
            scores[maxIndex] += i;
            spinList.set(maxIndex, -1);
        }

        // Display results
        dc.clear();
        dc.setFont(titleFont);
        dc.drawString("Time's Up!", 150, 100);
        dc.setFont(textFont);
        for (int i = 0; i < spins.length; i++) {
            dc.drawString("Player " + (i + 1) + " spins: " + spins[i], 50, 200 + i * 50);
        }
        if (winner != -1) {
            dc.drawString("Winner: Player " + (winner + 1) + "!", 50, 400);
        } else {
            dc.drawString("No Winner!", 50, 400);
        }
        dc.redraw();
        dc.pause(3000);
        App.switchGame(new Leaderboard(dc, playerControllers, scores));

    }
}

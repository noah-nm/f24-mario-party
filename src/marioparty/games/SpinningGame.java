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

        this.dc.setOrigin(DConsole.ORIGIN_TOP_LEFT);
        this.dc.clear();
        this.dc.setFont(titleFont);
        this.dc.drawString("Stir the pot!", 50, 100);
        this.dc.setFont(textFont);
        this.dc.drawString("Use your left stick.", 50, 190);
        this.dc.drawString("You have 5 seconds!", 50, 240);
        this.dc.drawImage("src\\marioparty\\games\\images\\Soup.png", 50, 300);
        this.dc.redraw();
        DConsole.pause(3000);

        this.dc.clear();
        this.dc.drawString("Go!", 200, 100);
        this.dc.redraw();

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
            DConsole.pause(50);
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
        this.dc.clear();
        this.dc.setFont(titleFont);
        this.dc.drawString("Time's Up!", 150, 100);
        this.dc.setFont(textFont);
        for (int i = 0; i < spins.length; i++) {
            this.dc.drawString("Player " + (i + 1) + " spins: " + spins[i], 50, 200 + i * 50);
        }
        if (winner != -1) {
            this.dc.drawString("Winner: Player " + (winner + 1) + "!", 50, 400);
        } else {
            this.dc.drawString("No Winner!", 50, 400);
        }
        this.dc.redraw();
        DConsole.pause(3000);
        App.switchGame(new Leaderboard(dc, playerControllers, scores));

    }
}

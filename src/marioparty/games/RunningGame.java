package marioparty.games;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import DLibX.DConsole;
import marioparty.App;
import marioparty.menus.Leaderboard;
import marioparty.utils.AbstractGamepad;

public class RunningGame extends Game {

    private boolean[] prevInput = new boolean[] { false, false, false, false };
    private boolean[] readyPlayers = new boolean[] { false, false, false, false };
    private int[] playerPos = new int[] { 100, 100, 100, 100 };
    private long[] tripTime = new long[4];
    private int[] tripImmunity = new int[4];
    private ArrayList<Integer> winnerOrder = new ArrayList<>();

    private int screen = 0;

    public RunningGame(DConsole dc, AbstractGamepad[] players, int[] scores) {
        super(dc, players, scores);
    }

    @Override
    public void play() {
        for (AbstractGamepad player : this.playerControllers) {
            player.poll();
        }

        if (screen == 0) { // ready screen

            // handle button presses
            for (int i = 0; i < playerControllers.length; i++) {
                if (this.playerControllers[i].getAButton()) {
                    readyPlayers[i] = true;
                }

                if (this.playerControllers[i].getBButton()) {
                    readyPlayers[i] = false;
                }
            }

            // if all players are ready, start / switch to screen 1
            boolean allPlayersReady = true;
            for (int i = 0; i < readyPlayers.length; i++) {
                if (!readyPlayers[i]) {
                    allPlayersReady = false;
                }
            }

            if (allPlayersReady) {
                screen = 1;
            }

            // bg
            this.dc.setPaint(Color.CYAN);
            this.dc.fillRect(this.dc.getWidth() / 2, this.dc.getHeight() / 2, this.dc.getWidth(), this.dc.getHeight());

            Font arialTitle = new Font("arial", 1, 80);
            Font arialSmall = new Font("arial", 1, 30);
            Font arialMedium = new Font("arial", 1, 40);

            // text
            this.dc.setPaint(Color.BLACK);
            this.dc.setOrigin(DConsole.ORIGIN_CENTER);
            this.dc.setFont(arialTitle);
            this.dc.drawString("Runner", this.dc.getWidth() / 2, (this.dc.getHeight() / 2) - 200);

            // instructions
            this.dc.setFont(arialSmall);
            this.dc.drawString("Press A when ready", this.dc.getWidth() / 2, this.dc.getHeight() / 2 - 100);
            this.dc.setPaint(Color.BLACK);
            this.dc.drawString("Press BACK to unselect", this.dc.getWidth() / 2, this.dc.getHeight() / 2 + 350);

            // draw player blobs
            this.dc.setPaint(Color.RED);
            this.dc.fillEllipse(225, 500, 100, 100);
            this.dc.setPaint(Color.BLUE);
            this.dc.fillEllipse(475, 500, 100, 100);
            this.dc.setPaint(Color.GREEN);
            this.dc.fillEllipse(725, 500, 100, 100);
            this.dc.setPaint(Color.YELLOW);
            this.dc.fillEllipse(975, 500, 100, 100);

            // text on blob
            this.dc.setPaint(Color.BLACK);
            this.dc.setFont(arialSmall);
            this.dc.drawString("Red", 225, 495);
            this.dc.drawString("Blue", 475, 495);
            this.dc.drawString("Green", 725, 495);
            this.dc.drawString("Yellow", 975, 495);

            // Player number text
            this.dc.setFont(arialMedium);
            this.dc.setPaint(Color.RED);
            this.dc.drawString("Player 1", 225, 600);
            this.dc.setPaint(Color.BLUE);
            this.dc.drawString("Player 2", 475, 600);
            this.dc.setPaint(Color.GREEN);
            this.dc.drawString("Player 3", 725, 600);
            this.dc.setPaint(Color.YELLOW);
            this.dc.drawString("Player 4", 975, 600);

            // draw buttons
            this.dc.setPaint(new Color(245, 14, 10, 220)); // light red B button
            this.dc.fillEllipse(225, 700, 50, 50);
            this.dc.setPaint(new Color(24, 72, 204, 220)); // light blue X button
            this.dc.fillEllipse(475, 700, 50, 50);
            this.dc.setPaint(new Color(48, 201, 61, 220)); // light green A button
            this.dc.fillEllipse(725, 700, 50, 50);
            this.dc.setPaint(new Color(227, 185, 16, 220)); // light yellow Y button
            this.dc.fillEllipse(975, 700, 50, 50);

            Color notTaken = new Color(180, 21, 11);
            Color taken = new Color(39, 219, 39);

            // change color if player 1 taken
            if (!readyPlayers[0]) {
                this.dc.setPaint(notTaken);
            } else {
                this.dc.setPaint(taken);
            }

            // player 1 indicator
            this.dc.fillEllipse(225, 420, 25, 25);

            // change color if player 2 taken
            if (!readyPlayers[1]) {
                this.dc.setPaint(notTaken);
            } else {
                this.dc.setPaint(taken);
            }

            // player 2 indicator
            this.dc.fillEllipse(475, 420, 25, 25);

            // change color is player 3 is taken
            if (!readyPlayers[2]) {
                this.dc.setPaint(notTaken);
            } else {
                this.dc.setPaint(taken);
            }

            // player 3 indicator
            this.dc.fillEllipse(725, 420, 25, 25);

            // change color is player 4 is taken
            if (!readyPlayers[3]) {
                this.dc.setPaint(notTaken);
            } else {
                this.dc.setPaint(taken);
            }

            // player 4 indicator
            this.dc.fillEllipse(974, 420, 25, 25);

            DConsole.pause(15);
        } else if (screen == 1) { // game screen
            this.dc.setPaint(Color.BLACK);

            // draw lines
            for (int i = 0; i < 5; i++) {
                this.dc.drawLine(100, (i * 150) + 100, this.dc.getWidth() - 100, (i * 150) + 100);
            }

            // draw players
            this.dc.setPaint(Color.RED);
            this.dc.fillEllipse(playerPos[0], 200, 50, 50);
            this.dc.setPaint(Color.BLUE);
            this.dc.fillEllipse(playerPos[1], 350, 50, 50);
            this.dc.setPaint(Color.GREEN);
            this.dc.fillEllipse(playerPos[2], 500, 50, 50);
            this.dc.setPaint(Color.YELLOW);
            this.dc.fillEllipse(playerPos[3], 650, 50, 50);

            boolean gameDone = winnerOrder.size() == 4;

            // calc winner
            for (int i = 0; i < playerControllers.length; i++) {
                tripImmunity[i] -= 1;
                if (playerPos[i] > this.dc.getWidth() - 100) {
                    // TODO: Add points and switch game
                    if (!winnerOrder.contains(i)) {
                        winnerOrder.add(i);
                    }
                }
            }

            // handle when the game is done
            if (gameDone) {
                // hand out scores 4-1
                for (int i = winnerOrder.size() - 1; i >= 0; i--) {
                    scores[winnerOrder.get(i)] += i + 1;
                }
                // switch
                App.switchGame(new Leaderboard(dc, playerControllers, scores));
            }

            // game loop
            for (int i = 0; i < playerControllers.length; i++) {

                // while player is not tripped
                if (tripTime[i] < System.currentTimeMillis()) {

                    // player button indicators
                    this.dc.setPaint(Color.BLACK);
                    this.dc.setFont(new Font("arial", 0, 16));
                    if (!prevInput[i]) {
                        this.dc.drawString("B", playerPos[i], 150 + (i * 150));
                    } else {
                        this.dc.drawString("X", playerPos[i], 150 + (i * 150));
                    }

                    // tripping
                    if (playerControllers[i].getXButton() && !prevInput[i] && tripImmunity[i] <= 0) {
                        tripTime[i] = System.currentTimeMillis() + 1000;
                        prevInput[i] = true;
                    }

                    if (playerControllers[i].getBButton() && prevInput[i] && tripImmunity[i] <= 0) {
                        tripTime[i] = System.currentTimeMillis() + 1000;
                        prevInput[i] = false;
                    }

                    // game movement
                    if (playerControllers[i].getXButton() && prevInput[i] && playerPos[i] < this.dc.getWidth() - 50) {
                        playerPos[i] += 10;
                        tripImmunity[i] = 5;
                        prevInput[i] = false;
                    }

                    if (playerControllers[i].getBButton() && !prevInput[i] && playerPos[i] < this.dc.getWidth() - 50) {
                        playerPos[i] += 10;
                        tripImmunity[i] = 5;
                        prevInput[i] = true;
                    }
                } else {
                    // tripped display
                    this.dc.setPaint(Color.BLACK);
                    this.dc.setFont(new Font("arial", 0, 16));
                    this.dc.drawString("Tripped!", playerPos[i], 150 + (i * 150));
                }

            }

            DConsole.pause(15);
        }

    }

}
package marioparty.games;

import marioparty.utils.Gamepad;
import marioparty.App;
import marioparty.games.DodgeFromBullets.Player;
import marioparty.menus.Leaderboard;
import marioparty.utils.AbstractGamepad;
import DLibX.*;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import java.awt.Font;

public class ColorReactionGame extends Game {

    public ColorReactionGame(DConsole dc, AbstractGamepad[] playerControllers, int[] scores) {
        super(dc, playerControllers, scores);
    }

    public void play() {

        dc.setOrigin(DConsole.ORIGIN_CENTER);

        // Game setup
        Random rand = new Random();
        int screen = 0; // 0: Start Screen, 1: Game
        int[] score = { 0, 0, 0, 0 }; // Scores for 4 players
        double timer = System.currentTimeMillis();
        double gameStartTime = timer; // Store the time when the game starts
        // Store the time when the game starts
        // Raw keycodes for 4 players

        // -------------------------player thing help!-------------------------
        /*
         * 
         * players[0] = (this.playerControllers[0].getBButton()) // Red
         * players[1] = (this.playerControllers[0].getXButton() ) // Blue
         * players[2] = (this.playerControllers[0].getAButton()) // Green
         * players[3] = (this.playerControllers[0].getYButton()) // Yellow
         * 
         * 
         */

        while (true) {
            if (screen == 0) {
                // Start Screen
                dc.setPaint(new Color(75, 181, 43)); // Background color
                dc.fillRect(300, 300, 600, 550);
                dc.setPaint(Color.WHITE); // Text color
                dc.setFont(new Font("Serif", Font.BOLD, 48));
                dc.drawString("Reaction Game", 300, 100);
                dc.setFont(new Font("Serif", Font.BOLD, 22));
                dc.setPaint(Color.WHITE);
                dc.fillRect(300, 185, 200, 30); // Background for instruction text
                dc.setPaint(new Color(20, 85, 0));
                dc.drawString("Press start to Start", 300, 175);
                dc.drawString("use right bumper to play!", 300, 225);
                dc.redraw();

                System.out.println("Intro screen");

                // Wait for Enter key to start
                playerControllers[0].poll();
                while (!playerControllers[0].getStartButton()) {
                    dc.pause(10);
                    playerControllers[0].poll();
                }

                System.out.println("Game Started");
                screen = 1;
                gameStartTime = System.currentTimeMillis(); // Reset the start time
            } else {
                // Main Game Loop
                // dc.pause(1000); // Game delay before each round

                double currentTime = System.currentTimeMillis();
                double elapsedTime = (currentTime - gameStartTime) / 1000;

                if (elapsedTime <= 30) {
                    // Countdown Timer at the top-right corner
                    int remainingTime = 30 - (int) elapsedTime;

                    // Set background for game round
                    dc.setPaint(new Color(22, 69, 2));
                    dc.fillRect(300, 300, 600, 550);
                    dc.setPaint(Color.WHITE);
                    dc.setFont(new Font("Serif", Font.BOLD, 35));
                    dc.drawString("Reaction Game", 300, 60);

                    // Draw scores
                    dc.setPaint(Color.WHITE);
                    dc.setFont(new Font("Serif", Font.BOLD, 22));
                    dc.drawString("Player 1: " + score[0], 75, 52);
                    dc.drawString("Player 2: " + score[1], 75, 74);
                    dc.drawString("Player 3: " + score[2], 75, 96);
                    dc.drawString("Player 4: " + score[3], 75, 118);

                    // Draw countdown timer in the top-right corner
                    dc.setPaint(Color.WHITE);
                    dc.setFont(new Font("Serif", Font.BOLD, 22));
                    dc.drawString("Time: " + remainingTime, 500, 40);
                    dc.redraw();
                    dc.pause(500);

                    // Randomly choose a color for the prompt
                    Color[] colors = { Color.RED, Color.BLUE, Color.GREEN, Color.ORANGE };
                    Color currentColor = colors[rand.nextInt(colors.length)];

                    // Draw the color circle
                    dc.setPaint(currentColor);
                    dc.fillEllipse(300, 250, 100, 100);
                    dc.redraw();

                    // Wait for player to react by pressing the correct key
                    boolean keyPressed = false;
                    double startTime = System.currentTimeMillis();

                    while (!keyPressed && System.currentTimeMillis() - startTime < 1500) {

                        for (int i = 0; i < 4; i++) {
                            playerControllers[i].poll();
                            if (playerControllers[i].getRightPaddleButton()) {
                                System.out.println("blah");// Check if the player's key was pressed
                                if (currentColor == colors[i]) {
                                    score[i]++; // Correct player pressed the right key
                                    System.out.println("Player " + (i + 1) + " wins this round!");
                                } else {
                                    score[i]--; // Wrong player pressed the wrong key, loses a point
                                    System.out.println(
                                            "Player " + (i + 1) + " loses a point for pressing the wrong key!");
                                }
                                keyPressed = true;
                            }

                        }

                        // redraw timer
                        currentTime = System.currentTimeMillis();
                        elapsedTime = (currentTime - gameStartTime) / 1000;
                        remainingTime = 30 - (int) elapsedTime;

                        dc.setPaint(new Color(22, 69, 2));
                        dc.fillRect(500, 45, 100, 30);

                        dc.setPaint(Color.WHITE);
                        dc.setFont(new Font("Serif", Font.BOLD, 22));
                        dc.drawString("Time: " + remainingTime, 500, 40);

                        dc.redraw();

                        dc.pause(10);

                    }

                    // Reset and prepare for next round
                    dc.setPaint(new Color(22, 69, 2)); // Background reset
                    dc.fillRect(300, 300, 600, 550);
                    dc.redraw();
                }
                // Elapsed time in seconds

                if (elapsedTime >= 30) {
                    // End the game after 30 seconds
                    dc.setPaint(Color.WHITE);
                    dc.setFont(new Font("Serif", Font.BOLD, 48));
                    dc.drawString("Game Over!", 300, 250);
                    dc.setFont(new Font("Serif", Font.BOLD, 22));
                    dc.drawString("Final Scores: ", 300, 300);
                    dc.drawString("Player 1: " + score[0], 300, 330);
                    dc.drawString("Player 2: " + score[1], 300, 350);
                    dc.drawString("Player 3: " + score[2], 300, 370);
                    dc.drawString("Player 4: " + score[3], 300, 390);
                    dc.redraw();

                    scores[0] += score[0]/2;
                    scores[1] += score[1]/2;
                    scores[2] += score[2]/2;
                    scores[3] += score[3]/2;
                    dc.pause(3000);
                    
                    App.switchGame(new Leaderboard(dc, playerControllers, scores));
                    
                    break;
                }
                
            }
        }
    }
}

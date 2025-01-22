package marioparty.games;

import DLibX.*;
import marioparty.App;
import marioparty.menus.Leaderboard;
import marioparty.utils.AbstractGamepad;
import java.awt.Color;
import java.util.Random;
import java.awt.Font;

public class SIMON extends Game {

  public SIMON(DConsole dc, AbstractGamepad[] playerControllers, int[] scores) {
    super(dc, playerControllers, scores);
  }

  @Override
  public void play() {
    showTitleScreen();
    runSingleRound();
    showEndScreen();
  }

  private void showTitleScreen() {
    dc.clear();
    dc.setPaint(Color.BLACK);
    dc.fillRect(0, 0, 10000, 10000);
    dc.setPaint(Color.WHITE);
    Font titleFont = new Font("Arial", Font.BOLD, 60);
    dc.setFont(titleFont);
    dc.drawString("SIMON", 600, 400);
    dc.redraw();
    DConsole.pause(2000); // Wait for the title screen
  }

  private void runSingleRound() {
    Random random = new Random();
    Color[] playerColors = { Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW };
    String[] playerNames = { "Player 1", "Player 2", "Player 3", "Player 4" };

    // Generate a target colour for players to match
    int targetPlayer = random.nextInt(4);
    Color targetColor = playerColors[targetPlayer];
    boolean roundActive = true;

    while (roundActive) {
      dc.clear();
      dc.setPaint(Color.BLACK);
      dc.fillRect(0, 0, 1200, 800);

      // Draw player quadrants
      dc.setPaint(playerColors[0]);
      dc.fillRect(300, 100, 600, 400); // Player 1
      dc.setPaint(Color.BLACK);
      dc.drawString(playerNames[0], 250, 120);

      dc.setPaint(playerColors[1]);
      dc.fillRect(900, 100, 600, 400); // Player 2
      dc.setPaint(Color.BLACK);
      dc.drawString(playerNames[1], 850, 120);

      dc.setPaint(playerColors[2]);
      dc.fillRect(300, 500, 600, 400); // Player 3
      dc.setPaint(Color.BLACK);
      dc.drawString(playerNames[2], 250, 520);

      dc.setPaint(playerColors[3]);
      dc.fillRect(900, 500, 600, 400); // Player 4
      dc.setPaint(Color.BLACK);
      dc.drawString(playerNames[3], 850, 520);

      // Draw target color
      dc.setPaint(targetColor);
      dc.fillRect(500, 300, 100, 100);
      dc.setPaint(Color.BLACK);
      dc.drawString("PRESS THIS QUICK!", 550, 700);

      dc.redraw();

      // Poll player inputs
      for (int i = 0; i < playerControllers.length; i++) {
        playerControllers[i].poll();
        if (playerControllers[i].getAButton()) {
          if (i == targetPlayer) {
            displayWinnerScreen(i, playerColors, playerNames); // Display winner screen
          } else {
            displayWinnerScreen(targetPlayer, playerColors, playerNames); // Default to target player
          }
          roundActive = false; // End the round
          break;
        }
      }

      DConsole.pause(20); // Short pause to control game speed
    }
  }

  private void displayWinnerScreen(int winner, Color[] playerColors, String[] playerNames) {
    dc.clear();
    dc.setPaint(Color.BLACK);
    dc.fillRect(0, 0, 10000, 10000);
    dc.setPaint(playerColors[winner]);
    Font winnerFont = new Font("Arial", Font.BOLD, 50);
    dc.setFont(winnerFont);
    dc.drawString(playerNames[winner] + " Wins!", 600, 400);
    dc.redraw();
    DConsole.pause(5000); // Display the winner screen for 5 seconds
  }

  private void showEndScreen() {
    dc.clear();
    dc.setPaint(Color.BLACK);
    dc.fillRect(0, 0, 10000, 10000);
    dc.setPaint(Color.WHITE);
    Font endFont = new Font("Arial", Font.BOLD, 50);
    dc.setFont(endFont);
    dc.drawString("Game Over! Thanks for playing!", 600, 400);
    dc.redraw();
    DConsole.pause(3000); // Show the end screen for 3 seconds
    App.switchGame(new Leaderboard(dc, playerControllers, scores));
  }

}

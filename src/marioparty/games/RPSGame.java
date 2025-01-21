package marioparty.games;

import java.awt.Font;

import DLibX.DConsole;
import marioparty.utils.AbstractGamepad;

public class RPSGame extends Game {

    public RPSGame(DConsole dc, AbstractGamepad[] playerControllers, int[] scores) {
        super(dc, playerControllers, scores);
    }

    public void play() {
        dc.setOrigin(DConsole.ORIGIN_TOP_LEFT);
        Font arialTitle = new Font("arial", 1, 80);

        int winner1 = 0;
        int winner2 = 0;
        int finalWinner = 0;

        dc.clear();
        dc.setFont(arialTitle);

        // Round 1: P1 vs P2
        while (winner1 == 0) {
            winner1 = playRound(0, 1, "P1 vs P2");
        }

        // Round 2: P3 vs P4
        while (winner2 == 0) {
            winner2 = playRound(2, 3, "P3 vs P4");
        }

        // Final Round: Winner1 vs Winner2
        while (finalWinner == 0) {
            finalWinner = playRound(winner1 - 1, winner2 - 1, "Winner vs Winner");
        }

        // Display final winner
        dc.clear();
        dc.drawString("The Final Winner is Player " + finalWinner + "!", 50, 200);
        dc.redraw();

        scores[winner1 - 1] += 2;
        scores[winner2 - 1] += 2;
        scores[finalWinner - 1] += 2;
        
        dc.pause(3000);
    }

    private int playRound(int playerAIndex, int playerBIndex, String roundTitle) {
        int playerAChoice = 0;
        int playerBChoice = 0;
        String[] choices = { "Rock", "Paper", "Scissors" };
        Font arialMedium = new Font("arial", 1, 40);

        dc.clear();
        dc.drawString(roundTitle, 50, 100);
        dc.drawString("Rock, Paper, Scissors, Shoot!", 50, 200);
        dc.setFont(arialMedium);
        dc.drawString("Rock = x, Paper = y, Scissors = a", 50, 300);
        dc.redraw();

        while (playerAChoice == 0 || playerBChoice == 0) {
            for (AbstractGamepad playerController : playerControllers) {
                playerController.poll();
            }

            // if (playerAChoice == 0) {
            if (playerControllers[playerAIndex].getXButton()) {
                playerAChoice = 1; // Rock
            } else if (playerControllers[playerAIndex].getYButton()) {
                playerAChoice = 2; // Paper
            } else if (playerControllers[playerAIndex].getAButton()) {
                playerAChoice = 3; // Scissors
            }
            // }

            // if (playerBChoice == 0) {
            if (playerControllers[playerBIndex].getXButton()) {
                playerBChoice = 1; // Rock
            } else if (playerControllers[playerBIndex].getYButton()) {
                playerBChoice = 2; // Paper
            } else if (playerControllers[playerBIndex].getAButton()) {
                playerBChoice = 3; // Scissors
            }
            // }
        }

        int winner = -1;

        if (playerAChoice == playerBChoice) {
            winner = 0;
        } else if ((playerAChoice == 1 && playerBChoice == 3) || (playerAChoice == 2 && playerBChoice == 1)
                || (playerAChoice == 3 && playerBChoice == 2)) {
            // Player A wins
            winner = 1;
        } else {
            // Player B wins
            winner = 2;
        }

        dc.clear();
        dc.drawString("Player " + (playerAIndex + 1) + " chose " + choices[playerAChoice - 1], 50, 200);
        dc.drawString("Player " + (playerBIndex + 1) + " chose " + choices[playerBChoice - 1], 50, 300);

        if (winner == 0) {
            dc.drawString("It's a tie!", 50, 400);
        } else {
            int winningPlayer = 0;
            if (winner == 1) {
                winningPlayer = playerAIndex + 1; // Player A wins
            } else {
                winningPlayer = playerBIndex + 1; // Player B wins
            }
            dc.drawString("Winner is Player " + winningPlayer, 50, 400);
        }
        dc.redraw();
        // Wait 3 seconds before clearing
        dc.pause(3000);

        if (winner == 1) {
            return playerAIndex + 1;
        } else if (winner == 2) {
            return playerBIndex + 1;
        } else {
            return 0; // Tie
        }
    }
}
package marioparty.menus;

import java.awt.Color;
import java.awt.Font;
import java.util.Arrays;
import java.util.HashMap;

import DLibX.DConsole;
import marioparty.App;
import marioparty.games.Game;
import marioparty.games.GameSelect;
import marioparty.utils.AbstractGamepad;

public class Leaderboard extends Game {

    public Leaderboard(DConsole dc, AbstractGamepad[] gamepads, int[] scores) {
        super(dc, gamepads, scores);
    }

    public void play() {
        int[] workingScores = Arrays.copyOf(scores, 4);
        Color[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW};
        Font arialMedium = new Font("arial", Font.BOLD, 40);

        HashMap<Integer, Integer> indexToSortedValue = new HashMap<>();

        // Store original indexes and values in a temporary array
        Integer[] originalIndexes = new Integer[workingScores.length];
        for (int i = 0; i < workingScores.length; i++) {
            originalIndexes[i] = i;
        }

        // Sort the indexes array based on the values in the scores array
        Arrays.sort(originalIndexes, (a, b) -> Integer.compare(workingScores[b], workingScores[a]));

        // Create the mapping of sorted indexes to values
        for (int i = 0; i < originalIndexes.length; i++) {
            indexToSortedValue.put(i, workingScores[originalIndexes[i]]);
        }

        dc.clear();

        // Draw the leaderboard GUI
        dc.setFont(arialMedium);

        for (int rank = 0; rank < originalIndexes.length; rank++) {
            int playerIndex = originalIndexes[rank];
            int yPosition = 100 + rank * 120;

            // Set the background color for each rank
            dc.setPaint(colors[playerIndex]);
            dc.fillRect(100, yPosition, 1000, 80);

            // Draw the player name and score
            dc.setPaint(Color.BLACK);
            dc.drawString("Player " + (playerIndex + 1), 150, yPosition + 10);
            dc.drawString(String.valueOf(workingScores[playerIndex]), 950, yPosition + 10);
        }

        dc.redraw();
        dc.pause(5000);
        App.switchGame(new GameSelect(dc, playerControllers, workingScores));
    }
}

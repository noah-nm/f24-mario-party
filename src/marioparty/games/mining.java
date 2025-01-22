package marioparty.games;

import DLibX.DConsole;
import marioparty.App;
import marioparty.menus.Leaderboard;
import marioparty.utils.AbstractGamepad;

import java.util.Random;
import java.awt.Color;

public class mining extends Game {
    private Random random;

    public mining(DConsole dc, AbstractGamepad[] players, int[] scores) {
        super(dc, players, scores);
        this.random = new Random();
    }

    @Override
    public void play() {

        int x = 100;
        int y = 100;
        int size = 100;
        double[] amountMined = new double[4];
        Color[] colors = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW};
        Color colorRound;

        for (int rounds = 0; rounds < 4; rounds++) {
            colorRound = colors[random.nextInt(colors.length)];
            dc.clear();
            dc.setPaint(colorRound);
            dc.fillEllipse(x, y, size, size);
            dc.redraw();

            long roundStartTime = System.nanoTime();
            long roundTime = 10 * 1000000000;

            while (System.nanoTime() - roundStartTime < roundTime) {
                dc.clear();
                dc.setPaint(colorRound);
                dc.fillEllipse(x, y, size, size);
                dc.redraw();

                if (dc.isMouseButton(1)) {
                    amountMined[rounds] += 1;
                }

                DConsole.pause(10);
            }

            dc.clear();
            dc.redraw();
        }

        int winningPlayer = 0;
        double maxMined = amountMined[0];
        for (int i = 1; i < amountMined.length; i++) {
            if (amountMined[i] > maxMined) {
                maxMined = amountMined[i];
                winningPlayer = i;
            }
        }

        scores[winningPlayer] += 4;
        App.switchGame(new Leaderboard(dc, playerControllers, scores));
    }
}
package marioparty.games;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import DLibX.DConsole; //1200 , 800
import marioparty.App;
import marioparty.menus.Leaderboard;
import marioparty.utils.AbstractGamepad;

public class Mashing extends Game {
    private AbstractGamepad[] players;
    private int[] count = new int[4];

    public Mashing(DConsole dc, AbstractGamepad[] playerControllers, int[] playerScores) { // inheritance stuff
        super(dc, playerControllers, playerScores);
        this.players = playerControllers;
    }

    // no holding button down for points
    private boolean buttonIsDown1 = false;
    private boolean buttonIsDown2 = false;
    private boolean buttonIsDown3 = false;
    private boolean buttonIsDown4 = false;

    ArrayList<Integer> orders = new ArrayList<>();

    @Override
    public void play() { // game itself

        dc.clear();

        for (AbstractGamepad player : this.playerControllers) { // poll controller
            player.poll();
        }

        if (this.players[0].getAButton()) { // player1 stuff
            if (!buttonIsDown1) {
                count[0]++;
            }
            buttonIsDown1 = true;

        } else {
            buttonIsDown1 = false;
        }

        if (this.players[1].getAButton()) { // player2 stuff
            if (!buttonIsDown2) {
                count[1]++;
            }
            buttonIsDown2 = true;
        } else {
            buttonIsDown2 = false;
        }

        if (this.players[2].getAButton()) { // player3 stuff
            if (!buttonIsDown3) {
                count[2]++;
            }
            buttonIsDown3 = true;
        } else {
            buttonIsDown3 = false;
        }

        if (this.players[3].getAButton()) { // player4 stuff
            if (!buttonIsDown4) {
                count[3]++;
            }
            buttonIsDown4 = true;
        } else {
            buttonIsDown4 = false;
        }

        dc.setPaint(new Color(135, 206, 235));
        dc.fillRect(500, 500, 50000, 500000);

        Font arialTitle = new Font("arial", 1, 100);// displaying count
        this.dc.setFont(arialTitle);
        this.dc.setPaint(Color.BLACK);

        this.dc.drawString("GET TO 50!", 600, 100);
        this.dc.drawString(count[0], 300, 300);
        this.dc.drawString(count[1], 900, 300);
        this.dc.drawString(count[2], 300, 600);
        this.dc.drawString(count[3], 900, 600);

        boolean gameDone = orders.size() == 4;

        for (int i = 0; i < count.length; i++) {
            if (count[i] >= 50 && !orders.contains(i)) { // scores
                orders.add(i);
            }
        }

        if (gameDone) {
            for (int i = orders.size() - 1; i >= 0; i--) {
                scores[(orders.get(i))] += i + 1;
            }
            App.switchGame(new Leaderboard(dc, playerControllers, scores));

            dc.redraw();
            dc.pause(20);

        }
    }
}

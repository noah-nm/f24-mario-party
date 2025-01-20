package marioparty.games;

import marioparty.App;
import marioparty.menus.Leaderboard;
import marioparty.utils.AbstractGamepad;
import DLibX.DConsole; //1200 , 800
import java.awt.Color;
import java.util.ArrayList;
import java.awt.Font;
import java.util.Random;

public class GrabCoin extends Game {
    private AbstractGamepad[] players;

    public GrabCoin(DConsole dc, AbstractGamepad[] playerControllers, int[] scores) { // inheritance stuff
        super(dc, playerControllers, scores);
        this.players = playerControllers;
    }

    private Random rg = new Random();

    private int coinX = rg.nextInt(1200); // coin's X + Y
    private int coinY = rg.nextInt(800);

    private int[] playerX = new int[] { 350, 900, 350, 900 }; // player's X + Y
    private int[] playerY = new int[] { 250, 250, 500, 500 };

    public void draw() { // draw stuff

        // coins
        this.dc.setPaint(new Color(255, 215, 0));
        dc.fillEllipse(coinX, coinY, 30, 50);

        // players
        this.dc.setPaint(Color.RED);
        dc.fillRect(playerX[0], playerY[0], 50, 50);
        this.dc.setPaint(Color.BLUE);
        dc.fillRect(playerX[1], playerY[1], 50, 50);
        this.dc.setPaint(Color.GREEN);
        dc.fillRect(playerX[2], playerY[2], 50, 50);
        this.dc.setPaint(Color.YELLOW);
        dc.fillRect(playerX[3], playerY[3], 50, 50);

    }

    // winner order
    ArrayList<Integer> orders = new ArrayList<>();

    @Override
    public void play() {

        dc.clear();

        this.draw();

        for (AbstractGamepad player : this.playerControllers) { // poll controller
            player.poll();
        }

        // Player 1 movement
        if (this.players[0].getAButton()) {
            playerY[0] = playerY[0] + 15;
        }

        if (this.players[0].getBButton()) {
            playerX[0] = playerX[0] + 15;
        }

        if (this.players[0].getXButton()) {
            playerX[0] = playerX[0] - 15;
        }

        if (this.players[0].getYButton()) {
            playerY[0] = playerY[0] - 15;
        }

        // Player 2 movement
        if (this.players[1].getAButton()) {
            playerY[1] = playerY[1] + 15;
        }

        if (this.players[1].getBButton()) {
            playerX[1] = playerX[1] + 15;
        }

        if (this.players[1].getXButton()) {
            playerX[1] = playerX[1] - 15;
        }

        if (this.players[1].getYButton()) {
            playerY[1] = playerY[1] - 15;
        }

        // Player 3 movement
        if (this.players[2].getAButton()) {
            playerY[2] = playerY[2] + 15;
        }

        if (this.players[2].getBButton()) {
            playerX[2] = playerX[2] + 15;
        }

        if (this.players[2].getXButton()) {
            playerX[2] = playerX[2] - 15;
        }

        if (this.players[2].getYButton()) {
            playerY[2] = playerY[2] - 15;
        }

        // Player 4 movement
        if (this.players[3].getAButton()) {
            playerY[3] = playerY[3] + 15;
        }

        if (this.players[3].getBButton()) {
            playerX[3] = playerX[3] + 15;
        }

        if (this.players[3].getXButton()) {
            playerX[3] = playerX[3] - 15;
        }

        if (this.players[3].getYButton()) {
            playerY[3] = playerY[3] - 15;
        }

        for (int i = 0; i < playerControllers.length; i++) { // hitbox for coin
            if (playerX[i] + 25 >= coinX &&
                    playerX[i] - 25 <= coinX &&
                    playerY[i] + 25 >= coinY &&
                    playerY[i] - 25 <= coinY) {

                if (!orders.contains(i)) { // add to arraylist
                    orders.add(i);
                }

            }
        }

        boolean gameDone = orders.size() == 4; // scoring

        if (gameDone) {
            for (int i = orders.size() - 1; i >= 0; i--) {
                scores[(orders.get(i))] += i + 1;
            }
            App.switchGame(new Leaderboard(dc, playerControllers, scores));
        }

        dc.redraw();
        dc.pause(20);

    }
}

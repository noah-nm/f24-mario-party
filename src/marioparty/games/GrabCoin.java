package marioparty.games;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import DLibX.DConsole; //1200 , 800
import marioparty.App;
import marioparty.menus.Leaderboard;
import marioparty.utils.AbstractGamepad;

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
    
    // winner order
    ArrayList<Integer> orders = new ArrayList<>();

    public void draw() { // draw stuff

        // coins
        this.dc.setPaint(new Color(255, 215, 0));
        this.dc.fillEllipse(coinX, coinY, 30, 50);

        // players
        this.dc.setPaint(Color.RED);
        this.dc.fillRect(playerX[0], playerY[0], 50, 50);
        this.dc.setPaint(Color.BLUE);
        this.dc.fillRect(playerX[1], playerY[1], 50, 50);
        this.dc.setPaint(Color.GREEN);
        this.dc.fillRect(playerX[2], playerY[2], 50, 50);
        this.dc.setPaint(Color.YELLOW);
        this.dc.fillRect(playerX[3], playerY[3], 50, 50);
    }


    @Override
    public void play() {

        this.dc.clear();

        this.draw();

        for (AbstractGamepad player : this.playerControllers) { // poll controller
            player.poll();
        }

        // Player 1 movement
        if (this.players[0].getAButton()) {
            this.playerY[0] = this.playerY[0] + 15;
        }

        if (this.players[0].getBButton()) {
            this.playerX[0] = this.playerX[0] + 15;
        }

        if (this.players[0].getXButton()) {
            this.playerX[0] = this.playerX[0] - 15;
        }

        if (this.players[0].getYButton()) {
            this.playerY[0] = this.playerY[0] - 15;
        }

        // Player 2 movement
        if (this.players[1].getAButton()) {
            this.playerY[1] = this.playerY[1] + 15;
        }

        if (this.players[1].getBButton()) {
            this.playerX[1] = this.playerX[1] + 15;
        }

        if (this.players[1].getXButton()) {
            this.playerX[1] = this.playerX[1] - 15;
        }

        if (this.players[1].getYButton()) {
            this.playerY[1] = this.playerY[1] - 15;
        }

        // Player 3 movement
        if (this.players[2].getAButton()) {
            this.playerY[2] = this.playerY[2] + 15;
        }

        if (this.players[2].getBButton()) {
            this.playerX[2] = this.playerX[2] + 15;
        }

        if (this.players[2].getXButton()) {
            this.playerX[2] = this.playerX[2] - 15;
        }

        if (this.players[2].getYButton()) {
            this.playerY[2] = this.playerY[2] - 15;
        }

        // Player 4 movement
        if (this.players[3].getAButton()) {
            this.playerY[3] = this.playerY[3] + 15;
        }

        if (this.players[3].getBButton()) {
            this.playerX[3] = this.playerX[3] + 15;
        }

        if (this.players[3].getXButton()) {
            this.playerX[3] = this.playerX[3] - 15;
        }

        if (this.players[3].getYButton()) {
            this.playerY[3] = this.playerY[3] - 15;
        }

        for (int i = 0; i < playerControllers.length; i++) { // hitbox for coin
            if (this.playerX[i] + 25 >= this.coinX &&
                    this.playerX[i] - 25 <= this.coinX &&
                    this.playerY[i] + 25 >= this.coinY &&
                    this.playerY[i] - 25 <= this.coinY) {

                if (!this.orders.contains(i)) { // add to arraylist
                    this.orders.add(i);
                }

            }
        }

        boolean gameDone = this.orders.size() == 4; // scoring

        if (gameDone) {
            for (int i = this.orders.size() - 1; i >= 0; i--) {
                scores[this.orders.get(i)] += i + 1;
            }
            App.switchGame(new Leaderboard(this.dc, this.playerControllers, this.scores));
        }

        this.dc.redraw();
        this.dc.pause(20);

    }
}

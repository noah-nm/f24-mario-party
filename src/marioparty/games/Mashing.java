package marioparty.games;

import marioparty.App;
import marioparty.menus.Leaderboard;
import marioparty.utils.AbstractGamepad;
import DLibX.DConsole; //1200 , 800
import java.awt.Color;
import java.util.ArrayList;
import java.awt.Font;

public class Mashing extends Game {
    private DConsole d;
    private AbstractGamepad[] player;
    int[] scores = new int[4];
    int[] count = new int[4];

    public Mashing(DConsole dc, AbstractGamepad[] playerControllers, int[] scores) { // inheritance stuff
        super(dc, playerControllers, scores);
        this.player = playerControllers;
    }
   
        private boolean buttonIsDown = false;

    ArrayList <Integer> orders = new ArrayList<>();

    @Override
    public void play() { // game itself

        dc.clear();

        for (AbstractGamepad player : this.playerControllers) {
            player.poll();
        }

        if (this.player[0].getAButton()) { // player1 stuff
            if (!buttonIsDown) {
                count[0]++;
            }
            buttonIsDown = true;

        } else {
            buttonIsDown = false;
        }

        if (this.player[1].getAButton()) { // player2 stuff
            if (!buttonIsDown) {
                count[1]++;
            }
            buttonIsDown = true;
        } else {
            buttonIsDown = false;
        }

        if (this.player[2].getAButton()) { // player3 stuff
            if (!buttonIsDown) {
                count[2]++;
            }
            buttonIsDown = true;
        } else {
            buttonIsDown = false;
        }

        if (this.player[3].getAButton()) { // player4 stuff
            if (!buttonIsDown) {
                count[3]++;
            }
            buttonIsDown = true;
        } else {
            buttonIsDown = false;
        }

        dc.setPaint(new Color(135, 206, 235));
        dc.fillRect(500, 500, 50000, 500000);

        Font arialTitle = new Font("arial", 1, 100);// displaying count
        this.dc.setFont(arialTitle);
        this.dc.setPaint(Color.BLACK);
        this.dc.drawString(count[0], 300, 300);
        this.dc.drawString(count[1], 900, 300);
        this.dc.drawString(count[2], 300, 600);
        this.dc.drawString(count[3], 900, 600);

        dc.redraw();
        dc.pause(20);

        boolean gameDone = orders.size() == 4;

        for (int i = 0; i < count.length; i++) {
            if (count[i] <= 150 && orders.contains(count[i])) { // scores
                orders.add(count[i]);
            }
        } 

        if(gameDone){
           for (int i = orders.size()-1; i <= 0; i--) {
                scores[(orders.get(i))] += i+1;
           }
           App.switchGame(new Leaderboard(dc, playerControllers, scores));
        }
    }
}

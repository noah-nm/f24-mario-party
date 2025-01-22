package marioparty.menus;

import java.awt.Color;
import DLibX.DConsole;
import marioparty.utils.AbstractGamepad;
import marioparty.games.Game;
import java.awt.Font;


public class Winner extends Game {

    public Winner(DConsole dc, AbstractGamepad[] playerControllers, int[] scores) {
        super(dc, playerControllers, scores);
    }

    @Override
    public void play() {

        while(true) {
            this.checkClose();
            this.drawGui();
        }

    }

    private void drawGui() {

        // fonts
        Font arialTitle = new Font("arial", 1, 100);
        Font subText = new Font("arial", 1, 60);

        // colors
        Color lightBlue = new Color(121, 224, 218, 200);

        dc.clear();

        // background
        this.dc.setOrigin(DConsole.ORIGIN_TOP_LEFT);
        this.dc.setPaint(lightBlue);
        this.dc.fillRect(0, 0, this.dc.getWidth(), this.dc.getHeight());

        // say who won
        this.dc.setOrigin(DConsole.ORIGIN_CENTER);
        this.dc.setPaint(Color.RED);
        this.dc.setFont(arialTitle);
        this.dc.drawString("The Winner is Player " + getWinner() + "!", this.dc.getWidth() / 2, this.dc.getHeight() / 2);

        // exit propt
        this.dc.setFont(subText);
        this.dc.drawString("Press X to Exit", this.dc.getWidth() / 2, this.dc.getHeight() / 2 + 100);

        dc.redraw();
        DConsole.pause(20);
    }

    private int getWinner() {
        int highestIndex = 0;
        int highestScore = scores[0];
    
        for (int i = 1; i < scores.length; i++) {
            if (scores[i] >= highestScore) {
                highestScore = scores[i];
                highestIndex = i;
            }
        }
        return highestIndex + 1;
    }
    

    private void checkClose() {
        for(AbstractGamepad player : playerControllers) {
            player.poll();
            if(player.getXButton()) {
                System.exit(0);
            }
        }
    }
    
}

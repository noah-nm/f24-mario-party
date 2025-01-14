package games;

import utils.AbstractGamepad;
import DLibX.DConsole; //1200 , 800
import java.awt.Color;
import java.util.ArrayList;
import java.awt.Font;

public class Mashing extends Game {
    private DConsole d;
    private AbstractGamepad[] player;

    public Mashing(DConsole dc, AbstractGamepad[] playerControllers) { // inheritance stuff
        super(dc, playerControllers);
        this.player = playerControllers;
    }

    private int count1 = 0;
    private int count2 = 0;
    private int count3 = 0;
    private int count4 = 0;

    @Override
    public void play() { // game itself
        dc.clear();

        for (AbstractGamepad player : this.playerControllers) {
            player.poll();
        }

        if (this.player[0].getAButton()) {
            count1++;
        }
        if (this.player[1].getAButton()) {
            count2++;
        }

        if (this.player[2].getAButton()) {
            count3++;
        }

        if (this.player[3].getAButton()) {
            count4++;
        }

        dc.setPaint(new Color(135, 206, 235));
        dc.fillRect(500, 500, 50000, 500000);

        Font arialTitle = new Font("arial", 1, 100);
        this.dc.setFont(arialTitle);
        this.dc.setPaint(Color.BLACK);
        this.dc.drawString(count1, 300, 300);
        this.dc.drawString(count2, 900, 300);
        this.dc.drawString(count3, 300, 600);
        this.dc.drawString(count4, 900, 600);

        dc.redraw();
        dc.pause(20);
    }
}

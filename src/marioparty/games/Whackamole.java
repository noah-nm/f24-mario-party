package marioparty.games;

import DLibX.DConsole;
import marioparty.App;
import marioparty.games.Game;
import marioparty.menus.Leaderboard;
import marioparty.utils.AbstractGamepad;

import java.util.Arrays;
import java.util.Random;
import java.awt.Color;

public class Whackamole extends Game{
    private Random random;
    
    public Whackamole(DConsole dc, AbstractGamepad[] players, int[] scores) {
        super(dc, players, scores);
        this.random = new Random();
    }

    @Override
    public void play() {
        Random random = new Random();
        int x;
        int y;
        int size = random.nextInt(30) + 15;
        long start;
        double[] times = new double[4];
        int[] amountOfMoles = new int[4];


        for(int rounds = 0; rounds < 4; rounds++) {
            while(true){
            x = random.nextInt(dc.getWidth() - size);
            y = random.nextInt(dc.getHeight() - size);
            // I use nanoseconds because of errors with it displaying or sending the wrong amount of time
            // Nanoseconds worked so I just used it
            start = System.nanoTime();

            dc.clear();
            dc.setPaint(Color.RED);
            dc.fillEllipse(x, y, size, size);
            dc.redraw();


            while(!dc.isMouseButton(1)) {
                DConsole.pause(10);
            }

            while(dc.isMouseButton(1)) {
                int mouseX = dc.getMouseXPosition();
                int mouseY = dc.getMouseYPosition();
    
                if (dc.isMouseButton(1)) {
                    if (mouseX >= x && mouseX <= x + size && mouseY >= y && mouseY <= y + size) {
                        int radius = size / 2;
                        int centerX = x + radius;
                        int centerY = y + radius;
    
                        if (Math.abs(mouseX - centerX) <= radius && Math.abs(mouseY - centerY) <= radius) {
                            amountOfMoles[rounds] += 1;
                            times[rounds] += (System.nanoTime() - start) / 1000000000;
                        }
                    }
                }
                DConsole.pause(10);
            }
            if(amountOfMoles[rounds] == 10) {
                break;
            }
        }
        }
        dc.clear();
        dc.redraw();

        // increments scores
        if(times[0] < times[1] && times[0] < times[2] && times[0] < times[3]) {
            scores[0] += 4;
        } else if(times[1] < times[0] && times[1] < times[2] && times[1] < times[3]) {
            scores[1] += 4;
        } else if(times[2] < times[0] && times[2] < times[1] && times[2] < times[3]) {
            scores[2] += 4;
        } else {
            scores[3] += 4;
        }

        App.switchGame(new Leaderboard(dc, playerControllers, scores));
    }
}


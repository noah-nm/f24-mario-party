package marioparty.games;

import DLibX.DConsole;
import marioparty.utils.AbstractGamepad;

import java.util.Random;
import java.awt.Color;
public class ReactionGame extends Game{
    private DConsole dc;
    private Random random;
    
    public ReactionGame(DConsole dc, AbstractGamepad[] players, int[] scores) {
        super(dc, players, scores);
        this.random = new Random();
    }

    @Override
    public void play() {
        int x = 100;
        int y = 100;
        int size = 100;
        long start;
        long fastest;
        double[] times = new double[4];


        for(int rounds = 0; rounds < 4; rounds++) {
            x = random.nextInt(dc.getWidth() - size);
            y = random.nextInt(dc.getHeight() - size);

            dc.clear();
            dc.setPaint(Color.RED);
            dc.fillEllipse(x, y, size, size);
            dc.redraw();

            start = System.nanoTime();
            
            // I don't know how to implement controller but otherwise this is 4 player if we use mouse
            // It compares all 4 players scores to select who wins
            while(!dc.isMouseButton(1)) {
                DConsole.pause(10);
            }

            while(dc.isMouseButton(1)) {
                DConsole.pause(10);
            }

            fastest = System.nanoTime() - start;
            double fastestSeconds = fastest / 1_000_000_000.0;
            fastestSeconds = Math.round(fastestSeconds * 100.0) / 100.0;
            times[rounds] = fastestSeconds;
            System.out.println("Reaction: " + fastestSeconds + " time");
        }
        dc.clear();
        dc.redraw();
        if(times[0] < times[1] && times[0] < times[2] && times[0] < times[3]) {
            System.out.println("Player 1 is the winner with " + times[0] + " reaction time");
        } else if(times[1] < times[0] && times[1] < times[2] && times[1] < times[3]) {
            System.out.println("Player 2 is the winner with " + times[1] + " reaction time");
        } else if(times[2] < times[0] && times[2] < times[1] && times[2] < times[3]) {
            System.out.println("Player 3 is the winner with " + times[2] + " reaction time"); 
        } else {
            System.out.println("Player 4 is the winner with " + times[3] + " reaction time");  
        }
    }
}

package marioparty.games;

import DLibX.DConsole;
import marioparty.App;
import marioparty.menus.Leaderboard;
import marioparty.utils.AbstractGamepad;
import java.awt.Color;
import java.awt.Font;

public class GuessingGame extends Game {

    public GuessingGame(DConsole dc, AbstractGamepad[] playerControllers, int[] scores) {
        super(dc, playerControllers, scores);
    }

    public void play() {

        dc.clear();
        int randomNumber = (int) (Math.random() * 100);
        int[] guesses = new int[4];
        guesses[0] = 50;
        guesses[1] = 50;
        guesses[2] = 50;
        guesses[3] = 50;
        boolean[] guessesLocked = new boolean[4];

        Font arialBig = new Font("arial", 1, 80);
        Font arialMedium = new Font("arial", 1, 40);

        dc.setFont(arialBig);
        dc.setPaint(Color.BLACK);
        dc.clear();
        dc.drawString("Triggers to change guess", dc.getWidth() / 2, dc.getHeight() / 2);
        dc.redraw();
        DConsole.pause(2000);

        dc.clear();
        dc.drawString("Start to confirm guess", dc.getWidth() / 2, dc.getHeight() / 2);
        dc.redraw();
        DConsole.pause(2000);

        dc.clear();
        dc.drawString("Back to unconfirm guess", dc.getWidth() / 2, dc.getHeight() / 2);
        dc.redraw();
        DConsole.pause(2000);

        while (true) {

            for (AbstractGamepad playerController : playerControllers) {
                playerController.poll();
            }

            dc.clear();
            dc.setPaint(Color.BLACK);
            dc.setOrigin(DConsole.ORIGIN_CENTER);

            dc.setFont(arialBig);
            dc.drawString("Guess the number!", dc.getWidth() / 2, 40);

            dc.setPaint(Color.RED);
            dc.setFont(arialMedium);
            dc.drawString(1, dc.getWidth() / 2, 120);
            dc.setFont(arialBig);
            dc.drawString(guesses[0], dc.getWidth() / 2, 170);

            dc.setPaint(Color.BLUE);
            dc.setFont(arialMedium);
            dc.drawString(2, dc.getWidth() / 2, 240);
            dc.setFont(arialBig);
            dc.drawString(guesses[1], dc.getWidth() / 2, 290);

            dc.setPaint(Color.GREEN);
            dc.setFont(arialMedium);
            dc.drawString(3, dc.getWidth() / 2, 360);
            dc.setFont(arialBig);
            dc.drawString(guesses[2], dc.getWidth() / 2, 410);

            dc.setPaint(new Color(112, 3, 255));
            dc.setFont(arialMedium);
            dc.drawString(4, dc.getWidth() / 2, 480);
            dc.setFont(arialBig);
            dc.drawString(guesses[3], dc.getWidth() / 2, 530);

            if (playerControllers[0].getTriggers() > 0.5 && guesses[0] < 100 && guessesLocked[0] == false) {
                guesses[0]--;
            } else if (playerControllers[0].getTriggers() < -0.5 && guesses[0] > 0 && guessesLocked[0] == false) {
                guesses[0]++;
            }
            if (playerControllers[1].getTriggers() > 0.5 && guesses[1] < 100 && guessesLocked[1] == false) {
                guesses[1]--;
            } else if (playerControllers[1].getTriggers() < -0.5 && guesses[1] > 0 && guessesLocked[1] == false) {
                guesses[1]++;
            }
            if (playerControllers[2].getTriggers() > 0.5 && guesses[2] < 100 && guessesLocked[2] == false) {
                guesses[2]--;
            } else if (playerControllers[2].getTriggers() < -0.5 && guesses[2] > 0 && guessesLocked[2] == false) {
                guesses[2]++;
            }
            if (playerControllers[3].getTriggers() > 0.5 && guesses[3] < 100 && guessesLocked[3] == false) {
                guesses[3]--;
            } else if (playerControllers[3].getTriggers() < -0.5 && guesses[3] > 0 && guessesLocked[3] == false) {
                guesses[3]++;
            }
            if (playerControllers[0].getStartButton()) {
                guessesLocked[0] = true;
            }
            if (playerControllers[1].getStartButton()) {
                guessesLocked[1] = true;
            }
            if (playerControllers[2].getStartButton()) {
                guessesLocked[2] = true;
            }
            if (playerControllers[3].getStartButton()) {
                guessesLocked[3] = true;
            }

            if (playerControllers[0].getBackButton()) {
                guessesLocked[0] = false;
            }
            if (playerControllers[1].getBackButton()) {
                guessesLocked[1] = false;
            }
            if (playerControllers[2].getBackButton()) {
                guessesLocked[2] = false;
            }
            if (playerControllers[3].getBackButton()) {
                guessesLocked[3] = false;
            }

            if (guessesLocked[0]) {
                this.dc.setPaint(Color.RED);
                this.dc.fillEllipse(dc.getWidth() - 110, dc.getHeight() - 25, 20, 20);
            }
            if (guessesLocked[1]) {
                this.dc.setPaint(Color.BLUE);
                this.dc.fillEllipse(dc.getWidth() - 80, dc.getHeight() - 25, 20, 20);
            }
            if (guessesLocked[2]) {
                this.dc.setPaint(Color.GREEN);
                this.dc.fillEllipse(dc.getWidth() - 50, dc.getHeight() - 25, 20, 20);
            }
            if (guessesLocked[3]) {
                this.dc.setPaint(Color.YELLOW);
                this.dc.fillEllipse(dc.getWidth() - 20, dc.getHeight() - 25, 20, 20);
            }

            DConsole.pause(100);
            dc.redraw();

            if (guessesLocked[0] & guessesLocked[1] && guessesLocked[2] && guessesLocked[3]) {
                break;
            }
        }

        dc.clear();
        dc.setFont(arialBig);
        dc.setPaint(Color.BLACK);
        dc.drawString("The number was " + randomNumber, dc.getWidth() / 2, dc.getHeight() / 2);
        dc.redraw();
        DConsole.pause(3000);

        if (guesses[0] >= randomNumber) {
            guesses[0] -= randomNumber;
        } else {
            guesses[0] = randomNumber - guesses[0];
        }
        if (guesses[1] >= randomNumber) {
            guesses[1] -= randomNumber;
        } else {
            guesses[1] = randomNumber - guesses[1];
        }
        if (guesses[2] >= randomNumber) {
            guesses[2] -= randomNumber;
        } else {
            guesses[2] = randomNumber - guesses[2];
        }
        if (guesses[3] >= randomNumber) {
            guesses[3] -= randomNumber;
        } else {
            guesses[3] = randomNumber - guesses[3];
        }

        int first = -1;
        int second = -1;
        int third = -1;
        int fourth = -1;

        for(int i = 0; i == 100; i++){
            for(int j = 0; j < 4; j++){
                if(guesses[j] == i && first == -1){
                    first = j;
                }else if(guesses[j] == i && second == -1){
                    second = j;
                }else if(guesses[j] == i && third == -1){
                    third = j;
                }else if(guesses[j] == i && fourth == -1){
                    fourth = j;
                }
            }
        }

        for(int i = 0; i < 4; i++){
            if(first == i){
                scores[i]+= 4;
            }else if(second == i){
                scores[i]+= 3;
            }else if(third == i){
                scores[i]+= 2;
            }else if(fourth == i){
                scores[i]+= 1;
            }
        }

        App.switchGame(new Leaderboard(dc, playerControllers, scores));
    }
}

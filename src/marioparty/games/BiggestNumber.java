package marioparty.games;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import DLibX.DConsole;
import marioparty.App;
import marioparty.menus.Leaderboard;
import marioparty.utils.AbstractGamepad;

public class BiggestNumber extends Game {

    public BiggestNumber(DConsole dc, AbstractGamepad[] playerControllers, int[] scores) {
        super(dc, playerControllers, scores);
    }

    public void play() {

        Random r = new Random();

        //players guessses
        int number0 = 0;
        int number1 = 0;
        int number2 = 0;
        int number3 = 0;
        int guess = r.nextInt(19) + 1;
        //making the button so cant be held
        boolean buttonLow0 = false;
        boolean buttonLow1 = false;
        boolean buttonLow2 = false;
        boolean buttonLow3 = false;
        //keeps track of # of guesses
        int count0 = 0;
        int count1 = 0;
        int count2 = 0;
        int count3 = 0;
        int[] place = new int[4];
        //points
        int rankCounter = 0;
        //whos finished
        boolean printed0 = false;
        boolean printed1 = false;
        boolean printed2 = false;
        boolean printed3 = false;

        while (true) {
            dc.clear();

            for (AbstractGamepad player : this.playerControllers) {
                player.poll();
            }

            //background
            dc.setPaint(Color.PINK);
            dc.fillRect(500, 500, 10000, 10000);

            //while player 1 hasnt guesses right
            if (number0 != guess) {
                if (playerControllers[0].getAButton()) {
                    if (!buttonLow0) {
                        number0 = r.nextInt(15) + 1;
                        count0++;
                    }
                    buttonLow0 = true;
                } else {
                    buttonLow0 = false;
                }

                //when they guess right
                if (number0 == guess) {
                    if (printed0 == false) {
                        place[0] = 4 - rankCounter;
                        rankCounter++;
                        printed0 = true;
                    }
                }
            }

            //while player2 hasnt guessed right
            if (number1 != guess) {
                if (playerControllers[1].getAButton()) {
                    if (!buttonLow1) {
                        number1 = r.nextInt(15) + 1;
                        count1++;
                    }
                    buttonLow1 = true;
                } else {
                    buttonLow1 = false;
                }

                //when they huess right
                if (number1 == guess) {
                    if (printed1 == false) {
                        place[1] = 4 - rankCounter;
                        rankCounter++;
                        printed1 = true;
                    }
                }
            }

            //while player 3 hasnt guessed right
            if (number2 != guess) {
                if (playerControllers[2].getAButton()) {
                    if (!buttonLow2) {
                        number2 = r.nextInt(15) + 1;
                        count2++;
                    }
                    buttonLow2 = true;
                } else {
                    buttonLow2 = false;
                }

                //player 3 guessed right
                if (number2 == guess) {
                    if (printed2 == false) {
                        place[2] = 4 - rankCounter;
                        rankCounter++;
                        printed2 = true;
                    }
                }
            }

            //while player 4 hasnt guessed right
            if (number3 != guess) {
                if (playerControllers[3].getAButton()) {
                    if (!buttonLow3) {
                        number3 = r.nextInt(15) + 1;
                        count3++;
                    }
                    buttonLow3 = true;
                } else {
                    buttonLow3 = false;
                }

                //player 4 guessed right
                if (number3 == guess) {
                    if (printed3 == false) {
                        place[3] = 4 - rankCounter;
                        rankCounter++;
                        printed3 = true;
                    }
                }
            }

            //color for the players
            dc.setPaint(Color.RED);
            dc.fillRect(840, 240, 100, 80);

            dc.setPaint(Color.BLUE);
            dc.fillRect(240, 240, 100, 80);

            dc.setPaint(Color.GREEN);
            dc.fillRect(840, 640, 100, 80);

            dc.setPaint(Color.yellow);
            dc.fillRect(240, 640, 100, 80);

            //shows # of guesses and player guesses
            dc.setPaint(Color.BLACK);
            dc.drawString("Number: " + number0, 840, 290);
            dc.drawString("Guess# " + count0, 840, 240);

            dc.drawString("Number: " + number1, 240, 290);
            dc.drawString("Guess# " + count1, 240, 240);

            dc.drawString("Number: " + number2, 840, 690);
            dc.drawString("Guess# " + count2, 840, 640);

            dc.drawString("Number: " + number3, 240, 690);
            dc.drawString("Guess# " + count3, 240, 640);

            dc.drawString("Guess " + guess, 500, 400);

            //when all players finised
            if (rankCounter == 4) {
                scores[0] += place[0];
                scores[1] += place[1];
                scores[2] += place[2];
                scores[3] += place[3];
                break;
            }
            App.switchGame(new Leaderboard(dc, playerControllers, scores));
            dc.redraw();
            DConsole.pause(20);

            //end
        }
    }
}

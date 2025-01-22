package marioparty.games;

import DLibX.DConsole;
import marioparty.App;
import marioparty.menus.Leaderboard;
import marioparty.utils.AbstractGamepad;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.awt.Font;
import java.awt.Color;
import java.util.ArrayList;

public class Trivia extends Game {

    public Trivia(DConsole dc, AbstractGamepad[] playerControllers, int[] scores) {
        super(dc, playerControllers, scores);
    }

    public void play() {

        boolean used = false;
        int randomNumber;
        ArrayList<Integer> usedQuestions = new ArrayList<Integer>();
        int correctAnswers[] = new int[4];
        int playerAnswers[] = new int[4];
        boolean answersLocked[] = new boolean[4];
        boolean allLocked = false;
        String question = "";
        String[] answers = new String[4];
        int correctAnswer = -1;
        int timer;
        long startTime;
        long timeElapsed;

        DConsole.pause(1000);

        Font arialMedium = new Font("arial", Font.BOLD, 30);
        Font bigFont = new Font("arial", Font.BOLD, 50);
        Font crazyFont = new Font("arial", Font.BOLD, 15);
        Font crazierFont = new Font("arial", Font.BOLD, 35);

        dc.setPaint(Color.BLACK);
        dc.setFont(crazierFont);

        dc.clear();
        dc.drawString("There will be 5 questions", dc.getWidth() / 2, dc.getHeight() / 2);
        dc.redraw();
        DConsole.pause(2000);

        dc.clear();
        dc.drawString("A, B, X, Y to select an answer", dc.getWidth() / 2, dc.getHeight() / 2);
        dc.redraw();
        DConsole.pause(2000);

        dc.clear();
        dc.drawString("Start to confirm answer", dc.getWidth() / 2, dc.getHeight() / 2);
        dc.redraw();
        DConsole.pause(2000);

        dc.clear();
        dc.drawString("Back to uncomfirm answer", dc.getWidth() / 2, dc.getHeight() / 2);
        dc.redraw();
        DConsole.pause(2000);

        for (int i = 1; i <= 5; i++) {

            dc.clear();

            do {
                randomNumber = ((int) (Math.random() * 20) * 6) + 1;
                used = false;

                for (int j = 0; j < usedQuestions.size(); j++) {
                    if (randomNumber == usedQuestions.get(j)) {
                        used = true;
                    }
                }

            } while (used == true);
            usedQuestions.add(randomNumber);

            File file = new File("src\\marioparty\\games\\TriviaQuestions.txt");

            try (BufferedReader br = new BufferedReader(new FileReader(file))) {

                String line;
                int currentLine = 1;

                while ((line = br.readLine()) != null) {
                    if (currentLine == randomNumber) {
                        question = line;
                    }
                    if (currentLine == randomNumber + 1) {
                        answers[0] = line;
                    }
                    if (currentLine == randomNumber + 2) {
                        answers[1] = line;
                    }
                    if (currentLine == randomNumber + 3) {
                        answers[2] = line;
                    }
                    if (currentLine == randomNumber + 4) {
                        answers[3] = line;
                    }
                    if (currentLine == randomNumber + 5) {
                        correctAnswer = Integer.parseInt(line);
                    }
                    currentLine++;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            playerAnswers[0] = 0;
            playerAnswers[1] = 0;
            playerAnswers[2] = 0;
            playerAnswers[3] = 0;
            answersLocked[0] = false;
            answersLocked[1] = false;
            answersLocked[2] = false;
            answersLocked[3] = false;
            allLocked = false;

            timer = 15;

            startTime = System.currentTimeMillis();

            while (timer != 0 && allLocked == false) {

                for (AbstractGamepad playerController : playerControllers) {
                    playerController.poll();
                }

                timeElapsed = System.currentTimeMillis() - startTime;
                timer = 15 - Math.toIntExact(timeElapsed / 1000);

                this.dc.clear();

                this.dc.setFont(bigFont);
                this.dc.drawString(timer, 60, dc.getHeight() - 60);

                this.dc.setOrigin(DConsole.ORIGIN_CENTER);

                this.dc.setFont(arialMedium);

                this.dc.drawString(question, dc.getWidth() / 2, 75);

                this.dc.setPaint(Color.YELLOW);
                this.dc.fillEllipse(dc.getWidth() / 2, 150, 30, 30);
                this.dc.setPaint(Color.BLUE);
                this.dc.fillEllipse(dc.getWidth() / 4 - 100, dc.getHeight() / 2 + 100, 30, 30);
                this.dc.setPaint(Color.RED);
                this.dc.fillEllipse(dc.getWidth() / 4 * 3 + 100, dc.getHeight() / 2 + 100, 30, 30);
                this.dc.setPaint(Color.GREEN);
                this.dc.fillEllipse(dc.getWidth() / 2, 750, 30, 30);

                this.dc.setPaint(Color.BLACK);
                this.dc.setFont(crazyFont);
                this.dc.drawString("Y", dc.getWidth() / 2, 145);
                this.dc.drawString("X", dc.getWidth() / 4 - 100, dc.getHeight() / 2 + 95);
                this.dc.drawString("B", dc.getWidth() / 4 * 3 + 100, dc.getHeight() / 2 + 95);
                this.dc.drawString("A", dc.getWidth() / 2, 745);

                this.dc.setFont(crazierFont);
                this.dc.drawString(answers[0], dc.getWidth() / 2, 180);
                this.dc.drawString(answers[1], dc.getWidth() / 4 - 100, dc.getHeight() / 2 + 50);
                this.dc.drawString(answers[2], dc.getWidth() / 4 * 3 + 100, dc.getHeight() / 2 + 50);
                this.dc.drawString(answers[3], dc.getWidth() / 2, 700);

                if (playerControllers[0].getYButton() && answersLocked[0] == false) {
                    playerAnswers[0] = 1;
                }
                if (playerControllers[0].getXButton() && answersLocked[0] == false) {
                    playerAnswers[0] = 2;
                }
                if (playerControllers[0].getBButton() && answersLocked[0] == false) {
                    playerAnswers[0] = 3;
                }
                if (playerControllers[0].getAButton() && answersLocked[0] == false) {
                    playerAnswers[0] = 4;
                }
                if (playerControllers[0].getStartButton()) {
                    answersLocked[0] = true;
                }
                if (playerControllers[0].getBackButton()) {
                    answersLocked[0] = false;
                }
                if (playerControllers[1].getYButton() && answersLocked[1] == false) {
                    playerAnswers[1] = 1;
                }
                if (playerControllers[1].getXButton() && answersLocked[1] == false) {
                    playerAnswers[1] = 2;
                }
                if (playerControllers[1].getBButton() && answersLocked[1] == false) {
                    playerAnswers[1] = 3;
                }
                if (playerControllers[1].getAButton() && answersLocked[1] == false) {
                    playerAnswers[1] = 4;
                }
                if (playerControllers[1].getStartButton()) {
                    answersLocked[1] = true;
                }
                if (playerControllers[1].getBackButton()) {
                    answersLocked[1] = false;
                }
                if (playerControllers[2].getYButton() && answersLocked[2] == false) {
                    playerAnswers[2] = 1;
                }
                if (playerControllers[2].getXButton() && answersLocked[2] == false) {
                    playerAnswers[2] = 2;
                }
                if (playerControllers[2].getBButton() && answersLocked[2] == false) {
                    playerAnswers[2] = 3;
                }
                if (playerControllers[2].getAButton() && answersLocked[2] == false) {
                    playerAnswers[2] = 4;
                }
                if (playerControllers[2].getStartButton()) {
                    answersLocked[2] = true;
                }
                if (playerControllers[2].getBackButton()) {
                    answersLocked[2] = false;
                }
                if (playerControllers[3].getYButton() && answersLocked[3] == false) {
                    playerAnswers[3] = 1;
                }
                if (playerControllers[3].getXButton() && answersLocked[3] == false) {
                    playerAnswers[3] = 2;
                }
                if (playerControllers[3].getBButton() && answersLocked[3] == false) {
                    playerAnswers[3] = 3;
                }
                if (playerControllers[3].getAButton() && answersLocked[3] == false) {
                    playerAnswers[3] = 4;
                }
                if (playerControllers[3].getStartButton()) {
                    answersLocked[3] = true;
                }
                if (playerControllers[3].getBackButton()) {
                    answersLocked[3] = false;
                }
                if (answersLocked[0] && answersLocked[1] && answersLocked[2] && answersLocked[3]) {
                    allLocked = true;
                    DConsole.pause(1000);
                } else {
                    allLocked = false;
                }
                if (answersLocked[0]) {
                    this.dc.setPaint(Color.RED);
                    this.dc.fillEllipse(dc.getWidth() - 110, dc.getHeight() - 25, 20, 20);
                }
                if (answersLocked[1]) {
                    this.dc.setPaint(Color.BLUE);
                    this.dc.fillEllipse(dc.getWidth() - 80, dc.getHeight() - 25, 20, 20);
                }
                if (answersLocked[2]) {
                    this.dc.setPaint(Color.GREEN);
                    this.dc.fillEllipse(dc.getWidth() - 50, dc.getHeight() - 25, 20, 20);
                }
                if (answersLocked[3]) {
                    this.dc.setPaint(Color.YELLOW);
                    this.dc.fillEllipse(dc.getWidth() - 20, dc.getHeight() - 25, 20, 20);
                }

                this.dc.redraw();
            }

            if (playerAnswers[0] == correctAnswer) {
                correctAnswers[0]++;
            }
            if (playerAnswers[1] == correctAnswer) {
                correctAnswers[1]++;
            }
            if (playerAnswers[2] == correctAnswer) {
                correctAnswers[2]++;
            }
            if (playerAnswers[3] == correctAnswer) {
                correctAnswers[3]++;
            }

            dc.clear();
            dc.setFont(crazierFont);
            dc.setPaint(Color.BLACK);
            dc.drawString("Correct Answer Is: " + answers[correctAnswer - 1], dc.getWidth() / 2,
                    dc.getHeight() / 4 - 100);

            dc.drawString("Leaderboard", dc.getWidth() / 2, dc.getHeight() / 2 - 50);

            dc.setPaint(Color.RED);
            dc.drawString(correctAnswers[0], dc.getWidth() / 2, dc.getHeight() / 2);

            dc.setPaint(Color.BLUE);
            dc.drawString(correctAnswers[1], dc.getWidth() / 2, dc.getHeight() / 2 + 50);

            dc.setPaint(Color.GREEN);
            dc.drawString(correctAnswers[2], dc.getWidth() / 2, dc.getHeight() / 2 + 100);

            dc.setPaint(Color.YELLOW);
            dc.drawString(correctAnswers[3], dc.getWidth() / 2, dc.getHeight() / 2 + 150);

            dc.redraw();
            DConsole.pause(5000);

        }

        int first = -1;
        int second = -1;
        int third = -1;
        int fourth = -1;

        for (int i = 5; i == 0; i--) {
            if (scores[0] == i) {
                if (first == -1 || first == i) {
                    scores[0] += 4;
                } else if (second == -1 || second == i) {
                    scores[0] += 3;
                } else if (third == -1 || third == i) {
                    scores[0] += 2;
                } else if (fourth == -1 || fourth == i) {
                    scores[0] += 1;
                }
            }
            if (scores[1] == i) {
                if (first == -1 || first == i) {
                    scores[1] += 4;
                } else if (second == -1 || second == i) {
                    scores[1] += 3;
                } else if (third == -1 || third == i) {
                    scores[1] += 2;
                } else if (fourth == -1 || fourth == i) {
                    scores[1] += 1;
                }
            }
            if (scores[2] == i) {
                if (first == -1 || first == i) {
                    scores[2] += 4;
                } else if (second == -1 || second == i) {
                    scores[2] += 3;
                } else if (third == -1 || third == i) {
                    scores[2] += 2;
                } else if (fourth == -1 || fourth == i) {
                    scores[2] += 1;
                }
            }
            if (scores[3] == i) {
                if (first == -1 || first == i) {
                    scores[3] += 4;
                } else if (second == -1 || second == i) {
                    scores[3] += 3;
                } else if (third == -1 || third == i) {
                    scores[3] += 2;
                } else if (fourth == -1 || fourth == i) {
                    scores[3] += 1;
                }
            }
        }
        App.switchGame(new Leaderboard(dc, playerControllers, scores));

    }
}

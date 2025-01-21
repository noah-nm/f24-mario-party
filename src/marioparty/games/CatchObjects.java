package marioparty.games;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import DLibX.DConsole;
import marioparty.utils.AbstractGamepad;

public class CatchObjects extends Game {

  public CatchObjects(DConsole dc, AbstractGamepad[] playerControllers, int[] scores) {
    super(dc, playerControllers, scores);
  }

  public void play() {

    dc.setOrigin(DConsole.ORIGIN_BOTTOM_LEFT);

    Random randGen = new Random();
    ArrayList<Ball> balls = new ArrayList<>();

    // Create 1000 balls
    for (int i = 0; i < 25; i++) {
      Ball newBall = new Ball(dc, randGen);
      balls.add(newBall);
    }

    int boxxd = 500;
    int boxxc = 300;
    int boxxe = 700;
    int boxxf = 900;
    int collect1 = 0, collect2 = 0, collect3 = 0, collect4 = 0;

    Color colq = new Color(230, 190, 255, 255);
    Color wall = new Color(250, 160, 60, 255);

    while (true) {
      dc.clear();

      for (AbstractGamepad player : this.playerControllers) {
        player.poll();
      }

      // Controls for boxes

      if (playerControllers[0].getXButton()) {
        boxxc = boxxc - 20;
      }
      if (playerControllers[0].getBButton()) {
        boxxc = boxxc + 20;
      }
      if (playerControllers[1].getXButton()) {
        boxxd = boxxd - 20;
      }
      if (playerControllers[1].getBButton()) {
        boxxd = boxxd + 20;
      }
      if (playerControllers[2].getXButton()) {
        boxxe = boxxe - 20;
      }
      if (playerControllers[2].getBButton()) {
        boxxe = boxxe + 20;
      }
      if (playerControllers[3].getXButton()) {
        boxxf = boxxf - 20;
      }
      if (playerControllers[3].getBButton()) {
        boxxf = boxxf + 20;
      }

      // Boundary conditions for boxes
      boxxc = Math.max(0, Math.min(1200, boxxc));
      boxxd = Math.max(0, Math.min(1200, boxxd));
      boxxe = Math.max(0, Math.min(1200, boxxe));
      boxxf = Math.max(0, Math.min(1200, boxxf));

      // Draw background
      dc.setPaint(colq);
      dc.fillRect(0, 1000, 10000, 10000);

      // Draw boxes
      dc.setPaint(Color.RED);
      dc.fillRect(boxxc, 700, 100, 20);

      dc.setPaint(Color.BLUE);
      dc.fillRect(boxxd, 700, 100, 20);

      dc.setPaint(Color.GREEN);
      dc.fillRect(boxxe, 700, 100, 20);

      dc.setPaint(Color.YELLOW);
      dc.fillRect(boxxf, 700, 100, 20);

      // Move and draw balls
      for (Ball ball : balls) {
        ball.move();
        ball.draw();
      }

      // Check for ball collection
      for (Ball ball : balls) {
        double x = ball.getX();
        double y = ball.getY();

        if (x > boxxc && x < (boxxc + 100) && y > 700 && y < 730) {
          collect1++;
          ball.resetPosition();
        }

        if (x > boxxd && x < (boxxd + 100) && y > 700 && y < 730) {
          collect2++;
          ball.resetPosition();
        }

        if (x > boxxe && x < (boxxe + 100) && y > 700 && y < 730) {
          collect3++;
          ball.resetPosition();
        }

        if (x > boxxf && x < (boxxf + 100) && y > 700 && y < 730) {
          collect4++;
          ball.resetPosition();
        }

        // winners
        if (collect1 > 20) {
          dc.setPaint(Color.RED);
          dc.fillRect(0, 1000, 10000, 10000);
        }

        if (collect2 > 20) {
          dc.setPaint(Color.BLUE);
          dc.fillRect(0, 1000, 10000, 10000);
        }

        if (collect3 > 20) {
          dc.setPaint(Color.GREEN);
          dc.fillRect(0, 1000, 10000, 10000);
        }

        if (collect4 > 20) {
          dc.setPaint(Color.YELLOW);
          dc.fillRect(0, 1000, 10000, 10000);
        }
      }

      // Display scores
      dc.setPaint(wall);
      dc.fillRect(1000, 300, 300, 300);
      dc.setPaint(Color.BLACK);
      dc.drawString("Box 1: " + collect1, 1040, 200);
      dc.drawString("Box 2: " + collect2, 1040, 230);
      dc.drawString("Box 3: " + collect3, 1040, 260);
      dc.drawString("Box 4: " + collect4, 1040, 290);

      dc.redraw();
      DConsole.pause(20);

    }
  }

}

class Ball {

  // Instance variables
  private double x;
  private double y;
  private int size;
  private int xChange;
  private int yChange;
  private DConsole d;
  private Random r;
  private Color col;

  public Ball(DConsole dc, Random r) {
    this.d = dc;
    this.r = r;

    this.setPosition();

    this.size = this.r.nextInt(25) + 10;
    this.xChange = this.r.nextInt(18) - 5;
    this.yChange = this.r.nextInt(23) + 1;
    this.col = new Color(this.r.nextInt(255), this.r.nextInt(255), this.r.nextInt(255), 255);
  }

  public void draw() {
    this.d.setPaint(this.col);
    this.d.fillEllipse(this.x, this.y, this.size, this.size);
  }

  public void move() {
    this.y += this.yChange;
    this.x += this.xChange;

    if (this.y > this.d.getHeight() + this.size) {
      this.resetPosition();
    }
  }

  public void resetPosition() {
    this.x = this.r.nextInt(this.d.getWidth());
    this.y = -this.size;
  }

  public double getX() {
    return this.x;
  }

  public double getY() {
    return this.y;
  }

  private void setPosition() {
    this.x = this.r.nextInt(this.d.getWidth());
    this.y = this.r.nextInt(this.d.getHeight());
  }
}
